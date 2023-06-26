package prography.cakeke.server.storeControllerTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URI;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import prography.cakeke.server.common.BaseTest;
import prography.cakeke.server.store.exceptions.JsonResponseErrorException;

public class GetNaverSearchApiTest extends BaseTest {
    @Test
    @DisplayName("네이버 장소 검색 api 테스트(성공)")
    public void getNaverSearchApiTestSuccess() {
        // given
        String expectedResponse =
                "[{\"address\":\"서울특별시 종로구 누하동 219-3 1층\",\"roadAddress\":\"서울특별시 종로구 필운대로2길 23 1층\",\"link\":\"http://instagram.com/gyeomjae.___.cake\",\"description\":\"\",\"telephone\":\"\",\"title\":\"<b>겸재케이크<\\/b>\",\"category\":\"카페,디저트>케이크전문\",\"mapy\":\"553495\",\"mapx\":\"309197\"}]";
        // when
        ByteBuffer buffer = StandardCharsets.UTF_8.encode(testStoreName);
        String encode = StandardCharsets.UTF_8.decode(buffer).toString();

        String expectedApiUrl = "https://openapi.naver.com";
        URI uri = UriComponentsBuilder.fromUriString(expectedApiUrl)
                                      .path(testLocalPath)
                                      .queryParam("query", encode)
                                      .queryParam("display", testDisplayNum)
                                      .queryParam("start", 1)
                                      .queryParam("sort", "random")
                                      .build().encode()
                                      .toUri();

        RequestEntity<Void> req = RequestEntity
                .get(uri)
                .header("X-Naver-Client-Id", testClientID)
                .header("X-Naver-Client-Secret", testClientSecretKey)
                .build();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(req, String.class);
        String responseBody = response.getBody();

        JSONArray jsonResult = new JSONArray();
        if (responseBody != null) {
            try {
                JSONObject rjson = new JSONObject(responseBody);
                jsonResult = rjson.getJSONArray("items");
            } catch (JSONException e) {
                throw new JsonResponseErrorException();
            }
        }

        // then
        assertEquals(jsonResult.toString(), expectedResponse);
    }
}
