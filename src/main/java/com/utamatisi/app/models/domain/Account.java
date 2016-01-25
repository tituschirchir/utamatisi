package com.utamatisi.app.models.domain;

import com.utamatisi.app.models.milestone.BiTemporalDateMilestonedImpl;

import javax.persistence.*;

/**
 * Created by titus.chirchir12
 * Date Created 1/23/2016.
 * Package: ${PACKAGE}
 */

@Entity
@Table(name = "account")
@NamedQueries({
        @NamedQuery(
                name = "Account.findAll",
                query = "SELECT a FROM Account a"
        )
})
public class Account extends BiTemporalDateMilestonedImpl {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "acct_n", nullable = false)
    private String accountNumber;

    @Column(name = "acct_d", nullable = false)
    private String accountDescription;

    @Column(name = "balance", nullable = false)
    private String balance;

    public Account(){
        super(null);
    }
    public Account(String accountNumber, String accountDescription, String balance) {
        super(null);
        this.accountNumber = accountNumber;
        this.accountDescription = accountDescription;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountDescription() {
        return accountDescription;
    }

    public void setAccountDescription(String accountDescription) {
        this.accountDescription = accountDescription;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }
}
