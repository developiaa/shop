package pro.developia.shop.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import pro.developia.shop.domain.user.User;
import pro.developia.shop.domain.user.UserRole;
import pro.developia.shop.repository.user.UserCustomRepository;
import pro.developia.shop.repository.user.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
//@Rollback(false)
@Transactional
class UserServiceTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserCustomRepository userCustomRepository;

    @PersistenceContext
    EntityManager em;

    @BeforeEach
    void createUser() {
        List<User> userList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
             userList.add(User.builder()
                    .name("이름_test_" + i)
                    .nickName("별명_test_" + i)
                    .phone("0109170199" + i)
                    .pw("abcdefgIjkl1@")
                    .email("developia" + i + "@gmail.com")
                    .gender("1")
                    .role(UserRole.ROLE_USER)
                    .build());
        }
        userRepository.saveAll(userList);
    }

    @Test
    void paging(){
        //given
        String email = "developia1@gmail.com";
        PageRequest pageRequest = PageRequest.of(0, 5, Sort.by(Sort.Direction.DESC, "name"));

        //when
        Page<User> page = userRepository.findAll(pageRequest);
        List<User> content = page.getContent();
        long totalElements = page.getTotalElements();

        //then
        assertThat(content.size()).isEqualTo(5);
        assertThat(totalElements).isEqualTo(50);
        assertThat(page.getNumber()).isEqualTo(0);
        assertThat(page.getTotalPages()).isEqualTo(10);

    }

}