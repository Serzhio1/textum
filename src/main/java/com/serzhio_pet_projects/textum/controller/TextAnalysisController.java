package com.serzhio_pet_projects.textum.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.serzhio_pet_projects.textum.dto.TextAnalysisRequest;
import com.serzhio_pet_projects.textum.model.TextAnalysisResult;
import com.serzhio_pet_projects.textum.service.TextAnalysisService;


@Controller
public class TextAnalysisController {

    private final TextAnalysisService service;

    public TextAnalysisController(TextAnalysisService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String showForm(Model model) {
        model.addAttribute("request", new TextAnalysisRequest());
        return "index";
    }

    @GetMapping("/last5Requests")
    public String getLast5Requests(Model model) {
        List<TextAnalysisResult> last5Results = service.getLast5Requests();
        model.addAttribute("last5Results", last5Results);
        return "lastResults";
    }

    @PostMapping("/analyze")
    public String analyzeText(@ModelAttribute TextAnalysisRequest request, Model model) {
        TextAnalysisResult analysisResult = service.analyzeText(request.getText());
        model.addAttribute("result", analysisResult);
        return "result";
    }
}
