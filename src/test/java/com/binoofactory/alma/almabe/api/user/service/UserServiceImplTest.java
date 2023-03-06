package com.binoofactory.alma.almabe.api.user.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.client.HttpServerErrorException;

import com.binoofactory.alma.almabe.api.user.data.UserStatus;
import com.binoofactory.alma.almabe.api.user.model.docs.LoginHistory;
import com.binoofactory.alma.almabe.api.user.model.entity.Users;
import com.binoofactory.alma.almabe.api.user.repos.jpa.UserRepos;
import com.binoofactory.alma.almabe.api.user.repos.jpa.dsl.UsersDslRepos;
import com.binoofactory.alma.almabe.api.user.repos.mongo.LoginHistoryRepos;
import com.binoofactory.alma.almabe.api.user.service.impl.UserServiceImpl;
import com.binoofactory.alma.almabe.common.utils.PasswordUtil;

@SpringBootTest
public class UserServiceImplTest extends BDDMockito {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private PasswordUtil passwordUtil;

    @MockBean
    private UserJwtService userJwtService;

    @MockBean
    private UserRepos userRepos;

    @MockBean
    private UsersDslRepos userDslRepos;

    @MockBean
    private LoginHistoryRepos loginHistoryRepos;

    private MockHttpServletRequest fakeHeader() {
        String token = "token";
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader("Authorization", "bearer " + token);
        return request;
    }

    @Test
    @DisplayName("토큰으로 내 정보를 가져올 수 있다")
    public void shouldBeUserByHeaderToken() throws Exception {
        MockHttpServletRequest request = fakeHeader();

        given(userJwtService.getUserInfoByToken(request)).willReturn(new Users());

        Assertions.assertDoesNotThrow(() -> userService.findMe(request));
    }

    @Test
    @DisplayName("사용자 아이디로 중복된 사용자를 확인할 수 있다")
    public void shouldBeCheckNotDuplicateUserByUserId() throws Exception {
        String userId = "test";

        given(userRepos.findByUserId(userId)).willReturn(null);

        boolean usabled = Assertions.assertDoesNotThrow(() -> userService.checkUserId(userId));
        Assertions.assertTrue(usabled);
    }

    @Test
    @DisplayName("사용자 아이디로 중복되지 않은 사용자를 확인할 수 있다")
    public void shouldBeCheckDuplicatedUserByUserId() throws Exception {
        String userId = "test";

        given(userRepos.findByUserId(userId)).willReturn(new Users());

        boolean usabled = Assertions.assertDoesNotThrow(() -> userService.checkUserId(userId));
        Assertions.assertFalse(usabled);
    }

    @Test
    @DisplayName("삭제된 계정으로는 로그인할 수 없다")
    public void shouldNotBeLoginByDeletedUser() throws Exception {
        MockHttpServletRequest request = fakeHeader();
        Users dbUser = Users.builder()
            .userId("test")
            .password(passwordUtil.encode("1234"))
            .deleted(true)
            .build();
        Users requestParam = Users.builder()
            .userId("test")
            .password("1234")
            .build();

        given(userRepos.findByUserId(requestParam.getUserId())).willReturn(dbUser);
        given(loginHistoryRepos.save(any())).willReturn(LoginHistory.builder().build());

        HttpServerErrorException exception = Assertions.assertThrows(HttpServerErrorException.class,
            () -> userService.login(requestParam, request));
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
        Assertions.assertEquals("400 회원 정보가 존재하지 않습니다.", exception.getMessage());
    }

    @Test
    @DisplayName("비밀번호가 틀리면 로그인할 수 없다")
    public void shouldNotBeLoginByNotMatchPassword() throws Exception {
        MockHttpServletRequest request = fakeHeader();
        Users dbUser = Users.builder()
            .userId("test")
            .password(passwordUtil.encode("1234"))
            .deleted(false)
            .build();
        Users requestParam = Users.builder()
            .userId("test")
            .password("4321")
            .build();

        given(userRepos.findByUserId(requestParam.getUserId())).willReturn(dbUser);
        given(loginHistoryRepos.save(any())).willReturn(LoginHistory.builder().build());

        HttpServerErrorException exception = Assertions.assertThrows(HttpServerErrorException.class,
            () -> userService.login(requestParam, request));
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
        Assertions.assertEquals("400 비밀번호가 일치하지 않습니다.", exception.getMessage());
    }

    // 로그인 해피패스는 제외
}