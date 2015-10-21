package struts;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionSupport;

import JDBCTool.DbOperate;
import JvavBean.Goods;
import JvavBean.User;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@SuppressWarnings("serial")
public class GoodsUpload_Struts extends ActionSupport implements ServletRequestAware, ServletResponseAware {

	
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	
	public void setServletResponse(HttpServletResponse arg0) {
		
		response = arg0;
		response.setCharacterEncoding("utf-8");
	}

	public void setServletRequest(HttpServletRequest arg0) {
		
		request = arg0;
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * 查询数据库并将结果返回给客户端
	 * @return
	 * @throws IOException
	 */
	public String goodsLoad() throws IOException{
		
		DbOperate operate = new DbOperate();
		String user_id = request.getParameter("user_id");
		String first = request.getParameter("first");
		String max = request.getParameter("max");
		String key = request.getParameter("key");
		List<Goods> list = new ArrayList<Goods>();
		
		if(user_id != null) list = operate.getGoodsByUser_id(user_id);
		
		if(first != null && max != null) list = operate.getGoodsByPage(Integer.parseInt(first), Integer.parseInt(max));
		
		if(key != null) list = operate.getGoodsByKey(key);
		System.out.println(list.size());
		JSONArray json = JSONArray.fromObject(list.toArray());
		PrintWriter out = response.getWriter();
		out.println(json);
		out.flush();
		out.close();
		return null;
		
	}

}
