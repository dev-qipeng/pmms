package JavaBean;

import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

public class MyDayBean implements ServletRequestAware{

	private String Day;
	private String thing;
	private ResultSet rs=null;
	private HttpServletRequest request;
	public String getDay() {
		return Day;
	}
	public void setDay(String day) {
		Day = day;
	}
	public String getThing() {
		return thing;
	}
	public void setThing(String thing) {
		this.thing = thing;
	}
	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		request=arg0;
	}
	
}
