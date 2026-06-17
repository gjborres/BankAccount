package firstpackage;

import exceptions.AccountFrozenException;
import exceptions.InsufficientFundsException;
import exceptions.InvalidAmountException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;



public class BankAccount2 {
/**
* The BankAccount2 class represents a simple bank account with basic,
* functionalities such as deposit, withdraw, freeze account,
* and check balance.
*/
	public double balance = 0.00;

	/**
	 * Indicates whether the account is frozen or not.
	 */
	private boolean isFrozen = false;

	/**
	 * A list to keep track of all transactions (deposits and withdrawals).
	 */
	private List<Transaction> transactionsHistory = new ArrayList<>();

	/**
	 * Constructor for the BankAccount2 class.
	 * Initializes the transaction history
	 * list.
	 */
	public BankAccount2() {
		transactionsHistory = new ArrayList<>();
	}

	/**
	 * Deposits a specified amount into the bank account.
	 * @param amount
	 * @throws InvalidAmountException
	 * @throws AccountFrozenException
	 */

	public void deposit(final double amount)
			throws InvalidAmountException, AccountFrozenException {

		if (isFrozen) {
			throw new AccountFrozenException(
					"Account is FROZEN. Unable to transact."
					);
		}

		if (amount <= 0) {
			throw new InvalidAmountException(
					"Invalid amount. Must be greater than 0"
					);
		}

		balance += amount;
		transactionsHistory.add(new Transaction("Deposit", amount));
	}

	/**
	 * Withdraws a specified amount from the bank account.
	 * @param amount
	 * @throws InvalidAmountException
	 * @throws InsufficientFundsException
	 * @throws AccountFrozenException
	 */

	public void withdraw(final double amount)
			throws InvalidAmountException,
			InsufficientFundsException, AccountFrozenException {

		if (isFrozen) {
			throw new AccountFrozenException(
				"Account is FROZEN. Unable to transact.");
		}

		if (amount <= 0) {
			throw new InvalidAmountException(
				"Invalid amount. Must be greater than 0");
		}

		if (amount > balance) {
			throw new InsufficientFundsException(
					"Insufficient balance.");
		}

		balance -= amount;
		transactionsHistory.add(new Transaction("Withdraw", amount));
	}

	/**
	 * Returns the transaction history for the bank account.
	 * @return transactionsHistory
	 */

	public List<Transaction> getTransactionHistory() {
		return transactionsHistory;
	}

	/**
	 * Unfreezes the bank account, allowing transactions to be made.
	 * @return true or false depending on the account's frozen status
	 */
	public boolean unfreezeAccount() {
		isFrozen = false;
		return isFrozen;
	}

	/**
	 * Freezes the bank account,
	 * preventing any transactions from being made.
	 * @return true or false depending on the account's frozen status
	 */

	public boolean freezeAccount() {
		isFrozen = true;
		return isFrozen;
	}

	/**
	 * Checks if the bank account is currently frozen.
	 * @return true or false depending on the account's frozen status
	 */

	public double getBalance() {
		return balance;
	}

	/**
	 * Main method to demonstrate the
	 * functionality of the BankAccount2 class.
	 * @param args
	 */
	public static void main(final String[] args) {

		BankAccount2 acc1 = new SavingsAccount2("Jejy");

		final double okane = 1000.125;
		final double okane1 = -500;
		final double okane2 = 500;
		final double okane3 = 11540;
		try {
			acc1.deposit(okane);
		} catch (InvalidAmountException | AccountFrozenException e) {
			System.out.println(e.getMessage());
		}

		for (Transaction t : acc1.getTransactionHistory()) {
			System.out.println(t);
		}

		BankAccountManager accM1 = new BankAccountManager();

		System.out.println("Bank Account Manager created.");

		accM1.addAccount(acc1);

		SavingsAccount2 acc2 = new SavingsAccount2("Borres");
		accM1.addAccount(acc2);

		accM1.listAccounts();

		// Initial transactions for Test Cases 6 and 7
		try {
			acc1.deposit(okane);
			acc1.withdraw(okane2);
			acc1.withdraw(okane1);
		} catch (InvalidAmountException
				| InsufficientFundsException
				| AccountFrozenException e) {
			System.out.println(e.getMessage());
		}

		System.out.println("TEST CASE 1");

		try {
			acc1.deposit(okane1);
		} catch (InvalidAmountException | AccountFrozenException e) {
			System.out.println(e.getMessage());
		}

		System.out.println("TEST CASE 2");
		acc1.freezeAccount();
		try {
			acc1.deposit(okane3);
		} catch (InvalidAmountException | AccountFrozenException e) {
			System.out.println(e.getMessage());
		}
		acc1.unfreezeAccount();

		System.out.println("TEST CASE 3");
		System.out.println("Amount withdrawn: -100.00");
		try {
			acc1.withdraw(okane1);
		} catch (InvalidAmountException
				| InsufficientFundsException
				| AccountFrozenException e) {
			System.out.println(e.getMessage());
		}

		System.out.println("TEST CASE 4");

		try {
			acc2.deposit(okane2);

			System.out.println("Amount withdrawn: 1500.00");
			acc2.withdraw(okane2);

		} catch (InvalidAmountException e) {
			System.out.println(e.getMessage());
		} catch (InsufficientFundsException e) {
			System.out.println(e.getMessage());
			System.out.printf("Current balance: %.2f%n",
					acc2.getBalance());
		} catch (AccountFrozenException e) {
			System.out.println(e.getMessage());
		}

		System.out.println("TEST CASE 5");
		acc1.freezeAccount();

		try {
			acc1.withdraw(okane2);
		} catch (InvalidAmountException
				| InsufficientFundsException
				| AccountFrozenException e) {
			System.out.println(e.getMessage());
		}

		acc1.unfreezeAccount();

		System.out.println("TEST CASE 6");
		System.out.println("Filtering transactions at or above 200...");

		final double transaction = 200;
		List<Transaction> filtered =
				accM1.filterTransactionsAtOrAbove(
				transaction, acc1.getTransactionHistory());

		System.out.println(filtered);

		System.out.println("TEST CASE 7");
		System.out.println("Sorting transactions by amount...");

		List<Transaction> sorted =
				accM1.sortTransactionsByAmount(
						acc1.getTransactionHistory());

		System.out.println(sorted);

	}
}

class SavingsAccount2 extends BankAccount2 {
	/**
	 * The SavingsAccount2 class represents a savings account.
	 */
	private String ownerName;

	SavingsAccount2(final String ownerName) {
		this.ownerName = ownerName;
	}

	public String getOwnerName() {
		return ownerName;
	}
}

class Transaction {

	/**
	 * The Transaction class represents a single transaction (deposit or
	 * withdrawal).
	 */
	private String type;

	/**
	 * The amount involved in the transaction.
	 */
	private double amount;
	/**
	 * The time_stamp when the transaction occurred.
	 */
	private LocalDateTime timeStamp;

	Transaction(String type, final double amount) {
		this.type = type;
		this.amount = amount;
		this.timeStamp = LocalDateTime.now();
	}

	public String getType() {
		return type;
	}

	public double getAmount() {
		return amount;
	}

	@Override
	public String toString() {
		String timeStr = timeStamp.format(
				DateTimeFormatter.ISO_LOCAL_DATE_TIME);

		return String.format(
				"Transaction[type=%s, amount=%.2f,"
				+ " timeStamp=%s]",
				type, amount, timeStr);
	}
}
