package users_classes;

import java.util.Scanner;
/**
 *
 *
 *
 * @author Huy Ng
 * @version 1.0
 * @since   7/2021
 */


public class Account {
    private String name;
    private String email;
    private String password;
    private int type;
    private boolean lock;

    // ------- constructor ---------
    public Account() {

    }

    public Account(String name, String email, String password, int type, boolean lock) {
        super();
        this.name = name;
        this.email = email;
        this.password = password;
        this.type = type;
        this.lock = lock;
    }

    // --------- getter and setter----------
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public int getType() {
        return type;
    }
    public void setType(int type) {
        this.type = type;
    }
    public boolean isLock() {
        return lock;
    }
    public void setLock(boolean lock) {
        this.lock = lock;
    }

    // ---------- change password function -----------
    public void changePassword() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap mat khau cu: ");
        String oldPassword = sc.nextLine();

        if(!oldPassword.equalsIgnoreCase(this.password)){
            System.out.println("Ban da nhap sai mat khau cu!");
        }

        if(oldPassword.equalsIgnoreCase(this.password)){
            System.out.println("Nhap mat khau moi: ");
            String newPassword = sc.nextLine();
            System.out.println("Xac nhan lai mat khau moi: ");
            String passwordConfirm = sc.nextLine();

            if(!newPassword.equalsIgnoreCase(passwordConfirm)){
                System.out.println("Xac nhan sai mat khau!");
            }

            if(newPassword.equalsIgnoreCase(passwordConfirm)){
                this.password = newPassword;
                System.out.println("Doi mat khau thanh cong");
            }
        }

    }
}