package com.serzhio_pet_projects.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.serzhio_pet_projects.model.TextAnalysisResult;
import com.serzhio_pet_projects.repository.TextAnalysisRepository;
import static com.serzhio_pet_projects.util.TextAnalyzer.getAverageSentenceLength;
import static com.serzhio_pet_projects.util.TextAnalyzer.getAverageWordLength;
import static com.serzhio_pet_projects.util.TextAnalyzer.getListSentences;
import static com.serzhio_pet_projects.util.TextAnalyzer.getListWords;
import static com.serzhio_pet_projects.util.TextAnalyzer.getMostFrequencyWord;


@ Service
public class TextAnalysisService {

    private static TextAnalysisRepository repository;

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
