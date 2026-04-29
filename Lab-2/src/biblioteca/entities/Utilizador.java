package biblioteca.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Utilizador {
    private final String id;
    private final String nomeCompleto;
    private final String contacto;
    private final List<Emprestimo> emprestimos;

    public Utilizador(String id, String nomeCompleto, String contacto) {
        this.id = id;
        this.nomeCompleto = nomeCompleto;
        this.contacto = contacto;
        this.emprestimos = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public String getContacto() {
        return contacto;
    }

    public List<Emprestimo> getEmprestimos() {
        return Collections.unmodifiableList(emprestimos);
    }

    public void adicionarEmprestimo(Emprestimo emprestimo) {
        emprestimos.add(emprestimo);
    }

    public int contarEmprestimosAtivos() {
        int total = 0;
        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getEstado() == EstadoEmprestimo.ATIVO || emprestimo.getEstado() == EstadoEmprestimo.ATRASADO) {
                total++;
            }
        }
        return total;
    }

    public boolean podeRequisitar() {
        return contarEmprestimosAtivos() < 3;
    }

    @Override
    public String toString() {
        return "Utilizador{id='" + id + "', nome='" + nomeCompleto + "', contacto='" + contacto + "'}";
    }
}
