package filter;

import java.io.IOException; 
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/*")
public class AuthenticationFilter extends HttpFilter implements Filter {
	private static final long serialVersionUID = 1L;

    public AuthenticationFilter() {
        
    }
    
	public void destroy() {}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		if(req.getRequestURI().contains("login") || req.getRequestURI().contains("signup")) {
			HttpSession session = req.getSession();
			if(session.getAttribute("principal") != null) {
				resp.sendRedirect("omokMainPage3.jsp");
				return;
			}
		}		
//		 else {
//	    // 로그인 페이지가 아닌 다른 페이지를 방문하는 데 세션 정보가 없다면 로그인 페이지로 리다이렉트
//	    HttpSession session = req.getSession();
//	    if (session.getAttribute("principal") == null) {
//	        resp.sendRedirect("loginPageCookie.jsp");
//	        return;
//	    }
//	}
		req.getServletContext();
		
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		fConfig.getServletContext();
	}

}







