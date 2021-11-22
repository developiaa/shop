package pro.developia.shop.service.user.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pro.developia.shop.domain.user.MyUserDetails;
import pro.developia.shop.exception.UserNotFoundException;
import pro.developia.shop.repository.user.UserRepository;

import java.util.Collections;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) {
        return userRepository.findByEmail(email)
                .map(user -> new MyUserDetails(user, Collections.singleton(new SimpleGrantedAuthority(user.getRole().getValue()))))
                .orElseThrow(() -> new UserNotFoundException(email));
    }

}
