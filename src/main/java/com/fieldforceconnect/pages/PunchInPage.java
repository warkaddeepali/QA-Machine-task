package com.fieldforceconnect.pages;

import com.fieldforceconnect.elements.PunchInElements;
import com.fieldforceconnect.managers.WaitManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class PunchInPage {
    private final WebDriver driver;

    public PunchInPage(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateToPunchInModule() {
        WebElement menu = WaitManager.getWait(driver)
                .until(ExpectedConditions.presenceOfElementLocated(PunchInElements.PUNCH_IN_MENU));
        try {
            menu.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", menu);
        }
    }

    public void clickAddNew() {
        try {
            WaitManager.getWait(driver)
                    .until(ExpectedConditions.invisibilityOfElementLocated(PunchInElements.GLOBAL_TOAST_MESSAGE));
        } catch (Exception ignored) {}

        WebElement addNewBtn = WaitManager.getWait(driver)
                .until(ExpectedConditions.presenceOfElementLocated(PunchInElements.ADD_NEW_BUTTON));
        try {
            addNewBtn.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addNewBtn);
        }
    }

    public void enterFullTimesheetSequence() {
        java.time.LocalDateTime punchInDateTime = java.time.LocalDateTime.now();
        java.time.LocalDateTime punchOutDateTime = punchInDateTime.plusHours(9);
        clickPickerIconAndSelectDate(PunchInElements.PUNCH_IN_DATE_ICON, punchInDateTime);
        waitForBackdropToClear();
        clickPickerIconAndSetTime(PunchInElements.PUNCH_IN_TIME_ICON, punchInDateTime.toLocalTime());
        waitForPopoverToClose();
        clickPickerIconAndSelectDate(PunchInElements.PUNCH_OUT_DATE_ICON, punchOutDateTime);
        waitForBackdropToClear();
        clickPickerIconAndSetTime(PunchInElements.PUNCH_OUT_TIME_ICON, punchOutDateTime.toLocalTime());
        waitForPopoverToClose();
    }


    private void openPickerSafely(By locator) {
        WebElement targetIcon = WaitManager.getWait(driver).until(ExpectedConditions.presenceOfElementLocated(locator));
        try {
            WaitManager.getWait(driver).until(ExpectedConditions.elementToBeClickable(targetIcon)).click();
        } catch (Exception baseEx) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", targetIcon);
        }
    }

    private void clickPickerIconAndSelectDate(By pickerIconLocator, java.time.LocalDateTime targetDateTime) {
        openPickerSafely(pickerIconLocator);

        try {
            java.time.LocalDate today = java.time.LocalDate.now();
            java.time.LocalDate targetDate = targetDateTime.toLocalDate();

            WebElement dateCell;
            if (targetDate.isAfter(today)) {
                System.out.println("Time crossed midnight! Selecting next calendar day: " + targetDate.getDayOfMonth());
                String nextDayXpath = String.format("//button[contains(@class, 'MuiPickersDay-root') and text()='%d']", targetDate.getDayOfMonth());
                dateCell = WaitManager.getWait(driver).until(ExpectedConditions.elementToBeClickable(By.xpath(nextDayXpath)));
            } else {
                dateCell = WaitManager.getWait(driver).until(ExpectedConditions.elementToBeClickable(PunchInElements.CURRENT_DAY_SELECTION));
            }

            dateCell.click();
        } catch (Exception e) {
            System.err.println("Could not select the dynamic date cell: " + e.getMessage());
            WebElement fallbackDate = driver.findElement(PunchInElements.CURRENT_DAY_SELECTION);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", fallbackDate);
        }
    }


    private void clickPickerIconAndSetTime(By pickerIconLocator, LocalTime targetTime) {
        openPickerSafely(pickerIconLocator);
        String hh = targetTime.format(DateTimeFormatter.ofPattern("hh"));
        String mm = targetTime.format(DateTimeFormatter.ofPattern("mm"));
        String targetPeriod = targetTime.format(DateTimeFormatter.ofPattern("a")); // Evaluates to "AM" or "PM"

        try {
            WebElement popoverContainer = WaitManager.getWait(driver)
                    .until(ExpectedConditions.visibilityOfElementLocated(By.className("MuiPopover-paper")));
            WebElement hhInput = popoverContainer.findElement(By.xpath(".//input[@placeholder='HH']"));
            hhInput.click();
            hhInput.sendKeys(Keys.chord(Keys.CONTROL, "a"));
            hhInput.sendKeys(Keys.BACK_SPACE);
            hhInput.sendKeys(hh);
            WebElement mmInput = popoverContainer.findElement(By.xpath(".//input[@placeholder='MM']"));
            mmInput.click();
            mmInput.sendKeys(Keys.chord(Keys.CONTROL, "a"));
            mmInput.sendKeys(Keys.BACK_SPACE);
            mmInput.sendKeys(mm);

            WebElement periodBtn = popoverContainer.findElement(By.xpath(".//button[text()='AM' or text()='PM']"));
            String activePeriod = periodBtn.getText().trim();
            if (!activePeriod.equalsIgnoreCase(targetPeriod)) {
                try {
                    periodBtn.click();
                } catch (Exception e) {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", periodBtn);
                }
            }


            WebElement doneBtn = popoverContainer.findElement(By.xpath(".//button[text()='Done']"));
            try {
                doneBtn.click();
            } catch (Exception e) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", doneBtn);
            }

        } catch (Exception popoverInteractionException) {
            System.err.println("Critical failure while manipulating the open text input fields inside the time popover window: "
                    + popoverInteractionException.getMessage());
            dismissActiveOverlayBackdrop();
        }
    }

    private void waitForBackdropToClear() {
        try {
            WaitManager.getWait(driver).until(ExpectedConditions.invisibilityOfElementLocated(PunchInElements.POPOVER_BACKDROP));
        } catch (Exception ignored) {}
    }

    private void waitForPopoverToClose() {
        try {
            WaitManager.getWait(driver).until(ExpectedConditions.invisibilityOfElementLocated(By.className("MuiPopover-paper")));
        } catch (Exception ignored) {}
    }

    private void dismissActiveOverlayBackdrop() {
        try {
            driver.findElement(PunchInElements.DIALOG_TITLE_HEADER).click();
        } catch (Exception ignored) {}
    }

    public void clickSave() {
        WebElement saveBtn = WaitManager.getWait(driver)
                .until(ExpectedConditions.presenceOfElementLocated(PunchInElements.SAVE_BUTTON));
        try {
            saveBtn.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", saveBtn);
        }
    }

    public String getToastMessageText() {
        try {
            WebElement toastTextElement = WaitManager.getWait(driver)
                    .until(ExpectedConditions.visibilityOfElementLocated(PunchInElements.GLOBAL_TOAST_MESSAGE));

            int retries = 0;
            String capturedText = "";
            while (retries < 20) {
                capturedText = toastTextElement.getText();
                if (capturedText != null) {
                    capturedText = capturedText.trim();
                }
                if (capturedText == null || capturedText.isEmpty()) {
                    String textContent = toastTextElement.getAttribute("textContent");
                    capturedText = (textContent != null) ? textContent.trim() : "";
                }
                if (capturedText.isEmpty()) {
                    String innerText = toastTextElement.getAttribute("innerText");
                    capturedText = (innerText != null) ? innerText.trim() : "";
                }
                if (capturedText != null && !capturedText.isEmpty()) {
                    return capturedText;
                }

                Thread.sleep(100);
                retries++;
            }
            return capturedText;
        } catch (Exception e) {
            System.err.println("Could not extract toast notification text layer context: " + e.getMessage());
            return "";
        }
    }

}
