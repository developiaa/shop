package pro.developia.shop.dto.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
public class UserDataDto {
    private Object userId;
    private String email;
    private String gender;
    private String name;
    private String nickName;
    private String phone;

    private Object orderId;
    private Integer count;
    private Timestamp orderDate;
    private String orderNumber;
    private Integer orderPrice;
    private String productName;


}
