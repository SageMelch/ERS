import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
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

import static java.lang.Integer.parseInt;

public class LoginServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        PrintWriter pw = response.getWriter();
        response.setContentType("text/html");
        String title = request.getParameter("title");
        Configuration config = new Configuration();
        config.configure("hibernate.cfg.xml");
        SessionFactory factory = config.buildSessionFactory();
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();

        if (title.equals("Employee")) {
            title = "employee";
            pw.println("<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                    "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                    "    <title>Document</title>\n" +
                    "    <style>\n" +
                    "\n" +
                    "        body{\n" +
                    "            background-color: lightblue;\n" +
                    "        }\n" +
                    "        #form{\n" +
                    "            background-image:url(https://udcareers.files.wordpress.com/2020/04/emile-perron-xrvdyzrgdw4-unsplash.jpg?w=1200&h=900&crop=1);\n" +
                    "            overflow: scroll;\n" +
                    "            background-color: aliceblue;\n" +
                    "            margin: auto;\n" +
                    "            width: 1200px;\n" +
                    "            height: 900px;\n" +
                    "\n" +
                    "\n" +
                    "        }\n" +
                    "        #border{\n" +
                    "            margin: auto;\n" +
                    "            width: 1200px;\n" +
                    "            height: 1200x;\n" +
                    "            border-width: 1300px;\n" +
                    "            border: orange;\n" +
                    "            background-image:url(https://udcareers.files.wordpress.com/2020/04/emile-perron-xrvdyzrgdw4-unsplash.jpg?w=1200&h=900&crop=1);\n" +
                    "\n" +
                    "        }\n" +
                    "        #arrow{\n" +
                    "            background-color: aliceblue;\n" +
                    "            width: 25px;\n" +
                    "            position: relative;\n" +
                    "            top: 8px;\n" +
                    "            border-radius: 10px;\n" +
                    "        }\n" +
                    "        #lock{\n" +
                    "            width: 25px;\n" +
                    "            position: relative;\n" +
                    "            top: 10px;\n" +
                    "            border-radius: 100px;\n" +
                    "        }\n" +
                    "\n" +
                    "        input{\n" +
                    "            font-family:'Trebuchet MS', 'Lucida Sans Unicode', 'Lucida Grande', 'Lucida Sans', Arial, sans-serif;\n" +
                    "        }\n" +
                    "\n" +
                    "        form{\n" +
                    "            display:inline-table;\n" +
                    "            margin:auto;\n" +
                    "            padding-bottom: 325px;\n" +
                    "            padding-left: 100px;\n" +
                    "        }\n" +
                    "        .loginRef{\n" +
                    "            font-family: 'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif;\n" +
                    "\n" +
                    "        }\n" +
                    "\n" +
                    "        #logo{\n" +
                    "            height: 150px;\n" +
                    "\n" +
                    "            width: 500px;\n" +
                    "        }\n" +
                    "        h1 {\n" +
                    "            width: 1200px;\n" +
                    "\n" +
                    "            margin: auto;\n" +
                    "            background-image: url(https://images.freecreatives.com/wp-content/uploads/2016/02/Light-Blue-Background-For-Free1.jpg)\n" +
                    "\n" +
                    "        }\n" +
                    "        .intro{\n" +
                    "            font-size: 40px;\n" +
                    "            font-family: Impact, Haettenschweiler, 'Arial Narrow Bold', sans-serif;\n" +
                    "            padding-left: 100px;\n" +
                    "        }\n" +
                    "        a{\n" +
                    "            color: black;\n" +
                    "        }\n" +
                    "    </style>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "<center><h1><img id=\"logo\" src=\"https://revature.com/wp-content/uploads/2017/12/revature-logo-600x219.png\"></h1></center>\n" +
                    "<div id=\"border\">\n" +
                    "    <br><br><br><br><br><br><br>\n" +
                    "    <div class=\"intro\">Employee Login</div>\n" +
                    " <form id=\"info\" method=\"post\">\n" +
                    "            <p><center>Username:</center><img id='arrow' src=\"arrow.png\">&nbsp<input type=\"text\" name=\"username\" placeholder=\"Enter Username\"></p>\n" +
                    "            <p><center>Password:</center><img id='lock' src=\"lock.png\">&nbsp<input type=\"text\" name=\"password\" placeholder=\"Enter Password\"></p>\n" +
                    "            <input type=\"submit\" value=\"LOGIN\"><br><br><br><br><br><br><br>\n" +
                    "            <br><br><a href=\"\" class=\"loginRef\">New User? Click Here!</a>\n" +
                    "            <br><br><a href=\"\" class=\"loginRef\">Forgot User ID or Password?</a>\n" +
                    "        </form>\n" +
                    "</body>\n" +
                    "</html>");
            List<Employee> list = session.createQuery("from Employee where username='" + request.getParameter("username") + "' AND password='" + request.getParameter("password") + "'", Employee.class).list();
            String user = list.get(0).getUsername();
            String pass = list.get(0).getPassword();
            transaction.commit();
            session.close();
        } else if (title.equals("Manager")) {
            title = "manager";
            pw.println("<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                    "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                    "    <title>Document</title>\n" +
                    "    <style>\n" +
                    "\n" +
                    "        body{\n" +
                    "            background-color: lightblue;\n" +
                    "        }\n" +
                    "        #form{\n" +
                    "            background-image:url(https://udcareers.files.wordpress.com/2020/04/emile-perron-xrvdyzrgdw4-unsplash.jpg?w=1200&h=900&crop=1);\n" +
                    "            overflow: scroll;\n" +
                    "            background-color: aliceblue;\n" +
                    "            margin: auto;\n" +
                    "            width: 1200px;\n" +
                    "            height: 900px;\n" +
                    "\n" +
                    "\n" +
                    "        }\n" +
                    "        #border{\n" +
                    "            margin: auto;\n" +
                    "            width: 1200px;\n" +
                    "            height: 1200x;\n" +
                    "            border-width: 1300px;\n" +
                    "            border: orange;\n" +
                    "            background-image:url(https://udcareers.files.wordpress.com/2020/04/emile-perron-xrvdyzrgdw4-unsplash.jpg?w=1200&h=900&crop=1);\n" +
                    "\n" +
                    "        }\n" +
                    "        #arrow{\n" +
                    "            background-color: aliceblue;\n" +
                    "            width: 25px;\n" +
                    "            position: relative;\n" +
                    "            top: 8px;\n" +
                    "            border-radius: 10px;\n" +
                    "        }\n" +
                    "        #lock{\n" +
                    "            width: 25px;\n" +
                    "            position: relative;\n" +
                    "            top: 10px;\n" +
                    "            border-radius: 100px;\n" +
                    "        }\n" +
                    "\n" +
                    "        input{\n" +
                    "            font-family:'Trebuchet MS', 'Lucida Sans Unicode', 'Lucida Grande', 'Lucida Sans', Arial, sans-serif;\n" +
                    "        }\n" +
                    "\n" +
                    "        form{\n" +
                    "            display:inline-table;\n" +
                    "            margin:auto;\n" +
                    "            padding-bottom: 325px;\n" +
                    "            padding-left: 100px;\n" +
                    "        }\n" +
                    "        .loginRef{\n" +
                    "            font-family: 'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif;\n" +
                    "\n" +
                    "        }\n" +
                    "\n" +
                    "        #logo{\n" +
                    "            height: 150px;\n" +
                    "\n" +
                    "            width: 500px;\n" +
                    "        }\n" +
                    "        h1 {\n" +
                    "            width: 1200px;\n" +
                    "\n" +
                    "            margin: auto;\n" +
                    "            background-image: url(https://images.freecreatives.com/wp-content/uploads/2016/02/Light-Blue-Background-For-Free1.jpg)\n" +
                    "\n" +
                    "        }\n" +
                    "        .intro{\n" +
                    "            font-size: 40px;\n" +
                    "            font-family: Impact, Haettenschweiler, 'Arial Narrow Bold', sans-serif;\n" +
                    "            padding-left: 100px;\n" +
                    "        }\n" +
                    "        a{\n" +
                    "            color: black;\n" +
                    "        }\n" +
                    "    </style>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "<center><h1><img id=\"logo\" src=\"https://revature.com/wp-content/uploads/2017/12/revature-logo-600x219.png\"></h1></center>\n" +
                    "<div id=\"border\">\n" +
                    "    <br><br><br><br><br><br><br>\n" +
                    "    <div class=\"intro\">Manager Login</div>\n" +
                    " <form id=\"info\" method=\"post\">\n" +
                    "            <p><center>Username:</center><img id='arrow' src=\"arrow.png\">&nbsp<input type=\"text\" name=\"username\" placeholder=\"Enter Username\"></p>\n" +
                    "            <p><center>Password:</center><img id='lock' src=\"lock.png\">&nbsp<input type=\"text\" name=\"password\" placeholder=\"Enter Password\"></p>\n" +
                    "            <input type=\"submit\" value=\"LOGIN\"><br><br><br><br><br><br><br>\n" +
                    "            <br><br><a href=\"\" class=\"loginRef\">New User? Click Here!</a>\n" +
                    "            <br><br><a href=\"\" class=\"loginRef\">Forgot User ID or Password?</a>\n" +
                    "        </form>\n" +
                    "</body>\n" +
                    "</html>");
            List<Employee> list = session.createQuery("from Employee where username='" + request.getParameter("username") + "' AND password='" + request.getParameter("password") + "'", Employee.class).list();
            String user = list.get(0).getUsername();
            String pass = list.get(0).getPassword();
            transaction.commit();
            session.close();
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        PrintWriter pw = response.getWriter();
        response.setContentType("text/html");
        Configuration config = new Configuration();
        config.configure("hibernate.cfg.xml");
        SessionFactory factory = config.buildSessionFactory();
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        String title = request.getParameter("title");
        List<Employee> list = session.createQuery("from Employee where username='" + request.getParameter("username") + "' AND password='" + request.getParameter("password") + "' AND title='" + title + "'", Employee.class).list();
        System.out.println("Works");
        try {
            String user = list.get(0).getUsername();
            String pass = list.get(0).getPassword();
            System.out.println("Works");
            if (user.equals(request.getParameter("username")) && pass.equals(request.getParameter("password")) && title.equals("Employee")) {
                request.getRequestDispatcher("employeeHome.html").include(request, response);
                Employee employee = new Employee();
                pw.println("<br><h1>&nbsp&nbsp&nbsp&nbspHome</h1>\n" +
                        "<table>\n" +
                        "        <thead>\n<br>" +
                        "            <p>&nbsp&nbspEmployee Information:</p>\n" +
                        "            <th>ID</th>\n" +
                        "            <th>Name</th>\n" +
                        "            <th>Username</th>\n" +
                        "            <th>Password</th>\n" +
                        "            <th>Email</th>\n" +
                        "            <th>Title</th>\n" +
                        "        </thead>");
                for (Employee e : list) {
                    pw.println("&nbsp&nbsp&nbsp&nbsp&nbsp<tbody>\n" +
                            "            <td>" + e.getId() + "</td>\n" +
                            "            <td>" + e.getName() + "</td>\n" +
                            "            <td>" + e.getUsername() + "</td>\n" +
                            "            <td>" + e.getPassword() + "</td>\n" +
                            "            <td>" + e.getEmail() + "</td>\n" +
                            "            <td>" + e.getTitle() + "</td>\n" +
                            "        </tbody>\n");
                    ServletContext servletContext = getServletContext();
                    servletContext.setAttribute("username", e);
                    HttpSession httpSession = request.getSession(true);
                    httpSession.setAttribute("id", e.getId());
                }
                pw.println("</table>\n" +
                        "</body>\n" +
                        "</html>");
                transaction.commit();
            } else if(user.equals(request.getParameter("username")) && pass.equals(request.getParameter("password")) && title.equals("Manager")) {
                request.getRequestDispatcher("managerHome.html").include(request, response);
                pw.println("<br><h1>&nbsp&nbsp&nbsp&nbspHome</h1>\n" +
                        "<table>\n" +
                        "        <thead>\n<br>" +
                        "            <p>&nbsp&nbspManager Information:</p>\n" +
                        "            <th>ID</th>\n" +
                        "            <th>Name</th>\n" +
                        "            <th>Username</th>\n" +
                        "            <th>Password</th>\n" +
                        "            <th>Email</th>\n" +
                        "            <th>Title</th>\n" +
                        "        </thead>");
                for (Employee e : list) {
                    pw.println("&nbsp&nbsp&nbsp&nbsp&nbsp<tbody>\n" +
                            "            <td>" + e.getId() + "</td>\n" +
                            "            <td>" + e.getName() + "</td>\n" +
                            "            <td>" + e.getUsername() + "</td>\n" +
                            "            <td>" + e.getPassword() + "</td>\n" +
                            "            <td>" + e.getEmail() + "</td>\n" +
                            "            <td>" + e.getTitle() + "</td>\n" +
                            "        </tbody>\n");
                    HttpSession httpSession = request.getSession(true);
                    httpSession.setAttribute("id", e.getId());
            }
                pw.println("</table>\n" +
                        "</body>\n" +
                        "</html>");
            } else {
                pw.println("<h1>Failure</h1>");
            }
            transaction.commit();
            session.close();
        } catch (IndexOutOfBoundsException e) {
            request.getRequestDispatcher("index.html").include(request, response);
            pw.println("<h1>Invalid Credentials</h1>");
            e.printStackTrace();
        }
    }
}

