package struts;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionSupport;

import JDBCTool.DbOperate;
import JvavBean.Goods;
import net.sf.json.JSONObject;

@SuppressWarnings("serial")
public class GoodsUpdown_Struts extends ActionSupport implements ServletRequestAware, ServletResponseAware {
	
	private static final int SIZE = 1024;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private static final String RESULT = "success";
	
	public void setServletResponse(HttpServletResponse arg0) {
		
		response = arg0;
		response.setCharacterEncoding("UTF-8");
	}

	public void setServletRequest(HttpServletRequest arg0) {
		request = arg0;
	}
	
	
	/**
	 * 将goods对象存入数据库
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public String upDown() throws IOException, ClassNotFoundException{
		
		DataInputStream in= new DataInputStream(request.getInputStream());
		byte[] buf = new byte[SIZE];
		StringBuilder requestContent = new StringBuilder();
		
		while(in.read(buf) != -1){
			requestContent.append(new String(buf,0,buf.length));
		}
		
		JSONObject json_goods = JSONObject.fromObject(requestContent.toString());
		JSONObject json = json_goods.getJSONObject("goods");
		JSONObject json_user = json.getJSONObject("user");
		System.out.println(json_goods.toString());
		DbOperate operate = new DbOperate();
		Goods goods = new Goods();
		goods.setName(json.getString("name"));
		goods.setPhonenumber(json.getString("phonenumber"));
		goods.setAddress(json.getString("address"));
		goods.setIntroduction(json.getString("introduction"));
		goods.setPrice(json.getString("price"));
		goods.setUrl(json.getString("url"));
		goods.setQq(json.getString("qq"));
		goods.setTime(json.getString("time"));
		goods.setType(json.getString("type"));
		goods.setUser(operate.getUserByUserName(json_user.getString("username")));
		System.out.println(goods.getAddress());
		operate.save(goods);
		
		Map<String ,String> map = new HashMap<String,String>();
		map.put("result", RESULT);
		JSONObject json_result = JSONObject.fromObject(map);
		PrintWriter out = response.getWriter();
		out.println(json_result);
		return null;
		
	}

}
