package com.awesome.testing.pages.awesome;

import com.awesome.testing.dto.Roles;
import com.awesome.testing.dto.UserDto;
import com.awesome.testing.pages.AbstractBasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class AwesomeEditPage extends AbstractBasePage {

    @FindBy(name = "username")
    private WebElement usernameField;

    @FindBy(name = "email")
    private WebElement emailField;

    @FindBy(name = "firstName")
    private WebElement firstNameField;

    @FindBy(name = "lastName")
    private WebElement lastNameField;

    @FindBy(name = "roles")
    private WebElement rolesField;


    public AwesomeEditPage(WebDriver driver) {
        super(driver);
    }

    public void verifyUserDataCorrectlyDisplayed(UserDto user) {
        assertThat(usernameField.getAttribute("value")).isEqualTo(user.getUsername());
        assertThat(emailField.getAttribute("value")).isEqualTo(user.getEmail());
        assertThat(firstNameField.getAttribute("value")).isEqualTo(user.getFirstName());
        assertThat(lastNameField.getAttribute("value")).isEqualTo(user.getLastName());
        assertThat(rolesField.getAttribute("value")).isEqualTo(rolesToString(user.getRoles()));
    }

    public String rolesToString(Roles[] roles) {
        return Arrays.stream(roles)
                .map(Roles::name)
                .collect(Collectors.joining(","));
    }

}
