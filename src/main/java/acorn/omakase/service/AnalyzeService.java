package acorn.omakase.service;

import acorn.omakase.dto.analyzeDto.newAnalyzeRequest;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.nio.charset.StandardCharsets;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class AnalyzeService {
    // REST API를 요청하는 주체
    private static RestTemplate restTemplate;

    public JsonNode sendData(newAnalyzeRequest newAnalyzeRequest) {

        // REST API 반환값에 대한 변환을 위해 선언
//        ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        // 요청 URL
        String API_URL = "http://localhost:8000/analyze";

        // UriComponents 생성
        UriComponents URI_COMPONENTS = UriComponentsBuilder.fromUriString(API_URL).build();

        // 참고로 Patch Method 를 쓰기 위해서는
        // HttpComponentsClientHttpRequestFactory를 사용해야 한다!
        HttpComponentsClientHttpRequestFactory factory
                = new HttpComponentsClientHttpRequestFactory();

        factory.setConnectTimeout(100000);
        factory.setReadTimeout(100000);
        factory.setBufferRequestBody(false);

        // Response 한글 깨짐 방지
        restTemplate = new RestTemplate(factory);
        restTemplate.getMessageConverters()
                .add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));

        // HTTP BODY에 넣을 JSON 생성
        ObjectNode jsonNodes = JsonNodeFactory.instance.objectNode();
        jsonNodes.put("addressSido", newAnalyzeRequest.getAddressSido());
        jsonNodes.put("addressSigungu", newAnalyzeRequest.getAddressSigungu());
        jsonNodes.put("cateLData", newAnalyzeRequest.getCateLData());
        jsonNodes.put("cateAData", newAnalyzeRequest.getCateAData());
        jsonNodes.put("cateMData", newAnalyzeRequest.getCateMData());

        ResponseEntity<JsonNode> postResult = restTemplate.postForEntity(
                URI_COMPONENTS.toUri(),
                jsonNodes,
                JsonNode.class
        );

        JsonNode postResultJSON = postResult.getBody();
        return postResultJSON;
    }
}
