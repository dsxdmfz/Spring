package com.spring.jdbc;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class DepartmentDao extends JdbcDaoSupport {

	@Autowired
	public void setDS(DataSource dataSource){
		setDataSource(dataSource);
	}
	
	public Department getDS(Integer id) {
		String sql = "select id,name from department where id = ?";
		RowMapper<Department> rowMapper = new BeanPropertyRowMapper<>(Department.class);
		return getJdbcTemplate().queryForObject(sql, rowMapper, id);
	}
}
