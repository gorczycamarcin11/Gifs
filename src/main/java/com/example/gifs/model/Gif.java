package com.example.gifs.model;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

/**
 * Created by marcin on 14.07.17.
 */
@Table(name = "GIF")
@Entity
public class Gif extends AbstractPersistable<Long> {

    private String title;
    private String description;
    private LocalDate timestamp;
    private String imagePath;
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

    public LocalDate getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDate timestamp) {
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


