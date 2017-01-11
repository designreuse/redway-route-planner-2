package org.mkhackathon.routing;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Location {

    private final String text;

    @JsonCreator
    public Location(@JsonProperty("text") String text) {
        this.text = text;
    }

    public String get() {
        if (isPostcode()) {
            return text;
        }
        return text + ", Milton Keynes";
    }

    private boolean isPostcode() {
        return text.matches("MK.+");
    }

    public String toString() {
        return get();
    }
}
