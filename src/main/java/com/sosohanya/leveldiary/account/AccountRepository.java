package com.sosohanya.leveldiary.account;

import java.util.List;

public interface AccountRepository {

	int count();
	
	long save(Account account);
	
	int update(Account account);
	
	int deleteById(Long id);
	
	void deleteAll();
	
	List<Account> findAll();
	
	Account findById(Long id);
	
	Account findByEmail(String email);
}
