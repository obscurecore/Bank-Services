package bank.service.finecardservice.client.dto;

import bank.service.finecardservice.entity.FineCard;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * The type Fine dto.
 *
 * @author Ruslan Potapov
 */
@Data
@NoArgsConstructor
public class FineDTO {
    private String id;
    private BigDecimal amount;
    private FineCard.FineType fineType;
    private Date dueDate;
}
