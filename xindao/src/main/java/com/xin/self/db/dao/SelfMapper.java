package com.xin.self.db.dao;

import java.util.List;
import java.util.Map;

public interface SelfMapper {

	/**
	 * 设置本月薪资发放完毕
	 * 
	 * @param param
	 */
	public void salaryComplete(Map<String, Object> param);

	/**
	 * 获取员工列表
	 * 
	 * @param param
	 * @return
	 */
	List<Map<String, Object>> listEmployee(Map<String, Object> param);

	/**
	 * 获取工资列表数据
	 * 
	 * @param tid
	 * @return
	 */
	List<Map<String, Object>> listSalary(Map<String, Object> param);

	/**
	 * 获取一个员工所有的字段
	 * 
	 * @param zhEid
	 * @return
	 */
	// List<Map<String, Object>> getEmployeeAllFields(String zhEid);

	/**
	 * 执行sql
	 * 
	 * @param sql
	 * @return
	 */
	List<Map<String, Object>> execSqlSelect(String value);

	/**
	 * 执行sql
	 * 
	 * @param sqlStr
	 */
	public void execSql(String value);

	public Integer countBySql(String value);

}
