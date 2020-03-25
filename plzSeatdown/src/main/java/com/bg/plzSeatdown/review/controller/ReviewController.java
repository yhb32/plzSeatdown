package com.bg.plzSeatdown.review.controller;

import java.io.File;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bg.plzSeatdown.api.model.vo.Theater;
import com.bg.plzSeatdown.common.ExceptionForward;
import com.bg.plzSeatdown.common.FileRename;
import com.bg.plzSeatdown.common.Pagination;
import com.bg.plzSeatdown.common.vo.PageInfo;
import com.bg.plzSeatdown.member.model.vo.Member;
import com.bg.plzSeatdown.review.model.service.ReviewService;
import com.bg.plzSeatdown.review.model.vo.Review;
import com.bg.plzSeatdown.review.model.vo.ReviewImage;
import com.bg.plzSeatdown.review.model.vo.SeatReview;
import com.bg.plzSeatdown.review.model.vo.Show;
import com.bg.plzSeatdown.seat.model.vo.Seat;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@SessionAttributes({"loginMember","msg"})
@Controller
@RequestMapping("/review/*")
public class ReviewController {

	@Autowired
	private ReviewService reviewService;
	
	@RequestMapping("theater")
	public String theaterList(Model model, 
								@RequestParam(value="currentPage", required=false) Integer currentPage,
								@RequestParam(value="searchTheater", required=false) String searchTheater) {
			
		try {
			
			// 전체 공연시설장 수 조회
			int theaterCount = reviewService.getTheaterCount(searchTheater);
			
			// 현재 페이지 확인
			if(currentPage == null) currentPage = 1;
			
			// 페이지 정보 저장
			PageInfo pInf = Pagination.getPageInfo(9, 5, currentPage, theaterCount);
			
			// 공연시설장 목록 조회
			List<Theater> list = reviewService.selectTheaterList(searchTheater, pInf);
			
			model.addAttribute("list", list);
			model.addAttribute("pInf", pInf);
			
			return "review/reviewTheater";
			
		}catch(Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMsg", "공연장 목록 조회중 오류 발생");
			return "common/errorPage";
		}
	}	
	
	// 공연별
	@RequestMapping("show")
	public String showList(Model model, 
							@RequestParam(value="currentPage", required=false) Integer currentPage,
							@RequestParam(value="showStatus", required=false) String showStatus,
							@RequestParam(value="searchShow", required=false) String searchShow) {

		try {
			
			if(showStatus == null) showStatus = "공연중";
			
			Map<String, String> map = new HashMap<String, String>();
			map.put("showStatus", showStatus);
			map.put("searchShow", searchShow);
			
			// 전체 공연 수 조회
			//int showCount = reviewService.getShowCount(searchValue);
			int showCount = reviewService.getShowCount(map);
			
			// 현재 페이지 확인
			if(currentPage == null) currentPage = 1;
			
			// 페이지 정보 저장
			PageInfo pInf = Pagination.getPageInfo(8, 5, currentPage, showCount);
			
			// 공연 목록 조회
			//List<Show> list = reviewService.selectList(searchValue, pInf);
			List<Show> list = reviewService.selectShowList(map, pInf);
			
			model.addAttribute("list", list);
			model.addAttribute("pInf", pInf);
			
			return "review/reviewShow";
		
		}catch(Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMsg", "공연 목록 조회중 오류 발생");
			return "common/errorPage";
		}
	}
	
	// 공연 상세 정보 사이드 패널 Ajax
	@ResponseBody
	@RequestMapping(value="selectShowDetail", produces="application/json")
	public String selectShowDetail(String selectShowCode) {
		
		Show show = reviewService.selectShowDetail(selectShowCode);
		
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		
		//return new Gson().toJson(show);
		return gson.toJson(show);
	}
	
	// 좌석 리뷰 페이지
	@RequestMapping("seats")
	public String seats(Model model, 
						@RequestParam(value="thCode", required=false) String thCode) {
		
		try {
			
			Theater theater = reviewService.selectTheaterDetail(thCode);
			Show nowShow = reviewService.selectNowShow(thCode);
			List<SeatReview> review = reviewService.selectReviewList(thCode);
			
			model.addAttribute("review", review);
			model.addAttribute("theater", theater);
			model.addAttribute("show", nowShow);
			return "review/reviewSeats";
			
		}catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMsg", "좌석 리뷰 조회중 오류 발생");
			return "common/errorPage";
		}
		
	}
	
	// 리뷰 작성 화면 전환
	@RequestMapping(value="write")
	public String reviewWrite(Model model,
			RedirectAttributes rdAttr,
			HttpServletRequest request) {
		try {
			String referer = request.getHeader("Referer");
			String view = null;
			List<Theater> tList = reviewService.selectTList();
			if(tList != null) {
				model.addAttribute("tList", tList);
				view = "review/reviewWrite";
			}else {
				rdAttr.addFlashAttribute("msg", "리뷰 작성 화면 전환 실패");
				view = "redirect:"+referer;
			}
			return view;
		}catch (Exception e) {
			return ExceptionForward.errorPage("리뷰 작성화면 전환", model, e);
		}
	}
	
	// 공연 목록 조회
	@ResponseBody
	@RequestMapping(value="selectShowList", produces="application/json; charset=utf-8")
	public String selectShowList(
			@RequestParam(value="showDt", required = false)String date,
			@RequestParam(value="theater", required = false)String thName){
		try {
			Date showDate = Date.valueOf(date);
			String thCode = reviewService.selectTheaterCode(thName);
			Show show = new Show(showDate, thCode);
			List<Show> sList = reviewService.selectSList(show);
			
			return new Gson().toJson(sList);
		}catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	
	
	// 층 목록 조회
	@ResponseBody
	@RequestMapping(value="selectSeatFloor", produces="application/json; charset=utf-8")
	public String selectFloorList(
			@RequestParam(value = "theater", required = false)String thName) {
		try {
			String thCode = reviewService.selectTheaterCode(thName);
			List<String> fList = reviewService.selectFList(thCode);
			return new Gson().toJson(fList);
		}catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	
	
	// 구역 목록 조회
	@ResponseBody
	@RequestMapping(value="selectSeatArea", produces="application/json; charset=utf-8")
	public String selectAreaList(
			@RequestParam(value = "theater", required = false)String thName,
			@RequestParam(value = "seatFloor", required = false)String seatFloor) {
		try {
			String thCode = reviewService.selectTheaterCode(thName);
			Seat seat = new Seat(seatFloor, thCode);
			List<String> aList = reviewService.selectAList(seat);
			return new Gson().toJson(aList);
		}catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	
	
	// 열 목록 조회 (구역 없음)
	@ResponseBody
	@RequestMapping(value="selectSeatRow1", produces="application/json; charset=utf-8")
	public String selectRowList(
			@RequestParam(value = "theater", required = false)String thName,
			@RequestParam(value = "seatFloor", required = false)String seatFloor) {
		try {
			String thCode = reviewService.selectTheaterCode(thName);
			Seat seat = new Seat(seatFloor, thCode);
			List<String> rList = reviewService.selectRList(seat);
			return new Gson().toJson(rList);
		}catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	
	// 열 목록 조회 (구역 없음)
	@ResponseBody
	@RequestMapping(value="selectSeatRow2", produces="application/json; charset=utf-8")
	public String selectRowList2(
			@RequestParam(value = "theater", required = false)String thName,
			@RequestParam(value = "seatFloor", required = false)String seatFloor,
			@RequestParam(value = "seatArea", required = false)String seatArea) {
		try {
			String thCode = reviewService.selectTheaterCode(thName);
			Seat seat = new Seat(seatFloor, seatArea, thCode);
			List<String> rList = reviewService.selectRList2(seat);
			return new Gson().toJson(rList);
		}catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	
	// 번호 목록 조회
	@ResponseBody
	@RequestMapping(value="selectSeatCol", produces="application/json; charset=utf-8")
	public String selectColList(
			@RequestParam(value = "theater", required = false)String thName,
			@RequestParam(value = "seatFloor", required = false)String seatFloor,
			@RequestParam(value = "seatArea", required = false)String seatArea,
			@RequestParam(value = "seatRow", required = false)String seatRow) {
		try {
			String thCode = reviewService.selectTheaterCode(thName);
			Seat seat = null;
			List<String> cList = null;
			if(seatArea.equals("-1")) {
				seat = new Seat(0, seatFloor, seatRow, thCode);
				cList = reviewService.selectCList(seat);
			}else {
				seat = new Seat(seatFloor, seatArea, seatRow, thCode);
				cList = reviewService.selectCList2(seat);
			}
			return new Gson().toJson(cList);
		}catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	
	@ResponseBody
	@RequestMapping(value="selectSeatCode", produces="application/json; charset=utf-8")
	public String selectSeatCode(
			@RequestParam(value = "theater", required = false)String thName,
			@RequestParam(value = "seatFloor", required = false)String seatFloor,
			@RequestParam(value = "seatArea", required = false)String seatArea,
			@RequestParam(value = "seatRow", required = false)String seatRow,
			@RequestParam(value = "seatCol", required = false)String seatCol) {
		try {
			String thCode = reviewService.selectTheaterCode(thName);
			Seat seat = null;
			String result = null;
			if(seatArea.equals("-1")) {
				seat = new Seat(0, seatFloor, seatRow, seatCol, thCode);
				result = reviewService.selectSeatCode(seat);
			}else {
				seat = new Seat(seatFloor, seatArea, seatRow, seatCol, thCode);
				result = reviewService.selectSeatCode2(seat);
			}
			return new Gson().toJson(result);
		}catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	
	@RequestMapping(value="writeReview")
	public String insertReview(Review review, Model model,
			HttpServletRequest request, // 파일경로
			RedirectAttributes rdAttr,
			@RequestParam(value="thName", required=false)String thName,
			@RequestParam(value="seatFile", required=false)MultipartFile seatImg,
			@RequestParam(value="ticketFile", required=false)MultipartFile ticketImg) {
		
		// Session에서 회원번호 얻어오기
		Member loginMember = (Member)model.getAttribute("loginMember");
		int reviewWriter = loginMember.getMemberNo();
		
		review.setReviewWriter(reviewWriter);
		
		// 파일 저장 경로
		String root = request.getSession().getServletContext().getRealPath("resources");
		String savePath = root + "/reviewImages";
		
		// 저장 폴더 선택
		File folder = new File(savePath);
		if(!folder.exists()) folder.mkdir();
		
		try {
			String seatCode = review.getSeatCode();
			String thCode = reviewService.selectTheaterCode(thName);
			List<ReviewImage> files = new ArrayList<ReviewImage>();
			ReviewImage ri = null;
			
			if(!seatImg.getOriginalFilename().equals("")) {
				String changeFileName = FileRename.rename(seatImg.getOriginalFilename());
				ri = new ReviewImage(changeFileName);
				ri.setImageType(0);
				files.add(ri);
			}
			if(!ticketImg.getOriginalFilename().equals("")) {
				String changeFileName = FileRename.rename(ticketImg.getOriginalFilename());
				ri = new ReviewImage(changeFileName);
				ri.setImageType(1);
				files.add(ri);
			}
			
			int result = reviewService.insertReview(review, files);
			
			String msg = null;
			String url = null;
			
			if(result > 0) {
				for(ReviewImage file : files) {
					if(file.getImageType() == 0) {
						seatImg.transferTo(new File(savePath+"/"+file.getReviewImagePath()));
					}else {
						ticketImg.transferTo(new File(savePath+"/"+file.getReviewImagePath()));
					}
				}
				msg = "리뷰 등록 성공";
				url = "/";
			}else {
				msg = "리뷰 등록 실패";
				url = "write";
			}
			rdAttr.addFlashAttribute("msg", msg);
			return "redirect:"+url;
		}catch (Exception e) {
			return ExceptionForward.errorPage("리뷰 작성", model, e);
		}
	}
}
