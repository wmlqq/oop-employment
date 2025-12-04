package com.school.employment.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@DiscriminatorValue("COMPANY")
public class CompanyConsultant extends Consultant {

    @Column(name = "company", length = 100)
    private String company;        // 单位

    // 无参构造方法
    public CompanyConsultant() {
    }
}