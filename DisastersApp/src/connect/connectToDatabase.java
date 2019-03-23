package connect;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class connectToDatabase{

	private static final String DATABASE_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/disasters";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "1994";
	private static ResultSet result;
	
	private Connection connection = null;
	private PreparedStatement statement = null;
	
	public connectToDatabase() {
		
		try{
			Class.forName(DATABASE_DRIVER);
			connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);	
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void sendQuery(String query, ArrayList<String> res, String type) {
		
		try{
			statement = connection.prepareStatement(query);
			result = statement.executeQuery();
			
			while(result.next()) {
				if (!type.equals("")) {
					res.add(result.getString(type));
				}
				else {
					res.add(String.valueOf(result.getDouble(1)));
					System.out.println(result.getDouble(1));
				}
				
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	
}
		

