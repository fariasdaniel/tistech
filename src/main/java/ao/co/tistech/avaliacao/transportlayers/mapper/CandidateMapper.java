package ao.co.tistech.avaliacao.transportlayers.mapper;

import ao.co.tistech.avaliacao.entities.Candidate;
import ao.co.tistech.avaliacao.openapi.model.CandidateResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CandidateMapper {

    CandidateMapper INSTANCE = Mappers.getMapper(CandidateMapper.class);

    @Mapping(target = "name", source = "name")
    Candidate map(ao.co.tistech.avaliacao.openapi.model.Candidate candidate);

    @Mapping(target = "name", source = "name")
    CandidateResponse map(Candidate candidate);


    @Mapping(target = "name", source = "name")
    Candidate map(CandidateResponse candidate);

    @Mapping(target = "name", source = "name")
    List<CandidateResponse> mapList(List<Candidate> candidates);

}
