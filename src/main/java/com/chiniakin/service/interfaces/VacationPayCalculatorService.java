package com.chiniakin.service.interfaces;

import com.chiniakin.model.Money;

/**
 * Интерфейс для сервиса для расчета суммы отпускных.
 *
 * @author ChiniakinD
 */
public interface VacationPayCalculatorService {

    Money cashForVacation(int averageSalary, int vacationDays);

    Money cashForVacationWithPeriod(int averageSalary, String startDate, String endDate);

}
