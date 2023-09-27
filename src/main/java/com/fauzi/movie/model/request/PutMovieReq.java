package com.fauzi.movie.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PutMovieReq {
    private String title;

    private String description;

    private Float rating;

    private String image;
}
