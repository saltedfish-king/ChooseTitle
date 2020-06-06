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

public class StuChange extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public StuChange() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");  
	    response.setContentType("application/json;charset=UTF-8");
	    
	    String sid = request.getParameter("sid");
	    String spwd = request.getParameter("spwd");
	    
	    Stumssg mssg = new Stumssg();
	    stuManager stu = new stuManager();
	    
	    mssg.setSid(sid);
	    mssg.setSpwd(spwd);
	    
	    if(spwd != null) {
	    	try {
				stu.SupdatePwd(mssg);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    
	    JSONObject jobject = JSONObject.fromObject(stu);
		String jStr = jobject.toString();
		PrintWriter out = response.getWriter();
		out.print(jStr);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
