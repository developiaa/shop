package pro.developia.shop.domain.product;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import pro.developia.shop.domain.Common;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product extends Common {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String productName;

    @Builder
    public Product(Long id, String productName) {
        this.id = id;
        this.productName = productName;
    }
}
