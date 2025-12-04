package com.school.employment.service;

import com.school.employment.dto.StudentDTO;
import com.school.employment.entity.Student;
import com.school.employment.exceptions.StudentIdException;
import com.school.employment.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Student addStudent(StudentDTO studentDTO) throws StudentIdException {

        //检查学号是否重复
        if(studentRepository.findByStudentId(studentDTO.getStudentId())!=null){
            throw new StudentIdException("学号已存在");
        }

        //数据验证应该在Controller中执行

        //DTO转Entity
        Student student = new Student();
        student.setStudentId(studentDTO.getStudentId());
        student.setName(studentDTO.getName());
        student.setGender(studentDTO.getGender());
        student.setCollege(studentDTO.getCollege());
        student.setPhone(studentDTO.getPhone());
        student.setBirthday(studentDTO.getBirthday());

        return studentRepository.save(student);
    }

    public List<Student> searchStudents(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return studentRepository.findAll();
        }

        // 模糊搜索姓名或学号
        return studentRepository.findByNameContainingOrStudentIdContaining(keyword, keyword);
    }
}
