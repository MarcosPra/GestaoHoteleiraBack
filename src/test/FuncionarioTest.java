package test;

import controller.FuncionarioController;

import java.time.LocalDate;

public class FuncionarioTest {


    public static String testeCadastro(
            String nomeCompleto,
            LocalDate dataNascimento,
            String documento,
            String pais,
            String estado,
            String cidade,
            String cargo,
            Double salario

    )
    {
        FuncionarioController funcionarioController = new FuncionarioController();
        String resposta = funcionarioController.cadastrar(

                nomeCompleto,
                dataNascimento,
                documento,
                pais,
                estado,
                cidade,
                cargo,
                salario
        );
        return resposta;
    }

    public static String testeListagem() {
        FuncionarioController funcionarioController = new FuncionarioController();
        String resposta = funcionarioController.listar();
        return resposta;
    }

    public  static String testeAlteracao(
            Long id,
            String cargo,
            Double salario
    ) {
        FuncionarioController funcionarioController = new FuncionarioController();
        String resposta = funcionarioController.alterar(
                id,
                cargo,
                salario
        );
        return resposta;
    }

    public static String testeExclusao(Long id) {
        FuncionarioController funcionarioController = new FuncionarioController();
        String resposta = funcionarioController.excluir(id);
        return resposta;
    }
}
