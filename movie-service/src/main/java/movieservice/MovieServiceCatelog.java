package movieservice;


import movieservice.model.CatalogItem;
import movieservice.model.Movie;
import movieservice.model.Rating;
import movieservice.model.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieServiceCatelog {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {


        UserRating ratings = restTemplate.getForObject("http://ratinginfo/rating/user/"+userId, UserRating.class);
        // call info for each ID
        return ratings.getRatings().stream().map(rating-> {
            Movie movie = restTemplate.getForObject("http://movieinfo/movies/"+rating.getMovieId(), Movie.class);
            return new CatalogItem(movie.getName(),movie.getDescription(),rating.getRating());
        })
            .collect(Collectors.toList());

        // put together

    }
}
