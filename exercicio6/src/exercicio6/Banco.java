/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exercicio6;

/**
 *
 * @author Déleo Cambula
 */
// Banco.java
import java.io.*;
import java.util.ArrayList;

public class Banco {
    
    public static ArrayList<Conta> ler() {
        File ficheiro = new File("Contas.dat");

        if (!ficheiro.exists() || ficheiro.length() == 0) {
            return new ArrayList<>(); // ficheiro não existe ou está vazio
        }

        try (FileInputStream meuFicheiro = new FileInputStream(ficheiro);
             ObjectInputStream in = new ObjectInputStream(meuFicheiro)) {

            return (ArrayList<Conta>) in.readObject();

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro na leitura: " + e.getMessage());
            return new ArrayList<>();
        }
    }
    
    public static Boolean gravar(Conta conta) {
        ArrayList<Conta> lista = ler(); // apenas uma leitura

        if (lista == null) {
            lista = new ArrayList<>();
        }

        lista.add(conta);

        try (FileOutputStream meuFicheiro = new FileOutputStream("Contas.dat");
            ObjectOutputStream os = new ObjectOutputStream(meuFicheiro)) {

            os.writeObject(lista);
            return true;

        } catch (IOException e) {
            System.out.println("Erro ao gravar: " + e.getMessage());
            return false;
        }
    }
    
    // Método recursivo para calcular taxas totais
    public static double calcularTaxasTotais(ArrayList<Conta> contas) {
        return calcularTaxasRecursivo(contas, 0, 0);
    }
    
    private static double calcularTaxasRecursivo(ArrayList<Conta> contas, int index, double total) {
        // Caso base: quando chegamos ao final da lista
        if (index >= contas.size()) {
            return total;
        }
        
        // Caso recursivo: soma a taxa da conta atual e chama para a próxima
        double taxaAtual = contas.get(index).calcularTaxa();
        return calcularTaxasRecursivo(contas, index + 1, total + taxaAtual);
    }
}