package com.school.employment.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "consultant")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Data
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
public abstract class Consultant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_card", unique = true, nullable = false, length = 18)
    private String idCard;         // 身份证号

    @Column(name = "name", nullable = false, length = 50)
    private String name;           // 姓名

    @Column(name = "phone", length = 20)
    private String phone;          // 联系电话

    @Column(name = "introduction", columnDefinition = "TEXT")
    private String introduction;   // 个人简介

    // 无参构造方法
    public Consultant() {}
}