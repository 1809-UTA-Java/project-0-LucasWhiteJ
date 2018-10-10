package com.revature;
import java.sql.*;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;
import com.revature.Customer;
import com.revature.CustomerDao;
import com.revature.Account;
import com.revature.AccountDao;
import com.revature.Employee;
import com.revature.EmployeeDao;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import oracle.net.aso.i;

/*
 * Lucas White
 */
public class Project0 
{
	static Logger logger = Logger.getLogger(Project0.class);
    public static void main( String[] args )
    {
        PropertyConfigurator.configure("log4j.properties");
    	mainMenu();
    }
    
    public static void mainMenu() {
    	Scanner sc = new Scanner(System.in);
    	String custOrEmp;
    	int i = 0;
        
        while (i == 0) {
        	System.out.println("Would you like to register a new user account or login?: ");
        	System.out.print("1: Register		2: Login:");
            String input = sc.nextLine();
        if (input.equals("1")) {
        	int x = 0;
        	while(x == 0) {
        	System.out.println("Would you like to register a new Customer or a new Employee?:");
        	System.out.print("1: Customer		2: Employee:");
            custOrEmp = sc.nextLine();
            if (custOrEmp.equals("1")) {
        		registerCust();
        		x = 1;
            }
        	else if (custOrEmp.equals("2")) {
        		registerEmp();
        		x = 1;
        	}
        	else {
        		System.out.println("Use either 1 or 2 to select your decision");
        		input = "";
        	}
        	}
            i = 1;
            }
        else if (input.equals("2")) {
        	int x = 0;
        	while(x == 0) {
        	System.out.println("Would you like to as a Customer or an Employee?: ");
            System.out.print("1: Customer		2: Employee:");
            custOrEmp = sc.nextLine();
            if (custOrEmp.equals("1")) { 
            	custLogin();
            	x =1;
                sc.close();
            }
        	else if (custOrEmp.equals("2")) {
        		empLogin();
        		x =1;
                sc.close();
        	}
        	else {
        		System.out.println("Use either 1 or 2 to select your decision");
        		input = "";
        	}
            i = 1;
        	}
        }
        else {
        	System.out.println("Use either 1 or 2 to select your decision");
        	input = "";
        }
        }
        sc.close();
    }
    
    public static void registerCust() {
    	Scanner sc = new Scanner(System.in);
    	
    	System.out.print("Name: ");
        String custName = sc.nextLine();
        System.out.print("User Name: ");
        String custUserName = sc.nextLine();
        System.out.print("Password: ");
        String custPassword = sc.nextLine();
        
        CustomerDao cDao = new CustomerDao();
        cDao.addNewCustomer(custName, custUserName, custPassword);
        System.out.println("Registration Successful");
        
        Customer c = cDao.getCustByUserName(custUserName);
        c.getCustUserName();
        
        sc.close();
    }
    
    public static void custLogin() {
            Scanner sc = new Scanner(System.in);
            int i = 0;
            
            while(i==0) {
            System.out.print("User Name: ");
            String userName = sc.nextLine();
            System.out.print("Password: ");
            String passWord = sc.nextLine();
            
            CustomerDao cDao = new CustomerDao();
            Customer x = cDao.getCustByUserName(userName);
            
            String pw = x.getCustPassword();
            if (passWord.equals(pw)) {
            	System.out.println("Login Successful");
            	int custID = x.getCustID();
            	int j = 0;
            	
            	while(j==0) {
            	System.out.println("Would you like to 1: Open a new account 2: Make a deposit 3: Make a withdawal 4: Transfer money between accounts 5: View your account information?: ");
                System.out.print("1: Open Account 	2: Deposit 	3: Withdrawal 	4: Transfer 	5: View Account");
                String userInput = sc.nextLine();
                
                if (userInput.equals("1")) {
                	createAcc(custID);
                }
                else if (userInput.equals("2")) { 
                	makeDeposit(userName);
                	j=1;
                    sc.close();
                }
                else if (userInput.equals("3")) {
                	makeWithdrawal(userName);
                	j=1;
                    sc.close();
                }
                else if (userInput.equals("4")) {
                	makeTransfer(userName);
                	j=1;
                    sc.close();
                }
                else if (userInput.equals("5")) {
                	viewAccount(userName);
                	j=1;
                    sc.close();
                }
                else
                	System.out.println("Use either 1, 2, 3, 4, 5 to select your decision");
            	}
                i = 1;
            	}
            else
            	System.out.println("Login Failed");
            }
    }
    
    public static void createAcc(int custID) {
    	Scanner sc = new Scanner(System.in);
    	int i = 1;
    	int coownerID = 0;
    	
        while(i == 1) {
        	System.out.print("Is this a joint account? Y/N: ");
            String input = sc.nextLine();
        	if(input.equals("Y") || input.equals("y")) {
        		System.out.print("User Name of Co-Owner: ");
        		String coownerUserName = sc.nextLine();
        		CustomerDao cDao = new CustomerDao();
                Customer x = cDao.getCustByUserName(coownerUserName);
                coownerID = x.getCustID();
        		i = 0;
        	} else if(input.equals("N") || input.equals("n")) {
        		i = 0;
        	} else
        		System.out.println("Input must be either Y or N");        		
        }
    	System.out.print("Account Name: ");
        String accName = sc.nextLine();
        
        sc.close();
        
        AccountDao aDao = new AccountDao();
        aDao.addNewAccount(accName, custID, coownerID);
        System.out.println("Account awaitng approval");
    }
    
    public static void registerEmp() {
    	Scanner sc = new Scanner(System.in);
    	int i=0;
    	
    	System.out.print("Name: ");
        String empName = sc.nextLine();
        System.out.print("User Name: ");
        String empUserName = sc.nextLine();
        System.out.print("Password: ");
        String empPassword = sc.nextLine();
        while(i==0) {
        System.out.print("Is this Employee an Admin?: ");
        System.out.print("1: Yes		2: No");
        String isAnAdmin = sc.nextLine();
        if(isAnAdmin.equals("1")) {
        	isAnAdmin = "true";
        	i=1;
        }
        else if (isAnAdmin.equals("2")) {
        	isAnAdmin = "false";
        	i=1;
        }
        else
        	System.out.println("Use either 1 or 2 to select your decision");
        EmployeeDao eDao = new EmployeeDao();
        eDao.addNewEmployee(empName, empUserName, empPassword, isAnAdmin);
        System.out.println("Registration Successful");
        }
        sc.close();
    }
    
    public static void empLogin() {            
            Scanner sc = new Scanner(System.in);
            int i=0;
            int z=0;
            
            while(z==0) {
            System.out.print("User Name: ");
            String userName = sc.nextLine();
            System.out.print("Password: ");
            String passWord = sc.nextLine();
            
            EmployeeDao eDao = new EmployeeDao();
            Employee x = eDao.getEmpByUserName(userName);
            
            String pw = x.getEmpPassword();
            if (passWord.equals(pw)) {
            	z=1;
            	System.out.println("Login Successful");
            	
            	while(i==0) {
            	System.out.println("Would you like to 1: Review accounts 2: Make a deposit 3: Make a withdawal 4: Transfer money between accounts 5: View Customer account information 6: Cancel an account?: ");
                System.out.print("1: Review Account 	2: Deposit 	3: Withdrawal 	4: Transfer 	5: View Account 	6: Cancel Account");
                String input = sc.nextLine();
                
                if (input.equals("1")) {
                    System.out.print("User Name of Customer who's account(s) you'ld like to review for approval:");
                    String custUserName = sc.nextLine();
                    CustomerDao cDao = new CustomerDao();
                    Customer c = cDao.getCustByUserName(custUserName);
                    int custID = c.getCustID(); 
                	reviewAcc(custID);
                	i=1;
                }
                else if (input.equals("2")) {
                	if(x.getIsAnAdmin().equals("true")) {
                	System.out.print("User Name of Customer who's account you'ld like to make a deposit into:");
                    String custUserName = sc.nextLine();
                    CustomerDao cDao = new CustomerDao();
                    Customer c = cDao.getCustByUserName(custUserName);
                	makeDeposit(custUserName);
                	i=1;
                	}
                	else
                		System.out.println("You must be an Admin to perform this action");
                }
                else if (input.equals("3")) {
                	if(x.getIsAnAdmin().equals("true")) {
                	System.out.print("User Name of Customer who's account you'ld like to make a withdrawal from:");
                    String custUserName = sc.nextLine();
                    CustomerDao cDao = new CustomerDao();
                    Customer c = cDao.getCustByUserName(custUserName);
                	makeWithdrawal(custUserName);
                	i=1;
                	}
                	else
                		System.out.println("You must be an Admin to perform this action");
                }
                else if (input.equals("4")) {
                	if(x.getIsAnAdmin().equals("true")) {
                	System.out.print("User Name of Customer who's accounts you'ld like to transfer funds between:");
                    String custUserName = sc.nextLine();
                    CustomerDao cDao = new CustomerDao();
                    Customer c = cDao.getCustByUserName(custUserName);
                	makeTransfer(custUserName);
                	i=1;
                	}
                	else
                		System.out.println("You must be an Admin to perform this action");
                }
                else if (input.equals("5")) {
                	System.out.print("User Name of Customer who's personnal information and accounts you'ld like to view:");
                    String custUserName = sc.nextLine();
                    CustomerDao cDao = new CustomerDao();
                    Customer c = cDao.getCustByUserName(custUserName);
                	viewAccount(custUserName);
                	i=1;
                }
                else if (input.equals("6")) {
                	if(x.getIsAnAdmin().equals("true")) {
                	System.out.print("User Name of Customer who's you'ld like to cancel:");
                    String custUserName = sc.nextLine();
                    System.out.print("Name of Account that you want to cancel:");
                    String accName = sc.nextLine();
                    CustomerDao cDao = new CustomerDao();
                    Customer c = cDao.getCustByUserName(custUserName);
                    int custID = c.getCustID();
                	cancelAcc(custID, accName);
                	i=1;
                	}else
                		System.out.println("You must be an Admin to perform this action");
                }
                else
                	System.out.println("Use either 1, 2, 3, 4, 5, or 6 to select your decision");
            	}
            }
            else
            	System.out.println("Login Failed");
            }
            sc.close();
    }
    
    public static void makeDeposit(String userName) {
    	Scanner sc = new Scanner(System.in);
    	int i = 0;
    	
    	while(i==0) {
    	System.out.print("Account Name: ");
        String accName = sc.nextLine();
        System.out.print("Amount to deposit: ");
        String inputAmount = sc.nextLine();
        try {
        int amount = Integer.parseInt(inputAmount);
        if(amount>=0) {
        CustomerDao cDao = new CustomerDao();
        Customer x = cDao.getCustByUserName(userName);
        int ownerid = x.getCustID();

        AccountDao aDao = new AccountDao();
        aDao.makeDeposit(accName, ownerid, amount);
        i=1;
        System.out.println("Deposit made Successfully");
        logger.debug("A deposit of " + amount + " was made to " + accName + " which is owned by " + x.getCustUserName());
        } else
        	System.out.println("Amount cannot be negative");
    	}
        catch (NumberFormatException ex) {
        	System.out.println("Must be a number");
    	}
    	}
        sc.close();
    }
    
    public static void makeWithdrawal(String userName) {
    	Scanner sc = new Scanner(System.in);
    	int i = 0;
    	
    	while(i==0) {
    	
    	System.out.print("Account Name: ");
        String accName = sc.nextLine();
        System.out.print("Amount to withdraw: ");
        String inputAmount = sc.nextLine();
        try {
        int amount = Integer.parseInt(inputAmount);
        if(amount>=0) {
        CustomerDao cDao = new CustomerDao();
        Customer x = cDao.getCustByUserName(userName);
        int ownerid = x.getCustID();
        
        AccountDao aDao = new AccountDao();
        aDao.makeWithdrawal(accName, ownerid, amount);
        System.out.println("Withdrawal made Successfully");
        i=1;
        logger.debug("A withdrawal of " + amount + " was made from " + accName + " which is owned by " + x.getCustUserName());
        } else
        	System.out.println("Amount cannot be negative");
        }
        catch (NumberFormatException ex) {
        	System.out.println("Must be a number");
    	}
    	}
        sc.close();
    }
    
    public static void makeTransfer(String userName) {
    	Scanner sc = new Scanner(System.in);
    	int i = 0;
    	
    	while(i==0) {
    	
    	System.out.print("Account to Transfer from: ");
        String accName = sc.nextLine();
        System.out.print("Account to Transfer to: ");
        String otherAccName = sc.nextLine();
        System.out.print("Amount to withdraw: ");
        String inputAmount = sc.nextLine();
        try {
        int amount = Integer.parseInt(inputAmount);
        if(amount>=0) {
        
        CustomerDao cDao = new CustomerDao();
        Customer x = cDao.getCustByUserName(userName);
        int ownerid = x.getCustID();
        
        AccountDao aDao = new AccountDao();
        aDao.makeTransfer(accName, otherAccName, ownerid, amount);
        i=1;
        System.out.println("Transfer Successful");
        logger.debug("A transfer of " + amount + " was made from " + accName + " to " + otherAccName + " which is owned by " + x.getCustUserName());
        } else
        	System.out.println("Amount cannot be negative");
        }
        catch (NumberFormatException ex) {
        	System.out.println("Must be a number");
    	}
    	}
        sc.close();
    }
    
    public static void viewAccount(String userName) {
    	AccountDao aDao = new AccountDao();
    	CustomerDao cDao = new CustomerDao();
        Customer x = cDao.getCustByUserName(userName);
        int ownerID = x.getCustID();
        System.out.println(x.toString());
        System.out.println("--------------------------------------------------------------------------------------");
        List<Account> accounts = aDao.getAccountsByOwnerID(ownerID);
		for (Account a : accounts) {
			System.out.println(a.toString());
		}
    }

    public static void reviewAcc(int custID) {
    	AccountDao aDao = new AccountDao();
    	aDao.updateAccStatus(custID);
    }
    
    public static void cancelAcc(int custID, String accName) {
    	AccountDao aDao = new AccountDao();
    	aDao.cancelAccount(custID, accName);
    	System.out.println("Account Canceled");
    }
}
