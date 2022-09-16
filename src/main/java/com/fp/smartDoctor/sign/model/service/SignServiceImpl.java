package com.fp.smartDoctor.sign.model.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fp.smartDoctor.common.model.vo.PageInfo;
import com.fp.smartDoctor.member.model.vo.Dept;
import com.fp.smartDoctor.member.model.vo.Member;
import com.fp.smartDoctor.sign.model.dao.SignDao;
import com.fp.smartDoctor.sign.model.vo.Form;
import com.fp.smartDoctor.sign.model.vo.Line;
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
	@Override
	public ArrayList<Form> selectApprFormList() {
		return sDao.selectApprFormList(sqlSession);
	}
	@Override
	public Form selectApprFormDetail(int formNo) {
		return sDao.selectApprFormDetail(sqlSession, formNo);
	}
	@Override
	public ArrayList<Member> selectApprLineList() {
		return sDao.selectApprLineList(sqlSession);
	}
	@Override
	public ArrayList<Dept> selectApprLineDept() {
		return sDao.selectApprLineDept(sqlSession);
	}
	@Override
	public Member selectApprEmp(int empNo) {
		return sDao.selectApprEmp(sqlSession, empNo);
	}
	@Override
	public int selectApprListCount() {
		return sDao.selectApprListCount(sqlSession);
	}
	@Override
	public ArrayList<Sign> selectApprStandbyList(PageInfo pi) {
		return sDao.selectApprStandbyList(sqlSession, pi);
	}
	@Override
	public int insertAppr(Sign s) {
		return sDao.insertAppr(sqlSession, s);
	}
	@Override
	public int selectReferListCount(String empNo) {
		return sDao.selectReferListCount(sqlSession, empNo);
	}
	@Override
	public ArrayList<Sign> selectApprReferList(PageInfo pi, String empNo) {
		return sDao.selectApprReferList(sqlSession, pi, empNo);
	}
	@Override
	public Sign selectApprReferDetail(int apprNo) {
		return sDao.selectApprReferDetail(sqlSession, apprNo);
	}
	@Override
	public ArrayList<Line> selectApprLine(int apprNo) {
		return sDao.selectApprLine(sqlSession, apprNo);
	}
	@Override
	public ArrayList<Line> selectApprRef(int apprNo) {
		return sDao.selectApprRef(sqlSession, apprNo);
	}
	@Override
	public int selectReportListCount(String empNo) {
		return sDao.selectReportListCount(sqlSession, empNo);
	}
	@Override
	public ArrayList<Sign> selectApprReportList(PageInfo pi, String empNo) {
		return sDao.selectApprReportList(sqlSession, pi, empNo);
	}
	@Override
	public Sign selectApprReportDetail(int apprNo) {
		return sDao.selectApprReportDetail(sqlSession, apprNo);
	}
	@Override
	public int selectCommentCount(int apprNo) {
		return sDao.selectCommentCount(sqlSession, apprNo);
	}
	@Override
	public int insertLine(ArrayList<Line> lineList) {
		return sDao.insertLine(sqlSession, lineList);
	}
	@Override
	public int insertRef(ArrayList<Line> refList) {
		return sDao.insertRef(sqlSession, refList);
	}
	@Override
	public int selectGetListCount(String empNo) {
		return sDao.selectGetListCount(sqlSession, empNo);
	}
	@Override
	public ArrayList<Sign> selectApprGetList(PageInfo pi, String empNo) {
		return sDao.selectApprGetList(sqlSession, pi, empNo);
	}

}
