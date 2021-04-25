package com.publicis.sapient.footballapi.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiReponse {
    @JsonProperty("country_id")
    private String countryId;

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(final String countryId) {
        this.countryId = countryId;
    }
}
