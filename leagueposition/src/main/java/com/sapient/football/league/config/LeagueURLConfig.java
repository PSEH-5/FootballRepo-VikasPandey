package com.sapient.football.league.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class LeagueURLConfig {
	
	@Value("${get_countries_url}")
	private String getCountriesURL;
	
	@Value("${get_leagues_url}")
	private String getLeaguesURL;
	
	@Value("${get_standings_url}")
	private String getStandingsURL;

	public String getGetCountriesURL() {
		return getCountriesURL;
	}

	public void setGetCountriesURL(String getCountriesURL) {
		this.getCountriesURL = getCountriesURL;
	}

	public String getGetLeaguesURL() {
		return getLeaguesURL;
	}

	public void setGetLeaguesURL(String getLeaguesURL) {
		this.getLeaguesURL = getLeaguesURL;
	}

	public String getGetStandingsURL() {
		return getStandingsURL;
	}

	public void setGetStandingsURL(String getStandingsURL) {
		this.getStandingsURL = getStandingsURL;
	}

	
	

}
