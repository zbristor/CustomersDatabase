import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class CustomersApp {
	public static void main(String[] args) {
		String check = "yes";
		String inp = "1";
		Customers c1 = new Customers();
		Scanner sc = new Scanner(System.in);
		String sql = null;
		String sql2 = null;
		String sql3 = null;
		String lastName=null;
		String street=null;
		String city=null; 
		String state=null;
		String zip=null;
		do{
		if(inp.equals("1")){
			lastName=c1.getInput();
		}
		else if(inp.equals("2"))
		{
			System.out.println("Enter the new street: ");
			street = sc.nextLine();
			System.out.println("Enter the new city: ");
			city = sc.nextLine();
			System.out.println("Enter the new state: ");
			state = sc.nextLine();
			System.out.println("Enter the new zip code: ");
			zip = sc.nextLine();
			System.out.println();
		}
		sql = "select FirstName, LastName, StreetAddress,City,State,ZipCode, EmailAddress, Position from customers,Address,tblPosition "
				+ "where customers.AddressID=Address.AddressID AND customers.PositionID=tblPosition.PositionID AND customers.LastName=?";
		sql2 = "update Address set StreetAddress=?,City=?,State=?,ZipCode=? where Address.AddressID=customers.AddressID";
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement pstmt=null;
		PreparedStatement pstmt2=null;

		{
		try{
			Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Customers?"+ "user=root&password=password");
            
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, lastName);
            //if(inp.equals("1")){
            rs = pstmt.executeQuery();
    		while(rs.next()){

    			System.out.print(rs.getString("FirstName")+ "\t");
    			System.out.print(rs.getString("LastName") + "\n");
    			System.out.print(rs.getString("StreetAddress")+ "\n");
    			System.out.print(rs.getString("City")+", ");
    			System.out.print(rs.getString("State")+ " ");
    			System.out.print(rs.getString("ZipCode")+ "\n");
    			System.out.print(rs.getString("EmailAddress")+"\n");
    			System.out.print(rs.getString("Email"));
    			System.out.println();
    		}
           // }
            pstmt2 = con.prepareStatement(sql2);
    		pstmt2.setString(1, street);
    		pstmt2.setString(2, city);
    		pstmt2.setString(3, state);
    		pstmt2.setString(4, zip);
    		pstmt2.executeUpdate();
    	
        	}catch (SQLException e){
        
		} catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		}finally {
			try {

				pstmt.close();
				//pstmt2.close();
				con.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		
	}
		System.out.println("Press (1) to search for another customer or press (2) to Edit the customer's address.");
		inp = sc.nextLine();
		}while((inp.equals("1")) || inp.equals("2"));
	}
}