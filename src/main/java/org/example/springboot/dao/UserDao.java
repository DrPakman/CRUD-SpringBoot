package org.example.springboot.dao;

import org.example.springboot.models.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    public List<User> index() {
        return entityManager.createQuery("select u from User u", User.class).getResultList();

    }

    @Transactional(readOnly = true)
    public User show(int id) {
        return entityManager.find(User.class, id);

    }

    @Transactional
    public void save(User user) {
        entityManager.persist(user);


    }

    @Transactional
    public void update(int id, User updateUser) {
        User user = entityManager.find(User.class, id);
        if (user != null) {
            user.setAge(updateUser.getAge());
            user.setUsername(updateUser.getUsername());
            user.setEmail(updateUser.getEmail());
            entityManager.merge(user);
        }

    }

    @Transactional
    public void delete(int id) {
        User user = entityManager.find(User.class, id);
        if (user != null) {
            entityManager.remove(user);
        }
    }
}



