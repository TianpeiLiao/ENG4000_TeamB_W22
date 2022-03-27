package dao;

import java.sql.*;
import java.util.Calendar;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class RecordsDAO {
	DataSource ds;
	
	/**
	 * construct the object and connect the database
	 * @throws ClassNotFoundException
	 */
	public RecordsDAO() throws ClassNotFoundException{
		try {
			ds = (DataSource) (new InitialContext()).lookup("java:/comp/env/jdbc/EECS");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * insert new record in database
	 * @param sid
	 * @param givenname
	 * @param surname
	 * @param credittake
	 * @param creditgraduate
	 * @return
	 * @throws SQLException
	 * @throws NamingException
	 */
	public int insert(int id, int datasetID) throws SQLException, NamingException {
		//note that the query parameters are set as ?
		String preparedStatement ="insert into RECORDS values(?,?,?)";
		Connection con = this.ds.getConnection();
		//PreparedStatements prevent SQL injection
		PreparedStatement stmt = con.prepareStatement(preparedStatement); //here we set individual parameters through method calls
		//first parameter is the place holder position in the ? //pattern above
		stmt.setInt(1, id);
		stmt.setDate(2, new Date(Calendar.getInstance().getTimeInMillis()));
		stmt.setInt(3, datasetID);
		
		return stmt.executeUpdate(); 
	}
	
	/**
	 * delete the record in database with sid
	 * @param sid
	 * @return
	 * @throws SQLException
	 * @throws NamingException
	 */
	public int delete(int id) throws SQLException, NamingException{
		String preparedStatement ="delete from RECORDS where id=?"; 
		Connection con = this.ds.getConnection();
		PreparedStatement stmt = con.prepareStatement(preparedStatement);
		stmt.setInt(1, id);
		return stmt.executeUpdate();
	}
	
	
	public int getDatasetID(int id, Date dateOfRecord) throws SQLException, NamingException {
        String query = "select id from RECORDS where id = ? and dateOfRecord = ?";
        int datasetID = -1;
        Connection con = this.ds.getConnection();
        PreparedStatement p = con.prepareStatement(query);
        p.setInt(1, id);
        p.setDate(2, dateOfRecord);
        ResultSet r = p.executeQuery();
        while (r.next()){
        	try {
        		datasetID = r.getInt("datasetID");
			}
			catch(Exception e) {
				
			}
            
        }
        r.close();
        p.close();
        con.close();
        return datasetID;
    }
}