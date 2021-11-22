package pro.developia.shop.repository.user.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import pro.developia.shop.dto.user.UserDataDto;
import pro.developia.shop.repository.user.UserCustomRepository;

import javax.persistence.EntityManager;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class UserCustomRepositoryImpl implements UserCustomRepository {

    private final EntityManager em;

    @Override
    public Page<UserDataDto> findAll(String email, String name, Pageable pageable) {
        String sql = "select u.user_id, u.email, u.gender, u.name, u.nick_name, u.phone" +
                ", c.order_id, c.count, c.order_date, c.order_number, c.order_price, c.product_name" +
                " from user as u join " +
                "(select a.order_id, a.count, a.order_date, a.order_number, a.order_price, a.product_name, a.user_id" +
                " from idus.orders as a join " +
                "(select Max(o.order_id) as order_id " +
                "from idus.orders as o group by o.user_id) as b " +
                "on a.order_id = b.order_id " +
                ") as c on u.user_id = c.user_id";

        log.info("email -> {}, name -> {}", email, name);
        List<Object[]> resultList = null;
        if (!email.equals("") && name.equals("")) {
            sql += " where email = :email";
            resultList = em.createNativeQuery(sql).setParameter("email", email).getResultList();
        }

        if (email.equals("") && !name.equals("")) {
            sql += " where name = :name";
            resultList = em.createNativeQuery(sql).setParameter("name", name).getResultList();
        }

        if (!email.equals("") && !name.equals("")) {
            sql += " where email= :email and name = :name";
            resultList = em.createNativeQuery(sql)
                    .setParameter("email", email)
                    .setParameter("name", name).getResultList();
        }
        if (email.equals("") && name.equals("")) {
            resultList = em.createNativeQuery(sql).getResultList();
        }

        List<UserDataDto> userList = new ArrayList<>();

        for (Object[] objects : resultList) {
            UserDataDto userDataDto = new UserDataDto();
            userDataDto.setUserId(objects[0]);
            userDataDto.setEmail((String) objects[1]);
            userDataDto.setGender((String) objects[2]);
            userDataDto.setName((String) objects[3]);
            userDataDto.setNickName((String) objects[4]);
            userDataDto.setPhone((String) objects[5]);
            userDataDto.setOrderId(objects[6]);
            userDataDto.setCount((Integer) objects[7]);
            userDataDto.setOrderDate((Timestamp) objects[8]);
            userDataDto.setOrderNumber((String) objects[9]);
            userDataDto.setOrderPrice((Integer) objects[10]);
            userDataDto.setProductName((String) objects[11]);
            userList.add(userDataDto);
        }

        return new PageImpl<>(userList);
    }


    public List findAllByRecentOrder(Long id) {
        return em.createQuery("select o from Order o where o.id=:id")
                .setParameter("id", id)
                .getResultList();
    }


}
