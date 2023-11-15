package org.iesbelen.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.iesbelen.dao.UsuarioDAOImpl;
import org.iesbelen.model.Fabricante;
import org.iesbelen.model.Usuario;

import java.io.IOException;
import java.util.Arrays;

@WebServlet(name = "usuariosServlet", value = "/tienda/usuarios/*")
public class UsuariosServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        RequestDispatcher rd;

        if (pathInfo == null || pathInfo.equals("/")) {
            UsuarioDAOImpl uDAO = new UsuarioDAOImpl();
            req.setAttribute("listaUsuarios",uDAO.getAll());

            rd = req.getRequestDispatcher("/WEB-INF/jsp/usuarios/usuarios.jsp");
            rd.forward(req,resp);
        }else{
            String[] pathInfoSplit = pathInfo.split("/");

            if (pathInfoSplit.length == 2 && pathInfoSplit[1].equals("crear")){
                rd = req.getRequestDispatcher("/WEB-INF/jsp/usuarios/crear-usuario.jsp");
                rd.forward(req,resp);
            } else if (pathInfoSplit.length == 2) {
                UsuarioDAOImpl uDAO = new UsuarioDAOImpl();

                try {
                    int id = Integer.parseInt(pathInfoSplit[1]);
                    req.setAttribute("usuario",uDAO.find(id));
                    rd = req.getRequestDispatcher("/WEB-INF/jsp/usuarios/detalle-usuario.jsp");
                    rd.forward(req,resp);

                }catch (NumberFormatException e){
                    rd = req.getRequestDispatcher("/WEB-INF/jsp/usuarios/usuarios.jsp");
                    rd.forward(req,resp);
                }

            }else if (pathInfoSplit.length == 3 && pathInfoSplit[1].equals("borrar")) {
                doDelete(req,resp);
            }else if (pathInfoSplit.length == 3 && pathInfoSplit[1].equals("editar")) {
                UsuarioDAOImpl uDAO = new UsuarioDAOImpl();

                try {
                    int id = Integer.parseInt(pathInfoSplit[2]);
                    if (uDAO.find(id).isPresent()) {
                        req.setAttribute("usuario",uDAO.find(id));
                        rd = req.getRequestDispatcher("/WEB-INF/jsp/usuarios/editar-usuario.jsp");
                        rd.forward(req,resp);
                    }else {
                        rd = req.getRequestDispatcher("/WEB-INF/jsp/usuarios/usuarios.jsp");
                        rd.forward(req,resp);
                    }

                }catch (NumberFormatException e) {
                    rd = req.getRequestDispatcher("/WEB-INF/jsp/usuarios/usuarios.jsp");
                    rd.forward(req,resp);
                }

                doPut(req,resp);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String __method__ = req.getParameter("__method__");

        if (__method__ == null){
            UsuarioDAOImpl us = new UsuarioDAOImpl();
            String usuario = req.getParameter("usuario");
            String password = req.getParameter("contrasena");
            String rol = req.getParameter("rol");

            Usuario u = new Usuario();
            u.setUsuario(usuario);
            u.setPassword(password);
            u.setRol(rol);
            us.create(u);

        } else if (__method__.equals("delete")) {
            doDelete(req,resp);
        } else if (__method__.equals("put")) {
            doPut(req,resp);
        }
        resp.sendRedirect(req.getContextPath() + "/tienda/usuarios");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] pathInfoSplit = req.getPathInfo().split("/");
        UsuarioDAOImpl us = new UsuarioDAOImpl();

        if (pathInfoSplit.length == 3 && pathInfoSplit[1].equals("borrar")){
            us.delete(Integer.parseInt(pathInfoSplit[2]));
            resp.sendRedirect(req.getContextPath() + "/tienda/usuarios");
        }else{
            us.delete(Integer.parseInt(req.getParameter("codigo")));
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UsuarioDAOImpl us = new UsuarioDAOImpl();

        Usuario u = new Usuario();

        u.setIdUsuario(Integer.parseInt(req.getParameter("codigo")));
        u.setUsuario(req.getParameter("usuario"));
        u.setPassword(req.getParameter("password"));
        u.setRol(req.getParameter("rol"));

        us.update(u);
    }
}
