package br.com.fiap.produtos.view;

import br.com.fiap.produtos.model.Categoria;
import br.com.fiap.produtos.repository.CategoriaCollectionRepository;

import javax.swing.*;

public class CategoriaView {
    static CategoriaCollectionRepository repository;

    public static Categoria select(Categoria categoria){
        Categoria ret = (Categoria) JOptionPane.showInputDialog(
                null,
                "Selecione uma opção",
                "Menu",
                JOptionPane.QUESTION_MESSAGE,
                null, //icone
                repository.findAll().toArray(), //Número da opção
                categoria == null ? 1 : categoria);
        return ret;
    }

    public void sucesso(){
        JOptionPane.showMessageDialog(null, "A categoria foi salva com sucesso!");
    }

    public void sucesso(Categoria categoria){
        JOptionPane.showMessageDialog(null, "A Categoria " + categoria.getNome() + " foi salva com sucesso!");
    }

    public static Categoria form(Categoria categoria){
        String nome = JOptionPane.showInputDialog(null, "Informe o nome da categoria", categoria != null ? categoria.getNome() : "");
        return new Categoria(nome);
    }
}
