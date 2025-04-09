package com.serzhio_pet_projects.textum.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static com.serzhio_pet_projects.textum.util.TextAnalyzer.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.InputStream;
import java.util.List;
import java.util.stream.Stream;


public class TextAnalyzerTest {

    record TestCase(String inputStr, String[] expectedList) {}

    @ParameterizedTest
    @MethodSource("testCasesForCalculateAverageLengthMethod")
    void testGetAverageSentenceLength(String[] testSentenceList, double expectedAverageSentenceLength) {
        assertEquals(expectedAverageSentenceLength, calculateAverageLenght(testSentenceList));
    }

    private static Stream<Arguments> testCasesForCalculateAverageLengthMethod() {
        return Stream.of(
                Arguments.of(null, 0),
                Arguments.of(new String[0], 0.0),
                Arguments.of(new String[]{""}, 0.0),
                Arguments.of(new String[]{"a"}, 1.0),
                Arguments.of(new String[]{"a", "bb", "ccc"}, 2.0),
                Arguments.of(new String[]{"", "", ""}, 0.0),
                Arguments.of(new String[]{"", "a", "bb", ""}, 0.75),
                Arguments.of(new String[]{" a ", "  bb  ", "   ccc   "}, 6.0),
                Arguments.of(new String[]{"", "", "", "a"}, 0.25),
                Arguments.of(new String[]{"word!", "123", "@#$%"}, 4.0),
                Arguments.of(new String[]{"Hello"}, 5.0),
                Arguments.of(new String[]{"Hello", "World"}, 5.0),
                Arguments.of(new String[]{"Hi", "there", "world"}, 4.0),
                Arguments.of(new String[]{"Hello!", "How are you?"}, 9.0),
                Arguments.of(new String[]{" ", "\t", "\n"}, 1.0),
                Arguments.of(new String[]{"Verylongword" + "a".repeat(1000)}, 1012.0)
        );
    }

    @ParameterizedTest
    @MethodSource("testCasesForGetListSentencesMethodFromJson")
    void testGetListSentences(String inputStr, String[] expectedList) {
        assertArrayEquals(expectedList, getListSentences(inputStr));
    }

    static Stream<Arguments> testCasesForGetListSentencesMethodFromJson() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        InputStream inputStream = TextAnalyzerTest.class.getResourceAsStream("/testDataForGetListSentencesMethod.json");
        TestCase[] testCases = mapper.readValue(inputStream, TestCase[].class);

        return Stream.of(testCases)
                .map(testCase -> Arguments.of(testCase.inputStr, testCase.expectedList));
    }

    @TestFactory
    Stream<DynamicTest> dynamicTestForGetListWords() {

        List<TestCase> testCases = List.of(
                new TestCase("Hello world", new String[]{"Hello", "world"}),
                new TestCase("Hello           world", new String[]{"Hello", "world"}),
                new TestCase("  Multiple   spaces  ", new String[]{"Multiple", "spaces"}),
                new TestCase("Multiple   spaces   ", new String[]{"Multiple", "spaces"}),
                new TestCase("  Multiple   spaces", new String[]{"Multiple", "spaces"}),
                new TestCase("Multiple spaces a b c  ", new String[]{"Multiple", "spaces", "a", "b", "c"}),
                new TestCase("a b c d e f g k l m", new String[]{"a", "b", "c", "d", "e", "f", "g", "k", "l", "m"}),
                new TestCase("oneWord", new String[]{"oneWord"}),
                new TestCase("One! Two?", new String[]{"One!", "Two?"}),
                new TestCase("a", new String[]{"a"}),
                new TestCase("a", new String[]{"a"}),
                new TestCase("", new String[0]),
                new TestCase(" ", new String[0]),
                new TestCase("          ", new String[0]),
                new TestCase(null, new String[0])
        );

        return testCases.stream()
                .map(testCase -> DynamicTest.dynamicTest(
                        "Тест для: " + testCase.inputStr(),
                        () -> {
                            String[] actualList = getListWords(testCase.inputStr());
                            assertArrayEquals(testCase.expectedList(), actualList);
                        }
                ));
    }

    @ParameterizedTest
    @MethodSource("testCasesForMostFrequentWordMethod")
    void testGetMostFrequencyWord(String[] inputWords, String expectedResult) {
        assertEquals(expectedResult, getMostFrequencyWord(inputWords));
    }

    private static Stream<Arguments> testCasesForMostFrequentWordMethod() {
        return Stream.of(
                Arguments.of(new String[]{"apple", "banana", "apple"}, "apple"),
                Arguments.of(new String[]{"cat", "dog", "bird"}, "cat"),
                Arguments.of(new String[]{"dog", "cat", "bird", "cat", "dog"}, "cat"),
                Arguments.of(new String[]{"hello!", "hello", "hello?"}, "hello"),
                Arguments.of(new String[]{"stop.", "stop", "stop!"}, "stop"),
                Arguments.of(new String[]{"Apple", "apple", "APPLE"}, "apple"),
                Arguments.of(new String[]{""}, ""),
                Arguments.of(new String[]{"", "", ""}, ""),
                Arguments.of(new String[0], "N/A"),
                Arguments.of(new String[]{"can't", "wont", "can't"}, "can't")
        );
    }
}
