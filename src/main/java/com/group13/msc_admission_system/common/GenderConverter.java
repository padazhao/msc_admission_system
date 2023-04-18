package com.group13.msc_admission_system.common;

import org.springframework.core.convert.converter.Converter;

/**
 * This is a helper class that helps convert strings to enum
 * In this case it converts strings to Gender
 */
public class GenderConverter implements Converter<String, Gender> {
        @Override
        public Gender convert(String source) {
            try {
                return Gender.valueOf(source.toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Gender should be MALE. FEMALE or OTHER");
            }
        }
}

