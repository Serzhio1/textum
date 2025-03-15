package com.serzhio_pet_projects.textum.service;

import static com.serzhio_pet_projects.textum.util.TextAnalyzer.getAverageSentenceLength;
import static com.serzhio_pet_projects.textum.util.TextAnalyzer.getAverageWordLength;
import static com.serzhio_pet_projects.textum.util.TextAnalyzer.getListSentences;
import static com.serzhio_pet_projects.textum.util.TextAnalyzer.getListWords;
import static com.serzhio_pet_projects.textum.util.TextAnalyzer.getMostFrequencyWord;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.serzhio_pet_projects.textum.model.TextAnalysisResult;
import com.serzhio_pet_projects.textum.repository.TextAnalysisRepository;


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
        resultEntity.setAvgWordLength(getAverageWordLength(words));
        resultEntity.setAvgSentenceLength(getAverageSentenceLength(sentences));
        resultEntity.setMostFrequentWord(getMostFrequencyWord(words));
        resultEntity.setCreatedAt(LocalDateTime.now());

        return repository.save(resultEntity);
    }
}
