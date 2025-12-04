package com.school.employment.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "consult_record")
@Data
public class ConsultRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "consultant_id", nullable = false)
    private Consultant consultant; // 咨询师

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;       // 学生

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;             // 咨询室

    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime; // 起始时间

    @Column(name = "end_time", nullable = false)
    private LocalDateTime endTime;   // 结束时间

    @Column(name = "question", columnDefinition = "TEXT")
    private String question;       // 咨询问题

    @Column(name = "conclusion", columnDefinition = "TEXT")
    private String conclusion;     // 咨询结论

    @Column(name = "status", length = 20)
    private String status;          // 状态：未完成、已完成、已取消

    // 无参构造方法
    public ConsultRecord() {
    }
}