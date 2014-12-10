package com.epam.multithreading.model.test;

import com.epam.multithreading.model.*;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class PersonTest {

    @Test
    public final void checkConstructor() {
        final String fn = "f";
        final String ln = "l";
        final String govId = "g";
        final Person person = new Person(fn, ln, govId);
        assertEquals(person.getFirstName(), fn);
        assertEquals(person.getLastName(), ln);
        assertEquals(person.getGovermentalId(), govId);
    }
}
