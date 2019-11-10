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
        if (req.getParameter("type") != null) {
            if (req.getParameter("type").equals("form")) {
                String[] x = req.getParameterValues("checkbox[]");
                String y = req.getParameter("textarea");
                String r = req.getParameter("select");
                if (x != null && y != null & r != null) {
                    boolean hasOkParams = false;
                    for (String strX : x) {
                        if (isNumeric(strX) && isInt(Double.parseDouble(strX))) {
                            hasOkParams = true;
                        }
                    }
                    if (isNumeric(y) && isNumeric(r) && hasOkParams) {
                        req.getRequestDispatcher("areaCheckServlet").forward(req, resp);
                    } else {
                        resp.sendRedirect("index.jsp");//todo добавить сообщение о некорректных данных
                    }
                } else {
                    resp.sendRedirect("index.jsp");
                }
            } else {
                req.getRequestDispatcher("areaCheckServlet").forward(req, resp);
            }
        } else {
            resp.sendRedirect("index.jsp");
        }
    }

    private void initializeBean(HttpServletRequest req) {
        if (req.getServletContext().getAttribute("array") == null) {
            PointBean array = new PointBean();
            req.getServletContext().setAttribute("array", array);
        }
    }

    private boolean validate(int x, double y, double r) {//значения в корректных пределах
        return x <= 5 && x >= -3 && y > -3 && y < 5 && (r == 1 || r == 1.5 || r == 2 || r == 2.5 || r == 3);
    }

    private boolean isInt(double number) {
        return number == (int) number;
    }

    private boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}