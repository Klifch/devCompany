package com.programming3.devcompany.presentation.converter;

import com.programming3.devcompany.domain.Position;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PositionConverter implements Converter<String, Position> {

    private Logger logger = LoggerFactory.getLogger(PositionConverter.class);
    @Override
    public Position convert(String source) {
        try {
            logger.info("Converting String {} to Position ...", source);
            return Position.valueOf(source.toUpperCase());
        } catch (IllegalArgumentException e) {
            logger.warn("Impossible to convert String {} to Position!", source);
            return null;
        }
    }
}
