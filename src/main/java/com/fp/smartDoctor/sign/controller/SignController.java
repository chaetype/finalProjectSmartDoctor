package com.fp.smartDoctor.sign.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fp.smartDoctor.common.model.vo.PageInfo;
import com.fp.smartDoctor.common.template.Pagination;
import com.fp.smartDoctor.member.model.vo.Dept;
import com.fp.smartDoctor.member.model.vo.Member;
import com.fp.smartDoctor.sign.model.service.SignService;
import com.fp.smartDoctor.sign.model.vo.Form;
import com.google.gson.Gson;

@Controller
public class SignController {
	
	@Autowired
	private SignService sService;
	
	// 관리자_결재양식조회
	@RequestMapping("formList.si")
	public ModelAndView selectFormList(@RequestParam(value="cpage", defaultValue="1")int currentPage, ModelAndView mv) {
		
		int listCount = sService.selectListCount();
		
		PageInfo pi = Pagination.getPageInfo(listCount, currentPage, 10, 5);
		ArrayList<Form> list = sService.selectFormList(pi);
		
		mv.addObject("pi", pi)
		  .addObject("list", list)
		  .setViewName("kma/formListView");
		
		return mv;
	}
	
	// 관리자_결재양식상세보기
	@RequestMapping("formDetail.si")
	public ModelAndView selectFormDetail(int formNo, ModelAndView mv) {
		
		Form f = sService.selectFormDetail(formNo);
		mv.addObject("f", f).setViewName("kma/formDetailView");
		
		return mv;
	}
	
	// 관리자_결재양식 수정
	@RequestMapping("formUpdate.si")
	public String updateForm(Form f, HttpSession session) {
		
		int result = sService.updateForm(f);
		
		if(result > 0) { // 수정 성공
			session.setAttribute("alertMsg", "결재양식이 수정되었습니다.");
			return "redirect:formDetail.si?formNo=" + f.getFormNo();
		}else {
			session.setAttribute("alertMsg", "결재양식 수정에 실패하였습니다.");
			return "redirect:formDetail.si?formNo=" + f.getFormNo();
		}
	}
	
	// 관리자_결재양식 추가페이지
	@RequestMapping("formEnrollForm.si")
	public String formEnrollForm() {
		return "kma/formInsertView";
	}
	
	// 관리자_결재양식 추가
	@RequestMapping("formInsert.si")
	public String insertForm(Form f, HttpSession session) {
		
		int result = sService.insertForm(f);
		
		if(result > 0) { // 추가 성공
			session.setAttribute("alertMsg", "결재양식이 추가되었습니다.");
			return "redirect:formList.si";
		}else { // 실패
			session.setAttribute("alertMsg", "결재양식 추가에 실패하였습니다.");
			return "redirect:formList.si";
		}
	}
	
	// 관리자_결재양식 삭제
	@RequestMapping("formDelete.si")
	public String deleteForm(int formNo, HttpSession session) {
		
		int result = sService.deleteForm(formNo);
		
		if(result > 0) { // 삭제 성공
			session.setAttribute("alertMsg", "결재양식이 삭제되었습니다.");
			return "redirect:formList.si";
		}else {
			session.setAttribute("alertMsg", "결재양식 삭제에 실패하였습니다.");
			return "redirect:formList.si";
		}
	}
	
	
	
	/*------------------------ 사용자 --------------------------------*/
	
	
	// 사용자_결재문 작성페이지
	@RequestMapping("apprEnrollForm.si")
	public String apprEnrollForm() {
		return "kma/apprEnrollForm";
	}
	
	// 사용자_결재양식 리스트 조회 (ajax)
	@ResponseBody
	@RequestMapping(value="apprFormList.si", produces="application/json; charset=UTF-8")
	public String selectApprFormList() {
		
		ArrayList<Form> list = sService.selectApprFormList();
		return new Gson().toJson(list);
	}
	
	// 사용자_결재양식 불러오기 (ajax)
	@ResponseBody
	@RequestMapping(value="apprFormDetail.si", produces="application/json; charset=UTF-8")
	public String selectApprFormDetail(int formNo) {
		
		Form f = sService.selectFormDetail(formNo);
		return new Gson().toJson(f);
	}
	
	// 사용자_결재라인 조직도 조회 (ajax)
	@ResponseBody
	@RequestMapping(value="apprLineList.si", produces="application/json; charset=UTF-8")
	public String selectApprLineList() {
		
		ArrayList<Dept> deptList = sService.selectApprLineDept();
		ArrayList<Member> empList = sService.selectApprLineList();
		
		HashMap<String, Object> map = new HashMap<>();
		map.put("deptList", deptList);
		map.put("empList", empList);
		
		return new Gson().toJson(map);
	}
	
	// 사용자_결재라인 조직도 부서 조회 (ajax)
	/*
	@ResponseBody
	@RequestMapping(value="apprLineDept.si", produces="application/json; charset=UTF-8")
	public String selectApprLineDept() {
		
		ArrayList<Dept> list = sService.selectApprLineDept();
		
		return new Gson().toJson(list);
	}*/
	
	// 사용자_결재라인 사원 조회 (ajax)
	@ResponseBody
	@RequestMapping(value="apprLineEmp.si", produces="application/json; charset=UTF-8")
	public String selectApprEmp(int empNo) {
		
		Member m = sService.selectApprEmp(empNo);
		return new Gson().toJson(m);
	}
	
	
	
	
	
}
