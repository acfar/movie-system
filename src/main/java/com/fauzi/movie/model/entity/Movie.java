package com.fauzi.movie.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="movie")
public class Movie {
    @Id
    @Column(name="id", columnDefinition = "serial")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "rating")
    private Float rating;

    @Column(name = "image")
    private String image;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;
}
