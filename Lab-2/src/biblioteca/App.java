package biblioteca;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();

        Utilizador u1 = new Utilizador("U001", "Ana Silva", "ana@isptec.co.ao");
        Utilizador u2 = new Utilizador("U002", "Carlos Pedro", "carlos@isptec.co.ao");

        Livro l1 = new Livro("978-1000000001", "Engenharia de Software", 2020, "Sommerville", 2);
        Livro l2 = new Livro("978-1000000002", "Estruturas de Dados", 2019, "Mark Allen", 1);
        Livro l3 = new Livro("978-1000000003", "Bases de Dados", 2021, "Silberschatz", 1);
        Livro l4 = new Livro("978-1000000004", "Redes de Computadores", 2018, "Tanenbaum", 1);

        biblioteca.registarUtilizador(u1);
        biblioteca.registarUtilizador(u2);

        biblioteca.registarLivro(l1);
        biblioteca.registarLivro(l2);
        biblioteca.registarLivro(l3);
        biblioteca.registarLivro(l4);

        System.out.println("=== REGISTO DE UTILIZADORES ===");
        for (Utilizador u : biblioteca.getUtilizadores()) {
            System.out.println(u);
        }

        System.out.println("\n=== REGISTO DE LIVROS ===");
        for (Livro l : biblioteca.getLivros()) {
            System.out.println(l);
        }

        System.out.println("\n=== REALIZACAO DE EMPRESTIMOS ===");
        Emprestimo e1 = biblioteca.registarEmprestimo(u1, l1, LocalDate.now(), LocalDate.now().plusDays(7));
        Emprestimo e2 = biblioteca.registarEmprestimo(u1, l2, LocalDate.now(), LocalDate.now().plusDays(5));
        Emprestimo e3 = biblioteca.registarEmprestimo(u1, l3, LocalDate.now(), LocalDate.now().plusDays(3));
        System.out.println(e1);
        System.out.println(e2);
        System.out.println(e3);

        try {
            biblioteca.registarEmprestimo(u1, l4, LocalDate.now(), LocalDate.now().plusDays(4));
        } catch (Exception ex) {
            System.out.println("Falha esperada (limite de ativos): " + ex.getMessage());
        }

        Emprestimo e4 = biblioteca.registarEmprestimo(u2, l1, LocalDate.now(), LocalDate.now().plusDays(2));
        System.out.println(e4);

        try {
            biblioteca.registarEmprestimo(u2, l1, LocalDate.now(), LocalDate.now().plusDays(2));
        } catch (Exception ex) {
            System.out.println("Falha esperada (sem exemplares): " + ex.getMessage());
        }

        System.out.println("\n=== DEVOLUCAO DE LIVRO ===");
        biblioteca.devolverLivro(e2);
        System.out.println("Emprestimo devolvido: " + e2.getId() + " | estado=" + e2.getEstado());

        System.out.println("\n=== ATUALIZACAO DE ESTADOS ===");
        biblioteca.atualizarEstadosEmprestimos(LocalDate.now().plusDays(10));
        for (Emprestimo e : biblioteca.getEmprestimos()) {
            System.out.println(e.getId() + " -> " + e.getEstado());
        }

        System.out.println("\n=== DISPONIBILIDADE FINAL DOS LIVROS ===");
        for (Livro l : biblioteca.getLivros()) {
            System.out.println(l.getTitulo() + ": " + l.getExemplaresDisponiveis() + "/" + l.getTotalExemplares());
        }
    }
}
