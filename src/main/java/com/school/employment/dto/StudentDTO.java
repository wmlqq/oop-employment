package com.school.employment.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class StudentDTO {

    @NotBlank(message = "学号不能为空")
    @Pattern(regexp = "\\d{8,12}", message = "学号必须是8-12位数字")
    private String studentId;

    @NotBlank(message = "姓名不能为空")
    @Size(min = 2, max = 50, message = "姓名长度必须在2-50个字符之间")
    private String name;

    @Pattern(regexp = "[男女]", message = "性别必须是男或女")
    private String gender;

    @Size(max = 100, message = "学院名称过长")
    private String college;

    @Pattern(regexp = "1[3-9]\\d{9}", message = "手机号格式不正确")
    private String phone;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Past(message = "生日必须是过去的时间")
    private LocalDate birthday;

    // 无参构造方法
    public StudentDTO() {}
}
