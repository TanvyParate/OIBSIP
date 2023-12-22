import java.util.ArrayList;
import java.util.Scanner;

class Transaction {
	private String type;
	private double amount;
	
	public Transaction(String type, double amount) {
		this.type = type;
		this.amount = amount;
	}
	
	public String getType() {
		return type;
	}
	
	public double getAmount() {
		return amount;
	}
}

class Account {
	private String userId;
	private String userPin;
	private double balance;     // gets updated as transactions occurs
	private ArrayList<Transaction> transactionHistory;
	
	Account(String userId, String userPin, double initialBalance) {
		this.userId= userId;
		this.userPin = userPin;
		this.balance = initialBalance;         // initially 1000.0
		this.transactionHistory = new ArrayList<>();
	}
	
	public String getUserId() {
		return userId;
	}
	
	public boolean authenticate(String enteredPin) {
		return userPin.equals(enteredPin);
	}
	
	public double getBalance() {
		return balance;
	}
	
	public void deposit(double amount) {
		balance += amount;
		addToTransactionHistory("Deposit", amount);
	}
	
	public void withdraw(double amount) {
		if(amount > 0 && amount <= balance) {
			balance -= amount;
			addToTransactionHistory("Withdrawal", amount);
		}
		else {
			System.out.println("Invalid withdrawal amount or insufficient funds.");
		}
	}
	
	public void transfer(Account recipient, double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            recipient.deposit(amount);
            addToTransactionHistory("Transfer to " + recipient.getUserId(), amount);
        } else {
            System.out.println("Invalid transfer amount or insufficient funds.");
        }
    }
	
	public void addToTransactionHistory(String type, double amount) {
		Transaction transaction = new Transaction(type, amount);
		transactionHistory.add(transaction);
	}
	
	public void displayTransactionHistory() {
		System.out.println("Transaction History: ");
		for (Transaction transaction : transactionHistory) {
			System.out.println(transaction.getType() + ": " + transaction.getAmount());
		}
	}
	
}

public class AtmInterface {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		//create an account
		Account userAccount = new Account("12345", "5678", 1000.0);
		Account anotherAccount = new Account("67890", "4321", 500.0);
		
		// ATM login
		System.out.println("Enter user ID: ");
		String enteredUserId = sc.nextLine();
		
		System.out.println("Enter Pin ");
		String enteredPin = sc.nextLine();
		
		if(enteredUserId.equals(userAccount.getUserId()) && userAccount.authenticate(enteredPin)) {
			System.out.println("Login successful");
			
			// ATM 
			int choice;
			do {
				System.out.println("\n1. Transactions History");
				System.out.println("2. Withdraw");
				System.out.println("3. Deposit");
				System.out.println("4. Transfer");
				System.out.println("5. Quit");
				System.out.println("Enter your choice: ");
				choice = sc.nextInt();
				
				switch(choice) {
				case 1: 
					userAccount.displayTransactionHistory();
					break;
				case 2:
					//Withdraw
					System.out.println("Enter withdrawal amount: ");
					double withdrawalAmount = sc.nextDouble();
					userAccount.withdraw(withdrawalAmount);
					System.out.println("Withdrawal successful. Current balance : " + userAccount.getBalance());
					break;
				case 3: 
					//Deposit
					System.out.println("Enter deposit amount: ");
					double depositAmount = sc.nextDouble();
					userAccount.deposit(depositAmount);
					System.out.println("Deposit successful. Current balance: " + userAccount.getBalance());
					break;
				case 4:
					//Transfer
					System.out.println("Enter recipient's user ID: ");
					String recipientUserId = sc.next();
					System.out.println("Enter transfer amount: ");
					double transferAmount = sc.nextDouble();
					
					userAccount.transfer(anotherAccount, transferAmount);
                    System.out.println("Transfer successful. Current balance: " + userAccount.getBalance());
                    break;
				case 5:
					//Quit
                    System.out.println("Exiting ATM. Thank you!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
				}
				
			}while(choice!= 5);
		}
		else {
			System.out.println("Login failed. Invalid user Id or PIN");
		}
	}
}
