import java.sql.SQLException;
import java.util.List;

interface EmployeeDao {
    void addEmployee(Employee employee) throws SQLException;
    void updateEmployee(Employee employee);
    void deleteEmployee(Employee employee);
    List<Employee> getEmployees();
    Employee getEmployeeById(int id);
    Employee getEmployeeLogin(String username) throws SQLException;
    List<Employee> getEmployee(String username, String password) throws SQLException;

}