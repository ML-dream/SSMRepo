package cfmes.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.wl.forms.User;
import com.wl.tools.MD5;
import com.wl.tools.Sqlhelper;
import com.wl.tools.login;

/**
 * Servlet implementation class LoginHandler
 */
public class LoginHandler extends HttpServlet {

	private static final long serialVersionUID = -6480924742009510047L;

	/**
     * @see HttpServlet#HttpServlet()
     */
    public LoginHandler() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Cache-Control", "no-store");
		response.setDateHeader("Expires", 0);
		response.setHeader("Pragma", "no-cache");
		HttpSession session = request.getSession();
		
		String userId = request.getParameter("username").trim();
		String password = request.getParameter("passwd").trim();
		
		System.out.println("userId=="+userId);
		System.out.println("password=="+password);
//		password = MD5.MD5Convert(password);
		User user = null;
		
		boolean loginok = false;
		if (userId=="" || password=="") {
			System.out.println("用户名或者密码为空！！！");
		}else {
			String getpassword = "select b.work_type workerType,A.USER_ID userId,A.STAFF_CODE staffCode,A.USER_NAME userName,A.PASSWORD password,A.AUTHORITY authority,B.staff_name staffName " +
					"from sysusers A " +
					"left join employee_info B on A.STAFF_CODE=B.STAFF_CODE "+
					" where USER_ID=?";
			String[] params = {userId};
			try {
				user = Sqlhelper.exeQueryBean(getpassword, params, User.class);
			}catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		if (null!=user&&password.equals(user.getPassword())) {
			loginok = true;
		}
		if (loginok) {           //登录成功！！
			System.out.println("登录成功！！！");
			session.setAttribute("user", user);
//			暂时可以先这样，其实这里可以使用在主页的jsp写判断逻辑！或者在
			/*RequestDispatcher dispatcher=request.getRequestDispatcher("/WEB-INF/MyMainJsp.jsp");
			dispatcher.forward(request, response);*/
			response.sendRedirect("MyMainJsp.jsp");
		}else {					//登录失败！！
			System.out.println("登录失败！！！");
			response.sendRedirect("login.jsp");
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
//		if (session.getAttribute("user") != null) {
//			log.setout(((User) session.getAttribute("user")).getUser_id());
//			session.invalidate(); // ʹ�Ự��Ч�������²���ֱ�ӵ�¼����Ҫ���¿�һ����¼����
//		}
//		String ip = request.getRemoteHost(); // ip
//
//		String mesg = "";
//		int num = 0; // �û�״̬
//		int a = 2;
//		String temp = "0";
//		int error_num = Integer.parseInt(temp);
//
//		String username = request.getParameter("username").equals("") ? ""
//				: request.getParameter("username");
//		if (username != null && !username.equals("")) {
//			String passwd = request.getParameter("passwd");
//			log.setUserid(username);
//			log.setPasswd(passwd);
//			int flag = log.excute();
//			if (flag == 0) {
//				mesg = "���˺Ų����ڣ�";
//				session=request.getSession(true);
//				response.sendRedirect("login.jsp");
//				setAttrib(session, mesg, username, error_num);
//			} else {
//				num = log.getUser_status();
//				if (num == 0) {
//					if (flag == 1) {
//
//						error_num++;
//
//						mesg = "����������!����" + (5 - error_num) + "�λ��";
//						if (error_num == 5) {
//							mesg = "��������ε�½�����ѱ���";
//
//							log.Up_status(username, a);
//						}
//						session=request.getSession(true);
//						response.sendRedirect("login.jsp");
//						setAttrib(session, mesg, username, error_num);
//					} else {
//						User user = new User();
//						user.setUser_id(username);
//						user.setRole_id(log.getRole_name());
//						session=request.getSession(true);
//						session.setAttribute("user", user); // ����¼�û������Ա�����session�С���fy
//						log.setinfo(username, log.getRole_name(), ip);
//						if (30 - log.getDay() <= 10) {
//							out.println("<script>alert('��������Ч�ڻ�ʣ"
//									+ (30 - log.getDay())
//									+ "�죬�뼰ʱ�޸����룡');</script>");
//							response.sendRedirect("index.jsp");
//						} else {
//							response.sendRedirect("index.jsp");
//						}
//					}
//				} else if (num == 1) {
//					mesg = "����˺��Ѿ���½!";// out.println("<script>alert('����˺��Ѿ���½��')</script>");
//					session=request.getSession(true);
//					response.sendRedirect("login.jsp");
//					setAttrib(session, mesg, username, error_num);
//				} else if (num == 2) {
//					mesg = "����˺��ѱ�������ϵ����Ա����";// out.println("<script>alert('����˺������½����������Σ�����ϵ����Ա����')</script>");
//					session=request.getSession(true);
//					response.sendRedirect("login.jsp");
//					setAttrib(session, mesg, username, error_num);
//				} else if (num == 3) {
//					mesg = "����˺����볬�ڣ��������룡";// out.println("<script>alert('����˺����볬�ڣ��������������룡')</script>");
//					session=request.getSession(true);
//					response.sendRedirect("login.jsp");
//					setAttrib(session, mesg, username, error_num);
//				}
//			}
//		}
	}

	private void setAttrib(HttpSession session,String mesg,String username,int error_num) {
		session.setAttribute("mesg", mesg);
		session.setAttribute("username", username);
		session.setAttribute("error_num", error_num);
	}
	}


