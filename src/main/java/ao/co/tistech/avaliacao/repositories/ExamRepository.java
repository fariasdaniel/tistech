package ao.co.tistech.avaliacao.repositories;

import ao.co.tistech.avaliacao.entities.Exam;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExamRepository extends JpaRepository<Exam, Integer> {
    List<Exam> findByRoomIdAndAvailabilityId(int room_id, int availability_id);
}
