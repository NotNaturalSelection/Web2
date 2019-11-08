import beans.Point;
import beans.PointBean;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ControllerServlet", urlPatterns = "controllerServlet")
public class ControllerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        if (req.getServletContext().getAttribute("array") == null) {
            PointBean array = new PointBean();
            req.getServletContext().setAttribute("array", array);
        }
        String[] x = req.getParameterValues("checkbox[]");
        String y = req.getParameter("textarea");
        String r = req.getParameter("select");
        if (x != null && y != null & r != null) {
            req.getRequestDispatcher("areaCheckServlet").forward(req, resp);
        } else {
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        }
    }

//    @Override
//    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        if(req.getMethod())
//    }
}