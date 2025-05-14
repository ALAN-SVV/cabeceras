package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Producto;
import service.ProductoService;
import service.ProductoServiceImplement;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

// Implementamos la anotación
@WebServlet({"/products.xls", "/productos.html"})
public class ProductoXlsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp) throws ServletException,
            IOException {
        ProductoService service = new ProductoServiceImplement();
        List<Producto> productos = service.listar();
        resp.setContentType("text/html;charset=UTF-8");
        String servletPath = req.getServletPath();
        boolean esXls = servletPath.endsWith(".xls");

        if (esXls) {
            resp.setContentType("application/vnd.ms-excel");
            resp.setHeader("Content-Disposition", "attachment; filename=products.xls");
        }

        try (PrintWriter out = resp.getWriter()) {
            if (!esXls) {
                // Creo la plantilla html
                out.print("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("    <meta charset=\"utf-8\">");
                out.println("    <title>Listado de Productos</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("    <h1>Listado de productos</h1>");
                out.println("</h1>");
                out.println("<p><a href=\"" + req.getContextPath() + "/products.xls\">Exportar a Excel</a></p>");
                out.println("<p><a href=\"" + req.getContextPath() + "/producto.json\">Mostrar JSON</a></p>");

                out.println("<table>");
                out.println("    <tr>");
                out.println("        <th>ID</th>");
                out.println("        <th>Nombre</th>");
                out.println("        <th>Tipo</th>");
                out.println("        <th>Precio</th>");
                out.println("    </tr>");

                productos.forEach(p -> {
                    out.println("    <tr>");
                    out.println("        <td>" + p.getId() + "</td>");
                    out.println("        <td>" + p.getNombre() + "</td>");
                    out.println("        <td>" + p.getTipo() + "</td>");
                    out.println("        <td>" + p.getPrecio() + "</td>");
                    out.println("    </tr>");
                });
                out.println("</table>");
                if (!esXls) {
                    out.println("</body>");
                    out.println("</html>");
                }
            }
        }
    }
}
