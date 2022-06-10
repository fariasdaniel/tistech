package ao.co.tistech.avaliacao.service;

import ao.co.tistech.avaliacao.entities.Exam;
import ao.co.tistech.avaliacao.repositories.CandidateRepository;
import ao.co.tistech.avaliacao.repositories.ExamRepository;
import ao.co.tistech.avaliacao.repositories.RoomRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;

@Service
@Log
public class ExamService {

    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Exam addExam(Exam exam) throws Exception {
        if (!candidateRepository.existsById(exam.getCandidate().getId())) {
            throw new Exception("Candidate not Exists, please register the candidate with the endpoint candidate");
        }
        if (!roomAvailabilityExists(exam.getRoom().getId(), exam.getAvailability().getId())) {
            throw new Exception("Room vs Availability not Exists");
        }
        if (!roomAvailability(exam.getRoom().getId(), exam.getAvailability().getId())) {
            throw new Exception("Room and Availability not Available");
        }
        return examRepository.save(exam);
    }

    public List<Exam> listAll() {
        return examRepository.findAll();
    }

    public void delete(Integer id) throws Exception {
        examRepository.delete(findById(id));
    }

    public Exam findById(Integer id) throws Exception {
        Optional<Exam> room = examRepository.findById(id);
        if (room.isEmpty()) {
            throw new Exception("Entity not found");
        }
        return room.get();
    }

    public Exam update(Exam exam) throws Exception {
        if (!roomAvailabilityExists(exam.getRoom().getId(), exam.getAvailability().getId())) {
            throw new Exception("Room vs Availability not Exists");
        }
        if (!roomAvailability(exam.getRoom().getId(), exam.getAvailability().getId())) {
            throw new Exception("Room and Availability not Available");
        }
        Exam room = findById(exam.getId());
        room.setName(exam.getName());
        room.setRoom(exam.getRoom());
        room.setAvailability(exam.getAvailability());
        room.setCandidate(exam.getCandidate());
        return examRepository.save(room);
    }

    public boolean roomAvailability(int room_id, int availability_id) {
        return examRepository.findByRoomIdAndAvailabilityId(room_id, availability_id).isEmpty();
    }

    public boolean roomAvailabilityExists(int room_id, int availability_id) {
        String query = "SELECT ra.room_id FROM room_availability ra WHERE 1 = 1 ";

        query += "and ra.room_id = ? and ra.availability_id = ?";

        log.log(Level.INFO, "Query: " + query);

        log.log(Level.INFO, "Parameters: room_id=" + room_id + ", availability_id=" + availability_id);

        List roomAvailabitily =
                jdbcTemplate.query
                        (query, new ObjectMapper(), room_id, availability_id);

        return !roomAvailabitily.isEmpty();
    }

    class ObjectMapper implements RowMapper<Object> {
        @Override
        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            return rs.getInt("ra.room_id");
        }
    }


}
