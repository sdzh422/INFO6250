package com.me.tracking.identifying;

import com.me.tracking.dao.RedisProvider;
import com.mysql.cj.xdevapi.Result;

import redis.clients.jedis.Jedis;

public class EZgenerator {
	public static String getRandom(int length) {
        String num = "";
        for (int i = 0 ; i < length ; i ++) {
            num = num + String.valueOf((int) Math.floor(Math.random() * 9 + 1));
        }
        return num;
	}
	public static String generate_reset(String username)
	{
		String Result =null;
		Jedis jedis = RedisProvider.getJedis();
		boolean isExist = jedis.exists(username);
		if(isExist)
			Result = jedis.get(username);
		else {
			Result = EZgenerator.getRandom(4);
			jedis.psetex(username, 60*1000, Result); //ms
		}
		return Result;
	}
}
