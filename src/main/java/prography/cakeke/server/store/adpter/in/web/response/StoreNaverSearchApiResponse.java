package prography.cakeke.server.store.adpter.in.web.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class StoreNaverSearchApiResponse {
    String link; // 가게 URL

    String description; // 가게 설명

    String phoneNumber; // 가게 번호

    String address; // 도로명 주소

    @Builder
    public StoreNaverSearchApiResponse(
            String link, String description, String phoneNumber, String address
    ) {
        this.link = link;
        this.description = description;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }
}
