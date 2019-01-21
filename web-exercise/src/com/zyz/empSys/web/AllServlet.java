package com.zyz.empSys.web;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;
import java.sql.Date;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zyz.empSys.domain.Emp;
import com.zyz.empSys.service.IEmpSysService;
import com.zyz.empSys.serviceImpl.EmpSysService;

/**
 * Servlet implementation class AllSerevlet
 */
@SuppressWarnings("serial")
@WebServlet("/AllServlet")
public class AllServlet extends HttpServlet {

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡȫ�ֲ�������
		ServletContext context = request.getServletContext();
		String encoding = context.getInitParameter("encoding");
		
		//������������ʽ
		request.setCharacterEncoding(encoding);
		//������Ӧ�����ʽ
		response.setContentType("text/html;charset=" + encoding);
		String cmd = request.getParameter("cmd");
		if(cmd.equals("empListS")) {//���ò鿴Ա���б���
			empListServlet(request,response);
		}else if(cmd.equals("logout")) {//���ð�ȫ�˳�����
			logoutServlet(request,response);
		}else if(cmd.equals("modifyEmp")) {//���û�ȡԱ���޸���Ϣ����
			modifyServlet(request,response);
		}else if(cmd.equals("delEmp")) {//����ɾ��Ա����Ϣ�ķ���
			deleteServlet(request,response);
		}else if(cmd.equals("recycleEmp")) {//���ý������վ�ķ���
			recycleServlet(request,response);
		}else if(cmd.equals("rEmp")) {//���ûָ�Ա����Ϣ�ķ���
			rServlet(request, response);
		}else if(cmd.equals("destroyEmp")) {//��������Ա����Ϣ�ķ���
			destroyServlet(request,response);
		}else if(cmd.equals("udateEmp")) {//�����޸�Ա����Ϣ�ķ���
			updateServlet(request,response);
		}else if(cmd.equals("login")) {//���õ�¼����
			loginServlet(request,response);
		}else if(cmd.equals("validate")) {//����������֤��ķ���
			validateServlet(request,response);
		}else if(cmd.equals("regist")) {//����ע��ķ���
			registServlet(request,response);
		}
		
		
	}
	
	/**
	 * ע��Servlet
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void registServlet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//��ȡȫ�ֲ�������
		ServletContext context = request.getServletContext();
		//��ȡ�������
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String gender = request.getParameter("gender");
		String age = request.getParameter("age");
		String hiredate = request.getParameter("hiredate");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		
		IEmpSysService service = new EmpSysService();
		service.addEmp(null, name, password, gender, Integer.parseInt(age), Date.valueOf(hiredate), phone, email);
		response.getWriter().write("��ϲע��ɹ�,3s����ת����¼����");
		
		response.setHeader("refresh", "3;url=" + context.getContextPath() + "/login/login.jsp");
	}

	/**
	 * ��ȡ��֤��Servlet
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void validateServlet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//��һ��:���Ȳ����ַ�����
				String str = "abcdefghijkmnpqrstuvwxyABCDEFGHJKLMNPQRSTUVWXY23456789";
				//�ڶ���:�����ȡ�ĸ��ַ�
				StringBuilder sb = new StringBuilder();
				for(int i = 0;i < 4; i++) {
					sb.append(str.charAt(getRandomNum(str.length())));
				}
				//������:�ѻ�ȡ�����ĸ��ַ����뵽session��
				request.getSession().setAttribute("validate", sb.toString());
				//���Ĳ�:���ڴ��д���ͼƬ����
				int width = 80;
				int height = 30;
				int imgType = BufferedImage.TYPE_INT_RGB;
				BufferedImage img = new BufferedImage(width,height,imgType);
				//���岽:��ͼƬ�л�ȡ���ʶ���
				Graphics g = img.getGraphics();
				
				g.setFont(new Font("����",Font.BOLD,24));
				for(int i = 0; i < 5; i++) {
					g.setColor(new Color(getRandomNum(256),getRandomNum(256),getRandomNum(256)));
					g.drawLine(getRandomNum(10), getRandomNum(10) + 10, getRandomNum(70), getRandomNum(10) + 10);
				}
				g.drawString(sb.toString(), 20, 20);
				//������Ӧͷ֪ͨ�������ͼƬ��ʽ��
				response.setContentType("image/jpeg");//��ͬ��response.setHeader("Content-Type","image/jpeg");
				//������Ӧͷ���������������
				response.setDateHeader("expries",-1);
				response.setHeader("Cache-Control", "no-cache");
				response.setHeader("Pragma","no-cache");
				//������:��ͼƬд�������
				ImageIO.write(img, "jpg", response.getOutputStream());
	}

	/**
	 * ��¼Servlet
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void loginServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡ�������
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		//��ȡ�û��������֤��
		String validate = request.getParameter("validate");
		
		//��ȡsession�е���֤��
		String val_in_session = (String) request.getSession().getAttribute("validate");
		if(!val_in_session.equalsIgnoreCase(validate)) {
			request.setAttribute("val_msg", "��֤������,����������");
			request.getRequestDispatcher("login/login.jsp").forward(request, response);
			return;
		}
		
		//����Service�еķ������е�¼
		IEmpSysService service = new EmpSysService();
		//��¼��֤,ͨ�����ֺ���������û�
		Emp emp = service.findEmpByNameAndPassword(name,password);
		
		if(emp != null) {
			//�ѵ�¼�û����뵽session��,��ʾ������һ�λػ�
			HttpSession session = request.getSession();
			session.setAttribute("emp", emp);
			
			
			//��ʽ��ʱ������
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
			String lastTime = sdf.format(new java.util.Date());
			
			//��ȡ�����е�cookie
			Cookie[] cookies = request.getCookies();
			if(cookies != null) {
				Cookie c = null;
				for (Cookie cookie : cookies) {
					String cookieName = cookie.getName();
					if("lastTime".equals(cookieName)) {
						c = cookie;
						break;
					}
				}
				if(c != null) {
					String value = c.getValue();
					request.setAttribute("lastTime",value );
					c.setValue(lastTime);
					response.addCookie(c);
					request.getRequestDispatcher("/homePage/homepage.jsp").forward(request, response);
				}else {
					c = new Cookie("lastTime", lastTime);
					//����cookie
					c.setPath("/");
					c.setMaxAge(60 * 60 * 24 * 365);
					request.setAttribute("lastTime", lastTime);
					response.addCookie(c);
					request.getRequestDispatcher("/homePage/homepage.jsp").forward(request, response);
				}	
			}else {
				Cookie c = new Cookie("lastTime",lastTime);
				//����cookie
				c.setPath("/");
				c.setMaxAge(60 * 60 * 24 * 365);
				request.setAttribute("lastTime", lastTime);
				response.addCookie(c);
				request.getRequestDispatcher("/homePage/homepage.jsp").forward(request, response);
			}
			return;
		}else {
			//����ת��,�Ѵ������Ϣת����ǰ��ҳ��
			request.setAttribute("error_msg", "�˺Ż���������,��������µ�¼");
			request.getRequestDispatcher("/login/login.jsp").forward(request, response);
			return;
		}
	}

	/**
	 * �޸�Ա����ϢServlet
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void updateServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String gender = request.getParameter("gender");
		String age = request.getParameter("age");
		String hiredate = request.getParameter("hiredate");
		String salary = request.getParameter("salary");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		IEmpSysService service = new EmpSysService();
		service.modifyEmp(Integer.parseInt(id), name, password, gender, Integer.parseInt(age), Date.valueOf(hiredate), Double.parseDouble(salary), phone, email);
		request.getRequestDispatcher("/AllServlet?cmd=empListS").forward(request, response);
	}

	/**
	 * ���ٻ���վԱ����ϢServlet
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void destroyServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		IEmpSysService service = new EmpSysService();
		service.deleteEmpByIdR(Integer.parseInt(id));
		request.getRequestDispatcher("/AllServlet?cmd=recycleEmp").forward(request, response);
	}

	/**
	 * �ָ�Ա����ϢServlet
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void rServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		IEmpSysService service = new EmpSysService();
		service.rEmp(Integer.parseInt(id));
		request.getRequestDispatcher("/AllServlet?cmd=recycleEmp").forward(request, response);
	}

	/**
	 * ��ʾ����վԱ���б�Servlet
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void recycleServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IEmpSysService service = new EmpSysService();
		List<Emp> listr = service.findAllR();
		request.setAttribute("listr", listr);
		request.getRequestDispatcher("/empList/empListR.jsp").forward(request, response);
	}

	/**
	 * ɾ��Ա��Servlet
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void deleteServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		IEmpSysService service = new EmpSysService();
		service.deleteEmpById(Integer.parseInt(id));
		request.getRequestDispatcher("/AllServlet?cmd=empListS").forward(request, response);
	}



	/**
	 * ��ȡ�޸���ϢServlet
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void modifyServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		IEmpSysService service = new EmpSysService();
		Emp emp = service.findEmpById(Integer.parseInt(id));
		request.setAttribute("emp", emp);
		request.getRequestDispatcher("/empList/modifyEmp.jsp").forward(request, response);
	}
	
	/**
	 * 
	 * ��ȫ�˳���¼Servlet
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void logoutServlet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		Emp emp = (Emp)session.getAttribute("emp");
		if(emp != null) {
			//����session
			session.invalidate();
		}
		//�ض�����ҳ
		response.sendRedirect(request.getContextPath() + "/homePage/homepage.jsp");
	}

	/**
	 * ��ʾԱ���б�Servlet
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void empListServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//ͨ��service��ȡ����Ա����Ϣ
		IEmpSysService service = new EmpSysService();
		List<Emp> list = service.findAll();
		
		//������Ա����Ϣ���뵽��������
		request.setAttribute("list", list);
		//����ת��
		request.getRequestDispatcher("/empList/empList.jsp").forward(request, response);
	}
	
	/**
	 * ����������ķ���
	 */
	private int getRandomNum(int r) {
		Random random = new Random();
		return random.nextInt(r);
	}
	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
