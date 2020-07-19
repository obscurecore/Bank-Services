package bank.service.userservice.client.common;

import lombok.Builder;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Builder

@NoArgsConstructor
public class GeoPosition {
    private BigDecimal longitude;
    private BigDecimal latitude;

    public GeoPosition(BigDecimal longitude, BigDecimal latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }
}
