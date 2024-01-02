package org.gfg.musicapplication.repositories;


import org.gfg.musicapplication.model.Songs;
import org.gfg.musicapplication.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Repository
public class SongCacheRepository {

    private final String SONG_KEY_PREFIX = "song::";
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    public void set(Songs songs){
        String key = getKey(songs.getSongTitle());
        redisTemplate.opsForValue().set(key,songs, 10, TimeUnit.MINUTES);
    }

    public Songs get(String title){
        String key = getKey(title);
        return (Songs) redisTemplate.opsForValue().get(key);
    }

    private String getKey(String title){
        return SONG_KEY_PREFIX+title;
    }
}

