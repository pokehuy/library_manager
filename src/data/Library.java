package data;


import java.util.ArrayList;
import java.util.Scanner;

import book_class.Book;
import book_class.BorrowBook;
import users_classes.Account;

import java.time.LocalDateTime;
/**
 * Process main function / Ham khoi tao chuong trinh
 *
 *
 * @author Huy Ng
 * @version 1.0
 * @since   7/2021
 */
public class Library implements CanShowBooksList, CanShowAccountsList {
    private ArrayList<Account> accountsList = new ArrayList<Account>();
    private ArrayList<Book> booksList = new ArrayList<Book>();


    // --------- constructor--------------
    public Library() {
    }


    // -------- getter and setter -----------
    @Override
    public ArrayList<Account> getAccountsList() {
        return accountsList;
    }
    @Override
    public ArrayList<Book> getBooksList() {
        return booksList;
    }


    // -------- create sample accounts function-------------
    public ArrayList<Account> createAccountsList() {
        Account account = new Account("Huy Ng","adm@gg.com","123",1,false);
        accountsList.add(account);
        Account account1 = new Account("Libra Ng","lib@gg.com","123",2,false);
        accountsList.add(account1);
        Account account2 = new Account("Stu1 Ng","st1@gg.com","123",3,false);
        accountsList.add(account2);
        Account account3 = new Account("Stu3 Ng","st2@gg.com","123",3,false);
        accountsList.add(account3);
        return accountsList;
    }


    // -------- create sample books function ---------
    public ArrayList<Book> createBooksList() {
        Book book = new Book("Lap trinh C++",1,10,10,new ArrayList<BorrowBook>());
        booksList.add(book);
        Book book1 = new Book("Lap trinh Java",2,9,9,new ArrayList<BorrowBook>());
        booksList.add(book1);
        return booksList;
    }


    public Account Login(LocalDateTime currentDate) {
        System.out.println("***** Current Date: " + currentDate + " *****");

        Scanner sc = new Scanner(System.in);
        System.out.println("-----Login------");
        System.out.println("Nhap email: ");
        String email = sc.nextLine();
        System.out.println("Nhap mat khau: ");
        String password = sc.nextLine();

        for(Account account : this.accountsList) {

            if(account.getEmail().equals(email)) {
                if(account.isLock() == false) {
                    if(account.getPassword().equals(password)) {
                        System.out.println("Dang nhap thanh cong"); return account;
                    }
                    System.out.println("Nhap sai mat khau!"); return null;
                }
                System.out.println("Tai khoan da bi khoa"); return null;
            }
        }
        System.out.println("Tai khoan khong ton tai!"); return null;
    }

    @Override
    public void showBooksList() {
        System.out.println("-----DS Sach trong thu vien------");
        for(Book book : this.booksList) {
            System.out.println("Ma_so: " + book.getCodeBook() + " ,Ten: " + book.getName() + " ,So luong " + book.getSum() + " ,Kho: " + book.getStorage() + " ,Muon: " + (book.getSum() - book.getStorage()));
        }
        System.out.println("=================================");
    }

}