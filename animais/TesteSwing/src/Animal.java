
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Lélio Sbung
 */
public abstract class Animal implements Serializable{
    private String nome;
    private int idade;
    
     // Construtor
    public Animal(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
    }
    
    public Animal(){}
    // Getters e setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public void setAll(String nome, int idade){
        this.setIdade(idade);
        this.setNome(nome);
    }
        
    @Override
    public String toString() {
        return "Nome: " + nome + ", Idade: " + idade + '}';
    }
    
        //metodo reponsavel por ediatr Um usuario no ficheiro de objectos
    public static Boolean editarUsuario(Animal animal) { 
        ArrayList<Animal> animais = lerAnimal();
         
        String nome = animal.getNome();
        
        for(Animal s: animais){
            if(s.getNome().equalsIgnoreCase(nome)){
                s.setAll(animal.getNome(), animal.getIdade());
                break;
            }
        }
        try {
            FileOutputStream meuFicheiro = new FileOutputStream("animal.dat");
            ObjectOutputStream os = new ObjectOutputStream(meuFicheiro);  
            
            os.writeObject(animais);

            os.close();
            meuFicheiro.close();

            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    //metodo reposnsavel por grvar usuario no ficeiro de objectos
    public static Boolean gravarUsuario(Animal animal) {
        ArrayList<Animal> lista = lerAnimal(); // apenas uma leitura

        if (lista == null) {
            lista = new ArrayList<>();
        }

        lista.add(animal);

        try (FileOutputStream meuFicheiro = new FileOutputStream("animal.dat");
            ObjectOutputStream os = new ObjectOutputStream(meuFicheiro)) {

            os.writeObject(lista);
            System.out.println("Erro ao gravar: ");
            return true;
            
        } catch (IOException e) {
            System.out.println("Erro ao gravar: " + e.getMessage());
            return false;
        }
    }

    //responsavel por ler usuarios no ficheiro de objectos
    public static ArrayList<Animal> lerAnimal() {
        File ficheiro = new File("animal.dat");

        if (!ficheiro.exists() || ficheiro.length() == 0) {
            return new ArrayList<>(); // ficheiro não existe ou está vazio
        }

        try (FileInputStream meuFicheiro = new FileInputStream(ficheiro);
            ObjectInputStream in = new ObjectInputStream(meuFicheiro)) {

            return (ArrayList<Animal>) in.readObject();

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro na leitura: " + e.getMessage());
            return new ArrayList<>();
        }
    }    
    public abstract String fazerSom();  
    
}
