package br.atividadeMapa.controle;

import br.atividadeMapa.exceptions.DadoConsultadoException;
import br.atividadeMapa.modelo.Projeto;

import java.text.ParseException;
import java.util.List;

public interface ProjetoDAO {

    void adicionar(Projeto projeto);
    List<Projeto> listar();

    Projeto consultarPorNome(String nome) throws DadoConsultadoException;
    Projeto alterar(String nome, Projeto projeto) throws DadoConsultadoException, ParseException;

    void excluir(Projeto projeto) throws DadoConsultadoException, UnsupportedOperationException;
    void excluir(String nome) throws DadoConsultadoException, UnsupportedOperationException;

}
