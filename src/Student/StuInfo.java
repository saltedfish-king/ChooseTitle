package Student;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.Stumssg;
import net.sf.json.JSONObject;

public class StuInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public StuInfo() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");  
		response.setContentType("application/json;charset=UTF-8");
		
		String sid = request.getParameter("sid");
		String semail = request.getParameter("semail");
		String stell = request.getParameter("stell");
		
		stuManager stu = new stuManager();
		Stumssg mssg = new Stumssg();
		
		mssg.setSid(sid);
		mssg.setSemail(semail);
		mssg.setStell(stell);
		
		try {
			stu.StuInfo(mssg);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject jobject = JSONObject.fromObject(mssg);
		String jStr = jobject.toString();
		PrintWriter out = response.getWriter();
		out.print(jStr);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
