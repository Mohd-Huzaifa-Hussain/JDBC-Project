package in.ineuron.main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import in.ineuron.util.JdbcUtil;

public class Update {

	public void updateOp() {
		
		//Resources used
		Connection connection = null;
		PreparedStatement pstmt1 = null, pstmt2 = null;
		Scanner scanner = null;
		ResultSet resultSet = null;
		
		//Variables used
		Integer sid = null;
		String sname = null; 
		String saddress = null;
		Integer sage = null;
		
		try {
			//Establishing the connection
			connection = JdbcUtil.getConnection();
			
			String sqlSelectQuery = "SELECT sid,sname,sage,saddress FROM studentinfo WHERE sid = ?";
			
			if(connection != null)
				pstmt1 = connection.prepareStatement(sqlSelectQuery);
			
			if(pstmt1 != null) {
				
				scanner = new Scanner(System.in);
				if(scanner != null) {
					System.out.println("Enter the id of the student to see the record");
					sid = scanner.nextInt();
				}
				
				pstmt1.setInt(1, sid);
				
				resultSet = pstmt1.executeQuery();
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
				
				String sqlUpdateQuery = "UPDATE studentinfo SET sname=?,sage=?,saddress=? WHERE sid=?";
				pstmt2 = connection.prepareStatement(sqlUpdateQuery);
				
			
					//Take inputs from user
					System.out.println("Enter the new name of the student if not Enter 'N'");
					sname=scanner.next();
					
					System.out.println("Enter the new age of the student  if not Enter 0");
					sage=scanner.nextInt();
					
					System.out.println("Enter the new address of the student if not Enter 'N'");
					saddress=scanner.next();
				}
				
			if(sname.equals("N"))
				pstmt2.setString(1,resultSet.getString("sname"));
			else
				pstmt2.setString(1, sname);
				
			if(sage == 0)
				pstmt2.setInt(2,resultSet.getInt("sage"));
			else
				pstmt2.setInt(2, sage);
				
			if(saddress.equals("N"))
				pstmt2.setString(3,resultSet.getString("saddress"));
			else
				pstmt2.setString(3, saddress);
			
			pstmt2.setInt(4, sid);
				
				int rowAffected = pstmt2.executeUpdate();
				
				if(rowAffected == 1)
					System.out.println("The record inserted succesfully");
				else
					System.out.println("The record insertion failed!");
			
			
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				JdbcUtil.cleanUp(connection, pstmt1, null);
				pstmt2.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
	}

}
