import java.util.Scanner;

public class Customers {
/*
private String firstName;
private String lastName;
private String title;
private String email;
*/
Scanner sc = new Scanner(System.in);
public Customers()
{

}

public String getInput()
{
	System.out.println("Enter your last name: ");
	String input = sc.nextLine();
	return input;
}
}
