package com.awesome.testing.tests.testarena;

import com.awesome.testing.config.ConfigProvider;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class TALoginTest extends AbstractTestArenaTest {

    @Test
    public void shouldSuccessfullyLogin() {
        String mail = ConfigProvider.get("credentials.username");
        String password = ConfigProvider.get("credentials.password");

        TALoginPage.attemptLogin(mail, password)
                .openProjects()
                .waitForTextAreaToLoad();
    }

}
