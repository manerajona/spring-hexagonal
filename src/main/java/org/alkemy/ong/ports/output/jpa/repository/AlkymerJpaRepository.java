package org.alkemy.ong.ports.output.jpa.repository;

import org.alkemy.ong.ports.output.jpa.entity.AlkymerJpa;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AlkymerJpaRepository extends PagingAndSortingRepository<AlkymerJpa, Long> {
}
