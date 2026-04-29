package biblioteca.entities;

public class Livro {
    private final String isbn;
    private final String titulo;
    private final int anoPublicacao;
    private final String autor;
    private final int totalExemplares;
    private int exemplaresDisponiveis;

    public Livro(String isbn, String titulo, int anoPublicacao, String autor, int totalExemplares) {
        if (totalExemplares < 1) {
            throw new IllegalArgumentException("Um livro deve ter pelo menos 1 exemplar.");
        }

        this.isbn = isbn;
        this.titulo = titulo;
        this.anoPublicacao = anoPublicacao;
        this.autor = autor;
        this.totalExemplares = totalExemplares;
        this.exemplaresDisponiveis = totalExemplares;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getAnoPublicacao() {
        return anoPublicacao;
    }

    public String getAutor() {
        return autor;
    }

    public int getTotalExemplares() {
        return totalExemplares;
    }

    public int getExemplaresDisponiveis() {
        return exemplaresDisponiveis;
    }

    public boolean verificarDisponibilidade() {
        return exemplaresDisponiveis > 0;
    }

    public void reduzirDisponibilidade() {
        if (!verificarDisponibilidade()) {
            throw new IllegalStateException("Nao ha exemplares disponiveis para o livro: " + titulo);
        }
        exemplaresDisponiveis--;
    }

    public void reporDisponibilidade() {
        if (exemplaresDisponiveis >= totalExemplares) {
            throw new IllegalStateException("A disponibilidade ja esta no maximo para o livro: " + titulo);
        }
        exemplaresDisponiveis++;
    }

    @Override
    public String toString() {
        return "Livro{isbn='" + isbn + "', titulo='" + titulo + "', autor='" + autor + "', disponiveis="
                + exemplaresDisponiveis + "/" + totalExemplares + "}";
    }
}
