package br.com.dio.crud.meetingroom.meetingroom.service;

import br.com.dio.crud.meetingroom.meetingroom.exception.ResourceNotFoundException;
import br.com.dio.crud.meetingroom.meetingroom.model.Room;
import br.com.dio.crud.meetingroom.meetingroom.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    public Room getRoomById(long roomId) throws ResourceNotFoundException {
        return roomRepository.findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found for this id:: " + roomId));
    }

    public Room createRoom(Room newRoom) {
        return roomRepository.save(newRoom);
    }

    public Room updateRoom(Long roomId, Room updatedRoom) throws ResourceNotFoundException {
        Room roomForUpdate = getRoomById(roomId);

        updatedRoom.setId(roomForUpdate.getId());

        return roomRepository.save(updatedRoom);
    }

    public Map<String, Boolean> deleteRoom(Long roomId) throws ResourceNotFoundException {
        Room deletedRoom = getRoomById(roomId);

        roomRepository.delete(deletedRoom);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);

        return response;
    }
}
