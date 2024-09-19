package com.chiniakin.service;

import com.chiniakin.entity.Holiday;
import com.chiniakin.exception.IncorrectDateFormatException;
import com.chiniakin.model.Money;
import com.chiniakin.repository.HolidayRepository;
import com.chiniakin.service.interfaces.VacationPayCalculatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * Реализация сервиса для расчета суммы отпускных.
 *
 * @author ChiniakinD.
 */
@Service
@RequiredArgsConstructor
public class VacationPayCalculatorServiceImpl implements VacationPayCalculatorService {

    private final HolidayRepository holidayRepository;

    /**
     * Коэффициент для перевода в копейки.
     */
    @SuppressWarnings("checkstyle:StaticVariableName")
    private static int COPEC_KOEF = 100;

    /**
     * Среднее количество рабочий дней в месяц.
     */
    @SuppressWarnings("checkstyle:StaticVariableName")
    private static int WORKING_DAYS = 21;

    private static final String DATE_FORMAT = "^(0[1-9]|[12][0-9]|3[01])\\.(0[0-9]|1[12])\\.(20\\d{2})$";

    /**
     * Расчет суммы отпускных без учета праздничных дней.
     *
     * @param averageSalary средняя зарплата.
     * @param vacationDays  количество дней отпуска.
     * @return сумму отпусных.
     */
    @Override
    public Money cashForVacation(int averageSalary, int vacationDays) {
        return new Money((((averageSalary * COPEC_KOEF) / WORKING_DAYS) * vacationDays) / COPEC_KOEF);
    }

    /**
     * Расчет суммы отпускных с учетом праздничных дней(они не оплачиваются).
     *
     * @param averageSalary средняя зарплата.
     * @param startDate     дата начала отпуска.
     * @param endDate       дата окончания отпуска.
     * @return сумму отпусных.
     */
    @Override
    public Money cashForVacationWithPeriod(int averageSalary, String startDate, String endDate) {
        isCorrectFormat(startDate, endDate);
        LocalDate startVacationDate = convertStringToLocalDate(startDate);
        LocalDate endVacationDate = convertStringToLocalDate(endDate);
        int vacationDays = 0;
        List<Holiday> holidays = holidayRepository.findAll();
        while (!startVacationDate.isAfter(endVacationDate)) {
            Holiday holiday = new Holiday(startVacationDate.getDayOfMonth(), startVacationDate.getMonthValue());
            if (!holidays.contains(holiday)) {
                vacationDays++;
            }
            startVacationDate = startVacationDate.plusDays(1);
        }
        return new Money(((averageSalary * COPEC_KOEF) / WORKING_DAYS) * vacationDays / COPEC_KOEF);
    }

    /**
     * Определяет соответствие даты требуемому формату.
     *
     * @param startDate дата начала отпуска.
     * @param endDate   дата окончания отпуска.
     */
    private void isCorrectFormat(String startDate, String endDate) {
        if (!(startDate.matches(DATE_FORMAT) && endDate.matches(DATE_FORMAT))) {
            throw new IncorrectDateFormatException("Некорректный формат даты: dd.mm.yyyy");
        }
    }

    /**
     * Преобразует строку с датой в объект LocalDate.
     *
     * @param date строка в форматк dd.mm.yyyy.
     * @return объект LocalDate.
     */
    private LocalDate convertStringToLocalDate(String date) {
        String[] dateArray = date.split("\\.");
        return LocalDate.of(Integer.parseInt(dateArray[2]), Integer.parseInt(dateArray[1]), Integer.parseInt(dateArray[0]));
    }

}
