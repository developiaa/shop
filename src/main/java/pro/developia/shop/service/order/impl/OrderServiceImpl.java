package pro.developia.shop.service.order.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pro.developia.shop.domain.user.User;
import pro.developia.shop.repository.order.OrderRepository;
import pro.developia.shop.repository.user.UserRepository;
import pro.developia.shop.service.order.OrderService;

@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;


}
