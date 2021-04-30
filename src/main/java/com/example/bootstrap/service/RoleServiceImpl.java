package com.example.bootstrap.service;


import com.example.bootstrap.dao.RoleDao;
import com.example.bootstrap.model.Role;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    private final RoleDao roleDao;

    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    @Transactional
    public List<Role> listRole() {
        return roleDao.listRole();
    }

    @Override
    @Transactional
    public void addRole(Role role) {
        roleDao.addRole(role);
    }

    @Override
    @Transactional
    public void editRole(Role role) {
        roleDao.editRole(role);
    }

    @Override
    @Transactional
    public void removeRole(Role role) {
        roleDao.removeRole(role);
    }

    @Override
    @Transactional
    public Role getRoleById(Long id) {
        return roleDao.getRoleById(id);
    }

    @Override
    @Transactional
    public Role getRoleByName(String name) {
        return roleDao.getRoleByName(name);
    }

}
