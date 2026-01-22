package qartest.work.dto.seller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SellerResponse {
    private Long id;
    private String name;
    private String contactInfo;
    private LocalDateTime registrationDate;
}
