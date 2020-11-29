package com.amazonaws.lambda.demo;

import java.sql.*;
import java.util.LinkedList;

public class JdbcConnector {

	@SuppressWarnings("unused")
	public static Connection getCon() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			System.out.println("Where is your MySQL JDBC Driver?");
			e1.printStackTrace();
			return null;
		}

		System.out.println("MySQL JDBC Driver Registered!");
		Connection connection = null;

		try {
			connection = DriverManager.getConnection(
					"jdbc:mysql://lambda-demo.cegwmtais7hb.us-east-1.rds.amazonaws.com:3306/lambda_demo", "admin",
					"password");
			return connection;
		} catch (SQLException e) {
			System.out.println("Connection Failed!:\n" + e.getMessage());
		}

		if (connection != null) {
			System.out.println("SUCCESS!!!! You made it, take control on your database now!");
		} else {
			System.out.println("FAILURE! Failed to make connection!");
		}
		return connection;
	}
	
	
	

	static LinkedList<DataPojo> runTestQuery(Connection conn, String selectQuery) {
		Statement statement = null;
		DataPojo row;
		LinkedList<DataPojo> data = new LinkedList<DataPojo>();

		try {

			System.out.println("Creating statement...");
			statement = conn.createStatement();
			String sql;
			sql = selectQuery;
			ResultSet rs = statement.executeQuery(sql);

			// STEP 5: Extract data from result set
			while (rs.next()) {
				// Retrieve by column name
				int recipe_id = rs.getInt("recipe_id");
				String recipe_name = rs.getString("recipe_name");

				// Display values
				System.out.print("Recipe ID: " + recipe_id);
				System.out.print(", Recipe Name: " + recipe_name);

				row = new DataPojo();
				row.setRecipe_id(recipe_id);
				row.setRecipe_name(recipe_name);
				data.add(row);
			}
			// STEP 6: Clean-up environment
			rs.close();
			statement.close();
			conn.close();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException se2) {
			} // nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try
		} // end try
		return data;
	}




	public static int insertQuery(Connection conn, String query) {
		Statement statement = null;
		int rs = 0;
		try {

			System.out.println("Creating statement...");
			statement = conn.createStatement();
			String sql;
			sql = query;
			rs = statement.executeUpdate(sql);
			statement.close();
			conn.close();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException se2) {
			} // nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try
		} // end try
		
		return rs;
	}




	public static LinkedList<DataPojo> updateQuery(Connection conn, String query) {
		Statement stmt = null;
		DataPojo row;
		LinkedList<DataPojo> data = new LinkedList<DataPojo>();
		try {
		//STEP 1: Execute a query
	      System.out.println("Creating statement...");
	      stmt = conn.createStatement();
	      String sql = query;
	      stmt.executeUpdate(sql);

	      // Now you can extract all the records
	      // to see the updated records
	      sql = "SELECT recipe_id, recipe_name FROM recipe";
	      ResultSet rs = stmt.executeQuery(sql);

	      while(rs.next()){
	    	// Retrieve by column name
				int recipe_id = rs.getInt("recipe_id");
				String recipe_name = rs.getString("recipe_name");

				// Display values
				System.out.print("Recipe ID: " + recipe_id);
				System.out.print(", Recipe Name: " + recipe_name);

				row = new DataPojo();
				row.setRecipe_id(recipe_id);
				row.setRecipe_name(recipe_name);
				data.add(row);
	      }
	      rs.close();
	   }catch(SQLException se){
	      //Handle errors for JDBC
	      se.printStackTrace();
	   }catch(Exception e){
	      //Handle errors for Class.forName
	      e.printStackTrace();
	   }finally{
	      //finally block used to close resources
	      try{
	         if(stmt!=null)
	            conn.close();
	      }catch(SQLException se){
	      }// do nothing
	      try{
	         if(conn!=null)
	            conn.close();
	      }catch(SQLException se){
	         se.printStackTrace();
	      }//end finally try
	   }//end try
	   System.out.println("Goodbye!");
		return data;
	}




	public static LinkedList<DataPojo> deleteQuery(Connection conn, String query) {
		
		Statement stmt = null;
		DataPojo row;
		LinkedList<DataPojo> data = new LinkedList<DataPojo>();
		try {
		//STEP 1: Execute a query
	      System.out.println("Creating statement...");
	      stmt = conn.createStatement();
	      String sql = query;
	      stmt.executeUpdate(sql);

	      // Now you can extract all the records
	      // to see the updated records
	      sql = "SELECT recipe_id, recipe_name FROM recipe";
	      ResultSet rs = stmt.executeQuery(sql);

	      while(rs.next()){
	    	// Retrieve by column name
				int recipe_id = rs.getInt("recipe_id");
				String recipe_name = rs.getString("recipe_name");

				// Display values
				System.out.print("Recipe ID: " + recipe_id);
				System.out.print(", Recipe Name: " + recipe_name);

				row = new DataPojo();
				row.setRecipe_id(recipe_id);
				row.setRecipe_name(recipe_name);
				data.add(row);
	      }
	      rs.close();
	   }catch(SQLException se){
	      //Handle errors for JDBC
	      se.printStackTrace();
	   }catch(Exception e){
	      //Handle errors for Class.forName
	      e.printStackTrace();
	   }finally{
	      //finally block used to close resources
	      try{
	         if(stmt!=null)
	            conn.close();
	      }catch(SQLException se){
	      }// do nothing
	      try{
	         if(conn!=null)
	            conn.close();
	      }catch(SQLException se){
	         se.printStackTrace();
	      }//end finally try
	   }//end try
	   System.out.println("Goodbye!");
		return data;
	}
	
	/*
	 * public static void main(String args[]) {
	 * 
	 * Connection con = getCon(); String query="select * from recipes";
	 * runTestQuery(con,query); }
	 */
}
