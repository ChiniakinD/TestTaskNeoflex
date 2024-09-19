package com.chiniakin.service;

import com.chiniakin.controller.VacationPayCalculatorController;
import com.chiniakin.model.Money;
import com.chiniakin.service.interfaces.VacationPayCalculatorService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(VacationPayCalculatorController.class)
class VacationPayCalculatorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VacationPayCalculatorService vacationPayCalculatorService;

    @Test
    void calculateShouldInvokeCorrectMethodAndReturnCorrectResponse() throws Exception {
        int averageSalary = 50000;
        int vacationDays = 10;
        Money expectedMoney = new Money(23809);
        Mockito.when(vacationPayCalculatorService.cashForVacation(averageSalary, vacationDays))
                .thenReturn(expectedMoney);
        mockMvc.perform(MockMvcRequestBuilders.get("/calculate/")
                        .param("averageSalary", String.valueOf(averageSalary))
                        .param("vacationDays", String.valueOf(vacationDays)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.money").value(expectedMoney.getMoney()));
    }

    @Test
    void calculatePeriodShouldInvokeCorrectMethodAndReturnCorrectResponse() throws Exception {
        int averageSalary = 50000;
        String startDate = "01.04.2024";
        String endDate = "10.04.2024";
        Money expectedMoney = new Money(19047);
        Mockito.when(vacationPayCalculatorService.cashForVacationWithPeriod(averageSalary, startDate, endDate))
                .thenReturn(expectedMoney);
        mockMvc.perform(MockMvcRequestBuilders.get("/calculate/period")
                        .param("averageSalary", String.valueOf(averageSalary))
                        .param("startDate", startDate)
                        .param("endDate", endDate))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.money").value(expectedMoney.getMoney()));
    }

    @Test
    void cashForVacationWithInvalidSalaryShouldReturnBadRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/calculate/")
                        .param("averageSalary", "-100")
                        .param("vacationDays", "-5"))
                .andExpect(status().isBadRequest());
    }



}
