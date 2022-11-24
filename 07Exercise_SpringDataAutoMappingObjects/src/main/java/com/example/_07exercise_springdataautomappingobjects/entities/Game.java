package com.example._07exercise_springdataautomappingobjects.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "games")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(name = "trailer_id",nullable = false)
    private String trailerId;

    @Column(nullable = false, name = "image_thumbnail")
    private String imageThumbnail;

    private float size;

    @Column(nullable = false)
    private BigDecimal price;

    private String description;

    @Column(nullable = false, name = "release_date")
    private LocalDate releaseDate;

    public Game(){}

    public Game(String title, String trailerId, String imageThumbnail, float size, BigDecimal price, String description, LocalDate releaseDate) {
        this.title = title;
        this.trailerId = trailerId;
        this.imageThumbnail = imageThumbnail;
        this.size = size;
        this.price = price;
        this.description = description;
        this.releaseDate = releaseDate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTrailer() {
        return trailerId;
    }

    public void setTrailer(String trailerId) {
        this.trailerId = trailerId;
    }

    public String getImageThumbnail() {
        return imageThumbnail;
    }

    public void setImageThumbnail(String imageThumbnail) {
        this.imageThumbnail = imageThumbnail;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }
}
