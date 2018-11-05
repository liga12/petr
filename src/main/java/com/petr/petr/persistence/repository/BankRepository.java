package com.petr.petr.persistence.repository;

import com.petr.petr.persistence.entity.Bank;
import com.petr.petr.persistence.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BankRepository extends JpaRepository<Bank, Long>, JpaSpecificationExecutor<Bank> {

    boolean existsByName(String name);

    User findByName(String name);
}