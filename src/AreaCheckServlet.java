import beans.Point;
import beans.PointBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Math.pow;

@WebServlet(name = "AreaCheckServlet", urlPatterns = "areaCheckServlet")
public class AreaCheckServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String[] x = req.getParameterValues("checkbox[]");
        double numberY = Double.parseDouble(req.getParameter("textarea"));
        double numberR = Double.parseDouble(req.getParameter("select"));
        PointBean bean = (PointBean) getServletContext().getAttribute("array");
        List<Point> list = new ArrayList<>();
        for (String value : x) {
            if (ControllerServlet.isNumeric(value)) {
                bean.addValue(Double.parseDouble(value), numberY, numberR, isHit(Double.parseDouble(value), numberY, numberR));
                list.add(new Point(Double.parseDouble(value), numberY, numberR, isHit(Double.parseDouble(value), numberY, numberR)));
            }
        }
        if (req.getHeader("X-Requested-With") != null) {
            resp.getWriter().write(Point.toJsonArray(list.toArray(new Point[0])));
        } else {
            resp.sendRedirect("index.jsp");
        }
    }

    private static boolean isHit(double numberX, double numberY, double numberR) {
        boolean result = false;
        if (numberX <= 0 && numberY >= 0 && numberY - numberX <= numberR) {
            result = true;
        }
        if (numberX <= 0 && numberY <= 0 && pow(numberX, 2d) + pow(numberY, 2d) <= pow(numberR / 2, 2d)) {
            result = true;
        }
        if (numberX >= 0 && numberY >= 0 && numberX <= numberR && numberY <= numberR / 2) {
            result = true;
        }
        return result;
    }
}