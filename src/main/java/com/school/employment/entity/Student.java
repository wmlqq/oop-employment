package com.school.employment.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "student")
@Data
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "student_id", unique = true, nullable = false, length = 20)
    private String studentId;      // 学号

    @Column(name = "name", nullable = false, length = 50)
    private String name;           // 姓名

    @Column(name = "gender", length = 10)
    private String gender;         // 性别

    @Column(name = "college", length = 100)
    private String college;        // 学院

    @Column(name = "phone", length = 20)
    private String phone;          // 联系电话

    @Column(name = "birthday")
    private LocalDate birthday;    // 生日

    // 无参构造方法（JPA要求）
    public Student() {}

    // 有参构造方法
    public Student(String studentId, String name, String gender, String college, String phone, LocalDate birthday) {
        this.studentId = studentId;
        this.name = name;
        this.gender = gender;
        this.college = college;
        this.phone = phone;
        this.birthday = birthday;
    }
}