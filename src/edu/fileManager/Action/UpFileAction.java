package edu.fileManager.Action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;

import DBJavaBean.DB;

public class UpFileAction extends ActionSupport implements ServletRequestAware {

	private String userName;
	private String uploadFileName;
	private String title;
	private int size;
	private String uploadContentType;
	
	private File upload;
	private String filePath;
	private HttpServletRequest request;
	private String message="error";
	

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	
	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}
	
	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	
	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}
	
	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		request=arg0;
	}

	@Override
	public void validate() {
		if(getTitle()==null||getTitle().length()==0){
			addFieldError("title", "文件标题不允许为空！");
		}
		
	}
	
	@Override
	public String execute() throws Exception {
		filePath=ServletActionContext.getServletContext().getRealPath("myfile/"+this.getUploadFileName());
		System.out.println(filePath);
		FileOutputStream fos = new FileOutputStream(filePath);
		FileInputStream fis = new FileInputStream(getUpload());
		size=fis.available();
		System.out.println(size);
		byte[] buffer = new byte[1024];
		int len=0;
		while((len=fis.read(buffer))>0){
			fos.write(buffer, 0, len);
		}
		fos.close();
		fis.close();
		
		DB mysql = new DB();
		userName = mysql.returnLogin(request);
		System.out.println(request);
		System.out.println(userName);
		System.out.println(title);
		System.out.println(uploadFileName);
		System.out.println(uploadContentType);
		String dd=mysql.insertFile(request, userName, title, uploadFileName, uploadContentType, size+"", getFilePath());
		if (dd.equals("ok")) {
			message=SUCCESS;
		} else if(dd.equals("one")){
			message=INPUT;
		}
		return message;
	}



	

	

	

	

	
}
