package com.serzhio_pet_projects.textum.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.serzhio_pet_projects.textum.model.TextAnalysisResult;


@Repository
public interface TextAnalysisRepository extends JpaRepository<TextAnalysisResult, Long>{

    @Query("SELECT t FROM TextAnalysisResult t ORDER BY t.createdAt DESC LIMIT 5")
    List<TextAnalysisResult> get5LastRequestsFromDb();
}
