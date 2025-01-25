package com.example.Banking;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
	
	   @Autowired
	    private AccountRepository accountRepository;

	    public Account createAccount(Account account) {
	        return accountRepository.save(account);
	    }

	    public Optional<Account> getAccount(Integer id) {
	        return accountRepository.findById(id);
	    }

	    public Account deposit(Integer id, double amount) {
	        Account account = getAccount(id).orElseThrow(() -> new RuntimeException("Account not found"));
	        account.setBalance(account.getBalance() + amount);
	        return accountRepository.save(account);
	    }

	    public Account withdraw(Integer id, double amount) {
	        Account account = getAccount(id).orElseThrow(() -> new RuntimeException("Account not found"));
	        if (account.getBalance() < amount) {
	            throw new RuntimeException("Insufficient funds");
	        }
	        account.setBalance(account.getBalance() - amount);
	        return accountRepository.save(account);
	    }


}
