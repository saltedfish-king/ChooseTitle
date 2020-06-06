package teacher;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

public class TeacherChange extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public TeacherChange() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//解决乱码
		response.setCharacterEncoding("UTF-8");  
	    response.setContentType("application/json;charset=UTF-8");
		
	    //获取修改后的密码及对应的id
		String tid = request.getParameter("tid");
		String tpwd = request.getParameter("tpwd");
		
		Teacher tea = new Teacher();
		teaManager tmgr = new teaManager();
		
		tea.setTid(tid);
		tea.setTpwd(tpwd);
		
		if(tpwd != null) {
			try {
				tmgr.updatePwd(tea);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		//利用json返回信息给首页
		JSONObject jobject = JSONObject.fromObject(tea);
		String jStr = jobject.toString();
		PrintWriter out = response.getWriter();
		out.print(jStr);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
