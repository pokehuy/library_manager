package book_class;

import data.CanShowBooksList;

import java.util.ArrayList;
/**
 * Process main function / Ham khoi tao chuong trinh
 *
 *
 * @author Huy Ng
 * @version 1.0
 * @since   7/2021
 */

public class Book{
    private String name;
    private int codeBook;
    private int sum;
    private int storage;
    private ArrayList<BorrowBook> borrowBook = new ArrayList<BorrowBook>();

    //----------- constructor --------------
    public Book() {
    }

    public Book(String name, int codeBook, int sum, int storage, ArrayList<BorrowBook> borrowBook) {
        super();
        this.name = name;
        this.codeBook = codeBook;
        this.sum = sum;
        this.storage = storage;
        this.borrowBook = borrowBook;
    }


    //----------- getter and setter ------------
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getCodeBook() {
        return codeBook;
    }
    public void setCodeBook(int codeBook) {
        this.codeBook = codeBook;
    }
    public int getSum() {
        return sum;
    }
    public void setSum(int sum) {
        this.sum = sum;
    }
    public int getStorage() {
        return storage;
    }
    public void setStorage(int storage) {
        this.storage = storage;
    }
    public ArrayList<BorrowBook> getBorrowBook() {
        return borrowBook;
    }
    public void setBorrowBook(ArrayList<BorrowBook> borrowBook) {
        this.borrowBook = borrowBook;
    }

}

