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

public class ManResolvedServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        request.getRequestDispatcher("managerResolved.html").include(request, response);
        Configuration config = new Configuration();
        config.configure("hibernate.cfg.xml");
        SessionFactory factory = config.buildSessionFactory();
        Session session = factory.openSession();
        List<Receipt> list = session.createQuery("from Receipt where status= 'approved' OR status = 'rejected'", Receipt.class).list();
        Transaction transaction = session.beginTransaction();

        for(Receipt r:list){
            String status = r.getStatus();
            if(status==null){
                status = "Pending";
            }
            pw.println("<tbody>\n" +
                    "        <td>"+r.getId()+"</td>\n" +
                    "        <td>"+r.getRequest()+"</td>\n" +
                    "        <td>"+r.getAmount()+"</td>\n" +
                    "        <td>"+status+"</td>\n" +
                    "        <td>"+r.getSubDate()+"</td>\n" +
                    "        <td>"+r.getResDate()+"</td>" +
                    "        </tbody>\n");
        }
        pw.println("</table>\n" +
                "</div>");
    }
}
