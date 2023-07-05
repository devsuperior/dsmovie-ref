package com.devsuperior.dsmovie.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dsmovie.tests.TokenUtil;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class MovieControllerIT {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private TokenUtil tokenUtil;
	
	private String clientUsername;
	private String clientPassword;
	private String adminUsername;
	private String adminPassword;
	
	@BeforeEach
	void setUp() throws Exception {
		
		clientUsername = "alex@gmail.com";
		clientPassword = "123456";
		adminUsername = "maria@gmail.com";
		adminPassword = "123456";
	}
	
	@Test
	public void deleteShouldReturnForbiddenWhenClientLogged() throws Exception {
		ResultActions result = callDeleteMovie(29L, clientToken());		
		result.andExpect(status().isForbidden());
	}

	@Test
	public void deleteShouldReturnNoContentWhenAdminLogged() throws Exception {
		ResultActions result = callDeleteMovie(29L, adminToken());
		result.andExpect(status().isNoContent());
	}
	
	private ResultActions callDeleteMovie(Long movieId, String accessToken) throws Exception {
		return mockMvc.perform(delete("/movies/" + movieId)
				.header("Authorization", "Bearer " + accessToken)
				.accept(MediaType.APPLICATION_JSON));
	}
	
	private String adminToken() throws Exception {
		return tokenUtil.obtainAccessToken(mockMvc, adminUsername, adminPassword);
	}
	
	private String clientToken() throws Exception {
		return tokenUtil.obtainAccessToken(mockMvc, clientUsername, clientPassword);
	}	
}
