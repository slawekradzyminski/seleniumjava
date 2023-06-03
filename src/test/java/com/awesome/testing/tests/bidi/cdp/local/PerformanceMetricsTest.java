package com.awesome.testing.tests.bidi.cdp.local;

import com.awesome.testing.tests.bidi.BidiTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v111.performance.Performance;
import org.openqa.selenium.devtools.v111.performance.model.Metric;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class PerformanceMetricsTest extends BidiTest {

    private List<Metric> metricList;

    @BeforeEach
    public void setUp() {
        DevTools devTools = driver.getDevTools();
        devTools.createSession();
        devTools.send(Performance.enable(Optional.empty()));
        metricList = devTools.send(Performance.getMetrics());
    }

    @Test
    public void shouldCollectPerformanceMetrics() {
        // when
        driver.get("https://awesome-testing.com");

        // then
        metricList.forEach(PerformanceMetricsTest::logAndAssert);
    }

    private static void logAndAssert(Metric metric) {
        log.info("{} = {}", metric.getName(), metric.getValue());
        assertThat(metric.getValue().longValue()).isGreaterThanOrEqualTo(0);
    }

}
