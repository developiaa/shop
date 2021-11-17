package pro.developia.shop.domain.order;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import pro.developia.shop.domain.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @NotEmpty
    @Column(length = 12, nullable = false)
    //주문번호
    private String orderNumber;

    @NotEmpty
    @Column(length = 100, nullable = false)
    //제품명
    private String productName;

    @NotEmpty
    @CreationTimestamp
    @Column(nullable = false)
    //결제일시
    public LocalDateTime orderDate;

    @NotEmpty
    @Column(length = 9, nullable = false)
    //주문가격
    private Integer orderPrice;

    @NotEmpty
    @Column(length = 9, nullable = false)
    //주문 수량
    private Integer count;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;


    //연관관계 메소드
    public void setUser(User user) {
        this.user = user;
        user.getOrders().add(this);
    }


}
