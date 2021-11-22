package pro.developia.shop.repository.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pro.developia.shop.dto.user.UserDataDto;

public interface UserCustomRepository {
    Page<UserDataDto> findAll(String email, String name, Pageable pageable);
}
