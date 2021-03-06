package ru.internship.ballot.web.restaurant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.internship.ballot.model.Restaurant;
import ru.internship.ballot.service.RestaurantService;

import java.util.List;

import static ru.internship.ballot.util.ValidationUtil.*;

public abstract class AbstractRestaurantController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private RestaurantService service;

    public Restaurant getWithDishes(int id) {
        log.info("get restaurant {}", id);
        return service.getWithDishes(id);
    }

    public List<Restaurant> getAll() {
        log.info("get all restaurants");
        return service.getAll();
    }

    public void delete(int id) {
        log.info("delete restaurant {}", id);
        service.delete(id);
    }

    public void update(Restaurant restaurant, int id) {
        assureIdConsistent(restaurant, id);
        log.info("update restaurant {}", id);
        service.update(restaurant);
    }

    public Restaurant create(Restaurant restaurant) {
        checkNew(restaurant);
        log.info("create restaurant {}", restaurant.getId());
        return service.create(restaurant);
    }

}
