package com.SurveyApp.auth.signup;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.SurveyApp.userProfile.common.password.config.SecurityConfig;
import com.SurveyApp.userProfile.userSignUp.controller.SignUpController;
import com.SurveyApp.userProfile.userSignUp.model.userDTO.UserSingUpDTO;
import com.SurveyApp.userProfile.userSignUp.service.UserSignUpService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@WebMvcTest(controllers = SignUpController.class)
@Import(SecurityConfig.class)
public class SignUpControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockitoBean
    UserSignUpService service;

    @Test
    public void signUpCompletedTest() throws Exception {
        GsonBuilder gsBuilder = new GsonBuilder();
        Gson gs = gsBuilder.create();
        mvc.perform(MockMvcRequestBuilders
                .post("http://192.168.1.37:8080/session/signup")
                .with(csrf())
                .content(
                        gs.toJson(new UserSingUpDTO("Name", "Password123#", "email@g.c"), UserSingUpDTO.class))
                .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
                .accept(org.springframework.http.MediaType.APPLICATION_JSON)).andExpect(status().isOk());

    }
}
