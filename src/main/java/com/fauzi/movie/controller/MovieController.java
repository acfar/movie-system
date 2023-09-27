package com.fauzi.movie.controller;

import com.fauzi.movie.model.request.PostMovieReq;
import com.fauzi.movie.model.request.PutMovieReq;
import com.fauzi.movie.model.response.MovieResponse;
import com.fauzi.movie.service.MovieService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class MovieController {
    private MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping(value = "/v1/Movies")
    public List<MovieResponse> getAllMovie() {
        return movieService.getAllMovie();
    }

    @GetMapping(value = "/v1/Movies/{id}")
    public MovieResponse getMovieById (@PathVariable Long id) {
        return movieService.getMovieById(id);
    }

    @PostMapping(value = "/v1/Movies")
    public MovieResponse createMovie (@RequestBody @Valid PostMovieReq postMovieReq) {
        return movieService.createMovie(postMovieReq);
    }

    @PatchMapping(value = "/v1/Movies/{id}")
    public MovieResponse updateMovie (@RequestBody @Valid PutMovieReq putMovieReq, @PathVariable Long id) {
        return movieService.updateMovie(putMovieReq, id);
    }

    @DeleteMapping(value = "/v1/Movies/{id}")
    public void deleteMovie (@PathVariable Long id) {
        movieService.deleteMovie( id);
    }
}
