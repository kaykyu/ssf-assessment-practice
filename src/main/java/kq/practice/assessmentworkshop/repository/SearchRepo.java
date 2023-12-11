package kq.practice.assessmentworkshop.repository;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import kq.practice.assessmentworkshop.model.Request;

@Repository
public class SearchRepo {

    @Autowired
    @Qualifier("myredis")
    RedisTemplate<String, String> template;

    @Value("${spring.redis.expiration}")
    private Long expiry;

    public void save(Request request, String result) {
        ValueOperations<String, String> vOps = template.opsForValue();
        String key = "%s, %s".formatted(request.getCategory(), request.getCode());
        vOps.set(key, result);
        template.expire(key, Duration.ofMinutes(expiry));
    }

    public Boolean exists(Request request) {

        ValueOperations<String, String> vOps = template.opsForValue();
        String key = "%s, %s".formatted(request.getCategory(), request.getCode());
        if (vOps.get(key) != null) {
            return true;
        }
        return false;
    }

    public String getNews(Request request) {

        ValueOperations<String, String> vOps = template.opsForValue();
        String key = "%s, %s".formatted(request.getCategory(), request.getCode());

        return vOps.get(key);
    }
}
