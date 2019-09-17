package com.sosohanya.leveldiary.diary;

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
public class MybatisDiaryRepositoryTest {

	@Autowired
	MybatisDiaryRepository diaryRepository;
	
String defaultContents = "defaultContents";
	
	@Before
	public void setUp() {
		diaryRepository.deleteAll();		
	}
	
	@Test
	public void count() {
		assertEquals(0, diaryRepository.count());
		diaryRepository.save(new Diary("20190901", "diary1"));
		diaryRepository.save(new Diary("20190901", "diary2"));
		diaryRepository.save(new Diary("20190902", "diary3"));
		assertEquals(3, diaryRepository.count());
	}
	
	@Test
	public void saveAndFindAll() {
		diaryRepository.save(new Diary("20190901", "diary1"));
		diaryRepository.save(new Diary("20190901", "diary2"));
		diaryRepository.save(new Diary("20190902", "diary3"));
		
		List<Diary> getAll = diaryRepository.findAll();
		assertEquals(3, getAll.size());
	}
	
	@Test
	public void saveAndFindById() {
		long resultId = diaryRepository.save(new Diary("20190901", defaultContents));
		Diary getDiary = diaryRepository.findById(resultId);
		
		assertNotNull(getDiary);
		assertEquals(defaultContents, getDiary.getContents());
	}
	
	@Test
	public void saveAndFindByDiaryDate() {
		diaryRepository.save(new Diary("20190901", "diary1"));
		diaryRepository.save(new Diary("20190901", "diary2"));
		diaryRepository.save(new Diary("20190902", "diary3"));
		List<Diary> getDiary = diaryRepository.findByDiaryDate("20190901");
		
		assertEquals(2, getDiary.size());
	}
	
	@Test
	public void update() {
		long resultId = diaryRepository.save(new Diary("20190901", defaultContents));
		Diary getDiary = diaryRepository.findById(resultId);
		
		getDiary.setContents("updateContents");
		diaryRepository.update(getDiary);
		Diary updatedDiary = diaryRepository.findById(resultId);
		assertEquals("updateContents", updatedDiary.getContents());
	}
	
	@Test
	public void deleteById() {
		long resultId1 = diaryRepository.save(new Diary("20190901", "diary1"));
		long resultId2 = diaryRepository.save(new Diary("20190901", "diary2"));
		long resultId3 = diaryRepository.save(new Diary("20190902", "diary3"));
		
		diaryRepository.deleteById(resultId2);
		assertEquals(2, diaryRepository.count()); 
		
		assertNotNull(diaryRepository.findById(resultId1));
		assertNull(diaryRepository.findById(resultId2));
		assertNotNull(diaryRepository.findById(resultId3));
	}
	
}
