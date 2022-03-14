import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.ListIterator;

public class ManAllEmpsServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        request.getRequestDispatcher("managerAllEmployees.html").include(request, response);
        Configuration config = new Configuration();
        config.configure("hibernate.cfg.xml");
        SessionFactory factory = config.buildSessionFactory();
        Session session = factory.openSession();
        List<Employee> list = session.createQuery("from Employee", Employee.class).list();
        Transaction transaction = session.beginTransaction();
        for (Employee e : list) {
            pw.println("<form>\n<tbody>\n" +
                    "        <td>" + e.getId() + "</td>\n" +
                    "        <td>" + e.getName() + "</td>\n" +
                    "        <td>" + e.getUsername() + "</td>\n" +
                    "        <td>" + e.getPassword() + "</td>\n" +
                    "        <td>" + e.getEmail() + "</td>\n" +
                    "        <td>" + e.getTitle() + "</td>\n" +
                    "        </tbody>\n</form>\n");
        }
        pw.println("</table>\n" +
                "</div>\n");

    }
}
