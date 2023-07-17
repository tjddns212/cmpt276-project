package com.example.cmpt276project.models;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
    List<Image> findByName(String name);

    List<Image> findByUid(int uid);

    List<Image> findByType(String type);
}