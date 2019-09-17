package com.sosohanya.leveldiary.account;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MybatisAccountRepository implements AccountRepository {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int count() {
		return sqlSession.selectOne("Account.count");
	}

	@Override
	public long save(Account account) {
		sqlSession.insert("Account.save", account);
		return account.getId();
	}

	@Override
	public int update(Account account) {
		return sqlSession.update("Account.update", account);
	}

	@Override
	public int deleteById(Long id) {
		return sqlSession.delete("Account.deleteById", id);
	}

	@Override
	public void deleteAll() {
		sqlSession.delete("Account.deleteAll");
	}

	@Override
	public List<Account> findAll() {
		return sqlSession.selectList("Account.findAll");
	}

	@Override
	public Account findById(Long id) {
		return sqlSession.selectOne("Account.findById", id);
	}

	@Override
	public Account findByEmail(String email) {
		return sqlSession.selectOne("Account.findByEmail", email);
	}

}
