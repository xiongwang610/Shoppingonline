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

import org.apache.commons.io.FileUtils;

import JDBCTool.DbOperate;
import JvavBean.User;
import net.sf.json.JSONObject;

@SuppressWarnings("serial")
public class Login_Servlet extends HttpServlet {
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String userName = req.getParameter("userName");
		String password = req.getParameter("password");
		DbOperate operate = new DbOperate();
		User user = operate.getUserByUserName(userName);
		resp.setCharacterEncoding("utf-8");
		PrintWriter out=resp.getWriter();
		Map<String,Object> map = new HashMap<String,Object>();
		
		if(user != null){
			
			if(user.getPassword().equals(password)){
				
				map.put("result", "success");
				map.put("user", user);
				
			}else map.put("result", "failure");
			
		}else 	map.put("result", "failure");
		
		JSONObject json = JSONObject.fromObject(map);
		out.println(json);
		out.close();
	}
}
