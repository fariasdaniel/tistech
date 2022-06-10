package ao.co.tistech.avaliacao.service;

import ao.co.tistech.avaliacao.entities.Availability;
import ao.co.tistech.avaliacao.repositories.AvailabilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AvailabilityService {

    @Autowired
    private AvailabilityRepository repository;

    public Availability addRoom(Availability availability) {
        return repository.save(availability);
    }

    public List<Availability> listAll() {
        return repository.findAll();
    }

    public void delete(Integer id) throws Exception {
        repository.delete(findById(id));
    }

    public Availability findById(Integer id) throws Exception {
        Optional<Availability> room = repository.findById(id);
        if (room.isEmpty()) {
            throw new Exception("Entity not found");
        }
        return room.get();
    }

    public Availability update(Availability availability) throws Exception {
        Availability room = findById(availability.getId());
        room.setName(availability.getName());
        return repository.save(room);
    }
}
