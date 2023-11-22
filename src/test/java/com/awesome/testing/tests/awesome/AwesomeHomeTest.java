package com.awesome.testing.tests.awesome;

import com.awesome.testing.api.LoginApi;
import com.awesome.testing.api.RegisterApi;
import com.awesome.testing.api.dto.LoginRequestDto;
import com.awesome.testing.api.dto.LoginResponseDto;
import com.awesome.testing.generators.UserDto;
import com.awesome.testing.pages.awesome.AwesomeHomePage;
import com.awesome.testing.tests.SeleniumTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.html5.WebStorage;

public class AwesomeHomeTest extends SeleniumTest {

    private final RegisterApi registerApi = new RegisterApi();
    private final LoginApi loginApi = new LoginApi();
    private String token;
    private UserDto user;
    private AwesomeHomePage homePage;

    @SneakyThrows
    @BeforeEach
    public void logIn() {
        driver.get("http://localhost:8081");
        user = UserDto.getRandomUser();
        registerApi.register(user);
        // 1: wysyłamy request na users/signin
        LoginResponseDto loginResponseDto = loginApi.login(new LoginRequestDto(user.getUsername(), user.getPassword()));
        // 2: ustawiamy odpowiedź w localStorage po kluczem user
        ((WebStorage) driver).getLocalStorage().setItem("user", new ObjectMapper().writeValueAsString(loginResponseDto));
        // 3: ustawiamy ciastko token z wartością tokena z odpowiedzi
        // 3a: pamiętajmy że Selenium dodaje ciastko dla domeny tożsamej ze stroną na której aktualnie jesteśmy
        driver.manage().addCookie(new Cookie("token", loginResponseDto.getToken()));
        // 4: proces logowania zwracał token do użytku w kolejnych testach
        token = loginResponseDto.getToken();
        driver.navigate().to("http://localhost:8081");
        homePage = new AwesomeHomePage(driver);
    }

    @Test
    public void shouldDisplayWelcomeHeader() {
        homePage
                .verifyHeaderForName(user.getFirstName());
    }

}
