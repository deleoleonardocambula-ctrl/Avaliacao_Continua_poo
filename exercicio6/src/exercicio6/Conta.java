/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package exercicio6;

import java.io.Serializable;

/**
 *
 * @author Déleo Cambula
 */

public interface Conta extends Serializable {
    double calcularTaxa();
    String getNumero();
    double getSaldo();
    String getTipo();
}