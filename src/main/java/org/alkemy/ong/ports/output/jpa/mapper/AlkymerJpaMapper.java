package org.alkemy.ong.ports.output.jpa.mapper;

import org.alkemy.ong.core.model.Alkymer;
import org.alkemy.ong.ports.output.jpa.entity.AlkymerJpa;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

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
