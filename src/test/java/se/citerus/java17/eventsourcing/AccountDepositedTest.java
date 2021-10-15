package se.citerus.java17.eventsourcing;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class AccountDepositedTest {
    private final static String json = """
                {
                    "version":1,
                    "timestamp":"2021-10-15T13:50:41.816981Z",
                    "id":"09d191cb-7882-4b57-b0c8-bfc9b72a8a53",
                    "balance":100
                }
                """.replaceAll("[\\n\\t ]", "");;
    @Test
    void canSerialize() throws JsonProcessingException {
        AccountDeposited accountDeposited = new AccountDeposited(Version.of(1), Instant.parse("2021-10-15T13:50:41.816981Z"), AccountId.of(UUID.fromString("09d191cb-7882-4b57-b0c8-bfc9b72a8a53")), Balance.of(100));
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        String actual = mapper.writeValueAsString(accountDeposited);
        assertEquals(json, actual);
    }

    @Test
    void canDeserialize() throws JsonProcessingException {

        AccountDeposited accountDeposited = new ObjectMapper().registerModule(new JavaTimeModule()).readValue(json, AccountDeposited.class);
        assertEquals(100, accountDeposited.balance().value());
        assertEquals(1, accountDeposited.version().value());
        assertEquals(Instant.parse("2021-10-15T13:50:41.816981Z"), accountDeposited.timestamp());
        assertEquals(AccountId.of(UUID.fromString("09d191cb-7882-4b57-b0c8-bfc9b72a8a53")), accountDeposited.id());
    }
}