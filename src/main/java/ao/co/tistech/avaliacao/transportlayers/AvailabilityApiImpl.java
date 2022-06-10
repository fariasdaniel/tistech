package ao.co.tistech.avaliacao.transportlayers;

import ao.co.tistech.avaliacao.openapi.model.Availability;
import ao.co.tistech.avaliacao.openapi.model.AvailabilityResponse;
import ao.co.tistech.avaliacao.service.AvailabilityService;
import ao.co.tistech.avaliacao.transportlayers.mapper.AvailabilityMapper;
import ao.co.tistech.avaliacao.transportlayers.openapi.api.AvailabilityApi;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AvailabilityApiImpl implements AvailabilityApi {
    @Autowired
    AvailabilityService service;

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
    public ResponseEntity<List<AvailabilityResponse>> findAvailabilityById(Integer id) {
        try {
            return new ResponseEntity(AvailabilityMapper.INSTANCE.map(service.findById(id)), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<List<AvailabilityResponse>> get() {
        return new ResponseEntity<>(AvailabilityMapper.INSTANCE.mapList(service.listAll()), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<AvailabilityResponse> post(Availability availability) {
        return new ResponseEntity<>(
                AvailabilityMapper.INSTANCE.map(
                        service.addRoom(AvailabilityMapper.INSTANCE.map(availability))
                ), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<AvailabilityResponse> update(AvailabilityResponse availability) {
        try {
            return new ResponseEntity<>(AvailabilityMapper.INSTANCE.map(service.update(AvailabilityMapper.INSTANCE.map(availability))), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            //TODO: Create expections for basic Errors, using generic error
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
