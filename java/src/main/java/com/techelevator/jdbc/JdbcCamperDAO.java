package com.techelevator.jdbc;


import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.test.context.jdbc.Sql;

import com.techelevator.camper.model.Camper;
import com.techelevator.camper.model.History;
import com.techelevator.dao.CamperDAO;

import net.bytebuddy.asm.Advice.Local;

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
	public void addCamper(Camper camper) {
		String familySql = "INSERT INTO family_contact (family_contact_id, family_contact_name, email_address, phone_number) "
				+ "VALUES (DEFAULT, ?, ?, ?) RETURNING family_contact_id";
		SqlRowSet row = jdbcTemplate.queryForRowSet(familySql, camper.getFamilyContactName(), camper.getEmail(), camper.getPhoneNumber());
		
		row.next();
		int familyId = row.getInt("family_contact_id");
		camper.setFamilyContactId(familyId);
		
		
		String camperSql = "INSERT INTO camper (camper_id, camper_first_name, camper_last_name, date_of_birth, street_address, street_address_line_two, city, state_code, zip_code, payment_status, family_contact_id, additional_camper_notes) "
				+ "VALUES (DEFAULT, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		jdbcTemplate.update(camperSql, camper.getFirstName(), camper.getLastName(), camper.getDateOfBirth(), camper.getStreetAddress(), camper.getStreetAddressLineTwo(), camper.getCity(), camper.getStateCode(), camper.getZipCode(), camper.isPaymentStatus(), camper.getFamilyContactId(), camper.getAdditionalNotes());
		
	}
	
	@Override
	public void history(String changeType, String changeSpecifics, String changeDateAndTime, String userName) {
		String sql = "INSERT INTO history (history_id, change_type, change_specifics, change_date_and_time, user_name) "
				+ "VALUES (DEFAULT, ?, ?, ?, ?)";
		jdbcTemplate.update(sql, changeType, changeSpecifics, changeDateAndTime, userName);
		
	}
	
	@Override
	public List<History> listAllHistory() {
		String sql = "SELECT history_id, change_type, change_specifics, change_date_and_time, user_name FROM history";
		SqlRowSet rows = jdbcTemplate.queryForRowSet(sql);
		
		List<History> histories = new ArrayList<History>();
		while (rows.next()) {
			History history = mapHistoryFromRowSet(rows);
			histories.add(history);
		}
		return histories;
		
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
	
	private History mapHistoryFromRowSet(SqlRowSet historyRows) {
		History history = new History();
		history.setUserName(historyRows.getString("user_name"));
		history.setChangeType(historyRows.getString("change_type"));
		history.setChangeSpecifics(historyRows.getString("change_specifics"));
		history.setChangeDateAndTime(historyRows.getString("change_date_and_time"));
		return history;
	}

}
