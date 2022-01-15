package com.example.woc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author: 風楪fy
 * @create: 2022-01-15 03:54
 **/
//Lombok的注解
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Account {
    private Integer id;
    @Id
    private String username;
    private String password;
    private String email;
}
