package qartest.work.dto.transaction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import qartest.work.model.PaymentType;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionResponse {
    private Long id;
    private Long sellerId;
    private long amount;
    private PaymentType paymentType;
    private LocalDateTime transactionDate;
}
