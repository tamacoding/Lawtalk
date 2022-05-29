package com.lawknow.userWrite;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lawyer.action.ActionInfo;

public class UserWriteFrontController {
	
	  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	       doProcess(req, resp);
	    }
	    
	    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	       doProcess(req, resp);
	    }

	    protected void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	       String requestURL = req.getRequestURI();
	       String command = requestURL.substring(requestURL.lastIndexOf("/") + 1);
	       ActionInfo actionInfo = null;
	       
	       if(command.equals("UserWriteOk.uwfc")) {
	          actionInfo = new UserWriteOk().execute(req, resp);
	       }
	       else if(command.equals("UserWrite.uwfc")) { // 글 작성
	          actionInfo = new ActionInfo();
	           actionInfo.setRedirect(true);
	           actionInfo.setPath(req.getContextPath() + "/write.jsp");
	       } 
	       else if(command.equals("UserWriteUpdateOk.uwfc")) { // 글 수정
	          actionInfo = new UserWriteUpdateOk().execute(req, resp);
	       } else if(command.equals("UserWriteDeleteOk.uwfc")) {// 글 삭제
	          actionInfo = new UserWriteDeleteOk().execute(req, resp);
	       }else if(command.equals("UserRequestNumOk.uwfc")) {// 번호 조회
	          actionInfo = new UserRequestNumOk().execute(req, resp);
	       }else if(command.equals("UserSelectFieldOk.uwfc")) {
	    	   actionInfo = new UserSelectFieldOk().execute(req, resp);
	       }
	       else {
	          // 404 일 때 출력할 에러 페이지 경로 작성
	       }
	       
	       
	       if(actionInfo != null) {
	          if(actionInfo.isRedirect()) {
	             resp.sendRedirect(actionInfo.getPath());
	          }else {
	             RequestDispatcher dispatcher = req.getRequestDispatcher(actionInfo.getPath());
	             dispatcher.forward(req, resp);
	          }
	       }
	       
	    }

}
