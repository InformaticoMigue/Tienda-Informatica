package org.iesbelen.dao;

import org.iesbelen.model.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsuarioDAOImpl extends AbstractDAOImpl implements UsuarioDAO {
    @Override
    public void create(Usuario u) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rsGenKeys = null;

        try {
            conn = connectDB();

            ps = conn.prepareStatement("INSERT INTO usuarios (usuario,password,rol) VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS);

            int idx = 1;
            ps.setString(idx++, u.getUsuario());
            ps.setString(idx++, u.getPassword());
            ps.setString(idx++,u.getRol());

            int rows = ps.executeUpdate();
            if (rows == 0)
                System.out.println("INSERT de fabricante con 0 filas insertadas.");

            rsGenKeys = ps.getGeneratedKeys();
            if (rsGenKeys.next())
                u.setIdUsuario(rsGenKeys.getInt(1));

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, ps, rsGenKeys);
        }
    }

    @Override
    public List<Usuario> getAll() {
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        List<Usuario> list = new ArrayList<>();

        try {
            conn = connectDB();

            ps = conn.prepareStatement("SELECT * FROM usuarios");
            rs = ps.executeQuery();

            while (rs.next()) {
                int index = 1;
                Usuario u = new Usuario();
                u.setIdUsuario(rs.getInt(index++));
                u.setUsuario(rs.getString(index++));
                u.setPassword(rs.getString(index++));
                u.setRol(rs.getString(index++));
                list.add(u);
            }

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            closeDb(conn,ps,rs);
        }

        return list;
    }

    @Override
    public void delete(int pk) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = connectDB();
            ps = conn.prepareStatement("DELETE FROM usuarios WHERE idUsuario = ?");
            ps.setInt(1,pk);
            ps.executeUpdate();

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            closeDb(conn,ps,rs);
        }
    }

    @Override
    public Optional<Usuario> find(int pk) {
        Optional<Usuario> op = Optional.empty();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int index = 1;

        try {
            conn = connectDB();
            ps = conn.prepareStatement("select * from usuarios where idUsuario = ?");
            ps.setInt(1,pk);
            rs = ps.executeQuery();

            if (rs.next()) {
                Usuario u = new Usuario();
                u.setIdUsuario(rs.getInt(index++));
                u.setUsuario(rs.getString(index++));
                u.setPassword(rs.getString(index++));
                u.setRol(rs.getString(index++));
                op = Optional.of(u);
            }

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            closeDb(conn,ps,rs);
        }

        return op;
    }

    @Override
    public void update(Usuario u) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int index = 1;
        try {
            conn = connectDB();
            ps = conn.prepareStatement("UPDATE usuarios SET usuario = ?, password = ?, rol = ? WHERE idUsuario = ?");

            ps.setString(index++, u.getUsuario());
            ps.setString(index++, u.getPassword());
            ps.setString(index++, u.getRol());
            ps.setInt(index++, u.getIdUsuario());

            ps.executeUpdate();

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            closeDb(conn,ps,rs);
        }
    }

    @Override
    public Usuario exists(Usuario u) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int index = 1;
        Usuario usuario = new Usuario();


        try {
            conn = connectDB();
            ps = conn.prepareStatement("SELECT * FROM usuarios where usuario = ? and password = ?");
            ps.setString(1,u.getUsuario());
            ps.setString(2,u.getPassword());

            rs = ps.executeQuery();

            if (rs.next()) {
                usuario.setIdUsuario(rs.getInt(index++));
                usuario.setUsuario(rs.getString(index++));
                usuario.setPassword(rs.getString(index++));
                usuario.setRol(rs.getString(index++));
                return usuario;
            }else{
                return null;
            }

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }finally {
            closeDb(conn,ps,rs);
        }

    }
}
