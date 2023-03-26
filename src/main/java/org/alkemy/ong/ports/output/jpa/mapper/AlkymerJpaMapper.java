package org.alkemy.ong.ports.output.jpa.mapper;

import org.alkemy.ong.core.domain.Alkymer;
import org.alkemy.ong.ports.output.jpa.entity.AlkymerJpa;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedSourcePolicy = ReportingPolicy.WARN)
public interface AlkymerJpaMapper {

    @IterableMapping(qualifiedByName = "alkymerJpaToAlkymer")
    List<Alkymer> alkymerJpaListToAlkymerList(List<AlkymerJpa> alkymers);

    @Named("alkymerJpaToAlkymer")
    Alkymer alkymerJpaToAlkymer(AlkymerJpa alkymer);

    @Mapping(target = "skills", ignore = true)
    AlkymerJpa alkymerToAlkymerJpa(Alkymer alkymer);
}
