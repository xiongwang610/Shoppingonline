package struts;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;

import JDBCTool.DbOperate;
import JvavBean.User;

@SuppressWarnings("serial")
public class UserUpdate_Struts extends ActionSupport implements ServletRequestAware{

	private HttpServletRequest request;
	

	public void setServletRequest(HttpServletRequest arg0) {
		request = arg0;
	}
	
	public String userUpdate() throws IOException, ClassNotFoundException{
		InputStream in= request.getInputStream();
		ObjectInputStream objectInputStream = new ObjectInputStream(in);
		User user = (User) objectInputStream.readObject();
		DbOperate operate = new DbOperate();
		operate.update(user);
		
		return null;
		
	}

}
