# MotorPH Employee App

The **MotorPH Employee App** is a Java Swing desktop application for managing employee records and computing payroll information. It was developed as the final project for **MO-IT103 - Computer Programming 2**.

## Features

The final application integrates all five required features:

1. **Java Swing GUI**
   - Clear employee-entry form
   - Non-editable employee table
   - User-friendly success, error, and confirmation dialogs

2. **Employee Record Management**
   - Add employee records
   - Display records in a `JTable`
   - Prevent duplicate employee numbers
   - Save records to `Employees.csv`

3. **Salary Computation**
   - Compute Gross Pay
   - Apply Deductions
   - Compute Net Pay
   - Display company totals and average Net Pay

4. **Update and Delete**
   - Select employee records from the table
   - Update existing employee information
   - Delete records with confirmation
   - Save all changes to the CSV file

5. **Payroll Summary**
   - Display the total number of employees
   - Display total Gross Pay
   - Display total Deductions
   - Display average Net Pay

## Payroll Formulas

```text
Gross Pay = Rate x Hours/Days Worked
Net Pay = Gross Pay - Deductions
Average Net Pay = Total Net Pay / Total Employees
```

## CSV Structure

Employee records are stored in `Employees.csv` using nine columns:

```csv
Employee Number,Name,Department,Position,Rate,Hours/Days Worked,Deductions,Gross Pay,Net Pay
```

The application loads the CSV when it starts and saves changes after adding, updating, deleting, or computing employee records.

## Project Files

```text
Main.java
MotorPHEmployeeAppGUI.java
EmployeeDataHandler.java
SalaryComputationModule.java
UpdateDeleteModule.java
Employees.csv
Employee.java
EmployeeApp.java
AttendanceRecord.java
Payroll.java
Deduction.java
ClassDiagram.jpg
README.md
```

## Requirements

- Java Development Kit (JDK) 8 or later
- VS Code, NetBeans, IntelliJ IDEA, or another Java-compatible IDE

## How to Run in VS Code

1. Download or clone the repository.
2. Open the project folder in VS Code.
3. Confirm that `Employees.csv` is in the same folder as the Java source files.
4. Open `Main.java`.
5. Click **Run** above the `main` method.

The application window should open and load the employee records from the CSV file.

## Command-Line Instructions

Compile all Java files:

```bash
javac *.java
```

Run the application:

```bash
java Main
```

## Validation and Data Handling

The application includes validation for:

- Missing required fields
- Invalid numeric input
- Employee Numbers, Rates, and Hours/Days Worked that are not greater than zero
- Negative deductions
- Duplicate employee numbers
- Empty employee lists during salary and summary generation

Employee Number is locked while editing a selected record. Gross Pay and Net Pay are reset only when Rate, Hours/Days Worked, or Deductions change, allowing the salary to be recomputed accurately.

## Testing Completed

The application was tested for:

- Adding, updating, and deleting employees
- Duplicate employee prevention
- Missing and invalid input handling
- Salary calculation accuracy
- Payroll summary accuracy
- Clear Fields behavior
- Confirmation dialogs
- CSV persistence after restarting
- Preservation of computed salaries after restarting

## Known Limitation

The current beginner-level CSV reader uses commas as separators. Avoid entering commas inside names, departments, or positions because they may be interpreted as additional CSV columns.

## Developer

**Christian Gabriel Y. Narte**  
Mapua Malayan Digital College
