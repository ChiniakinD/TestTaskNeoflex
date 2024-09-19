package com.chiniakin.entity;

import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

/**
 * Сущность праздничного дня.
 *
 * @author ChiniakinD.
 */
@Getter
@Setter
@Entity
@Table(name = "holiday")
@NoArgsConstructor
public class Holiday {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int day;

    private int month;

    public Holiday(int day, int month) {
        this.id = 0L;
        this.day = day;
        this.month = month;
    }

    /**
     * Проверяет эквивалентность объектов Holiday без учета индекса.
     *
     * @param o объект сравнения.
     * @return true, если объекты равны по дню и месяцу, иначе false.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Holiday holiday = (Holiday) o;
        return day == holiday.day && month == holiday.month;
    }

    @Override
    public int hashCode() {
        return Objects.hash(day, month);
    }

}
