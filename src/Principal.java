import controller.ExemploController;
import controller.FuncionarioController;
import dao.FuncionarioDAO;
import model.*;
import model.enums.TipoMidia;
import test.ExemploTest;
import test.FuncionarioTest;

import java.time.LocalDate;
import java.util.Date;

import static test.FuncionarioTest.*;

public class Principal {
    public static void main(String[] args) {
        System.out.println("Protótipo de Aplicação para Gestão Hoteleira");

//        System.out.println(testeCadastro(
//                "Manoel da Silva",
//                LocalDate.of(1960, 01, 02),
//                "789-888",
//                "Brasil",
//                "SC",
//                "Jaguaruna",
//                "Serviços Gerais",
//                3000.0
//        ));
//        System.out.println("Teste de Cadastro:");
//        String respostaCadastro = testeCadastro(
//                "Nome Teste",
//                LocalDate.of(1990, 1, 1),
//                "123456789",
//                "Brasil",
//                "SP",
//                "Sao Paulo",
//                "Desenvolvedor",
//                5000.00
//        );
//        System.out.println(respostaCadastro);
//
//        FuncionarioController funcionarioController = new FuncionarioController();

////        // Escolha o id a ser alterado
//        Long idParaAlterar = 20L;
//
//        // Valores para a alteração
//        String novoCargo = "Novo Cargo";
//        Double novoSalario = 7000.0;
//
//        // metodo de exclusao alteração
//        String respostaAlteracao = funcionarioController.alterar(idParaAlterar, novoCargo, novoSalario);
//
//        // Exibição de resposta
//        System.out.println(respostaAlteracao);
//    }

//        FuncionarioController funcionarioController = new FuncionarioController();
//
//        // Escolha o id a ser excluido
//        Long idParaExcluir = 11L;
//
//        // metodo de exclusao
//        String respostaExclusao = funcionarioController.excluir(idParaExcluir);
//
//        // Exibição de resposta
//        System.out.println(respostaExclusao);

        FuncionarioController funcionarioController = new FuncionarioController();

        // Chame o método de listagem
        String respostaListagem = funcionarioController.listar();

        // Exiba a resposta
        System.out.println(respostaListagem);
//
    }
}