package com.example.bootstrap.dao;


import com.example.bootstrap.model.Role;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Role> listRole() {
        return em.createQuery("from Role", Role.class).getResultList();
    }

    @Override
    public void addRole(Role role) {
        em.persist(role);
    }

    @Override
    public void editRole(Role roleEdit) {
        em.merge(roleEdit);
    }

    @Override
    public void removeRole(Role role) {
        em.remove(role);
    }

    @Override
    public Role getRoleById(Long id) {
        TypedQuery<Role> query = em.createQuery("select u from Role u where u.id = :id", Role.class);
        return query.setParameter("id", id).getSingleResult();
    }

    @Override
    public Role getRoleByName(String name) {
        TypedQuery<Role> query = em.createQuery("select u from Role u where u.role =:role", Role.class);
        return query.setParameter("role", name).getSingleResult();
    }

}

