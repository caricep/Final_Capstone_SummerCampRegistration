package com.techelevator.jdbc;


import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.camper.model.Camper;
import com.techelevator.camper.model.FamilyContact;
import com.techelevator.camper.model.History;
import com.techelevator.dao.CamperDAO;

@Component
public class JdbcCamperDAO implements CamperDAO {
	
	private JdbcTemplate jdbcTemplate;
	
	public JdbcCamperDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<Camper> listAllCampers() {
		String sql = "SELECT camper_id, camper_first_name, camper_last_name, date_of_birth, street_address, "
				+ "street_address_line_two, city, state_code, zip_code, payment_status, camper.family_contact_id, additional_camper_notes, "
				+ "family_contact.family_contact_id, family_contact.family_contact_name, family_contact.email_address, family_contact.phone_number "
				+ "FROM camper JOIN family_contact ON camper.family_contact_id = family_contact.family_contact_id";

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
	public void history(String changeType, String changeSpecifics, String changeDateAndTime, String userName, int updateStatusId) {
		String sql = "INSERT INTO history (history_id, change_type, change_specifics, change_date_and_time, user_name, update_status_id) "
				+ "VALUES (DEFAULT, ?, ?, ?, ?, ?)";
		jdbcTemplate.update(sql, changeType, changeSpecifics, changeDateAndTime, userName, updateStatusId);
		
	}
	
	@Override
	public List<History> listAllHistory() {
		String sql = "SELECT history_id, change_type, change_specifics, change_date_and_time, user_name, history.update_status_id, update_status.update_status_desc "
				+ "FROM history JOIN update_status ON history.update_status_id = update_status.update_status_id";
		SqlRowSet rows = jdbcTemplate.queryForRowSet(sql);
		
		List<History> histories = new ArrayList<History>();
		while (rows.next()) {
			History history = mapHistoryFromRowSet(rows);
			histories.add(history);
		}
		return histories;
		
		}
	
	@Override
	public void updateCamper(Camper camper) {
		String updateCamperSql = "UPDATE camper SET camper_first_name = ?, camper_last_name = ?, date_of_birth = ?, street_address = ?, "
				+ "street_address_line_two = ?, city = ?, state_code = ?, zip_code = ?, payment_status = ?, family_contact_id = ?, additional_camper_notes = ? WHERE camper_id = ?";
		jdbcTemplate.update(updateCamperSql, camper.getFirstName(), camper.getLastName(), camper.getDateOfBirth(), camper.getStreetAddress(), 
				camper.getStreetAddressLineTwo(), camper.getCity(), camper.getStateCode(), camper.getZipCode(), camper.isPaymentStatus(), camper.getFamilyContact(), camper.getAdditionalNotes(),  camper.getCamperID());
		
		String updateFamilyContactSql = "UPDATE family_contact SET family_contact_name = ?, email_address = ?, phone_number = ? WHERE family_contact_id = ?";
		jdbcTemplate.update(updateFamilyContactSql, camper.getFamilyContactName(), camper.getEmail(), camper.getPhoneNumber(), camper.getFamilyContactId());

		
	}
	
	@Override
	public void updateCamperAddress(Camper camper) {
		String updateSql = "UPDATE camper SET street_address = ?, street_address_line_two = ?, city = ?, state_code = ?, zip_code = ? WHERE camper_id = ?";
		jdbcTemplate.update(updateSql, camper.getStreetAddress(), camper.getStreetAddressLineTwo(), 
				camper.getCity(), camper.getStateCode(), camper.getZipCode(), camper.getCamperID());	
	}

	@Override
	public void updateCamperPaymentStatus(Camper camper) {
		String updateSql = "UPDATE camper SET payment_status = ? WHERE camper_id = ?";
		jdbcTemplate.update(updateSql, camper.isPaymentStatus(), camper.getCamperID());
		
	}

	@Override
	public void updateCamperNotes(Camper camper) {
		String updateSql = "UPDATE camper SET additional_camper_notes = ? WHERE camper_id = ?";
		jdbcTemplate.update(updateSql, camper.getAdditionalNotes(), camper.getCamperID());
		
	}
	
	@Override
	public void updateFamilyContactName(FamilyContact familyContact) {
		String updateSql = "UPDATE family_contact SET family_contact_name = ? WHERE family_contact_id = ?";
		jdbcTemplate.update(updateSql, familyContact.getFamilyContactName(), familyContact.getFamilyContactID());
		
	}
	
	@Override
	public void updateFamilyContactEmailAddress(FamilyContact familyContact) {
		String updateSql = "UPDATE family_contact SET email_address = ? WHERE family_contact_id = ?";
		jdbcTemplate.update(updateSql, familyContact.getEmailAddress(), familyContact.getFamilyContactID());
		
	}

	@Override
	public void updateFamilyContactPhoneNumber(FamilyContact familyContact) {
		String updateSql = "UPDATE family_contact SET phone_number = ? WHERE family_contact_id = ?";
		jdbcTemplate.update(updateSql, familyContact.getPhoneNumber(), familyContact.getFamilyContactID());
		
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
		camper.setFamilyContact(camperRows.getInt("family_contact_id"));
		camper.setAdditionalNotes(camperRows.getString("additional_camper_notes"));
		camper.setFamilyContactId(camperRows.getInt("family_contact_id"));
		camper.setFamilyContactName(camperRows.getString("family_contact_name"));
		camper.setEmail(camperRows.getString("email_address"));
		camper.setPhoneNumber(camperRows.getString("phone_number"));
		return camper;
	}
	
	private History mapHistoryFromRowSet(SqlRowSet historyRows) {
		History history = new History();
		history.setUserName(historyRows.getString("user_name"));
		history.setChangeType(historyRows.getString("change_type"));
		history.setChangeSpecifics(historyRows.getString("change_specifics"));
		history.setChangeDateAndTime(historyRows.getString("change_date_and_time"));
		history.setUpdateStatusId(historyRows.getInt("update_status_id"));
		return history;
	}

	

	

}
