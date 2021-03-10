package com.zk.jedis;

import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JedisTestor {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1", 6379);

        try {
            jedis.auth("12345");
            jedis.select(2);
            System.out.println("Redis连接成功");
            // 字符串
            jedis.set("name", "Jack");
            String name = jedis.get("name");
            System.out.println(name);
            jedis.mset(new String[]{"title", "xx", "num", "20"});
            List<String> list = jedis.mget(new String[]{"title", "num", "name"});
            System.out.println(list);
            Long num = jedis.incr("num");
            System.out.println(num);
            // Hash
            jedis.hset("student:3312", "name", "rose");
            String name1 = jedis.hget("student:3312", "name");
            System.out.println(name1);
            Map<String, String> studentMap = new HashMap<>();
            studentMap.put("name", "james");
            studentMap.put("age", "18");
            studentMap.put("id", "3313");
            jedis.hmset("student:3313", studentMap);
            Map<String, String> resultMap = jedis.hgetAll("student:3313");
            System.out.println(resultMap);
            // List
            jedis.del("letter");
            jedis.rpush("letter", new String[]{"d", "e", "f"});
            jedis.lpush("letter", new String[]{"c", "b", "a"});
            jedis.lpop("letter");
            jedis.rpop("letter");
            List<String> letter = jedis.lrange("letter", 0, -1);
            System.out.println(letter);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }

    }
}
