/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exercicios5;

/**
 *
 * @author Déleo Cambula
 */
import java.io.*;
import java.util.ArrayList;

public class SistemaPagamentos {
    
    public static ArrayList<Pagavel> ler() {
        File ficheiro = new File("Pagamentos.dat");

        if (!ficheiro.exists() || ficheiro.length() == 0) {
            return new ArrayList<>(); // ficheiro não existe ou está vazio
        }

        try (FileInputStream meuFicheiro = new FileInputStream(ficheiro);
             ObjectInputStream in = new ObjectInputStream(meuFicheiro)) {

            return (ArrayList<Pagavel>) in.readObject();

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro na leitura: " + e.getMessage());
            return new ArrayList<>();
        }
    }
    
    public static Boolean gravar(Pagavel pagavel) {
        ArrayList<Pagavel> lista = ler(); // apenas uma leitura

        if (lista == null) {
            lista = new ArrayList<>();
        }

        lista.add(pagavel);

        try (FileOutputStream meuFicheiro = new FileOutputStream("Pagamentos.dat");
            ObjectOutputStream os = new ObjectOutputStream(meuFicheiro)) {

            os.writeObject(lista);
            return true;

        } catch (IOException e) {
            System.out.println("Erro ao gravar: " + e.getMessage());
            return false;
        }
    }
    
    // Método para calcular total de pagamentos usando polimorfismo
    public static double calcularTotalPagamentos() {
        ArrayList<Pagavel> pagaveis = ler();
        double total = 0;
        
        for (Pagavel pagavel : pagaveis) {
            total += pagavel.calcularPagamento(); // Polimorfismo aqui!
        }
        
        return total;
    }
}