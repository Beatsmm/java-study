package com.example.javastudy.skill.stream;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {

    private Integer id;

    private String name;

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
