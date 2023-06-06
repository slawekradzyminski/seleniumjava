package com.awesome.testing.extensions;

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
//        @Override
//        public void beforeAnyWebElementCall(WebElement element, Method method, Object[] args) {
//            log.info("About to call a method {} in element {} with parameters {}",
//                    method, element, args);
//        }
//
//        @Override
//        public void afterAnyWebElementCall(WebElement element, Method method, Object[] args, Object result) {
//            log.info("Method {} called in element {} with parameters {} returned {}",
//                    method, element, args, result);
//        }

        @Override
        public void beforeClick(WebElement element) {
            log.info("About to call element with text {}. Element details are {}", element.getText(), element);
        }

        @Override
        public void afterClick(WebElement element) {
            log.info("Clicked element with text {}. Element details are {}", element.getText(), element);
        }

    };

}
