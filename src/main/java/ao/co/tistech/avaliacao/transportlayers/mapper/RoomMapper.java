package ao.co.tistech.avaliacao.transportlayers.mapper;

import ao.co.tistech.avaliacao.entities.Room;
import ao.co.tistech.avaliacao.openapi.model.RoomResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface RoomMapper {

    RoomMapper INSTANCE = Mappers.getMapper(RoomMapper.class);

    @Mapping(target = "name", source = "name")
    Room map(ao.co.tistech.avaliacao.openapi.model.Room r);

    @Mapping(target = "name", source = "name")
    Room map(RoomResponse r);

    @Mapping(target = "name", source = "name")
    RoomResponse map(Room r);

    @Mapping(target = "name", source = "name")
    List<RoomResponse> mapList(List<Room> rooms);

    @Mapping(target = "name", source = "name")
    List<ao.co.tistech.avaliacao.openapi.model.RoomResponse> mapResponseList(List<Room> rooms);
}
