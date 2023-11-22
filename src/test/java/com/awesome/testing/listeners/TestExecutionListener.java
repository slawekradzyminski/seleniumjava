package com.awesome.testing.listeners;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverListener;

@Slf4j
public class TestExecutionListener implements WebDriverListener {

//    @Override
//    public void beforeAnyCall(Object target, Method method, Object[] args) {
//        log.info("About to call a method {} in {} with parameters {}",
//                method, target, args);
//    }
//
//    @Override
//    public void afterAnyCall(Object target, Method method, Object[] args, Object result) {
//        log.info("Method {} called in {} with parameters {} returned {}",
//                method, target, args, result);
//    }

    @Override
    public void beforeSendKeys(WebElement element, CharSequence... keysToSend) {
        log.info("About to sendKeys {} into {}", keysToSend, element);
    }

}
