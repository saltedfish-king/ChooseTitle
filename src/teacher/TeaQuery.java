package teacher;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

public class TeaQuery extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public TeaQuery() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//解决乱码
		response.setCharacterEncoding("UTF-8");  
		response.setContentType("application/json;charset=UTF-8");
		
		String tid = request.getParameter("tid");
		
		teaManager tmgr = new teaManager();
		Teacher teacher =new Teacher();
		
		try {
			teacher = tmgr.query(tid);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//利用json返回信息给老师页面
		JSONObject jobject = JSONObject.fromObject(teacher);
		String jStr = jobject.toString();
		PrintWriter out = response.getWriter();
		out.print(jStr);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
