package com.techelevator.dao;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.junit.*;
import org.springframework.jdbc.core.JdbcTemplate;

import com.techelevator.camper.model.Camper;
import com.techelevator.jdbc.JdbcCamperDAO;

public class JdbcCamperDaoIntegrationTest extends DAOIntegrationTest{
	
	private CamperDAO camperDAO;
	private JdbcTemplate jdbcTemplate;
	
	@Before
	public void setup() {
		jdbcTemplate = new JdbcTemplate(getDataSource());
		camperDAO = new JdbcCamperDAO(jdbcTemplate);
	}
	
	@Test
	public void listOfCampers() {
		List<Camper> campers = camperDAO.listAllCampers();
		int originalCount = campers.size();
		insertCamper();
		int newCount = camperDAO.listAllCampers().size();
		Assert.assertEquals(originalCount + 1, newCount);
		
	}
	
	private void insertCamper() {
		String sqlFamily = "INSERT INTO family_contact (family_contact_id, family_contact_name, email_address, phone_number) "
				+ "VALUES (?, ?, ?, ?)";
		jdbcTemplate.update(sqlFamily, 999999, "testFamily", "test@gmail.com", "testNumber");
		
		String sqlCamper = "INSERT INTO camper (camper_id, camper_first_name, camper_last_name, date_of_birth, street_address, street_address_line_two, "
				+ "city, state_code, zip_code, payment_status, family_contact_id, additional_camper_notes) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		jdbcTemplate.update(sqlCamper, 8888888, "testFirst", "testLast", LocalDate.parse("01-01-2001", DateTimeFormatter.ofPattern("MM-dd-yyyy")), "testAddress", "testLineTwo", "testCity", "TE", "43035", false, 999999, "testNotes");
		
	}
	

}
