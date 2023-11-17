package org.iesbelen.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
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
        RequestDispatcher rd = null;

        if (pathInfo == null || pathInfo.equals("/")) {
            UsuarioDAOImpl uDAO = new UsuarioDAOImpl();
            req.setAttribute("listaUsuarios",uDAO.getAll());

            rd = req.getRequestDispatcher("/WEB-INF/jsp/usuarios/usuarios.jsp");
        }else{
            String[] pathInfoSplit = pathInfo.split("/");

            if (pathInfoSplit.length == 2 && pathInfoSplit[1].equals("login")){
                rd = req.getRequestDispatcher("/WEB-INF/jsp/usuarios/login.jsp");

            }else if (pathInfoSplit.length == 2 && pathInfoSplit[1].equals("crear")){
                rd = req.getRequestDispatcher("/WEB-INF/jsp/usuarios/crear-usuario.jsp");

            } else if (pathInfoSplit.length == 2 ) {
                UsuarioDAOImpl uDAO = new UsuarioDAOImpl();

                try {
                    int id = Integer.parseInt(pathInfoSplit[1]);
                    req.setAttribute("usuario", uDAO.find(id));
                    rd = req.getRequestDispatcher("/WEB-INF/jsp/usuarios/detalle-usuario.jsp");

                } catch (NumberFormatException e) {
                    rd = req.getRequestDispatcher("/WEB-INF/jsp/usuarios/usuarios.jsp");
                }

            }else if (pathInfoSplit.length == 3 && pathInfoSplit[1].equals("editar")) {
                UsuarioDAOImpl uDAO = new UsuarioDAOImpl();

                try {
                    int id = Integer.parseInt(pathInfoSplit[2]);
                    req.setAttribute("usu",uDAO.find(id));
                    rd = req.getRequestDispatcher("/WEB-INF/jsp/usuarios/editar-usuario.jsp");

                }catch (NumberFormatException e) {
                    rd = req.getRequestDispatcher("/WEB-INF/jsp/usuarios/usuarios.jsp");
                }
            }else if (pathInfoSplit.length == 3 && pathInfoSplit[1].equals("borrar")) {
                 doDelete(req,resp);
            }
        }
        if (rd != null) {
            rd.forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String __method__ = req.getParameter("__method__");
        UsuarioDAOImpl us = new UsuarioDAOImpl();
        String[] __url__ = req.getPathInfo().split("/");

         if (__url__[1].equals("login")){
             Usuario auxUser = new Usuario();
             auxUser.setUsuario(req.getParameter("username"));
             auxUser.setPassword(req.getParameter("password"));

             Usuario real = us.exists(auxUser);

             if (real != null) {
                 HttpSession session = req.getSession(true);
                 session.setAttribute("usuarioLogin", real);
             }
             resp.sendRedirect(req.getContextPath());

         }else if (__url__[1].equals("logout")){
             HttpSession session= req.getSession();
             session.invalidate();
             resp.sendRedirect(req.getContextPath());
         }else {
             if (__method__ == null) {
                 String usuario = req.getParameter("usuario");
                 String password = req.getParameter("contrasena");
                 String rol = req.getParameter("rol");

                 if (!usuario.isEmpty() && !password.isEmpty()) {
                     Usuario u = new Usuario();
                     u.setUsuario(usuario);
                     u.setPassword(password);
                     u.setRol(rol);
                     us.create(u);
                 }

             }else if (__method__.equals("delete")) {
                 doDelete(req, resp);
             }else if (__method__.equals("put")) {
                 doPut(req, resp);
             }
             resp.sendRedirect(req.getContextPath() + "/tienda/usuarios");
         }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] pathInfoSplit = req.getPathInfo().split("/");
        UsuarioDAOImpl us = new UsuarioDAOImpl();
        String codigo = req.getParameter("codigo");

        if (codigo == null){
            us.delete(Integer.parseInt(pathInfoSplit[2]));
            resp.sendRedirect(req.getContextPath() + "/tienda/usuarios");
        }else{
            us.delete(Integer.parseInt(codigo));
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
