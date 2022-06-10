package ao.co.tistech.avaliacao.transportlayers.mapper;

import ao.co.tistech.avaliacao.entities.Exam;
import ao.co.tistech.avaliacao.openapi.model.ExamRequestPost;
import ao.co.tistech.avaliacao.openapi.model.ExamRequestPut;
import ao.co.tistech.avaliacao.openapi.model.ExamResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ExamMapper {

    ExamMapper INSTANCE = Mappers.getMapper(ExamMapper.class);

    @Mapping(target = "name", source = "name")
    @Mapping(target = "candidate", source = "candidate")
    Exam map(ExamRequestPost exam);

    @Mapping(target = "name", source = "name")
    @Mapping(target = "candidate", source = "candidate")
    Exam map(ExamRequestPut exam);

    @Mapping(target = "name", source = "name")
    ExamResponse map(Exam exam);

    @Mapping(target = "name", source = "name")
    List<ExamResponse> mapList(List<Exam> exams);

}
