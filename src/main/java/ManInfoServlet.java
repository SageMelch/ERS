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
import java.util.List;

public class ManInfoServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        request.getRequestDispatcher("managerInfo.html").include(request, response);
        Configuration config = new Configuration();
        config.configure("hibernate.cfg.xml");
        SessionFactory factory = config.buildSessionFactory();
        Session session = factory.openSession();
        List<Employee> list = session.createQuery("from Employee where username ="+request.getParameter("username"), Employee.class).list();
        Transaction transaction = session.beginTransaction();
    }
}

//<tbody>
//<td>20</td>
//<td>Ryan</td>
//<td>Cell</td>
//<td>pass</td>
//<td>C@gmail.com</td>
//<td>manager</td>
//</tbody>
//</table>
//</div>