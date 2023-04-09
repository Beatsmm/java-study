package com.demos.service;


import com.demos.entity.Users;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

import static org.springframework.data.domain.Sort.Direction.DESC;

@Service
public class UserService {

    private MongoTemplate mongoTemplate;

    public int insertUser(Users user){
        mongoTemplate.insert(user);
        return 1;
    }

    public int updateUser(Users user){
        // 通过query查询到对应对象
        Query query = new Query(Criteria.where("_id").is(user.getId()));
        Update update = new Update().set("name", user.getName());
        mongoTemplate.updateFirst(query, update, Users.class);
        return 1;
    }

    public int removeUser(Long id){
        Query query = new Query(Criteria.where("_id").is(id));
        mongoTemplate.remove(query, Users.class);
        return 1;
    }

    public Users findById(Users users){
        Query query = new Query(Criteria.where("_id").is(users.getId()));
        return mongoTemplate.findOne(query, Users.class);
    }

    public List<Users> findLike(Users users){
        Pattern pattern = Pattern.compile("^.*" + users.getName().trim() + ".*$", Pattern.CASE_INSENSITIVE);
        Query query = new Query(Criteria.where("name").regex(pattern));
        return mongoTemplate.find(query, Users.class);
    }

    public List<Users> findMore(Users users){
        Query query = new Query(Criteria.where("name").is(users.getName()));
        return mongoTemplate.find(query, Users.class);
    }

    public List<Users> findAge(Users users){
        Query query = new Query();
        Sort sort = Sort.by(DESC, "age");
        query.with(sort);
        return mongoTemplate.find(query, Users.class);
    }
}
