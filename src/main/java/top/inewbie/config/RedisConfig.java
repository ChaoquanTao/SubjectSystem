package top.inewbie.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableCaching
public class RedisConfig {

    @Bean(name = "redisTemplate")
    public RedisTemplate initRedisTemplate(){
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxIdle(50);
        poolConfig.setMaxTotal(100);
        poolConfig.setMaxWaitMillis(20000);

        //jedis连接工厂
        JedisConnectionFactory connectionFactory = new JedisConnectionFactory(poolConfig) ;
        connectionFactory.setHostName("127.0.0.1");
        connectionFactory.setPort(6379);

        connectionFactory.afterPropertiesSet();

        //序列化器
        RedisSerializer jdkSerializer = new JdkSerializationRedisSerializer();
        RedisSerializer stringSerializer = new StringRedisSerializer() ;

        //RedisTemplate
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(connectionFactory);

        //设置序列化器
        redisTemplate.setDefaultSerializer(stringSerializer);
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setValueSerializer(jdkSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
        redisTemplate.setHashValueSerializer(jdkSerializer);

        return redisTemplate;

    }

    @Bean(name = "redisCacheManager")
    public CacheManager initRedisCacheManager(@Autowired RedisTemplate redisTemplate){
        RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate) ;

        //超时时间
        cacheManager.setDefaultExpiration(600);

        //缓存名称
        List<String> cacheNames = new ArrayList<String>() ;
        cacheNames.add("redisCacheManager") ;
        cacheManager.setCacheNames(cacheNames);
        return cacheManager;
    }

}
