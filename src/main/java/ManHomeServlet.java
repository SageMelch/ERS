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
import java.util.Date;
import java.util.List;

public class ManHomeServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        request.getRequestDispatcher("managerHome.html").include(request, response);
        Configuration config = new Configuration();
        config.configure("hibernate.cfg.xml");
        SessionFactory factory = config.buildSessionFactory();
        Session session = factory.openSession();
        List<Receipt> list = session.createQuery("from Receipt where status=NULL", Receipt.class).list();
        Transaction transaction = session.beginTransaction();
        if(list.isEmpty()){
            pw.println("<p>No Pending Receipts</p>");
        }else{
            pw.println("<h3>&nbsp&nbsp&nbsp&nbspTo Do List: </h3>\n" +
                    "    <table>\n" +
                    "        <thead>\n" +
                    "        <th>Employee ID</th>\n" +
                    "        <th>Request</th>\n" +
                    "        <th>Amount</th>\n" +
                    "        <th>Status</th>\n" +
                    "        <th>Date Submitted</th>\n" +
                    "        </thead>");
            for(Receipt r:list){
                String status = r.getStatus();
                if(status==null){
                    status = "Pending";
                }
                pw.println("<tbody>\n" +
                        "        <td>"+r.getId()+"</td>\n" +
                        "        <td>"+r.getRequest()+"</td>\n" +
                        "        <td>$"+r.getAmount()+"</td>\n" +
                        "        <td>"+status+"</td>\n" +
                        "        <td>"+r.getSubDate()+"</td>\n" +
                        "        </tbody>\n");
            }
            pw.println("</table>\n" +
                    "<br><center><form id='AppRejForm' method='post'>\n" +
                    "Employee ID: <input class='toDo' name='id' type='text'>&nbsp&nbsp&nbsp&nbsp&nbsp" +
                    "Request: <input class='toDo' name='req' type='text'>&nbsp&nbsp&nbsp&nbsp&nbsp" +
                    "Amount: <input class='toDo' name='amount' type='text'>&nbsp&nbsp&nbsp&nbsp&nbsp" +
                    "<select name=\"status\">" +
                    "           <option>Approve</option>" +
                    "           <option>Reject</option>\n" +
                    "</select>&nbsp&nbsp&nbsp&nbsp&nbsp" +
                    "   <input type='submit' value='Submit'></form></center>" +
                    "</div>");
        }

    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try{
            response.setContentType("text/html");
            PrintWriter pw = response.getWriter();
            Configuration config = new Configuration();
            config.configure("hibernate.cfg.xml");
            SessionFactory factory = config.buildSessionFactory();
            Session session = factory.openSession();
            int id = Integer.parseInt(request.getParameter("id"));
            double amount = Double.parseDouble(request.getParameter("amount"));
            Transaction transaction = session.beginTransaction();
            Receipt r = session.createQuery("from Receipt where id="+request.getParameter("id")+" AND request='"+request.getParameter("req")+"' AND amount="+request.getParameter("amount"), Receipt.class).getSingleResult();
            Date date = new Date();
            System.out.println(r.getRequest());
            String stat = request.getParameter("status");
            if (stat.equals("Approve")) {
                r.getId();
                r.getRequest();
                r.getAmount();
                r.setResDate(date.toString());
                r.setStatus("approved");
                r.getSubDate();
                session.update(r);
                transaction.commit();
                session.close();
            } else if (stat.equals("Reject")) {
                r.getId();
                r.getRequest();
                r.getAmount();
                r.setStatus("rejected");
                r.setResDate(date.toString());
                r.getSubDate();
                session.update(r);
                transaction.commit();
                session.close();
            }
            doGet(request, response);
        } catch (IllegalArgumentException e){
            e.printStackTrace();
            response.setContentType("text/html");
            PrintWriter pw = response.getWriter();
            Configuration config = new Configuration();
            config.configure("hibernate.cfg.xml");
            SessionFactory factory = config.buildSessionFactory();
            Session session = factory.openSession();
            List<Receipt> list = session.createQuery("from Receipt where status=NULL", Receipt.class).list();
            pw.println("<h3>&nbsp&nbsp&nbsp&nbspTo Do List: </h3>\n" +
                    "    <table>\n" +
                    "        <thead>\n" +
                    "        <th>Employee ID</th>\n" +
                    "        <th>Request</th>\n" +
                    "        <th>Amount</th>\n" +
                    "        <th>Status</th>\n" +
                    "        <th>Date Submitted</th>\n" +
                    "        </thead>");
            for(Receipt r:list){
                String status = r.getStatus();
                if(status==null){
                    status = "Pending";
                }
                pw.println("<tbody>\n" +
                        "        <td>"+r.getId()+"</td>\n" +
                        "        <td>"+r.getRequest()+"</td>\n" +
                        "        <td>$"+r.getAmount()+"</td>\n" +
                        "        <td>"+status+"</td>\n" +
                        "        <td>"+r.getSubDate()+"</td>\n" +
                        "        </tbody>\n");
            }
            pw.println("</table>\n" +
                    "<br><center><form id='AppRejForm' method='post'>Employee ID: <input name='id' type='text'>&nbsp&nbsp&nbsp&nbsp&nbsp<select name=\"status\"><option name='approve'>Approve</option><option name='reject'>Reject</option></select>&nbsp&nbsp&nbsp&nbsp&nbsp<input type='submit' value='Submit'></form></center>" +
                    "</div>");

        }
    }
}

//        response.setContentType("text/html");
//                PrintWriter pw = response.getWriter();
//                request.getRequestDispatcher("index.html").include(request, response);
//
//
//                Configuration config = new Configuration();
//                config.configure("hibernate.cfg.xml");
//
//                SessionFactory factory = config.buildSessionFactory();
//                Session session = factory.openSession();
//                List<Employee> list = session.createQuery("from Employee", Employee.class).list();
//
//
//
//
//        Transaction transaction = session.beginTransaction();
//
//
//
//        pw.println("<table>\n" +
//        "        <thead>\n<br>" +
//        "            <tr>Employee List</tr>\n" +
//        "            <th>ID</th>\n" +
//        "            <th>Name</th>\n" +
//        "            <th>Email</th>\n" +
//        "            <th>Gender</th>\n" +
//        "            <th>Country</th>\n" +
//        "        </thead>");
//        for(Employee e:list){
//        String id = request.getParameter("id");
//        String name = request.getParameter("name");
//        String email = request.getParameter("email");
//        String country = request.getParameter("country");
//        String gender = request.getParameter("gender");
//        pw.println("<!DOCTYPE html>\n" +
//        "<html lang=\"en\">\n" +
//        "<head>\n" +
//        "    <meta charset=\"UTF-8\">\n" +
//        "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
//        "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
//        "    <title>Document</title>\n" +
//        "    <style>\n" +
//        "        table{\n" +
//        "            background-color: gray;\n" +
//        "            width: 500px;\n" +
//        "            height: 100px;\n" +
//        "        }\n" +
//        "    </style>\n" +
//        "\n" +
//        "</head>\n" +
//        "<body>\n" +
//        "    <table>\n" +
//        "        <tbody>\n" +
//        "            <td>&nbsp&nbsp&nbsp&nbsp&nbsp"+e.getId()+"</td>\n" +
//        "            <td>"+e.getName()+"</td>\n" +
//        "            <td>"+e.getEmail()+"</td>\n" +
//        "            <td>"+e.getGender()+"</td>\n" +
//        "            <td>"+e.getCountry()+"</td>\n" +
//        "        </tbody>\n" +
//        "    </table>\n" +
//        "\n" +
//        "</body>\n" +
//        "</html>");
//        }
//        pw.println("</table>");
//        transaction.commit();
//        session.close();
//
//        }
//