package com.kyle.Schedule;

import com.kyle.domain.User;
import com.kyle.mapper.UsersRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Component
@Configuration
@EnableScheduling    //计时器注入,使@Schedule注解可用
public class AddUserTickets {
    @Resource
    private UsersRepository usersRepository;
    @Scheduled(cron = " 0 0 0 1 * ?")//每月一号执行一次
//@Scheduled(cron="0/10 * *  * * ? ")//每10执行一次
public void addUserTicket(){
//    System.out.println("每10秒执行一次"+new Date());
        List<User> all = usersRepository.findAll();
        for (User u:all){
            if (u.getUvip()!=0){
                u.setUticket(5);
                usersRepository.saveAndFlush(u);
            }
        }
    }
}
