package course2.lessson_2_4.servise;

import course2.lessson_2_4.Employee;
import course2.lessson_2_4.exception.EmployeeIsNotFoundException;
import course2.lessson_2_4.exception.EmployeeListOverflowException;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final Employee[] employees = new Employee[10];
    private int size;

    public void addEmployee(String firstName, String lastName) {
        if (size == employees.length) {
            throw new EmployeeListOverflowException();
        }
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] == null) {
                employees[i] = new Employee(firstName, lastName);
                size++;
                return;
            }
        }
    }

    @Override
    public void removeEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] != null && employees[i].equals(employee)) {
                employees[i] = null;
                size--;
                return;
            }
        }
        throw new EmployeeIsNotFoundException();
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] != null && employees[i].equals(employee)) {
                return employee;
            }
        }
        throw new EmployeeIsNotFoundException();
    }
}
