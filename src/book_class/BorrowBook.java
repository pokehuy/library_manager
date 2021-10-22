package book_class;

import java.time.LocalDateTime;
/**
 * Process main function / Ham khoi tao chuong trinh
 *
 *
 * @author Huy Ng
 * @version 1.0
 * @since   7/2021
 */

public class BorrowBook {
    private String studentName;
    private int number;
    private LocalDateTime borrowDate;

    // ------- constructor ---------
    public BorrowBook() {
    }

    //------- getter and setter ----------
    public String getStudentName() {
        return studentName;
    }
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }
    public LocalDateTime getBorrowDate() {
        return borrowDate;
    }
    public void setBorrowDate(LocalDateTime borrowDate) {
        this.borrowDate = borrowDate;
    }

}



