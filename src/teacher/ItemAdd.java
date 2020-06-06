package teacher;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

public class ItemAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ItemAdd() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");  
		response.setContentType("application/json;charset=UTF-8");
		
		String iid = request.getParameter("iid");
		String tid = request.getParameter("tid");
		String ikind = request.getParameter("ikind");
		String iname = request.getParameter("iname");
		String ishow = request.getParameter("ishow");
		String itool = request.getParameter("itool");
		
		teaManager tmgr = new teaManager();
		Item1 item1 = new Item1();
		
		item1.setIid(iid);
		item1.setIkind(ikind);
		item1.setIname(iname);
		item1.setIshow(ishow);
		item1.setTid(tid);
		item1.setItool(itool);
		
		try {
			tmgr.addItem(item1);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		JSONObject jObject = JSONObject.fromObject(item1);
		String jStr = jObject.toString();
		PrintWriter out = response.getWriter();
		out.print(jStr);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
