package com.sosohanya.leveldiary.diary;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MybatisDiaryRepository implements DiaryRepository {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int count() {
		return sqlSession.selectOne("Diary.count");
	}

	@Override
	public long save(Diary diary) {
		sqlSession.insert("Diary.save", diary);
		return diary.getId();
	}

	@Override
	public int update(Diary diary) {
		return sqlSession.update("Diary.update", diary);
	}

	@Override
	public int deleteById(Long id) {
		return sqlSession.delete("Diary.deleteById", id);
	}

	@Override
	public void deleteAll() {
		sqlSession.delete("Diary.deleteAll");
	}

	@Override
	public List<Diary> findAll() {
		return sqlSession.selectList("Diary.findAll");
	}

	@Override
	public Diary findById(Long id) {
		return sqlSession.selectOne("Diary.findById", id);
	}

	@Override
	public List<Diary> findByDiaryDate(String diaryDate) {
		return sqlSession.selectList("Diary.findByDiaryDate", diaryDate);
	}

}
