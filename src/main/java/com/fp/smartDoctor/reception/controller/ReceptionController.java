package com.fp.smartDoctor.reception.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fp.smartDoctor.common.model.vo.PageInfo;
import com.fp.smartDoctor.common.template.Pagination;
import com.fp.smartDoctor.member.model.vo.Dept;
import com.fp.smartDoctor.member.model.vo.Member;
import com.fp.smartDoctor.reception.model.service.ReceptionService;
import com.fp.smartDoctor.treatment.model.vo.Clinic;
import com.fp.smartDoctor.treatment.model.vo.Patient;
import com.google.gson.Gson;

@Controller
public class ReceptionController {
	
	@Autowired
	private ReceptionService rService;
	
	//------------------페이지 이동 매핑---------------------
	
	// 진료 페이지
	@RequestMapping("reception.mj")
	public String reception(Model model) {
		
		ArrayList<Dept> deptList = rService.selectDeptList();
		ArrayList<Member> profList = rService.selectProfList();
//		System.out.println(deptList);
		
		model.addAttribute("deptList", deptList);
		model.addAttribute("profList", profList);
		
		return "kmj/reception";
	}
	
	// 환자 조회 페이지
	@RequestMapping("detail.mj")
	public String detail() {
		return "kmj/patientDetail";
	}
	
	// 병원 캘린더 페이지
	@RequestMapping("hospitalCalender.mj")
	public String hospitalCalender() {
		return "kmj/hospitalCalender";
	}
	
	// 개인 캘린더 페이지
	@RequestMapping("personalCalender.mj")
	public String personalCalender() {
		return "kmj/personalCalender";
	}
	
	// 예약 대기 페이지
	@RequestMapping("rsvWaiting.mj")
	public String rsvWaiting() {
		return "kmj/rsvWaiting";
	}
	
	// 입원실 현황 페이지
	@RequestMapping("room.mj")
	public String room() {
		return "kmj/room";
	}
	
	// 수납 대기 페이지
	@RequestMapping("pay.mj")
	public String pay() {
		return "kmj/payWaiting";
	}
	
	// 수납 완료 페이지
	@RequestMapping("payDone.mj")
	public String payDone() {
		return "kmj/payDone";
	}
	
	//-----------------------------------------------------
	
	
	// 환자 조회용 메소드
	@ResponseBody
	@RequestMapping(value= "list.pt", produces="apllication/json; charset=utf-8")
	public String ajaxSelectPatientList(@RequestParam(value="cpage", defaultValue="1") int currentPage) {
		int listCount = rService.selectListCount();
		PageInfo pi = Pagination.getPageInfo(listCount, currentPage, 5, 10);
		ArrayList<Patient> list = rService.selectList(pi);
		return new Gson().toJson(list); // "[{}, {}, {} ..]"
	}
	
	// 환자 등록
	@RequestMapping("insert.pt")
	public String insertPatient(Patient p, HttpSession session, Model model) {
		
		// 넘어온 첨부파일이 없을 경우 b : 제목, 작성자, 내용
		// 넘어온 첨부파일이 있을 경우 b : 제목, 작성자, 내용, 파일원본명, 파일저장경로
		System.out.println(p);
		
		int result = rService.insertPatient(p);
		
		if(result > 0) { // 성공 => alert, 게시글 리스트페이지
			session.setAttribute("alertMsg", "성공적으로 환자 등록되었습니다.");
			return "redirect:reception.mj";
		}else { // 실패 => 에러문구, 에러페이지
			model.addAttribute("errorMsg", "게시글 등록 실패");
			return "common/errorPage";
		}
		
	}
	
	// 환자 선택
	@RequestMapping("select.pt")
	public ModelAndView selectPatient(int chartNo, ModelAndView mv) {
		
		
		Patient p = rService.selectPatient(chartNo);
		
		//System.out.println(p);
		
		String beforeFV = p.getFirstVisit();
		p.setFirstVisit(beforeFV.substring(0,11));
		String beforeLV = p.getLastVisit();
		p.setLastVisit(beforeLV.substring(0,11));
		
		if(p != null) {
			mv.addObject("p",p);
			ArrayList<Dept> deptList = rService.selectDeptList();
			ArrayList<Member> profList = rService.selectProfList();
			
			mv.addObject("deptList", deptList);
			mv.addObject("profList", profList);
			mv.setViewName("kmj/reception");
			
		}else {
			mv.addObject("errorMsg", "환자 선택 실패").setViewName("common/errorPage");
		}
		
		return mv;
	}
	
	// 진료 접수
	@ResponseBody
	@RequestMapping("insert.tr")
	public String ajaxInsertTreatment(Clinic c, HttpSession session, Model model) {
		
		/*
		System.out.println(c.getChartNo());
		System.out.println(c.getDeptNo());
		System.out.println(c.getEmpNo());
		System.out.println(c.getDiagnosisContent());
		System.out.println(c);
		*/
		
		int result = rService.insertTreatment(c);
		
		return result > 0 ? "success" : "fail";
		
	}
	
	// 진료 대기, 진료중 환자 조회
	@ResponseBody
	@RequestMapping(value = "clist.pt")
	public Map<String, Object> returnMap() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		ArrayList<Clinic> wlist = rService.ajaxSelectWaitingPatient();
		
		ArrayList<Clinic> plist = rService.ajaxSelectIngPatient();

		
		/* map.put(jsp에서 사용할 이름, 넘길 자바변수);  */
		map.put("wlist", wlist);
		map.put("plist", plist);
		
		return map;
	}
	
	// 진료중으로 상태변경
	@ResponseBody
	@RequestMapping("change.pt")
	public String ajaxChangePatientStatus(@RequestParam("changeArray[]") int[] changeArray, Model model) {
		
		//System.out.println("차트번호" + changeChartNo);
		
		int result = 0;
        for(int change : changeArray) {
        	result = rService.ajaxChangePatientStatus(change);
        }
		
		//int result = rService.ajaxChangePatientStatus(changeChartNo);
		
		return result > 0 ? "success" : "fail";
	}
}
