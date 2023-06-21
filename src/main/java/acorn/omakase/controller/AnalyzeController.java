package acorn.omakase.controller;

import acorn.omakase.dto.analyzeDto.newAnalyzeRequest;

import acorn.omakase.service.AnalyzeService;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/analyze")
public class AnalyzeController {
    private final AnalyzeService analyzeService;

    @PostMapping("/**")
    public ResponseEntity sendData(@RequestBody newAnalyzeRequest newAnalyzeRequest){
        JsonNode postResultJSON = analyzeService.sendData(newAnalyzeRequest);

        return new ResponseEntity(postResultJSON, HttpStatus.OK);
    }
}