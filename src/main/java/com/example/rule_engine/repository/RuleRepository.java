package com.example.rule_engine.repository;


import com.example.rule_engine.RuleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RuleRepository extends JpaRepository<RuleEntity, Long> {
}
