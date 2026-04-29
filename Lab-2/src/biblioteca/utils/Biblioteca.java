package biblioteca.utils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import biblioteca.entities.Emprestimo;
import biblioteca.entities.EstadoEmprestimo;
import biblioteca.entities.Livro;
import biblioteca.entities.Utilizador;

public class Biblioteca {
    private final List<Utilizador> utilizadores;
    private final List<Livro> livros;
    private final List<Emprestimo> emprestimos;

    public Biblioteca() {
        this.utilizadores = new ArrayList<>();
        this.livros = new ArrayList<>();
        this.emprestimos = new ArrayList<>();
    }

    public List<Utilizador> getUtilizadores() {
        return Collections.unmodifiableList(utilizadores);
    }

    public List<Livro> getLivros() {
        return Collections.unmodifiableList(livros);
    }

    public List<Emprestimo> getEmprestimos() {
        return Collections.unmodifiableList(emprestimos);
    }

    public void registarUtilizador(Utilizador utilizador) {
        utilizadores.add(utilizador);
    }

    public void registarLivro(Livro livro) {
        livros.add(livro);
    }

    public Emprestimo registarEmprestimo(Utilizador utilizador, Livro livro, LocalDate inicio, LocalDate prevista) {
        if (!utilizadores.contains(utilizador)) {
            throw new IllegalArgumentException("Utilizador nao registado na biblioteca.");
        }

        if (!livros.contains(livro)) {
            throw new IllegalArgumentException("Livro nao registado na biblioteca.");
        }

        if (!utilizador.podeRequisitar()) {
            throw new IllegalStateException("Utilizador atingiu o limite de 3 emprestimos ativos.");
        }

        if (!livro.verificarDisponibilidade()) {
            throw new IllegalStateException("Nao ha exemplares disponiveis para este livro.");
        }

        Emprestimo emprestimo = new Emprestimo(inicio, prevista, utilizador, livro);
        emprestimo.registar();

        livro.reduzirDisponibilidade();
        utilizador.adicionarEmprestimo(emprestimo);
        emprestimos.add(emprestimo);

        return emprestimo;
    }

    public void devolverLivro(Emprestimo emprestimo) {
        if (!emprestimos.contains(emprestimo)) {
            throw new IllegalArgumentException("Emprestimo nao encontrado no historico da biblioteca.");
        }

        if (emprestimo.getEstado() == EstadoEmprestimo.DEVOLVIDO) {
            throw new IllegalStateException("Este emprestimo ja foi devolvido.");
        }

        emprestimo.devolver(LocalDate.now());
        emprestimo.getLivro().reporDisponibilidade();
    }

    public void atualizarEstadosEmprestimos(LocalDate dataAtual) {
        for (Emprestimo emprestimo : emprestimos) {
            emprestimo.verificarAtraso(dataAtual);
        }
    }
}
