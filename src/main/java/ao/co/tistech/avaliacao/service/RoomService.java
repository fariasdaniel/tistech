package ao.co.tistech.avaliacao.service;

import ao.co.tistech.avaliacao.entities.Room;
import ao.co.tistech.avaliacao.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    public Room addRoom(Room room) {
        return roomRepository.save(room);
    }

    public List<Room> listAll() {
        return roomRepository.findAll();
    }

    public void delete(Integer id) throws Exception {
        roomRepository.delete(findById(id));
    }

    public Room findById(Integer id) throws Exception {
        Optional<Room> room = roomRepository.findById(id);
        if (room.isEmpty()) {
            throw new Exception("Entity not found");
        }
        return room.get();
    }

    public Room update(Room r) throws Exception {
        Room room = findById(r.getId());
        room.setName(r.getName());
        return roomRepository.save(room);
    }
}
