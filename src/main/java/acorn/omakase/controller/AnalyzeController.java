package acorn.omakase.controller;

import acorn.omakase.dto.analyzeDto.newAnalyzeRequest;
import acorn.omakase.dto.commentDto.newCommentRequest;
import acorn.omakase.service.AnalyzeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/analyze")
public class AnalyzeController {
//    private final AnalyzeService analyzeService;

    @PostMapping("/test")
    public ResponseEntity sendData(@RequestBody newAnalyzeRequest newAnalyzeRequest){
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add(newAnalyzeRequest.getCategory(), newAnalyzeRequest.getAddress());

//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Header", "header");

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(params);

        RestTemplate rt = new RestTemplate();

        HttpEntity<String> response = rt.exchange(
                "http://localhost:8000/test",
                HttpMethod.POST,
                entity,
                String.class
        );

        return new ResponseEntity(HttpStatus.OK);
    }
}
