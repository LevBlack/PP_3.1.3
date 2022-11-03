package kataAcademy313.services;

import kataAcademy313.models.Role;
import kataAcademy313.repositories.RoleRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Service
public class RoleServiceImp implements RoleService{

    private final RoleRepositories roleRepositories;

    @Autowired
    public RoleServiceImp(RoleRepositories roleRepositories) {
        this.roleRepositories = roleRepositories;
    }

    @Override
    @Transactional
    public Set<Role> getSetRole() {
        return new HashSet<>(roleRepositories.findAll());
    }
    @Override
    @Transactional
    public void addRole(Role role){
        roleRepositories.save(role);
    }
}
