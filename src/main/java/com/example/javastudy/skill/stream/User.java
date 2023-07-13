package com.example.javastudy.skill.stream;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aspectj.bridge.IMessage;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {

    @NotNull(message = "id不能为空")
    private Integer id;

    @NotBlank(message = "name不能为空")
    private String name;

    @NotNull(message = "age不能为空")
    private Integer age;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
    
    public User merge(User user){
        if (this.id.equals(user.getId())){
            return new User(this.id, this.name, user.getAge());
        }
        return this;
    }
}
