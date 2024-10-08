package com.komanrudden.multiauthapi.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Custom serializer for `LocalDateTime` objects.
 * <p>
 * This serializer formats `LocalDateTime` objects into a string representation
 * using the pattern "dd-MM-yyyy hh:mm:ss".
 * </p>
 *
 * <p>It extends `StdSerializer<LocalDateTime>` to provide custom serialization logic.</p>
 */
public class CustomDateSerializer extends StdSerializer<LocalDateTime> {

    public CustomDateSerializer() {
        this(null);
    }

    public CustomDateSerializer(Class<LocalDateTime> clazz) {
        super(clazz);
    }

    @Override
    public void serialize(LocalDateTime value,
                          JsonGenerator gen,
                          SerializerProvider arg2) throws IOException {

        gen.writeString(value.format(DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss")));
    }
}
