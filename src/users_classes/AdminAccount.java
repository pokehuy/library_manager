package users_classes;
import data.CanShowAccountsList;

import java.util.Scanner;
import java.time.LocalDateTime;
/**
 *
 *
 *
 * @author Huy Ng
 * @version 1.1
 * @since   7/2021
 *
 */
public class AdminAccount extends Account {
    //private CanShowAccountsList accountsList;
    private boolean isLock;
    private int typeAccount;
    private int option;

    public void menuAdmin(CanShowAccountsList accountsList, LocalDateTime currentDate) {
        boolean notOut = true;
        do {
            System.out.println();
            System.out.println("***** Current Date: " + currentDate + " *****");
            System.out.println();

            System.out.println("-------Admin Menu-------");
            System.out.println("1.Doi mat khau\n2.Tao tai khoan thu thu\n3.Tao tai khoan sinh vien\n4.Danh sach tai khoan\n5.Reset mat khau\n6.Lock tai khoan\n7.Unlock tai khoan\n8.Doi tai khoan\n9.Thoat chuong trinh");
            System.out.println("Nhap lua chon: ");
            Scanner sc = new Scanner(System.in);
            option = Integer.parseInt(sc.nextLine());

            switch(option) {
                case 1:
                    changePassword();
                    break;
                case 2:
                    typeAccount = 2;
                    createAccount(accountsList, typeAccount);
                    break;
                case 3:
                    typeAccount = 3;
                    createAccount(accountsList, typeAccount);
                    break;
                case 4:
                    showAccountsList(accountsList);
                    break;
                case 5:
                    resetPassword(accountsList);
                    break;
                case 6:
                    isLock = false;
                    lockAndUnlockAccount(accountsList, isLock, "lock");
                    break;
                case 7:
                    isLock = true;
                    lockAndUnlockAccount(accountsList, isLock, "unlock");
                    break;
                case 8:
                    notOut = false;
                    break;
                case 9:
                    notOut = false;
                    break;
            }
        } while (notOut);
    }


    // -------- constructor --------------
    public AdminAccount() {
    }

    public AdminAccount(String name, String email, String password, int type, boolean lock) {
        super(name, email, password, type, lock);
    }


    // ------ getter and setter --------
    public int getOption() {
        return option;
    }


    // h??m ki???m tra xem account c???n t??m c?? xu???t hi???n ??? trong ArrayList accountsList hay ko, d??ng cho c??c h??m ??? d?????i, tr??? v??? email nh???p v??o ho???c null n???u ???? c?? acc trong list
    private String checkAccount(CanShowAccountsList accountsList) {
        System.out.println("Nhap tai khoan email: ");
        Scanner sc = new Scanner(System.in);
        String email = sc.nextLine();

        for(Account account : accountsList.getAccountsList()) {

            if(account.getEmail().equalsIgnoreCase(email)) {
                System.out.println("Tai khoan da ton tai!");
                return null;
            }

        }
        return email;
    }

    private void showAccountsList(CanShowAccountsList accountsList) {
        for(Account account : accountsList.getAccountsList()) {
            System.out.println("Ten: " + account.getName() + " ,Email: " + account.getEmail() + " ,Password: " + account.getPassword() + " ,Type: " + (account.getType() == 1?"Admin":(account.getType()==2?"Librarian":"Student")) + " ,Tinh trang: " + (account.isLock() == true?"Locked":"Active"));
        }
    }

    // t???o account nh???n accountsList l?? parameter ????? duy???t trong h??m
    private void createAccount(CanShowAccountsList accountsList, int a) {

        Account account = new Account();
        Scanner sc = new Scanner(System.in);
        account.setEmail(checkAccount(accountsList));// check account, n???u c?? tr??? v??? null, n???u ko tr??? v??? email

        if(account.getEmail() != null) { // tr?????ng h???p tr??? v??? email (email nh???p ko c?? trong list)

            System.out.println("Ten: ");
            account.setName(sc.nextLine());
            System.out.println("Password: ");
            account.setPassword(sc.nextLine());

            account.setType(a);
            account.setLock(false);

            accountsList.getAccountsList().add(account);
            System.out.println("Tao tai khoan thanh cong!");
        }
    }

    // h??m ?????t l???i password
    private void resetPassword(CanShowAccountsList accountsList) {
        boolean check = false; // bi???n check d??ng trong tr?????ng h???p v??ng for kh??ng t??m th???y gi?? tr???
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap email muon reset mat khau: ");
        String email = sc.nextLine();

        for(Account account : accountsList.getAccountsList()) {

            if(account.getEmail().equalsIgnoreCase(email)) {
                check = true; // t??m th???y email c???n reset password
                System.out.println("Nhap mat khau moi: ");
                String newPass = sc.nextLine();
                System.out.println("Xac nhan doi mat khau? Y/N Yes bam 1, No bam 2.");
                int accept = sc.nextInt();

                if(accept == 1) {
                    account.setPassword(newPass);
                    System.out.println("Reset mat khau cua tai khoan " + account.getEmail() + " thanh cong!");
                } else if(accept == 2) {
                    System.out.println("Huy doi mat khau");
                }

            }

        }

        if(check == false) { // tr?????ng h???p kh??ng t??m th???y
            System.out.println("Khong tim thay tai khoan!");
        }
    }

    // h??m kho?? v?? m??? kho?? t??i kho???n
    private void lockAndUnlockAccount(CanShowAccountsList accountsList, boolean lock, String string) {
        boolean check = false;

        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap email muon khoa/mo khoa: ");
        String email = sc.nextLine();

        for(Account account : accountsList.getAccountsList()) {
            if(account.getEmail().equalsIgnoreCase(email)) {
                check = true;
                if(account.getType() != 1) {
                    if(account.isLock() == lock) {
                        account.setLock(!lock);
                        System.out.println("Tai khoan " + account.getEmail() + " da duoc " + string + "!");
                    } else {
                        System.out.println("Tai khoan " + account.getEmail() + " da duoc " + string + " truoc roi!");
                    }
                }
                if(account.getType() == 1){
                    System.out.println("Tai khoan Admin khong the bi khoa!");
                }
            }
        }
        if(check == false) {
            System.out.println("Tai khoan khong ton tai!");
        }
    }
}