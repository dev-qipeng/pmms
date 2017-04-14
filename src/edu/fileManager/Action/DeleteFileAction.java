package edu.fileManager.Action;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;

import DBJavaBean.DB;

public class DeleteFileAction extends ActionSupport implements ServletRequestAware {

	private String userName;
	private String fileName;
	private String title;
	private HttpServletRequest request;
	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		request=arg0;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	@Override
	public String execute() throws Exception {
		DB mysql = new DB();
		userName=mysql.returnLogin(request);
		String dd=mysql.deleteFile(request, userName, title);
		File file = new File(ServletActionContext.getServletContext().getRealPath("myfile/"+getFileName()));
		if(file.exists()&&dd.equals("ok")){
			file.delete();
			return SUCCESS;
		}else{
			return ERROR;
		}
	}
	

}
