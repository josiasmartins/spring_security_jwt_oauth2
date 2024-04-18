package com.josiasmartins.spring_security_jwt_oauth2.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Entity
@Table(name = "tb_tweets")
@Data
public class Tweet {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
//    @Column(name = "tweet_id")
    private Long tweetId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String content;

    @CreationTimestamp
    private Instant createTimestamp;

}
