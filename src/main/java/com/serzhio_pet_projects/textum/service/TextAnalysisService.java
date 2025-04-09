package com.serzhio_pet_projects.textum.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.serzhio_pet_projects.textum.model.TextAnalysisResult;
import com.serzhio_pet_projects.textum.repository.TextAnalysisRepository;

import static com.serzhio_pet_projects.textum.util.TextAnalyzer.*;


@ Service
public class TextAnalysisService {

    private TextAnalysisRepository repository;

    @Autowired
    public TextAnalysisService(TextAnalysisRepository repository) {
        this.repository = repository;
    }

    public TextAnalysisResult analyzeText(String text) {

        text = text.trim();
        String[] words = getListWords(text);
        String[] sentences = getListSentences(text);

        TextAnalysisResult resultEntity = new TextAnalysisResult();
        resultEntity.setText(text);
        resultEntity.setWordCount(words.length);
        resultEntity.setCharacterCount(text.length());
        resultEntity.setSentenceCount(sentences.length);
        resultEntity.setAvgWordLength(calculateAverageLenght(words));
        resultEntity.setAvgSentenceLength(calculateAverageLenght(sentences));
        resultEntity.setMostFrequentWord(getMostFrequencyWord(words));
        resultEntity.setCreatedAt(LocalDateTime.now());

        return repository.save(resultEntity);
    }

    public List<TextAnalysisResult> getLast5Requests() {
        return repository.get5LastRequestsFromDb();
    }
}
