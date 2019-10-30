package com.sapient.football.league.model;

public class LeagueResponse {
	private String countryId;
	private String countryName;
	private String legaueId;
	private String leagueName;
	private String teamId;
	private String teamName;
	private String leaguePosition;
	
	public String getCountryId() {
		return countryId;
	}
	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public String getLegaueId() {
		return legaueId;
	}
	public void setLegaueId(String legaueId) {
		this.legaueId = legaueId;
	}
	public String getLeagueName() {
		return leagueName;
	}
	public void setLeagueName(String leagueName) {
		this.leagueName = leagueName;
	}
	public String getTeamId() {
		return teamId;
	}
	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public String getLeaguePosition() {
		return leaguePosition;
	}
	public void setLeaguePosition(String leaguePosition) {
		this.leaguePosition = leaguePosition;
	}
	
	@Override
	public String toString() {
		return "FootBallLeagueResponse [countryId=" + countryId + ", countryName=" + countryName + ", legaueId="
				+ legaueId + ", leagueName=" + leagueName + ", teamId=" + teamId + ", teamName=" + teamName
				+ ", leaguePosition=" + leaguePosition + "]";
	}
	
	
	
}
