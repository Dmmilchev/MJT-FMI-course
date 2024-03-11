package bg.sofia.uni.fmi.mjt.udemy.account;

import bg.sofia.uni.fmi.mjt.udemy.course.Category;
import bg.sofia.uni.fmi.mjt.udemy.account.type.AccountType;
public class BusinessAccount extends AccountBase{
    public static final AccountType ACCOUNT_TYPE = AccountType.BUSINESS;
    private final Category[] categories;
    public BusinessAccount(String username, double balance, Category[] allowedCategories) {
        super(username, balance);
        categories = allowedCategories;
    }

    public Category[] getCategories() {
        return categories;
    }
}
