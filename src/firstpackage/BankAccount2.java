package firstpackage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;
import java.lang.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class BankAccount2 {
	
	public double balance = 0.00;
	boolean isFrozen = false;
	
	private List<Transaction> transactionsHistory = new ArrayList<>();
	
	public BankAccount2() {
		transactionsHistory = new ArrayList<>(); 
	}
	
	public void deposit(double amount) {
		if (!isFrozen && amount > 0) {
			balance += amount;
			transactionsHistory.add(new Transaction("Deposit", amount));
		} else if (isFrozen) {
			System.out.println("Account is FROZEN. Unable to transact");
		}
			else {
				System.out.println( amount + " Invalid amount. Must be greater than 0");
		
		}
			
	}
	
	public void withdraw(double amount) {
	    if (!isFrozen && amount > 0 && amount <= balance) {
	        balance -= amount;
	        transactionsHistory.add(new Transaction("Withdraw", amount));
	    } else if (isFrozen) {
	        System.out.println("Account is FROZEN. Unable to transact");
	    } else if (amount <= 0) {
	        System.out.println(amount + " is an invalid amount. Must be greater than 0");
	    } else {
	        System.out.println("Insufficient funds");
	    }
	}
	
	
	
    public List<Transaction> getTransactionHistory() {
        return transactionsHistory;
    }
	
	public boolean unfreezeAccount() {
		isFrozen = false;
		return isFrozen;
	}
	
	public boolean freezeAccount() {
		isFrozen = true;
		return isFrozen;
	}
	

	public double getBalance() {
        return balance;

	}
	
	
	
public static void main(String[] args) {
    BankAccount2 acc1 = new SavingsAccount2("Jejy");

    acc1.deposit(1000.125);


    for (Transaction t : acc1.getTransactionHistory()) {
        System.out.println(t);
    }

    BankAccountManager accM1 = new BankAccountManager();
    System.out.println("Bank Account Manager created.");

    accM1.addAccount(acc1);

    SavingsAccount2 acc2 = new SavingsAccount2("Borres");
    accM1.addAccount(acc2);

    accM1.listAccounts();

}
}



 class SavingsAccount2 extends BankAccount2 {

    private String ownerName;

    public SavingsAccount2(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerName() {
        return ownerName;
    }
 }
	
 class Transaction {
	    private String type;
	    private double amount;
	    private LocalDateTime time_stamp;

	    public Transaction(String type, double amount) {
	        this.type = type;
	        this.amount = amount;
	        this.time_stamp = LocalDateTime.now();
	    }

	  
	    public String toString() {
	        String timeStr = time_stamp.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
	        return String.format("Transaction[type=%s, amount=%.2f, time_stamp=%s]", type, amount, timeStr);
	    }

	
	   
	}
 
 interface Bank {

	    void addAccount(BankAccount2 account);
	    BankAccount2 getAccount(int id);
	}
 
 
 class BankAccountManager implements Bank {

	    private Map<Integer, BankAccount2> accounts;
	    private int id;

	    public BankAccountManager() {
	        accounts = new HashMap<>();
	        id = 0;
	    }

	    public void addAccount(BankAccount2 account) {
	        if (id < 100) {
	            accounts.put(id, account);
	            id++;
	        } else {
	            System.out.println("Maximum account limit reached.");
	        }
	    }

	    public BankAccount2 getAccount(int id) {
	        return accounts.get(id);
	    }

	    public void listAccounts() {
	        for (Map.Entry<Integer, BankAccount2> entry : accounts.entrySet()) {

	            int accountId = entry.getKey();
	            BankAccount2 account = entry.getValue();

	            if (account instanceof SavingsAccount2) {
	                SavingsAccount2 sa = (SavingsAccount2) account;

	                System.out.println(
	                    "ID: " + accountId +
	                    ", Owner: " + sa.getOwnerName() +
	                    ", Balance: " + account.balance
	                );
	            }
	        }
	    }
	}

 
 
