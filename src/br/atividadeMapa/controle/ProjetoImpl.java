package br.atividadeMapa.controle;

import br.atividadeMapa.exceptions.DadoConsultadoException;
import br.atividadeMapa.modelo.Projeto;

import java.text.ParseException;
import java.util.*;

public class ProjetoImpl implements ProjetoDAO {

    private static Set<Projeto> projetos = new HashSet<>();

    @Override
    public void adicionar(Projeto projeto){

        projetos.add(projeto);
    }


    @Override
    public List<Projeto> listar(){
        System.out.println("=== Lista de Projetos ===");
        List<Projeto> projetoList = new ArrayList<>();

        projetoList.addAll(projetos);

        return projetoList;
    }


    @Override
    public Projeto consultarPorNome(String nome) throws DadoConsultadoException{

        for(Projeto projeto : projetos){
            if (nome.equalsIgnoreCase(projeto.getNome())){
                return projeto;
            }
        }
        throw new DadoConsultadoException("Não há projeto com esse nome: "+nome);

    }

    @Override
    public Projeto alterar(String nome,  Projeto projeto) throws DadoConsultadoException, ParseException {

        Projeto projetoEncontrado =  consultarPorNome(nome);

        projetoEncontrado.setNome(projeto.getNome());
        projetoEncontrado.setObjetivo(projeto.getObjetivo());
        projetoEncontrado.setNecessidades(projeto.getNecessidades());
        projetoEncontrado.setDataInicio(projeto.getDataInicio());
        projetoEncontrado.setDataFinal(projeto.getDataFinal());
        projetoEncontrado.setStatus(projeto.getStatus());


        return projetoEncontrado;
    }

    @Override
    public void excluir(Projeto projeto) throws DadoConsultadoException, UnsupportedOperationException{

        try {
            if (projetos.contains(projeto)) {
                projetos.remove(projeto);

                return;
            }
        }catch (Exception e){
            throw new DadoConsultadoException("Não foi localizado nenhum projeto para exclusão");
        }

    }

    @Override
    public void excluir(String nome) throws DadoConsultadoException, UnsupportedOperationException{

            Projeto projeto = consultarPorNome(nome);

            this.excluir(projeto);

        }

    }
