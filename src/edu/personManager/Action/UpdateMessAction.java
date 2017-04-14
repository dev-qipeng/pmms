package edu.personManager.Action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;

import DBJavaBean.DB;

public class UpdateMessAction extends ActionSupport implements ServletRequestAware {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String sex;
	private String birth;
	private String nation;
	private String edu;
	private String work;
	private String phone;
	private String place;
	private String email;
	private String userName;
	private HttpServletRequest request;
	private String message=ERROR;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public String getEdu() {
		return edu;
	}
	public void setEdu(String edu) {
		this.edu = edu;
	}
	public String getWork() {
		return work;
	}
	public void setWork(String work) {
		this.work = work;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}


	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public void setServletRequest(HttpServletRequest arg0) {

		request=arg0;
	}
	
	@Override
	public void validate() {
		if (getName()==null||getName().length()==0) {
			addFieldError("name", "用户姓名不允许为空！");
		}
		if (getSex()==null||getSex().length()==0) {
			addFieldError("sex", "用户性别不能为空");
		}
		if (getBirth()==null||getBirth().length()==0||getBirth().equals("yyyy-mm-dd")) {
			addFieldError("birth", "用户生日不允许为空！");
		}else {
			if (getBirth().length()!=10) {
				addFieldError("birth", "用户生日格式为'yyyy-mm-dd'！");
			} else {
				String an=this.getBirth().substring(4,5);
				String bn=this.getBirth().substring(7, 8);
				if(!(an.equals("-"))||!(bn.equals("-"))){
					addFieldError("birth", "用户生日格式为'yyy-mm-dd'！");
				}
			}
		}
		if (getNation()==null||getNation().length()==0) {
			addFieldError("nation", "用户民族不允许为空!");
		}
		if (getEdu().equals("1")) {
			addFieldError("edu", "请选择用户学历！");
		}
		if(getWork().equals("1")){
			addFieldError("word", "请选择用户工作！");
		}
		if (getPhone()==null||getPhone().length()==0) {
			addFieldError("phone", "用户电话不允许为空！");
		}
		if (getPlace()==null||getPlace().length()==0) {
			addFieldError("place", "用户地址不允许为空！");
		}
		if (getEmail()==null||getEmail().length()==0) {
			addFieldError("email", "用户email不允许为空！");
		}
	}
	
	@Override
	public String execute() throws Exception {
		DB mysql = new DB();
		userName=mysql.returnLogin(request);
		String mess=mysql.updateMess(request, userName, getName(), getSex(), this.getBirth(), this.getNation(), this.getEdu(), this.getWork(), this.getPhone(), this.getPlace(), this.getEmail());
		if (mess.equals("ok")) {
			message=SUCCESS;
		}else {
			System.out.println("修改失败");
		}
		return message;
	}
	

}
