package bank.service.userservice.client.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserData {
    private String userId;
    private GeoPosition geoPosition;
    private Long currentDate;
}
