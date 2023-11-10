package org.iesbelen.dto;

import org.iesbelen.dao.FabricanteDAO;
import org.iesbelen.dao.FabricanteDAOImpl;
import org.iesbelen.model.Fabricante;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class FabricanteDTO extends Fabricante {

    private int cantProductos;

    public FabricanteDTO(Fabricante f){
        setIdFabricante(f.getIdFabricante());
        setNombre(f.getNombre());
    }
    public int getCantProductos() {
        return cantProductos;
    }
    public void setCantProductos(){
        FabricanteDAO fDao = new FabricanteDAOImpl();
        if (fDao.getCountProductos(this.getIdFabricante()).isPresent()) {
            this.cantProductos = fDao.getCountProductos(this.getIdFabricante()).get();
        }
    }
}
