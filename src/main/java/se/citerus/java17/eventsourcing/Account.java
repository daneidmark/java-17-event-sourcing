package se.citerus.java17.eventsourcing;

public class Account extends AggregateRoot<AccountId> {
    private Balance balance;

    private Account(AccountId accountId) {
        applyNew(new AccountOpened(initialVersion(), now(), accountId, new Balance(0)));
    }

    public static Account open(AccountId accountId) {
        return new Account(accountId);
    }

    public void withdraw(Amount amount) {
        applyNew(new AccountWithdrawn(initialVersion(), now(), getId(), balance.subtract(amount)));
    }

    public void deposit(Amount amount) {
        applyNew(new AccountDeposited(initialVersion(), now(), getId(), balance.add(amount)));
    }

    public Balance getBalance() {
        return balance;
    }

    // Event handlers

    @Override
    protected void apply(DomainEvent<AccountId> domainEvent) {
        switch (domainEvent) {
            case AccountOpened event -> this.handle(event);
            case AccountDeposited event -> this.handle(event);
            case AccountWithdrawn event -> this.handle(event);
            default -> throw new RuntimeException();
        }
    }

    private void handle(AccountOpened event) {
        this.balance = event.balance();
    }

    private void handle(AccountDeposited event) {
        this.balance = event.balance();
    }

    private void handle(AccountWithdrawn event) {
        this.balance = event.balance();
    }
}
