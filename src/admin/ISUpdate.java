package admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

public class ISUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public ISUpdate() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");  
		response.setContentType("application/json;charset=UTF-8");
		
		String sid = request.getParameter("sid");
		String iid = request.getParameter("iid");
		
		adManager ad = new adManager();
		Stumssg mssg = new Stumssg();
		
		mssg.setIid(iid);
		mssg.setSid(sid);
		
		try {
			ad.ISupdate(mssg);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JSONObject jObject = JSONObject.fromObject(mssg);
		String jStr = jObject.toString();
		PrintWriter out = response.getWriter();
		out.print(jStr);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
