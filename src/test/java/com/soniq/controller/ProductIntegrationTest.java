package com.soniq.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.soniq.Application;
import com.soniq.dto.BuildingDTO;
import com.soniq.dto.ResultDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author R.fatthi
 */
@SpringBootTest(classes = Application.class)
@RunWith(SpringRunner.class)
public class ProductIntegrationTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void setUp() {
        this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }

    @Test
    public void Optimise_IsOk() throws Exception {
        BuildingDTO buildingDTO = BuildingDTO.builder().senior(10).junior(6).rooms(Arrays.asList(35, 21, 17, 28)).build();
        ObjectMapper objectMapper = new ObjectMapper();
        MvcResult mvcResult = mvc
                .perform(post("/api/optimiser").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(buildingDTO)))
                .andExpect(status().isOk()).andReturn();
        String responseJson = mvcResult.getResponse().getContentAsString();
        ResultDTO[] resultDTOS = objectMapper.readValue(responseJson, ResultDTO[].class);
        assertThat(resultDTOS.length).isEqualTo(4);
    }
}
