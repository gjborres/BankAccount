package firstpackage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;
import java.lang.*;

public class BankAccount {
	
	private String account;

	public double balance = 0.00;
	boolean isFrozen = false;

	public void deposit(double amount) {
		if (!isFrozen && amount > 0) {
			balance += amount;

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
	    } else if (isFrozen) {
	        System.out.println("Account is FROZEN. Unable to transact");
	    } else if (amount <= 0) {
	        System.out.println(amount + " is an invalid amount. Must be greater than 0");
	    } else {
	        System.out.println("Insufficient funds");
	    }
	}
	
	
	public boolean unfreezeAccount() {
		isFrozen = false;
		return isFrozen;
	}
	
	public boolean freezeAccount() {
		isFrozen = true;
		return isFrozen;
	}
	
	public boolean isFrozen() {
		return true;
		
	}
	
	public double getBalance() {
        return balance;

	}
	
	
	
public static void main(String[] args) {
	BankAccount acc = new SavingsAccount("Jejy");
    System.out.println("Account Owner: " +
            ((SavingsAccount) acc).getOwnerName());

    // Test Case 2
    System.out.println("\nAmount deposited: 1000.12332");
    acc.deposit(1000.125);
    System.out.printf("Current balance: %.2f%n", acc.balance);

    // Test Case 3
    System.out.println("\nAmount deposited: 0.0");
    acc.deposit(0);

    // Test Case 4
    System.out.println("\nAmount deposited: -1000.0");
    acc.deposit(-500);

    // Test Case 5
    System.out.println("\nAmount withdrawn: 500.0");
    acc.withdraw(500);
    System.out.printf("Current balance: %.2f%n", acc.balance);
	}
}



 class SavingsAccount extends BankAccount {

    private String ownerName;

    public SavingsAccount(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerName() {
        return ownerName;
    }
 }
	
 

