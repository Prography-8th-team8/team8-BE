package prography.cakeke.server.store.adpter.out.web;

import java.net.URI;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import prography.cakeke.server.store.adpter.in.web.response.StoreNaverSearchApiResponse;

@Component
public class NaverApiResult {
    @Value("${api.naver.search.path}")
    String path;

    @Value("${api.naver.search.clientID}")
    String clientID;

    @Value("${api.naver.search.clientSecretKey}")
    String clientSecretKey;

    public StoreNaverSearchApiResponse getNaverSearchApiResponse(String storeName){
        ByteBuffer buffer = StandardCharsets.UTF_8.encode(storeName);
        String encode = StandardCharsets.UTF_8.decode(buffer).toString();

        URI uri = UriComponentsBuilder
                .fromUriString("https://openapi.naver.com")
                .path(path)
                .queryParam("query",encode)
                .queryParam("display",1)
                .queryParam("start",1)
                .queryParam("sort","random")
                .encode()
                .build()
                .toUri();

        RestTemplate restTemplate = new RestTemplate();

        RequestEntity<Void> req = RequestEntity
                .get(uri)
                .header("X-Naver-Client-Id", clientID)
                .header("X-Naver-Client-Secret",clientSecretKey)
                .build();

        ResponseEntity<String> result = restTemplate.exchange(req, String.class);

        String responseJson = result.getBody();

        int linkStartIndex = Objects.requireNonNull(responseJson).indexOf("\"link\":\"") + 8;
        int linkEndIndex = responseJson.indexOf("\"", linkStartIndex);
        String link = responseJson.substring(linkStartIndex, linkEndIndex);

        int descriptionStartIndex = responseJson.indexOf("\"description\":\"") + 15;
        int descriptionEndIndex = responseJson.indexOf("\"", descriptionStartIndex);
        String description = responseJson.substring(descriptionStartIndex, descriptionEndIndex);

        int telephoneStartIndex = responseJson.indexOf("\"telephone\":\"") + 13;
        int telephoneEndIndex = responseJson.indexOf("\"", telephoneStartIndex);
        String phoneNumber = responseJson.substring(telephoneStartIndex, telephoneEndIndex);

        int roadAddressStartIndex = responseJson.indexOf("\"roadAddress\":\"") + 15;
        int roadAddressEndIndex = responseJson.indexOf("\"", roadAddressStartIndex);
        String address = responseJson.substring(roadAddressStartIndex, roadAddressEndIndex);

        return new StoreNaverSearchApiResponse(link, description, phoneNumber, address);
    }
}
