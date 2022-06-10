package ao.co.tistech.avaliacao.transportlayers;

import ao.co.tistech.avaliacao.openapi.model.Room;
import ao.co.tistech.avaliacao.openapi.model.RoomResponse;
import ao.co.tistech.avaliacao.service.RoomService;
import ao.co.tistech.avaliacao.transportlayers.mapper.RoomMapper;
import ao.co.tistech.avaliacao.transportlayers.openapi.api.RoomApi;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RoomApiImpl implements RoomApi {
    @Autowired
    RoomService roomService;

    @Override
    public ResponseEntity delete(Integer id) {
        try {
            roomService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity findRoomById(Integer id) {
        try {
            return new ResponseEntity<>(RoomMapper.INSTANCE.map(roomService.findById(id)), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<List<RoomResponse>> get() {
        return new ResponseEntity<>(RoomMapper.INSTANCE.mapResponseList(roomService.listAll()), HttpStatus.OK);
    }


    @Override
    public ResponseEntity<RoomResponse> post(Room room) {
        return new ResponseEntity<>(RoomMapper.INSTANCE.map(roomService.addRoom(RoomMapper.INSTANCE.map(room))), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity update(RoomResponse room) {
        try {
            return new ResponseEntity<>(RoomMapper.INSTANCE.map(roomService.update(RoomMapper.INSTANCE.map(room))), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            //TODO: Create expections for basic Errors, using generic error
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
