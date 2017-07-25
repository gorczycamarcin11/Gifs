package com.example.gifs.model;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by marcin on 14.07.17.
 */
@Table(name = "GIF")
@Entity
public class Gif extends AbstractPersistable<Long> {

    @Column(name = "TITLE", nullable = false)
    @NotEmpty(message = "This field cannot be empty")
    @NotNull
    private String title;

    @Column(name = "DESCRIPTION", nullable = false)
    @NotEmpty(message = "This field cannot be empty")
    @NotNull
    private String description;

    @Column(name = "TIMESTAMP", nullable = false)
    private LocalDateTime timestamp;

    @Column(name = "IMAGE", columnDefinition = "BYTEA")
    private byte[] image;

    @Column(name = "VISIT_COUNT")
    private int visitCount;

    @OneToMany(mappedBy = "gif")
    private List<Comment> commentList;

    public Gif() {
    }

    @Override
    public Long getId() {
        return super.getId();
    }

    @Override
    public void setId(Long id) {
        super.setId(id);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public int getVisitCount() {
        return visitCount;
    }

    public void setVisitCount(int visitCount) {
        this.visitCount = visitCount;
    }

    public void increaseCount() {
        ++this.visitCount;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Gif gif = (Gif) o;

        return title != null ? title.equals(gif.title) : gif.title == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (title != null ? title.hashCode() : 0);
        return result;
    }

    @PrePersist
    public void onPersist() {
        this.setTimestamp(LocalDateTime.now());
    }


    @Override
    public String toString() {
        return "Gif: " +
                "id = " + getId() +
                ", title = " + title +
                ", description = " + description +
                ", timestamp = " + timestamp +
                ", image = " + image +
                ", visitCount = " + visitCount;
    }
}


