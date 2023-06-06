package com.awesome.testing.tests.arena;

import com.awesome.testing.generator.dto.Project;
import com.awesome.testing.pages.arena.ArenaAddProjectPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static com.awesome.testing.generator.ProjectProvider.getRandomProject;

public class ArenaAddProjectTest extends AbstractArenaLoggedInTest {

    private ArenaAddProjectPage arenaAddProjectPage;

    @BeforeEach
    public void setUp() {
        driver.get("http://demo.testarena.pl/administration/add_project");
        arenaAddProjectPage = new ArenaAddProjectPage(driver);
    }

    @RepeatedTest(5)
    public void shouldAddNewProject() {
        Project project = getRandomProject();
        arenaAddProjectPage.addProject(project)
                .verifySuccessMessage();
    }

}
