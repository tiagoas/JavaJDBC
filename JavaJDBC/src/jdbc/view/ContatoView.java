package jdbc.view;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import jdbc.dao.ProdutoDao;
import jdbc.modelo.Produto;

public class ContatoView {

    public static void main(String[] args) {

        String menu = "1. Inserir produto\n"
                + "2. Listar produtos\n"
                + "3. Sair";

        String strEscolha = JOptionPane.showInputDialog(null, menu);

        while (strEscolha.contains("3") == false) {

            switch (strEscolha) {

                case "1": {
                    Produto p = new Produto();
                    p.setNome("Mouse");
                    p.setPeso(0.2F);
                    p.setPreco(200.0F);

                    ProdutoDao prodDao = new ProdutoDao();
                    boolean retorno = prodDao.insere(p);

                    if (retorno == false) {
                        System.out.println("Erro ao inserir!");
                    } else {
                        System.out.println("Insercao realizada com sucesso!");
                    }
                    
                    break;
                }
                
                case "2":{
                    ProdutoDao prodDao = new ProdutoDao();
                    ArrayList<Produto> arlProd = prodDao.consulta();
                    for(int i=0; i<arlProd.size(); i++){
                        Produto p = arlProd.get(i);
                        JOptionPane.showMessageDialog(null, p.getNome());
                    }
                    
                    break;
                    
                } 
            }
            
            strEscolha = JOptionPane.showInputDialog(null, menu);

        }

    }

}
