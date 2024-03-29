package com.binoofactory.alma.almabe.common.service.impl;

import java.time.Duration;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.binoofactory.alma.almabe.common.service.RedisCacheService;

@SuppressWarnings("rawtypes")
@Repository
public class RedisCacheServiceImpl implements RedisCacheService {
	private final RedisTemplate redisTemplate;

	@Autowired
	public RedisCacheServiceImpl(RedisTemplate redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setValue(String key, Object value, long secondes) {
		redisTemplate.opsForValue().set(key, value, Duration.ofSeconds(secondes).toMillis());
	}

	@Override
	public String getValue(String key) {
		Object data = redisTemplate.opsForValue().get(key);
		return Objects.nonNull(data) ? data.toString() : null;
	}
}
