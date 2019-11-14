package com.kyle.contorller;

import com.kyle.mapper.AuthorRepository;
import com.kyle.domain.Author;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class AuthorController {
    @Resource
    private AuthorRepository authorRepository;

    @RequestMapping("/findAuthorAll")
    public List<Author> findAuthorAll(){
        List<Author> all = authorRepository.findAll();
        return all;
    }
}
