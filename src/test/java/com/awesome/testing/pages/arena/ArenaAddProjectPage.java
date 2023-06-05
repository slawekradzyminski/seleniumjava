package com.awesome.testing.pages.arena;

import com.awesome.testing.generator.dto.Project;
import com.awesome.testing.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ArenaAddProjectPage extends BasePage {

    @FindBy(id = "name")
    private WebElement nameField;

    @FindBy(id = "prefix")
    private WebElement prefixField;

    @FindBy(id = "description")
    private WebElement descriptionField;

    @FindBy(id = "save")
    private WebElement saveButton;

    public ArenaAddProjectPage(WebDriver driver) {
        super(driver);
    }

    public ArenaAddProjectPage addProject(Project project) {
        nameField.sendKeys(project.getName());
        prefixField.sendKeys(project.getPrefix());
        descriptionField.sendKeys(project.getDescription());
        saveButton.click();
        return this;
    }

    public void verifySuccessMessage() {
        wait.until(ExpectedConditions.textToBe(By.cssSelector("#j_info_box p"), "Projekt został dodany."));
    }
}
