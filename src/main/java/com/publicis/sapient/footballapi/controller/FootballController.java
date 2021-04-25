package com.publicis.sapient.footballapi.controller;

import com.publicis.sapient.footballapi.service.FootballService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class FootballController {

  private final FootballService footballService;

  @Autowired
  public FootballController(final FootballService footballService) {
    this.footballService = footballService;
  }

  @ApiOperation("To get Team Standings for all leagues")
  @ApiResponses({
    @io.swagger.annotations.ApiResponse(code = 200, message = "Accepted"),
    @io.swagger.annotations.ApiResponse(code = 400, message = "Bad Request")
  })
  @GetMapping("/getStandings")
  public String getStandings(
      @RequestParam(value = "country_id", required = false) String countryId,
      @RequestParam(value = "league_id") String leagueId) {

    return footballService.getStandings(countryId != null ? countryId : "", leagueId);
  }
}
