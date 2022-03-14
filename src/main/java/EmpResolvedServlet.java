import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

public class EmpResolvedServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        request.getRequestDispatcher("employeeResolved.html").include(request, response);
        Configuration config = new Configuration();
        config.configure("hibernate.cfg.xml");
        SessionFactory factory = config.buildSessionFactory();
        Session session = factory.openSession();
        HttpSession httpSession = request.getSession(false);
        int id = (Integer) httpSession.getAttribute("id");

        List<Receipt> list = session.createQuery("from Receipt where (status='approved' OR status='rejected') AND id="+id, Receipt.class).list();
        Transaction transaction1 = session.beginTransaction();
        for (Receipt r : list) {
            pw.println("<tbody>\n" +
                    "        <td>" + r.getId() + "</td>\n" +
                    "        <td>" + r.getRequest() + "</td>\n" +
                    "        <td>" + r.getAmount() + "</td>\n" +
                    "        <td>" + r.getStatus() + "</td>\n" +
                    "        <td>" + r.getSubDate() + "</td>\n" +
                    "        <td>" + r.getResDate() + "</td>\n" +
                    "</tbody>\n");
            transaction1.commit();
            session.close();
        }
        List<Receipt> list2 = session.createQuery("from Receipt where id="+id+" AND status='approved' OR status='rejected'" , Receipt.class).list();
        for(Receipt r: list2){

        }
        pw.println("</table>\n");
        session.close();
    }
}