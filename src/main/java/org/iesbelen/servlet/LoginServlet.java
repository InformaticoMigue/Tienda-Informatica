package org.iesbelen.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.iesbelen.dao.UsuarioDAOImpl;
import org.iesbelen.model.Usuario;

import java.io.IOException;
import java.util.Arrays;

@WebServlet(name = "loginServlet", value = "/tienda/login/*")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getPathInfo() == null || req.getPathInfo().equals("/")){
            RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/jsp/usuarios/login.jsp");
            rd.forward(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String __url__ = req.getPathInfo();
        UsuarioDAOImpl us = new UsuarioDAOImpl();



        if (__url__ == null){
            Usuario auxUser = new Usuario();
            auxUser.setUsuario(req.getParameter("username"));
            auxUser.setPassword(req.getParameter("password"));

            Usuario real = us.exists(auxUser);

            if (real != null) {
                HttpSession session = req.getSession(true);
                session.setAttribute("usuarioLogin", real);
            }
            resp.sendRedirect(req.getContextPath());

        }else {
            String[] __url__split = req.getPathInfo().split("/");
            if ("logout".equals(__url__split[1])) {
                HttpSession session = req.getSession();
                session.invalidate();
                resp.sendRedirect(req.getContextPath());
            }
        }

    }
}
