package org.alkemy.ong.ports.input.rs.mapper;

import org.alkemy.ong.core.domain.Alkymer;
import org.alkemy.ong.ports.input.rs.dto.AlkymerResponse;
import org.alkemy.ong.ports.input.rs.dto.CreateAlkymerRequest;
import org.alkemy.ong.ports.input.rs.dto.UpdateAlkymerRequest;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedSourcePolicy = ReportingPolicy.WARN)
public interface AlkymerControllerMapper extends CommonMapper {

    @IterableMapping(qualifiedByName = "alkymerToAlkymerResponse")
    List<AlkymerResponse> alkymerListToAlkymerResponseList(List<Alkymer> alkymers);

    @Named("alkymerToAlkymerResponse")
    @Mapping(target = "startDate", source = "startDate", qualifiedByName = "localDateTimeToDate")
    @Mapping(target = "endDate", source = "endDate", qualifiedByName = "localDateTimeToDate")
    AlkymerResponse alkymerToAlkymerResponse(Alkymer alkymer);

    Alkymer createAlkymerRequestToAlkymer(CreateAlkymerRequest create);

    Alkymer updateAlkymerRequestToAlkymer(UpdateAlkymerRequest create);
}
