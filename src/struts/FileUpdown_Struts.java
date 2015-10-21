package struts;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONObject;

@SuppressWarnings("serial")
public class FileUpdown_Struts extends ActionSupport  implements 
ServletRequestAware,ServletResponseAware{
	
	private HttpServletResponse response;
	private HttpServletRequest request;
	private String name;
	private String type;
	private boolean state = true;
	
	
	
	public String upDown() throws IOException{
		
			Map<String,String> map = new HashMap<String,String>();
			if(state){
				String path = ServletActionContext.getServletContext().getRealPath("images");
				
				File file_image = new File(path);
				if(!file_image.isDirectory()){
					file_image.mkdir();
				}
				
				String fileName = null;
				fileName = new Date().getTime() + name + type;
				ServletInputStream  in = request.getInputStream();
				FileUtils.copyInputStreamToFile(in, new File(file_image,fileName));
				map.put("result", "success");
				map.put("url",fileName );
				
			}else map.put("success", "failure");
			
			JSONObject json = JSONObject.fromObject(map);
			PrintWriter out = response.getWriter();
			out.println(json);
			out.close();
		return null;
	}
	
	public void setServletResponse(HttpServletResponse arg0) {
		response = arg0;
		response.setCharacterEncoding("UTF-8");
	}
	
	public void setServletRequest(HttpServletRequest arg0) {
		
		request = arg0;
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		name = request.getParameter("name");
		type = request.getParameter("type");
	}
	
	@Override
	public void addFieldError(String fieldName, String errorMessage) {
		state = false;
	}
}
