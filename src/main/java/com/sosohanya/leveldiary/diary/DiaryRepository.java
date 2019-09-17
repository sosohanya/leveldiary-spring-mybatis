package com.sosohanya.leveldiary.diary;

import java.util.List;

public interface DiaryRepository {
	int count();
	
	long save(Diary diary);
	
	int update(Diary diary);
	
	int deleteById(Long id);
	
	void deleteAll();
	
	List<Diary> findAll();
	
	Diary findById(Long id);
	
	List<Diary> findByDiaryDate(String diaryDate);
}
