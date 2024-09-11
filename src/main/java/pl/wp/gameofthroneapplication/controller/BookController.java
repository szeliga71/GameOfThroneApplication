package pl.wp.gameofthroneapplication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.wp.gameofthroneapplication.connection.BookAPIHandler;
import pl.wp.gameofthroneapplication.connection.CharacterAPIHandler;
import pl.wp.gameofthroneapplication.model.Book;

@Controller
public class BookController {

    private final BookAPIHandler bookAPIHandler = new BookAPIHandler();
    private final CharacterAPIHandler characterAPIHandler = new CharacterAPIHandler();

    @RequestMapping("/books")
    public String getBookList(Model model) {
        model.addAttribute("bookList", bookAPIHandler.getAllBooks());
        return "booksPage";
    }

    @RequestMapping("/book")
    public String getSingleBookXX(Model model, @RequestParam Integer id) {
        model.addAttribute("book", bookAPIHandler.getSingleBook(id));
        return "book";
    }

    @RequestMapping("/book/{id}")
    public String getSingleBook(Model model, @PathVariable("id") Integer id) {

        // Test obslugi bledow
        if (id == null) {
            return "error"; // Zwróć nazwę widoku błędu lub przekieruj na odpowiednią stronę
        }
        Book book = bookAPIHandler.getSingleBook(id);
        if (book == null) {
            return "not-found"; // Zwróć nazwę widoku "not found" lub inny obsługiwany błąd
        }
        model.addAttribute("book", book);
        return "book"; // Nazwa widoku strony książki
    }

    @PostMapping("/book")
    public String getSpecificCharacter(Model model, @RequestParam("URIAdress") String URIAdress) {
        URIAdress = URIAdress.replace("\"", "");
        int lastIndex = URIAdress.lastIndexOf("/");
        String id = URIAdress.substring(lastIndex + 1);
        model.addAttribute("character", characterAPIHandler.getCharacterById(id));
        return "character";
    }
}