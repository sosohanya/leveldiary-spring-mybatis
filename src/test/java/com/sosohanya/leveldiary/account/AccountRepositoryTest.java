package com.sosohanya.leveldiary.account;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountRepositoryTest { //변경전 : JdbcAccountRepositoryTest

	@Autowired
	MybatisAccountRepository accountRepository; //변경전 : jdbcAccountRepository
	
	String defaultEmail = "add@email.com";
	
	@Before
	public void setUp() {
		accountRepository.deleteAll();		
	}
	
	@Test
	public void count() {
		assertEquals(0, accountRepository.count());
		accountRepository.save(new Account("add1@email.com", "pwd"));
		accountRepository.save(new Account("add2@email.com", "pwd"));
		accountRepository.save(new Account("add3@email.com", "pwd"));
		assertEquals(3, accountRepository.count());
	}
	
	@Test
	public void saveAndFindAll() {
		accountRepository.save(new Account("add1@email.com", "pwd"));
		accountRepository.save(new Account("add2@email.com", "pwd"));
		accountRepository.save(new Account("add3@email.com", "pwd"));
		
		List<Account> getAll = accountRepository.findAll();
		assertEquals(3, getAll.size());
	}
	
	@Test
	public void saveAndFindById() {
		long resultId = accountRepository.save(new Account(defaultEmail, "pwd"));
		Account getAccount = accountRepository.findById(resultId);
		
		assertNotNull(getAccount);
		assertEquals(defaultEmail, getAccount.getEmail());
	}
	
	@Test
	public void saveAndFindByEmail() {
		accountRepository.save(new Account(defaultEmail, "pwd"));
		Account getAccount = accountRepository.findByEmail(defaultEmail);
		
		assertNotNull(getAccount);
		assertEquals(defaultEmail, getAccount.getEmail());
	}
	
	@Test
	public void update() {
		long resultId = accountRepository.save(new Account(defaultEmail, "pwd"));
		Account getAccount = accountRepository.findById(resultId);
		
		getAccount.setEmail("update@email.com");
		accountRepository.update(getAccount);
		Account updatedAccount = accountRepository.findById(resultId);
		assertEquals("update@email.com", updatedAccount.getEmail());
	}
	
	@Test
	public void deleteById() {
		long resultId1 = accountRepository.save(new Account("add1@email.com", "pwd"));
		long resultId2 = accountRepository.save(new Account("add2@email.com", "pwd"));
		long resultId3 = accountRepository.save(new Account("add3@email.com", "pwd"));
		
		accountRepository.deleteById(resultId2);
		assertEquals(2, accountRepository.count()); 
		
		assertNotNull(accountRepository.findById(resultId1));
		assertNull(accountRepository.findById(resultId2));
		assertNotNull(accountRepository.findById(resultId3));
	}
}
