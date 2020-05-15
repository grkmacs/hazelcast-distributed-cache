package com.hazelcastdistributedcache.controller;

import com.hazelcast.core.HazelcastInstance;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(value = "/hazelcast")
public class HazelcastController {

    private final HazelcastInstance hazelcastInstance;

    public HazelcastController(@Qualifier("hazelcastInstance") HazelcastInstance hazelcastInstance) {
        this.hazelcastInstance = hazelcastInstance;
    }

    @PostMapping
    public void addHazelcastMap(@RequestParam("key") String key, @RequestParam("value") String value) {
        Map<String, String> hazelcastMap = hazelcastInstance.getMap("hazelcastMap");
        hazelcastMap.put(key, value);
    }

    @GetMapping()
    public String getHazelcastMap(@RequestParam("key") String key) {
        Map<String, String> hazelcastMap = hazelcastInstance.getMap("hazelcastMap");
        return "Hazelcast values is :" + hazelcastMap.get(key);
    }

    @DeleteMapping
    public void removeHazelcastMap(@RequestParam("key") String key) {
        Map<String, String> hazelcastMap = hazelcastInstance.getMap("hazelcastMap");
        hazelcastMap.remove(key);
    }
}
