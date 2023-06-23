package in.ineuron.main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import in.ineuron.util.JdbcUtil;

public class Select {

	public void SelectOp() {
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		Scanner scanner = null;
		ResultSet resultSet = null;
		
		int sid = 0; 
		
		try {
			connection = JdbcUtil.getConnection();
			
			String sqlSelectQuery = "SELECT sid,sname,sage,saddress FROM studentinfo WHERE sid = ?";
			if(connection != null)
				pstmt = connection.prepareStatement(sqlSelectQuery);
			
			if(pstmt != null) {
				
				scanner = new Scanner(System.in);
				if(scanner != null) {
					System.out.println("Enter the id of the student to see the record");
					sid = scanner.nextInt();
				}
				
				pstmt.setInt(1, sid);
				
				resultSet = pstmt.executeQuery();
			}
			
			if (resultSet != null) {
				
				if(resultSet.next()) {
					System.out.println("SID\tSNAME\tSAGE\tSADDRESS");
					Integer id = resultSet.getInt("sid");
					String name = resultSet.getString("sname");
					Integer age = resultSet.getInt("sage");
					String address = resultSet.getString("saddress");
					
					System.out.println(id + "\t" + 
									   name + "\t" +
									   age + "\t" +
									   address);
					
				}
				else {
					System.out.println("Student id not found!");
				}
			}
		} catch (IOException | SQLException e) {
			
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				JdbcUtil.cleanUp(connection, pstmt, resultSet);
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		
	}
}
