package qartest.work.dto.transaction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import qartest.work.model.PaymentType;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor

public class TransactionDto {
    private Long sellerId;
    private long amount;
    private PaymentType paymentType;
}
