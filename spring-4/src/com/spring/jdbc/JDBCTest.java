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
	 * 使用具名参数时，可以使用.update(String sql, SqlParameterSource paramSource) 方法进行更新操作
	 * 1.SQL 语句中的参数名和类的属性一致！
	 * 2.使用SqlParameterSource 的BeanPropertySqlParameterSource 实现类作为参数
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
	 * 可以为参数起名字
	 * 1.好处：若有多个参数，则不用再去对应位置，直接对应参数名，便于维护
	 * 2.缺点：较为麻烦
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
	 * 获取单个列的值或者做统计查询
	 * 使用queryForObject(String sql, Class<Long> requiredType) 
	 */
	@Test
	public void testQueryForObject2() {
		String sql = "select count(id) from employees";
		long count = jdbcTemplate.queryForObject(sql, Long.class);
		System.out.println(count);
	}

	/**
	 * 查到实体类的集合
	 * 注意调用的不是queryForList 方法
	 */
	@Test
	public void testQueryForList() {
		String sql = "select id,name,email from employees where id > ?";
		RowMapper<Employee> rowMapper = new BeanPropertyRowMapper<>(Employee.class);
		List<Employee> employees = jdbcTemplate.query(sql, rowMapper,2);
		System.out.println(employees);
	}
	
	/**
	 * 从数据库中获取一条记录，实际得到对应的一个对象
	 * 注意不是调用queryForObject(String sql, Class<Employee> requiredType, Object... args) 方法！
	 * 而需要调用.queryForObject(String sql, RowMapper<Employee> rowMapper, Object... args)
	 * 1.其中的RowMapper 指定如何去映射结果集的行，常用的实现类为BeanPropertyRowMapper
	 * 2.使用sql 中列的别名完成列名和类的属性名的映射。例如last_name lastName
	 * 3.不支持级联属性。JdbcTemplate 到底只是一个JDBC 的小工具，而不是ORM 框架
	 */
	@Test
	public void testQueryForObject(){
		String sql = "select id,name,email,department_id as \"department.id\" from employees where id=?";
		RowMapper<Employee> rowMapper = new BeanPropertyRowMapper<>(Employee.class) ;
		Employee employee = jdbcTemplate.queryForObject(sql, rowMapper, 1);
		System.out.println(employee);
	}
	
	/**
	 * 执行批量更新：批量的INSET,UPDATE,DELETE
	 * 最后一个参数是Object[] 的List 类型：以为修改一条记录需要一个Object 的数组，那么多条不就需要多个Object 的数组吗
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
	 * 执行INSERT,UPDATE,DELETE
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
