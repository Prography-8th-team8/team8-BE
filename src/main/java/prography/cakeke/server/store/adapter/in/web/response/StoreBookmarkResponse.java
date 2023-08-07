package prography.cakeke.server.store.adapter.in.web.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import prography.cakeke.server.store.domain.District;
import prography.cakeke.server.store.domain.Store;

@Getter
@AllArgsConstructor
public class StoreBookmarkResponse {

    Long id;

    String name;

    District district;

    String location;

    List<String> imageUrls;

    public StoreBookmarkResponse(Store store) {
        this.id = store.getId();
        this.name = store.getName();
        this.district = store.getDistrict();
        this.location = store.getLocation();
        this.imageUrls = store.getImageUrls() != null ? store.getImageUrls() : List.of();
    }
}