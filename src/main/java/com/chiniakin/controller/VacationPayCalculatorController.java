package com.chiniakin.controller;

import com.chiniakin.model.Money;
import com.chiniakin.service.interfaces.VacationPayCalculatorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для вычисления суммы отпускных.
 *
 * @author ChiniakinD.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/calculate")
@Validated
@Tag(name = "VacationPayCalculatorController", description = "Контроллер для вычисления суммы отпускных")
public class VacationPayCalculatorController {

    private final VacationPayCalculatorService vacationPayCalculatorService;

    /**
     * Расчет суммы отпускных без учета праздничных дней.
     *
     * @param averageSalary - средняя зарплата за 12 месяцев.
     * @param vacationDays  - количество дней отпуска.
     * @return сумму отпусных.
     */
    @Operation(summary = "Расчет суммы отпускных без учета праздничных дней")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok")
    })
    @GetMapping("/")
    public Money vacationPayCalculator(@Parameter(description = "Средняя зарплата за 12 мсесяцев")
                                       @Min(value = 0, message = "Зарплата должна быть не меньше 0") @NotNull int averageSalary,
                                       @Parameter(description = "Количество дней отпуска")
                                       @Min(value = 0, message = "Количество дней отпуска должно быть не меньше 0") @NotNull int vacationDays) {
        return vacationPayCalculatorService.cashForVacation(averageSalary, vacationDays);
    }

    /**
     * Расчет суммы отпускных с учетом праздничных дней(они не оплачиваются).
     *
     * @param averageSalary средняя зарплата.
     * @param startDate     дата начала отпуска.
     * @param endDate       дата конца отпуска.
     * @return сумму отпусных.
     */
    @Operation(summary = "Расчет суммы отпускных с учетом праздничных дней", description = "Расчет суммы отпускных с учетом праздничных дней(они не оплачиваются)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "400", description = "Некорректный формат даты: dd.mm.yyyy")
    })
    @GetMapping("/period")
    public Money vacationPayCalculator(@Parameter(description = "Средняя зарплата за 12 мсесяцев")
                                       @Min(value = 0, message = "Зарплата должна быть не меньше 0") @NotNull int averageSalary,
                                       @Parameter(description = "Дата начала отпуска")
                                       @NotBlank(message = "Дата начала отпуска не должна быть пустой") String startDate,
                                       @Parameter(description = "Дата окончания отпуска")
                                       @NotBlank(message = "Дата конца отпуска не должна быть пустой") String endDate) {
        return vacationPayCalculatorService.cashForVacationWithPeriod(averageSalary, startDate, endDate);
    }

}
