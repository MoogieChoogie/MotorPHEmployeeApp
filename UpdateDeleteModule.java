import java.util.ArrayList;
import java.util.HashMap;

public class UpdateDeleteModule {

    public static HashMap<String, String[]> createEmployeeMap(ArrayList<String[]> employees) {
        HashMap<String, String[]> employeeMap = new HashMap<String, String[]>();

        for (String[] employee : employees) {
            employeeMap.put(employee[0], employee);
        }

        return employeeMap;
    }

    public static boolean updateRecord(ArrayList<String[]> employees, String employeeId,
            String name, String department, String position,
            String ratePerDay, String daysWorked, String deductions) {

        for (String[] employee : employees) {
            if (employee[0].equals(employeeId)) {
                employee[1] = name;
                employee[2] = department;
                employee[3] = position;
                employee[4] = ratePerDay;
                employee[5] = daysWorked;
                employee[6] = deductions;

                // Reset computed values because employee salary details changed.
                employee[7] = "0";
                employee[8] = "0";

                return true;
            }
        }

        return false;
    }

    public static boolean deleteRecord(ArrayList<String[]> employees, String employeeId) {
        for (int i = 0; i < employees.size(); i++) {
            String[] employee = employees.get(i);

            if (employee[0].equals(employeeId)) {
                employees.remove(i);
                return true;
            }
        }

        return false;
    }
}