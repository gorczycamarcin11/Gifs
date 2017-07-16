package com.example.gifs.model;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import java.time.LocalDateTime;

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

    @Column(name = "IMAGE_PATH", unique = true, nullable = false)
    private String imagePath;

    @Column(name = "VISIT_COUNT")
    private int visitCount;

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

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
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
                ", imagePath = " + imagePath +
                ", visitCount = " + visitCount;
    }
}


