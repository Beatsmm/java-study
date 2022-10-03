package com.example.javastudy.designMode.chainMode;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CheckName extends CheckUser{
    @Override
    protected List<User> checkUserAns(List<User> user) {
        List<User> nameNotAdopt = user.stream().filter(u -> {
            String name = u.getName();
            return Objects.isNull(name) || "".equals(name);
        }).collect(Collectors.toList());
        user.removeAll(nameNotAdopt);
        return user;
    }
}
