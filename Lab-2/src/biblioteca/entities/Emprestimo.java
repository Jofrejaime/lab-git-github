package biblioteca.entities;

import java.time.LocalDate;
import java.util.UUID;

public class Emprestimo {
    private final String id;
    private final LocalDate dataInicio;
    private final LocalDate dataPrevistaDevolucao;
    private LocalDate dataDevolucao;
    private EstadoEmprestimo estado;
    private final Utilizador utilizador;
    private final Livro livro;

    public Emprestimo(LocalDate dataInicio, LocalDate dataPrevistaDevolucao, Utilizador utilizador, Livro livro) {
        if (dataPrevistaDevolucao.isBefore(dataInicio)) {
            throw new IllegalArgumentException("A data prevista de devolucao nao pode ser anterior a data de inicio.");
        }

        this.id = UUID.randomUUID().toString();
        this.dataInicio = dataInicio;
        this.dataPrevistaDevolucao = dataPrevistaDevolucao;
        this.utilizador = utilizador;
        this.livro = livro;
        this.estado = EstadoEmprestimo.ATIVO;
    }

    public String getId() {
        return id;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public LocalDate getDataPrevistaDevolucao() {
        return dataPrevistaDevolucao;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public EstadoEmprestimo getEstado() {
        return estado;
    }

    public Utilizador getUtilizador() {
        return utilizador;
    }

    public Livro getLivro() {
        return livro;
    }

    public void registar() {
        estado = EstadoEmprestimo.ATIVO;
    }

    public void devolver(LocalDate data) {
        if (estado == EstadoEmprestimo.DEVOLVIDO) {
            throw new IllegalStateException("Este emprestimo ja foi devolvido.");
        }

        dataDevolucao = data;
        estado = EstadoEmprestimo.DEVOLVIDO;
    }

    public void verificarAtraso(LocalDate dataAtual) {
        if (estado == EstadoEmprestimo.ATIVO && dataAtual.isAfter(dataPrevistaDevolucao)) {
            estado = EstadoEmprestimo.ATRASADO;
        }
    }

    @Override
    public String toString() {
        return "Emprestimo{id='" + id + "', utilizador='" + utilizador.getNomeCompleto() + "', livro='"
                + livro.getTitulo() + "', inicio=" + dataInicio + ", prevista=" + dataPrevistaDevolucao
                + ", estado=" + estado + "}";
    }
}
