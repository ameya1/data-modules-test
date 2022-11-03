package org.test.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.hibernate.Session;
import org.test.model.entity.User;
import redis.clients.jedis.JedisPooled;
import redis.clients.jedis.json.Path;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/data")
@Log4j2
public class DataController {

    @Autowired
    Session session;

    @Autowired
    JedisPooled jedisClient;

    @PostMapping("/user")
    public ResponseEntity<String> saveSQL() {
        try {
            User user = user();
            session.save(user);
            jedisClient.jsonSet("user:" + user.getUserId(), new Path("$"), user);
            return ResponseEntity.ok("Saved " + user);
        } catch (Exception e) {
            log.error("Exception e : " + e.getMessage(), e);
            return ResponseEntity.ok("Could not save user");
        }
    }

    public static User user() {
        return User.builder()
                .userId(UUID.randomUUID())
                .username("sully-" + UUID.randomUUID())
                .createdAt(LocalDateTime.now())
                .email("sully@mail.com " + UUID.randomUUID())
                .firstName("Sully")
                .lastName("Drake")
                .build();
    }


}
