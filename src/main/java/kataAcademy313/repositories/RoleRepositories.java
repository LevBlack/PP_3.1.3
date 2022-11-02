package kataAcademy313.repositories;

import kataAcademy313.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepositories extends JpaRepository<Role, Integer> {
}
