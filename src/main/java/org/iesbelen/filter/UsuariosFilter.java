package org.iesbelen.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.iesbelen.model.Usuario;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;

@WebFilter(
        urlPatterns = "/tienda/usuarios/*",
        initParams = @WebInitParam(name = "acceso-concedido-a-rol", value = "administrador")
)
public class UsuariosFilter implements Filter {

    private String rolAcceso;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

        rolAcceso = filterConfig.getInitParameter("acceso-concedido-a-rol");
 /*
TO-DO Leer de filterConfig el init-param acceso-concedido-a-rol y guardar en variable privada
*/
    }


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        HttpSession session = request.getSession();
        Usuario us = (Usuario) session.getAttribute("usuarioLogin");

        String[] pathInfo = request.getPathInfo().split("/");

        if (pathInfo.length == 0) {
            filterChain.doFilter(servletRequest,servletResponse);
        }else if (us == null && (pathInfo[1].equals("crear") || pathInfo[1].equals("editar") || pathInfo[1].equals("borrar"))){
            response.sendRedirect(request.getContextPath() + "/tienda/login");
        }else {
            filterChain.doFilter(servletRequest,servletResponse);
        }


    }



 /*
TO-DO Control de sesión con usuario con rol de administrador.
 si no existe bean usuario en sesión o el bean es de rol distinto de administrador realiza un response.sendRedirect LOGIN
 si el bean de usuario en sesión es de tipo administrador hacer

chain.doFilter(request, response);
*/


    @Override
    public void destroy() {

    }
}