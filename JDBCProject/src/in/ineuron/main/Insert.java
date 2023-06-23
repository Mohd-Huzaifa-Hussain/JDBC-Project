package in.ineuron.main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import in.ineuron.util.JdbcUtil;

public class Insert {

	public void insertOp() {
		
		//Resources used
		Connection connection = null;
		PreparedStatement pstmt = null;
		Scanner scanner = null;
		
		//Variables used
		String sname = null; 
		String saddress = null;
		Integer sage = null;
		
		try {
			//Establishing the connection
			connection = JdbcUtil.getConnection();
			
			String sqlInsertQuery = "INSERT INTO studentinfo(`sname`,`sage`,`saddress`) VALUES(?,?,?)";
			if(connection != null)
				pstmt = connection.prepareStatement(sqlInsertQuery);
			
			if(pstmt != null) {
				
				scanner = new Scanner(System.in);
				
				if (scanner != null) {
					//Take inputs from user
					System.out.println("Enter the name of the student");
					sname=scanner.next();
					
					System.out.println("Enter the age of the student");
					sage=scanner.nextInt();
					
					System.out.println("Enter the address of the student");
					saddress=scanner.next();
				}
				
				pstmt.setString(1, sname);
				pstmt.setInt(2, sage);
				pstmt.setString(3, saddress);
				
				int rowAffected = pstmt.executeUpdate();
				
				if(rowAffected == 1)
					System.out.println("The record inserted succesfully");
				else
					System.out.println("The record insertion failed!");
				
				
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
