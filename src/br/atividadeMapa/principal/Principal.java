package br.atividadeMapa.principal;

import br.atividadeMapa.controle.ProjetoDAO;
import br.atividadeMapa.controle.ProjetoImpl;
import br.atividadeMapa.exceptions.DadoConsultadoException;
import br.atividadeMapa.modelo.Projeto;


import java.text.ParseException;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        ProjetoDAO dao = new ProjetoImpl();

        int opcao;
        do{
            System.out.println("=== REGISTRO DE PROJETOS ===");
            System.out.println("------------------------------");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Alterar");
            System.out.println("3 - Listar");
            System.out.println("4 - Localizar por nome");
            System.out.println("5 - Excluir");
            System.out.println("0 - Sair");
            System.out.println("------------------------------");

            Scanner scanner = new Scanner(System.in);
            opcao = scanner.nextInt();

            try{
                switch (opcao){
                    case 1:
                        // Cadastrar Projetos

                        Projeto projeto = new Projeto();
                        System.out.println("==== CADASTRO DE PROJETOS ====");
                        System.out.println("------------------------------");
                        projeto.setNome(coletarTexto("Digite o nome do projeto"));

                        projeto.setObjetivo(coletarTexto("Digite os objetivos do projetos"));
                        projeto.setNecessidades(coletarTexto("Digite as necessidades do projeto"));
                        projeto.setDataInicio(coletarTexto("Digite a data de ínicio do projeto"));
                        projeto.setDataFinal(coletarTexto("Digite a data final do projeto"));
                        projeto.setStatus(coletarTexto("Digite o status do projeto"));
                        System.out.println("------------------------------");
                        dao.adicionar(projeto);

                        break;

                    case 2:
                        // Alterar Produtos
                        Projeto projetoNovo = new Projeto();

                        System.out.println("==== ALTERAR PROJETO ====");
                        System.out.println("------------------------------");
                        String nomeConsultaAlterar = coletarTexto("Digite o nome do projeto que está buscando para alterar");

                        projetoNovo.setNome(coletarTexto("Digite o nome do projeto"));
                        projetoNovo.setObjetivo(coletarTexto("Digite os objetivos do projeto"));
                        projetoNovo.setNecessidades(coletarTexto("Digite as necessidades do projeto"));
                        projetoNovo.setDataInicio(coletarTexto("Digite a data de início do projeto"));
                        projetoNovo.setDataFinal(coletarTexto("Digite a data final do projeto"));
                        projetoNovo.setStatus(coletarTexto("Digite o status do projeto"));
                        System.out.println("------------------------------");


                        dao.alterar(nomeConsultaAlterar, projetoNovo);

                        break;

                    case 3:
                        // Listar Produtos

                        System.out.println(dao.listar());

                        break;

                    case 4:
                        // Localizar por Nome
                        System.out.println("==== PESQUISAR POR NOME ====");
                        System.out.println("------------------------------");
                        String nomeConsultado = coletarTexto("Digite o nome do projeto que está buscando");
                        Projeto projetoEncontrado = dao.consultarPorNome(nomeConsultado);
                        System.out.println(projetoEncontrado);

                        break;

                    case 5:
                        //Excluir Produto
                        System.out.println("=== EXCLUIR PROJETO ===");
                        System.out.println("------------------------------");
                        String nomeConsultaExcluir = coletarTexto("Digite o nome do projeto que está buscando para excluir");

                        dao.excluir(nomeConsultaExcluir);


                        break;

                    case 0:
                        System.out.println("Saindo...");
                        break;
                    default:
                        System.out.println("Opção invalida");

                }
            }catch (InputMismatchException e){
                System.out.println("Não foi possivel registrar corretamente a sua opção.");
            }catch (ParseException | DateTimeParseException e){
                System.out.println("Não foi possivel converter a data, tente informar dd/mm/aaaa");
            }catch (DadoConsultadoException e){
                System.out.println(e.getMessage());
            }
            coletarTexto("\nPressione enter para voltar/sair");
        }while (opcao != 0);
    }

    private static String coletarTexto(String descricao){
        Scanner scanner = new Scanner(System.in);
        System.out.println(descricao);
        return scanner.nextLine();
    }

}
