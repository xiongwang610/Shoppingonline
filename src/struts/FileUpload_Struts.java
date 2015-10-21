package struts;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class FileUpload_Struts extends ActionSupport implements ServletRequestAware, ServletResponseAware {

	
	private HttpServletResponse response;
	private HttpServletRequest request;
	private String fileName;
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void setServletResponse(HttpServletResponse arg0) {
		
		response = arg0;
	}

	public void setServletRequest(HttpServletRequest arg0) {
		
		request = arg0;
	}
	
	/**
	 * 文件下载
	 * @return
	 * @throws IOException
	 */
	public String fileLoad() throws IOException{
		
		String path = ServletActionContext.getServletContext().getRealPath("images");
		File file_image = new File(path);
		File file_load = new File(file_image,fileName);
		if(file_load.exists()){
			
			OutputStream out = response.getOutputStream();
			FileUtils.copyFile(file_load,out);
			out.flush();
			out.close();
			
		}
		
		return null;
		
	}

}
