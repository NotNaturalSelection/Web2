import beans.ErrorBean;
import beans.PointBean;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet(name = "ControllerServlet", urlPatterns = "controllerServlet")
public class ControllerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        initializeBean(req);
        if (hasParameters(req)) {
            if (hasRequiredParameters(req)) {
                if (hasCorrectParameters(req)) {
                    req.getRequestDispatcher("areaCheckServlet").forward(req, resp);
                } else {
                    ((ErrorBean)getServletContext().getAttribute("errors")).addError("Параметры запроса некорректны");
                    resp.sendRedirect("error.jsp");
                }
            } else {
                ((ErrorBean)getServletContext().getAttribute("errors")).addError("В запросе нет необходимых параметров");
                resp.sendRedirect("error.jsp");
            }
        } else {
            resp.sendRedirect("index.jsp");
        }
    }

    private void initializeBean(HttpServletRequest req) {
        if (getServletContext().getAttribute("array") == null) {
            PointBean array = new PointBean();
            getServletContext().setAttribute("array", array);
        }
        if (getServletContext().getAttribute("errors") == null) {
            ErrorBean errors = new ErrorBean();
            getServletContext().setAttribute("errors", errors);
        }
    }

    private boolean validate(int x, double y, double r) {//значения в корректных пределах
        return x <= 5 && x >= -3 && y > -3 && y < 5 && (r == 1 || r == 1.5 || r == 2 || r == 2.5 || r == 3);
    }

    private boolean isInt(double number) {
        return number == (int) number;
    }

    static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean hasRequiredParameters(HttpServletRequest req) {
        Map<String, String[]> map = req.getParameterMap();
        return (map.containsKey("textarea") && map.containsKey("checkbox[]") && map.containsKey("select"));
    }

    private boolean hasParameters(HttpServletRequest req) {
        return !req.getParameterMap().isEmpty();
    }

    private boolean hasCorrectParameters(HttpServletRequest req) {
        return isTextareaCorrect(req) && isSelectCorrect(req) && isCheckboxCorrect(req);
    }

    private boolean isTextareaCorrect(HttpServletRequest req) {
        String param = req.getParameter("textarea");
        if (isNumeric(param)) {
            double p = Double.parseDouble(param);
            return !(p >= 5 || p <= -3);
        }
        return false;
    }

    private boolean isSelectCorrect(HttpServletRequest req) {
        String param = req.getParameter("select");
        if (isNumeric(param)) {
            double p = Double.parseDouble(param);
            return !(p > 3 || p < 1);
        }
        return false;
    }

    private boolean isCheckboxCorrect(HttpServletRequest req) {
        String[] x = req.getParameterValues("checkbox[]");
        boolean hasOkParams = false;
        for (String strX : x) {
            if (isNumeric(strX)) {
                double p = Double.parseDouble(strX);
                if (!(p >= 5 || p <= -3)) {
                    hasOkParams = true;
                }
            }
        }
        return hasOkParams;
    }
}