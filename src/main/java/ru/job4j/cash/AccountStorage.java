package ru.job4j.cash;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;
import java.util.Optional;

@ThreadSafe
public class AccountStorage {
    @GuardedBy("this")
    private final HashMap<Integer, Account> accounts = new HashMap<>();

    public synchronized boolean add(Account account) {
        return accounts.put(account.id(), account) != null;
    }

    public synchronized boolean update(Account account) {
        return accounts.put(account.id(), account) != null;
    }

    public synchronized boolean delete(int id) {
        return accounts.remove(id) != null;
    }

    public synchronized Optional<Account> getById(int id) {
        return Optional.ofNullable(accounts.get(id));
    }

    public synchronized boolean transfer(int fromId, int toId, int amount) {
        Account account1 = getById(fromId).get();
        Account account2 = getById(toId).get();
        if (account1 != null && account2 != null) {
            int amount1 = account1.amount();
            int amount2 = account2.amount();
            update(new Account(account1.id(), amount1 - amount));
            update(new Account(account2.id(), amount2 + amount));
            return true;
        }
        return false;
    }
}
