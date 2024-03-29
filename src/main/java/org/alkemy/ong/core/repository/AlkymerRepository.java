package org.alkemy.ong.core.repository;

import org.alkemy.ong.core.domain.Alkymer;
import org.alkemy.ong.core.domain.AlkymerList;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface AlkymerRepository {
    Long create(Alkymer alkymer);

    Optional<Alkymer> findOne(Long id);

    Optional<Alkymer> update(Long id, Alkymer alkymer);

    AlkymerList findAllByPageable(Pageable pageable);

    void delete(Long id);
}
