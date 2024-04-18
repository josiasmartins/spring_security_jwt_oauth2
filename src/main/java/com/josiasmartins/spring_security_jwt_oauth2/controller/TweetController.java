package com.josiasmartins.spring_security_jwt_oauth2.controller;

import com.josiasmartins.spring_security_jwt_oauth2.controller.dto.CreateTweetDTO;
import com.josiasmartins.spring_security_jwt_oauth2.controller.dto.FeedDTO;
import com.josiasmartins.spring_security_jwt_oauth2.controller.dto.FeedItemDTO;
import com.josiasmartins.spring_security_jwt_oauth2.entities.Role;
import com.josiasmartins.spring_security_jwt_oauth2.entities.Tweet;
import com.josiasmartins.spring_security_jwt_oauth2.repository.TweetRepository;
import com.josiasmartins.spring_security_jwt_oauth2.repository.UserRepository;
import org.apache.catalina.connector.Response;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@RestController
public class TweetController {

    private final TweetRepository tweetRepository;
    private final UserRepository userRepository;

    public TweetController(TweetRepository tweetRepository, UserRepository userRepository) {
        this.tweetRepository = tweetRepository;
        this.userRepository = userRepository;
    }

    @PostMapping("/tweets")
    public ResponseEntity<Void> createTweet(
            @RequestBody CreateTweetDTO dto,
            JwtAuthenticationToken token) {
        var user = userRepository.findById(UUID.fromString(token.getName()));

        var tweet = new Tweet();
        tweet.setUser(user.get());
        tweet.setContent(dto.content());

        tweetRepository.save(tweet);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/feed")
    public ResponseEntity<FeedDTO> feed(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize
    ) {
        var tweets = this.tweetRepository.findAll(
                PageRequest.of(page, pageSize, Sort.Direction.DESC, "createTimestamp"))
                .map(tweet -> new FeedItemDTO(
                        tweet.getTweetId(),
                        tweet.getContent(),
                        tweet.getUser().getUsername()
                ));

        return ResponseEntity.ok(new FeedDTO(
                tweets.getContent(),
                page,
                pageSize,
                tweets.getTotalPages(),
                tweets.getTotalElements())
        );

    }

    @DeleteMapping("/tweets/{id}")
    public ResponseEntity<Void> deleteTweet(
            @PathVariable("id") Long tweetId,
            JwtAuthenticationToken token) {

        var user = userRepository.findById(UUID.fromString(token.getName()));
        var tweet = tweetRepository.findById(tweetId)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        var isAdmin = user.get().getRoles()
                .stream()
                .anyMatch(role -> role.getName().equalsIgnoreCase(Role.Values.ADMIN.name()));

        if (isAdmin || tweet.getUser().getUserId().equals(UUID.fromString(token.getName()))) {
            tweetRepository.deleteById(tweetId);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        return ResponseEntity.ok().build();
    }

}
