package com.awesome.testing.tests.awesome;

import com.awesome.testing.api.UserDetailsApi;
import com.awesome.testing.api.dto.UserResponseDto;
import com.awesome.testing.generators.Roles;
import com.awesome.testing.generators.UserDto;
import com.awesome.testing.pages.awesome.AwesomeEditPage;
import com.awesome.testing.pages.awesome.AwesomeHomePage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AwesomeEditTest extends AbstractAwesomeLoggedInTest {

    private final UserDetailsApi userDetailsApi = new UserDetailsApi();

    private AwesomeEditPage editPage;

    @BeforeEach
    public void init() {
        AwesomeHomePage homePage = new AwesomeHomePage(driver);
        editPage = homePage.clickEditOnSpecificUser(user);
    }

    @Test
    public void shouldCorrectlyAutofillData() {
        editPage.verifyUserDataAutocompleted(user);
    }

    @Test
    public void shouldCorrectlyEditUser() {
        // given
        UserDto newUser = UserDto.getRandomUser();

        // when
        editPage.attemptToEditUser(newUser)
                .getAlert()
                .verifyAlertSuccess("Updating user successful");

        // then
        UserResponseDto userDetails = userDetailsApi.getUserDetails(user.getUsername(), token);
        assertUserDetails(userDetails, newUser);
    }

    private void assertUserDetails(UserResponseDto userDetails, UserDto newUser) {
        assertThat(userDetails.getUsername()).isEqualTo(user.getUsername());
        assertThat(userDetails.getRoles()).contains(Roles.ROLE_ADMIN, Roles.ROLE_CLIENT);
        assertThat(userDetails.getFirstName()).isEqualTo(newUser.getFirstName());
        assertThat(userDetails.getLastName()).isEqualTo(newUser.getLastName());
        assertThat(userDetails.getEmail()).isEqualTo(newUser.getEmail());
    }

}
