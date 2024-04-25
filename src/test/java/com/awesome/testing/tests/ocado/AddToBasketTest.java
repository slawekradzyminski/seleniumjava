//package com.awesome.testing.tests.ocado;
//
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.Test;
//import org.openqa.selenium.By;
//
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@Slf4j
//public class AddToBasketTest extends AbstractOcadoTest {
//
//    private static final List<String> PRODUCTS = List.of(
//            "Haribo",
//            "Chupa Chups",
//            "Tomatoes",
//            "Pizza",
//            "Kinder Surprise Eggs",
//            "Paprika",
//            "Eggs"
//    );
//
//    @Test
//    public void shouldAddProducts() {
//        log.info("Starting shopping now :)");
//        long currentTimeMillis = System.currentTimeMillis();
//        PRODUCTS.forEach(product -> browsePage.searchFor(product).addToTrolley());
//        assertThat(driver.findElement(By.cssSelector(".hd-basketSummary__count span")).getText())
//                .isEqualTo(String.valueOf(PRODUCTS.size()));
//        log.info("Shopping took {} seconds", lengthInSeconds(currentTimeMillis));
//    }
//
//    private long lengthInSeconds(long currentTimeMillis) {
//        return (System.currentTimeMillis() - currentTimeMillis) / 1000;
//    }
//
//}
