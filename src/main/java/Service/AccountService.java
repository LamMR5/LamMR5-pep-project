package Service;


import Model.Account;
import DAO.AccountDAO;

import java.util.List;



public class AccountService {
    private AccountDAO accountDAO;
  
    public AccountService(){
        accountDAO = new AccountDAO();
    }

    public AccountService(AccountDAO accountDAO){
        this.accountDAO = accountDAO;
    }
    public Account addAccount(Account account) {
        if(account.username.isBlank()) return null;
        if(account.getPassword().length()<4) return null;
        return accountDAO.insertAccount(account);
    }

    public Account Loginaccount(String username, String password) {
        Account account = accountDAO.loginAccount(username, password);
        return account;
        
    }

    public Account getAccountById(int posted_by) {
        return accountDAO.getAccountByID(posted_by);
    }

}
