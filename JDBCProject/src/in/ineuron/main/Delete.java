package in.ineuron.main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import in.ineuron.util.JdbcUtil;

public class Delete {

	public void deleteOp() {
		
		//Resources used
		Connection connection = null;
		PreparedStatement pstmt = null;
		Scanner scanner = null;
		
		//Variables used
		
		Integer sid = null;
		
		try {
			//Establishing the connection
			connection = JdbcUtil.getConnection();
			
			String sqlDeleteQuery = "DELETE FROM studentinfo WHERE sid=?";
			if(connection != null)
				pstmt = connection.prepareStatement(sqlDeleteQuery);
			
			if(pstmt != null) {
				
				scanner = new Scanner(System.in);
				
				if (scanner != null) {
					//Take inputs from user
					System.out.println("Enter the id of the student to delete the record");
					sid=scanner.nextInt();
					
					
				}
				
				
				pstmt.setInt(1, sid);
				
				
				int rowAffected = pstmt.executeUpdate();
				
				if(rowAffected == 1)
					System.out.println("The record deleted succesfully");
				else
					System.out.println("The record not available for given id.");
				
				
			}
			
			
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				JdbcUtil.cleanUp(connection, pstmt, null);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

}
