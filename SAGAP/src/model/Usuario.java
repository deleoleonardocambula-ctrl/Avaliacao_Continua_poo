/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
/**
 *
 * @author HP
 */
public class Usuario extends Pessoa implements Serializable{
    private int id;
    private String senha;
    private String nivelDeAcesso;
    private boolean status;
    private static final long serialVersionUID = 1L;
    
    //CriacÃ£o do mÃ©todo construtor da classe Usuario
        public Usuario() {
        super("", 0);
        this.status = true;
    } 
    public Usuario(int id, String nome, int idade, String senha, String nivelDeAcesso) {    
        super(nome, idade);
        this.id = id;
        this.senha = senha;
        this.nivelDeAcesso = nivelDeAcesso;
        this.status = true;
    }
    
    //Metodo resposnavel por ciriar um objecto usuario e salvar no ficheiro
    public static void CriarUsuario(String nome, int idade, String senha, String nivelDeAcesso){
        if(usuarioJaExiste(nome)){
            System.out.println("Ja existe um usuario com esse nome");
        }else{
            if(!nivelDeAcesso.equals("Admin") && !nivelDeAcesso.equals("Gestor") && !nivelDeAcesso.equals("Professor")){
                System.out.println("Erro de nivel de acesso");
            }else{
                Usuario u1 = new Usuario(incrementarId(), nome, idade, senha, nivelDeAcesso);
                Usuario.gravarUsuario(u1);
            }
        }
    }
    
    //metodo reponsavel por atribuir id's a novos usuarios
    public static int incrementarId() {
        ArrayList<Usuario> userLista = lerUsuario();

        int maxId = 0;
        for (Usuario user : userLista) {
            if (user.getId() > maxId) {
                maxId = user.getId();
            }
        }
        return maxId + 1;  
}
    //metodo responsavel por verificar se usuarios existe
public static Boolean usuarioJaExiste(String nome){
    ArrayList<Usuario> listaDeUsuario = lerUsuario();
    
    Boolean res = false;
    if(listaDeUsuario != null){
        for(Usuario usuario: listaDeUsuario){
            // Adicione estas verificações para evitar NullPointerException
            if(usuario != null && usuario.getNome() != null && 
               usuario.getNome().equals(nome) && usuario.getStatus() == true){
                res = true;
                break;
            }
        }
    }
    return res;
}
    
    public int getId(){
        return id;
    }
    
    
     public void setId(int id) {
        this.id = id;
    }
     

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    public String getNivelDeAcesso() {
        return nivelDeAcesso;
    }

    public void setNivelDeAcesso(String nivelDeAcesso) {
        this.nivelDeAcesso = nivelDeAcesso;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    
    public void setAll(String nome, String nivelDeAcesso, boolean status, String senha){
            setNome(nome);
            setSenha(senha);
            setNivelDeAcesso(nivelDeAcesso);
            setStatus(status);  
    }
    
    //metodo reponsavel por ediatr Um usuario no ficheiro de objectos
    public static Boolean editarUsuario(Usuario usuario) { 
        ArrayList<Usuario> usuarios = lerUsuario();
         
        int id = usuario.getId();
        
        for(Usuario s: usuarios){
            if(s.getId() == id){
                s.setAll(usuario.getNome(), usuario.getNivelDeAcesso(), usuario.getStatus(), usuario.getSenha());
                break;
            }
        }
        try {
            FileOutputStream meuFicheiro = new FileOutputStream("UsuarioFile.dat");
            ObjectOutputStream os = new ObjectOutputStream(meuFicheiro);  
            
            os.writeObject(usuarios);

            os.close();
            meuFicheiro.close();

            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    //metodo reposnsavel por grvar usuario no ficeiro de objectos
    public static Boolean gravarUsuario(Usuario usuario) {
        ArrayList<Usuario> lista = lerUsuario(); // apenas uma leitura

        if (lista == null) {
            lista = new ArrayList<>();
        }

        lista.add(usuario);

        try (FileOutputStream meuFicheiro = new FileOutputStream("UsuarioFile.dat");
            ObjectOutputStream os = new ObjectOutputStream(meuFicheiro)) {

            os.writeObject(lista);
            return true;

        } catch (IOException e) {
            System.out.println("Erro ao gravar: " + e.getMessage());
            return false;
        }
    }

    //responsavel por ler usuarios no ficheiro de objectos
    public static ArrayList<Usuario> lerUsuario() {
        File ficheiro = new File("UsuarioFile.dat");

        if (!ficheiro.exists() || ficheiro.length() == 0) {
            return new ArrayList<>(); // ficheiro nÃ£o existe ou estÃ¡ vazio
        }

        try (FileInputStream meuFicheiro = new FileInputStream(ficheiro);
            ObjectInputStream in = new ObjectInputStream(meuFicheiro)) {

            return (ArrayList<Usuario>) in.readObject();

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro na leitura: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public static Usuario login(String nome, String senha) {
        ArrayList<Usuario> listaDeUsuarios = lerUsuario();
        for(Usuario p : listaDeUsuarios) {
            if(p != null && p.getNome() != null && p.getNome().equals(nome) && p.getSenha().equals(senha) && p.getStatus() != false) {
                return p; // Retorna o usuÃ¡rio encontrado
            }
        }
        return null; // Retorna null se nÃ£o encontrar
    }

    @Override
    public String toString() {
        return "Usuario{" + "nome=" + nome + ", senha=" + senha + ", nivelDeAcesso=" + nivelDeAcesso + ", status=" + status + '}';
    }   
    
}
