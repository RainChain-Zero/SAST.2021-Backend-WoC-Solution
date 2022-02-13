package com.example.woc.entity;

import com.example.woc.component.ValidGroup;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * @author: 風楪fy
 * @create: 2022-01-15 03:54
 **/

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Account {
    @Id
    @NotBlank(groups = ValidGroup.usernameCheck.class)
    private String username;

    @NotBlank(groups = ValidGroup.usernameCheck.class)
    private String password;

    @Email(groups = ValidGroup.emailCheck.class)
    private String email;

    private Integer role = 0;   //role默认为0
}
