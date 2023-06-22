package prography.cakeke.server.storeControllerTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.ResultActions;

import prography.cakeke.server.common.BaseTest;

public class GetStoreDetailTest extends BaseTest {
    @Test
    @DisplayName("케이크샵 상세 정보 조회 테스트(성공)")
    public void getStoreDetailTestSuccess() throws Exception {
        // given
        String expectedJson =
                "{\"id\":" + testStoreId
                + ",\"createdAt\":\"2023-06-01T22:17:05.90791\",\"modifiedAt\":\"2023-06-13T13:20:00.352762\",\"name\":\"겸재케이크\",\"shareLink\":\"https://naver.me/5L3ooY3t\",\"city\":\"SEOUL\",\"district\":\"JONGNO\",\"location\":\"서울 종로구 필운대로2길 23 1층\",\"latitude\":37.5791727,\"longitude\":126.9696887,\"storeTypes\":[],\"link\":\"http://instagram.com/gyeomjae.___.cake\",\"description\":\"\",\"phoneNumber\":\"\",\"address\":\"서울특별시 종로구 필운대로2길 23 1층\"}";

        // when
        ResultActions result = mockMvc.perform(get("/api/store/" + testStoreId.toString()));

        // then
        result.andExpect(status().isOk()).andExpect(content().json(expectedJson));
    }

}
