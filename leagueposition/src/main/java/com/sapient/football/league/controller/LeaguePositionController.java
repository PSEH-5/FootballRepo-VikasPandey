package com.sapient.football.league.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sapient.football.league.exceptions.LeagueException;
import com.sapient.football.league.model.LeagueResponse;
import com.sapient.football.league.services.LeagueService;

@RestController
@RequestMapping("/football")
public class LeaguePositionController {
	private static final Logger LOGGER = LoggerFactory.getLogger(LeaguePositionController.class);
	@Autowired
	private LeagueService leagueService;

	/**
	 * @param countryName
	 * @param leagueName
	 * @param teamName
	 * @param leaguePosition
	 * @return
	 * @throws LeagueException
	 */
	@RequestMapping(value = "/league/{countryName}/{leagueName}/{teamName}", method = RequestMethod.GET)
	public ResponseEntity<List<LeagueResponse>> getTeamStanding(
			@PathVariable(value = "countryName", required = true) String countryName,
			@PathVariable(value = "leagueName", required = true) String leagueName,
			@PathVariable(value = "teamName", required = true) String teamName
			) throws RuntimeException {

		List<LeagueResponse> responselist = leagueService.getStandingDetails(countryName, leagueName, teamName);

		return ResponseEntity.status(HttpStatus.OK).body(responselist);
	}
}
