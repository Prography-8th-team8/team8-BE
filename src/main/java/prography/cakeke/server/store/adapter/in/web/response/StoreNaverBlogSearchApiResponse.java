package prography.cakeke.server.store.adapter.in.web.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class StoreNaverBlogSearchApiResponse {
    String title;

    String link;

    String description;

    String bloggername;

    String bloggerlink;

    String postdate;

    @Builder
    public StoreNaverBlogSearchApiResponse(
            String title, String link, String description, String bloggername, String bloggerlink,
            String postdate
    ) {
        this.title = title;
        this.link = link;
        this.description = description;
        this.bloggername = bloggername;
        this.bloggerlink = bloggerlink;
        this.postdate = postdate;
    }
}
