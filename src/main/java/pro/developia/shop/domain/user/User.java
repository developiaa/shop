package pro.developia.shop.domain.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import pro.developia.shop.domain.Common;
import pro.developia.shop.domain.order.Order;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends Common {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @NotEmpty
    @Column(length = 20, nullable = false)
    //이름
    private String name;

    @NotEmpty
    @Column(length = 30, nullable = false)
    //별명
    private String nickName;

    @NotEmpty
    @Column(nullable = false)
    //비밀번호
    private String pw;

    @NotEmpty
    @Column(length = 20, nullable = false)
    //전화번호
    private String phone;

    @NotEmpty
    @Column(length = 100, nullable = false, unique = true)
    //이메일
    private String email;

    //성별
    @Column(length = 1)
    private String gender;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Order> orders = new ArrayList<>();

}
