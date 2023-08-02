package com.example.cmpt276project.models;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Integer> {

  Question findByQid(int qid);
} 