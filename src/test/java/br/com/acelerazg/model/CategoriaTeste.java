package br.com.acelerazg.model;

import br.com.acelerazg.controller.GerenciamentoCategorias;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalTime;

public class CategoriaTeste {

    private static GerenciamentoCategorias categorias;

    @Before
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
