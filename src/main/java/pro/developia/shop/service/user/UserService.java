package pro.developia.shop.service.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pro.developia.shop.domain.user.User;
import pro.developia.shop.dto.user.UserDataDto;
import pro.developia.shop.dto.user.UserDto;
import pro.developia.shop.dto.user.UserSignUpDTO;

public interface UserService {

    boolean isEmailDuplicated(String email);

    User signUp(UserSignUpDTO userSignUpDTO);

    UserDto getUserInfo(Long userId);

    Page<UserDataDto> findAll(String email, String name, Pageable pageable);

}
