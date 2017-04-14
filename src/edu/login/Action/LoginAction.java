package edu.login.Action;

import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;

import DBJavaBean.DB;

public class LoginAction extends ActionSupport implements ServletRequestAware{
	private String userName;
	private String password;
	private ResultSet rs=null;
	private String message=ERROR;
	private HttpServletRequest request;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		request=arg0;
	}
	
	public void validate(){
		if(this.getUserName()==null||this.getUserName().length()==0){
			addFieldError("userName", "请输入登陆名字！");
		}else {
			try {
				DB mysql=new DB();
				rs=mysql.selectMess(request,this.getUserName());
				if (!rs.next()) {
					addFieldError("userName", "此用户尚未注册！");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(this.getPassword()==null||this.getPassword().length()==0){
			addFieldError("password", "请输入登录密码！");
		}else {
			try {
				DB mysql=new DB();
				rs=mysql.selectMess(request,this.getUserName());
				if (rs.next()) {
					rs=mysql.selectLogin(request,this.getUserName(),this.getPassword());
					if (!rs.next()) {
						addFieldError("password", "登录密码错误！");
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	@Override
	public String execute() throws Exception {
		DB mysql=new DB();
		String add=mysql.addList(request,this.getUserName());
		if (add.equals("ok")) {
			message=SUCCESS;
		}
		return message;
	}
}
