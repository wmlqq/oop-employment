package com.school.employment.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ConsultRecordDTO {

    @NotNull(message = "学生ID不能为空")
    private Long studentId;
    @NotNull(message = "咨询师ID不能为空")
    private Long consultantId;
    @NotNull(message = "房间ID不能为空")
    private Long roomId;
    @NotNull(message = "开始时间不能为空")
    @Future(message = "开始时间必须是未来时间")
    private LocalDateTime startTime;
    @NotNull(message = "状态不能为空")
    private String status;

    // 无参构造方法
    public ConsultRecordDTO() {}
}
