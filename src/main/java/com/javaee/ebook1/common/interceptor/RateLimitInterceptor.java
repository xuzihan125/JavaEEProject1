package com.javaee.ebook1.common.interceptor;

import io.github.bucket4j.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author xuizhan
 * @version 1.0
 * @description: TODO
 * @data 2021/5/21
 **/
public class RateLimitInterceptor implements HandlerInterceptor {
    private final Map<String, Bucket> buckets = new ConcurrentHashMap();

    private final Bucket freeBucket = Bucket4j.builder()
            .addLimit(Bandwidth.classic(2, Refill.intervally(15, Duration.ofSeconds(20))))
            .build();


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {

        Bucket requestBucket = this.freeBucket;

        ConsumptionProbe probe = requestBucket.tryConsumeAndReturnRemaining(1);
        return probe.isConsumed();
    }
}
