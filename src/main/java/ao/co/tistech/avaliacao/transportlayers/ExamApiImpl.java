package ao.co.tistech.avaliacao.transportlayers;

import ao.co.tistech.avaliacao.openapi.model.ExamRequestPost;
import ao.co.tistech.avaliacao.openapi.model.ExamRequestPut;
import ao.co.tistech.avaliacao.openapi.model.ExamResponse;
import ao.co.tistech.avaliacao.service.ExamService;
import ao.co.tistech.avaliacao.transportlayers.mapper.ExamMapper;
import ao.co.tistech.avaliacao.transportlayers.openapi.api.ExamApi;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ExamApiImpl implements ExamApi {
    @Autowired
    ExamService service;

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
    public ResponseEntity<List<ExamResponse>> findExamById(Integer id) {
        try {
            return new ResponseEntity(ExamMapper.INSTANCE.map(service.findById(id)), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<List<ExamResponse>> get() {
        return new ResponseEntity<>(ExamMapper.INSTANCE.mapList(service.listAll()), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ExamResponse> post(ExamRequestPost exam) {
        try {
            return new ResponseEntity<>(ExamMapper.INSTANCE.map(service.addExam(ExamMapper.INSTANCE.map(exam))), HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            //TODO: Create expections for basic Errors, using generic error
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<ExamResponse> update(ExamRequestPut exam) {
        try {
            return new ResponseEntity<>(ExamMapper.INSTANCE.map(service.update(ExamMapper.INSTANCE.map(exam))), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            //TODO: Create expections for basic Errors, using generic error
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
