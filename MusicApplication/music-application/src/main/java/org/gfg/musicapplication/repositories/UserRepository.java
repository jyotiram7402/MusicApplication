package org.gfg.musicapplication.repositories;

import org.gfg.musicapplication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    public User findByMail(String mail);
}
