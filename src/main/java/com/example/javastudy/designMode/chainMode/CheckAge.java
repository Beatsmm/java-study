package com.example.javastudy.designMode.chainMode;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CheckAge extends CheckUser{

    @Override
    protected List<User> checkUserAns(List<User> user) {
        List<User> userAdopt = user.stream().filter(u -> {
            Integer age = u.getAge();
            return Objects.isNull(age) || age <= 0 || age >= 60;
        }).collect(Collectors.toList());

        user.removeAll(userAdopt);
        return user;
    }
}
