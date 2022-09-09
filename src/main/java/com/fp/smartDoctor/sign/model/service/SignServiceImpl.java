package com.fp.smartDoctor.sign.model.service;

import java.util.ArrayList;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fp.smartDoctor.common.model.vo.PageInfo;
import com.fp.smartDoctor.sign.model.dao.SignDao;
import com.fp.smartDoctor.sign.model.vo.Form;

@Service
public class SignServiceImpl implements SignService {

	@Autowired
	private SqlSessionTemplate sqlSession;	
	@Autowired
	private SignDao sDao;
	
	@Override
	public int selectListCount() {
		return sDao.selectListCount(sqlSession);
	}
	@Override
	public ArrayList<Form> selectFormList(PageInfo pi) {
		return sDao.selectFormList(sqlSession, pi);
	}
	@Override
	public Form selectFormDetail(int formNo) {
		return sDao.selectFormDetail(sqlSession, formNo);
	}
	@Override
	public int updateForm(Form f) {
		return sDao.updateForm(sqlSession, f);
	}
	@Override
	public int insertForm(Form f) {
		return sDao.insertForm(sqlSession, f);
	}
	@Override
	public int deleteForm(int formNo) {
		return sDao.deleteForm(sqlSession, formNo);
	}
}
