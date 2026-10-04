package com.example;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TodoSeleniumTest {

    @Test
    public void testAddTask() {

        WebDriver driver = new ChromeDriver();

        try {

            // Open the Docker application
            driver.get("http://localhost:8081/todo-app/");

            // Enter task
            driver.findElement(By.id("taskInput"))
                    .sendKeys("Selenium Test Task");

            // Click Add Task
            driver.findElement(By.id("addButton"))
                    .click();

            // Get displayed tasks
            String taskList = driver.findElement(By.id("taskList"))
                    .getText();

            // Verify task
            assertTrue(
                taskList.contains("Selenium Test Task"),
                "Task was not added"
            );

        } finally {

            driver.quit();
        }
    }
}