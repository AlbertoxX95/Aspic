/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

/**
 *
 * @author Usuario
 */
public class GestorCliente {
    Socket servidor = null;
    PrintStream ps = null;
    DataInputStream dis = null;
    FileOutputStream fos = null;
    FileInputStream fis = null;
    
    public GestorCliente(){
        
    }
    public boolean login(String user, String pwd){
   
            try {
            this.servidor = new Socket("localhost", 6666);
			dis = new DataInputStream(servidor.getInputStream());
            ps = new PrintStream(servidor.getOutputStream());
            
            ps.println("LOGIN " + user + " " + pwd);
            ps.flush();
            
            String respuesta = dis.readLine();
            System.out.println(respuesta);
            
            if(respuesta.equals("OK")){
                return true;
            }
            if(respuesta.equals("ERROR")){
            	System.out.println("Contraseña incorrecta");
               return false;
            }
            } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            return true;
    }
    
    public boolean registrarse(String user, String pwd) {
    		try {
    			this.servidor = new Socket("localhost", 6666);
				dis = new DataInputStream(servidor.getInputStream());
				ps = new PrintStream(servidor.getOutputStream());
				
				ps.println("SIGNIN " + user + " " + pwd);
				ps.flush();
				
				String respuesta = dis.readLine();
				if(respuesta.equals("OK")) {
					return true;
				}
				else if(respuesta.equals("Ya existe")){
					return false;
				}else {
					System.out.println("OTRA");
					return false;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		return true;
    }
    
    public boolean subirFoto(File foto, String user) {
    	try {
    		File f = new File("tux.jpg");
    		this.servidor = new Socket("localhost", 6666);
    		System.out.println("GestorClienteSubirFoto");
    		ps = new PrintStream(servidor.getOutputStream());
    		long tam = f.length();
			//dis = new DataInputStream(servidor.getInputStream());
			//OutputStream dos = new DataOutputStream(servidor.getOutputStream());
    		FileInputStream fis = new FileInputStream(foto);
    		DataOutputStream dos = new DataOutputStream(servidor.getOutputStream());

			ps.println("SUBIR " + user + " "+ tam  + " " + foto);
			ps.flush();
			//InputStream is = new FileInputStream(foto);
			
			
			//byte buffer[] = new byte[1024];
			int leidos =0;
			
			int i =0;
			while(i < tam) {
				leidos = fis.read();
				dos.write(leidos);
				i++;
			}
//			while (leido != -1) {
//				System.out.println(leido);
//				dos.write(buffer, 0, leido);
//				leido = is.read(buffer);
//			}
			dos.flush();
			dos.close();
			//dos.flush();		
			System.out.println("lo termina de mandar");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return true;
}
    
    public boolean eliminarFoto(String foto, String user) {
		try {
			this.servidor = new Socket("localhost", 6666);
			dis = new DataInputStream(servidor.getInputStream());
			ps = new PrintStream(servidor.getOutputStream());

			ps.println("ELIMINAR " + user + " " + foto);
			ps.flush();

			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
    }
    
    
    public boolean seguirAmigo(String user1, String user2) {
    	try {
			this.servidor = new Socket("localhost", 6666);
			ps = new PrintStream(servidor.getOutputStream());
			dis = new DataInputStream(servidor.getInputStream());
			ps.println("SEGUIR " + user1 +" " + user2);
			ps.flush();
			String respuesta = dis.readLine();
			if(respuesta.equals("OK")) {
				return true;
			}else {
				return false;
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return true;
    	
    }
    
    public List<ImageIcon> mostrarFotos(String user) {
    	List<ImageIcon> fotos = new ArrayList<ImageIcon>();
    	try {
			this.servidor = new Socket("localhost", 6666);
			ps = new PrintStream(servidor.getOutputStream());
			dis = new DataInputStream(servidor.getInputStream());
			
			ps.println("MOSTRAR " + user);
			ps.flush();
			String respuesta=dis.readLine();
			
			while(respuesta != null){

	            System.out.println(respuesta);
	            String[] partesRespuesta = respuesta.split(" ");
				
				//long tam = Long.parseLong(partesRespuesta[2]);
	        	String foto = partesRespuesta[1];
	        	//String[] partesFoto = foto.split("\\");
	        	//String nombreFoto = partesFoto[partesFoto.length - 1];
	        	//String nfoto=foto.substring(foto.lastIndexOf("\\"), foto.length());
	        	//System.out.println("nombreFoto);
	        	//dis = new DataInputStream(cliente.getInputStream());
	        	//OutputStream os = new FileOutputStream(user + ".png");

	        	//fos = new FileOutputStream("src\\imagenes\\usuarios\\" + user + "\\" + nfoto);
	        	//int tam = Integer.parseInt(partesRespuesta[2]);
	        	//System.out.println("Antes del for");	

//				byte buffer[] = new byte[tam];
//				byte buff[] = new byte[1024];
//				int i = 0;
//				//int i = 0;
//				int leidos = dis.read(buff);
////				while (i  < tam) {
////					os.write(buff, 0, leido);
////					
////					leidos = dis.read(buffer);
////				}
//				fos = new FileOutputStream("aux");
//				while (i < tam) {
//					leidos = dis.read();
//					fos.write(leidos);
//					i++;
//				}
//				fos.flush();
//				fis = new FileInputStream("aux");
//				fis.read(buffer);
	        	
	        	
				ImageIcon img = new ImageIcon(foto);
				fotos.add(img);
				System.out.println(fotos.size());
				respuesta = dis.readLine();
				//fos.flush();
				//fos.close();
				//System.out.println("sale del for");
				
				
				//Asi las ordenamos por fecha, no hay que poner el date en el nombre que no deja poner los ":"
				//File f = new File("src\\imagenes\\usuarios\\" + user + "\\" + nfoto);
				//dos.flush();
	        	

			}

    } catch (IOException e) {
		// TODO Bloque catch generado automáticamente
		e.printStackTrace();
	}
    	return fotos;
 
    }
    
}
