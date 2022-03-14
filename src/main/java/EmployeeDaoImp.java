import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.sql.SQLException;
import java.util.List;

public class EmployeeDaoImp implements EmployeeDao {
    private Session currentSession;
    private Transaction currentTransaction;
    public EmployeeDaoImp(){

    }
    @Override
    public void addEmployee(Employee employee) throws SQLException {

    }

    @Override
    public void updateEmployee(Employee employee) {

    }

    @Override
    public void deleteEmployee(Employee employee) {

    }

    @Override
    public List<Employee> getEmployees() {
        return null;
    }

    @Override
    public Employee getEmployeeById(int id) {
        return null;
    }

    @Override
    public Employee getEmployeeLogin(String username) throws SQLException {
        Configuration config = new Configuration();
        config.configure("hibernate.cf.xml");
        SessionFactory factory = config.buildSessionFactory();
        Session session = factory.openSession();
        Employee employee = session.createQuery("from Employee where username='"+username+"'", Employee.class).getSingleResult();
        return employee;
    }

    @Override
    public List<Employee> getEmployee(String username, String password) throws SQLException {
        return null;
    }
}