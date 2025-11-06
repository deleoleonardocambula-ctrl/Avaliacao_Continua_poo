/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package exercicios5;

/**
 *
 * @author Déleo Cambula
 */
import java.io.Serializable;

public interface Pagavel extends Serializable {
    double calcularPagamento();
    String getNome();
    String getTipo();
}