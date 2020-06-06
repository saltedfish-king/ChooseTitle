package admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

public class StuAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public StuAdd() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");  
		response.setContentType("application/json;charset=UTF-8");
		
		String sid = request.getParameter("sid");
		String spwd = request.getParameter("spwd");
		String sname = request.getParameter("sname");
		String ssex = request.getParameter("ssex");
		String smajor = request.getParameter("smajor");
		String syear = request.getParameter("syear");
		String semail = request.getParameter("semail");
		String stell = request.getParameter("stell");
		String sgrade = request.getParameter("sgrade");
		String iid = request.getParameter("iid");
		
		adManager ad = new adManager();
		Stumssg stumssg = new Stumssg();
		
		stumssg.setIid(iid);
		stumssg.setSemail(semail);
		stumssg.setSgrade(sgrade);
		stumssg.setSid(sid);
		stumssg.setSmajor(smajor);
		stumssg.setSname(sname);
		stumssg.setSpwd(spwd);
		stumssg.setSsex(ssex);
		stumssg.setStell(stell);
		stumssg.setSyear(syear);
		
		try {
			ad.addStu(stumssg);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JSONObject jObject = JSONObject.fromObject(stumssg);
		String jStr = jObject.toString();
		PrintWriter out = response.getWriter();
		out.print(jStr);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
