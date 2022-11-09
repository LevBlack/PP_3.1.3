package kataAcademy313.services;


import kataAcademy313.models.Role;

import java.util.Set;

public interface RoleService {
    Set<Role> getSetRole();
    void addRole(Role role);
    Role findById(int id);
}
