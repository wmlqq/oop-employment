package com.school.employment.service;

import com.school.employment.entity.ConsultRecord;
import com.school.employment.entity.Consultant;
import com.school.employment.entity.Student;
import com.school.employment.entity.Room;
import com.school.employment.repository.ConsultRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class TxtExportRecordService implements ExportRecordService {

    @Autowired
    private ConsultRecordRepository consultRecordRepository;

    private static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String FIELD_SEPARATOR = "_";
    private static final String RECORD_SEPARATOR = "----------------------------------------";

    @Override
    public String format() {
        List<ConsultRecord> records = consultRecordRepository.findAll();
        return convertToTxt(records);
    }

    public String convertToTxt(List<ConsultRecord> records) {
        if (records == null || records.isEmpty()) {
            return "暂无咨询记录数据";
        }

        StringBuilder txtBuilder = new StringBuilder();
        txtBuilder.append("咨询记录导出报告").append(LINE_SEPARATOR);
        txtBuilder.append("导出时间_").append(java.time.LocalDateTime.now().format(DATE_TIME_FORMATTER))
                .append(LINE_SEPARATOR);
        txtBuilder.append("记录总数_").append(records.size()).append(LINE_SEPARATOR);
        txtBuilder.append(RECORD_SEPARATOR).append(LINE_SEPARATOR).append(LINE_SEPARATOR);

        for (int i = 0; i < records.size(); i++) {
            ConsultRecord record = records.get(i);
            txtBuilder.append("记录").append(i + 1).append(LINE_SEPARATOR);
            appendRecordToTxt(txtBuilder, record);
            txtBuilder.append(LINE_SEPARATOR);
        }

        return txtBuilder.toString();
    }

    private void appendRecordToTxt(StringBuilder builder, ConsultRecord record) {
        // 基本记录信息
        appendField(builder, "记录ID", record.getId());
        appendField(builder, "开始时间",
                record.getStartTime() != null ?
                        record.getStartTime().format(DATE_TIME_FORMATTER) : null);
        appendField(builder, "结束时间",
                record.getEndTime() != null ?
                        record.getEndTime().format(DATE_TIME_FORMATTER) : null);
        appendField(builder, "咨询问题", record.getQuestion());
        appendField(builder, "咨询结论", record.getConclusion());
        appendField(builder, "状态", record.getStatus());

        // 咨询师信息
        if (record.getConsultant() != null) {
            builder.append("咨询师信息").append(LINE_SEPARATOR);
            appendConsultantToTxt(builder, record.getConsultant(), 2);
        }

        // 学生信息
        if (record.getStudent() != null) {
            builder.append("学生信息").append(LINE_SEPARATOR);
            appendStudentToTxt(builder, record.getStudent(), 2);
        }

        // 咨询室信息
        if (record.getRoom() != null) {
            builder.append("咨询室信息").append(LINE_SEPARATOR);
            appendRoomToTxt(builder, record.getRoom(), 2);
        }

        builder.append(RECORD_SEPARATOR);
    }

    private void appendConsultantToTxt(StringBuilder builder, Consultant consultant, int indentLevel) {
        String indent = createIndent(indentLevel);
        appendField(builder, indent + "咨询师ID", consultant.getId());
        appendField(builder, indent + "身份证号", consultant.getIdCard());
        appendField(builder, indent + "姓名", consultant.getName());
        appendField(builder, indent + "电话", consultant.getPhone());
        appendField(builder, indent + "简介", consultant.getIntroduction());

        // 处理咨询师类型特有字段
        if (consultant instanceof com.school.employment.entity.OwnConsultant) {
            com.school.employment.entity.OwnConsultant ownConsultant =
                    (com.school.employment.entity.OwnConsultant) consultant;
            appendField(builder, indent + "学校", ownConsultant.getSchool());
        } else if (consultant instanceof com.school.employment.entity.CompanyConsultant) {
            com.school.employment.entity.CompanyConsultant companyConsultant =
                    (com.school.employment.entity.CompanyConsultant) consultant;
            appendField(builder, indent + "公司", companyConsultant.getCompany());
        }
    }

    private void appendStudentToTxt(StringBuilder builder, Student student, int indentLevel) {
        String indent = createIndent(indentLevel);
        appendField(builder, indent + "学生ID", student.getId());
        appendField(builder, indent + "学号", student.getStudentId());
        appendField(builder, indent + "姓名", student.getName());
        appendField(builder, indent + "性别", student.getGender());
        appendField(builder, indent + "学院", student.getCollege());
        appendField(builder, indent + "电话", student.getPhone());
        appendField(builder, indent + "生日",
                student.getBirthday() != null ? student.getBirthday().toString() : null);
    }

    private void appendRoomToTxt(StringBuilder builder, Room room, int indentLevel) {
        String indent = createIndent(indentLevel);
        appendField(builder, indent + "房间ID", room.getId());
        appendField(builder, indent + "房间编号", room.getRoomId());
        appendField(builder, indent + "校区", room.getCampus());
        appendField(builder, indent + "楼宇", room.getBuilding());
        appendField(builder, indent + "房间号", room.getRoomNumber());
        appendField(builder, indent + "容量", room.getCapacity());
    }

    private void appendField(StringBuilder builder, String fieldName, Object value) {
        if (value != null) {
            builder.append(fieldName)
                    .append(FIELD_SEPARATOR)
                    .append(value.toString().replace("\n", " ").replace("\r", " "))
                    .append(LINE_SEPARATOR);
        } else {
            builder.append(fieldName).append(FIELD_SEPARATOR).append("null").append(LINE_SEPARATOR);
        }
    }

    private String createIndent(int level) {
        return "  ".repeat(Math.max(0, level)) // 每个缩进级别2个空格
                ;
    }
}
