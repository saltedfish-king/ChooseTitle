package teacher;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

public class ItemQuery extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ItemQuery() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//解决乱码
		response.setCharacterEncoding("UTF-8");  
		response.setContentType("application/json;charset=UTF-8");
		
		
		String tid = request.getParameter("tid");
		
		teaManager tmgr = new teaManager();
		Item item = new Item();
		
		try {
			item = tmgr.Iquery(tid);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//json返回信息
		JSONObject jObject = JSONObject.fromObject(item);
		String jStr = jObject.toString();
		PrintWriter out = response.getWriter();
		out.print(jStr);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
