package ao.co.tistech.avaliacao.repositories;

import ao.co.tistech.avaliacao.entities.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepository extends JpaRepository<Candidate, Integer> {
}
