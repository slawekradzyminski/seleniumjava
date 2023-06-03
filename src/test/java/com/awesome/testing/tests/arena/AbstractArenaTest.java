package com.awesome.testing.tests.arena;

import com.awesome.testing.pages.arena.ArenaLoginPage;
import com.awesome.testing.tests.SeleniumTest;
import org.junit.jupiter.api.BeforeEach;

public class AbstractArenaTest extends SeleniumTest {

    protected ArenaLoginPage arenaLoginPage;

    @BeforeEach
    public void navigate() {
        driver.get("http://demo.testarena.pl/zaloguj");
        arenaLoginPage = new ArenaLoginPage(driver);
    }

}
