package common;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by ehsan on 9/12/2018.
 */
@WebFilter(filterName = "RequestValidatorFilter" , urlPatterns = "/spring/secured/*")
public class RequestValidatorFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req,
                         ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest request = (HttpServletRequest) req;

        if (request.getSession().getAttribute("ticket") != null)
        {
            chain.doFilter(req, resp);
        }
        else
            {
                ((HttpServletResponse)resp).sendRedirect("/spring/Registration_Form");
            }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
