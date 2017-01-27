/*****************************************************************************
 @author Sharma Chakravarthy
 LANGUAGE   : Java version 6
 OS         : Windows win 7 Ultimate
 PLATFORM   : PC
 Compiler   : javac
 
 CONCEPTS   : classes and methods
 PURPOSE    : defines an interface
******************************************************************************/

public interface Employee {
	public double computeSalary(int salaryParameter); //this cannot be static
    public int    lengthOfService();
}
