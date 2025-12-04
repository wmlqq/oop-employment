package com.school.employment.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@DiscriminatorValue("Own")
public class OwnConsultant extends Consultant {

    @Column(name = "school", length = 100)
    private String school;         // 所在学校

    // 无参构造方法
    public OwnConsultant() {
    }
}