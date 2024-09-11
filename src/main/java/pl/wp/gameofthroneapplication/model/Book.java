package pl.wp.gameofthroneapplication.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDateTime;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Book {

    private String name;
    private String isbn;
    private List<String> authors;
    private Integer numbersOfPages;
    private String publisher;
    private String countryOfOrigin;
    private LocalDateTime releaseDate;
    private List<String> characters;


    public Book(String name, String isbn, List<String> authors, Integer numbersOfPages, String publisher, String countryOfOrigin, LocalDateTime releaseDate, List<String> characters) {
        this.name = name;
        this.isbn = isbn;
        this.authors = authors;
        this.numbersOfPages = numbersOfPages;
        this.publisher = publisher;
        this.countryOfOrigin = countryOfOrigin;
        this.releaseDate = releaseDate;
        this.characters = characters;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthor(List<String> authors) {
        this.authors = authors;
    }

    public Integer getNumbersOfPages() {
        return numbersOfPages;
    }

    public void setNumbersOfPages(Integer numbersOfPages) {
        this.numbersOfPages = numbersOfPages;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getCountryOfOrigin() {
        return countryOfOrigin;
    }

    public void setCountryOfOrigin(String countryOfOrigin) {
        this.countryOfOrigin = countryOfOrigin;
    }

    public LocalDateTime getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDateTime releaseDate) {
        this.releaseDate = releaseDate;
    }

    public List<String> getCharacters() {
        return characters;
    }

    public void setCharacters(List<String> characters) {
        this.characters = characters;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", isbn='" + isbn + '\'' +
                ", authors='" + authors + '\'' +
                ", numbersOfPages=" + numbersOfPages +
                ", publisher='" + publisher + '\'' +
                ", countryOfOrigin='" + countryOfOrigin + '\'' +
                ", releaseDate=" + releaseDate +
                ", characters=" + characters +
                '}';
    }
}