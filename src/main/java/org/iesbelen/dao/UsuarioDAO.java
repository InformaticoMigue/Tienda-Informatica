package org.iesbelen.dao;

import org.iesbelen.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioDAO {
    public void create(Usuario u);
    public List<Usuario> getAll();
    public void delete(int pk);
    public Optional<Usuario> find(int pk);
    public void update(Usuario u);

}
