package com.sapient.football.league.services;

import java.util.List;

import com.sapient.football.league.exceptions.LeagueException;
import com.sapient.football.league.model.LeagueResponse;
public interface LeagueService {
	String getCountryId(String countryName) throws LeagueException;
	
	String getLeagueId(String leagueName) throws LeagueException;
	
	List<LeagueResponse> getStandingDetails(String countryName, String leagueName, String teamName);
			
}
