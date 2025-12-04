package com.school.employment.service;

import com.school.employment.entity.Consultant;
import com.school.employment.entity.Room;
import com.school.employment.repository.ConsultantRepository;
import com.school.employment.repository.RoomRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    //返回所有咨询室数据
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    public List<Room> searchRooms(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return List.of();
        }
        try{
            Long id= getIdFromString(keyword);
            return roomRepository.findByRoomIdContainingOrId(keyword,id);
        }catch (IllegalArgumentException e){
            return roomRepository.findByRoomIdContaining(keyword);
        }
    }

    private Long getIdFromString(String idString) throws IllegalArgumentException {
        if(idString == null || idString.trim().isEmpty()) {
            throw new IllegalArgumentException("idString cannot be null or empty");
        }
        String numberStr = idString.replaceAll("[^0-9]", "");
        if (numberStr.isEmpty()) {
            throw new IllegalArgumentException("idString does not contain any numbers");
        }
        try{
            return Long.parseLong(numberStr);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("idString is not a valid number", e);
        }
    }
}
