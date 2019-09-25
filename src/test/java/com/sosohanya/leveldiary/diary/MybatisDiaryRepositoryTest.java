package com.sosohanya.leveldiary.diary;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sosohanya.leveldiary.account.Account;
import com.sosohanya.leveldiary.account.MybatisAccountRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisDiaryRepositoryTest {

	@Autowired
	MybatisDiaryRepository diaryRepository;
	
	@Autowired
	MybatisAccountRepository accountRepository;
	
	String defaultContents = "defaultContents";
	Account diaryAccount;
	
	@Before
	public void setUp() {
		accountRepository.deleteAll();
		Long insertedId = accountRepository.save(new Account("diaryAccount@email.com"));
		diaryAccount = accountRepository.findById(insertedId);
		
		diaryRepository.deleteAll();		
	}
	
	@Test
	public void count() {
		assertEquals(0, diaryRepository.count());
		diaryRepository.save(new Diary("20190901", "diary1", diaryAccount));
		diaryRepository.save(new Diary("20190901", "diary2", diaryAccount));
		diaryRepository.save(new Diary("20190902", "diary3", diaryAccount));
		assertEquals(3, diaryRepository.count());
	}
	
	@Test
	public void saveAndFindAll() {
		diaryRepository.save(new Diary("20190901", "diary1", diaryAccount));
		diaryRepository.save(new Diary("20190901", "diary2", diaryAccount));
		diaryRepository.save(new Diary("20190902", "diary3", diaryAccount));
		
		List<Diary> getAll = diaryRepository.findAll();
		assertEquals(3, getAll.size());
	}
	
	@Test
	public void saveAndFindById() {
		long resultId = diaryRepository.save(new Diary("20190901", defaultContents, diaryAccount));
		Diary getDiary = diaryRepository.findById(resultId);
		
		assertNotNull(getDiary);
		assertEquals(defaultContents, getDiary.getContents());
		assertEquals(diaryAccount.getEmail(), getDiary.getAccount().getEmail());
	}
	
	@Test
	public void saveAndFindByDiaryDate() {
		diaryRepository.save(new Diary("20190901", "diary1", diaryAccount));
		diaryRepository.save(new Diary("20190901", "diary2", diaryAccount));
		diaryRepository.save(new Diary("20190902", "diary3", diaryAccount));
		List<Diary> getDiary = diaryRepository.findByDiaryDate("20190901");
		
		assertEquals(2, getDiary.size());
	}
	
	@Test
	public void update() {
		long resultId = diaryRepository.save(new Diary("20190901", defaultContents, diaryAccount));
		Diary getDiary = diaryRepository.findById(resultId);
		
		getDiary.setContents("updateContents");
		diaryRepository.update(getDiary);
		Diary updatedDiary = diaryRepository.findById(resultId);
		assertEquals("updateContents", updatedDiary.getContents());
	}
	
	@Test
	public void deleteById() {
		long resultId1 = diaryRepository.save(new Diary("20190901", "diary1", diaryAccount));
		long resultId2 = diaryRepository.save(new Diary("20190901", "diary2", diaryAccount));
		long resultId3 = diaryRepository.save(new Diary("20190902", "diary3", diaryAccount));
		
		diaryRepository.deleteById(resultId2);
		assertEquals(2, diaryRepository.count()); 
		
		assertNotNull(diaryRepository.findById(resultId1));
		assertNull(diaryRepository.findById(resultId2));
		assertNotNull(diaryRepository.findById(resultId3));
	}
	
}
