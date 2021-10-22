
import data.Library;
import users_classes.Account;
import users_classes.AdminAccount;
import users_classes.Librarian;
import users_classes.Student;

import java.time.LocalDateTime;

/**
 * Process main function / Ham khoi tao chuong trinh
 *
 *
 * @author Huy Ng
 * @version 1.0
 * @since   7/2021
 */

public class Main {
    public static void main(String[] args) {
        boolean out = false;
        Library library = new Library();
        LocalDateTime currentDate = LocalDateTime.now();

        library.createAccountsList();
        library.createBooksList();

        while(!out) {
            Account account = library.Login(currentDate);
            if(account != null) {

                if(account.getType() == 1) {
                    AdminAccount admin = new AdminAccount(account.getName(),account.getEmail(),account.getPassword(),account.getType(),account.isLock());
                    admin.menuAdmin(library, currentDate);
                    if(admin.getOption() == 9) {
                        out = true;
                    }
                }

                if(account.getType() == 2) { // thủ thư
                    Librarian librarian = new Librarian(account.getName(),account.getEmail(),account.getPassword(),account.getType(),account.isLock(),library);
                    librarian.menuLibrarian(currentDate);
                    if(librarian.getDateChanged() != null) {
                        currentDate = librarian.getDateChanged();
                    }
                    if(librarian.getOption() == 8) {
                        out = true;
                    }
                }

                if(account.getType() == 3) { // sinh viên
                    Student student = new Student(account.getName(),account.getEmail(),account.getPassword(),account.getType(),account.isLock(),library);
                    student.menuStudent(currentDate);
                    if(student.getOption() == 7) {
                        out = true;
                    }
                }

            }

        }
        System.out.print("Ban da thoat chuong trinh!");
    }
}


