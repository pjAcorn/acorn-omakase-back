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
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.nio.charset.StandardCharsets;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/analyze")
public class AnalyzeController {
//    private final AnalyzeService analyzeService;

    // REST API를 요청하는 주체
    private static RestTemplate restTemplate;

    @PostMapping("/test")
    public ResponseEntity sendData(@RequestBody newAnalyzeRequest newAnalyzeRequest){

        // REST API 반환값에 대한 변환을 위해 선언
        ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        // 요청 URL
        String API_URL = "http://localhost:8000/test";

        // UriComponents 생성
        UriComponents URI_COMPONENTS = UriComponentsBuilder.fromUriString(API_URL).build();

        // 참고로 Patch Method 를 쓰기 위해서는
        // HttpComponentsClientHttpRequestFactory를 사용해야 한다!
        HttpComponentsClientHttpRequestFactory factory
                = new HttpComponentsClientHttpRequestFactory();

        factory.setConnectTimeout(5000);
        factory.setReadTimeout(5000);
        factory.setBufferRequestBody(false);

        // Response 한글 깨짐 방지
        restTemplate = new RestTemplate(factory);
        restTemplate.getMessageConverters()
                .add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));

        // HTTP BODY에 넣을 JSON 생성
        ObjectNode jsonNodes = JsonNodeFactory.instance.objectNode();
        jsonNodes.put("category", newAnalyzeRequest.getCategory());
        jsonNodes.put("address", newAnalyzeRequest.getAddress());

        ResponseEntity<JsonNode> postResult = restTemplate.postForEntity(
                                                URI_COMPONENTS.toUri(),
                                                jsonNodes,
                                                JsonNode.class
                                               );

//        HttpEntity<JsonNode> requestEntity = new HttpEntity<>(jsonNodes);
//
//        ResponseEntity<Map<String, JsonNode>> result = restTemplate.exchange(
//                                                        URI_COMPONENTS.toUri(),
//                                                        HttpMethod.POST,
//                                                        requestEntity,
//                                                        new ParameterizedTypeReference<Map<String, JsonNode>>() {}
//                                                     );

        return new ResponseEntity(HttpStatus.OK);
    }
}
