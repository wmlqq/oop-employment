package com.school.employment.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "room")
@Data
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "room_id", unique = true, nullable = false, length = 20)
    private String roomId;         // 编号

    @Column(name = "campus", length = 50)
    private String campus;         // 校区

    @Column(name = "building", length = 50)
    private String building;       // 楼宇

    @Column(name = "room_number", length = 20)
    private String roomNumber;     // 房间号

    @Column(name = "capacity")
    private Integer capacity;      // 容量

    // 无参构造方法
    public Room() {
    }
}

