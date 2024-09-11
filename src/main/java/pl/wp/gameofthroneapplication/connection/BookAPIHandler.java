package pl.wp.gameofthroneapplication.connection;

import pl.wp.gameofthroneapplication.mapper.BookMapper;
import pl.wp.gameofthroneapplication.model.Book;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class BookAPIHandler {

    private final HttpClient client = HttpClient.newHttpClient();
    private final String BOOK_API_URL = "https://www.anapioficeandfire.com/api/books/";

    public Book getSingleBook(Integer numberOfBook) {
        try {
            HttpRequest request = HttpRequest.newBuilder(new URI(BOOK_API_URL + numberOfBook)).GET().build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                return BookMapper.jsonToBook(response.body());
            } else {
                return null;
            }
        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Book> getAllBooks() {
        try {
            HttpRequest request = HttpRequest.newBuilder(new URI(BOOK_API_URL + "?pageSize=15")).GET().build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return BookMapper.jsonToListOfBook(response.body());
        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}