public class SalariedEmployee extends Employee{
    // Instance variable
    double salary;

    //constructor
    public SalariedEmployee(){};

    public SalariedEmployee(int empId, String name, double salary)
    {
        this.setEmpId(empId);
        this.setName(name);
        this.setSalary(salary);
    };

    // getter
    public double getSalary() {
        return salary;
    }

    //setter
    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return String.format("  Employee id = %d\n  Name = %s\n  Salary = %.2f" +
                "\n  Monthly pay = %.2f\n",this.getEmpId(), this.getName()
                , salary , this.monthlyPayment());
    }

    /**
     * Description:
     *  Returns the monthly salary
     * Inputs:
     *  None
     * Outputs:
     *  monthly payment : double
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
