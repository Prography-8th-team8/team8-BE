package prography.cakeke.server.marker.domain;

import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import prography.cakeke.server.common.Core;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Marker extends Core {
    private String name;

    private City city;

    private District district;

    private String latitude;

    private String longitude;
}