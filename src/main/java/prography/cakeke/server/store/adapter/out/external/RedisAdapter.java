package prography.cakeke.server.store.adapter.out.external;

import java.time.Duration;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import prography.cakeke.server.store.application.port.out.LoadRedisPort;
import prography.cakeke.server.store.application.port.out.SaveRedisPort;

@Repository
@RequiredArgsConstructor
public class RedisAdapter implements LoadRedisPort, SaveRedisPort {
    private final RedisTemplate<String, String> redisTemplate;
    private final Integer TTL = 86400;

    @Override
    public String save(String key, String value) {
        getOperations().set(key, value, Duration.ofMillis(TTL));
        return value;
    }

    @Override
    public String getByKey(String key) {
        return getOperations().get(key);
    }

    private ValueOperations<String, String> getOperations() {
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(String.class));
        return redisTemplate.opsForValue();
    }
}
