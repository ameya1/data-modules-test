package org.test.model.params;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@ToString
@AllArgsConstructor
@Getter
@Setter
@Configuration
@NoArgsConstructor
@EnableConfigurationProperties
@ConfigurationProperties("redis")
public class RedisParams {
    private String url;
    private Integer port;
}
