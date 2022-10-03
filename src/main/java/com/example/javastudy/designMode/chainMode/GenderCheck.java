package com.example.javastudy.designMode.chainMode;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class GenderCheck extends CheckUser{
    @Override
    protected List<User> checkUserAns(List<User> user) {
        List<User> stGenderAdopt = user.stream().filter(stu -> {
            String gender = stu.getGender();
            return Objects.isNull(gender) || !("男".equals(gender) || "女".equals(gender));
        }).collect(Collectors.toList());

        user.remove(stGenderAdopt);
        return user;
    }
}
