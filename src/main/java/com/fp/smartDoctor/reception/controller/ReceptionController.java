package com.fp.smartDoctor.reception.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
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
import com.fp.smartDoctor.reception.model.vo.Prescription;
import com.fp.smartDoctor.reception.model.vo.ProomCalendar;
import com.fp.smartDoctor.reception.model.vo.Receipt;
import com.fp.smartDoctor.treatment.model.vo.Clinic;
import com.fp.smartDoctor.treatment.model.vo.Medicine;
import com.fp.smartDoctor.treatment.model.vo.Patient;
import com.google.gson.Gson;

@Controller
public class ReceptionController {

	@Autowired
	private ReceptionService rService;

	// ------------------페이지 이동
	// 매핑------------------------------------------------------------------------------------

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

	// 환자 리스트 조회 페이지
	@RequestMapping("list.mj")
	public ModelAndView selectList(@RequestParam(value = "cpage", defaultValue = "1") int currentPage,
			ModelAndView mv) {

		// 전체 환자 수 조회
		int listCount = rService.selectListCount();

		PageInfo pi = Pagination.getPageInfo(listCount, currentPage, 5, 5);
		ArrayList<Patient> list = rService.selectList(pi);

		mv.addObject("pi", pi).addObject("list", list).setViewName("kmj/patientList");

		return mv;
	}

	// 환자 상세 조회 페이지
	@RequestMapping("detail.pt")
	public ModelAndView detail(@RequestParam(value = "cpage", defaultValue = "1") int currentPage, int chartNo,
			ModelAndView mv) {

		// 전체 진료 수 조회
		int listCount = rService.pastClinicListCount(chartNo);

		PageInfo pi = Pagination.getPageInfo(listCount, currentPage, 5, 5);

		// 환자 정보 조회
		Patient p = rService.selectPatient(chartNo);

		// 진료 내역 조회
		ArrayList<Clinic> list = rService.pastClinicList(pi, chartNo);

		mv.addObject("p", p).addObject("pi", pi).addObject("chartNo", chartNo).addObject("list", list)
				.setViewName("kmj/patientDetail");

		return mv;
	}

	// 개인 처방전 조회
	@RequestMapping("prescription.pt")
	public ModelAndView prescription(int clinicNo, ModelAndView mv) {

		// 처방전 -> 진료 정보 조회
		Prescription ps = rService.selectPrescription(clinicNo);

		// 처방전 -> 약 정보 조회
		ArrayList<Medicine> mlist = rService.selectMedicineList(clinicNo);

		mv.addObject("ps", ps).addObject("mlist", mlist).setViewName("kmj/prescription");

		return mv;
	}

	// 개인 수납 조회
	@RequestMapping("receipt.pt")
	public ModelAndView receipt(int clinicNo, ModelAndView mv) {

		// 처방전 -> 진료 정보 조회
		Receipt r = rService.selectReceipt(clinicNo);

		mv.addObject("r", r).setViewName("kmj/receipt");

		return mv;
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
	public String room(@RequestParam(value = "cDate", defaultValue = "0") String cDate, Model model) throws ParseException {

		// thead에 들어갈 입원실 목록 조회
		ArrayList<ProomCalendar> roomList = rService.selectPRoomList();
		model.addAttribute("roomList", roomList);

		if (cDate.equals("0")) { // 전달받은 cDate가 없으면 현재달로 입원실 현황 조회

			// 오늘 날짜 구해서 String으로 형변환
			GregorianCalendar cal = new GregorianCalendar();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String nowDate = sdf.format(cal.getTime());

			// 화면에 출력할 현재날짜(yyyy년 mm월)
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy년 MM월");
			String showDate = sdf2.format(cal.getTime());

			// 전월 날짜 구하기
			cal.add(GregorianCalendar.MONTH, -1);
			String preDate = sdf.format(cal.getTime());

			// 다음 날짜 구하기
			cal.add(GregorianCalendar.MONTH, +2);
			String nextDate = sdf.format(cal.getTime());

			// String으로 형변환한 오늘 날짜 전달해서 이번달 1일~말일 구하기
			ArrayList<ProomCalendar> dayList = rService.selectDateList(nowDate);

			//System.out.println(dayList);

			// 예약중인 환자 리스트 조회
			ArrayList<ProomCalendar> bookingList = rService.selectPRoomBookingList(nowDate);

			System.out.println(bookingList);

			model.addAttribute("dayList", dayList);
			model.addAttribute("showDate", showDate);
			model.addAttribute("preDate", preDate);
			model.addAttribute("nextDate", nextDate);
			model.addAttribute("bookingList", bookingList);
		} else {

			// 오늘 날짜 구해서 String으로 형변환
			GregorianCalendar cal = new GregorianCalendar();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String nowDate = cDate;
			
			// 전달받은 날짜 Date로 변환
			Date tempt = sdf.parse(cDate);
			
			// 전달받은 날짜 대입
			cal.setTime(tempt);
			
			// 화면에 출력할 현재날짜(yyyy년 mm월)
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy년 MM월");
			String showDate = sdf2.format(cal.getTime());

			// 전월 날짜 구하기
			cal.add(GregorianCalendar.MONTH, -1);
			String preDate = sdf.format(cal.getTime());

			// 다음 날짜 구하기
			cal.add(GregorianCalendar.MONTH, +2);
			String nextDate = sdf.format(cal.getTime());

			// String으로 형변환한 오늘 날짜 전달해서 이번달 1일~말일 구하기
			ArrayList<ProomCalendar> dayList = rService.selectDateList(nowDate);

			System.out.println(dayList);

			// 예약중인 환자 리스트 조회
			ArrayList<ProomCalendar> bookingList = rService.selectPRoomBookingList(nowDate);

			System.out.println(bookingList);

			model.addAttribute("dayList", dayList);
			model.addAttribute("showDate", showDate);
			model.addAttribute("preDate", preDate);
			model.addAttribute("nextDate", nextDate);
			model.addAttribute("bookingList", bookingList);
		}

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

	// ------------------------------------------------------------------------------------------------------------------------

	// 환자 조회용 메소드
	@ResponseBody
	@RequestMapping(value = "list.pt", produces = "apllication/json; charset=utf-8")
	public String ajaxSelectPatientList(@RequestParam(value = "cpage", defaultValue = "1") int currentPage) {
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

		if (result > 0) { // 성공 => alert, 게시글 리스트페이지
			session.setAttribute("alertMsg", "성공적으로 환자 등록되었습니다.");
			return "redirect:reception.mj";
		} else { // 실패 => 에러문구, 에러페이지
			model.addAttribute("errorMsg", "게시글 등록 실패");
			return "common/errorPage";
		}

	}

	// 환자 선택
	@RequestMapping("select.pt")
	public ModelAndView selectPatient(int chartNo, ModelAndView mv) {

		Patient p = rService.selectPatient(chartNo);

		// System.out.println(p);

		String beforeFV = p.getFirstVisit();
		p.setFirstVisit(beforeFV.substring(0, 11));
		String beforeLV = p.getLastVisit();
		p.setLastVisit(beforeLV.substring(0, 11));

		if (p != null) {
			mv.addObject("p", p);
			ArrayList<Dept> deptList = rService.selectDeptList();
			ArrayList<Member> profList = rService.selectProfList();

			mv.addObject("deptList", deptList);
			mv.addObject("profList", profList);
			mv.setViewName("kmj/reception");

		} else {
			mv.addObject("errorMsg", "환자 선택 실패").setViewName("common/errorPage");
		}

		return mv;
	}

	// 진료 접수
	@ResponseBody
	@RequestMapping("insert.tr")
	public String ajaxInsertTreatment(Clinic c, HttpSession session, Model model) {

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

		/* map.put(jsp에서 사용할 이름, 넘길 자바변수); */
		map.put("wlist", wlist);
		map.put("plist", plist);

		return map;
	}

	// 진료중으로 상태변경
	@ResponseBody
	@RequestMapping("change.pt")
	public String ajaxChangePatientStatus(@RequestParam("changeArray[]") int[] changeArray, Model model) {

		// System.out.println("차트번호" + changeChartNo);

		int result = 0;
		for (int change : changeArray) {
			result = rService.ajaxChangePatientStatus(change);
		}

		// int result = rService.ajaxChangePatientStatus(changeChartNo);

		return result > 0 ? "success" : "fail";
	}

	// 예약 대기 환자 조회
	@ResponseBody
	@RequestMapping(value = "rsvWaitinglist.pt")
	public Map<String, Object> returnRsvWaitingMap() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();

		ArrayList<Clinic> slist = rService.ajaxSurgeryWaitingList();
		ArrayList<Clinic> plist = rService.ajaxPRoomWaitingList();

		/* map.put(jsp에서 사용할 이름, 넘길 자바변수); */
		map.put("slist", slist);
		map.put("plist", plist);

		return map;
	}

	// 예약 완료 후 surgery 상태 변경
	@ResponseBody
	@RequestMapping("updateSurgery.cl")
	public int ajaxUpdateClinicSurgery(@RequestParam("clinicNo") int clinicNo, HttpSession session, Model model) {

		int result = rService.ajaxUpdateClinicSurgery(clinicNo);

		return result > 0 ? clinicNo : null;

	}

	// 예약 완료 후 enter 상태 변경
	@ResponseBody
	@RequestMapping("updateEnter.cl")
	public int ajaxUpdateClinicEnter(@RequestParam("clinicNo") int clinicNo, HttpSession session, Model model) {

		int result = rService.ajaxUpdateClinicEnter(clinicNo);

		return result > 0 ? clinicNo : null;

	}
}