/**
 * Programmer: 	Sharma Chakravarthy
 * Language:	Java
 * date:        09/27/2013
 * Purpose:	This program uses StadiumMgmtTest class to read data from a text file to initialize
 * 		employees, facilities, games, users etc.
 * 				
 *              It checks and recovers from some exceptions while reading the input file
 * 
 * USAGE:       java StadiumMgmtTest <inputDataFile> <outputFileToWrite>
 *              ex: java StadimumMgmtTest dataFile-proj3.txt out1.txt
 *              if you do not provide files, you will be prompted for that!
 *               
 *              once the values are read into local variables, 
 *              it  is YOUR responsibility to add code at proper places to create objects and manage them!
 *     
 *              you can remove or comment out prints once you are sure it is reading the input correctly
 *
 * MAKE SURE:   your program is case insensitive (for gender, employee type etc.)
 */

import java.io.*;
/*import java.util.Scanner;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Comparator;
import java.util.Collections;
import java.util.List;*/
import java.util.*;
import javax.swing.*;
import java.lang.String;

/**
 * @param fileName
 *            as input data filename containing input items with  ! as item separators
 * @param   outputFileName as output file name 
 */
abstract class Person {
	int personId;
	String fName;
	String lName;
	Date dob;
	String gender;
	Date hireDate;
	Date releaseDate;
	static int idGen = 0;
	
	public Person(String f, String l, Date dob, String g, Date hd, Date rd) {
		idGen++;
		personId = idGen;
		fName = f;
		lName = l;
		this.dob = dob;
		gender = g;
		hireDate = hd;
		releaseDate = rd;
	}
	
	public Person(int id, String f, String l, Date dob) {
		personId = id;
		fName = f;
		lName = l;
		this.dob = dob;
		gender = null;
		hireDate = null;
		releaseDate = null;
	}
	abstract public int age();
	
	public void setReleaseDate(Date rDate) {
		releaseDate = rDate;
	}
	
	public String toString() {
		String fString = String.format("%-4d %-10s %-10s %-12s %-8s %-12s %-12s", 
			personId, fName, lName, dob, gender, hireDate, releaseDate);
		return fString;
	}
}

abstract class SalariedEmployee extends Person implements Employee {
	String type;
	
	public SalariedEmployee(String f, String l, Date dob, String g, Date hd, Date rd, String t) {
		super(f, l, dob, g, hd, rd);
		type = t;
	}
	
	public int getId() {
		return personId;
	}
	
	/**
	* 
	*/
	@Override
	public int age() {
		Date todaysDate = new Date();
		int a;
		a = todaysDate.getYear() - dob.getYear();
		return a;
	}
	
	/**
	*
	*/
	@Override
	public int lengthOfService() {
		int duration;
		if(releaseDate != null) {
			duration = releaseDate.getYear() - hireDate.getYear();  
		}
		else {
			Date todaysDate = new Date();
			duration = todaysDate.getYear() - hireDate.getYear();
		}
		return duration;
	}
}

class WebDesigner extends SalariedEmployee {
	private double monthlyBase;
	private double salary;
	
	public WebDesigner(String f, String l, Date dob, String g, Date hd, Date rd, double mB, double sal) {
		super(f, l, dob, g, hd, rd, "Web Designer");
		monthlyBase = mB;
		salary = sal;
	}
	
	/**
	* @param salaryParameter
	*/
	@Override
	public double computeSalary(int salaryParameter) {
		double total;
		total = (salary * salaryParameter) + monthlyBase;
		return total;
	}
	
	/**
	* toString
	*/
	public String toString() {
		String fString = String.format("%s age: %d   length of service: %d\n", super.toString(), age(), lengthOfService());
		return fString; 
	}
}

class SalesExecutive extends SalariedEmployee {
	private double monthlyBase;
	private double commission;
	
	public SalesExecutive(String f, String l, Date dob, String g, Date hd, Date rd, double mB, double sal) {
		super(f, l, dob, g, hd, rd, "Sales Executive");
		monthlyBase = mB;
		commission = sal;
	}
	
	/**
	* @param salaryParameter
	*/
	@Override
	public double computeSalary(int salaryParameter) {
		double total;
		total = (commission * salaryParameter) + monthlyBase;
		return total;
	}
	
	public String toString() {
		String fString = String.format(super.toString() + "age: %d   length of service: %d\n", age(), lengthOfService());
		return fString; 
	}
}

class Maintainer extends SalariedEmployee {
	private double monthlyBase;
	private double hourlyRate;
	
	public Maintainer(String f, String l, Date dob, String g, Date hd, Date rd, double mB, double sal) {
		super(f, l, dob, g, hd, rd, "Maintainer");
		monthlyBase = mB;
		hourlyRate = sal;
	}

	/**
	* @param salaryParameter
	*/
	@Override
	public double computeSalary(int salaryParameter) {
		double total;
		total = (hourlyRate * salaryParameter) + monthlyBase;
		return total;
	}
	
	public String toString() {
		String fString = String.format(super.toString() + "age: %d   length of service: %d\n", age(), lengthOfService());
		return fString; 
	}
}

class Security extends SalariedEmployee {
	private double monthlyBase;
	private double hourlyRate;
	
	public Security(String f, String l, Date dob, String g, Date hd, Date rd, double mB, double sal) {
		super(f, l, dob, g, hd, rd, "Security");
		monthlyBase = mB;
		hourlyRate = sal;
	}
	
	/**
	* @param salaryParameter
	*/
	@Override
	public double computeSalary(int salaryParameter) {
		double total;
		total = (hourlyRate * salaryParameter) + monthlyBase;
		return total;
	}
	
	public String toString() {
		String fString = String.format(super.toString() + "age: %d   length of service: %d\n", age(), lengthOfService());
		return fString; 
	}
}

class Customer extends Person {
	private String address;
	private String state;
	private String zipCode;
	private String type;
	
	public Customer (int id, String f, String l, Date dob, String adrs, String s, String zip, String t) {
		super(id, f, l, dob);
		address = adrs;
		state = s;
		zipCode = zip;
		type = t;
	}
	
	/**
	* age calc
	*/
	@Override
	public int age() {
		Date todaysDate = new Date();
		int a;
		a = todaysDate.getYear() - dob.getYear();
		return a;
	}
	
	public String toString() {
		String fString = String.format(super.toString() + "%-21s %-4s %-5s %-8s\n", address, state, zipCode, type);
		return fString;
	}
}

class Facility {
	private int id;
	private String type;
	public String name;
	public int capacity;
	public int floor;
	public String rentalType;
	private double rent;
	
	/**
	* Constructor: sets input data to the attributes
	* @param id facility id
	* @param type facility type
	* @param n name of facility
	* @param capacity max capacity of the facility
	* @param floor floor number
	* @param rentalType rental type
	* @param rent amount of rent
	*/
	public Facility(int id, String type, String n, int capacity, int floor, String rentalType, double rent) {
		this.id = id;
		this.type = type;
		name = n;
		this.capacity = capacity;
		this.floor = floor;
		this.rentalType = rentalType;
		this.rent = rent;
	}
	
	
	/**
	* @return returns the rent for the facility
	*/
	public double getRent() {
		return rent;
	}
	/**
	* replaces default toString of object class
	*/
	public String toString() {
		String formattedString = String.format("%-4d %-17s %-17s %-7d %-4d %-16s %-10.2f\n", 
			id, type, name, capacity, floor, rentalType, rent);
		return formattedString;
	}
}

class Game {
	private int gameNum;
	public Date date;
	private Time time;
	private String visitingTeam;
	private int homeScore;
	private int visitingScore;
	
	/**
	* Constructor: sets input data to the attributes
	* @param date date
	* @param num game id
	* @param time time that the game started
	* @param visitT visiting team
	* @param homeS score for home team
	* @param visitS score for visiting team
	*/
	public Game(int num, Date date, Time time, String visitT, int homeS, int visitS) {
		this.date = date;
		gameNum = num;
		this.time = time;
		visitingTeam = visitT;
		homeScore = homeS;
		visitingScore = visitS;
	}
	
	/**
	* Constructor: default constructor that sets everything to null or 0
	*/
	public Game() {
		date = null;
		gameNum = 0;
		time = null;
		visitingTeam = null;
		homeScore = 0;
		visitingScore = 0;
	}
	

	/**
	* replaces default toString of object class
	*/
	public String toString() {
		String formattedString = String.format("Date: %-12s Game Number: %-3d Time: %-9s Visiting Team: %-10s Home Score: %-4d Visiting Score: %-4d\n", 
				date, gameNum, time, visitingTeam, homeScore, visitingScore);
		return formattedString;
	}
	
}

class Enterprise implements Comparable<Enterprise>{
	private String name;
	public int flag=0;
	
	
	private int salesEmpId;
	private int custId;
	
	//ticket info
	private String tktType;
	private int gameID;
	private String year;
	private int numOfTkts;
	private double amount;
	private int boxID;
	
	//rental info
	public String facilityName;
	public Date date;
	private int numOfPeople;
	public int hours;
	
	/**
	* Constructor: sets enterprise name to its attribute
	* @param name enterprise name
	*/
	public Enterprise(String name) {
		this.name = name;
	}
	
	/**
	* Constructor: sets input data to attributes for tickets
	* @param sEmpId employee id that sold ticket to customer
	* @param cId customer id that bought ticket 
	* @param type ticket type
	* @param gID game id
	* @param year year of the game
	* @param numOfTkts number of tickets
	* @param amount price per ticket
	* @param boxID id for box
	*/
	public Enterprise(int sEmpId, int cId, String type, int gID, String year, int numOfTkts, double amount, int boxID) {
		salesEmpId = sEmpId;
		custId = cId;
		tktType = type;
		gameID = gID;
		this.year = year;
		this.numOfTkts = numOfTkts;
		this.amount = amount;
		this.boxID = boxID;
	}
	
	/**
	* Constructor: sets input data to attributes for rentals
	* @param sEmpId employee id that sold ticket to customer
	* @param cId customer id that bought ticket
	* @param facName facility name
	* @param date date it was rented
	* @param people number of people at facility
	* @param hours hours
	*/
	public Enterprise(int sEmpId, int custID, String facName, Date date, int people, int hours) {
		salesEmpId = sEmpId;
		this.custId = custID;
		facilityName = facName;
		this.date = date;
		numOfPeople = people;
		this.hours = hours;
		flag = 1;
	}
	
	/**
	* compares rental dates
	* @param ri Enterprise object for rentals
	* @return returns -1, 0, 1 for comparisons
	*/
	public int compareTo(Enterprise ri) {
		int result = this.date.compareTo(ri.date);
		return result;
	}
	
	/**
	* prints the total revenue from game tickets
	* @param num game id
	* @param year year the game was played
	* @param tickets ArrayList of tickets
	*/
	public static void gameTotals(int num, String year, ArrayList<Enterprise> tickets) {
		double total=0;
		for(int i=0; i < tickets.size(); i++) {
			if(tickets.get(i).flag == 0) {
				if( (tickets.get(i).gameID == num) && (tickets.get(i).year.equals(year)) ) {
					total = (tickets.get(i).numOfTkts * tickets.get(i).amount) + total;
				}
			}
		}
		System.out.printf("The total collection for game %d in the year %s is: $%f\n", num, year, total);
	}
	/**
	* @param ticketyear
	* @param type
	* @param box
	* @param tickets
	*/
	public void ticketTotals(String ticketYear, String type, int box, ArrayList<Enterprise> tickets) {
		int total=0, yearTotal=0, typeTotal=0, boxTotal=0;
		for(int i=0; i < tickets.size(); i++) {
			total = total + tickets.get(i).numOfTkts;
			
		}
		System.out.printf("The total number of tickets sold up to this point is %d\n", total); 
		for(int i=0; i < tickets.size(); i++) {
			if(ticketYear.equals(tickets.get(i).year)) {
				yearTotal = yearTotal + tickets.get(i).numOfTkts;
					
			}
		}
		System.out.printf("%d tickets were sold for the year %s\n", yearTotal, ticketYear);
		for(int i=0; i < tickets.size(); i++) {
			if(type.equals(tickets.get(i).tktType)) {
				typeTotal = typeTotal + tickets.get(i).numOfTkts;
				
			}
		}
		System.out.printf("%d tickets were sold for %s\n", typeTotal, type);
		for(int i=0; i < tickets.size(); i++) {
			if(box == tickets.get(i).boxID) {
				boxTotal = boxTotal + tickets.get(i).numOfTkts;
			}
		}
		System.out.printf("%d tickets were sold for box id %d\n", boxTotal, box);
	}
	
	/**
	* prints the total revenue from rentals
	* @param rentals ArrayList of rentals objects
	* @param facilities ArrayList of facility objects
	*/
	public static void rentTotals(ArrayList<Enterprise> rentals, ArrayList<Facility> facilities) {
		double total=0;
		for(int i = 0; i < rentals.size(); i++) {
			if(rentals.get(i).flag == 1) {
				for(int x = 0; x < facilities.size(); x++) {
						if(rentals.get(i).facilityName.equals(facilities.get(x).name)) {
							total = (rentals.get(i).hours * facilities.get(x).getRent()) + total;
						}
				}
			}
		}
		System.out.printf("the total rent collection is $%f\n", total);
		
	}
	/**
	* gives total revenue of a facility
	* @return total revenue
	*/
	public static double facilityTotalRevenue(String facName, ArrayList<Enterprise> rentals, ArrayList<Facility> facilities) {
		double total=0;
		for(int i=0; i < facilities.size(); i++) {
			if(facName.equals(facilities.get(i).name)) {
				for(int x=0; x < rentals.size(); x++) {
					if(facName.equals(rentals.get(x).facilityName)) {
						total = (rentals.get(x).hours * facilities.get(i).getRent()) + total;
					}
				}
				
			}
			
		}
		return total;
	}
	/**
	* @param input
	* @param inputYear
	* @param tickets
	* @param rentals
	* @param facilities
	*/
	public static void computeRevenue(String input, String inputYear, ArrayList<Enterprise> tickets, ArrayList<Enterprise> rentals, ArrayList<Facility> facilities) {
		double indR=0, seaR=0, boxR=0, rentalR=0;
		int indT=0, seaT=0, boxT=0;
		if(input.equals("ticket")) {
			for(int i=0; i < tickets.size(); i++) {
				if(inputYear.equals(tickets.get(i).year) && tickets.get(i).tktType.equals("IND")) {
					if(tickets.get(i).boxID != 0) {
						indT = indT + tickets.get(i).numOfTkts;
						indR = (tickets.get(i).numOfTkts * tickets.get(i).amount) + indR;
					}
					else {
						boxT = boxT + tickets.get(i).numOfTkts;
						boxR = (tickets.get(i).numOfTkts * tickets.get(i).amount) + boxR;
					}
				}
				else if (inputYear.equals(tickets.get(i).year) && tickets.get(i).tktType.equals("SEA")) {
						seaT = seaT + tickets.get(i).numOfTkts;
						seaR = (tickets.get(i).numOfTkts * tickets.get(i).amount) + seaR;
				}
			}
		}
		
		else {
			int yearInt = Integer.parseInt(inputYear);
			
			for(int i=0; i < rentals.size(); i++) {
				if(yearInt == rentals.get(i).date.getYear()) {
					for(int x = 0; x < facilities.size(); x++) {
						if(rentals.get(i).facilityName.equals(facilities.get(x).name)) {
							rentalR = (rentals.get(i).hours * facilities.get(x).getRent()) + rentalR;
						}
					}
				}
			}
			System.out.printf("The rental revenue for the year %s is $%1.2f\n", inputYear, rentalR);
		}
	}
	
	/**
	* @param inputYear
	* @param rentals
	* @param facilities
	*/
	public static void facilityReport(int inputYear, ArrayList<Enterprise> rentals, ArrayList<Facility> facilities) {
		double revenue=0;
		String yearStr = String.valueOf(inputYear);
		System.out.printf("%-12s %-2s %-5s\n", "date", "duration", "revenue");
		for(int i=0; i < rentals.size(); i++) {
			if(inputYear == rentals.get(i).date.getYear()) {
				for(int x = 0; x < facilities.size(); x++) {
					if(rentals.get(i).facilityName.equals(facilities.get(x).name)) {
						revenue = (rentals.get(i).hours * facilities.get(x).getRent()) + revenue;
						break;
					}
				}
				
				System.out.printf("%-12s %-2d $%-5.2f ", 
				rentals.get(i).date, rentals.get(i).hours, revenue);
			}
		}
	}

	
	/**
	* checks if employee exists
	* @param id employee id
	* @param employees ArrayList of employee objects
	* @return true if employee exists, false if employee doesn't exist
	*/
	public static boolean employeeExistence(int id, ArrayList<SalariedEmployee> employees) {
		for(int i = 0; i < employees.size(); i++) {
			if(id == employees.get(i).getId()) 
				return true;
		}
		return false;
	}
	
	/**
	* terminates an employee
	* @param id employee id
	* @param aDate date
	* @param employees ArrayList of employee objects
	*/
	public static void terminateEmployee(int id, String aDate, ArrayList<SalariedEmployee> employees) {
		if(aDate.length() == 1) {
			Date todaysDate = new Date();
			for(int i=0; i < employees.size(); i++) {
				if(id == employees.get(i).personId) {
					employees.get(i).setReleaseDate(todaysDate);
				}
			}
		}
		else {
			Date rDate = new Date(aDate);
			for(int i=0; i < employees.size(); i++) {
				if(id == employees.get(i).personId) {
					employees.get(i).setReleaseDate(rDate);
				}
			}
		}
		System.out.printf("%-3s %-10s %-10s %-12s\n", "ID", "First Name", "Last Name", "Release Date");
		for(int i=0; i < employees.size(); i++) {
			System.out.printf("%-3d %-10s %-10s %-12s \n",
			employees.get(i).personId, employees.get(i).fName, employees.get(i).lName, employees.get(i).releaseDate);
		}
	}
	
	/**
	* gets name of enterprise
	* @return returns the name of enterprise
	*/
	public String getName () {
		return name;
	}
	
	/**
	* replaces default toString of object class
	*/
	public String toString() {
		if(flag==0) {
			String formattedString = String.format("Employee ID: %-3d Customer ID: %-3d Ticket: %-4s Game ID: %-3d Year: %-5d # of Ticket: %-4d Amount: %-10.2f Box ID: %-3d\n",
			salesEmpId, custId, tktType, gameID, year, numOfTkts, amount, boxID);
			return formattedString;
		}
		else {
			String formattedString = String.format("Employee ID: %-3d Customer ID: %-3d Facility: %-11s Date: %-12s People: %-6d Hours: %-2d\n",
			salesEmpId, custId, facilityName, date, numOfPeople, hours);
			return formattedString;
		}
	}
}


class GameComparator implements Comparator<Game> {
	/**
	* compares game dates
	* @param game1 compares to game2 date
	* @param game2 compares to game1 date
	* @return returns -1,0, or 1 for comparisons
	*/
	public int compare(Game game1, Game game2) {
		int result = game1.date.compareTo(game2.date);
		return result;
	}
}



public class StadiumMgmtTest implements Proj3Constants, DateConstants {

	// introduce your (class and instance) attributes (if needed) for this test class
    // as i have indicated, it is preferable to have a StadiumMgmt class for the enterprise
    // and use this ONLY as a driver or test class
    // that way, this class can be removed to make your system a library!!

	private static BufferedReader finput;   //for reading from a file
    private static Scanner cp;              //this is still command prompt
    private static PrintWriter foutput;     //for writing to a file
    private static int command20Status = 0;

    //define other variables as needed

    //Note that we are using a DIFFERENT method for reading input file;
	/**
	 * @param iFileName is the input data file name
	 */		

    public static BufferedReader openReadFile(String iFileName){
        BufferedReader bf = null;
        try{
            bf = new BufferedReader(new FileReader(iFileName));
        }     
        catch(FileNotFoundException FNFE){    
          bf = null;
        }
       finally{
          return bf;
       }
    }

/**
	 * @param oFileName is the input data file name
	 */		
    
    public static PrintWriter openWriteFile(String oFileName){
        PrintWriter outputFile = null;
        try{
            outputFile = new PrintWriter(new FileWriter(oFileName));
        }     
        catch(IOException IOE){    
          outputFile = null;
        }
       finally{
          return outputFile;
       }
    }
    
	
	// add here constructors and methods as needed for this Test class

	/**
	 * takes fineNames as command line arguments. prompts if not given
	 *            
	 */
	public static void main(String[] args) {

		// declare variables used for input handling
        String enterprisename = DEFAULT_ENTERPRISE_NAME;
        String inputLine = EMPTY_STRING, ifName = EMPTY_STRING, ofName = EMPTY_STRING;
        ArrayList<SalariedEmployee> employees = new ArrayList<SalariedEmployee> ();
        ArrayList<Facility> facilityList = new ArrayList<Facility> ();
        ArrayList<Game> gameList = new ArrayList<Game> ();
        ArrayList<Person> customerList = new ArrayList<Person> ();
        ArrayList<Enterprise> tickets = new ArrayList<Enterprise> ();
		ArrayList<Enterprise> facilities = new ArrayList<Enterprise> ();
		List <Game> list = new ArrayList<Game> ();
		List <Enterprise> rentalList = new ArrayList<Enterprise>();
		Map<String, ArrayList<Enterprise>> rentalsHashMap = new HashMap<String, ArrayList<Enterprise>>();
		// determine if input file is provided

		cp = new Scanner(System.in);
		if (args.length < 1) {
			System.out.println("Input Data file name was not supplied");
			System.out.printf("Please type input data file name: ");
			ifName = cp.nextLine();
		} 
        else if (args.length < 2){
            ifName =  args[ZEROI];
            System.out.println("Output file name was not supplied");       
            System.out.printf("Please type output file name: ");
            ofName = cp.nextLine();
        } 
        else {
        	ifName = args[ZEROI];
            ofName = args[ONEI];
        }

		// See HOW RECOVERY is done (will cover in a few weeks)

		finput = openReadFile(ifName);
		while (finput == null) {
			System.out.printf("Error: input data FILE %s %s", ifName,
					" does not exist.\nEnter correct INPUT data file name: ");
			ifName = cp.nextLine();
			finput = openReadFile(ifName);
		}
        foutput = openWriteFile(ofName);
        while (foutput == null){
			System.out.printf("Error: OUTPUT FILE %s %s",  ofName,  
                        " does not exist.\nEnter correct OUTPUT FILE name: ");
            ofName = cp.nextLine();
            foutput = openWriteFile(ofName);
		}  

        System.out.printf("Input data File: %s\nOutput File: %s\n\n", ifName, ofName);
        foutput.printf("Input data File: %s\nOutput File: %s\n\n", ifName, ofName); 
		

		/* GET Stadium DETAILS */
		// start reading from data file
		// get name
		try {
			inputLine = finput.readLine();
			while (inputLine.charAt(BASE_INDEX) == '/')
				inputLine = finput.readLine();
			String enterpriseName = inputLine;
			System.out.printf("\n%s %s \n", "Enterprise name is: ",
					enterpriseName);

			// add code: you need to store enterprise name in the enterprise aobject!
			Enterprise enterpriseObj = new Enterprise(enterpriseName);
			
			/* GET EMPLOYEE DETAILS */

			// reading details for each employee from the data file
			System.out.printf("\nReading Employees: \n");

			int numEmployees = 0;
			inputLine = finput.readLine();
            foutput.println(inputLine); //writing to the output file!
			while (inputLine.charAt(BASE_INDEX) == '/'){
				inputLine = finput.readLine();
                foutput.println(inputLine);
            }
			while ( (!inputLine.toLowerCase().equals("end"))){	
                String[] chopEmp = inputLine.split("!");

				// fill all fields for a single employee from a single line
				String empType = chopEmp[ZEROI].toUpperCase();
				String empFName = chopEmp[ONEI];
				String empLName = chopEmp[TWOI];
				String empBDate = chopEmp[THREEI];
				String empGender = chopEmp[FOURI].toLowerCase();
				String empHireDate = chopEmp[FIVEI];
                String empReleaseDate = chopEmp[SIXI];
				double empBaseSalary = Double.parseDouble(chopEmp[SEVENI]);
                double empHourlyrate = Double.parseDouble(chopEmp[EIGHTI]);

				// add code here: in order to convert a date string to a Date object,
				// invoke the appropriate constructor of the Date class (shown below)
				Date dob = new Date(empBDate); // dob is a local variable
				Date hDate = new Date(empHireDate); 

				System.out.printf("(%6s, %10s, %6s, %12s, %12s,  %10.2f, %4s, %12s)\n",
						empFName, empLName, empGender, empHireDate,
						empReleaseDate, empBaseSalary, empType, dob);

				// also, empType is read as a string; if you are using a
				// enum, you need to convert it  using a switch 
                //or if statement or enum valueOf() method

                // add code here: based on the empType appripriate class objects 
                // of the inheritance hierarchy need to be created
                //make sure you set the release date to null (will be used later)
                //add this employee to an array or array list
                
                if(empType.equals("WD")) {	
                	if(!empReleaseDate.equals("null")) {
                		Date rDate = new Date(empReleaseDate);
                		SalariedEmployee emp = new WebDesigner(empFName, empLName, dob, empGender, hDate, rDate, empBaseSalary, empHourlyrate);
                		employees.add(emp);
                	}
                	else {
                		SalariedEmployee emp = new WebDesigner(empFName, empLName, dob, empGender, hDate, null, empBaseSalary, empHourlyrate);
                		employees.add(emp);
                	}
                }
                else if(empType.equals("MNTR")) {
                	if(!empReleaseDate.equals("null")) {
                		Date rDate = new Date(empReleaseDate);
                		SalariedEmployee emp = new Maintainer(empFName, empLName, dob, empGender, hDate, rDate, empBaseSalary, empHourlyrate);
                		employees.add(emp);
                	}
                	else {
                		SalariedEmployee emp = new Maintainer(empFName, empLName, dob, empGender, hDate, null, empBaseSalary, empHourlyrate);
                		employees.add(emp);
                	}
                }
                else if(empType.equals("SEC")) {
                	if(!empReleaseDate.equals("null")) {
                		Date rDate = new Date(empReleaseDate);
                		SalariedEmployee emp = new Security(empFName, empLName, dob, empGender, hDate, rDate, empBaseSalary, empHourlyrate);
                		employees.add(emp);
                	}
                	else {
                		SalariedEmployee emp = new Security(empFName, empLName, dob, empGender, hDate, null, empBaseSalary, empHourlyrate);
                		employees.add(emp);
                	}
                }
                else if(empType.equals("SALES")) {
                	if(!empReleaseDate.equals("null")) {
                		Date rDate = new Date(empReleaseDate);
                		SalariedEmployee emp = new SalesExecutive(empFName, empLName, dob, empGender, hDate, rDate, empBaseSalary, empHourlyrate);
                		employees.add(emp);
                	}
                	else {
                		SalariedEmployee emp = new SalesExecutive(empFName, empLName, dob, empGender, hDate, null, empBaseSalary, empHourlyrate);
                		employees.add(emp);
                	}
                }
                
                inputLine = finput.readLine();
                foutput.println(inputLine);
				while (inputLine.charAt(BASE_INDEX) == '/'){
					inputLine = finput.readLine();
                    foutput.println(inputLine);	
                }
                numEmployees +=1;
			}
            System.out.printf("#Employees: %d\n", numEmployees);

			/* GET FACILITY DETAILS */

            System.out.printf("\nReading Facilities: \n");
            int numFacilities = 0;
            inputLine = finput.readLine();
            foutput.println(inputLine);
            while (inputLine.charAt(BASE_INDEX) == '/'){
                inputLine = finput.readLine();
                foutput.println(inputLine);
            }
            while ( (!inputLine.toLowerCase().equals("end"))){            
                String[] chopPLots = inputLine.split("!");
            
                // get fields of a facility from one line of input
			
                int  facilityId = Integer.parseInt(chopPLots[ZEROI]);
                String  facilityType = chopPLots[ONEI];
                String  facilityName = chopPLots[TWOI];
                int facilityMaxCapacity = Integer.parseInt(chopPLots[THREEI]);
                int facilityLocation = Integer.parseInt(chopPLots[FOURI]);
                String rentalType = chopPLots[FIVEI];
                double facilityRent = Double.parseDouble(chopPLots[SIXI]);

                System.out.printf("[%d, %s, %s, %d, %d, %s,  %f]\n",facilityId,facilityType,
                    facilityName,facilityMaxCapacity, facilityLocation,
                    rentalType,facilityRent);
                
                // add code here to create facility object 
                // convert strings to enum as needed
                Facility facility = new Facility(facilityId, facilityType, facilityName, facilityMaxCapacity, facilityLocation, rentalType, facilityRent);
                facilityList.add(facility);
                
                inputLine = finput.readLine();
                foutput.println(inputLine);
				while (inputLine.charAt(BASE_INDEX) == '/'){
					inputLine = finput.readLine();
                    foutput.println(inputLine);	
                }
                numFacilities +=1;
             }
            System.out.printf("#Facilities: %d\n", numFacilities);
            
            /* GET GAME DETAILS */
            System.out.printf("\nReading GAMES: \n");
            int numGames = 0;
            inputLine = finput.readLine();
            foutput.println(inputLine);
            while (inputLine.charAt(BASE_INDEX) == '/'){
                inputLine = finput.readLine();
                foutput.println(inputLine);
            }
            while ( (!inputLine.toLowerCase().equals("end"))){    
                String[] chopPLots = inputLine.split("!");
                            
                //get details of games from each line of input
                int gameNumber = Integer.parseInt(chopPLots[ZEROI]);
                String gameDate = chopPLots[ONEI];
                String gameTime = chopPLots[TWOI];
                String visitingTeam = chopPLots[THREEI];
                int homeScore = Integer.parseInt(chopPLots[FOURI]);
                int awayScore = Integer.parseInt(chopPLots[FIVEI]);
                Date dog = new Date(gameDate);
                Time tog = new Time(gameTime);
                System.out.printf("[%s,  %d,  %s,  %s, %d, %d]\n", dog, gameNumber,
                    tog, visitingTeam,homeScore, awayScore);
                //create object and store
                Game newGame = new Game(gameNumber, dog, tog, visitingTeam, homeScore, awayScore);
                gameList.add(newGame);
                list.add(newGame);
                
                inputLine = finput.readLine();
                foutput.println(inputLine);
				while (inputLine.charAt(BASE_INDEX) == '/'){
					inputLine = finput.readLine();
                    foutput.println(inputLine);	
                }
                numGames +=1;
            }                      
             System.out.printf("#Games: %d\n", numGames);
                        
            /* GET CUSTOMER/USER DETAILS */

            // reading details for each user from the data file
            // note that numUsers is computed, not input!
                        
            System.out.printf("\nRead Customer info: \n");
			
            int numUsers =0;
            inputLine = finput.readLine();
            while (inputLine.charAt(BASE_INDEX) == '/'){
                inputLine = finput.readLine();
                foutput.println(inputLine);
            }
            while ( (!inputLine.toLowerCase().equals("end"))){
              System.out.println(inputLine);
              String[] chopUser = inputLine.split("!");
              // fill all fields for a single user/customer from a single line
              int userId = Integer.parseInt(chopUser[ZEROI]);
              String userFName = chopUser[ONEI];
              String userLName = chopUser[TWOI];
              String userDob = chopUser[THREEI];
              String userAddress = chopUser[FOURI];
              String userState = chopUser[FIVEI].toUpperCase();
              String userZipcode = chopUser[SIXI];
              String userType = chopUser[SEVENI].toUpperCase();
              Date dobUser = new Date(userDob);
              System.out.printf("{%d, %10s, %10s, %10s, %20s, %6s, %6s, %10s} \n",
                    userId,userFName, userLName,dobUser, userAddress, userState, 
                    userZipcode, userType);

            // create a user object as appropriate
            Person newCustomer = new Customer(userId, userFName, userLName, dobUser, userAddress, userState, userZipcode, userType);
            customerList.add(newCustomer);

            inputLine = finput.readLine();
            while (inputLine.charAt(BASE_INDEX) == '/'){
				inputLine = finput.readLine();
                foutput.println(inputLine);
            }
                numUsers += 1;
		  }
          System.out.printf("#users/customers: %d\n", numUsers);
          
          // you will be adding MOST of your menu processing code here
          // add code for processing menu and its output
          // DO NOT REMOVE THE CODE but add to it
          //remove print stmts once you have made sure input processing is correct!
            
            System.out.printf("\nStarting Menu Processing: \n\n");
            foutput.println("\nStarting Menu Processing: \n\n");
          
          //read each line as before and process according to the menu number
            inputLine = finput.readLine();
            System.out.println(inputLine);
            foutput.println(inputLine);
			while (inputLine.charAt(BASE_INDEX) == '/'){
				inputLine = finput.readLine();
                System.out.println(inputLine);
                foutput.println(inputLine);
            }
            System.out.println("10) Show the menu of project 2");
            System.out.println("11) Accept ticket sales info");
            System.out.println("12) Accept rental info");
            System.out.println("13) Hire a new employee");
            System.out.println("14) Terminate an employee");
            System.out.println("15) Compute monthly salary of an employee");
            System.out.println("16) Compute length of service");
            System.out.println("17) Revenue computation");
            System.out.println("18) Facility report");
            System.out.println("19) Sort Game");
            System.out.println("0) Exit program");
            
            foutput.println("10) Show the menu of project 2");
            foutput.println("11) Accept ticket sales info");
            foutput.println("12) Accept rental info");
            foutput.println("13) Hire a new employee");
            foutput.println("14) Terminate an employee");
            foutput.println("15) Compute monthly salary of an employee");
            foutput.println("16) Compute length of service");
            foutput.println("17) Revenue computation");
            foutput.println("18) Facility report");
            System.out.println("19) Sort Game");
            foutput.println("0) Exit program");

            while ( (!inputLine.toLowerCase().equals("end"))){
				String[] chopMenuLine = inputLine.split("!");

            switch (Integer.parseInt(chopMenuLine[ZEROI])){
            
            case 10: //for processing project 2 commands
                System.out.println("1) List all employees");
            	System.out.println("2) List all facilities");
            	System.out.println("3) List all games");
            	System.out.println("4) List all customers");
            	System.out.println("5) Display total collection for a game");
            	System.out.println("6) Display total collection from rentals");
            	System.out.println("7) Release an employee");
            	System.out.println("0) Exit program");
                break;
            case 11: // process ticket information for games
                   
				    String[] chopTicketingInfo = inputLine.split("!");

                    // fill all fields for a single ticket from a single line
                    // c-id!type of tkt (individual, season)!game id (0 if season tkt)!
                    // year!#tkts!amount!box id/0 for regular       
                            
                    int salesEmpId = Integer.parseInt(chopTicketingInfo[ONEI]);
                    int custId = Integer.parseInt(chopTicketingInfo[TWOI]);
                    String ticketType = chopTicketingInfo[THREEI];
                    int gameId = Integer.parseInt(chopTicketingInfo[FOURI]);
                    String gameTicketYear = chopTicketingInfo[FIVEI];
                    int noOfTickets = Integer.parseInt(chopTicketingInfo[SIXI]);
                    double ticketAmount = Double.parseDouble(chopTicketingInfo[SEVENI]);
                    int ticketBoxId = Integer.parseInt(chopTicketingInfo[EIGHTI]);
                				
                    System.out.printf("{%d, %d, %s, %d, %4s, %d, %f, %d} \n",
						salesEmpId, custId, ticketType, gameId, gameTicketYear, noOfTickets, 
                        ticketAmount, ticketBoxId);
                    
                    Enterprise ticketInfo = new Enterprise(salesEmpId, custId, ticketType, gameId, gameTicketYear, noOfTickets, ticketAmount, ticketBoxId);
                    tickets.add(ticketInfo);
                    
                    ticketInfo.ticketTotals(gameTicketYear, ticketType, ticketBoxId, tickets);
                    
                    break;
            case 12:// process facility rental info
                
				    String[] chopRentalInfo = inputLine.split("!");

                    // fill all fields for a single rental from a single line
                    // Customer id!Facility type!Date! #people using the facility (0 if stall)!days/hours (int) depending on the facility      
                            
                    int salesEmpID = Integer.parseInt(chopRentalInfo[ONEI]);
                    int custID = Integer.parseInt(chopRentalInfo[TWOI]);
                    String facilityType = chopRentalInfo[THREEI].toLowerCase();
                    String rentalDateString = chopRentalInfo[FOURI];
                    int peopleUsingFacility = Integer.parseInt(chopRentalInfo[FIVEI]);
                    int durationOfRent = Integer.parseInt(chopRentalInfo[SIXI]);
                    Date rentalDate = new Date(rentalDateString);
                                            				
                    System.out.printf("{%5d, %4d, %12s %12s, %d, %d} \n",
						salesEmpID, custID, facilityType, rentalDate, 
                        peopleUsingFacility, durationOfRent);
                        
                    Enterprise facilityInfo = new Enterprise(salesEmpID, custID, facilityType, rentalDate, peopleUsingFacility, durationOfRent);
                    facilities.add(facilityInfo);
                    rentalList.add(facilityInfo);
                    for(int i=0; i < facilities.size(); i++) {
                    	System.out.println(facilities.get(i));
                    }
                    
                     break;
            case 13://process hiring a new employee
            	String[] chopHiringInfo = inputLine.split("!");
            	
            	String empType = chopHiringInfo[ONEI].toUpperCase();
            	String fName = chopHiringInfo[TWOI];
            	String lName = chopHiringInfo[THREEI];
            	String dobStr = chopHiringInfo[FOURI];
            	Date dob = new Date(dobStr);
            	String gender = chopHiringInfo[FIVEI].toLowerCase();
            	String hireDateStr = chopHiringInfo[SIXI];
            	Date hireDate = new Date(hireDateStr);
            	double salary = Double.parseDouble(chopHiringInfo[SEVENI]);
            	double rate = Double.parseDouble(chopHiringInfo[EIGHTI]);
            	Date rDate = new Date();
            	
                if(empType.equals("WD")) {
                	SalariedEmployee newEmp = new WebDesigner(fName, lName, dob, gender, hireDate, null, salary, rate);
                	employees.add(newEmp);
                	System.out.println(newEmp);
                }
                else if(empType.equals("MNTR")) {
                	SalariedEmployee newEmp = new Maintainer(fName, lName, dob, gender, hireDate, null, salary, rate);
                	employees.add(newEmp);
                	System.out.println(newEmp);
                }
                else if(empType.equals("SEC")) {
                	SalariedEmployee newEmp = new Security(fName, lName, dob, gender, hireDate, null, salary, rate);
                	employees.add(newEmp);
                	System.out.println(newEmp);
                }
                else if(empType.equals("SALES")) {
                	SalariedEmployee newEmp = new SalesExecutive(fName, lName, dob, gender, hireDate, null, salary, rate);
                	employees.add(newEmp);
                	System.out.println(newEmp);
                }
                
                
            	
                    break;
            case 14://terminate an employee
            	String[] chopEmployee = inputLine.split("!");
            	int empId = Integer.parseInt(chopEmployee[ONEI]);
            	String aDate = chopEmployee[TWOI];
            	
            	Enterprise.terminateEmployee(empId, aDate, employees);
            	
                break;
            case 15://compute monthly salary
            	String[] chopSalary = inputLine.split("!");
            	int id = Integer.parseInt(chopSalary[ONEI]);
            	int param = Integer.parseInt(chopSalary[TWOI]);
            	int month = Integer.parseInt(chopSalary[THREEI]);
            	
            	double sal;
            	for(int i=0; i < employees.size(); i++) {
            		if(id == employees.get(i).personId) {
            			sal = employees.get(i).computeSalary(param);
            			System.out.printf("%-3d %-16s %-11s %-11s %-6s %-3d %-7.2f\n",
            			id, employees.get(i).type, employees.get(i).fName, employees.get(i).lName, employees.get(i).gender, month, sal);  
            		}
            	}
                    break;
            case 16://compute length of service
            	String[] chopServiceLength = inputLine.split("!");
				int iD = Integer.parseInt(chopServiceLength[ONEI]);
				
				if(iD == 0) {
					for(int i=0; i < employees.size(); i++) {
						System.out.println(employees.get(i));
					}
				}
				else {
					for(int i=0; i < employees.size(); i++) {
						if(iD == employees.get(i).personId) {
							System.out.println(employees.get(i));
						}
					}
				}
                break;
                    
            case 17:// compute ticket revenue
            	String[] chopTktRevenue = inputLine.split("!");
				String input = chopTktRevenue[ONEI].toLowerCase();
				String year = chopTktRevenue[TWOI];
				
				Enterprise.computeRevenue(input, year, tickets, facilities, facilityList);
				
                break;
            case 18: //generate facility report
            	String[] chopYear = inputLine.split("!");
            	int inputYear = Integer.parseInt(chopYear[ONEI]);
            	String yearStr = String.valueOf(inputYear);
            	
            	Enterprise.facilityReport(inputYear, facilities, facilityList);
            	Enterprise.computeRevenue("rental", yearStr, tickets, facilities, facilityList);
            	
                break;
            case 19: //prints sorted games by date
            	Collections.sort(list, Collections.reverseOrder(new GameComparator()));
            	System.out.printf("%s", list);
            	
            	break;
            case 20:
            	int i, flag=0;
            	String[] chopFacName = inputLine.split("!");
            	String name = (chopFacName[ONEI]).trim();
            	if((name.equals("*")) == false){
            		name.toLowerCase();
            		flag=1;
            	}
            	
            	if(command20Status == 0) {
            		command20Status = 1;
            		for(i=0; i < facilities.size(); i++) {
            			if((rentalsHashMap.isEmpty())==false && rentalsHashMap.containsKey(facilities.get(i).facilityName)) {
            				ArrayList<Enterprise> temp = new ArrayList<Enterprise>();
            				temp = rentalsHashMap.get(facilities.get(i).facilityName);
            				temp.add(facilities.get(i));
            				rentalsHashMap.put(facilities.get(i).facilityName, temp);
            			}
            			else {
            				ArrayList<Enterprise> fac = new ArrayList<Enterprise>();
            				fac.add(facilities.get(i));
            				rentalsHashMap.put(facilities.get(i).facilityName, fac);
            			}
            		}
            	}
            	if(flag==1) {
            		if(rentalsHashMap.containsKey(name)) {
            			ArrayList<Enterprise> temp1 = new ArrayList<Enterprise>();
            			temp1 = rentalsHashMap.get(name);
            			Collections.sort(temp1);
            			for(i=0; i < temp1.size(); i++) {
            				System.out.printf("%s", temp1.get(i));
            			}
            		}
            		else
            			System.out.printf("%s does not exist.\n", name);
            	}
            	else{
            		for(ArrayList<Enterprise> r : rentalsHashMap.values()) {
            			for(i=0; i < r.size(); i++) {
            				Collections.sort(r);
            				System.out.printf("%s", r.get(i));
            			}
            		}
            	}
				
            	break;
            case 0: //process exit
                break;
            default: System.out.printf("unknown command: %s: SKIPPED\n", inputLine);
                    foutput.printf("unknown command: %s: SKIPPED\n", inputLine);
                    break;       
                     
            }	
            inputLine = finput.readLine();
            System.out.println(inputLine);
            foutput.println(inputLine);
			while (inputLine.charAt(BASE_INDEX) == '/'){
				inputLine = finput.readLine();
                System.out.println(inputLine);
                foutput.println(inputLine);
            }
        } 
         System.out.printf("Finished processing all commands. bye!"); 
         foutput.printf("Finished processing all commands. bye!");      
        		// DO NOT REMOVE or DISTURB the REST OF THE CODE	

	    } //try
        catch(NullPointerException NPE){
            System.out.println("null pointer exception: " + "\nPlease correct " + NPE.getMessage());
          } 
        catch (NumberFormatException NFE) {
			System.out.println("I/O Error in File: " + ifName + "\ncheck for: "
					+ NFE.getMessage() + " and correct it in: " + inputLine);
		  } 
        catch (RuntimeException RE) {
			System.out.println("Invalid Data error in File: " + ifName
					+ "\nPlease correct " + RE.getMessage() + " in the file!" + inputLine);
		  }
        catch(IOException IOE){
            System.out.println("input/output Data error in File: " + ifName + "\nPlease correct " + IOE.getMessage() + " in the file!" + inputLine);
          } 
        
        finally {
		  foutput.close();
		  }
		/*TabbedMenu menu = new TabbedMenu(employees, facilityList, gameList, customerList, facilities);
		menu.setSize( 800, 600 );
		menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menu.setVisible(true);*/
		

	}//main
} //class