package com.chiniakin.service;

import com.chiniakin.entity.Holiday;
import com.chiniakin.exception.IncorrectDateFormatException;
import com.chiniakin.model.Money;
import com.chiniakin.repository.HolidayRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class VacationPayCalculatorServiceImplTest {

    @Mock
    private HolidayRepository holidayRepository;

    @InjectMocks
    private VacationPayCalculatorServiceImpl vacationPayCalculatorService;

    @Test
    void cashForVacationShouldReturnCorrectMoney() {
        int averageSalary = 50000;
        int vacationDays = 10;
        Money result = vacationPayCalculatorService.cashForVacation(averageSalary, vacationDays);
        assertEquals(23809, result.getMoney());
    }

    @Test
    void cashForVacationWithPeriodShouldReturnCorrectMoney() {
        int averageSalary = 50000;
        String startDate = "07.01.2024";
        String endDate = "16.01.2024";
        List<Holiday> holidays = Arrays.asList(new Holiday(7, 1), new Holiday(8, 1));
        Mockito.when(holidayRepository.findAll()).thenReturn(holidays);
        Money result = vacationPayCalculatorService.cashForVacationWithPeriod(averageSalary, startDate, endDate);
        assertEquals(19047, result.getMoney());
    }

    @Test
    void cashForVacationWithInvalidDateShouldReturnException() {
        int averageSalary = 50000;
        String invalidStartDate = "01/01.2024";
        String invalidEndDate = "10/1/2024";
        assertThrows(IncorrectDateFormatException.class, () -> {
            vacationPayCalculatorService.cashForVacationWithPeriod(averageSalary, invalidStartDate, invalidEndDate);
        });
    }

}
