package prography.cakeke.server.store.adapter.in.web.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import prography.cakeke.server.store.domain.District;

@Getter
@NoArgsConstructor
public class DistrictCountDTO {

    District district;

    Long count;

}
