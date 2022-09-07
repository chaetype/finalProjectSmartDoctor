package com.fp.smartDoctor.sign.model.service;

import java.util.ArrayList;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fp.smartDoctor.common.model.vo.PageInfo;
import com.fp.smartDoctor.sign.model.dao.SignDao;
import com.fp.smartDoctor.sign.model.vo.Sign;

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
	public ArrayList<Sign> selectFormList(PageInfo pi) {
		return sDao.selectFormList(sqlSession, pi);
	}
}