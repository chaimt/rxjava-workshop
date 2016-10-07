package com.turel.rxjava.web;

import com.turel.rxjava.Application;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by chaimturkel on 10/4/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=Application.class)
@WebAppConfiguration
public class DeferedExampleTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    protected MockMvc mockMvc;

    @InjectMocks
    private DeferedExample myController;

    @Before
    public void setup() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .build();
    }

    @Test
    public void testDefered() throws Exception {
        this.mockMvc.perform(get("/api/v1/example/post")
                .param("postId", "1"))
                .andExpect(request().asyncStarted())
                .andExpect(request().asyncResult(new ResponseEntity<>(new StatusDto("\"sunt aut facere repellat provident occaecati excepturi optio reprehenderit\"",0), null, HttpStatus.OK)))
                .andExpect(status().isOk());
    }

    @Test
    public void testSimpleDefered() throws Exception {
        this.mockMvc.perform(get("/api/v1/example/post/simple")
                .param("postId", "1"))
                .andExpect(request().asyncStarted())
                .andExpect(request().asyncResult(new ResponseEntity<>(new StatusDto("\"sunt aut facere repellat provident occaecati excepturi optio reprehenderit\"",0), null, HttpStatus.OK)))
                .andExpect(status().isOk());
    }
}
