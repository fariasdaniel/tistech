package ao.co.tistech.avaliacao.repositories;

import ao.co.tistech.avaliacao.entities.Availability;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvailabilityRepository extends JpaRepository<Availability, Integer> {
}
