package com.awesome.testing.listener;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverListener;

import java.lang.reflect.Method;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SeleniumListener {

    public final static WebDriverListener LISTENER = new WebDriverListener() {
        @Override
        public void beforeAnyWebElementCall(WebElement element, Method method, Object[] args) {
            log.info("About to call a method {} in element {} with parameters {}",
                    method, element, args);
        }

        @Override
        public void afterAnyWebElementCall(WebElement element, Method method, Object[] args, Object result) {
            log.info("Method {} called in element {} with parameters {} returned {}",
                    method, element, args, result);
        }
    };

}
