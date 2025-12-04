package com.school.employment.Controller;

import com.school.employment.dto.StudentDTO;
import com.school.employment.entity.Result;
import com.school.employment.exceptions.StudentIdException;
import com.school.employment.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Validated
@CrossOrigin(origins ="*")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/students")
    public Result addStudent(@Valid @RequestBody StudentDTO studentDTO)  {
        try{
            Long id=studentService.addStudent(studentDTO).getId();
            return new Result(true, "添加学生成功",id);
        } catch (StudentIdException e) {
            return new Result(false, e.getMessage());
        }
    }

    @GetMapping("/students/search")
    public Result searchStudents(@RequestParam String keyword) {
        return new Result(true, "搜索学生成功", studentService.searchStudents(keyword));
    }

}
