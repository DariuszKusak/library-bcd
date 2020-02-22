package com.library.bcd.librarybcd.service;

import com.library.bcd.librarybcd.entity.Book;
import com.library.bcd.librarybcd.entity.User;
import com.library.bcd.librarybcd.entity.User2Book;
import com.library.bcd.librarybcd.repository.BookRepository;
import com.library.bcd.librarybcd.repository.User2BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class User2BookService {

    private final User2BookRepository user2BookRepository;
    private final BookRepository bookRepository;

    @Autowired
    public User2BookService(User2BookRepository user2BookRepository, BookRepository bookRepository) {
        this.user2BookRepository = user2BookRepository;
        this.bookRepository = bookRepository;
    }

    public List<Book> getBooksForUser(User user) {
        List<User2Book> user2Books = user2BookRepository.findAllByUser(user);
        List<Book> books = new ArrayList<>();
        for (User2Book u2b : user2Books) {
            books.add(u2b.getBook());
        }
        return books;
    }

    public User2Book borrowBookForUser(User user, Book book) {
        System.out.println(user);
        System.out.println(book);
        User2Book u2b = new User2Book(0, book, user);
        user2BookRepository.save(u2b);
        return u2b;
    }

    public void updateUser2Books(User oldUser, User updatedUser) {
        List<User2Book> allByUser = user2BookRepository.findAllByUser(oldUser);
        for (User2Book u2b : allByUser) {
            u2b.setUser(updatedUser);
            user2BookRepository.saveAndFlush(u2b);
        }
    }

    public void returnBook(User user, Book book) {
        user2BookRepository.deleteByUserAndBook(user, book);
        book.setAmount(book.getAmount() + 1);
        book.setAvailable(true);
        bookRepository.save(book);
    }

}
