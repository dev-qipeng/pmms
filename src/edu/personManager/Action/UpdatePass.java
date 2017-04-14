package edu.personManager.Action;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;

import DBJavaBean.DB;

public class UpdatePass extends ActionSupport implements ServletRequestAware {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String password1;
	private String password2;
	private String userName;
	private HttpServletRequest request;
	private String message=INPUT;
	
	public String getPassword1() {
		return password1;
	}

	public void setPassword1(String password1) {
		this.password1 = password1;
	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		request=arg0;
	}

	@Override
	public void validate() {
		if(!(password1.equals(password2))){
			addFieldError("password2", "两次密码不同");
		}
	}

	@Override
	public String execute() throws Exception {
		DB mysql= new DB();
		userName=mysql.returnLogin(request);
		System.out.println("userName:"+userName);
		String pass=mysql.updatePass(request, userName, getPassword1());
		System.out.println("pass:"+pass);
		if(pass.equals("ok")){
			message=SUCCESS;
		}
		return message;
	}
	
}
