package com.fp.smartDoctor.member.model.dao;

import java.util.ArrayList;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.fp.smartDoctor.member.model.vo.Dept;
import com.fp.smartDoctor.member.model.vo.Member;

@Repository
public class MemberDao {
	
	public Member loginMember(SqlSessionTemplate sqlSession, Member m) {
		return sqlSession.selectOne("memberMapper.loginMember", m);
	}
	
	public int updatePwd(SqlSessionTemplate sqlSession, Member m) {
		return sqlSession.update("memberMapper.updatePwd", m);
	}
	
	public ArrayList<Dept> selectOrgChartDept(SqlSessionTemplate sqlSession){
		return (ArrayList)sqlSession.selectList("memberMapper.selectOrgChartDept");
	}
	
	public ArrayList<Member> selectOrgChartEmp(SqlSessionTemplate sqlSession){
		return (ArrayList)sqlSession.selectList("memberMapper.selectOrgChartEmp");
	}
	
	public int selectEmpCount(SqlSessionTemplate sqlSession, Dept d) {
		return sqlSession.selectOne("memberMapper.selectEmpCount", d);
	}
	
	public ArrayList<Member> selectEmp(SqlSessionTemplate sqlSession, Dept d){
		return (ArrayList)sqlSession.selectList("memberMapper.selectEmp", d);
	}

	public int insertMember(SqlSessionTemplate sqlSession, Member m) {
		return sqlSession.insert("memberMapper.insertMember", m);
	}
	
	public int deleteEmp(SqlSessionTemplate sqlSession, String empNo) {
		return sqlSession.update("memberMapper.deleteEmp", empNo);
	}
	
	public int updateEmp(SqlSessionTemplate sqlSession, Member m) {
		return sqlSession.update("memberMapper.updateEmp", m);
	}
		
	public int updateAdmin(SqlSessionTemplate sqlSession, Member m) {
		return sqlSession.update("memberMapper.updateAdmin", m);
	}
	
	public int updateDept(SqlSessionTemplate sqlSession, Dept d) {
		return sqlSession.update("memberMapper.updateDept", d);
	}
	
	public int deleteDept(SqlSessionTemplate sqlSession, int deptNo) {
		return sqlSession.delete("memberMapper.deleteDept", deptNo);
	}
}
