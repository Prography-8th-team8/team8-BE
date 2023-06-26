package prography.cakeke.server.store.adapter.out.external;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import prography.cakeke.server.store.adapter.in.web.response.StoreNaverBlogSearchApiResponse;
import prography.cakeke.server.store.adapter.in.web.response.StoreNaverLocalSearchApiResponse;
import prography.cakeke.server.store.application.port.out.LoadNaverSearchApiPort;
import prography.cakeke.server.store.exceptions.JsonResponseErrorException;

@Component
public class NaverSearchApiAdapter implements LoadNaverSearchApiPort {
    @Value("${api.naver.search.localpath}")
    String localPath;

    @Value("${api.naver.search.blogpath}")
    String blogPath;

    @Value("${api.naver.search.clientID}")
    String clientID;

    @Value("${api.naver.search.clientSecretKey}")
    String clientSecretKey;

    /** 네이버 api get */
    private JSONArray getNaverSearchApiResponse(String storeName, Integer displayNum, String sortOption,
                                                String path) {
        URI uri = UriComponentsBuilder
                .fromUriString("https://openapi.naver.com")
                .path(path)
                .queryParam("query", storeName)
                .queryParam("display", displayNum)
                .queryParam("start", 1)
                .queryParam("sort", sortOption)
                .encode()
                .build()
                .toUri();

        RestTemplate restTemplate = new RestTemplate();

        RequestEntity<Void> req = RequestEntity
                .get(uri)
                .header("X-Naver-Client-Id", clientID)
                .header("X-Naver-Client-Secret", clientSecretKey)
                .build();

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
        return jsonResult;
    }

    /** 네이버 지역 검색 */
    @Override
    public StoreNaverLocalSearchApiResponse getNaverLocalSearchResponse(String storeName) {
        final Integer DISPLAY_NUM = 1; // 아이템 개수
        /**
         * sortType 옵션
         * random : 정확도순으로 내림차순 정렬(기본값)
         * comment: 업체 및 기관에 대한 카페, 블로그의 리뷰 개수순으로 내림차순 정렬
         * */
        final String sortType = "random";

        JSONArray responseJson = getNaverSearchApiResponse(storeName, DISPLAY_NUM, sortType, localPath);

        String link = "", description = "", phoneNumber = "", address = "";

        if (responseJson.length() > 0) {
            JSONObject responseJsonObject = responseJson.optJSONObject(0);
            if (responseJsonObject != null) {
                link = responseJsonObject.optString("link");
                description = responseJsonObject.optString("description");
                phoneNumber = responseJsonObject.optString("telephone");
                address = responseJsonObject.optString("roadAddress");
            }
        }
        return new StoreNaverLocalSearchApiResponse(link, description, phoneNumber, address);
    }

    /** 네이버 블로그 검색 */
    public List<StoreNaverBlogSearchApiResponse> getNaverBlogSearchResponse(String storeName, Integer blogNum) {
        /**
         * sortType 옵션
         * sim: 정확도순으로 내림차순 정렬(기본값)
         * date: 날짜순으로 내림차순 정렬
         * */
        final String sortType = "sim";
        JSONArray responseJson = getNaverSearchApiResponse(storeName, blogNum, sortType, blogPath);

        List<StoreNaverBlogSearchApiResponse> storeNaverBlogSearchApiResponseList = new ArrayList<>();
        for (int i = 0; i < responseJson.length(); i++) {
            JSONObject responseJsonObject = responseJson.optJSONObject(i);

            String title = responseJsonObject.optString("title");
            String link = responseJsonObject.optString("link");
            String description = responseJsonObject.optString("description");
            String bloggername = responseJsonObject.optString("bloggername");
            String bloggerlink = responseJsonObject.optString("bloggerlink");
            String postDate = responseJsonObject.optString("postdate");

            StoreNaverBlogSearchApiResponse apiResponseItem = new StoreNaverBlogSearchApiResponse(title, link,
                                                                                                  description,
                                                                                                  bloggername,
                                                                                                  bloggerlink,
                                                                                                  postDate);
            storeNaverBlogSearchApiResponseList.add(apiResponseItem);
        }
        return storeNaverBlogSearchApiResponseList;
    }
}
