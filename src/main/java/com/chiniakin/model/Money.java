package com.chiniakin.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Модель суммы отпускных.
 *
 * @author ChiniakinD.
 */
@Getter
@Setter
@AllArgsConstructor
@Schema(name = "Объект, являющийся оберткой для суммы отпускных")
public class Money {

    @Schema(name = "money", example = "20000", description = "Сумма отпускных", required = true)
    private int money;

}
