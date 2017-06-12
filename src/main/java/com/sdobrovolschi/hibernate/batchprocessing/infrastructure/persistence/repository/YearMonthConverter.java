package com.sdobrovolschi.hibernate.batchprocessing.infrastructure.persistence.repository;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.YearMonth;

import static java.time.YearMonth.of;

/**
 * @author Stanislav Dobrovolschi
 */
@Converter(autoApply = true)
public class YearMonthConverter implements AttributeConverter<YearMonth, Integer> {

    @Override
    public Integer convertToDatabaseColumn(YearMonth yearMonth) {
        return yearMonth.getYear() * 100 + yearMonth.getMonthValue();
    }

    @Override
    public YearMonth convertToEntityAttribute(Integer value) {
        int month = value % 100;
        return of((value - month) / 100, month);
    }
}
