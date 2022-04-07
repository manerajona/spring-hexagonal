package org.alkemy.ong.domain.usecase.impl;

import lombok.RequiredArgsConstructor;
import org.alkemy.ong.domain.model.Alkymer;
import org.alkemy.ong.domain.model.AlkymerList;
import org.alkemy.ong.domain.repository.AlkymerRepository;
import org.alkemy.ong.domain.usecase.AlkymerService;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AlkymerServiceImpl implements AlkymerService {

    private final AlkymerRepository repository;

    @Override
    public Long createEntity(Alkymer alkymer) {
        return repository.create(alkymer);
    }

    @Override
    public Optional<Alkymer> getByIdIfExists(Long id) {
        return repository.findOne(id);
    }

    @Override
    public AlkymerList getList(PageRequest pageRequest) {
        return repository.findAllByPageable(pageRequest);
    }

    @Override
    public Optional<Alkymer> updateEntityIfExists(Long id, Alkymer alkymer) {
        return repository.update(id, alkymer);
    }

    @Override
    public void deleteById(Long id) {
        repository.delete(id);
    }

}
