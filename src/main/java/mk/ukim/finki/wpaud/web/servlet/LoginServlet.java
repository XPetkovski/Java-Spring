package mk.ukim.finki.wpaud.web.servlet;

import mk.ukim.finki.wpaud.model.User;
import mk.ukim.finki.wpaud.model.exceptions.InvalidUserCredentialsException;
import mk.ukim.finki.wpaud.service.AuthService;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@ServletComponentScan
@WebServlet(name = "LoginServlet", value = "/servlet/login")
public class LoginServlet extends HttpServlet {

    private final SpringTemplateEngine springTemplateEngine;
    private final AuthService authService;

    public LoginServlet(SpringTemplateEngine springTemplateEngine, AuthService authService) {
        this.springTemplateEngine = springTemplateEngine;
        this.authService = authService;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        WebContext context = new WebContext(request, response, request.getServletContext());
        springTemplateEngine.process("login.html", context, response.getWriter());

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = null;
        try
        {
            user = authService.login(username,password);
        } catch(InvalidUserCredentialsException exception)
        {
            WebContext context = new WebContext(request, response, request.getServletContext());
            context.setVariable("hasError", true);
            context.setVariable("error", exception.getMessage());
            springTemplateEngine.process("login.html", context, response.getWriter());
        }
        request.getSession().setAttribute("user", user);
        response.sendRedirect("/servlet/thymeleaf/category");
    }
}
