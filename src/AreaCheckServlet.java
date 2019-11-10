import beans.Point;
import beans.PointBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.lang.Math.pow;

@WebServlet(name = "AreaCheckServlet", urlPatterns = "areaCheckServlet")
public class AreaCheckServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter("type") != null) {
            if (req.getParameter("type").equals("form")) {

                String[] x = req.getParameterValues("checkbox[]");
                double numberY = Double.parseDouble(req.getParameter("textarea"));
                double numberR = Double.parseDouble(req.getParameter("select"));


                PointBean bean = (PointBean) getServletContext().getAttribute("array");
                for (String value : x) {
                    bean.addValue(Double.parseDouble(value), numberY, numberR, isHit(Double.parseDouble(value), numberY, numberR));
                }
                resp.sendRedirect("index.jsp");
            } else {
                double x = Double.parseDouble(req.getParameter("x"));
                double y = Double.parseDouble(req.getParameter("y"));
                double r = Double.parseDouble(req.getParameter("r"));

                ((PointBean) getServletContext().getAttribute("array")).addValue(x, y, r, AreaCheckServlet.isHit(x, y, r));
                resp.getWriter().write(new Point(x, y, r, AreaCheckServlet.isHit(x, y, r)).toJson());
            }
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