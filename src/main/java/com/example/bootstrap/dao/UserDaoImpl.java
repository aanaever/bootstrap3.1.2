package com.example.bootstrap.dao;


import com.example.bootstrap.model.Role;
import com.example.bootstrap.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<User> listUser() {
        return em.createQuery("select u from User u join fetch u.roles").getResultList();
    }

    @Override
    public void addUser(User user) {
        em.persist(user);
    }

    @Override
    public void editUser(User userEdit) {
        em.merge(userEdit);
    }

    @Override
    public void removeUser(Long id) {
        User user = em.find(User.class, id);
        em.remove(user);
    }

    @Override
    public User getUserById(Long id) {
//        return em.find(User.class, id);
        return em.createQuery("select a from User a join fetch a.roles where a.id =:id", User.class).setParameter("id", id).getSingleResult();
    }

    @Override
    public User getUserByName(String name) {
        return em.createQuery("select a from User a join fetch a.roles where a.name =:name", User.class).setParameter("name", name).getSingleResult();
    }

    @Override
    public Role getRoleByName(String role) {
        return em.createQuery("select a from Role a where a.role =:role", Role.class).setParameter("role", role).getSingleResult();
    }

    @Override
    public User getUserByEmail(String email) {
        return em.createQuery("select a from User a join fetch a.roles where a.email =:email", User.class).setParameter("email", email).getSingleResult();
    }

}
