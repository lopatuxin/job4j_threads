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
        Account minus = getById(fromId).get();
        Account plus = getById(toId).get();
        if (minus != null && plus != null) {
            if (minus.amount() >= amount) {
                int amountMinus = minus.amount();
                int amountPlus = plus.amount();
                update(new Account(minus.id(), amountMinus - amount));
                update(new Account(plus.id(), amountPlus + amount));
                return true;
            }
        }
        return false;
    }
}
