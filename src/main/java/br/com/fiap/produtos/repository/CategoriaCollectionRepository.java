package br.com.fiap.produtos.repository;

import br.com.fiap.produtos.model.Categoria;

import javax.swing.*;
import java.util.List;
import java.util.Vector;

public class CategoriaCollectionRepository {

    private static List<Categoria> categorias;

    //Assim que a classe for iniciada ela já ira iniciar com alguns valores padrões
    static{
        categorias = new Vector<>(); //Vector é uma class da familia das coleções do Java

        //Criando instancias da classe Categoria
        Categoria eletronicos = new Categoria(1l,"Eletrônicos");
        Categoria celulares = new Categoria(2l,"Celulares");
        Categoria livros = new Categoria(3l,"Livros");

        //Adicionando categorias ao vetor
        categorias.add(eletronicos);
        categorias.add(celulares);
        categorias.add(livros);
    }

    public static List<Categoria> findAll(){
        return categorias;
    }

    public static Categoria findById(Long id){
                         //transformo em stream
        return categorias.stream()
                //faço uma filtragem onde o c = c, pega um Id e tem que ser igual ao Id que eu recebi por parametro
                .filter(c->c.getId().equals(id))
                //Pego o primeiro, pois so tem um objeto com aquele Id
                .findFirst()
                //Este é um optional, ou ele vai retornar o Id ou ele ira retornar null
                .orElse(null);
    }

    public static List<Categoria> findByNome(String nome){
        return categorias.stream()
            .filter(c->c.getNome().equalsIgnoreCase(nome))
            .toList();
    }

    //Metodo que salva uma categoria
    public static Categoria save(Categoria categoria){
        //Se não houver a categoria passada ele ira criar
        if (!categorias.contains(categoria)){
            //fazendo com que a nova categoria receba uma id
            categoria.setId((long) categorias.size() +1);
            categorias.add(categoria);
            return categoria;
        }
        //retorna uma mensagem informativa
        else{
            JOptionPane.showMessageDialog(null,"Já existe uma categoria com esse nome");
            return null;
        }
    }
}
