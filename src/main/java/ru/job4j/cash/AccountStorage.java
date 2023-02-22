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

    public boolean update(Account account) {
        return accounts.put(account.id(), account) != null;
    }

    public synchronized boolean delete(int id) {
        return accounts.remove(id) != null;
    }

    public synchronized Optional<Account> getById(int id) {
        Optional<Account> optional = Optional.empty();
        if (accounts.get(id) != null) {
            optional = Optional.of(accounts.get(id));
        }
        return optional;
    }

    public boolean transfer(int fromId, int toId, int amount) {
        if (getById(fromId).isPresent() && getById(toId).isPresent()) {
            Account account1 = getById(fromId).get();
            Account account2 = getById(toId).get();
            int amount1 = account1.amount();
            int amount2 = account2.amount();
            update(new Account(account1.id(), amount1 - amount));
            update(new Account(account2.id(), amount2 + amount));
            return true;
        }
        return false;
    }
}
