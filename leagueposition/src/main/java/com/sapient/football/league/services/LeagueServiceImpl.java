package com.sapient.football.league.services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.sapient.football.league.config.LeagueURLConfig;
import com.sapient.football.league.controller.LeaguePositionController;
import com.sapient.football.league.dto.CountryResponseDTO;
import com.sapient.football.league.dto.LeagueResponseDTO;
import com.sapient.football.league.dto.StandingLeagueResponseDTO;
import com.sapient.football.league.exceptions.CountryNotFoundException;
import com.sapient.football.league.exceptions.LeagueNotFoundException;
import com.sapient.football.league.model.LeagueResponse;

@Component
public class LeagueServiceImpl implements LeagueService {
	private static final Logger LOGGER = LoggerFactory.getLogger(LeagueServiceImpl.class);
	@Autowired
	LeagueURLConfig leagueURLConfig;

	@Override
	public String getCountryId(String countryName) throws CountryNotFoundException {

		ResponseEntity<CountryResponseDTO[]> countryResponseEntity = new RestTemplate()
				.getForEntity(leagueURLConfig.getGetCountriesURL(), CountryResponseDTO[].class);
		String country_Id = "";

		for (CountryResponseDTO countryResponseDTO : countryResponseEntity.getBody()) {
			System.out.println("country == " + countryResponseDTO.toString());
			if (countryName.equalsIgnoreCase(countryResponseDTO.getCountry_name())) {
				country_Id = countryResponseDTO.getCountry_id();
			}
		}

		if (StringUtils.isEmpty(country_Id)) {
			throw new CountryNotFoundException("Country Not Found.");
		}
		return country_Id;
	}

	/**
	 *
	 */
	@Override
	public String getLeagueId(String country_Id) throws LeagueNotFoundException {

		String league_Id = "";

		String leagueUrl = UriComponentsBuilder.fromUriString(leagueURLConfig.getGetLeaguesURL())
				.replaceQueryParam("country_id", country_Id).toUriString();

		ResponseEntity<LeagueResponseDTO[]> leagueResponseEntity = new RestTemplate().getForEntity(leagueUrl, LeagueResponseDTO[].class);

		for (LeagueResponseDTO leagueResponseDTO : leagueResponseEntity.getBody()) {
			System.out.println("league list == " + leagueResponseDTO.toString());
			league_Id = leagueResponseDTO.getLeague_id();
		}

		if (StringUtils.isEmpty(league_Id)) {
			throw new LeagueNotFoundException("League not Found.");			
		}

		return league_Id;
	}

	@Override
	public List<LeagueResponse> getStandingDetails(String countryName, String leagueName, String teamName)  {
		// TODO Auto-generated method stub
		List<LeagueResponse> list = new ArrayList<>();
		String country_Id = null;
		String league_Id = null;
		LeagueResponse leagueResponse = null;
		try {
			country_Id = getCountryId(countryName);
			league_Id = getLeagueId(country_Id);
		}catch(CountryNotFoundException|LeagueNotFoundException e) {
			return list;
		}

		Object config;
		String standingUrl = UriComponentsBuilder.fromUriString(leagueURLConfig.getGetStandingsURL())
				.replaceQueryParam("league_id", league_Id).toUriString();

		ResponseEntity<StandingLeagueResponseDTO[]> standingResponseEntity = new RestTemplate()
				.getForEntity(standingUrl, StandingLeagueResponseDTO[].class);

		

		for (StandingLeagueResponseDTO standings : standingResponseEntity.getBody()) {
			System.out.println("standings ==  " + standings.toString());
			if (standings.getLeague_name().equalsIgnoreCase(leagueName)
					&& standings.getCountry_name().equalsIgnoreCase(countryName)
					&& standings.getTeam_name().equalsIgnoreCase(teamName)) {
				leagueResponse = new LeagueResponse();
				leagueResponse.setCountryId(country_Id);
				leagueResponse.setCountryName(standings.getCountry_name());
				leagueResponse.setLeagueName(standings.getLeague_name());
				leagueResponse.setLegaueId(league_Id);
				leagueResponse.setTeamId(standings.getTeam_id());
				leagueResponse.setTeamName(standings.getTeam_name());
				leagueResponse.setLeaguePosition(standings.getOverall_league_position());
				list.add(leagueResponse);
			}

		}
		return list;
	}

}
