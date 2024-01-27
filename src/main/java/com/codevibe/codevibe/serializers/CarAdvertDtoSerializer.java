package com.codevibe.codevibe.serializers;

import java.io.IOException;

import com.codevibe.codevibe.dto.CarAdvertDto;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class CarAdvertDtoSerializer extends StdSerializer<CarAdvertDto>
{
    public CarAdvertDtoSerializer() {
        this(null);
    }

    public CarAdvertDtoSerializer(Class<CarAdvertDto> t) {
        super(t);
    }

    @Override
    public void serialize(CarAdvertDto value, JsonGenerator generator, SerializerProvider provider) throws IOException
    {
        generator.writeStartObject();
        generator.writeObjectField("id", value.getId());
        generator.writeObjectField("title", value.getTitle());
        generator.writeObjectField("fuelType", value.getFuelType());
        generator.writeObjectField("price", value.getPrice());
        generator.writeObjectField("isNew", value.getIsNew());

        if (!Boolean.TRUE.equals(value.getIsNew())) {
            generator.writeObjectField("mileage", value.getMileage());
            generator.writeObjectField("firstRegistration", value.getFirstRegistration());
        }

        generator.writeEndObject();
    }
    
}
