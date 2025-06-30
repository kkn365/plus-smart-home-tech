package ru.yandex.practicum.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.avro.io.BinaryEncoder;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.specific.SpecificDatumWriter;
import org.apache.avro.specific.SpecificRecordBase;
import org.springframework.core.serializer.Serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HexFormat;

@Slf4j
public class EventAvroSerializer implements Serializer<SpecificRecordBase> {

    private static final HexFormat hexFormat = HexFormat.ofDelimiter(":");

    @Override
    public byte[] serialize(String topic, SpecificRecordBase event) {
        if(event == null) {
            return null;
        }

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {

            DatumWriter<SpecificRecordBase> datumWriter = new SpecificDatumWriter<>(event.getSchema());
            BinaryEncoder encoder = EncoderFactory.get().binaryEncoder(outputStream, null);
            datumWriter.write(event, encoder);
            encoder.flush();
            byte[] bytes = outputStream.toByteArray();

            log.info("Данные успешно сериализованы в формат Avro:\n{}", hexFormat.formatHex(bytes));

            return bytes;

        } catch (IOException e) {
            throw new SerializationException("Ошибка сериализации экземпляра Event", e);
        }
    }


    @Override
    public void serialize(SpecificRecordBase object, OutputStream outputStream) throws IOException {

    }
}
