package servlets;

import accounts.AccountService;
import accounts.UserProfile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Sergei on 26.04.2016.
 */
public class SignUpServlet extends HttpServlet {

    private final AccountService accountService;

    public SignUpServlet(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        response.setContentType("text/html; charset=utf-8");

        if (login == null || password == null || login.length() < 4 || password.length() < 4 ||
                login.length() > 15 || password.length() > 15) {
            response.getWriter().println("Wrong login or password!");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }


        accountService.addNewUser(new UserProfile(login, password));
        response.getWriter().println("You've been registered.");
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
