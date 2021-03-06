package ru.internship.ballot.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.internship.ballot.model.User;
import ru.internship.ballot.repository.UserRepository;
import ru.internship.ballot.service.UserService;
import ru.internship.ballot.util.exception.NotFoundException;

import static org.junit.jupiter.api.Assertions.*;
import static ru.internship.ballot.UserTestData.*;

@SpringJUnitConfig(locations = {
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
class UserServiceImplTest {

    @Autowired
    private UserService service;
    // gives additional functionality for testing
    @Autowired
    private UserRepository repository;

    @Test
    void testCreate() {
        User created = getCreated();
        service.create(created);
        assertMatch(repository.findAll(), USER1, USER2, ADMIN, created);
    }

    @Test
    void testDuplicate() {
        User duplicate = getDuplicate();
        assertThrows(DataAccessException.class, () ->
                service.create(duplicate));
    }

    @Test
    void testGetByEmail() {
        User user = service.getByEmail(EMAIL_USER1);
        assertMatch(user, USER1);
    }

    @Test
    void testGetByEmailNotFound() {
        assertThrows(NotFoundException.class, () ->
                service.getByEmail("qwerty@microsoft.com"));
    }
}