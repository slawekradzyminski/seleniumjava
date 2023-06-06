package com.awesome.testing.tests.awesome;

import com.awesome.testing.generator.dto.User;
import com.awesome.testing.pages.awesome.AwesomeEditPage;
import com.awesome.testing.pages.awesome.AwesomeHomePage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.awesome.testing.generator.UserProvider.getRandomUser;

public class AwesomeEditTest extends AbstractAwesomeLoggedInTest {

    private AwesomeEditPage awesomeEditPage;

    @BeforeEach
    public void openEditPage() {
        awesomeEditPage = new AwesomeHomePage(driver).clickEditOnLastUser();
    }

    @Test
    public void shouldSuccessfullyEditUser() {
        User newUser = getRandomUser();

        awesomeEditPage.editUser(newUser);
    }


}
