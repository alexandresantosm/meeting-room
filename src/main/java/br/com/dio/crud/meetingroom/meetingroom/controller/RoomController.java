package br.com.dio.crud.meetingroom.meetingroom.controller;

import br.com.dio.crud.meetingroom.meetingroom.exception.ResourceNotFoundException;
import br.com.dio.crud.meetingroom.meetingroom.model.Room;
import br.com.dio.crud.meetingroom.meetingroom.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @GetMapping("/rooms")
    public List<Room> getAllRooms() {
        return roomService.getAllRooms();
    }

    @GetMapping("/rooms/{id}")
    public ResponseEntity<Room> getRoomById(@PathVariable long id) throws ResourceNotFoundException {
        Room room = roomService.getRoomById(id);

        return ResponseEntity.ok().body(room);
    }

    @PostMapping("/rooms")
    public Room createRoom(@Valid @RequestBody Room newRoom) {
        return roomService.createRoom(newRoom);
    }

    @PutMapping("/rooms/{id}")
    public ResponseEntity<Room> updateRoom(
            @PathVariable Long roomId,
            @Valid @RequestBody Room roomDetails
    ) throws ResourceNotFoundException {
        return ResponseEntity.ok(roomService.updateRoom(roomId, roomDetails));
    }

    @DeleteMapping("/rooms/{id}")
    public Map<String, Boolean> deleteRoom(@PathVariable Long roomId) throws ResourceNotFoundException {
        return roomService.deleteRoom(roomId);
    }
}
