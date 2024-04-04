package br.com.fiap.produtos.repository;

import br.com.fiap.produtos.model.Categoria;
import br.com.fiap.produtos.model.Produto;

import javax.swing.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Vector;

public class ProdutoCollectionRepository {
    private static List<Produto> produtos;

    static{
        produtos = new Vector<>();

        Produto celular = new Produto();
        celular.setNome("Iphone 14")
                .setDescricao("Aparelho da Apple")
                .setCategoria(CategoriaCollectionRepository.findById(2l))
                .setDataDeCadastro(LocalDateTime.now())
                .setPreco(BigDecimal.valueOf((12000)));
        save(celular);
    }

    public static List<Produto> findAll() {
        return produtos;
    }

    public static Produto save(Produto produto){
        if (!produtos.contains(produto)){
            produto.setId((long) (produtos.size()+1));
            produtos.add(produto);
            return produto;
        }else {
            JOptionPane.showMessageDialog(null, "Já existe um produto cadastrado com esse nome");
            return null;
        }
    }

    public static List<Produto> findByCategoria(Categoria categoria) {
        return produtos.stream().filter(p->p.getCategoria().equals(categoria)).toList();
    }

    public static List<Produto> findById(Long id) {
        List<Produto> result = new ArrayList<>();
        for (Produto produto : produtos) {
            if (produto.getId().equals(id)) {
                result.add(produto);
            }
        }
        return result;
    }
}
