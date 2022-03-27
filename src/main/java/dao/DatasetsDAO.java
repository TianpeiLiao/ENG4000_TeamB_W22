package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class  DatasetsDAO {
	DataSource ds;
	
	/**
	 * construct the object and connect the database
	 * @throws ClassNotFoundException
	 */
	public DatasetsDAO() throws ClassNotFoundException{
		try {
			ds = (DataSource) (new InitialContext()).lookup("java:/comp/env/jdbc/EECS");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * insert new record in DATASETS
	 * @param datasetId
	 * @param accX
	 * @param accY
	 * @param accZ
	 * @param dtime
	 * @param state
	 * @return
	 * @throws SQLException
	 * @throws NamingException
	 */
	public int insert(int datasetId, double accX, double accY, double accZ,  String state) throws SQLException, NamingException {
		//note that the query parameters are set as ?
		String preparedStatement ="insert into DATASETS values(?,?,?,?,?,?)";
		Connection con = this.ds.getConnection();
		//PreparedStatements prevent SQL injection
		PreparedStatement stmt = con.prepareStatement(preparedStatement); //here we set individual parameters through method calls
		//first parameter is the place holder position in the ? //pattern above
		stmt.setInt(1, datasetId);
		stmt.setDouble(2, accX);
		stmt.setDouble(3, accY);
		stmt.setDouble(4, accZ);
		stmt.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
		stmt.setString(6, state);
		
		return stmt.executeUpdate(); 
	}
	
	public List<String> getStates(int datasetId) throws SQLException, NamingException {
		List<String> result = new ArrayList<>();
		//note that the query parameters are set as ?
		String preparedStatement ="select state from DATASETS where datasetID = ? order by dtime";
		Connection con = this.ds.getConnection();
		//PreparedStatements prevent SQL injection
		PreparedStatement stmt = con.prepareStatement(preparedStatement); //here we set individual parameters through method calls
		//first parameter is the place holder position in the ? //pattern above
		stmt.setInt(1, datasetId);
		ResultSet r = stmt.executeQuery();
		while (r.next()){
			result.add(r.getString("state"));
		}
		r.close();
		stmt.close();
		con.close();
		
		return result; 
	}
	
	/**
	 * delete the record in database with datasetId
	 * @param datasetId
	 * @return
	 * @throws SQLException
	 * @throws NamingException
	 */
	public int delete(int datasetId) throws SQLException, NamingException{
		String preparedStatement ="delete from DATASETS where datasetID=?"; 
		Connection con = this.ds.getConnection();
		PreparedStatement stmt = con.prepareStatement(preparedStatement);
		stmt.setInt(1, datasetId);
		return stmt.executeUpdate();
	}
	
	/**
	 * get a new id for new user
	 * @return new id
	 * @throws SQLException
	 * @throws NamingException
	 */
	public int newDatasetId() throws SQLException, NamingException {
		String query = "select max(datasetID) as md from DATASETS";
		int id = -1;
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		while (r.next()){
			try {
				id = r.getInt("md");
			}
			catch(Exception e) {
				
			}
		}
		r.close();
		p.close();
		con.close();
		return id + 1;
	}
}