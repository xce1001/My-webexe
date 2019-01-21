package com.zyz.empSys.dao;

import java.sql.Date;
import java.util.List;

import com.zyz.empSys.domain.Emp;

/**
 * emp DAO�ӿ�
 * @author Administrator
 *
 */
public interface IEmpDAO {

	/**
	 * ͨ���������������Ա��
	 * @param name
	 * @param password
	 * @return
	 */
	public Emp findEmpByNameAndPassword(String name, String password);
	
	/**
	 * ��ѯ����Ա����Ϣ
	 */
	public List<Emp> findAll();
	
	/**
	 * ���Ա��
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
	 * �޸�Ա����Ϣ
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
	 * ɾ��Ա��
	 * @param id
	 */
	public void deleteEmpById(Integer id);
	
	/**
	 * ͨ��id����Ա��
	 */
	public Emp findEmpById( Integer id) ;
	
	/**
	 * �ָ�ɾ����Ա��
	 */
	public void rEmp(Integer id);
	
	/**
	 * ��ѯɾ��������Ա��
	 */
	public List<Emp> findAllR();
	
	/**
	 * ����ɾ����Ա��
	 */
	public void deleteEmpByIdR(Integer id);
}
