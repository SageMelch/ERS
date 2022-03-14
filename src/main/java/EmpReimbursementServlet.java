import jakarta.persistence.Query;
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
import java.util.Date;
import java.util.List;

public class EmpReimbursementServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        request.getRequestDispatcher("employeeReimbursement.html").include(request, response);
        Configuration config = new Configuration();
        config.configure("hibernate.cfg.xml");
        SessionFactory factory = config.buildSessionFactory();
        Session session = factory.openSession();
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        request.getRequestDispatcher("employeeReimbursement.html").include(request, response);
        double coefficient = Double.parseDouble(request.getParameter("coefficient"));
        String dec = request.getParameter("decimal");
        double decimal = Double.parseDouble(request.getParameter("decimal"))/100;
        double amount = coefficient + decimal;
        if (dec.equals(null)){
            decimal = 0;
        }
        Receipt receipt = new Receipt();
        receipt.setId(Integer.parseInt(request.getParameter("id")));
        receipt.setRequest(request.getParameter("request"));
        receipt.setAmount(amount);
        receipt.getStatus();
        Date date = new Date();
        receipt.setSubDate(date.toString());
        receipt.setResDate(null);
        Configuration config = new Configuration();
        config.configure("hibernate.cfg.xml");
        SessionFactory factory = config.buildSessionFactory();
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(receipt);
        transaction.commit();
        session.close();
    }
}
