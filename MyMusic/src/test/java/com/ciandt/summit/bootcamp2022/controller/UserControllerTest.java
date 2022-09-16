package com.ciandt.summit.bootcamp2022.controller;

import com.ciandt.summit.bootcamp2022.entity.ERole;
import com.ciandt.summit.bootcamp2022.entity.User;
import com.ciandt.summit.bootcamp2022.entity.UserType;
import com.ciandt.summit.bootcamp2022.exception.UserNotFoundException;
import com.ciandt.summit.bootcamp2022.interceptor.TokenInterceptor;
import com.ciandt.summit.bootcamp2022.service.UserService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserControllerTest {

    @MockBean
    private UserService userService;

    @MockBean
    private TokenInterceptor tokenInterceptor;

    @Autowired
    private MockMvc mvc;

    private static User user;

    @BeforeAll
    public void init()  {
        UserType userType = new UserType("2", ERole.PREMIUM);
        user = new User("1", "Maria", userType);
    }

    @BeforeEach
    void initTest() {
        given(tokenInterceptor.preHandle(any(), any(), any())).willReturn(true);
    }

    @Test
    @DisplayName("Not existing user return HTTP.StatusCode.BADREQUEST")
    void findUserReturnsNotFound() throws Exception {

        given(userService.findUser(user.getId()))
                .willThrow(new UserNotFoundException("User not found"));

        RequestBuilder request = get("/api/v1/users/1");
        mvc.perform(request).andExpect(MockMvcResultMatchers.status().isBadRequest()).andReturn();
    }

    @Test
    @DisplayName("Existing user should return HTTP.StatusCode.OK")
    void findUserReturnsSuccess() throws Exception {

        RequestBuilder request = get("/api/v1/users/1");
        mvc.perform(request).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
    }

}