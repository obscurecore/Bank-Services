package bank.service.userservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * The type Stonk.
 * @author Ruslan Potapov
 */
@Data
@Builder
@AllArgsConstructor
@Document(collection = "buckets")
public class Stonk {

    @Id
    private String id;

    @NotBlank
    @Size(max = 10)
    private String title;

    private String description;
    private int personalNumber;
    private String imageLink;

}
