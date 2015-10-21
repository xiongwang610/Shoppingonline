package servlet;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import JDBCTool.DbOperate;
import JvavBean.User;
import net.sf.json.JSONObject;

@SuppressWarnings("serial")
public class Register_Servlet extends HttpServlet {
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("来啦");
		String userName = req.getParameter("userName");
		String password = req.getParameter("password");
		PrintWriter out=resp.getWriter();
		DbOperate operate = new DbOperate();
		User user = operate.getUserByUserName(userName);
		Map<String,String> map = new HashMap<String,String>();
		
		if(user == null){
			user = new User();
			System.out.println(userName);
			user.setUsername(userName);
			user.setPassword(password);
			System.err.println("ahahah");
			operate.save(user);
			//out.println("success"+new String(operate.getUserById("3").getUsername().getBytes("ISO-8859-1"),"GB2312"));
			
		}else{
			out.println("failure");
		}
		
		
		out.close();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
