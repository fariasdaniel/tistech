package ao.co.tistech.avaliacao.service;

import ao.co.tistech.avaliacao.entities.Candidate;
import ao.co.tistech.avaliacao.repositories.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CandidateService {

    @Autowired
    private CandidateRepository repository;

    public Candidate addRoom(Candidate candidate) {
        return repository.save(candidate);
    }

    public List<Candidate> listAll() {
        return repository.findAll();
    }

    public void delete(Integer id) throws Exception {
        repository.delete(findById(id));
    }

    public Candidate findById(Integer id) throws Exception {
        Optional<Candidate> room = repository.findById(id);
        if (room.isEmpty()) {
            throw new Exception("Entity not found");
        }
        return room.get();
    }

    public Candidate update(Candidate candidate) throws Exception {
        Candidate room = findById(candidate.getId());
        room.setName(candidate.getName());
        return repository.save(room);
    }
}
