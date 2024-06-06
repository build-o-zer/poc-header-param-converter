package org.juandfx.header_param_poc;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.time.Duration;

import org.springframework.stereotype.Component;

import jakarta.ws.rs.ext.ParamConverter;
import jakarta.ws.rs.ext.ParamConverterProvider;
import jakarta.ws.rs.ext.Provider;
import lombok.extern.slf4j.Slf4j;

@Component
@Provider
@Slf4j
public class DurationConverterProvider implements ParamConverterProvider {

    public DurationConverterProvider() {
        log.info("DurationConverterProvider ready to provide DurationConverter instances");
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> ParamConverter<T> getConverter(Class<T> rawType, Type genericType, Annotation[] annotations) {
        if (rawType.equals(Duration.class)) {
            return (ParamConverter<T>) new DurationConverter();
        }
        return null;
    }

    public static class DurationConverter  implements ParamConverter<Duration> {

        @Override
        public Duration fromString(String durationExpression) {
            try {
                return Duration.parse(durationExpression);
            } catch (Exception e) {
                log.error("Error parsing duration expression: {}", durationExpression);
                return null;
            }
        }
    
        @Override
        public String toString(Duration duration) {
           return duration.toString();
        }
        
    }
    
}
