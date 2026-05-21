package com.kna.async;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.concurrent.CompletableFuture;

@Service
public class GitHubLookUpService {

    private final static Logger log = LoggerFactory.getLogger(GitHubLookUpService.class);

    private final RestClient restClient;

    public GitHubLookUpService(RestClient.Builder restClientBuilder){
        this.restClient = restClientBuilder
                .baseUrl("https://api.github.com")
                .build();
    }

    @Async
    public CompletableFuture<User> findUser (String username) throws InterruptedException{
        log.info("Looking up {}", username);

        User rs = restClient.get()
                .uri("/users/{username}", username)
                .retrieve()
                .body(User.class);
        Thread.sleep(1000L);

        return CompletableFuture.completedFuture(rs);
    }


}

