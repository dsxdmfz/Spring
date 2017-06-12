package com.spring.jdbc;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

public class JDBCTest {

	private ApplicationContext ctx = null;
	private JdbcTemplate jdbcTemplate;
	private EmployeeDao employeeDao;
	private DepartmentDao departmentDao;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	{
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		jdbcTemplate = (JdbcTemplate) ctx.getBean("jdbcTemplate");
		employeeDao = ctx.getBean(EmployeeDao.class);
		departmentDao = ctx.getBean(DepartmentDao.class);
		namedParameterJdbcTemplate = ctx.getBean(NamedParameterJdbcTemplate.class);
	}
	/**
	 * ʹ�þ�������ʱ������ʹ��.update(String sql, SqlParameterSource paramSource) �������и��²���
	 * 1.SQL ����еĲ��������������һ�£�
	 * 2.ʹ��SqlParameterSource ��BeanPropertySqlParameterSource ʵ������Ϊ����
	 */
	@Test
	public void testNamedParameterJdbcTemplate2() {
		String sql = "insert into employees(name,email,department_id) values(:name,:email,:departmentId)";
		Employee employee = new Employee();
		employee.setName("rr");
		employee.setEmail("rr@rr.com");
		employee.setDepartmentId(2);
		SqlParameterSource paramSource = new BeanPropertySqlParameterSource(employee);
		namedParameterJdbcTemplate.update(sql, paramSource);
	}
	
	/**
	 * ����Ϊ����������
	 * 1.�ô������ж��������������ȥ��Ӧλ�ã�ֱ�Ӷ�Ӧ������������ά��
	 * 2.ȱ�㣺��Ϊ�鷳
	 */
	@Test
	public void testNamedParameterJdbcTemplate() {
		String sql = "insert into employees(name,email,department_id) values(:n,:e,:d)";
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("n", "ee");
		paramMap.put("e", "ee@ee.com");
		paramMap.put("d", 1);
		namedParameterJdbcTemplate.update(sql, paramMap);
	}
	
	@Test
	public void testDepartmentDao() {
		System.out.println(departmentDao.getDS(2));
	}
	
	@Test
	public void testEmployeeDao() {
		System.out.println(employeeDao.getEmployee(1));
	}
	
	/**
	 * ��ȡ�����е�ֵ������ͳ�Ʋ�ѯ
	 * ʹ��queryForObject(String sql, Class<Long> requiredType) 
	 */
	@Test
	public void testQueryForObject2() {
		String sql = "select count(id) from employees";
		long count = jdbcTemplate.queryForObject(sql, Long.class);
		System.out.println(count);
	}

	/**
	 * �鵽ʵ����ļ���
	 * ע����õĲ���queryForList ����
	 */
	@Test
	public void testQueryForList() {
		String sql = "select id,name,email from employees where id > ?";
		RowMapper<Employee> rowMapper = new BeanPropertyRowMapper<>(Employee.class);
		List<Employee> employees = jdbcTemplate.query(sql, rowMapper,2);
		System.out.println(employees);
	}
	
	/**
	 * �����ݿ��л�ȡһ����¼��ʵ�ʵõ���Ӧ��һ������
	 * ע�ⲻ�ǵ���queryForObject(String sql, Class<Employee> requiredType, Object... args) ������
	 * ����Ҫ����.queryForObject(String sql, RowMapper<Employee> rowMapper, Object... args)
	 * 1.���е�RowMapper ָ�����ȥӳ���������У����õ�ʵ����ΪBeanPropertyRowMapper
	 * 2.ʹ��sql ���еı�����������������������ӳ�䡣����last_name lastName
	 * 3.��֧�ּ������ԡ�JdbcTemplate ����ֻ��һ��JDBC ��С���ߣ�������ORM ���
	 */
	@Test
	public void testQueryForObject(){
		String sql = "select id,name,email,department_id as \"department.id\" from employees where id=?";
		RowMapper<Employee> rowMapper = new BeanPropertyRowMapper<>(Employee.class) ;
		Employee employee = jdbcTemplate.queryForObject(sql, rowMapper, 1);
		System.out.println(employee);
	}
	
	/**
	 * ִ���������£�������INSET,UPDATE,DELETE
	 * ���һ��������Object[] ��List ���ͣ���Ϊ�޸�һ����¼��Ҫһ��Object �����飬��ô����������Ҫ���Object ��������
	 */
	@Test
	public void testBatchUpdate(){
		String sql = "insert into employees(name,email,department_id) value(?,?,?)";
		List<Object[]> batchArgs = new ArrayList<>();
		batchArgs.add(new Object[]{"aa","aa@aa.com",1});
		batchArgs.add(new Object[]{"bb","bb@aa.com",2});
		batchArgs.add(new Object[]{"cc","cc@aa.com",3});
		
		jdbcTemplate.batchUpdate(sql, batchArgs);
	}
	
	/**
	 * ִ��INSERT,UPDATE,DELETE
	 */
	@Test
	public void testUpdate(){
		String sql = "update employees set name=? where id=?";
		jdbcTemplate.update(sql, "aa",1);
	}
	
	@Test
	public void test() throws SQLException {
		DataSource dataSource = ctx.getBean(DataSource.class);
		System.out.println(dataSource.getConnection());
	}

}
