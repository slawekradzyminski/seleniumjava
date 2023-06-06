package com.awesome.testing.tests.awesome;

import com.awesome.testing.api.UserApi;
import com.awesome.testing.api.dto.UserDetailsDto;
import com.awesome.testing.generator.dto.User;
import com.awesome.testing.pages.awesome.AwesomeEditPage;
import com.awesome.testing.pages.awesome.AwesomeHomePage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.awesome.testing.generator.UserProvider.getRandomUser;
import static org.assertj.core.api.Assertions.assertThat;

public class AwesomeEditTest extends AbstractAwesomeLoggedInTest {

    private final UserApi userApi = new UserApi();

    private AwesomeEditPage awesomeEditPage;
    private String username;

    @BeforeEach
    public void openEditPage() {
        awesomeEditPage = new AwesomeHomePage(driver).clickEditOnLastUser();
        username = awesomeEditPage.readUsername();
    }

    @Test
    public void shouldSuccessfullyEditUser() {
        User newUser = getRandomUser();

        awesomeEditPage.editUser(newUser)
                .getAlert()
                .verifySuccessMessage("Updating user successful");

        verifyUserUpdatedOnServerSide(newUser);
    }

    private void verifyUserUpdatedOnServerSide(User newUser) {
        UserDetailsDto userDetailsDto = userApi.getUserDetails(username, token);
        assertThat(userDetailsDto.getFirstName()).isEqualTo(newUser.getFirstName());
        assertThat(userDetailsDto.getLastName()).isEqualTo(newUser.getLastName());
        assertThat(userDetailsDto.getEmail()).isEqualTo(newUser.getEmail());
    }

}
