package bankmgtsystem;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class BankAccount {
    private String name;
    private String address;
    private String contact;
    private double balance;

    public BankAccount(String name, String address, String contact, double balance) {
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.balance = balance;
    }

    // Getters and setters here
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public void setContact(String contact) {
        this.contact = contact;
    }
    


    public void deposit(double amount) {
        balance += amount;
    }

    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
        } else {
            System.out.println("Insufficient funds!");
        }
    }

    public void transfer(BankAccount recipient, double amount) {
        if (balance >= amount) {
            withdraw(amount);
            recipient.deposit(amount);
        } else {
            System.out.println("Insufficient funds for the transfer!");
        }
    }

    @Override
    public String toString() {
        return "Account Information: \nName: " + name + "\nAddress: " + address +
                "\nContact: " + contact + "\nBalance: $" + balance;
    }
}

public class BankManagementSystem {
    private static final Map<Integer, BankAccount> accounts = new HashMap<>();
    private static int accountNumberGenerator = 1000;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nBank Management System :- \n\n" +
                    "1. User Registration\n" +
                    "2. Account Management\n" +
                    "3. Deposit\n" +
                    "4. Withdraw\n" +
                    "5. Fund Transfer\n" +
                    "6. Exit\n" +
                    "Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    registerUser(scanner);
                    break;
                case 2:
                    manageAccount(scanner);
                    break;
                case 3:
                    deposit(scanner);
                    break;
                case 4:
                    withdraw(scanner);
                    break;
                case 5:
                    fundTransfer(scanner);
                    break;
                case 6:
                    System.out.println("Exiting the Bank Management System. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }

    private static void registerUser(Scanner scanner) {
        System.out.println("User Registration");
        System.out.println("Enter your name:-");
        String name = scanner.next();
        System.out.println("Enter your address:-");
        String address = scanner.next();
        System.out.println("Enter your contact information:-");
        String contact = scanner.next();
        System.out.println("Enter the initial deposit amount:-");
        double initialDeposit = scanner.nextDouble();

        int accountNumber = generateAccountNumber();
        BankAccount newAccount = new BankAccount(name, address, contact, initialDeposit);
        accounts.put(accountNumber, newAccount);

        System.out.println("Registration successful!\nYour account number is: " + accountNumber);
    }

    private static void manageAccount(Scanner scanner) {
        System.out.println("Enter your account number: ");
        int accountNumber = scanner.nextInt();

        if (accounts.containsKey(accountNumber)) {
            BankAccount account = accounts.get(accountNumber);
            System.out.println(account);

            System.out.println("Do you want to update account information? (yes/no)");
            String updateChoice = scanner.next().toLowerCase();

            if (updateChoice.equals("yes")) {
                System.out.println("Enter updated name: ");
                account.setName(scanner.next());
                System.out.println("Enter updated address: ");
                account.setAddress(scanner.next());
                System.out.println("Enter updated contact information: ");
                account.setContact(scanner.next());

                System.out.println("Account information updated successfully!");
            }
        } else {
            System.out.println("Account not found. Please check the account number.");
        }
    }

    private static void deposit(Scanner scanner) {
        System.out.println("Enter your account number: ");
        int accountNumber = scanner.nextInt();

        if (accounts.containsKey(accountNumber)) {
            System.out.println("Enter the deposit amount: ");
            double depositAmount = scanner.nextDouble();

            BankAccount account = accounts.get(accountNumber);
            account.deposit(depositAmount);

            System.out.println("Deposit successful!\nUpdated account information:\n" + account);
        } else {
            System.out.println("Account not found. Please check the account number.");
        }
    }

    private static void withdraw(Scanner scanner) {
        System.out.println("Enter your account number: ");
        int accountNumber = scanner.nextInt();

        if (accounts.containsKey(accountNumber)) {
            System.out.println("Enter the withdrawal amount: ");
            double withdrawalAmount = scanner.nextDouble();

            BankAccount account = accounts.get(accountNumber);
            account.withdraw(withdrawalAmount);

            System.out.println("Withdrawal successful!\nUpdated account information:\n" + account);
        } else {
            System.out.println("Account not found. Please check the account number.");
        }
    }

    private static void fundTransfer(Scanner scanner) {
        System.out.println("Enter your account number: ");
        int senderAccountNumber = scanner.nextInt();

        if (accounts.containsKey(senderAccountNumber)) {
            System.out.println("Enter the recipient's account number: ");
            int recipientAccountNumber = scanner.nextInt();

            if (accounts.containsKey(recipientAccountNumber)) {
                System.out.println("Enter the transfer amount: ");
                double transferAmount = scanner.nextDouble();

                BankAccount senderAccount = accounts.get(senderAccountNumber);
                BankAccount recipientAccount = accounts.get(recipientAccountNumber);

                senderAccount.transfer(recipientAccount, transferAmount);

                System.out.println("Fund transfer successful!\nUpdated sender's account information:\n" + senderAccount);
                System.out.println("Updated recipient's account information:\n" + recipientAccount);
            } else {
                System.out.println("Recipient's account not found. Please check the account number.");
            }
        } else {
            System.out.println("Sender's account not found. Please check the account number.");
        }
    }

    private static int generateAccountNumber() {
        return ++accountNumberGenerator;
    }
}
