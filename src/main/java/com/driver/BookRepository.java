package com.driver;
import java.util.*;

import org.springframework.stereotype.Repository;

@Repository
public class BookRepository {
    static int id = 0;
    Map<Integer, Book> idMap = new HashMap<>();
    Map<String, List<Book>> authorMap = new HashMap<>();
    Map<String, List<Book>> genreMap = new HashMap<>();

    public BookRepository(){

    }

    public Book save(Book book){
        id++;
        book.setId(id);
        idMap.put(book.getId(), book);
        if(authorMap.containsKey(book.getAuthor()))
            authorMap.get(book.getAuthor()).add(book);
        else {
            authorMap.put(book.getAuthor(), new ArrayList<>());
            authorMap.get(book.getAuthor()).add(book);
        }
        if(genreMap.containsKey(book.getGenre()))
            genreMap.get(book.getGenre()).add(book);
        else {
            genreMap.put(book.getGenre(), new ArrayList<>());
            genreMap.get(book.getGenre()).add(book);
        }
        return book;
    }

    public Book findBookById(int id){
        return idMap.get(id);
    }

    public List<Book> findAll(){
        return new ArrayList<>(idMap.values());
    }

    public void deleteBookById(int id){
        Book book = idMap.get(id);
        idMap.remove(book.getId());
        authorMap.get(book.getAuthor()).remove(book);
        genreMap.get(book.getGenre()).remove(book);
    }

    public void deleteAll(){
        idMap.clear();
        authorMap.clear();
        genreMap.clear();
    }

    public List<Book> findBooksByAuthor(String author){
        return authorMap.get(author);
    }

    public List<Book> findBooksByGenre(String genre){
        return genreMap.get(genre);
    }
}