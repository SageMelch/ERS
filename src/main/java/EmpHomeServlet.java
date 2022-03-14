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

public class EmpHomeServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        request.getRequestDispatcher("employeeHome.html").include(request, response);


        Configuration config = new Configuration();
        config.configure("hibernate.cfg.xml");

        SessionFactory factory = config.buildSessionFactory();
        Session session = factory.openSession();
        String title = request.getParameter("title");
        Transaction transaction = session.beginTransaction();

        pw.println("<br><h1>&nbsp&nbsp&nbsp&nbsp Welcome " + request.getParameter("username") + "</h1>\n" +
                "<table>\n" +
                "        <thead>\n<br>" +
                "            <p>&nbsp&nbspUser Information</p>\n" +
                "            <th>ID</th>\n" +
                "            <th>Name</th>\n" +
                "            <th>Username</th>\n" +
                "            <th>Password</th>\n" +
                "            <th>Email</th>\n" +
                "            <th>Title</th>\n" +
                "        </thead>");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        List<Employee> list = session.createQuery("from Employee where username = '" + username + "' AND password = '" + password + "' AND title = 'employee'", Employee.class).list();
        for (Employee e : list) {
            if(e.getName().equals(null)){
                transaction.rollback();
            }else{
                pw.println("<tbody>\n" +
                        "            <td>&nbsp&nbsp&nbsp&nbsp&nbsp" + e.getId() + "</td>\n" +
                        "            <td>" + e.getName() + "</td>\n" +
                        "            <td>" + e.getUsername() + "</td>\n" +
                        "            <td>" + e.getPassword() + "</td>\n" +
                        "            <td>" + e.getEmail() + "</td>\n" +
                        "            <td>" + e.getTitle() + "</td>\n" +
                        "        </tbody>\n");
            }
            e = new Employee(e.getId(), e.getName(), e.getUsername(), e.getPassword(), e.getEmail(), e.getTitle());
        }
        pw.println("</table>\n" +
                "</body>\n" +
                "</html>");
        session.persist(list);
        session.save(list);
        transaction.commit();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        request.getRequestDispatcher("employeeHome.html").include(request, response);


        Configuration config = new Configuration();
        config.configure("hibernate.cfg.xml");

        SessionFactory factory = config.buildSessionFactory();
        Session session = factory.openSession();
        String title = request.getParameter("title");
        Transaction transaction = session.beginTransaction();
        String username = request.getParameter("username");
        String welcome = "Welcome "+username;
        String password = request.getParameter("password");
        List<Employee> list = session.createQuery("from Employee where username = '" + username + "' AND password = '" + password + "' AND title = 'employee'", Employee.class).list();
        pw.println("<br><h1>&nbsp&nbsp&nbsp&nbspHome</h1>\n" +
                "<table>\n" +
                "        <thead>\n<br>" +
                "            <p>&nbsp&nbspEmployee List:</p>\n" +
                "            <th>ID</th>\n" +
                "            <th>Name</th>\n" +
                "            <th>Username</th>\n" +
                "            <th>Password</th>\n" +
                "            <th>Email</th>\n" +
                "            <th>Title</th>\n" +
                "        </thead>");
        for (Employee e: list) {
            pw.println("<tbody>\n" +
                    "            <td>" + e.getId() + "</td>\n" +
                    "            <td>" + e.getName() + "</td>\n" +
                    "            <td>" + e.getUsername() + "</td>\n" +
                    "            <td>" + e.getPassword() + "</td>\n" +
                    "            <td>" + e.getEmail() + "</td>\n" +
                    "            <td>" + e.getTitle() + "</td>\n" +
                    "        </tbody>\n");

        }
        if (username == null) {
            List<Employee> list2 = session.createQuery("from Employee where title = 'employee'", Employee.class).list();
            for (Employee e2 : list2) {
                pw.println("<tbody>\n" +
                        "            <td>&nbsp&nbsp&nbsp&nbsp&nbsp" + e2.getId() + "</td>\n" +
                        "            <td>" + e2.getName() + "</td>\n" +
                        "            <td>" + e2.getUsername() + "</td>\n" +
                        "            <td>" + e2.getPassword() + "</td>\n" +
                        "            <td>" + e2.getEmail() + "</td>\n" +
                        "            <td>" + e2.getTitle() + "</td>\n" +
                        "        </tbody>\n");
            }
        }
        pw.println("</table>\n" +
                "</body>\n" +
                "</html>");
        transaction.commit();
    }
}