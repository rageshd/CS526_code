public class SalariedEmployee extends Employee{
    // Instance variable
    double salary;

    /**
     * Default constructor
     */
    public SalariedEmployee(){};

    /**
     * Parameterized constructor
     * @param empId : The employee Id
     * @param name : The name
     * @param salary : The salary
     */
    public SalariedEmployee(int empId, String name, double salary)
    {
        this.setEmpId(empId);
        this.setName(name);
        this.setSalary(salary);
    };

    /**
     * Getter
     * @return double : This returns the salary
     */
    public double getSalary() {
        return salary;
    }

    /**
     * Setter
     * @param salary : The salary
     */
    public void setSalary(double salary) {
        this.salary = salary;
    }

    /**
     * Convert the class instance to a string
     * @return String : Formatted instance of the class
     */
    @Override
    public String toString() {
        return String.format("  Employee id = %d\n  Name = %s\n  Salary = %.2f" +
                "\n  Monthly pay = %.2f\n",this.getEmpId(), this.getName()
                , salary , this.monthlyPayment());
    }

    /**
     * Returns the monthly salary
     * @return double : Monthly pay
     */
    public double monthlyPayment(){
        double monthlyPay = salary/12;
        return monthlyPay;
    }

    /**
     * This method prints employee info
     */
    public void employeeInfo(){
        System.out.println(this);
    }
}
