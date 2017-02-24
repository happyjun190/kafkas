package com.kafkas.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wushenjun on 17-1-17.
 * redis test
 */
public class RedisTest {
    private static final ObjectMapper jsonMapper = new ObjectMapper();
    private static final String USER_STORE_REDIS_CAR = "user_store_redis_car:";


    public static void main(String args[]) throws IOException {
        Jedis jedis = new Jedis("localhost", 6379);
        RedisCarDTO redisCarDTO1 = new RedisCarDTO("1",2,3,9);
        RedisCarDTO redisCarDTO2 = new RedisCarDTO("2",2,3,9);
        RedisCarDTO redisCarDTO3 = new RedisCarDTO("3",2,3,9);
        RedisCarDTO redisCarDTO4 = new RedisCarDTO("4",2,3,9);

        Map<String, String> redisCarMapInfo = new HashMap<>();
        redisCarMapInfo.put(redisCarDTO1.getCartId(), redisCarDTO1.toString());
        redisCarMapInfo.put(redisCarDTO2.getCartId(), redisCarDTO2.toString());
        redisCarMapInfo.put(redisCarDTO3.getCartId(), redisCarDTO3.toString());
        redisCarMapInfo.put(redisCarDTO4.getCartId(), redisCarDTO4.toString());

        String setKey = USER_STORE_REDIS_CAR;
        String hsetKey = USER_STORE_REDIS_CAR+5+"_"+ 4;
        //jedis.hmset(hsetKey, redisCarMapInfo);

        jedis.set("hello","hello world");
        System.out.println(jedis.get("hello"));

        jedis.hmset(hsetKey, redisCarMapInfo);

        //jedis.hdel(hsetKey, redisCarDTO1.getCartId(), redisCarDTO2.getCartId(),redisCarDTO3.getCartId(),redisCarDTO4.getCartId());
        /*jedis.hset(hsetKey, redisCarDTO1.getCartId(), redisCarDTO1.toString());
        jedis.hset(hsetKey, redisCarDTO2.getCartId(), redisCarDTO2.toString());
        jedis.hset(hsetKey, redisCarDTO3.getCartId(), redisCarDTO3.toString());
        jedis.hset(hsetKey, redisCarDTO4.getCartId(), redisCarDTO4.toString());
        jedis.hset(hsetKey, redisCarDTO3.getCartId(), redisCarDTO3.toString());*/

        List<String> value5 = jedis.hmget(hsetKey, "4");

        for(String value:value5) {
            System.out.println(value);
        }

        Map<String, String> maps = jedis.hgetAll(hsetKey);
        for(String key:maps.keySet()) {
            String value = maps.get(key);
            RedisCarDTO redisCarDTO = jsonMapper.readValue(value, RedisCarDTO.class);
            System.out.println(key+": "+redisCarDTO.toString());
        }
        /*List<String> aaaa = jedis.hmget(hsetKey);
        for (String aa:aaaa) {
            System.out.println(aa);
        }*/
        //List<String> aaaa = jedis.hmget(hmsetKey,"1");
        //aaaa.get(0);

        //Map<String, String> getRedisCarMapInfo = (Map<String, String>) jedis.hmget(hmsetKey);

        //String carinfo1 = getRedisCarMapInfo.get("1");
    }
}
