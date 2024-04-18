package com.josiasmartins.spring_security_jwt_oauth2.repository;

import com.josiasmartins.spring_security_jwt_oauth2.entities.Role;
import com.josiasmartins.spring_security_jwt_oauth2.entities.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TweetRepository extends JpaRepository<Tweet, Long> {
}
