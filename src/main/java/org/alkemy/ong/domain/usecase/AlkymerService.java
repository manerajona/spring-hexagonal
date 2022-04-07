package org.alkemy.ong.domain.usecase;

import org.alkemy.ong.domain.model.Alkymer;
import org.alkemy.ong.domain.model.AlkymerList;
import org.springframework.data.domain.PageRequest;

import java.util.Optional;

public interface AlkymerService {
    Long createEntity(Alkymer entity);
    void deleteById(Long id);
    Optional<Alkymer> getByIdIfExists(Long id);
    AlkymerList getList(PageRequest pageRequest);
    Optional<Alkymer> updateEntityIfExists(Long id, Alkymer entity);
}
