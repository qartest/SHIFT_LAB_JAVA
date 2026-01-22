package qartest.work.dto.seller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SellerResponseWithSum {
    private Long id;
    private String name;
    private String contactInfo;
    private LocalDateTime registrationDate;
    private Long sum;
}
