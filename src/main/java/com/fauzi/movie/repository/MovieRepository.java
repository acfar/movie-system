package com.fauzi.movie.repository;

import com.fauzi.movie.model.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
