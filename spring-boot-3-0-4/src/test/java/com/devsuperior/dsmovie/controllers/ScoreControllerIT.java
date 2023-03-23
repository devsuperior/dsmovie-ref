package com.devsuperior.dsmovie.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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

import com.devsuperior.dsmovie.dto.ScoreDTO;
import com.devsuperior.dsmovie.tests.TokenUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class ScoreControllerIT {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private TokenUtil tokenUtil;
	
	@Autowired
	private ObjectMapper objectMapper;

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
	public void putScoreShouldCalculateNewScoreWhenClientLoggedAndValidData() throws Exception {
		
		ScoreDTO scoreDTO = new ScoreDTO(1L, 4.0);

		ResultActions result = callPutScore(scoreDTO, clientToken());

		result.andExpect(status().isOk());
		result.andExpect(jsonPath("$.id").value(1L));
		result.andExpect(jsonPath("$.title").value("The Witcher"));
		result.andExpect(jsonPath("$.score").value(4.33));
		result.andExpect(jsonPath("$.count").value(3));
		result.andExpect(jsonPath("$.image").value("https://www.themoviedb.org/t/p/w533_and_h300_bestv2/jBJWaqoSCiARWtfV0GlqHrcdidd.jpg"));
	}

	@Test
	public void putScoreShouldCalculateNewScoreWhenAdminLoggedAndValidData() throws Exception {
		
		ScoreDTO scoreDTO = new ScoreDTO(1L, 4.0);

		ResultActions result = callPutScore(scoreDTO, adminToken());

		result.andExpect(status().isOk());
		result.andExpect(jsonPath("$.id").value(1L));
		result.andExpect(jsonPath("$.title").value("The Witcher"));
		result.andExpect(jsonPath("$.score").value(4.33));
		result.andExpect(jsonPath("$.count").value(3));
		result.andExpect(jsonPath("$.image").value("https://www.themoviedb.org/t/p/w533_and_h300_bestv2/jBJWaqoSCiARWtfV0GlqHrcdidd.jpg"));
	}
	
	private ResultActions callPutScore(ScoreDTO body, String accessToken) throws Exception {
		return mockMvc.perform(put("/scores")
				.header("Authorization", "Bearer " + accessToken)
				.content(objectMapper.writeValueAsString(body))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON));
	}
	
	private String clientToken() throws Exception {
		return tokenUtil.obtainAccessToken(mockMvc, clientUsername, clientPassword);
	}
	
	private String adminToken() throws Exception {
		return tokenUtil.obtainAccessToken(mockMvc, adminUsername, adminPassword);
	}
}
