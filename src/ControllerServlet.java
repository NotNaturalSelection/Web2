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
        initializeBeans(req);
        if (hasParameters(req)) {
            if (hasRequiredParameters(req)) {
                if (hasCorrectParameters(req)) {
                    req.getRequestDispatcher("/WEB-INF/areaCheckServlet").forward(req, resp);
                } else {
                    ErrorBean errorBean = (ErrorBean)req.getAttribute("errors");
                    searchErrors(req,errorBean);
//                    if(!isCheckboxCorrect(req)) {
//                        errorBean.addError("Параметр \"X\" некорректен");
//                    }
//                    if(!isTextareaCorrect(req)) {
//                        errorBean.addError("Параметр \"Y\" некорректен");
//                    }
//                    if(!isSelectCorrect(req)) {
//                        errorBean.addError("Параметр \"R\" некорректен");
//                    }
//                    resp.sendRedirect("error.jsp");
                    req.getRequestDispatcher("/WEB-INF/error.jsp").forward(req, resp);
                }
            } else {
                ErrorBean errorBean = (ErrorBean)req.getAttribute("errors");
//                if(!hasParameter(req,"checkbox[]")) {
//                    ((ErrorBean) req.getAttribute("errors")).addError("В запросе нет параметров \"X\"");
//                }
//                if(!hasParameter(req,"textarea")) {
//                    ((ErrorBean) req.getAttribute("errors")).addError("В запросе нет параметра \"Y\"");
//                }
//                if(!hasParameter(req,"select")) {
//                    ((ErrorBean) req.getAttribute("errors")).addError("В запросе нет параметра \"R\"");
//                }
                searchErrors(req, errorBean);
                req.getRequestDispatcher("/WEB-INF/error.jsp").forward(req, resp);
//                resp.sendRedirect("error.jsp");
            }
        } else {
            resp.sendRedirect("index.jsp");
        }
    }

    private void initializeBeans(HttpServletRequest req) {
        if (getServletContext().getAttribute("array") == null) {
            PointBean array = new PointBean();
            getServletContext().setAttribute("array", array);
        }
        if (req.getAttribute("errors") == null) {
            ErrorBean errors = new ErrorBean();
            req.setAttribute("errors", errors);
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
        } catch (Exception e) {
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

    private boolean hasParameter(HttpServletRequest req, String parameterName){
        return (req.getParameter(parameterName)!= null);
    }

    private void searchErrors(HttpServletRequest req, ErrorBean errorBean){
        if(!hasParameter(req,"checkbox[]")) {
            ((ErrorBean) req.getAttribute("errors")).addError("В запросе нет параметров \"X\"");
        } else {
            if(!isCheckboxCorrect(req)) {
                errorBean.addError("Параметр \"X\" некорректен");
            }
        }


        if(!hasParameter(req,"textarea")) {
            ((ErrorBean) req.getAttribute("errors")).addError("В запросе нет параметра \"Y\"");
        } else {
            if(!isTextareaCorrect(req)) {
                errorBean.addError("Параметр \"Y\" некорректен");
            }
        }


        if(!hasParameter(req,"select")) {
            ((ErrorBean) req.getAttribute("errors")).addError("В запросе нет параметра \"R\"");
        } else {
            if(!isSelectCorrect(req)) {
                errorBean.addError("Параметр \"R\" некорректен");
            }
        }
    }
}