package com.zyz.empSys.dao;

import java.sql.Date;
import java.util.List;

import com.zyz.empSys.domain.Emp;

/**
 * emp DAO接口
 * @author Administrator
 *
 */
public interface IEmpDAO {

	/**
	 * 通过姓名和密码查找员工
	 * @param name
	 * @param password
	 * @return
	 */
	public Emp findEmpByNameAndPassword(String name, String password);
	
	/**
	 * 查询所有员工信息
	 */
	public List<Emp> findAll();
	
	/**
	 * 添加员工
	 * @param name
	 * @param password
	 * @param gender
	 * @param age
	 * @param hiredate
	 * @param phone
	 * @param emial
	 */
	public void addEmp(Integer id, String name, String password, String gender, Integer age, Date hiredate, String phone, String email);
	
	/**
	 * 修改员工信息
	 * @param id
	 * @param name
	 * @param password
	 * @param gender
	 * @param age
	 * @param hiredate
	 * @param phone
	 * @param emial
	 */
	public void modifyEmp(Integer id, String name, String password, String gender, Integer age, Date hiredate, Double salary,String phone, String email);
	
	/**
	 * 删除员工
	 * @param id
	 */
	public void deleteEmpById(Integer id);
	
	/**
	 * 通过id查找员工
	 */
	public Emp findEmpById( Integer id) ;
	
	/**
	 * 恢复删除的员工
	 */
	public void rEmp(Integer id);
	
	/**
	 * 查询删除的所有员工
	 */
	public List<Emp> findAllR();
	
	/**
	 * 销毁删除的员工
	 */
	public void deleteEmpByIdR(Integer id);
}
