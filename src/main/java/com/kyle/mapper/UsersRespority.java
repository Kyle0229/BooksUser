package com.kyle.mapper;

import com.kyle.pojo.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRespority extends JpaRepository<Users,Integer>{
   Users findByUname(String name);
}
