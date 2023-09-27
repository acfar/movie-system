package com.fauzi.movie.service;

import com.fauzi.movie.config.exception.BusinessException;
import com.fauzi.movie.model.entity.Movie;
import com.fauzi.movie.model.request.PostMovieReq;
import com.fauzi.movie.model.request.PutMovieReq;
import com.fauzi.movie.model.response.MovieResponse;
import com.fauzi.movie.repository.MovieRepository;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Log4j2
public class MovieService {
    private MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<MovieResponse> getAllMovie(){
        List<Movie> movies = movieRepository.findAll();
        List<MovieResponse> movieResponses = new ArrayList<>();
        for (Movie movie : movies){
            MovieResponse data = MovieResponse.builder()
                    .id(movie.getId())
                    .title(movie.getTitle())
                    .description(movie.getDescription())
                    .rating(movie.getRating())
                    .image(movie.getImage())
                    .createdAt(timeStampToString(movie.getCreatedAt()))
                    .updatedAt(timeStampToString(movie.getUpdatedAt()))
                    .build();
            movieResponses.add(data);

        }
        return movieResponses;
    }

    public MovieResponse getMovieById(Long custId){
        Optional<Movie> movieOptional = movieRepository.findById(custId);
        if (!movieOptional.isPresent()){
            log.error("failed to retrieve movie with Id {}",custId);
            throw new BusinessException(HttpStatus.BAD_REQUEST, "Failed", "Can't find detail movie by that Id");
        }
        Movie movie = movieOptional.get();
        return MovieResponse.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .description(movie.getDescription())
                .rating(movie.getRating())
                .image(movie.getImage())
                .createdAt(timeStampToString(movie.getCreatedAt()))
                .updatedAt(timeStampToString(movie.getUpdatedAt()))
                .build();
    }

    public MovieResponse createMovie(PostMovieReq req){
        Movie movie = Movie.builder()
            .title(req.getTitle())
            .description(req.getDescription())
            .rating(req.getRating())
            .image(req.getImage())
            .createdAt(new Timestamp(System.currentTimeMillis()))
            .updatedAt(new Timestamp(System.currentTimeMillis()))
            .build();

        movieRepository.save(movie);
        return MovieResponse.builder()
                .id(movie.getId())
                .title(req.getTitle())
                .description(req.getDescription())
                .rating(req.getRating())
                .image(req.getImage())
                .createdAt(timeStampToString(movie.getCreatedAt()))
                .updatedAt(timeStampToString(movie.getUpdatedAt()))
                .build();
    }

    public MovieResponse updateMovie(PutMovieReq req, Long id){
        Optional<Movie> movieOptional = movieRepository.findById(id);
        if (!movieOptional.isPresent()){
            log.error("failed to retrieve movie with Id {}", id);
            throw new BusinessException(HttpStatus.BAD_REQUEST, "Failed", "Can't find detail movie by that Id");
        }
        Movie movie = movieOptional.get();
        if (StringUtils.isNotBlank(req.getTitle())){
            movie.setTitle(req.getTitle());
        }
        if (StringUtils.isNotBlank(req.getDescription())){
            movie.setDescription(req.getDescription());
        }
        if (Objects.nonNull(req.getImage())){
            movie.setImage(req.getImage());
        }
        if (Objects.nonNull(req.getRating())){
            movie.setRating(req.getRating());
        }
        movie.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        movieRepository.save(movie);
        return MovieResponse.builder()
                .id(movie.getId())
                .title(req.getTitle())
                .description(req.getDescription())
                .rating(req.getRating())
                .image(req.getImage())
                .createdAt(timeStampToString(movie.getCreatedAt()))
                .updatedAt(timeStampToString(movie.getUpdatedAt()))
                .build();
    }

    public void deleteMovie(Long id){
        Optional<Movie> movieOptional = movieRepository.findById(id);
        if (!movieOptional.isPresent()){
            log.error("failed to retrieve movie with Id {}", id);
            throw new BusinessException(HttpStatus.BAD_REQUEST, "Failed", "Can't find detail movie by that Id");
        }
        Movie movie = movieOptional.get();
        movieRepository.delete(movie);
    }

    private String timeStampToString(Timestamp date){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }
}
