/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import java.io.Closeable;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class AtenderPeticion implements Runnable{
    
    private Socket cliente;
    
    public AtenderPeticion(Socket cliente){
        this.cliente = cliente;
    }

    @Override
    public void run() {
        
        PrintStream ps = null;
        DataInputStream dis = null;
        FileInputStream fis = null;
    	FileOutputStream fos = null;
    	PrintStream ps2 = null;
    	DataInputStream dis2 = null;
    	DataOutputStream dos = null;
    	DataInputStream disAmigos = null;
        
        try {
            ps = new PrintStream(cliente.getOutputStream());
            dis = new DataInputStream(cliente.getInputStream());
            
         
            String peticion = dis.readLine();
            System.out.println("llega: " + peticion);
            String[] partes = peticion.split(" ");
            
            String opcion = partes[0];
           
                if(opcion.equals("LOGIN")) {
                	fis = new FileInputStream("Usuarios/" + partes[1] + ".txt");
                	dis2 =  new DataInputStream(fis);
                	String pwd = partes[2];
                	String linea = dis2.readLine();	
                	if(pwd.equals(linea)) {
                			ps.println("OK");
                			ps.flush();
                		}else {
                			ps.println("ERROR");
                    		ps.flush();
                		}
                		
                	
                   
                }
                else if(opcion.equals("SIGNIN")){
                	String user = partes[1];
                	File usuarios = new File("usuarios");
                	
                	for(String s : usuarios.list()) {
                		if(s.equals(user+".txt")) {
                			System.out.println("entra en ya existente");
                			ps.println("Ya existe");
                			ps.flush();
                			return;
                		}else {
                			File f = new File("usuarios/" + user +".txt");
                			File carpetaUsuario = new File("src/imagenes/usuarios/" + user);
                			carpetaUsuario.mkdir();
                			fos = new FileOutputStream("usuarios/"+ user + ".txt");
                			ps2 = new PrintStream(fos);
                			ps.println("OK");
                			ps.flush();
                			ps2.println(partes[2]);
                			ps2.flush();
                		}
                	}
                	
//                	String pwd = partes[2];
//                	
//                	String linea = dis2.readLine();
//                	boolean encontrado = false;
//                	while(linea != null) {
//                		String[] partLinea = linea.split(" ");
//                		for(String s : partLinea) {
//                			if(user.equals(s)) {
//                				ps.flush();
//                				encontrado = true;
//                			}
//                		}
//                		linea = dis2.readLine();
//                	}
//                	if(encontrado == false) {
//                		ps2.println(user + " " + pwd);
//                		ps2.flush();
//                		ps.println("OK");
//                		ps.flush();
//                	}else {
//                		ps.println("Existene");
//                		ps.flush();
//                	}
                	
                }
                else if(opcion.equals("SUBIR")) {
                	String user = partes[1];
                	String foto = partes[3];
                	//String[] partesFoto = foto.split("\\");
                	//String nombreFoto = partesFoto[partesFoto.length - 1];
                	String nfoto=foto.substring(foto.lastIndexOf("\\"), foto.length());
                	//System.out.println("nombreFoto);
                	dis = new DataInputStream(cliente.getInputStream());
                	//OutputStream os = new FileOutputStream(user + ".png");

                	fos = new FileOutputStream("src\\imagenes\\usuarios\\" + user + "\\" + nfoto);
                	int tam = Integer.parseInt(partes[2]);
                	System.out.println("Antes del for");	
        			//byte buffer[] = new byte[1024];
        			int leidos = 0;
        			int i = 0;
        			
//        			while (leido  != -1) {
//        				System.out.println(leido);
//        				os.write(buffer, 0, leido);
//        				System.out.println(leido);
//        				leido = dis.read(buffer);
//        			}
        			while (i < tam) {
						leidos = dis.read();
						fos.write(leidos);
						i++;
					}        			
        			
        			fos.flush();
        			fos.close();
        			System.out.println("sale del for");
        			
        			
        			//Asi las ordenamos por fecha, no hay que poner el date en el nombre que no deja poner los ":"
        			//File f = new File("src\\imagenes\\usuarios\\" + user + "\\" + nfoto);
        			//dos.flush();
                	
                }
                else if(opcion.equals("ELIMINAR")) {
                	String user = partes[1];
                	String foto = partes[2];

                	File fotografia = new File("src\\imagenes\\usuarios\\" + user + "\\" + foto);
                	fotografia.delete();
                	
                }

                else if(opcion.equals("SEGUIR")) {
                	String user1 = partes[1];
                	String user2 = partes[2];
                	boolean encontrado = false;
                	DataInputStream disUsuario = new DataInputStream(new FileInputStream("usuarios/" + user1 + ".txt"));
                	String linea = disUsuario.readLine();
                	while(linea != null) {
                		if(linea.equals(user2)) {
                			encontrado = true;
                			System.out.println("Ya sois amigos");
                			ps.println("Sois amigos");
                		}
                		linea = disUsuario.readLine();
                	}
                	if(encontrado == false) {
                		
                	
                	File f = new File("usuarios/" + user2 + ".txt");
                	if(f.exists()) {
                		ps2 = new PrintStream(new FileOutputStream("usuarios/" + user1 + ".txt", true));	
                    	ps2.println(user2);
                    	ps2.flush();
                    	ps.println("OK");
                    	ps.flush();
                	}else {
                		ps.println("No existe");
                		System.out.println("El usuario no existe.");
                	}
                	
                	}
                	
                }
                else if(opcion.equals("MOSTRAR")) {
                	String user = partes[1];
                	File famigos = new File("usuarios/" + user + ".txt");
                	disAmigos = new DataInputStream(new FileInputStream(famigos));
                	//Linea contraseña
                	String linea = disAmigos.readLine();
                	linea = disAmigos.readLine();
                	while(linea != null) {
                		File carpetaFotos = new File("src/imagenes/usuarios/" + linea);
                		for(File f : carpetaFotos.listFiles()) {
                			
                			try {
                	    		System.out.println("AtenderPeticion");
                	    		ps = new PrintStream(cliente.getOutputStream());
                	    		//long tam = f.length();
                				//dis = new DataInputStream(servidor.getInputStream());
                				//OutputStream dos = new DataOutputStream(servidor.getOutputStream());
                	    		//fis = new FileInputStream(f.getName());
                	    		//dos = new DataOutputStream(cliente.getOutputStream());

                				ps.println("IMAGEN " + f.getAbsolutePath());
                				ps.flush();
                				//InputStream is = new FileInputStream(foto);
                				
                				
//                				byte buffer[] = new byte[1024];
//                				int leido =0;
//                				
////                				int i =0;
////                				while(i < tam) {
////                					leidos = fis.read();
////                					dos.write(leidos);
////                					i++;
////                				}
//                				while (leido != -1) {
//                					System.out.println(leido);
//                					dos.write(buffer, 0, leido);
//                					leido = fis.read(buffer);
//                				}
//                				dos.flush();
//                				dos.close();
                				//dos.flush();		
                				System.out.println("lo termina de mandar");
                				linea = disAmigos.readLine();
                			} catch (IOException e) {
                				// TODO Auto-generated catch block
                				e.printStackTrace();
                			}
                			
                		}
                	}
                }
      
      
            
            
            
            
        } catch (IOException ex) {
            Logger.getLogger(AtenderPeticion.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("IOEXCEPTION");
        }finally {
        	cerrar(fis);
        	cerrar(fos);
        	cerrar(ps);
        	cerrar(ps2);
        	cerrar(dis);
        	cerrar(dis2);
        	cerrar(dos);
        	cerrar(disAmigos);
        	
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
