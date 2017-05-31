package com.sdobrovolschi.hibernate.sqlformatter;

import com.p6spy.engine.spy.appender.MessageFormattingStrategy;
import org.hibernate.engine.jdbc.internal.BasicFormatterImpl;
import org.hibernate.engine.jdbc.internal.Formatter;

/**
 * @author Stanislav Dobrovolschi
 */
public class SimpleFormatter implements MessageFormattingStrategy {

    private static final Formatter HIBERNATE_SQL_FORMATTER = new BasicFormatterImpl();

    @Override
    public String formatMessage(int connectionId, String now, long elapsed, String category, String prepared, String sql) {
        if (sql.isEmpty()) {
            return "";
        }
        return String.format("JDBC: %s {elapsed: %dms}", HIBERNATE_SQL_FORMATTER.format(sql), elapsed);
    }
}
