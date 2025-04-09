package com.serzhio_pet_projects.textum.util;

import java.util.HashMap;
import java.util.Map;


public class TextAnalyzer {

    public static String[] getListWords(String text) {
        if (text == null || text.isBlank()) {
            return new String[0];
        }
        else {
            String textWithoutSpacesAtEnds = text.trim();
            return textWithoutSpacesAtEnds.split("\\s+");
        }
    }

    public static String[] getListSentences(String text) {
        if (text == null) {
            return new String[0];
        } else {
            return text.split("(?<=[.!?])\\s+");
        }
    }

    public static double calculateAverageLenght(String[] elements) {
        if (elements == null || elements.length == 0) {
            return 0;
        } else {
            int totalLength = 0;
            for (String sentence: elements) {
                totalLength += sentence.length();
            }
            return (double) totalLength /elements.length;
        }
    }

    public static String getMostFrequencyWord(String[] words) {
        String mostFrequentWord = "N/A";
        int countMostFrequentWord = 0;

        Map<String, Integer> wordFrequency = new HashMap<>();
        for (String word: words) {
            String cleanedWord = word.replaceAll("[.!?]$", "").toLowerCase();
            wordFrequency.put(cleanedWord, wordFrequency.getOrDefault(cleanedWord, 0) + 1);
        }
        for (Map.Entry<String, Integer> entry: wordFrequency.entrySet()) {
            if (entry.getValue() > countMostFrequentWord) {
                mostFrequentWord = entry.getKey();
                countMostFrequentWord = entry.getValue();
            }
        }
        return mostFrequentWord;
    }
}
