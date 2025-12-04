package com.school.employment.repository;

import com.school.employment.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room,Long> {
    List<Room> findByRoomIdContainingOrId(String keyword, Long id);

    List<Room> findByRoomIdContaining(String keyword);
}
