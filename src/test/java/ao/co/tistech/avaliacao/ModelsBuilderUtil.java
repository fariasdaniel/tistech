package ao.co.tistech.avaliacao;

import ao.co.tistech.avaliacao.entities.Exam;
import ao.co.tistech.avaliacao.openapi.model.AvailabilityResponse;
import ao.co.tistech.avaliacao.openapi.model.CandidateResponse;
import ao.co.tistech.avaliacao.openapi.model.RoomResponse;
import ao.co.tistech.avaliacao.transportlayers.mapper.AvailabilityMapper;
import ao.co.tistech.avaliacao.transportlayers.mapper.CandidateMapper;
import ao.co.tistech.avaliacao.transportlayers.mapper.RoomMapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ModelsBuilderUtil {

    public static Exam exam() {
        return Exam.builder()
                .name("Exam Test")
                .availability(AvailabilityMapper.INSTANCE.map(new AvailabilityResponse().id(1)))
                .candidate(CandidateMapper.INSTANCE.map(new CandidateResponse().id(1)))
                .room(RoomMapper.INSTANCE.map(new RoomResponse().id(1))).build();
    }

    public static List<Exam> listExam() {
        List<Exam> list = new ArrayList<>();
        list.add(exam());
        return list;
    }
}
