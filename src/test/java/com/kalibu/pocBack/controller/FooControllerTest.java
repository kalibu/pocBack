package com.kalibu.pocBack.controller;

import com.google.gson.Gson;
import com.kalibu.pocBack.repository.FooRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class FooControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private FooRepository fooRepository;

    @Test
    public void test_getAllFoo() throws Exception {

        final String jsonAllEmployees = new Gson().toJson(fooRepository.findAll());

        this.mockMvc.perform(
                        get("/v1/foo/all"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(jsonAllEmployees));
    }

    @Test
    public void test_getAllFooAsc() throws Exception {

        final String jsonAllEmployees = new Gson().toJson(fooRepository.findAll(Sort.by(Sort.Direction.ASC, "fooProperty")));

        this.mockMvc.perform(
                        get("/v1/foo/all?order=A"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(jsonAllEmployees));
    }

    @Test
    public void test_getAllFooDesc() throws Exception {

        final String jsonAllEmployees = new Gson().toJson(fooRepository.findAll(Sort.by(Sort.Direction.DESC, "fooProperty")));

        this.mockMvc.perform(
                        get("/v1/foo/all?order=D"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(jsonAllEmployees));
    }

    @Test
    public void test_getAllFooDesc_IllegalArgument() throws Exception {

        this.mockMvc.perform(
                        get("/v1/foo/all?order=Desc"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

}
