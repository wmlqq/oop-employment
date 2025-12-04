package com.school.employment.Controller;

import com.school.employment.dto.ConsultRecordDTO;
import com.school.employment.dto.UpdateConsultRecordDTO;
import com.school.employment.entity.Result;
import com.school.employment.exceptions.FindingException;
import com.school.employment.service.ConsultRecordService;
import com.school.employment.service.ExportRecordService;
import com.school.employment.service.TxtExportRecordService;
import com.school.employment.service.XmlExportRecordService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Validated
@CrossOrigin(origins ="*")
public class ConsultRecordController {

    @Autowired
    private ConsultRecordService consultRecordService;
    @Autowired
    private TxtExportRecordService txtExportRecordService;
    @Autowired
    private XmlExportRecordService xmlExportRecordService;


    @PostMapping("/appointments")
    public Result addConsultRecord(@Valid @RequestBody ConsultRecordDTO consultRecordDTO)  {
        try{
            Long id= consultRecordService.addConsultRecord(consultRecordDTO).getId();
            return new Result(true, "预约成功",id);
        } catch (FindingException e) {
            return new Result(false, e.getMessage(), null);
        }

    }

    @PostMapping("/records")
    public ResponseEntity<Result> updateConsultRecord(@Valid @RequestBody UpdateConsultRecordDTO updateConsultRecordDTO)  {
        try{
            Long id= consultRecordService.updateConsultRecord(updateConsultRecordDTO).getId();
            return ResponseEntity.ok(new Result(true, "咨询已完成",id));
        } catch (FindingException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new Result(false, "咨询记录不存在", null));
        }
    }

    @PostMapping("/consult_records")
    public ResponseEntity<Result> showAllConsultRecord(){
        return ResponseEntity.ok(new Result(true, "获取咨询记录成功", consultRecordService.getAllConsultRecords()));
    }

    /**
     * 导出记录
     * @param format 导出格式，可选值为 "json", "xml", "txt"
     * @return 返回导出结果或文件流
     */
    @GetMapping("/records/export")
    public ResponseEntity<?> exportConsultRecord(@RequestParam String format) {
        HttpHeaders headers = new HttpHeaders();
        switch (format) {
            case "json":return ResponseEntity.ok(new Result(true, "导出成功", consultRecordService.getAllConsultRecords()));
            case "xml":
                String xmlContent = xmlExportRecordService.format();

                headers.setContentType(MediaType.APPLICATION_XML);
                headers.setContentDispositionFormData("attachment", "records.xml");
                return new ResponseEntity<>(xmlContent, headers, HttpStatus.OK);
            case "txt":
                String txtContent = txtExportRecordService.format();
                headers.setContentType(MediaType.TEXT_PLAIN);
                headers.setContentDispositionFormData("attachment", "records.txt");
                return new ResponseEntity<>(txtContent, headers, HttpStatus.OK);
            default :return ResponseEntity.badRequest().body("不支持的导出格式");
        }
    }
}