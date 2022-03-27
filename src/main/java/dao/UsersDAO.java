package dao;

import java.sql.*;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class UsersDAO {
	DataSource ds;
	
	/**
	 * construct the object and connect the database
	 * @throws ClassNotFoundException
	 */
	public UsersDAO() throws ClassNotFoundException{
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
	public int insert(int id, String name, String gender, int age, String phoneNumber) throws SQLException, NamingException {
		//note that the query parameters are set as ?
		String preparedStatement ="insert into USERS values(?,?,?,?,?)";
		Connection con = this.ds.getConnection();
		//PreparedStatements prevent SQL injection
		PreparedStatement stmt = con.prepareStatement(preparedStatement); //here we set individual parameters through method calls
		//first parameter is the place holder position in the ? //pattern above
		stmt.setInt(1, id);
		stmt.setString(2, name);
		stmt.setString(3, gender);
		stmt.setInt(4, age);
		stmt.setString(5, phoneNumber);
		
		return stmt.executeUpdate(); 
	}
	
	/**
	 * delete the record in database with id
	 * @param sid
	 * @return 
	 * @throws SQLException
	 * @throws NamingException
	 */
	public int delete(int id) throws SQLException, NamingException{
		String preparedStatement ="delete from USERS where id=?"; 
		Connection con = this.ds.getConnection();
		PreparedStatement stmt = con.prepareStatement(preparedStatement);
		stmt.setInt(1, id);
		return stmt.executeUpdate();
	}
	
	/**
	 * check the id of this user
	 * @param name
	 * @param gender
	 * @param age
	 * @return the number of id
	 * @throws SQLException
	 * @throws NamingException
	 */
	public int getID(String name, String gender, int age) throws SQLException, NamingException {
		String query = "select id from USERS where name = ? and gender = ? and age = ?";
		
		int id = -1;
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		p.setString(1, name);
		p.setString(2, gender);
		p.setInt(3, age);
		ResultSet r = p.executeQuery();
		
		while (r.next()){
			id = r.getInt("id");
			
		}
		r.close();
		p.close();
		con.close();
		return id;
	}
	
	/**
	 * get a new id for new user
	 * @return new id
	 * @throws SQLException
	 * @throws NamingException
	 */
	public int newId() throws SQLException, NamingException {
		String query = "select max(id) as mi from USERS";
		int id = -1;
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		
		while (r.next()){
			try {
				id = r.getInt("mi");
			}
			catch(Exception e) {
				
			}
			
		}
		System.out.println(id);
		r.close();
		p.close();
		con.close();
		return id + 1;
	}
}