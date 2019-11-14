package com.kyle.mapper;

import com.kyle.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<User,Integer>{
   User findByUname(String name);
}
