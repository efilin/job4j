package ru.job4j.map;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ProfilesTest {

    @Test
    public void whenCollectProfilesIntoAddresses() {
        Profiles profiles = new Profiles();
        List<Profile> profilesList = new ArrayList<>();
        profilesList.add(new Profile(new Address("Moscow", "Arbat", 2, 30)));
        profilesList.add(new Profile(new Address("NY", "Wall", 5, 27)));
        profilesList.add(new Profile(new Address("London", "Baker", 221, 2)));
        List<Address> result = profiles.collect(profilesList);
        List<Address> expected = new ArrayList<>();
        expected.add(new Address("Moscow", "Arbat", 2, 30));
        expected.add(new Address("NY", "Wall", 5, 27));
        expected.add(new Address("London", "Baker", 221, 2));
        assertEquals(expected, result);
    }

    @Test
    public void whenCollectUniqueProfilesIntoAddresses() {
        Profiles profiles = new Profiles();
        List<Profile> profilesList = new ArrayList<>();
        profilesList.add(new Profile(new Address("Moscow", "Arbat", 2, 30)));
        profilesList.add(new Profile(new Address("NY", "Wall", 5, 27)));
        profilesList.add(new Profile(new Address("London", "Baker", 221, 2)));
        profilesList.add(new Profile(new Address("London", "Baker", 221, 2)));
        List<Address> result = profiles.collectUnique(profilesList);
        List<Address> expected = new ArrayList<>();
        expected.add(new Address("London", "Baker", 221, 2));
        expected.add(new Address("Moscow", "Arbat", 2, 30));
        expected.add(new Address("NY", "Wall", 5, 27));
        assertEquals(expected, result);
    }
}