package controller;

import model.Categoria;

import java.util.HashSet;

public class GerenciamentoCategorias {

    HashSet<Categoria> categorias = new HashSet<>();

    public void setCategorias(Categoria categoria) {

        /*
        categorias.forEach((item) -> {
            if(item.equals(categoria)){
                ;
            }
        });
        */
        categorias.add(categoria);
    }
    public HashSet<Categoria> getCategorias() {
        return categorias;
    }


}
