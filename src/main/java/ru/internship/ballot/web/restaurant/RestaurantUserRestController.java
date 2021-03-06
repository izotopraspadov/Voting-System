package ru.internship.ballot.web.restaurant;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.internship.ballot.model.Restaurant;

import java.util.List;

@RestController
@RequestMapping(value = RestaurantUserRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantUserRestController extends AbstractRestaurantController {

    static final String REST_URL = "/rest/profile/restaurants";

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Restaurant getRestaurant(@PathVariable int id) {
        return super.getWithDishes(id);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Restaurant> getAllRestaurants() {
        return super.getAll();
    }

}
