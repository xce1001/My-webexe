package com.zyz.empSys.serviceImpl;

import java.sql.Date;
import java.util.List;

import com.zyz.empSys.dao.IEmpDAO;
import com.zyz.empSys.daoImpl.EmpDAO;
import com.zyz.empSys.domain.Emp;
import com.zyz.empSys.service.IEmpSysService;

/**
 * EmpService接口的实现
 * @author Administrator
 *
 */
public class EmpSysService implements IEmpSysService{

	IEmpDAO dao = new EmpDAO();
	
	@Override
	public Emp findEmpByNameAndPassword(String name, String password) {
		
		 return dao.findEmpByNameAndPassword(name, password);
		 
	}

	@Override
	public List<Emp> findAll() {
		
		return dao.findAll();
	}

	@Override
	public void addEmp(Integer id, String name, String password, String gender, Integer age, Date hiredate, String phone,
			String emial) {
		dao.addEmp(id, name, password, gender, age, hiredate, phone, emial);
	}

	@Override
	public void modifyEmp(Integer id, String name, String password, String gender, Integer age, Date hiredate, Double salary,
			String phone, String emial) {
		dao.modifyEmp(id, name, password, gender, age, hiredate, salary,  phone, emial);
	}

	@Override
	public void deleteEmpById(Integer id) {
		dao.deleteEmpById(id);
	}

	@Override
	public Emp findEmpById(Integer id) {
		 return dao.findEmpById(id);
	}

	@Override
	public void rEmp(Integer id) {
		dao.rEmp(id);
	}

	@Override
	public List<Emp> findAllR() {
		return dao.findAllR();
	}

	@Override
	public void deleteEmpByIdR(Integer id) {
		dao.deleteEmpByIdR(id);
	}

	
}
