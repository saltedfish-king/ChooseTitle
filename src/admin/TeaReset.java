package admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import teacher.Teacher;

public class TeaReset extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public TeaReset() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");  
		response.setContentType("application/json;charset=UTF-8");
		
		String tid = request.getParameter("tid");
		String tpwd = request.getParameter("tpwd");
		
		adManager ad = new adManager();
		Teacher teacher = new Teacher();
		
		teacher.setTid(tid);
		teacher.setTpwd(tpwd);
		
		try {
			ad.teaReset(teacher);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject jobject = JSONObject.fromObject(teacher);
		String jStr = jobject.toString();
		PrintWriter out = response.getWriter();
		out.print(jStr);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
