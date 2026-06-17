package firstpackage;

/**
 * The Bank interface defines the contract for a
 * bank that can manage bank accounts.
 */
public interface Bank {
/**
 * Adds a new bank account to the bank.
 * @param account
*/
void addAccount(BankAccount2 account);
/**
 * Retrieves a bank account by its ID.
 * @param id
 * @return the bank account associated with the given ID
 */

BankAccount2 getAccount(int id);
}
