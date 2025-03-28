package com.awesome.testing.logging;

import ch.qos.logback.core.AppenderBase;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.IThrowableProxy;
import ch.qos.logback.classic.spi.StackTraceElementProxy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryAppender extends AppenderBase<ILoggingEvent> {

    private final List<String> logMessages = new ArrayList<>();
    private static final int STACK_TRACE_LINE_LIMIT = 20;

    @Override
    protected void append(ILoggingEvent event) {
        StringBuilder logEntry = new StringBuilder();

        // Format: LEVEL - loggerName: message
        logEntry.append(event.getLevel())
                .append(" - ")
                .append(event.getLoggerName())
                .append(": ")
                .append(event.getFormattedMessage());

        // Add truncated stack trace
        IThrowableProxy throwableProxy = event.getThrowableProxy();
        if (throwableProxy != null) {
            logEntry.append(System.lineSeparator())
                    .append(throwableProxy.getClassName())
                    .append(": ")
                    .append(throwableProxy.getMessage());

            StackTraceElementProxy[] stackTrace = throwableProxy.getStackTraceElementProxyArray();
            int limit = Math.min(STACK_TRACE_LINE_LIMIT, stackTrace.length);
            for (int i = 0; i < limit; i++) {
                logEntry.append(System.lineSeparator())
                        .append("\tat ")
                        .append(stackTrace[i].toString());
            }

            if (stackTrace.length > STACK_TRACE_LINE_LIMIT) {
                logEntry.append(System.lineSeparator()).append("\t... (truncated)");
            }
        }

        logMessages.add(logEntry.toString());
    }

    public String getAllLogs() {
        return logMessages.stream().collect(Collectors.joining(System.lineSeparator()));
    }
}
