package edu.fileManager.Action;

import java.io.InputStream;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;

public class DownFileAction extends ActionSupport implements ServletRequestAware {

	private String fileName;
	private HttpServletRequest request;

	public String getFileName() {
		return fileName;
	}
	
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		request=arg0;
	}

	public InputStream getInputStream() throws Exception{
		
		return ServletActionContext.getServletContext().getResourceAsStream("myfile/"+getFileName());
	}

	public String execute() throws Exception {
		System.out.println(this.getFileName());
		return SUCCESS;
	}

}
