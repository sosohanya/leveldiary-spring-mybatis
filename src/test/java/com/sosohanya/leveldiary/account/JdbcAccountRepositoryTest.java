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
public class JdbcAccountRepositoryTest {

	@Autowired
	JdbcAccountRepository jdbcAccountRepository;
	
	String defaultEmail = "add@email.com";
	
	@Before
	public void setUp() {
		jdbcAccountRepository.deleteAll();		
	}
	
	@Test
	public void count() {
		assertEquals(0, jdbcAccountRepository.count());
		jdbcAccountRepository.save(new Account("add1@email.com"));
		jdbcAccountRepository.save(new Account("add2@email.com"));
		jdbcAccountRepository.save(new Account("add3@email.com"));
		assertEquals(3, jdbcAccountRepository.count());
	}
	
	@Test
	public void saveAndFindAll() {
		jdbcAccountRepository.save(new Account("add1@email.com"));
		jdbcAccountRepository.save(new Account("add2@email.com"));
		jdbcAccountRepository.save(new Account("add3@email.com"));
		
		List<Account> getAll = jdbcAccountRepository.findAll();
		assertEquals(3, getAll.size());
	}
	
	@Test
	public void saveAndFindById() {
		long resultId = jdbcAccountRepository.save(new Account(defaultEmail));
		Account getAccount = jdbcAccountRepository.findById(resultId);
		
		assertNotNull(getAccount);
		assertEquals(defaultEmail, getAccount.getEmail());
	}
	
	@Test
	public void saveAndFindByEmail() {
		jdbcAccountRepository.save(new Account(defaultEmail));
		Account getAccount = jdbcAccountRepository.findByEmail(defaultEmail);
		
		assertNotNull(getAccount);
		assertEquals(defaultEmail, getAccount.getEmail());
	}
	
	@Test
	public void update() {
		long resultId = jdbcAccountRepository.save(new Account(defaultEmail));
		Account getAccount = jdbcAccountRepository.findById(resultId);
		
		getAccount.setEmail("update@email.com");
		jdbcAccountRepository.update(getAccount);
		Account updatedAccount = jdbcAccountRepository.findById(resultId);
		assertEquals("update@email.com", updatedAccount.getEmail());
	}
	
	@Test
	public void deleteById() {
		long resultId1 = jdbcAccountRepository.save(new Account("add1@email.com"));
		long resultId2 = jdbcAccountRepository.save(new Account("add2@email.com"));
		long resultId3 = jdbcAccountRepository.save(new Account("add3@email.com"));
		
		jdbcAccountRepository.deleteById(resultId2);
		assertEquals(2, jdbcAccountRepository.count()); 
		
		assertNotNull(jdbcAccountRepository.findById(resultId1));
		assertNull(jdbcAccountRepository.findById(resultId2));
		assertNotNull(jdbcAccountRepository.findById(resultId3));
	}
}
