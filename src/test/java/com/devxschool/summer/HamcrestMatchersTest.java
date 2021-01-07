package com.devxschool.summer;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HamcrestMatchersTest {

    @Test
    public void hamcrestMatchers_1() {
        String animal = "Giraffe_ate_something_1";

//        MatcherAssert.assertThat(animal, Matchers.equalTo("Giraffe"));
//        MatcherAssert.assertThat(animal, Matchers.is("Elephant"));
//        MatcherAssert.assertThat(animal, Matchers.equalToIgnoringCase("giraffe"));
//        MatcherAssert.assertThat(animal, Matchers.not("Elephant"));

        MatcherAssert.assertThat(animal, Matchers.allOf(Matchers.containsString("Giraffe"), Matchers.endsWith("1")));
    }

    @Test
    public void hamcrestMatchers_2() {
        List<String> animals = Arrays.asList("Elephant", "Giraffe", "Crocodile");

//        MatcherAssert.assertThat(animals, Matchers.hasItem("Crocodile"));
//        MatcherAssert.assertThat(animals, Matchers.hasItems("Crocodile", "Elephant", "Giraffe", "Lion"));

        MatcherAssert.assertThat(animals, Matchers.anyOf(Matchers.hasItem("Crocodile"), Matchers.hasItem("Lion")));
        MatcherAssert.assertThat(animals, Matchers.allOf(Matchers.hasItem("Crocodile"), Matchers.hasItem("Lion")));
    }
}
