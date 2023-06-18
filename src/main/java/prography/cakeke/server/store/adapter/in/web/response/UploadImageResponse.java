package prography.cakeke.server.store.adapter.in.web.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UploadImageResponse {
    List<String> imageUrlList;
}
