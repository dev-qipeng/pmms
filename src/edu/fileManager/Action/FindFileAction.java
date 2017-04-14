package edu.fileManager.Action;

import javax.servlet.http.HttpServletRequest;
import javax.swing.JOptionPane;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;

import DBJavaBean.DB;

public class FindFileAction extends ActionSupport implements ServletRequestAware {

	private String userName;
	private String uploadFileName;
	private String title;
	
	private HttpServletRequest request;
	private String message="error";
	
	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		request=arg0;
	}
	
	public void message(String msg){
		JOptionPane.showMessageDialog(null, msg, "信息展示", JOptionPane.YES_NO_CANCEL_OPTION);
	}

	@Override
	public void validate() {
		if (this.getTitle()==null||this.getTitle().length()==0) {
			addFieldError(title, "文件标题不允许为空！");
		}
	}
	
	@Override
	public String execute() throws Exception {
		DB mysql = new DB();
		userName=mysql.returnLogin(request);
		String dd=mysql.findFile(request, userName,title);
		if (dd.equals("ok")) {
			message=SUCCESS;
		}
		return message;
	}
}
