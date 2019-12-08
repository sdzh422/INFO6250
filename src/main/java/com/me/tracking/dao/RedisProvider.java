package com.me.tracking.dao;

import java.util.ResourceBundle;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.exceptions.JedisConnectionException;

public class RedisProvider {
	protected static JedisPool jedispool;
	/*static{
        ResourceBundle bundle = ResourceBundle.getBundle("redis");  //¶ÁÈ¡redis.propertiesÎÄ¼þ
        if (bundle == null) {
            throw new IllegalArgumentException(
                    "[redis.properties] is not found!");
        }
        try {
            JedisPoolConfig jedisconfig = new JedisPoolConfig();
            jedisconfig.setMaxIdle(Integer.valueOf(bundle
                    .getString("redis.pool.maxIdle")));
            jedisconfig.setTestOnBorrow(Boolean.valueOf(bundle
                    .getString("redis.pool.testOnBorrow")));
            jedisconfig.setTestOnReturn(Boolean.valueOf(bundle
                    .getString("redis.pool.testOnReturn")));
            jedispool = new JedisPool(jedisconfig, bundle.getString("redis.ip"),
                    Integer.valueOf(bundle.getString("redis.port")),
                    Integer.valueOf(bundle.getString("redis.timeout")),
                    "",//test password = null
                    Integer.valueOf(bundle.getString("redis.database")));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }*/
  /*  private JedisPoolConfig getPoolConfig(){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(10);
        jedisPoolConfig.setMaxTotal(50);
        jedisPoolConfig.setTestOnBorrow(true);
        jedisPoolConfig.setTestOnReturn(true);
        return jedisPoolConfig;
    }*/
    
	public static Jedis getJedis() {
        Jedis jedis = null;
        try {
            jedis = new Jedis();
        } catch (JedisConnectionException jce) {
            jce.printStackTrace();
        }
        return jedis;
    }

}
