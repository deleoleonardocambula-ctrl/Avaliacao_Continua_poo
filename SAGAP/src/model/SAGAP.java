/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package model;
import java.util.ArrayList;

import view.TelaLogin;
import view.TelaMenuPrincipal;

/**
 *
 * @author Déleo Cambula
 */
public class SAGAP {
    /**
     * @param args the command line arguments
     */ 
    public static void main(String[] args) {
        // TODO code application logic here

    //    Usuario.CriarUsuario("Deleo",19, "deleo", "Admin");
    //    Usuario.CriarUsuario("Avelino", 21,  "1234", "Gestor");
    //    Usuario.CriarUsuario("Gerson", 22, "123", "Professor");
        

//        ArrayList<Usuario> lista = Usuario.lerUsuario();
//
//        if(lista != null){
//            for(Usuario p : lista) {
//                if(p != null) {
//                    System.out.println(p.getSenha()); 
//                }
//            }
//        }else{
//            System.out.println("deleo");
//        }
       new TelaLogin().setVisible(true);
    }
    
}
