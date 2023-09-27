package com.fauzi.movie.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieResponse {
    private Long id;

    private String title;

    private String description;

    private Float rating;

    private String image;

    private String createdAt;

    private String updatedAt;
}
