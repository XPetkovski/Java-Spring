package mk.ukim.finki.wpaud.web.servlet;

import org.springframework.boot.web.servlet.ServletComponentScan;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@ServletComponentScan
@WebServlet(name = "hello", urlPatterns = "")
public class HelloWorldServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");

        PrintWriter writer = resp.getWriter();
        writer.format("<html><head></head><body><h1>Hi! %s %s </h1></body></html>", name, surname);
        writer.flush();
    }
}
