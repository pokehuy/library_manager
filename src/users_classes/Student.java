package users_classes;

import book_class.Book;
import book_class.BorrowBook;
import data.CanShowBooksList;

import java.util.Scanner;
import java.time.LocalDateTime;
/**
 * Process main function / Ham khoi tao chuong trinh
 *
 *
 * @author Huy Ng
 * @version 1.1
 * @since   7/2021
 * version 1.1 sửa lỗi hiển thị hàm trả sách, sửa lỗi trường hợp 1 người mượn 1 sách 2 lần khác nhau nhưng số lượng sách không gộp
 */

//lớp Student kế thừa lớp Account
public class Student extends Account {
    private CanShowBooksList booksList;

    //biến option dùng trong tính năng đăng nhập lại ở hàm main trong lớp Process
    private int option;

    public void menuStudent(LocalDateTime currentDate) {
        //biến notOut dùng trong trường hợp thoát vòng lặp while để thoát menu
        boolean notOut = true;
        do {
            System.out.println();
            System.out.println("***** Current Date: " + currentDate + " *****");
            System.out.println();

            System.out.println("-------Student Menu-------");
            System.out.println("1.Doi mat khau\n2.Danh sach sach trong kho\n3.Muon sach\n4.Tra sach\n5.Danh sach sach dang muon\n6.Doi tai khoan\n7.Thoat chuong trinh");
            System.out.println("Nhap lua chon: ");
            Scanner sc = new Scanner(System.in);
            option = Integer.parseInt(sc.nextLine());

            switch(option) {
                case 1:
                    changePassword();
                    break;
                case 2:
                    booksList.showBooksList();
                    break;
                case 3:
                    borrowBook(currentDate);
                    break;
                case 4:
                    giveBookBack();
                    break;
                case 5:
                    borrowedBook();
                    break;
                case 6:
                    notOut = false;
                    break;
                case 7:
                    notOut = false;
                    break;
            }
        } while (notOut);
    }

    //-------- constructor -----------
    public Student() {
    }

    public Student(String name, String email, String password, int type, boolean lock, CanShowBooksList booksList) {
        super(name, email, password, type, lock);
        this.booksList = booksList;
    }


    //------ getter and setter -------------
    public int getOption() {
        return option;
    }


    // hàm mượn sách
    private void borrowBook(LocalDateTime currentDate) {
        // biến check dùng để in thông báo khi vòng for không tìm thấy giá trị phù hợp
        boolean check = false;
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap ma sach: ");
        int codeBook = Integer.parseInt(sc.nextLine());

        // vòng for chạy ArrayList booksList tìm mã sách
        for(Book book : booksList.getBooksList()) {

            if(book.getCodeBook() == codeBook && book.getStorage() > 0) { // tìm thấy sách cần mượn
                check = true; // check = true biểu thị vòng for tìm thấy kết quả
                System.out.println("Ten_sach: " + book.getName() + " So_luong: " + book.getStorage());
                System.out.println("Nhap so luong sach can muon: ");
                int num = Integer.parseInt(sc.nextLine()); // số lượng sách cần mượn

                if(num <= book.getStorage()) {
                    BorrowBook borrow = new BorrowBook(); // tạo object lưu thông tin người mượn, số lượng mượn
                    book.setStorage(book.getStorage() - num); // thay đổi số sách tồn kho sau khi mượn
                    borrow.setStudentName(getName());
                    borrow.setNumber(num);
                    borrow.setBorrowDate(currentDate);
                    book.getBorrowBook().add(borrow); // lưu object chứa thông tin người mượn vào ArrayList borrowBook ở vị trí sách đang tạo hành động mượn của ArrayList booksList
                    System.out.println("Ban da muon sach " + book.getName() + " so luong " + num);
                } else {
                    System.out.println("So sach muon vuot qua sach trong kho!");
                }

            }
        }

        if(check == false) {
            System.out.println("Sach khong co san, moi ban quay lai sau!");
        }
    }

    // hàm trả sách
    private void giveBookBack() {
        boolean check = false;

        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap ma sach muon tra: ");
        int codeBook = Integer.parseInt(sc.nextLine());

        for(Book book : booksList.getBooksList()) { // duyệt sách trong booksList
            if(book.getCodeBook() == codeBook) { //tìm thấy mã sách trùng
                for(int j = 0; j < book.getBorrowBook().size(); j++) { // duyệt danh sách người mượn cuốn sách đó
                    BorrowBook borrowBook = book.getBorrowBook().get(j); // tạo object người mượn sách với từng phần tử của danh sách người mượn
                    if(borrowBook.getStudentName() == getName() && borrowBook.getNumber() > 0) { //duyệt nếu người mượn là người đang đăng nhập
                        System.out.println(book.getName() + " so_luong: " + borrowBook.getNumber() + "\nSo luong sach muon tra: ");
                        int num = Integer.parseInt(sc.nextLine());
                        check = true;

                        if(num < borrowBook.getNumber()) { // sách trả nhỏ hơn số sách mượn
                            borrowBook.setNumber(borrowBook.getNumber() - num);
                            book.setStorage(book.getStorage() + num);
                            System.out.println("Ban da tra " + num + " sach " + book.getName());

                        } else if( num == borrowBook.getNumber()) { // sách trả hết, xoá object người mượn trong arraylist (sách mượn = 0 nên không cần lưu)
                            borrowBook.setNumber(borrowBook.getNumber() - num);
                            book.setStorage(book.getStorage() + num);
                            book.getBorrowBook().remove(borrowBook);
                            System.out.println("Ban da tra " + num + " sach " + book.getName());

                        } else if(num > borrowBook.getNumber()) {
                            System.out.println("So luong sach tra lon hon so luong sach da muon");
                        }

                    }
                }
            }
        }

        if(check == false) {
            System.out.println("Ban chua muon sach nay");
        }
    }
    // hàm hiện danh sách sách đang mượn của người đăng nhập
    private void borrowedBook() {

        System.out.println("------DS Sach Dang Muon------");

        for(Book book : booksList.getBooksList()) { // duyệt các sách trong ArrayList booksList

            if(book.getBorrowBook() != null) {

                for(int j = 0; j < book.getBorrowBook().size(); j++) { // duyệt danh sách tên và số lượng sách mượn của từng sách
                    BorrowBook borrowBook = book.getBorrowBook().get(j);

                    if(borrowBook.getStudentName() == getName()) {  // chỉ hiện sách đang được mượn bởi người đang đăng nhập, name trỏ về object student trong class Process
                        System.out.println(book.getName() + " so_luong: " + borrowBook.getNumber());
                    }

                }

            }

        }
    }

}