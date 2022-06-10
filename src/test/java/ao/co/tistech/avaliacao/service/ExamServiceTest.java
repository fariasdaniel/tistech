package ao.co.tistech.avaliacao.service;

import ao.co.tistech.avaliacao.ModelsBuilderUtil;
import ao.co.tistech.avaliacao.repositories.CandidateRepository;
import ao.co.tistech.avaliacao.repositories.ExamRepository;
import ao.co.tistech.avaliacao.repositories.RoomRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.ArrayList;
import java.util.Arrays;


@ExtendWith(MockitoExtension.class)
class ExamServiceTest {
    @InjectMocks
    ExamService service;

    @Mock
    RoomRepository roomRepository;

    @Mock
    ExamRepository examRepository;

    @Mock
    CandidateRepository candidateRepository;

    @Mock
    JdbcTemplate jdbcTemplate;

    @Test
    void scheduleOk() throws Exception {
        Mockito.when(examRepository.findByRoomIdAndAvailabilityId(Mockito.anyInt(), Mockito.anyInt())).
                thenReturn(new ArrayList<>());

        Mockito.when(
                jdbcTemplate.query(Mockito.anyString(), (RowMapper<? extends Object>) ArgumentMatchers.any(), ArgumentMatchers.any())
        ).thenReturn(new ArrayList(Arrays.asList(1)));

        Mockito.when(candidateRepository.existsById(Mockito.anyInt())).thenReturn(true);

        Mockito.when(examRepository.save(Mockito.any())).thenReturn(ModelsBuilderUtil.exam());
        service.addExam(ModelsBuilderUtil.exam());
        Mockito.verify(examRepository, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    void scheduleErrorAvailabilityInUse() throws Exception {


        Mockito.when(examRepository.findByRoomIdAndAvailabilityId(Mockito.anyInt(), Mockito.anyInt())).
                thenReturn(ModelsBuilderUtil.listExam());

        Mockito.when(
                jdbcTemplate.query(Mockito.anyString(), (RowMapper<? extends Object>) ArgumentMatchers.any(), ArgumentMatchers.any())
        ).thenReturn(new ArrayList(Arrays.asList(1)));

        Mockito.when(candidateRepository.existsById(Mockito.anyInt())).thenReturn(true);

        Assertions.assertThrows(Exception.class, () -> service.addExam(ModelsBuilderUtil.exam()));
    }
}