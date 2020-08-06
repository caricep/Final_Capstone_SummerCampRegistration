package com.techelevator.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import com.techelevator.camper.model.Dorm;
import com.techelevator.jdbc.JdbcDormDAO;

public class JdbcDormDaoIntegrationTest extends DAOIntegrationTest {

	private DormDAO dormDAO;
	private JdbcTemplate jdbcTemplate;
	
	@Before
	public void setup() {
		jdbcTemplate = new JdbcTemplate(getDataSource());
		dormDAO = new JdbcDormDAO(jdbcTemplate);
	}
	
	@Test
	public void listOfDorms() {
		List<Dorm> dorms = dormDAO.getListOfDorms();
		int originalCount = dorms.size();
		createDorm();
		int newCount = dormDAO.getListOfDorms().size();
		Assert.assertEquals(originalCount + 1, newCount);
	}
	
	private void createDorm() {
		String sql = "INSERT INTO dorm (dorm_id, dorm_name, camper_id) VALUES (?, ?, ?)";
		jdbcTemplate.update(sql, 999, "Dorm Test Name", 9999);
	}
	
	
}
