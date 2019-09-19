package ratinginfo;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ratinginfo.model.Rating;
import ratinginfo.model.UserRating;

import java.util.Arrays;
import java.util.List;


@RestController
@RequestMapping("/rating")
public class RatingInfo {

    @RequestMapping("/{movieId}")
    public Rating getRating(@PathVariable("movieId") String id){
        return new Rating(id, 4);
    }

    @RequestMapping("/user/{userId}")
    public UserRating getUserRatings(@PathVariable("userId") String userId) {
        List<Rating> ratings= Arrays.asList(
                new Rating("123",1),
                new Rating("123",2),
                new Rating("123",3),
                new Rating("123",4)
        );
        UserRating userRating = new UserRating();
        userRating.setRatings(ratings);
        return userRating;

    }

}
