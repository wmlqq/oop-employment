package com.school.employment.repository;

import com.school.employment.entity.Consultant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsultantRepository extends JpaRepository<Consultant, Long> {
    // 根据类型查询咨询师
    @Query("SELECT c FROM Consultant c WHERE TYPE(c) = :type")
    List<Consultant> findByType(@Param("type") Class<? extends Consultant> type);

    // 查询所有大学咨询师
    @Query("SELECT c FROM OwnConsultant c")
    List<Consultant> findAllOwnConsultants();

    // 查询所有企业咨询师
    @Query("SELECT c FROM CompanyConsultant c")
    List<Consultant> findAllCompanyConsultants();


    List<Consultant> findByNameContainingOrId(String name, Long id);

    List<Consultant> findByNameContaining(String keyword);
}
