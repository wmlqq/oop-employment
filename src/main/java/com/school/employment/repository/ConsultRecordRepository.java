package com.school.employment.repository;

import com.school.employment.entity.ConsultRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultRecordRepository extends JpaRepository<ConsultRecord,Long> {
}
