package mk.ukim.finki.wpaud.web.servlet;

import mk.ukim.finki.wpaud.model.Category;
import mk.ukim.finki.wpaud.service.CategoryService;
import org.springframework.boot.web.servlet.ServletComponentScan;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@ServletComponentScan
@WebServlet(name = "category-servlet", urlPatterns = "/servlet/category")
public class CategoryServlet extends HttpServlet {

    private final CategoryService categoryService;

    public CategoryServlet(CategoryService categoryService)
    {
        this.categoryService = categoryService;

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String ipAddress = req.getRemoteAddr();
        String clientAgent = req.getHeader("User-Agent");
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h3>User Info: </h3>");
        out.format("IP Address: %s</br>", ipAddress);
        out.format("Client Agent: %s ", clientAgent);
        out.println("<h3>Category List</h3>");
        out.println("<ul>");
        this.categoryService.listCategories().stream().forEach(r->out.format("<li>%s (%s)</li>", r.getName(), r.getDescription()));
        out.println("</ul>");
        out.println("<h3>Add Category</h3>");
        out.println("<form method='POST' action='/servlet/category'>");
        out.println("<label for='name'>Name: </label>");
        out.println("<input id='name' type='text' name='name'/>");
        out.println("<label for='desc'>Description: </label>");
        //out.println("<label for='desc'>Description:</label>");
        out.println("<input id='desc' type='text' name='desc'/>");
        out.println("<input type='submit' value='Submit'/>");
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String categoryName = req.getParameter("name");
        String categoryDesc = req.getParameter("desc");
        categoryService.create(categoryName,categoryDesc);
        //addCategory(categoryName, categoryDesc);
        resp.sendRedirect("/servlet/category");
    }

    //private void addCategory(String name, String description)
    //{
    //    if(name!=null && !name.isEmpty())
    //    {
    //        this.categoryList.add(new Category(name, description));
    //    }
    //}

}
