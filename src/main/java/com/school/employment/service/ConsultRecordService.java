package com.school.employment.service;


import com.school.employment.dto.ConsultRecordDTO;
import com.school.employment.dto.UpdateConsultRecordDTO;
import com.school.employment.entity.ConsultRecord;
import com.school.employment.entity.Consultant;
import com.school.employment.entity.Room;
import com.school.employment.entity.Student;
import com.school.employment.exceptions.FindingException;
import com.school.employment.repository.ConsultRecordRepository;
import com.school.employment.repository.ConsultantRepository;
import com.school.employment.repository.RoomRepository;
import com.school.employment.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Transactional
public class ConsultRecordService {

    @Autowired
    private ConsultRecordRepository consultRecordRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private ConsultantRepository consultantRepository;
    @Autowired
    private RoomRepository roomRepository;

    public ConsultRecord addConsultRecord(ConsultRecordDTO consultRecordDTO) throws FindingException {
        ConsultRecord consultRecord = new ConsultRecord();
        Student student= studentRepository.findById(consultRecordDTO.getStudentId()).orElse(null);
        if(student==null)throw new FindingException("学生不存在");

        consultRecord.setStudent(student);

        Consultant consultant= consultantRepository.findById(consultRecordDTO.getConsultantId()).orElse(null);
        if(consultant==null)throw new FindingException("咨询师不存在");

        consultRecord.setConsultant(consultant);

        Room room= roomRepository.findById(consultRecordDTO.getRoomId()).orElse(null);
        if(room==null)throw new FindingException("房间不存在");

        consultRecord.setRoom(room);

        consultRecord.setStartTime(consultRecordDTO.getStartTime());
        consultRecord.setStatus(consultRecordDTO.getStatus());

        return consultRecordRepository.save(consultRecord);
    }

    public ConsultRecord updateConsultRecord(UpdateConsultRecordDTO updateConsultRecordDTO) throws FindingException {
        ConsultRecord consultRecord= consultRecordRepository.findById(updateConsultRecordDTO.getRecordId()).orElse(null);
        if(consultRecord==null)throw new FindingException("咨询记录不存在");
        consultRecord.setEndTime(updateConsultRecordDTO.getEndTime());
        consultRecord.setQuestion(updateConsultRecordDTO.getQuestion());
        consultRecord.setConclusion(updateConsultRecordDTO.getConclusion());
        consultRecord.setStatus("已完成");
        return consultRecordRepository.save(consultRecord);
    }

    public List<ConsultRecord> getAllConsultRecords() {
        return consultRecordRepository.findAll();
    }
}
