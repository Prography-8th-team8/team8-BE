package prography.cakeke.server.store.adapter.in.web.response;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
public class StoreBlogResponse {
    List<StoreNaverBlogSearchApiResponse> blogPosts; // 네이버 블로그 목록

    @Builder
    public StoreBlogResponse(List<StoreNaverBlogSearchApiResponse> blogPosts) {
        this.blogPosts = blogPosts;
    }
}
