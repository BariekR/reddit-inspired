package com.greenfox.reddit.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

@Entity
@Getter
@Setter
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    @Lob
    @Column(length=1000)
    private String description;
    private String imgUrl;
    private int likes;
    private Timestamp creationDate;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public Post() {
        likes = 0;
        creationDate = new Timestamp(System.currentTimeMillis());
    }

    public void incrementLikes() {
        likes++;
    }

    public void decrementLikes() {
        likes--;
    }

    public String getDateTime() {
        return new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(creationDate);
    }
}
