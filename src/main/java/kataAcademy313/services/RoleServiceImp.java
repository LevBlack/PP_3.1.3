package kataAcademy313.services;

import kataAcademy313.models.Role;
import kataAcademy313.repositories.RoleRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Service
public class RoleServiceImp implements RoleService {

    private final RoleRepositories roleRepositories;

    @Autowired
    public RoleServiceImp(RoleRepositories roleRepositories) {
        this.roleRepositories = roleRepositories;
    }

    @Override
    public Set<Role> getSetRole() {
        return new HashSet<>(roleRepositories.findAll());
    }

    @Override
    @Transactional
    public void addRole(Role role) {
        roleRepositories.save(role);
    }

    @Override
    public Role findById(int id) {
        return roleRepositories.findById(id).get();
    }

    @Override
    public Set<Role> roleSetById(int[] rolesID) {
        Set<Role> roleSet = new HashSet<>(rolesID.length);

        for (int i : rolesID) {
            roleSet.add(findById(i));
        }

        return roleSet;
    }
}
