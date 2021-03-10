package com.zk.jedis;

import com.alibaba.fastjson.JSON;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CacheSample {
    public CacheSample() {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        try {
            List<Goods> goodsList = new ArrayList<>();
            goodsList.add(new Goods(8818, "红富士苹果1", "", 3.1f));
            goodsList.add(new Goods(8819, "红富士苹果2", "", 3.2f));
            goodsList.add(new Goods(8820, "红富士苹果3", "", 3.3f));
            jedis.auth("12345");
            jedis.select(3);
            for (Goods goods : goodsList) {
                String jsonString = JSON.toJSONString(goods);
                System.out.println(jsonString);
                String key = "goods:" + goods.getGoodsId();
                jedis.set(key, jsonString);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }
    }

    public static void main(String[] args) {
        System.out.println("请输入要查询的商品编号:");
        String goodsId = new Scanner(System.in).next();
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        try {
            jedis.auth("12345");
            jedis.select(3);
            String key = "goods:" + goodsId;
            if (jedis.exists(key)) {
                String json = jedis.get(key);
                System.out.println(json);
                Goods goods = JSON.parseObject(json, Goods.class);
                System.out.println(goods.getGoodsName());
            } else {
                System.out.println("你输入的商品编号不存在, 请重新输入");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }
    }
}
