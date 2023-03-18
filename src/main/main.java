package main;
import java.util.Scanner;
import com.ericsson.otp.erlang.*;
import nodeFactory.Node;

public class main {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		Scanner scanner =new Scanner(System.in);
		System.out.println("Bienvenido");
		boolean respuesta=true;
		while(respuesta==true) {
			System.out.println("Selecciona un opcion \n"
					+ "1-Receptor mensaje \n"
					+ "2-Enviar mensajes \n"
					+ "3-Enviar trama bolivia\n"
					+ "4-Enviar trama islas\n"
					+ "5-Salir");
			String opcion=scanner.nextLine();
			if(opcion.equalsIgnoreCase("1")) {
				System.out.println("vas a recibir mensajes");
			}else if(opcion.equalsIgnoreCase("2")) {
				System.out.println("vas a enviar un mensaje");
			}else if(opcion.equalsIgnoreCase("3")) {
				System.out.println("vas a enviar una trama al epin 1.5");
			}else if(opcion.equalsIgnoreCase("4")) {
				System.out.println("vas a enviar una trapa al epin 3.3");
			}else if(opcion.equalsIgnoreCase("5")){
				System.out.println("adios.");
				respuesta=false;
			}else{
				System.out.println("opcion invalida elige de nuevo una opcion");
			}
			if(opcion.equalsIgnoreCase("1") || opcion.equalsIgnoreCase("2")){
				System.out.println("digita el nombre del nodo que vas a crear");
				String nodeName=scanner.nextLine();
				System.out.println("digita la cookie del nodo que vas a crear");
				String cookie=scanner.nextLine();
				System.out.println("digita el nombre del mailbox");
				String mailbox=scanner.nextLine();
				Node nodo=new Node(nodeName,mailbox,cookie);
				System.out.println("el nodo fue creado correctamente");
				if(opcion.equalsIgnoreCase("1")) {
					nodo.recibe();
					nodo.closenodo();
				}
				if(opcion.equalsIgnoreCase("2")) {
					nodo.enviar();
					nodo.recibe();
					nodo.closenodo();
				}
			}
			if(opcion.equalsIgnoreCase("3")) {
				Node nodo=new Node("Tramas","dispatcher","inswitch_ups");
				System.out.println("Nodo creado listo a punto de enviar trama");
				nodo.trama("jwsgateway@BOMITCEP","dispatcher","bol");
				nodo.recibe();
				nodo.closenodo();
			}
			if(opcion.equalsIgnoreCase("4")){
				Node nodo=new Node("Tramas","dispatcher","inswitch_ups");
				Boolean bandera =true;
				while(bandera==true) {
					System.out.println("digite a que prepago desea enviarle una trama \n"+
							"1-Air \n"+
							"2-Cain \n"+
							"3-salir");
					String prepay=scanner.nextLine();
					if(prepay.equalsIgnoreCase("1")) {
						Boolean bandera2 =true;
						while(bandera2==true) {
							System.out.println("digite que trama de Air quiere replicar \n"+
									"1-get-account \n"+
									"2-refil \n"+
									"3-salir");
							String trama=scanner.nextLine();
							if(trama.equalsIgnoreCase("1")) {
								nodo.trama("jrpcgateway@BOMITCEP", "dispatcher", "islasGetA");
								nodo.recibe();
								nodo.closenodo();
							}else if(trama.equalsIgnoreCase("2")) {
								nodo.trama("jrpcgateway@BOMITCEP", "dispatcher", "islasRefil");
								nodo.recibe();
								nodo.closenodo();
							}else if(trama.equalsIgnoreCase("3")) {
								bandera=false;
								bandera2=false;
							}else{
								System.out.println("digitaste una opcion invalidad vuelve a intentarlo");
							}
							
						}
					}else if(prepay.equalsIgnoreCase("2")) {
						System.out.println("Cain aun no dispponible");
					}else if(prepay.equalsIgnoreCase("3")) {
						bandera=false;
						System.out.println("volviendo a menu principal");
					}else{
						System.out.println("volviendo a menu principal");
					}
				}
			}
		}
	}
}
