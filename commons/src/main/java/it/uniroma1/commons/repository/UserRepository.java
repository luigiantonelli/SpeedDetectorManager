package it.uniroma1.commons.repository;

import it.uniroma1.commons.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
