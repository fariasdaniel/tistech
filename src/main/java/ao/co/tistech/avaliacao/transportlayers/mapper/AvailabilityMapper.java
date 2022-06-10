package ao.co.tistech.avaliacao.transportlayers.mapper;

import ao.co.tistech.avaliacao.entities.Availability;
import ao.co.tistech.avaliacao.openapi.model.AvailabilityResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AvailabilityMapper {

    AvailabilityMapper INSTANCE = Mappers.getMapper(AvailabilityMapper.class);

    @Mapping(target = "name", source = "name")
    Availability map(ao.co.tistech.avaliacao.openapi.model.Availability availability);

    @Mapping(target = "name", source = "name")
    Availability map(ao.co.tistech.avaliacao.openapi.model.AvailabilityResponse availability);

    @Mapping(target = "name", source = "name")
    AvailabilityResponse map(Availability availability);

    @Mapping(target = "name", source = "name")
    List<AvailabilityResponse> mapList(List<Availability> availabilities);
}
