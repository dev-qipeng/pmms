package DBJavaBean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

import org.apache.struts2.interceptor.ServletRequestAware;

import JavaBean.MyDayBean;
import JavaBean.MyFileBean;
import JavaBean.MyMessBean;
import JavaBean.UserNameBean;

public class DB implements ServletRequestAware {

	private String driverName="com.mysql.jdbc.Driver";
	private String url="jdbc:mysql://localhost:3306/pmms?useUnicode=true&characterEncoding=gbk";
	private String user="root";
	private String password="";
	private Connection con=null;	
	private Statement st=null;
	private ResultSet rs=null;
	public DB() {
	}
	
	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
	}
	public Statement getStatement(){
		try {
			Class.forName(getDriverName());
			con=DriverManager.getConnection(getUrl(), getUser(), getPassword());
			return con.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public String insertMess(HttpServletRequest request,String userName,String password,String name,String sex,String birth,String nation,String edu,String work,String phone,String place,String email){
		try {
			String sure=null;
			rs=selectMess(request,userName);
			if (rs.next()) {
				sure="one";
			} else {
				String sql="insert into user (userName,password,name,sex,birth,nation,edu,work,phone,place,email)"
						+ "values('"+userName+"'"+","+"'"+password+"'"+","+"'"+name+"'"+","+"'"+sex+"'"+","+"'"+birth+"'"+","+"'"+nation+"'"+","+"'"+edu+"'"+","+"'"+work+"'"+","+"'"+phone+"'"+","+"'"+place+"'"+","+"'"+email+"'"+")";
				st=getStatement();
				int row=st.executeUpdate(sql);
				if (row==1) {
					String mess=myMessage(request,userName);
					if (mess.equals("ok")) {
						sure="ok";
					} else {
						sure=null;
					}
				} else {
					sure=null;
				}
			}
			return sure;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public String updateMess(HttpServletRequest request,String userName,String name,String sex,String birth,String nation,String edu,String work,String phone,String place,String email){
		try {
			String sure=null;
			String sql="update user set name='"+name+"',sex='"+sex+"',birth='"+birth+"',nation='"+nation+"',edu='"+edu+"',work='"+work+"',phone='"+phone+"',place='"+place+"',email='"+email+"' where userName='"+userName+"'";
			st=getStatement();
			int row=st.executeUpdate(sql);
			if (row==1) {
				String mess=myMessage(request,userName);
				if (mess.equals("ok")) {
					sure="ok";
				} else {
					sure=null;
				}
			} else {
				sure=null;
			}
			return sure;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public ResultSet selectMess(HttpServletRequest request,String userName){
		try {
			String sql="select * from user where userName='"+userName+"'";
			st=getStatement();
			return st.executeQuery(sql);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public String myMessage(HttpServletRequest request,String userName){
		try {
			ArrayList<MyMessBean> listName=null;
			HttpSession session=request.getSession();
			listName=new ArrayList<MyMessBean>();
			rs=selectMess(request, userName);
			while(rs.next()){
				MyMessBean mess=new MyMessBean();
				mess.setName(rs.getString("name"));
				mess.setSex(rs.getString("sex"));
				mess.setBirth(rs.getString("birth"));
				mess.setNation(rs.getString("nation"));
				mess.setEdu(rs.getString("edu"));
				mess.setWork(rs.getString("work"));
				mess.setPhone(rs.getString("phone"));
				mess.setPlace(rs.getString("place"));
				mess.setEmail(rs.getString("email"));
				listName.add(mess);
				session.setAttribute("MyMess", listName);
			}
			return "ok";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public String insertDay(HttpServletRequest request,String userName,String date,String thing){
		try {
			String sure=null;
			rs=selectDay(request,userName,date);
			if (rs.next()) {
				sure="one";
			} else {
				String sql="insert into date (userName,date,thing)"
						+ "values('"+userName+"'"+","+"'"+date+"'"+","+"'"+thing+"'"+")";
				st=getStatement();
				int row=st.executeUpdate(sql);
				if (row==1) {
					String day=myDayTime(request,userName);
					if (day.equals("ok")) {
						sure="ok";
					} else {
						sure=null;
					}
				} else {
					sure=null;
				}
			}
			return sure;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public String deleteDay(HttpServletRequest request,String userName,String date){
		try {
			String sure=null;
			String sql="delete from date where userName='"+userName+"' and date='"+date+"'";
			st=getStatement();
			int row=st.executeUpdate(sql);
			if (row==1) {
				String day=myDayTime(request,userName);
				if (day.equals("ok")) {
					sure="ok";
				} else {
					sure=null;
				}
			} else {
				sure=null;
			}
			return sure;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public String updateDay(HttpServletRequest request,String userName,String Day,String date,String thing){
		try {
			String sure=null;
			String del=deleteDay(request, userName, Day);
				if (del.equals("ok")) {
					String in=insertDay(request, userName, date, thing);
					if (in.equals("ok")) {
						String day=myDayTime(request, userName);
						if (day.equals("ok")) {
							sure="ok";
						} else {
							sure=null;
						}
					} else {
						sure=null;
					}
				} else {
					sure=null;
				}
				return sure;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public ResultSet selectDay(HttpServletRequest request,String userName,String date){
		try {
			String sql="select * from date where userName='"+userName+"'and date='"+date+"'";
			st=getStatement();
			return st.executeQuery(sql);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public ResultSet selectDayAll(HttpServletRequest request,String userName){
		try {
			String sql="select * from date where userName='"+userName+"'";
			st=getStatement();
			return st.executeQuery(sql);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public String myDayTime(HttpServletRequest request, String userName) {
		try {
			ArrayList<MyDayBean> listName=null;
			HttpSession session=request.getSession();
			listName=new ArrayList<MyDayBean>();
			rs=selectDayAll(request, userName);
			if (rs.next()) {
				rs=selectDayAll(request, userName);
				while(rs.next()){
					MyDayBean mess=new MyDayBean();
					mess.setDay(rs.getString("date"));
					mess.setThing(rs.getString("thing"));
					listName.add(mess);
					session.setAttribute("day", listName);
				}
			} else {
				session.setAttribute("day", listName);
			}
			return "ok";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public String insertFile(HttpServletRequest request,String userName,String title,String name,String contentType,String size,String filePath){
		try {
			String sure=null;
			rs=selectFile(request, userName,title);
			if (rs.next()) {
				sure="title";
			} else {
				rs=selectFile(request,userName,name);
				if(rs.next()){
					sure="name";
				}else{
					String sql="insert into file(userName,title,name,contentType,size,filePath) values('"+userName+"'"+","+"'"+title+"'"+","+"'"+name+"'"+","+"'"+contentType+"'"+","+"'"+size+"'"+","+"'"+filePath+"'"+")";
					st=getStatement();
					int row=st.executeUpdate(sql);
					if (row==1) {
						String file=myFile(request,userName);
						if (file.equals("ok")) {
							sure="ok";
						} else {
							sure=null;
						}
					} else {
						sure=null;
					}
				}
				
			}
			return sure;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public String deleteFile(HttpServletRequest request,String userName,String title){
		try {
			String sure=null;
			String sql="delete from file where userName='"+userName+"' and title='"+title+"'";
			st=getStatement();
			int row=st.executeUpdate(sql);
			if (row==1) {
				String file=myFile(request,userName);
				if (file.equals("ok")) {
					sure="ok";
				} else {
					sure=null;
				}
			} else {
				sure=null;
			}
			return sure;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public String updateFile (HttpServletRequest request,String userName,String Title,String title,String name,String contentType,String size,String filePath){
		try {
			String sure=null;
			String del=deleteFile(request, userName, Title);
				if (del.equals("ok")) {
					String in=insertFile(request, userName, title, name, contentType, size, filePath);
					if (in.equals("ok")) {
						String file=myFile(request, userName);
						if (file.equals("ok")) {
							sure="ok";
						} else {
							sure=null;
						}
					} else {
						sure=null;
					}
				} else {
					sure=null;
				}
				return sure;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public ResultSet selectFile(HttpServletRequest request,String userName,String title){
		try {
			String sql="select * from file where userName='"+userName+"' and title='"+title+"'";
			st=getStatement();
			return st.executeQuery(sql);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public ResultSet selectFileAll(HttpServletRequest request,String userName){
		try {
			String sql="select * from file where userName='"+userName+"'";
			st=getStatement();
			return st.executeQuery(sql);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public String myFile(HttpServletRequest request, String userName) {
		try {
			ArrayList<MyFileBean> listName=null;
			HttpSession session=request.getSession();
			listName=new ArrayList<MyFileBean>();
			rs=selectFileAll(request, userName);
			if (rs.next()) {
				rs=selectFileAll(request, userName);
				while(rs.next()){
					MyFileBean mess=new MyFileBean();
					mess.setTitle(rs.getString("title"));
					mess.setName(rs.getString("name"));
					mess.setContentType(rs.getString("contentType"));
					mess.setSize(rs.getString("size"));
					listName.add(mess);
					session.setAttribute("file", listName);
				}
			} else {
				session.setAttribute("file", listName);
			}
			return "ok";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public ResultSet selectLogin(HttpServletRequest request, String userName, String password) {
		try {
			String sql="select * from user where userName='"+userName+"'and password='"+password+"'";
			st=getStatement();
			return st.executeQuery(sql);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public String myLogin(HttpServletRequest request, String userName) {
		try {
			ArrayList<UserNameBean> listName=null;
			HttpSession session=request.getSession();
			listName=new ArrayList<UserNameBean>();
			rs=selectMess(request,userName);
			if (rs.next()) {
				rs=selectMess(request,userName);
				while(rs.next()){
					UserNameBean mess=new UserNameBean();
					mess.setUserName(rs.getString("userName"));
					mess.setPassword(rs.getString("password"));
					listName.add(mess);
					session.setAttribute("username", listName);
				}
			} else {
				session.setAttribute("username", listName);
			}
			return "ok";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public String returnLogin(HttpServletRequest request){
		String LoginName=null;
		HttpSession session=request.getSession();
		ArrayList<?> login=(ArrayList<?>) session.getAttribute("username");
		if(login==null||login.size()==0){
			LoginName=null;
		}else{
			for (int i = login.size()-1; i >=0; i--) {
				UserNameBean nm=(UserNameBean)login.get(i);
				LoginName=nm.getUserName();
			}
		}
		return LoginName;
	}
	
	public String addList(HttpServletRequest request, String userName) {
		String sure=null;
		String login=myLogin(request,userName);
		String mess=myMessage(request,userName);
		String day=myDayTime(request,userName);
		String file=myFile(request,userName);
		if (login.equals("ok")&&mess.equals("ok")&&day.equals("ok")&&file.equals("ok")) {
			sure="ok";
		} else {
			sure=null;
		}
		return sure;
	}
	
	public String updatePass(HttpServletRequest request,String userName,String password){
		try {
			String sure=null;
			String sql="update user set password='"+password+"' where userName='"+userName+"'";
			st=getStatement();
			int row=st.executeUpdate(sql);
			System.out.println("row:"+row);
			if (row==1) {
				String mess=myLogin(request, userName);
				if (mess.equals("ok")) {
					sure="ok";
				} else {
					sure=null;
				}
			} else {
				sure=null;
			}
			return sure;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public String findDay(HttpServletRequest request,String userName,String date) {
		try {
			ArrayList<MyDayBean> listName=null;
			HttpSession session=request.getSession();
			listName=new ArrayList<MyDayBean>();
			rs=selectDay(request, userName,date);
			if (rs.next()) {
				rs=selectDay(request, userName,date);
				while(rs.next()){
					MyDayBean mess=new MyDayBean();
					mess.setDay(rs.getString("date"));
					mess.setThing(rs.getString("thing"));
					listName.add(mess);
					session.setAttribute("findday", listName);
				}
			} else {
				session.setAttribute("findday", listName);
			}
			return "ok";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public String returnDay(HttpServletRequest request){
		String date=null;
		HttpSession session=request.getSession();
		ArrayList<?> login=(ArrayList<?>) session.getAttribute("findday");
		if (login==null||login.size()==0) {
			date=null;
		} else {
			for (int i = login.size()-1; i >=0; i--) {
				MyDayBean nm=(MyDayBean)login.get(i);
				date=nm.getDay();
			}
		}
		return date;
	}
	
	public String findFile(HttpServletRequest request,String userName,String title){
		try {
			ArrayList<MyFileBean> listName=null;
			HttpSession session=request.getSession();
			listName=new ArrayList<MyFileBean>();
			rs=selectFile(request, userName,title);
			if (rs.next()) {
				rs=selectFile(request, userName,title);
				while(rs.next()){
					MyFileBean mess=new MyFileBean();
					mess.setTitle(rs.getString("title"));
					mess.setName(rs.getString("name"));
					mess.setContentType(rs.getString("contentType"));
					mess.setSize(rs.getString("size"));
					mess.setFilePath(rs.getString("filePath"));
					listName.add(mess);
					session.setAttribute("findfile", listName);
				}
			} else {
				session.setAttribute("findfile", listName);
			}
			return "ok";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public String returnFile(HttpServletRequest request,String face){
		String file=null;
		HttpSession session=request.getSession();
		ArrayList<?> login=(ArrayList<?>) session.getAttribute("findfile");
		if (login==null||login.size()==0) {
			file=null;
		} else {
			for (int i = login.size()-1; i >=0; i--) {
				MyFileBean nm=(MyFileBean)login.get(i);
				if (face.equals("title")) {
					file=nm.getTitle();
				} else if(face.equals("filePath")){
					file=nm.getFilePath();
				}if(face.equals("fileName")){
					file=nm.getName();
				}
			}
		}
		return file;
	}
	
	public void message(String msg){
		int type=JOptionPane.YES_NO_CANCEL_OPTION;
		String title="信息提示";
		JOptionPane.showMessageDialog(null, msg,title,type);
	}
	
	

	

	

	


	
	
}
