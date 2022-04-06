package com.yangk.selflearn.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

/**
 * @Description TODO
 * @Author yangkun
 * @Date 2019/8/2
 * @Version 1.0
 */
//@Configuration
public class JedisClusterConfig {

    @Value("${spring.redis.cluster.nodes}")
    private String nodes;
    @Value("${spring.redis.cluster.max-redirects}")
    private int redirects;

    @Bean
    public JedisCluster getJedisCluster() {
        String[] serverArray = nodes.split(",");
        Set<HostAndPort> nodes = new HashSet<>();

        for (String ipPort : serverArray) {
            String[] ipPortPair = ipPort.split(":");
            nodes.add(new HostAndPort(ipPortPair[0].trim(), Integer.valueOf(ipPortPair[1].trim())));

        }
        return new JedisCluster(nodes, redirects);
    }

}

