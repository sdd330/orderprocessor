package com.github.sdd330.domain;

import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface StepRepository extends JpaRepository<Step, UUID>{
}