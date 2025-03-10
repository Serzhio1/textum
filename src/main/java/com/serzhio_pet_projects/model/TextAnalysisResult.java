package com.serzhio_pet_projects.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TextAnalysisResult {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition="TEXT")
    private String text;

    private int wordCount;
    private int characterCount;
    private int sentenceCount;
    private double avgWordLength;
    private double avgSentenceLength;
    private String mostFrequentWord;
    private LocalDateTime createdAt;
}
