package bank.service.finecardservice.client.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type User data.
 *
 * @author Ruslan Potapov
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserData {
    private String userId;
    private GeoPosition geoPosition;
    private Long currentDate;
}
