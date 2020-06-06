package teacher;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

public class ItemDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ItemDelete() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");  
		response.setContentType("application/json;charset=UTF-8");
		
		String iid = request.getParameter("iid");
		
		teaManager tmgr = new teaManager();
		Item1 item1 = new Item1();
		
		item1.setIid(iid);
		try {
			tmgr.Delete(iid);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
