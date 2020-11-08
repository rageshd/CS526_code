import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Scanner;

public class Hw1_P2 {
    // Array of salaried employees
    static SalariedEmployee[] employeeArray = new SalariedEmployee[10];

    /**
     * Main method
     * @param args
     */
    public static void main(String[] args) {
        // The complete input file path is the passed as the argument
        if (args.length!=1) {
            System.err.println("Error : Incorrect number of arguments" +
                    ", please provide the complete path of input file");
            System.exit(1);
        }
        String inputFile = args[0];

        // Read the employee_input.txt which is at the same folder as this code
        readFilePopulateArray(inputFile);

        // Test for employees earning more than $70000
        employeesAbove(employeeArray,70000);

        // Second test to test for employees earning more than $0
        employeesAbove(employeeArray,0);

        // Second test to test for employees earning more than $1,000,000
        employeesAbove(employeeArray,1000000);
    }


    /**
     * Reads a file and populates the SalariedEmployee array
     * @param fileName : The file name to read
     */
    public static void readFilePopulateArray(String fileName){
        File file = new File(fileName);

        // Try reading  the file and doing some actions
        try {
            Scanner scanner = new Scanner(file);
            int index = 0;
            while (scanner.hasNextLine()){
                // Split the line to a array
                String[] currentLine = scanner.nextLine().split(",");
                // If the result arry has 3 elements then create a salaried employee object and add to array
                if (currentLine.length==3){
                    int empId = Integer.parseInt(currentLine[0]);
                    String name = currentLine[1].trim();
                    double salary = Double.parseDouble(currentLine[2]);
                    SalariedEmployee salariedEmployee = new SalariedEmployee(empId, name, salary);
                    employeeArray[index] = salariedEmployee;
                    index++;
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error : File not found!!");
        }

    }


    /**
     * Selects from empArray only those employees who earn more than the given threshold amount
     * and displays their empId, name, salary, and monthly payment
     * @param empArray : The Salaried Employee array
     * @param threshold : The salary threshold
     */
    public static void employeesAbove (SalariedEmployee[ ] empArray, double threshold)
    {
        // loop through the input array
        System.out.printf("Employees earning more than $%d:\n\n",(long)threshold);
        for (SalariedEmployee salEmp : employeeArray){
            // Print if the salary is greater than threshold
            if (salEmp.getSalary() > threshold){
                salEmp.employeeInfo();
            }
        }
        System.out.println("=============== End of output ====================\n");
    }
}
