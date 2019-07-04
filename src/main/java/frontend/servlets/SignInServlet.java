package frontend.servlets;

import base.AccountService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

public class SignInServlet extends HttpServlet {
    private final AccountService accountService;
    private final static String TEXT_HTML = "text/html";
    private final static String CHARSET_UTF_8 = "charset=utf-8";

    public SignInServlet(AccountService accountService) {
        this.accountService = accountService;
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        boolean loggedIn = accountService.singIn(login, password);
        response.setContentType(TEXT_HTML + File.pathSeparator + CHARSET_UTF_8);
        if (loggedIn) {
            response.getWriter().println("Authorized: " + login);
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.getWriter().println("Unauthorized");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }
}
