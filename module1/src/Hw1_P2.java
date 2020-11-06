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
        readFilePopulateArray("employee_input.txt");
        System.out.println("Employees earning more than $70000:\n");
        employeesAbove(employeeArray,70000);

        System.out.println("Employees earning more than $10000000:");
        employeesAbove(employeeArray,10000000);
    }

    /**
     * Description:
     *  Reads a file and populates the SalariedEmployee array
     * Inputs:
     *  fileName : String
     * Outputs:
     *  None
     */
    public static void readFilePopulateArray(String fileName){
        URL path = Hw1_P2.class.getResource(fileName);
        File file = new File(path.getFile());

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
     * Description:
     *  Selects from empArray only those employees who earn more than the given threshold amount
     *  , and displays their empId, name, salary, and monthly payment
     * Inputs:
     *  empArray: An array of SalariedEmployee objects
     *  threshold: A salary threshold
     * Outputs:
     *  None
     */
    public static void employeesAbove (SalariedEmployee[ ] empArray, double threshold)
    {
        // loop through the input array
        for (SalariedEmployee salEmp : employeeArray){
            // Print if the salary is greater than threshold
            if (salEmp.getSalary() > threshold){
                System.out.println(salEmp);
            }
        }
    }
}
