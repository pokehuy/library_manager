package users_classes;

import book_class.Book;
import book_class.BorrowBook;
import data.CanShowBooksList;

import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
/**
 * Process main function / Ham khoi tao chuong trinh
 *
 *
 * @author Huy Ng
 * @version 1.0
 * @since   7/2021
 */
public class Librarian extends Account {
    private CanShowBooksList booksList;
    private LocalDateTime dateChanged;
    private int option; // biến option dùng cho chức năng đăng nhập lại

    public void menuLibrarian(LocalDateTime currentDate) {
        boolean notOut = true; // biến notOut thoát menu
        do {
            System.out.println();
            System.out.println("***** Current Date: " + currentDate + " *****");
            System.out.println();

            System.out.println("-------Librarian Menu-------");
            System.out.println("1.Doi mat khau\n2.Them sach\n3.Danh sach sach trong kho\n4.Danh sach sach dang duoc muon\n5.Them ngay vao ngay hien tai\n6.Danh sach sinh vien tra muon\n7.Doi tai khoan\n8.Thoat chuong trinh");
            System.out.println("Nhap lua chon: ");
            Scanner sc = new Scanner(System.in);
            option = Integer.parseInt(sc.nextLine());

            switch(option) {
                case 1:
                    changePassword();
                    break;
                case 2:
                    addBook();
                    break;
                case 3:
                    showBooksList();
                    break;
                case 4:
                    showBooksBorrowList();
                    break;
                case 5:
                    currentDate = addDays(currentDate);
                    break;
                case 6:
                    giveBackLateList(currentDate);
                    break;
                case 7:
                    notOut = false;
                    break;
                case 8:
                    notOut = false;
                    break;
            }
        } while (notOut);
    }

    // ------- constructor ------------
    public Librarian() {
    }

    public Librarian(String name, String email, String password, int type, boolean lock, CanShowBooksList booksList) {
        super(name, email, password, type, lock);
        this.booksList = booksList;
    }


    // ------ getter and setter ----------
    public LocalDateTime getDateChanged() {
        return dateChanged;
    }
    public int getOption() { return option; }


    // hàm tính tiền trả muộn
    private long calculateFine(LocalDateTime borrowDate , LocalDateTime currentDate, int quantity) {
        long daysLate = ChronoUnit.DAYS.between(borrowDate, currentDate);
        if(daysLate > 7 && daysLate < 10) {
            return 10000*quantity;
        }
        if (daysLate >= 10 && daysLate < 14) {
            return 20000*quantity;
        }
        if(daysLate >= 14) {
            return (30000 + (daysLate - 14)*3000)*quantity;
        }
        return 0;
    }


    private LocalDateTime addDays(LocalDateTime currentDate) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap vao so ngay them: ");
        int days = Integer.parseInt(sc.nextLine());
        this.dateChanged = currentDate.plusDays(days);
        return this.dateChanged;
    }


    private void giveBackLateList(LocalDateTime currentDate) {
        System.out.println("-----Danh sach tra sach tre-----");
        for(Book book : booksList.getBooksList()) {
            for(int j = 0; j < book.getBorrowBook().size(); j++) {
                BorrowBook borrow = book.getBorrowBook().get(j);
                System.out.println("Nguoi_muon: " + borrow.getStudentName() + " Ten_sach: " + book.getName() + " So_luong: " + borrow.getNumber() + " Ngay_muon: " + borrow.getBorrowDate() + " So_tien_phai_nop: " + calculateFine(borrow.getBorrowDate(), currentDate, borrow.getNumber()));
            }
        }
    }


    private void addBook() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap vao ma sach: ");
        int codeBook = Integer.parseInt(sc.nextLine()); // chống trôi lệnh
        boolean check = false; // biến check dùng để biết được trường hợp thêm sách mới hay thêm số lượng sách cũ

        for(Book book : booksList.getBooksList()) { // duyệt tìm sách có mã nhập vào xem có trùng với danh sách không

            if(book.getCodeBook() == codeBook) {
                System.out.println("Sach da duoc them tu truoc!\nTen sach: " + book.getName() + "\nSo luong sach muon them: ");
                int addNumber = sc.nextInt();
                book.setSum(book.getSum() + addNumber);
                book.setStorage(book.getStorage() + addNumber);
                check = true; // có trùng, chỉ thêm số lượng sách, không xét trường hợp sau
                System.out.println("Them sach thanh cong!\nTen sach: " + book.getName() + "\nTong so : " + book.getSum());
            }

        }

        if(check != true) { // không trùng, xét trường hợp thêm sách mới
            Book book = new Book();

            book.setCodeBook(codeBook);
            System.out.print("Ten sach: ");
            book.setName(sc.nextLine());
            System.out.println("So luong sach: ");
            book.setSum(Integer.parseInt(sc.nextLine()));
            book.setStorage(book.getSum()); // tổng số sách = sách tồn kho với sách mới


            booksList.getBooksList().add(book); // lưu sách mới vào booksList
            System.out.println("Them sach thanh cong!\nTen sach: " + book.getName() + "\nTong so : " + book.getSum());
        }
    }


    //hàm xuất danh sách sách trong thư viện
    private void showBooksList() {
        System.out.println("-----DS Sach trong thu vien------");
        for(Book book : booksList.getBooksList()) {
            System.out.println("Ma_so: " + book.getCodeBook() + " ,Ten: " + book.getName() + " ,So_luong " + book.getSum() + " ,Kho: " + book.getStorage() + " ,Muon: " + (book.getSum() - book.getStorage()));
        }
        System.out.println("================================");
    }


    // hàm xuất danh sách sách đang mượn với tên người mượn và số lượng mượn
    private void showBooksBorrowList() {
        System.out.println("------Danh sach muon sach------");
        for(Book book : booksList.getBooksList()) { // duyệt từng sách
            System.out.print("Ten_sach: " + book.getName());
            if(book.getBorrowBook() != null) {
                for(int j = 0; j < book.getBorrowBook().size(); j++) { // duyệt từng người mượn và số lượng ở mỗi sách
                    BorrowBook borrows = book.getBorrowBook().get(j);
                    System.out.println(" So_luong: " + borrows.getNumber() + " Ten_SV: " + borrows.getStudentName());
                }
            }
        }
        System.out.println();
    }
}
