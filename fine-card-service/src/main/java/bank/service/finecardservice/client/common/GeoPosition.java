package bank.service.finecardservice.client.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * The type Geo position.
 *
 * @author Ruslan Potapov
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GeoPosition {
    private BigDecimal longitude;
    private BigDecimal latitude;

}





