package firstpackage;

public class Zibra extends Primate implements HasTail {
		public boolean is TailStriped() {
			return false;
		}
		v
}



public class BankAccountManager implements Bank {

    private Map<Integer, BankAccount> accounts;
    private int id;

    public BankAccountManager() {
        accounts = new HashMap<>();
        id = 0;
    }

    @Override
    public void addAccount(BankAccount account) {
        if (id < 100) {
            accounts.put(id, account);
            id++;
        } else {
            System.out.println("Maximum account limit reached.");
        }
    }

    @Override
    public BankAccount getAccount(int id) {
        return accounts.get(id);
    }

    public void listAccounts() {
        for (Map.Entry<Integer, BankAccount> entry : accounts.entrySet()) {

            int accountId = entry.getKey();
            BankAccount account = entry.getValue();

            if (account instanceof SavingsAccount) {
                SavingsAccount sa = (SavingsAccount) account;

                System.out.println(
                    "ID: " + accountId +
                    ", Owner: " + sa.getOwnerName() +
                    ", Balance: " + account.balance
                );
            }
        }
    }
}