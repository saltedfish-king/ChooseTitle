package admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

public class UpdateTime extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public UpdateTime() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");  
		response.setContentType("application/json;charset=UTF-8");
		
		String aid = request.getParameter("aid");
	    String astime = request.getParameter("startTime");
	    String aetime = request.getParameter("endTime");
	    
	    Admin admin = new Admin();
	    adManager ad = new adManager();
	    
	    admin.setAid(aid);
	    admin.setAstime(astime);
	    admin.setAetime(aetime);
	    
	    try {
			ad.updateTime(admin);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    JSONObject jObject = JSONObject.fromObject(admin);
		String jStr = jObject.toString();
		PrintWriter out = response.getWriter();
		out.print(jStr);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
