package ao.co.tistech.avaliacao.transportlayers;

import ao.co.tistech.avaliacao.openapi.model.Candidate;
import ao.co.tistech.avaliacao.openapi.model.CandidateResponse;
import ao.co.tistech.avaliacao.service.CandidateService;
import ao.co.tistech.avaliacao.transportlayers.mapper.CandidateMapper;
import ao.co.tistech.avaliacao.transportlayers.openapi.api.CandidateApi;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CandidateApiImpl implements CandidateApi {
    @Autowired
    CandidateService service;

    @Override
    public ResponseEntity delete(Integer id) {
        try {
            service.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<List<CandidateResponse>> findCandidateById(Integer id) {
        try {
            return new ResponseEntity(CandidateMapper.INSTANCE.map(service.findById(id)), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<List<CandidateResponse>> get() {
        return new ResponseEntity<>(CandidateMapper.INSTANCE.mapList(service.listAll()), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CandidateResponse> post(Candidate candidate) {
        return new ResponseEntity<>(CandidateMapper.INSTANCE.map(
                service.addRoom(CandidateMapper.INSTANCE.map(candidate)))
                , HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<CandidateResponse> update(CandidateResponse candidate) {
        try {
            return new ResponseEntity<>(CandidateMapper.INSTANCE.map(service.update(CandidateMapper.INSTANCE.map(candidate))), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            //TODO: Create expections for basic Errors, using generic error
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
