package model;

import controller.GerenciamentoCategorias;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

public class CategoriaTeste {

    private static GerenciamentoCategorias categorias;

    @BeforeAll
    public void instanciarCategoria(){
        categorias = new GerenciamentoCategorias();
    }

    @Test
    public void criarCategoria(){
        Categoria categoria = new Categoria();

        categoria.setCategoria("PESSOAL");

        String expect = "PESSOAL";
        String result = categoria.getCategoria();

        System.out.println("Expect: " + expect);
        System.out.println("Result: " + result);
    }

}
