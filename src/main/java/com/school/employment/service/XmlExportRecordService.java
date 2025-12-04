
package com.school.employment.service;

import com.school.employment.entity.*;
import com.school.employment.repository.ConsultRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

        @Service
        public class XmlExportRecordService implements ExportRecordService {

            @Autowired
            private ConsultRecordRepository consultRecordRepository;

            @Override
            public String format() {
                List<ConsultRecord> records = consultRecordRepository.findAll();
                return convertToXml(records);
            }

            private String convertToXml(List<ConsultRecord> records) {
                if (records == null || records.isEmpty()) {
                    return "<?xml version=\"1.0\" encoding=\"UTF-8\"?><consultRecords></consultRecords>";
                }

                StringBuilder xmlBuilder = new StringBuilder();
                xmlBuilder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
                xmlBuilder.append("<consultRecords>\n");

                for (ConsultRecord record : records) {
                    xmlBuilder.append("  <consultRecord>\n");

                    // 基本字段
                    appendXmlElement(xmlBuilder, "id", record.getId(), 4);
                    appendXmlElement(xmlBuilder, "startTime", record.getStartTime(), 4);
                    appendXmlElement(xmlBuilder, "endTime", record.getEndTime(), 4);
                    appendXmlElement(xmlBuilder, "question", record.getQuestion(), 4);
                    appendXmlElement(xmlBuilder, "conclusion", record.getConclusion(), 4);
                    appendXmlElement(xmlBuilder, "status", record.getStatus(), 4);

                    // 咨询师信息
                    if (record.getConsultant() != null) {
                        xmlBuilder.append("    <consultant>\n");
                        Consultant c = record.getConsultant();
                        appendXmlElement(xmlBuilder, "id", c.getId(), 6);
                        appendXmlElement(xmlBuilder, "idCard", c.getIdCard(), 6);
                        appendXmlElement(xmlBuilder, "name", c.getName(), 6);
                        appendXmlElement(xmlBuilder, "phone", c.getPhone(), 6);
                        appendXmlElement(xmlBuilder, "introduction", c.getIntroduction(), 6);
                        xmlBuilder.append("    </consultant>\n");
                    }

                    // 学生信息
                    if (record.getStudent() != null) {
                        xmlBuilder.append("    <student>\n");
                        Student s = record.getStudent();
                        appendXmlElement(xmlBuilder, "id", s.getId(), 6);
                        appendXmlElement(xmlBuilder, "studentId", s.getStudentId(), 6);
                        appendXmlElement(xmlBuilder, "name", s.getName(), 6);
                        appendXmlElement(xmlBuilder, "gender", s.getGender(), 6);
                        appendXmlElement(xmlBuilder, "college", s.getCollege(), 6);
                        appendXmlElement(xmlBuilder, "phone", s.getPhone(), 6);
                        appendXmlElement(xmlBuilder, "birthday", s.getBirthday(), 6);
                        xmlBuilder.append("    </student>\n");
                    }

                    // 房间信息
                    if (record.getRoom() != null) {
                        xmlBuilder.append("    <room>\n");
                        Room r = record.getRoom();
                        appendXmlElement(xmlBuilder, "id", r.getId(), 6);
                        appendXmlElement(xmlBuilder, "roomId", r.getRoomId(), 6);
                        appendXmlElement(xmlBuilder, "campus", r.getCampus(), 6);
                        appendXmlElement(xmlBuilder, "building", r.getBuilding(), 6);
                        appendXmlElement(xmlBuilder, "roomNumber", r.getRoomNumber(), 6);
                        appendXmlElement(xmlBuilder, "capacity", r.getCapacity(), 6);
                        xmlBuilder.append("    </room>\n");
                    }

                    xmlBuilder.append("  </consultRecord>\n");
                }

                xmlBuilder.append("</consultRecords>");
                return xmlBuilder.toString();
            }

            private void appendXmlElement(StringBuilder builder, String tagName, Object value, int indent) {
                if (value != null) {
                    builder.append(" ".repeat(Math.max(0, indent)));
                    builder.append("<").append(tagName).append(">")
                            .append(escapeXml(value.toString()))
                            .append("</").append(tagName).append(">\n");
                }
            }

            private String escapeXml(String text) {
                if (text == null) return "";
                return text.replace("&", "&amp;")
                        .replace("<", "&lt;")
                        .replace(">", "&gt;")
                        .replace("\"", "&quot;")
                        .replace("'", "&apos;");
            }
        }
