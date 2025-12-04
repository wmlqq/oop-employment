package com.school.employment.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UpdateConsultRecordDTO {

    @NotNull(message = "咨询记录ID不能为空")
    private Long recordId;
    @NotNull(message = "结束时间不能为空")
    @Future(message = "结束时间必须为未来时间")
    private LocalDateTime endTime;
    @NotBlank(message = "问题不能为空")
    private String question;
    @NotBlank(message = "结论不能为空")
    private String conclusion;

    //无参构造方法
    public UpdateConsultRecordDTO() {

    }
}
