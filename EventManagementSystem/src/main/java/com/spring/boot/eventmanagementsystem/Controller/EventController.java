package com.spring.boot.eventmanagementsystem.Controller;

import com.spring.boot.eventmanagementsystem.Modle.Event;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/event")
public class EventController {

    ArrayList<Event> events = new ArrayList<>();

    @PostMapping("/new")
    public ResponseEntity<?> createNewEvent(@Valid @RequestBody Event event, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.getFieldError().getDefaultMessage());
        }

        if (event.getEndDate().isBefore(event.getStartDate())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error end date can not be before the start date");
        }


        events.add(event);
        return ResponseEntity.status(HttpStatus.OK).body("Event was created successfully");
    }

    @GetMapping("/list")
    public ResponseEntity<?> displayEvents() {
        return ResponseEntity.status(HttpStatus.OK).body(events);
    }

    @PutMapping("/update/{iD}")
    public ResponseEntity<?> updateEvent(@PathVariable String iD, @Valid @RequestBody Event event, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.getFieldError().getDefaultMessage());
        }

        boolean updated = false;
        event.setID(iD);

        for (Event e : events) {
            if (e.getID().equals(iD)) {
                events.set(events.indexOf(e), event);
                updated = true;
                break;
            }
        }

        if (updated) {
            return ResponseEntity.status(HttpStatus.OK).body("Event was updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error, Event does not exist");
        }
    }

    @DeleteMapping("/delete/{iD}")
    public ResponseEntity<?> deleteEvent(@PathVariable String iD) {
        boolean deleted = false;

        for (Event e : events) {
            if (e.getID().equals(iD)) {
                events.remove(e);
                deleted = true;
                break;
            }
        }

        if (deleted) {
            return ResponseEntity.status(HttpStatus.OK).body("Event was deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error, Event does not exist");
        }
    }

    @PutMapping("/change-capacity/{iD}/{capacity}")
    public ResponseEntity<?> changeCapacity(@PathVariable String iD, @PathVariable int capacity) {
        if (capacity < 26) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("capacity must not be less than 26" +
                    ", and it must be a positive number");
        }

        boolean capacityChanged = false;

        for (Event e : events) {
            if (e.getID().equals(iD)) {
                e.setCapacity(capacity);
                capacityChanged = true;
                break;
            }
        }

        if (capacityChanged) {
            return ResponseEntity.status(HttpStatus.OK).body("Event capacity was changed successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error, Event does not exist");
        }
    }

    @GetMapping("/search/{iD}")
    public ResponseEntity<?> searchEventId(@PathVariable String iD) {
        for (Event e : events) {
            if (e.getID().equals(iD)) {
                return ResponseEntity.status(HttpStatus.OK).body(e);
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(new Event(null, "Not found", 0,
                null,
                null));
    }

}
