package ru.job4j.map;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ProfilesTest {

    @Test
    public void whenCollectProfilesIntoAddresses() {
        Profiles profiles = new Profiles();
        List<Profile> profilesList = List.of(
                new Profile(new Address("Moscow", "Arbat", 2, 30)),
                new Profile(new Address("NY", "Wall", 5, 27)),
                new Profile(new Address("London", "Baker", 221, 2))
        );
        List<Address> result = profiles.collect(profilesList);
        List<Address> expected = List.of(
                new Address("Moscow", "Arbat", 2, 30),
                new Address("NY", "Wall", 5, 27),
                new Address("London", "Baker", 221, 2)
        );
        assertEquals(expected, result);
    }

    @Test
    public void whenCollectUniqueProfilesIntoAddresses() {
        Profiles profiles = new Profiles();
        List<Profile> profilesList = List.of(
                new Profile(new Address("Moscow", "Arbat", 2, 30)),
                new Profile(new Address("NY", "Wall", 5, 27)),
                new Profile(new Address("London", "Baker", 221, 2)),
                new Profile(new Address("London", "Baker", 221, 2))

        );
        List<Address> result = profiles.collectUnique(profilesList);
        List<Address> expected = List.of(
                new Address("London", "Baker", 221, 2),
                new Address("Moscow", "Arbat", 2, 30),
                new Address("NY", "Wall", 5, 27)
        );
        assertEquals(expected, result);
    }
}