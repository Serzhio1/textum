package com.serzhio_pet_projects.textum.controller;

import com.serzhio_pet_projects.textum.dto.TextAnalysisRequest;
import com.serzhio_pet_projects.textum.model.TextAnalysisResult;
import com.serzhio_pet_projects.textum.service.TextAnalysisService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;


@WebMvcTest(TextAnalysisController.class)
class TextAnalysisControllerTest {

    @MockitoBean
    private TextAnalysisService mockService;

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private Model mockModel;

    private TextAnalysisController controller;

    @BeforeEach
    void setUp() {
        controller = new TextAnalysisController(mockService);
    }

    @Test
    void testShowForm() {
        String viewName = controller.showForm(mockModel);

        assertEquals("index", viewName);
        Mockito.verify(mockModel).addAttribute(eq("request"), any(TextAnalysisRequest.class));
    }

    @Test
    void testGetLast5Requests() {
        List<TextAnalysisResult> mockResults = List.of(new TextAnalysisResult(), new TextAnalysisResult());
        Mockito.when(mockService.getLast5Requests()).thenReturn(mockResults);

        String viewName = controller.getLast5Requests(mockModel);

        assertEquals("lastResults", viewName);
        Mockito.verify(mockService).getLast5Requests();
        Mockito.verify(mockModel).addAttribute("last5Results", mockResults);
    }

    @Test
    void testAnalyzeText() {
        TextAnalysisRequest testRequest = new TextAnalysisRequest();
        testRequest.setText("test text");
        TextAnalysisResult testResult = new TextAnalysisResult();
        Mockito.when(mockService.analyzeText("test text")).thenReturn(testResult);

        String viewName = controller.analyzeText(testRequest, mockModel);

        assertEquals("result", viewName);
        Mockito.verify(mockService).analyzeText("test text");
        Mockito.verify(mockModel).addAttribute("result", testResult);
    }
}