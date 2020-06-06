package web_text;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public loginServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
		Info info = new Info();
		//��������
		response.setCharacterEncoding("UTF-8");  
	    response.setContentType("application/json;charset=UTF-8");
	    
	    //ȡֵ
		String pwd = request.getParameter("pwd");
		String id = request.getParameter("id");
		String kinds = request.getParameter("kinds");
		
		//�������ݿⲢ���в�ѯ
		String driverName="com.microsoft.sqlserver.jdbc.SQLServerDriver";
		String dbURL="jdbc:sqlserver://localhost:1433;DatabaseName=system";
		String userName="sa";
		String userPwd="123456";
		
		//����kinds������sql����ѯ��һ����
		String sql = null;
		if(kinds.equals("学生")) {
			sql = "select 密码 from "+kinds+" where 学号='"+id+"'";
		}
		else if(kinds.equals("老师")) {
			sql = "select 密码 from "+kinds+" where 工号='"+id+"'";
		}
		else if(kinds.equals("管理员")) {
			sql = "select 管理员密码 from "+kinds+" where 管理员账号='"+id+"'";
		}
		
		
		try {
			 Class.forName(driverName);
			 Connection con = DriverManager.getConnection(dbURL, userName, userPwd);
			 Statement stm = con.createStatement();
			 ResultSet rs = stm.executeQuery(sql);
			 
			 while(rs.next()) {
				 //��֤����
				 String upwd = null;
				 if(kinds.equals("学生")) {
					    upwd = rs.getString("密码");
					}
					else if(kinds.equals("老师")) {
					    upwd = rs.getString("密码");
					}
					else if(kinds.equals("管理员")) {
						upwd = rs.getString("管理员密码");
					}
				 
				 if(upwd.equals(pwd)) {
					 info.setState("SUC");
					 info.setMsg("密码正确");
				 }
				 else {
					 info.setState("ERR");
					 info.setMsg("密码错误");
				 }
			 }
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//����json������Ϣ����ҳ
		JSONObject jobject = JSONObject.fromObject(info);
		String jStr = jobject.toString();
	    PrintWriter out = response.getWriter();
		out.print(jStr);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
