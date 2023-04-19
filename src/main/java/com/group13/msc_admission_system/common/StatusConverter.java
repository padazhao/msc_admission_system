package com.group13.msc_admission_system.common;

import org.springframework.core.convert.converter.Converter;

public class StatusConverter implements Converter <String, Status> {
    @Override
    public Status convert(String source) {
        try {
            return Status.valueOf(source.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Unknown Status");
        }
    }
}
