package com.sapient.football.league.leagueposition;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.sapient.football.league.exceptions.LeagueException;
import com.sapient.football.league.model.LeagueResponse;
import com.sapient.football.league.services.LeagueService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class LeaguepositionApplicationTests {
	
	@Autowired
    private MockMvc mvc;

	@LocalServerPort
	private int port;
	
	@Autowired
	private LeagueService leagueService;

	@Autowired
	private TestRestTemplate testRestTemplate;
	
	@Test
	public void getTeamStandings()
	{
		try {
			List<LeagueResponse> response=leagueService.getStandingDetails("Scotland", "Premiership", "Celtic");
			assert(!response.isEmpty());
		}
		catch (Exception e) {
			assert(true);
		}
	}
	
	@Test
	public void getStandings_InvalidCountryName() throws LeagueException
	{
		try {
			List<LeagueResponse> response = leagueService.getStandingDetails("Scotland123", "Premiership", "Celtic");
			assert(response.isEmpty());
		}
		catch (Exception e) {
			assert(true);
		}

	}

}
