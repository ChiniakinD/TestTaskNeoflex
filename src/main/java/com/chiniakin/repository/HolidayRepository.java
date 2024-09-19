package com.chiniakin.repository;

import com.chiniakin.entity.Holiday;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репозиторий для работы с праздничными днями.
 *
 * @author ChiniakinD.
 */
public interface HolidayRepository extends JpaRepository<Holiday, String> {
}
