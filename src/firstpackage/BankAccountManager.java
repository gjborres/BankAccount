package firstpackage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


	class BankAccountManager implements Bank {

	    private Map<Integer, BankAccount2> accounts;
	    private int id;

	    public BankAccountManager() {
	        accounts = new HashMap<>();
	        id = 0;
	    }

	    @Override
	    public void addAccount(BankAccount2 account) {
	        if (id < 100) {
	            accounts.put(id, account);
	            id++;
	        } else {
	            System.out.println("Maximum account limit reached.");
	        }
	    }

	    @Override
	    public BankAccount2 getAccount(int id) {
	        return accounts.get(id);
	    }

	    public List<Transaction> filterTransactionsAtOrAbove(
	            double amount,
	            List<Transaction> txList) {

	        return txList.stream()
	                .filter(t -> t.getAmount() >= amount)
	                .collect(Collectors.toList());
	    }

	    public List<Transaction> sortTransactionsByAmount(
	            List<Transaction> txList) {

	        return txList.stream()
	                .sorted((t1, t2) ->
	                        Double.compare(
	                                t1.getAmount(),
	                                t2.getAmount()))
	                .collect(Collectors.toList());
	    }

	    public void listAccounts() {

	        for (Map.Entry<Integer,
	                BankAccount2> entry : accounts.entrySet()) {

	            int accountId = entry.getKey();
	            BankAccount2 account = entry.getValue();

	            if (account instanceof SavingsAccount2) {

	                SavingsAccount2 sa =
	                        (SavingsAccount2) account;

	                System.out.println(
	                        "ID: " + accountId
	                                + ", Owner: "
	                                + sa.getOwnerName()
	                                + ", Balance: "
	                                + account.balance);
	            }
	        }
	    }
	}

