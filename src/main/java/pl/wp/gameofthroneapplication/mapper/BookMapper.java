package pl.wp.gameofthroneapplication.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import pl.wp.gameofthroneapplication.model.Book;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BookMapper {

    private static final ObjectMapper mapper = createObjectMapper();

    private static ObjectMapper createObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return mapper;
    }

    public static Book jsonToBook(String json) throws JsonProcessingException {
        JsonNode rootNode = mapper.readTree(json);

        String name = rootNode.get("name").asText();
        String isbn = rootNode.get("isbn").asText();
        JsonNode authorsNode = rootNode.get("authors");
        List<String> authors = new ArrayList<>();
        if (authorsNode.isArray()) {
            for (JsonNode node : authorsNode) {
                authors.add(node.toString());
            }
        }
        Integer numberOfPages = rootNode.get("numberOfPages").asInt();
        String publisher = rootNode.get("publisher").asText();
        String countryOfOrigin = rootNode.get("country").asText();
        LocalDateTime releaseDate = LocalDateTime.parse(rootNode.get("released").asText());
        JsonNode charactersNode = rootNode.get("characters");
        List<String> characters = new ArrayList<>();
        if (charactersNode.isArray()) {
            for (JsonNode node : charactersNode) {
                characters.add(node.toString());
            }
        }
        return new Book(name, isbn, authors, numberOfPages, publisher, countryOfOrigin, releaseDate, characters);
    }

    public static List<Book> jsonToListOfBook(String json) throws JsonProcessingException {
        JsonNode rootNode = mapper.readTree(json);
        List<Book> books = new ArrayList<>();
        if (rootNode.isArray()) {
            for (JsonNode node : rootNode) {
                books.add(jsonToBook(node.toString()));
            }
        }
        return books;
    }
}