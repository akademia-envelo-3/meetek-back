package pl.envelo.meetek.controller.event;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.envelo.meetek.model.event.SingleEvent;
import pl.envelo.meetek.repository.event.SingleEventRepo;
import pl.envelo.meetek.service.event.SingleEventService;

import java.util.Optional;

@AllArgsConstructor
@RestController
public class SingleEventController {


    private final SingleEventService singleEventService;
    private final SingleEventRepo singleEventRepo;


    @GetMapping("/event/{eventId}")
    public Optional<SingleEvent> getSingleEvent(@PathVariable long eventId){
        return singleEventService.getOneEventById(eventId);


    }


    public ResponseEntity<SingleEvent> deleteEvent(@PathVariable long eventId){

        if(singleEventService.getOneEventById(eventId).isPresent()){

            singleEventService.deleteById(eventId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);


    }



}
