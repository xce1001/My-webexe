package test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import com.zyz.empSys.DButil.MyDBUtils;
import com.zyz.empSys.domain.Emp;
import com.zyz.empSys.service.IEmpSysService;
import com.zyz.empSys.serviceImpl.EmpSysService;

public class testConnection {

	@Test
	public void testConnectiona() throws SQLException {
		Connection connection = MyDBUtils.getConnection();
		System.out.println(connection);
		connection.close();
	}
	
	@Test
	public void testFindEmp() {
		IEmpSysService service = new EmpSysService();
		Emp emp = service.findEmpByNameAndPassword("lucy", "lucy123");
		System.out.println(emp);
	}
	
	
	@Test
	public void testfindall() {
		IEmpSysService service = new EmpSysService();
		List<Emp> list = service.findAll();
		for (Emp emp : list) {
			System.out.println(emp);
		}
	}
	
	@Test
	public void testaddEmployee() {
		IEmpSysService service = new EmpSysService();
		service.addEmp(null, "alex", "alex123", "ÄÐ", 21, null, "126710", "alex@qq.com");
	}
	
	@Test 
	public void testmodifyEmployee() {
		IEmpSysService service = new EmpSysService();
		service.modifyEmp(1, "alex", "alex123", "ÄÐ", 22, Date.valueOf("2019-01-12") , 4333.0, "10987654321", "alex@qq.com");
	}
	
	@Test
	public void testdeleteEmployee() {
		IEmpSysService service = new EmpSysService();
		service.deleteEmpById(8);
	}
	
	@Test
	public void testnuLL() {
		Date parseInt = Date.valueOf("2018/01/12");
		System.out.println(parseInt);
	}
	
	@Test
	public void testFindEmpById() {
		IEmpSysService service = new EmpSysService();
		Emp emp = service.findEmpById(2);
		System.out.println(emp);
	}
	
	@Test
	public void testREmp() {
		IEmpSysService service = new EmpSysService();
		service.rEmp(1);
	}
	
	@Test
	public void testFindAllR() {
		IEmpSysService service = new EmpSysService();
		List<Emp> list = service.findAllR();
		for (Emp emp : list) {
			System.out.println(emp);
		}
	}
	
	@Test
	public void testDeleteEmp() {
		IEmpSysService service = new EmpSysService();
		service.deleteEmpByIdR(5);
	}
	
}
