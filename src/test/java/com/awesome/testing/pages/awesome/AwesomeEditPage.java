package com.awesome.testing.pages.awesome;

import com.awesome.testing.generators.Roles;
import com.awesome.testing.generators.UserDto;
import com.awesome.testing.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.awesome.testing.util.RolesToStringConverter.convert;
import static org.assertj.core.api.Assertions.assertThat;

public class AwesomeEditPage extends BasePage {

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

    public void verifyUserDataAutocompleted(UserDto user) {
        assertThat(usernameField.getAttribute("value")).isEqualTo(user.getUsername());
        assertThat(firstNameField.getAttribute("value")).isEqualTo(user.getFirstName());
        assertThat(lastNameField.getAttribute("value")).isEqualTo(user.getLastName());
        assertThat(emailField.getAttribute("value")).isEqualTo(user.getEmail());
        assertThat(rolesField.getAttribute("value")).isEqualTo(convert(user.getRoles()));
    }



}
