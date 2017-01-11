package org.mkhackathon.routing;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

public class LocationTest {

    @Test
    public void should_just_return_postcode_if_postcode() throws Exception {
        assertThat(new Location("MK12 5JD").get(), equalTo("MK12 5JD"));
    }

    @Test
    public void should_append_milton_keynes_and_upper_case_if_not_postcode() throws Exception {
        assertThat(new Location("Wolverton").get(), equalTo("Wolverton, Milton Keynes"));
    }
}