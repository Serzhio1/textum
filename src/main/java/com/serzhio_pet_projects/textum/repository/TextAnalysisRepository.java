package com.serzhio_pet_projects.textum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.serzhio_pet_projects.textum.model.TextAnalysisResult;


@Repository
public interface TextAnalysisRepository extends JpaRepository<TextAnalysisResult, Long>{
    
}
