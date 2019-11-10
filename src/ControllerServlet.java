import beans.Point;
import beans.PointBean;
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
        initializeBean(req);
        if (req.getParameterValues("checkbox[]") != null && req.getParameter("textarea") != null & req.getParameter("select") != null) {
            req.getRequestDispatcher("areaCheckServlet").forward(req, resp);
        } else {
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("areaCheckServlet").forward(req, resp);
    }

    protected void initializeBean(HttpServletRequest req){
        if (req.getServletContext().getAttribute("array") == null) {
            PointBean array = new PointBean();
            req.getServletContext().setAttribute("array", array);
        }
    }
}