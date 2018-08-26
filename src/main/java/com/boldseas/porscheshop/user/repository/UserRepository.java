package com.boldseas.porscheshop.user.repository;

import com.boldseas.porscheshop.user.beans.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author fei.ye
 * @version 2018/5/9.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * 根据用户Id查询用户
     * @param id
     * @return
     */
    User findById(Long id);

    /**
     * 根据手机号查找用户信息
     * @param phone
     * @return
     */
    User findByPhone(String phone);

    /**
     * 获取某一时点之前的用户
     * @param dateTime
     * @return
     */
    List<User> findByCreateDateBeforeAndValidTrue(LocalDateTime dateTime);
}
