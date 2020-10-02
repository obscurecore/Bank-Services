package bank.service.orderservice.model;

import lombok.*;

import java.io.Serializable;


@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Message implements Serializable {
    private String message;
}
