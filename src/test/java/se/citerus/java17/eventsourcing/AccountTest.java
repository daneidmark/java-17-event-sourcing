package se.citerus.java17.eventsourcing;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AccountTest {

    @Test
    void canOpenAccount() {
        Account open = Account.open(new AccountId(UUID.randomUUID()));

        assertEquals(0, open.getBalance().value());
    }

    @Test
    void canDepositAmount() {
        Account open = Account.open(new AccountId(UUID.randomUUID()));
        open.deposit(new Amount(100));
        assertEquals(100, open.getBalance().value());
    }

    @Test
    void canWithdraw() {
        Account open = Account.open(new AccountId(UUID.randomUUID()));
        open.deposit(new Amount(100));
        open.withdraw(new Amount(50));
        assertEquals(50, open.getBalance().value());
    }
}