/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Déleo Cambula
 */
// PessoaManager.java
import java.io.*;
import java.util.ArrayList;

 public class PessoaManager {
    
    public static ArrayList<Pessoa> ler() {
        File ficheiro = new File("Pessoas.dat");

        if (!ficheiro.exists() || ficheiro.length() == 0) {
            return new ArrayList<>(); // ficheiro não existe ou está vazio
        }

        try (FileInputStream meuFicheiro = new FileInputStream(ficheiro);
             ObjectInputStream in = new ObjectInputStream(meuFicheiro)) {

            return (ArrayList<Pessoa>) in.readObject();

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro na leitura: " + e.getMessage());
            return new ArrayList<>();
        }
    }
    
    public static Boolean gravar(Pessoa pessoa) {
        ArrayList<Pessoa> lista = ler(); // apenas uma leitura

        if (lista == null) {
            lista = new ArrayList<>();
        }

        lista.add(pessoa);

        try (FileOutputStream meuFicheiro = new FileOutputStream("Pessoas.dat");
            ObjectOutputStream os = new ObjectOutputStream(meuFicheiro)) {

            os.writeObject(lista);
            return true;

        } catch (IOException e) {
            System.out.println("Erro ao gravar: " + e.getMessage());
            return false;
        }
    }
    
    public static Boolean salvarLista(ArrayList<Pessoa> lista) {
        try (FileOutputStream meuFicheiro = new FileOutputStream("Pessoas.dat");
            ObjectOutputStream os = new ObjectOutputStream(meuFicheiro)) {

            os.writeObject(lista);
            return true;

        } catch (IOException e) {
            System.out.println("Erro ao gravar lista: " + e.getMessage());
            return false;
        }
    }
}