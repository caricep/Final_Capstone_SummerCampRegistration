package com.techelevator.jdbc;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.camper.model.Camper;
import com.techelevator.dao.CamperDAO;

@Component
public class JdbcCamperDAO implements CamperDAO{
	
	private JdbcTemplate jdbcTemplate;
	
	public JdbcCamperDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<Camper> listAllCampers() {
		String sql = "SELECT camper_id, camper_first_name, camper_last_name, date_of_birth, street_address, "
				+ "street_address_line_two, city, state_code, zip_code, payment_status, family_contact_id, additional_camper_notes FROM camper";
		SqlRowSet rows = jdbcTemplate.queryForRowSet(sql);
		
		List<Camper> campers = new ArrayList<Camper>();
		while (rows.next()) {
			Camper camper = mapCamperFromRowSet(rows);
			campers.add(camper);
		}
		return campers;
	}
	
	@Override
	public void uploadFile(File file) {
		
		
		
	}
	
	private Camper mapCamperFromRowSet(SqlRowSet camperRows) {
		Camper camper = new Camper();
		camper.setCamperID(camperRows.getInt("camper_id"));
		camper.setFirstName(camperRows.getString("camper_first_name"));
		camper.setLastName(camperRows.getString("camper_last_name"));
		camper.setDateOfBirth(camperRows.getDate("date_of_birth").toLocalDate());
		camper.setStreetAddress(camperRows.getString("street_address"));
		camper.setStreetAddressLineTwo(camperRows.getString("street_address_line_two"));
		camper.setCity(camperRows.getString("city"));
		camper.setStateCode(camperRows.getString("state_code"));
		camper.setZipCode(camperRows.getString("zip_code"));
		camper.setPaymentStatus(camperRows.getBoolean("payment_status"));
		camper.setAdditionalNotes(camperRows.getString("additional_camper_notes"));
		return camper;
	}

}
