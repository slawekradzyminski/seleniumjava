package com.awesome.testing.local;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v111.emulation.Emulation;

import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class AutoDarkTest extends LocalTest {

    @BeforeEach
    public void setUp() {
        DevTools devTools = driver.getDevTools();
        devTools.createSession();
        devTools.send(Emulation.setAutoDarkModeOverride(Optional.of(true)));
    }

    @Test
    public void shouldTurnOffTheLights() {
        // when
        driver.get("https://github.com/slawekradzyminski");

        // then
        assertThat(getBGColor(driver.findElement(By.cssSelector(".js-pinned-items-reorder-container")))).contains("255");
    }

    private String getBGColor(WebElement elementToSearch) {
        WebElement current = elementToSearch;
        while(isTransparent(current.getCssValue("background-color"))) {
            if (current.getTagName().equals("body")) {
                return null;
            }
            // Find Parent
            current = current.findElement(By.xpath("./.."));
        }
        return current.getCssValue("background-color");
    }

    private boolean isTransparent(String color) {
        String colorMod = color.replaceAll("\\s+","").toLowerCase();
        return Arrays.asList("transparent","","rgba(0,0,0,0)").contains(colorMod);
    }

}
