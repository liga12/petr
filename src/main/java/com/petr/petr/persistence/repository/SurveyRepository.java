package com.petr.petr.persistence.repository;

import com.petr.petr.persistence.entity.Survey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SurveyRepository extends JpaRepository<Survey, Long>, JpaSpecificationExecutor<Survey> {

    boolean existsByName(String name);
}