package com.xyy.movies.service.impl;

import com.xyy.movies.service.MoviesService;
import org.springframework.stereotype.Service;

@Service
public class MoviesServiceImpl implements MoviesService {
    @Override
    public String test() {
        return "movies test";
    }
}
