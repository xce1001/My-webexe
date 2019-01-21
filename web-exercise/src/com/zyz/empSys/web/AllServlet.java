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
		//获取全局参数配置
		ServletContext context = request.getServletContext();
		String encoding = context.getInitParameter("encoding");
		
		//设置请求编码格式
		request.setCharacterEncoding(encoding);
		//设置响应编码格式
		response.setContentType("text/html;charset=" + encoding);
		String cmd = request.getParameter("cmd");
		if(cmd.equals("empListS")) {//调用查看员工列表方法
			empListServlet(request,response);
		}else if(cmd.equals("logout")) {//调用安全退出方法
			logoutServlet(request,response);
		}else if(cmd.equals("modifyEmp")) {//调用获取员工修改信息方法
			modifyServlet(request,response);
		}else if(cmd.equals("delEmp")) {//调用删除员工信息的方法
			deleteServlet(request,response);
		}else if(cmd.equals("recycleEmp")) {//调用进入回收站的方法
			recycleServlet(request,response);
		}else if(cmd.equals("rEmp")) {//调用恢复员工信息的方法
			rServlet(request, response);
		}else if(cmd.equals("destroyEmp")) {//调用销毁员工信息的方法
			destroyServlet(request,response);
		}else if(cmd.equals("udateEmp")) {//调用修改员工信息的方法
			updateServlet(request,response);
		}else if(cmd.equals("login")) {//调用登录方法
			loginServlet(request,response);
		}else if(cmd.equals("validate")) {//调用生成验证码的方法
			validateServlet(request,response);
		}else if(cmd.equals("regist")) {//调用注册的方法
			registServlet(request,response);
		}
		
		
	}
	
	/**
	 * 注册Servlet
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void registServlet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//获取全局参数配置
		ServletContext context = request.getServletContext();
		//获取请求参数
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String gender = request.getParameter("gender");
		String age = request.getParameter("age");
		String hiredate = request.getParameter("hiredate");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		
		IEmpSysService service = new EmpSysService();
		service.addEmp(null, name, password, gender, Integer.parseInt(age), Date.valueOf(hiredate), phone, email);
		response.getWriter().write("恭喜注册成功,3s后跳转到登录界面");
		
		response.setHeader("refresh", "3;url=" + context.getContextPath() + "/login/login.jsp");
	}

	/**
	 * 获取验证码Servlet
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void validateServlet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//第一步:首先产生字符串区
				String str = "abcdefghijkmnpqrstuvwxyABCDEFGHJKLMNPQRSTUVWXY23456789";
				//第二步:随机获取四个字符
				StringBuilder sb = new StringBuilder();
				for(int i = 0;i < 4; i++) {
					sb.append(str.charAt(getRandomNum(str.length())));
				}
				//第三步:把获取到的四个字符存入到session中
				request.getSession().setAttribute("validate", sb.toString());
				//第四步:在内存中创建图片对象
				int width = 80;
				int height = 30;
				int imgType = BufferedImage.TYPE_INT_RGB;
				BufferedImage img = new BufferedImage(width,height,imgType);
				//第五步:在图片中获取画笔对象
				Graphics g = img.getGraphics();
				
				g.setFont(new Font("楷体",Font.BOLD,24));
				for(int i = 0; i < 5; i++) {
					g.setColor(new Color(getRandomNum(256),getRandomNum(256),getRandomNum(256)));
					g.drawLine(getRandomNum(10), getRandomNum(10) + 10, getRandomNum(70), getRandomNum(10) + 10);
				}
				g.drawString(sb.toString(), 20, 20);
				//设置响应头通知浏览器以图片形式打开
				response.setContentType("image/jpeg");//等同于response.setHeader("Content-Type","image/jpeg");
				//设置响应头控制浏览器不缓存
				response.setDateHeader("expries",-1);
				response.setHeader("Cache-Control", "no-cache");
				response.setHeader("Pragma","no-cache");
				//第六步:将图片写入浏览器
				ImageIO.write(img, "jpg", response.getOutputStream());
	}

	/**
	 * 登录Servlet
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void loginServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取请求参数
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		//获取用户输入的验证码
		String validate = request.getParameter("validate");
		
		//获取session中的验证码
		String val_in_session = (String) request.getSession().getAttribute("validate");
		if(!val_in_session.equalsIgnoreCase(validate)) {
			request.setAttribute("val_msg", "验证码有误,请重新输入");
			request.getRequestDispatcher("login/login.jsp").forward(request, response);
			return;
		}
		
		//调用Service中的方法进行登录
		IEmpSysService service = new EmpSysService();
		//登录验证,通过名字和密码查找用户
		Emp emp = service.findEmpByNameAndPassword(name,password);
		
		if(emp != null) {
			//把登录用户存入到session中,表示开启了一次回话
			HttpSession session = request.getSession();
			session.setAttribute("emp", emp);
			
			
			//格式化时间日期
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
			String lastTime = sdf.format(new java.util.Date());
			
			//获取请求中的cookie
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
					//设置cookie
					c.setPath("/");
					c.setMaxAge(60 * 60 * 24 * 365);
					request.setAttribute("lastTime", lastTime);
					response.addCookie(c);
					request.getRequestDispatcher("/homePage/homepage.jsp").forward(request, response);
				}	
			}else {
				Cookie c = new Cookie("lastTime",lastTime);
				//设置cookie
				c.setPath("/");
				c.setMaxAge(60 * 60 * 24 * 365);
				request.setAttribute("lastTime", lastTime);
				response.addCookie(c);
				request.getRequestDispatcher("/homePage/homepage.jsp").forward(request, response);
			}
			return;
		}else {
			//请求转发,把错误的信息转发到前端页面
			request.setAttribute("error_msg", "账号或密码有误,请检查后重新登录");
			request.getRequestDispatcher("/login/login.jsp").forward(request, response);
			return;
		}
	}

	/**
	 * 修改员工信息Servlet
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
	 * 销毁回收站员工信息Servlet
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
	 * 恢复员工信息Servlet
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
	 * 显示回收站员工列表Servlet
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
	 * 删除员工Servlet
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
	 * 获取修改信息Servlet
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
	 * 安全退出登录Servlet
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void logoutServlet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		Emp emp = (Emp)session.getAttribute("emp");
		if(emp != null) {
			//销毁session
			session.invalidate();
		}
		//重定向到主页
		response.sendRedirect(request.getContextPath() + "/homePage/homepage.jsp");
	}

	/**
	 * 显示员工列表Servlet
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void empListServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//通过service获取所有员工信息
		IEmpSysService service = new EmpSysService();
		List<Emp> list = service.findAll();
		
		//把所有员工信息存入到请求域中
		request.setAttribute("list", list);
		//请求转发
		request.getRequestDispatcher("/empList/empList.jsp").forward(request, response);
	}
	
	/**
	 * 产生随机数的方法
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
