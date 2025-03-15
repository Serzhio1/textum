package com.serzhio_pet_projects.textum.util;

import java.util.HashMap;
import java.util.Map;


public class TextAnalyzer {

    public static String[] getListWords(String text) {
        return text.split("\\s+");
    }

    public static String[] getListSentences(String text) {
        return text.split("[.!?]\\s*");
    }

    public static double getAverageWordLength(String[] words) {
        if (words.length == 0) {
            return 0;
        } else {
            int totalLengthAllWords = 0;
            for (String word: words) {
                totalLengthAllWords += word.length();
            }
            return (double) totalLengthAllWords /words.length;
        }
    }

    public static double getAverageSentenceLength(String[] sentences) {
        if (sentences.length == 0) {
            return 0;
        } else {
            int totalLengthAllWords = 0;
            for (String sentence: sentences) {
                totalLengthAllWords += sentence.length();
            }
            return (double) totalLengthAllWords /sentences.length;
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
