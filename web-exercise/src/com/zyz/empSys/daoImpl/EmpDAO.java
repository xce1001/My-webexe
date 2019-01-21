package com.zyz.empSys.daoImpl;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.zyz.empSys.DButil.MyDBUtils;
import com.zyz.empSys.dao.IEmpDAO;
import com.zyz.empSys.domain.Emp;

/**
 * empDAO µœ÷¿‡
 * @author Administrator
 *
 */
public class EmpDAO implements IEmpDAO{

	@Override
	public Emp findEmpByNameAndPassword(String name, String password) {
		
		QueryRunner runner = new QueryRunner(MyDBUtils.getDataSource());
		
		try {
			return runner.query("select * from emp where name=? and password=?", new BeanHandler<Emp>(Emp.class),name,password);
		}catch(SQLException e) {
			e.printStackTrace();
		}
			
		return null;
	}

	@Override
	public List<Emp> findAll() {

		QueryRunner runner = new QueryRunner(MyDBUtils.getDataSource());
		try {
			return runner.query("select * from emp", new BeanListHandler<Emp>(Emp.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void addEmp(Integer id, String name, String password, String gender, Integer age, Date hiredate, String phone,
			String email) {
		QueryRunner runner = new QueryRunner(MyDBUtils.getDataSource());
		try {
			runner.update("insert into emp (id, name, password, gender, age, hiredate, phone, email) values(?,?,?,?,?,?,?,?)", id, name, password, gender, age, hiredate, phone, email);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void modifyEmp(Integer id, String name, String password, String gender, Integer age, Date hiredate, Double salary,
			String phone, String email) {
		QueryRunner runner = new QueryRunner(MyDBUtils.getDataSource());
		try {
			runner.update("update emp set name=?,password=?,gender=?,age=?,hiredate=?,salary=?,phone=?,email=? where id=?", name, password, gender, age, hiredate, salary, phone, email, id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteEmpById(Integer id) {
		QueryRunner runner = new QueryRunner(MyDBUtils.getDataSource());
		try {
			Emp emp = runner.query("select * from emp where id=?", new BeanHandler<Emp>(Emp.class),id);
			runner.update("insert into empr values(?,?,?,?,?,?,?,?,?)", emp.getId(), emp.getName(), emp.getPassword(), emp.getGender(), emp.getAge(), emp.getHiredate(), emp.getSalary(), emp.getPhone(), emp.getEmail());
			runner.update("delete from emp where id=?",id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Emp findEmpById(Integer id) {
		QueryRunner runner = new QueryRunner(MyDBUtils.getDataSource());
		try {
			return runner.query("select * from emp where id=?", new BeanHandler<Emp>(Emp.class),id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void rEmp(Integer id) {
		QueryRunner runner = new QueryRunner(MyDBUtils.getDataSource());
		try {
			Emp emp = runner.query("select * from empr where id=?", new BeanHandler<Emp>(Emp.class),id);
			runner.update("insert into emp values(?,?,?,?,?,?,?,?,?)", emp.getId(), emp.getName(), emp.getPassword(), emp.getGender(), emp.getAge(), emp.getHiredate(), emp.getSalary(), emp.getPhone(), emp.getEmail());
			runner.update("delete from empr where id=?",id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Emp> findAllR() {
		QueryRunner runner = new QueryRunner(MyDBUtils.getDataSource());
		try {
			return runner.query("select * from empr", new BeanListHandler<Emp>(Emp.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void deleteEmpByIdR(Integer id) {
		QueryRunner runner = new QueryRunner(MyDBUtils.getDataSource());
		try {
			runner.update("delete from empr where id=?",id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
