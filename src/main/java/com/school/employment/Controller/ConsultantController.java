package com.school.employment.Controller;

import com.school.employment.entity.Consultant;
import com.school.employment.entity.Result;
import com.school.employment.service.ConsultantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Validated
@CrossOrigin(origins ="*")
public class ConsultantController {
    @Autowired
    private ConsultantService consultantService;

    @PostMapping("/consultants")
    public ResponseEntity<Result> showAllConsultant(){
            List<Consultant> consultants=consultantService.getAllConsultants();
            return ResponseEntity.ok(new Result(true, "获取咨询师信息成功",consultants));
    }

    @GetMapping("/consultants/search")
    public Result searchConsultant(@RequestParam String keyword){
        return new Result(true, "搜索咨询师成功", consultantService.searchConsultant(keyword));
    }
}
