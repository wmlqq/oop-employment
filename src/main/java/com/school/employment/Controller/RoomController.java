package com.school.employment.Controller;

import com.school.employment.entity.Consultant;
import com.school.employment.entity.Result;
import com.school.employment.entity.Room;
import com.school.employment.service.ConsultantService;
import com.school.employment.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Validated
@CrossOrigin(origins ="*")
public class RoomController {
    @Autowired
    private RoomService roomService;

    @PostMapping("/rooms")
    public ResponseEntity<Result> showAllRooms(){
        List<Room> rooms=roomService.getAllRooms();
        return ResponseEntity.ok(new Result(true, "获取咨询室信息成功",rooms));
    }

    @GetMapping("/rooms/search")
    public Result searchRooms(@RequestParam String keyword) {
        return new Result(true, "搜索咨询室成功", roomService.searchRooms(keyword));
    }
}