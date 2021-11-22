package pro.developia.shop.service.user.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pro.developia.shop.domain.user.User;
import pro.developia.shop.domain.user.UserRole;
import pro.developia.shop.dto.user.UserDataDto;
import pro.developia.shop.dto.user.UserDto;
import pro.developia.shop.dto.user.UserSignUpDTO;
import pro.developia.shop.repository.user.UserCustomRepository;
import pro.developia.shop.repository.user.UserRepository;
import pro.developia.shop.service.user.UserService;


@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserCustomRepository userCustomRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public User signUp(UserSignUpDTO userSignUpDTO) {
        User user = User.builder()
                .name(userSignUpDTO.getName())
                .nickName(userSignUpDTO.getNickName())
                .phone(userSignUpDTO.getPhone())
                .email(userSignUpDTO.getEmail())
                .pw(passwordEncoder.encode(userSignUpDTO.getPassword()))
                .gender(userSignUpDTO.getGender())
                .role(UserRole.ROLE_USER)
                .build();
        return userRepository.save(user);
    }

    @Override
    public UserDto getUserInfo(Long userId) {
        User user = userRepository.findById(userId).get();
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .gender(user.getGender()).build();
    }

    @Override
    public Page<UserDataDto> findAll(String email, String name, Pageable pageable) {
        return userCustomRepository.findAll(email, name, pageable);
    }


    @Override
    public boolean isEmailDuplicated(String email) {
        log.info("isEmailDuplicated -> {}", email);
        return userRepository.existsByEmail(email);
    }
}
