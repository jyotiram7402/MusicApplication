package org.gfg.musicapplication.repositories;

import org.gfg.musicapplication.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Repository
public class UserCacheRepository {

    private final String USER_KEY_PREFIX = "user::";

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    public void set(User user){
        String key = getKey(user.getMail());
        redisTemplate.opsForValue().set(key,user, 10, TimeUnit.MINUTES);
    }

    public User get(String email){
        String key = getKey(email);
        return (User) redisTemplate.opsForValue().get(key);
    }

    private String getKey(String email){
        return USER_KEY_PREFIX+email;
    }
}
