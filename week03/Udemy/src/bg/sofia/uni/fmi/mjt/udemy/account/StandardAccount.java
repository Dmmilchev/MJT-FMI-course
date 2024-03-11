package bg.sofia.uni.fmi.mjt.udemy.account;

import bg.sofia.uni.fmi.mjt.udemy.account.type.AccountType;
public class StandardAccount extends AccountBase{
    public static final AccountType ACCOUNT_TYPE = AccountType.STANDARD;
    public StandardAccount(String username, double balance) {
        super(username, balance);
    }
}
