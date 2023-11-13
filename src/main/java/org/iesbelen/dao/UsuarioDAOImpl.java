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
            ps.executeQuery();

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            closeDb(conn,ps,rs);
        }
    }

    @Override
    public Optional<Usuario> find(int pk) {
        return Optional.empty();
    }

    @Override
    public void update(Usuario u) {

    }
}
