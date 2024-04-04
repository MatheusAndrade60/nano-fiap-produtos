package br.com.fiap.produtos.view;

import br.com.fiap.produtos.model.Categoria;
import br.com.fiap.produtos.model.Produto;
import br.com.fiap.produtos.repository.ProdutoCollectionRepository;
import javax.swing.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ProdutoView {
    public static Produto form(Produto produto) {

        Categoria categoria = null;

        //Método que só saira da condicional quando a categoria não for mais null
        do{
           categoria = CategoriaView.select(produto.getCategoria());
        }while (categoria == null);

        String nome = "";

        //validação para não receber um nome vazio
        do {
            nome = JOptionPane.showInputDialog(null, "Informe o nome do produto", produto.getNome());
        }while (nome.equals(""));

        String descricao = "";

        //validação para não receber uma descricão vazia
        do {
            descricao = JOptionPane.showInputDialog(null, "Informe a descricao do produto", produto.getDescricao());
        }while (descricao.equals(""));

        double preco = 0;

        //validação para não receber uma preco vazia
        do {
            try {
                preco = Double.parseDouble(JOptionPane.showInputDialog(null, "Informe a preço do produto", produto.getPreco()));
            }catch (Exception e){
                preco = 0;
            }

        }while (preco <= 0);

        Produto ret = produto;
        ret.setCategoria(categoria)
                .setNome(nome)
                .setDescricao(descricao)
                .setDataDeCadastro(LocalDateTime.now())
                .setPreco(BigDecimal.valueOf(preco));

        return ret;
    }

    public static Produto formById(Produto produto) {

        Long id = 0L;

        do {
            try {
                id = Long.parseLong(JOptionPane.showInputDialog(null, "Informe o id do produto", produto.getId()));
            }catch (Exception e){
                id = 0L;
            }

        }while (id <= 0L);

        Produto retById = produto;
        retById.setId(id);

        return retById;
    }

    //Método que retorna uma mensagem que o produto com salvo
    public void sucesso(){
        JOptionPane.showMessageDialog(null, "O produto foi salva com sucesso!");
    }

    //Método que recebe um produto como parametro e retorna uma mensagem que o produto com salvo
    public static void sucesso(Produto produto){
        JOptionPane.showMessageDialog(null, "O produto " + produto.getNome() + " foi salva com sucesso!");
    }

    public static Produto select(Produto produto){
        Produto ret = (Produto) JOptionPane.showInputDialog(
                null,
                "Selecione uma opção",
                "Menu",
                JOptionPane.QUESTION_MESSAGE,
                null, //icone
                ProdutoCollectionRepository.findAll().toArray(), //Número da opção
                produto == null ? 1 : produto);
        return ret;
    }

    //Método para exibir informações de um produto
    public static void show(Produto produto) {
        System.out.println(produto);
        String textoFormatado = String.format(
            "Produto: " + produto.getNome() + System.lineSeparator() +
            "Descrição: " + produto.getDescricao() + System.lineSeparator() +
            "Categoria: " + produto.getCategoria() + System.lineSeparator() +
            "Preço: " + produto.getPreco() + System.lineSeparator()
        );
        JOptionPane.showMessageDialog(null, textoFormatado);
    }

    //Método para fazer a atualização de uma produto
    public static void update(Produto produto){
        form(produto);
        sucesso(produto);
        show(produto);
    }
}
