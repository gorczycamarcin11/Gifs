package com.example.gifs.model;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.jpa.domain.AbstractPersistable;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * Created by marcin on 18.07.17.
 */
@Table(name = "COMMENT")
@Entity
public class Comment extends AbstractPersistable<Long> {

    @NotNull
    @NotEmpty(message = "This field cannot be empty")
    @Column(name = "NICKNAME", nullable = false)
    private String nickname;

    @NotNull
    @NotEmpty(message = "This field cannot be empty")
    @Column(name = "TEXT", nullable = false)
    private String text;

    @Column(name = "TIMESTAMP", nullable = false)
    private LocalDateTime timestamp;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "GIF_ID", referencedColumnName = "ID")
    private Gif gif;

    public Comment() {
    }

    @PrePersist
    public void onPersist() {
        this.setTimestamp(LocalDateTime.now());
    }

    @Override
    public Long getId(){
        return super.getId();
    }

    @Override
    public void setId(Long id){
        super.setId(id);
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Gif getGif() {
        return gif;
    }

    public void setGif(Gif gif) {
        this.gif = gif;
    }


}
