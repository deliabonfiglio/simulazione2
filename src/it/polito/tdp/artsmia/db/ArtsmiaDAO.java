package it.polito.tdp.artsmia.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.artsmia.model.ArtObject;
import it.polito.tdp.artsmia.model.Exhibition;

public class ArtsmiaDAO {

	public List<ArtObject> listObject(Integer anno) {
		
		String sql = "SELECT obj.* "+
				"from objects as obj, exhibition_objects as eo, exhibitions as e "+
				"where e.`begin`>=? and e.exhibition_id= eo.exhibition_id and eo.object_id= obj.object_id ";

		List<ArtObject> result = new ArrayList<>();

		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, anno);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				
				result.add(new ArtObject(res.getInt("object_id"), res.getString("classification"), res.getString("continent"), 
						res.getString("country"), res.getInt("curator_approved"), res.getString("dated"), res.getString("department"), 
						res.getString("medium"), res.getString("nationality"), res.getString("object_name"), res.getInt("restricted"), 
						res.getString("rights_type"), res.getString("role"), res.getString("room"), res.getString("style"), res.getString("title")));
			}

			conn.close();
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Integer> listAnni() {
		
		String sql = "SELECT DISTINCT begin from exhibitions ORDER BY begin ";

		List<Integer> result = new ArrayList<>();

		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet res = st.executeQuery();

			while (res.next()) {
				
				result.add(res.getInt("begin"));
			}

			conn.close();
			return result;
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Exhibition> getMostre(Integer anno) {
		
		String sql = "SELECT DISTINCT e.* "+
				"from exhibition_objects as eo, exhibitions as e "+
			"where e.`begin`>=? and e.exhibition_id= eo.exhibition_id ";

		List<Exhibition> result = new ArrayList<>();

		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, anno);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				Exhibition e = new Exhibition(res.getInt("exhibition_id"), res.getString("exhibition_department"), res.getString("exhibition_title"), res.getInt("begin"), res.getInt("end"));
				result.add(e);
				System.out.println(result.size());
			}

			conn.close();
			//System.out.println(result.size());
			return result;
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	public String getmax(Integer anno) {
		
		String sql = "select max(cnt) as mas, exhibition_id "+
						"from (select count(eo.exhibition_id) as cnt, eo.exhibition_id "+
									"from exhibitions as e, exhibition_objects as eo "+
									"where  e.exhibition_id=eo.exhibition_id and e.`begin`> ? "+
									"Group by e.exhibition_id ) as temp ";

		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, anno);
			ResultSet res = st.executeQuery();

			res.next() ;
				
			String result = res.getString("exhibition_id")+" "+res.getInt("mas");

			conn.close();
			//System.out.println(result.size());
			return result;
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	
}
