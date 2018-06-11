package com.ssw;

import com.ssw.domain.User;
import com.ssw.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository repository;

    @Test
    public void findByNameTest() {
        this.entityManager.persist(new User("seungwoo", "seungwoo@test.com", "123123"));
        User user = this.repository.findByUserName("seungwoo");
        assertThat(user.getUserName()).isEqualTo("seungwoo");
        assertThat(user.getEmail()).isEqualTo("seungwoo@test.com");
        assertThat(user.getPassword()).isEqualTo("123123");
        assertThat(user.getUserId()).isEqualTo(1);
        List<User> userList = this.repository.findAll();
        userList.forEach(System.out::println);
    }
}
