package ru.internship.ballot.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "restaurants", uniqueConstraints = {@UniqueConstraint(columnNames = {"title", "address"}, name = "restaurants_unique_title_address_idx")})
public class Restaurant extends AbstractTitleEntity {

    @Column(name = "address", nullable = false)
    @NotBlank
    @Size(min = 2, max = 120)
    private String address;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    @OrderBy("title")
    private List<Dish> dishes;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    @OrderBy("date DESC")
    private List<Vote> votes;

    public Restaurant() {

    }

    public Restaurant(String title, String address) {
        this(null, title, address);
    }

    public Restaurant(Integer id, String title, String address) {
        super(id, title);
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    public List<Vote> getVotes() {
        return votes;
    }

    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", title='" + title +
                ", address='" + address +
                '}';
    }
}
