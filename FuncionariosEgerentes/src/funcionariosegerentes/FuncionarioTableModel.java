package funcionariosegerentes;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Déleo Cambula
 */

public class FuncionarioTableModel extends AbstractTableModel {
    private List<Funcionario> funcionarios = new ArrayList<>();
    private String[] colunas = {"Tipo", "Nome", "Salário"};

    public void addFuncionario(Funcionario funcionario) {
        funcionarios.add(funcionario);
        fireTableRowsInserted(funcionarios.size()-1, funcionarios.size()-1);
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return funcionarios.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Funcionario funcionario = funcionarios.get(rowIndex);
        switch (columnIndex) {
            case 0: return funcionario instanceof Gerente ? "Gerente" : "Funcionário";
            case 1: return funcionario.getNome();
            case 2: return funcionario.getSalario();
            default: return "";
        }
    }
}