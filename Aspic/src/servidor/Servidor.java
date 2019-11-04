/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import java.io.Closeable;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class Servidor {

     public static void main(String[] args){
         
         ServerSocket s = null;
         ExecutorService pool = null;
         try {
             s = new ServerSocket(6666);
             pool = Executors.newCachedThreadPool();
             
             while(true){
                 Socket cliente = s.accept();
                 System.out.println("ha llegado un cliente");
                 AtenderPeticion atenderPeticion = new AtenderPeticion(cliente);
                 pool.execute(atenderPeticion);
             }
             
         } catch (IOException ex) {
             Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
         }finally {
        	 pool.shutdown();
        	 cerrar(s);
         }
         
     }
     public static void cerrar(Closeable c)
 	{
 		if(c!=null)
 		{
 			try
 			{
 				c.close();
 			}
 			catch(IOException e)
 			{
 				e.printStackTrace();
 			}
 		}
 	}
}
