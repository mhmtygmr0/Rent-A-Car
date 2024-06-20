package business;

import dao.BookDao;
import entity.Book;
import entity.Brand;

import java.util.ArrayList;

public class BookManager {
    private final BookDao bookDao;

    public BookManager() {
        this.bookDao = new BookDao();
    }

    public ArrayList<Book> findAll() {
        return this.bookDao.findAll();
    }

    public boolean save(Book book) {
        return this.bookDao.save(book);
    }
}
