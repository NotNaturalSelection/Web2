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
        String[] x = req.getParameterValues("checkbox[]");
        String y = req.getParameter("textarea");
        String r = req.getParameter("select");
        double numberY = Double.parseDouble(y);
        double numberR = Double.parseDouble(r);
        boolean result = false;
        PointBean bean = (PointBean)getServletContext().getAttribute("array");
        for (String value: x) {
            double numberX = Double.parseDouble(value);
            if(numberX<=0 && numberY>=0 && numberY-numberX<=numberR){
                result = true;
            }
            if(numberX<=0 && numberY<=0 && pow(numberX, 2d)+pow(numberY, 2d)<=pow(numberR/2, 2d)){
                result = true;
            }
            if(numberX >=0 && numberY>=0 && numberX<=numberR && numberY<= numberR/2){
                result = true;
            }
            bean.addValue(numberX, numberY, numberR, result);
        }
        req.getRequestDispatcher("index.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}