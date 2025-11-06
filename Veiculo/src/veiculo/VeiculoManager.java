/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package veiculo;

/**
 *
 * @author Déleo Cambula
 */
import java.io.*;
import java.util.ArrayList;

public class VeiculoManager {
    
    public static ArrayList<Veiculo> ler() {
        File ficheiro = new File("Veiculos.dat");

        if (!ficheiro.exists() || ficheiro.length() == 0) {
            return new ArrayList<>(); // ficheiro não existe ou está vazio
        }

        try (FileInputStream meuFicheiro = new FileInputStream(ficheiro);
             ObjectInputStream in = new ObjectInputStream(meuFicheiro)) {

            return (ArrayList<Veiculo>) in.readObject();

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro na leitura: " + e.getMessage());
            return new ArrayList<>();
        }
    }
    
    public static Boolean gravar(Veiculo veiculo) {
        ArrayList<Veiculo> lista = ler(); // apenas uma leitura

        if (lista == null) {
            lista = new ArrayList<>();
        }

        lista.add(veiculo);

        try (FileOutputStream meuFicheiro = new FileOutputStream("Veiculos.dat");
            ObjectOutputStream os = new ObjectOutputStream(meuFicheiro)) {

            os.writeObject(lista);
            return true;

        } catch (IOException e) {
            System.out.println("Erro ao gravar: " + e.getMessage());
            return false;
        }
    }
    
    // Método recursivo para mostrar todos os veículos
    public static String mostrarTodosRecursivo(ArrayList<Veiculo> veiculos) {
        return mostrarRecursivo(veiculos, 0, new StringBuilder()).toString();
    }
    
    private static StringBuilder mostrarRecursivo(ArrayList<Veiculo> veiculos, int index, StringBuilder sb) {
        // Caso base: quando chegamos ao final da lista
        if (index >= veiculos.size()) {
            return sb;
        }
        
        // Adiciona informações do veículo atual
        Veiculo veiculo = veiculos.get(index);
        sb.append("=== Veículo ").append(index + 1).append(" ===\n");
        sb.append("Tipo: ").append(veiculo.getClass().getSimpleName()).append("\n");
        sb.append("Modelo: ").append(veiculo.getInfo()).append("\n");
        sb.append("Movimento: ").append(veiculo.mover()).append("\n");
        
        // Adiciona informações específicas do tipo
        if (veiculo instanceof Carro) {
            Carro carro = (Carro) veiculo;
            sb.append("Combustível: ").append(carro.getTipoCombustivel()).append("\n");
        } else if (veiculo instanceof Bicicleta) {
            Bicicleta bike = (Bicicleta) veiculo;
            sb.append("Marchas: ").append(bike.getNumeroMarchas()).append("\n");
        }
        
        sb.append("\n");
        
        // Chamada recursiva para o próximo veículo
        return mostrarRecursivo(veiculos, index + 1, sb);
    }
}