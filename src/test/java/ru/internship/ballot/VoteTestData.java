package ru.internship.ballot;

import ru.internship.ballot.model.Vote;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static java.time.LocalDate.of;
import static org.assertj.core.api.Assertions.assertThat;
import static ru.internship.ballot.RestaurantTestData.FIRST_RESTAURANT;
import static ru.internship.ballot.RestaurantTestData.SECOND_RESTAURANT;
import static ru.internship.ballot.UserTestData.USER1;
import static ru.internship.ballot.UserTestData.USER2;
import static ru.internship.ballot.model.AbstractBaseEntity.START_SEQ;

public class VoteTestData {

    public static final int VOTE1_ID_USER1 = START_SEQ + 19;
    public static final int VOTE2_ID_USER1 = VOTE1_ID_USER1 + 2;
    public static final int NEXT_VOTE_ID = VOTE2_ID_USER1 + 2;

    public static final Vote VOTE1_USER1 = new Vote(VOTE1_ID_USER1, of(2018, Month.DECEMBER, 3), USER1, FIRST_RESTAURANT);
    public static final Vote VOTE2_USER1 = new Vote(VOTE2_ID_USER1, of(2018, Month.DECEMBER, 4), USER1, SECOND_RESTAURANT);

    public static final Vote VOTE1_USER2 = new Vote(VOTE1_ID_USER1 + 1, of(2018, Month.DECEMBER, 3), USER2, SECOND_RESTAURANT);
    public static final Vote VOTE2_USER2 = new Vote(VOTE1_ID_USER1 + 3, of(2018, Month.DECEMBER, 4), USER2, FIRST_RESTAURANT);

    public static final Vote TODAYVOTE_USER1 = new Vote(NEXT_VOTE_ID, LocalDate.now(), USER1, FIRST_RESTAURANT);

    public static Vote getUpdated() {
        Vote vote = new Vote(VOTE1_USER1.getDate());
        vote.setId(VOTE1_ID_USER1);
        vote.setRestaurant(FIRST_RESTAURANT);
        vote.setUser(USER1);
        return vote;
    }

    public static Vote getNextVote() {
        Vote vote = new Vote(LocalDate.now());
        vote.setId(NEXT_VOTE_ID);
        vote.setRestaurant(FIRST_RESTAURANT);
        vote.setUser(USER1);
        return vote;
    }

    public static void assertMatch(Vote actual, Vote expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "user", "restaurant");
    }

    public static void assertMatch(Iterable<Vote> actual, Vote... expected) {
        assertMatch(actual, List.of(expected));
    }

    public static void assertMatch(Iterable<Vote> actual, Iterable<Vote> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("user", "restaurant").isEqualTo(expected);
    }

}
