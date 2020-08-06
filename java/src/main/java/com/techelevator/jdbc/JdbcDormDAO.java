package com.techelevator.jdbc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.camper.model.Dorm;
import com.techelevator.dao.DormDAO;

@Component
public class JdbcDormDAO implements DormDAO {
	
	private JdbcTemplate jdbcTemplate;
	
	public JdbcDormDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<Dorm> getListOfDorms() {
		String sql = "SELECT dorm_id, dorm_name, camper_id FROM dorm";
		SqlRowSet rows = jdbcTemplate.queryForRowSet(sql);
		
		List<Dorm> dorms = new ArrayList<Dorm>();
		while (rows.next()) {
			Dorm dorm = mapDormFromRowSet(rows);
			dorms.add(dorm);
		}
		return dorms;
	}
	
	@Override
	public void addCamperToDorm(Dorm dorm) {
		String sql = "INSERT INTO dorm (dorm_id, dorm_name, camper_id) VALUES (DEFAULT, ?, ?)";
		jdbcTemplate.update(sql, dorm.getDormName(), dorm.getCamperID());
		
	}
	
	@Override
	public void removeCamperFromDorm(Dorm dorm) {
		String deleteSql = "DELETE FROM dorm WHERE dorm_id = ? AND camper_id = ?";
		jdbcTemplate.update(deleteSql, dorm.getDormID(), dorm.getCamperID());
			
	}

	private Dorm mapDormFromRowSet(SqlRowSet dormRows) {
		Dorm dorm = new Dorm();
		
		dorm.setDormID(dormRows.getInt("dorm_id"));
		dorm.setDormName(dormRows.getString("dorm_name"));
		dorm.setCamperID(dormRows.getInt("camper_id"));
		
		return dorm;
	}

	
}
