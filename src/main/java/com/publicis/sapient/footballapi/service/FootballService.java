package com.publicis.sapient.footballapi.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;

@Service
public class FootballService {

  @Value("${api.url}")
  private String apiUrl;

  @Value("${api.key}")
  private String apiKey;


 @HystrixCommand(fallbackMethod = "fallback")
  public String getStandings(String countryId, String leagueId) {

    apiUrl =
        new StringBuilder(apiUrl)
            .append("&APIkey=")
            .append(apiKey)
            .append("&country_id=")
            .append(countryId)
            .append("&league_id=")
            .append(leagueId).toString();

    WebClient webClient = WebClient.create(apiUrl);
    return webClient.get().retrieve().bodyToMono(String.class).timeout(Duration.ofMillis(5000)).block();
  }

  public String fallback(String countryId, String leagueId, Throwable hystrixCommand) {
    return "";
  }
}
