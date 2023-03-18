package nodeFactory;

import java.util.Scanner;

import com.ericsson.otp.erlang.*;

import messagefactory.CreacionMensaje;


public class Node {
	private static OtpNode node;
    private static OtpMbox mbox;
    private static String cookie;
    public Node (String nodeName, String mboxName, String cookie) throws Exception {
    	this.node = new OtpNode(nodeName,cookie);
        this.mbox = node.createMbox(mboxName);
        this.cookie=cookie;
    }
    
    public static void closenodo(){
    	System.out.println("nodo cerrado correctamente");
    	node.close();
    }
    public static void recibe() {
    	boolean a =true;
    	Scanner scanner =new Scanner(System.in);
    	while(a==true) {
    		System.out.println("esperando mensaje");
    		try {
    			 OtpErlangObject msg = mbox.receive();
    			 System.out.println("mensaje recibido "+msg);
    			 OtpErlangTuple t = (OtpErlangTuple) msg;
    			 if (t.elementAt(1) instanceof OtpErlangString) {
    				    // El segundo elemento es una cadena de Erlang
    				 System.out.println("soy un strin");
    			} else {
    				    // El segundo elemento no es una cadena de Erlang
    				    System.out.println("no entre");
    			}
    			
    			 OtpErlangPid from = (OtpErlangPid) t.elementAt(0);
    			 System.out.println("que quieres enviarle a erlang si quieres salir escribe s");
    			 String greeting = scanner.nextLine();		 
    			 if(greeting.equalsIgnoreCase("s")){
    				 a=false;
    				 System.out.println("adios");
    			 }else{
    				 //real
    				 OtpErlangString replystr = new OtpErlangString(greeting);
    				 OtpErlangTuple outMsg =new OtpErlangTuple(new OtpErlangObject[]{mbox.self(),replystr});
    				 mbox.send(from, outMsg);
    			 }
    		 }catch (Exception e) {
    			 System.out.println("caught error: " + e);
    		 }
    	}
    }
    public static void enviar() {
    	Scanner scanner =new Scanner(System.in);
    	OtpErlangObject[] msg = new OtpErlangObject[2];
		msg[0] = mbox.self();
		System.out.println("que mensaje quieres enviar.");
		String msj = scanner.nextLine();	
		msg[1] = new OtpErlangString(msj);
		OtpErlangTuple tuple = new OtpErlangTuple(msg);
		System.out.println("escribe el nodo al que quieras enviarle un mensaje.");
		String exnode = scanner.nextLine();
		System.out.println("escribe el mailbox del nodo al q le enviaras el mensaje");
		String exmail = scanner.nextLine();
		
		mbox.send(exmail,exnode, tuple);
    } 
    public static void trama(String exnode,String exmail,String destino) {
    	OtpErlangTuple Msg2=null;
    	if (destino.equalsIgnoreCase("bol")) {
    		Msg2=queryCustomerInfo();
    		
    	}
    	if(destino.equalsIgnoreCase("islasGetA")){
    		Msg2=airGetAccount();
    	}
    	if(destino.equalsIgnoreCase("islasRefil")) {
    		Msg2=refil();
    	}
    	System.out.println("Enviaras la trama: \n"+
				Msg2+"\n"+
				"al nodo: "+exnode);				
		mbox.send(exmail,exnode, Msg2);
    	
    }
    public static OtpErlangTuple queryCustomerInfo() {
		//process_identiier
		OtpErlangString string1 = new OtpErlangString("process_identifier");
		OtpErlangTuple pi = new OtpErlangTuple(new OtpErlangObject[] {string1, mbox.self()});
		
		//message number
		OtpErlangString string2 = new OtpErlangString("message_number");
		OtpErlangString string3 = new OtpErlangString("130500000");
		OtpErlangTuple mn = new OtpErlangTuple(new OtpErlangObject[] {string2, string3});
		
		//message type
		OtpErlangString string4 = new OtpErlangString("message_type");
		OtpErlangString string5 = new OtpErlangString("epin_huawei_bussinesmgr_query_customer_info");
		OtpErlangTuple mt = new OtpErlangTuple(new OtpErlangObject[] {string4, string5});
		
		//is transactional
		OtpErlangString string6 = new OtpErlangString("is_transactional");
		OtpErlangBoolean string7 = new OtpErlangBoolean(false);
		OtpErlangTuple it = new OtpErlangTuple(new OtpErlangObject[] {string6, string7});
		
		//timeout
		OtpErlangString string8 = new OtpErlangString("timeout");
		OtpErlangString string9 = new OtpErlangString("30000");
		OtpErlangTuple t = new OtpErlangTuple(new OtpErlangObject[] {string8, string9});
		
		//message
		
		//utfi
		OtpErlangString string10 = new OtpErlangString("utfi");
		OtpErlangString string11 = new OtpErlangString("ECYY4E56F0");
		OtpErlangTuple utfi = new OtpErlangTuple(new OtpErlangObject[] {string10, string11});
		
		//msisdn
		OtpErlangString string12 = new OtpErlangString("msisdn");
		OtpErlangString string13 = new OtpErlangString("59176652087");
		OtpErlangTuple msisdn = new OtpErlangTuple(new OtpErlangObject[] {string12, string13});
		
		//query_method
		OtpErlangString string14 = new OtpErlangString("query_method");
		OtpErlangString string15 = new OtpErlangString("0");
		OtpErlangTuple query_method = new OtpErlangTuple(new OtpErlangObject[] {string14, string15});
		
		
		OtpErlangTuple msg = new OtpErlangTuple(new OtpErlangObject[] {utfi,msisdn,query_method});
		OtpErlangString string16 = new OtpErlangString("message");
		OtpErlangTuple messge = new OtpErlangTuple(new OtpErlangObject[] {string16,msg}); 
		
		//union mensaje
		OtpErlangTuple enviar = new OtpErlangTuple(new OtpErlangObject[] {pi,mn,mt,it,t,messge});
		return enviar;
    }
    public static OtpErlangTuple refil() {
    	OtpErlangString string1 = new OtpErlangString("process_identifier");
		OtpErlangTuple pi = new OtpErlangTuple(new OtpErlangObject[] {string1, mbox.self()});
		
		//message number
		OtpErlangString string2 = new OtpErlangString("message_number");
		OtpErlangString string3 = new OtpErlangString("167912748076208");
		OtpErlangTuple mn = new OtpErlangTuple(new OtpErlangObject[] {string2, string3});
		
		//message type
		OtpErlangString string4 = new OtpErlangString("message_type");
		OtpErlangString string5 = new OtpErlangString("epin_air_refill_5_0_digicel_jm");
		OtpErlangTuple mt = new OtpErlangTuple(new OtpErlangObject[] {string4, string5});
		
		//is transactional
		OtpErlangString string6 = new OtpErlangString("is_transactional");
		OtpErlangBoolean string7 = new OtpErlangBoolean(false);
		OtpErlangTuple it = new OtpErlangTuple(new OtpErlangObject[] {string6, string7});
		
		//timeout
		OtpErlangString string8 = new OtpErlangString("timeout");
		OtpErlangString string9 = new OtpErlangString("30000");
		OtpErlangTuple t = new OtpErlangTuple(new OtpErlangObject[] {string8, string9});
		
		//message
		
		//utfi
		OtpErlangString string10 = new OtpErlangString("utfi");
		OtpErlangString string11 = new OtpErlangString("ECYY4E56F0");
		OtpErlangTuple utfi = new OtpErlangTuple(new OtpErlangObject[] {string10, string11});
		
		//connection_main
		OtpErlangString string12 = new OtpErlangString("connection_name");
		OtpErlangString string13 = new OtpErlangString("digicel.jamaica.air");
		OtpErlangTuple connection_name = new OtpErlangTuple(new OtpErlangObject[] {string12, string13});
		
		//user
		OtpErlangString string14 = new OtpErlangString("user");
		OtpErlangString string15 = new OtpErlangString("epin");
		OtpErlangTuple user = new OtpErlangTuple(new OtpErlangObject[] {string14, string15});
		
		//agent
		OtpErlangString string16 = new OtpErlangString("agent");
		OtpErlangString string17 = new OtpErlangString("-61-");
		OtpErlangTuple agent = new OtpErlangTuple(new OtpErlangObject[] {string16, string17});
		
		//subscriber 
		OtpErlangString string60 = new OtpErlangString("subscriber");
		OtpErlangString string61 = new OtpErlangString("18763759178");
		OtpErlangTuple subscriber = new OtpErlangTuple(new OtpErlangObject[] {string60, string61});
		
		//origin_node_type
		OtpErlangString string18 = new OtpErlangString("origin_node_type");
		OtpErlangString string19 = new OtpErlangString("EXT");
		OtpErlangTuple origin_node_type = new OtpErlangTuple(new OtpErlangObject[] {string18, string19});
		
		//origin_host_name
		OtpErlangString string20 = new OtpErlangString("origin_host_name");
		OtpErlangString string21 = new OtpErlangString("appjmxx01");
		OtpErlangTuple origin_host_name = new OtpErlangTuple(new OtpErlangObject[] {string20, string21});
		
		//origin_transaction_id
		OtpErlangString string22 = new OtpErlangString("origin_transaction_id");
		OtpErlangString string23 = new OtpErlangString("1745844347");
		OtpErlangTuple origin_transaction_id = new OtpErlangTuple(new OtpErlangObject[] {string22, string23});
		
		//currency
		OtpErlangString string24 = new OtpErlangString("currency");
		OtpErlangString string25 = new OtpErlangString("JMD");
		OtpErlangTuple currency = new OtpErlangTuple(new OtpErlangObject[] {string24, string25});
		
		//amount
		OtpErlangString string26 = new OtpErlangString("amount");
		OtpErlangString string27 = new OtpErlangString("10000");
		OtpErlangTuple amount = new OtpErlangTuple(new OtpErlangObject[] {string26, string27});
		
		//profile_id
		OtpErlangString string28 = new OtpErlangString("profile_id");
		OtpErlangString string29 = new OtpErlangString("-22-");
		OtpErlangTuple profile_id = new OtpErlangTuple(new OtpErlangObject[] {string28, string29});
		
		//external_data_1
		OtpErlangString string30 = new OtpErlangString("external_data_1");
		OtpErlangString string31 = new OtpErlangString("EPIN_Jamaica");
		OtpErlangTuple external_data_1 = new OtpErlangTuple(new OtpErlangObject[] {string30, string31});
		
		//2
		OtpErlangString string32 = new OtpErlangString("external_data_2");
		OtpErlangString string33 = new OtpErlangString("Scotiabank Distributor Account");
		OtpErlangTuple external_data_2 = new OtpErlangTuple(new OtpErlangObject[] {string32, string33});
		
		//3
		OtpErlangString string34 = new OtpErlangString("external_data_3");
		OtpErlangString string35 = new OtpErlangString("1745844347");
		OtpErlangTuple external_data_3 = new OtpErlangTuple(new OtpErlangObject[] {string34, string35});
		
		//4
		OtpErlangString string36 = new OtpErlangString("external_data_4");
		OtpErlangString string37 = new OtpErlangString("000000306991087");
		OtpErlangTuple external_data_4 = new OtpErlangTuple(new OtpErlangObject[] {string36, string37});
		
		//
		OtpErlangTuple msg = new OtpErlangTuple(new OtpErlangObject[] {utfi,connection_name,user,agent,subscriber,origin_node_type,origin_host_name,origin_transaction_id,currency,amount,profile_id,external_data_1,external_data_2,external_data_3,external_data_4});
		OtpErlangString string2400 = new OtpErlangString("message");
		OtpErlangTuple messge = new OtpErlangTuple(new OtpErlangObject[] {string2400,msg}); 
		
		//union mensaje
		OtpErlangTuple enviar = new OtpErlangTuple(new OtpErlangObject[] {pi,mn,mt,it,t,messge});
		return enviar;    	
    }
    public static OtpErlangTuple airGetAccount() {
    	
    	//
    	OtpErlangString string1 = new OtpErlangString("process_identifier");
		OtpErlangTuple pi = new OtpErlangTuple(new OtpErlangObject[] {string1, mbox.self()});
		
		//message number
		OtpErlangString string2 = new OtpErlangString("message_number");
		OtpErlangString string3 = new OtpErlangString("167912748076208");
		OtpErlangTuple mn = new OtpErlangTuple(new OtpErlangObject[] {string2, string3});
		
		//message type
		OtpErlangString string4 = new OtpErlangString("message_type");
		OtpErlangString string5 = new OtpErlangString("epin_air_get_account_5_0_digicel_jm");
		OtpErlangTuple mt = new OtpErlangTuple(new OtpErlangObject[] {string4, string5});
		
		//is transactional
		OtpErlangString string6 = new OtpErlangString("is_transactional");
		OtpErlangBoolean string7 = new OtpErlangBoolean(false);
		OtpErlangTuple it = new OtpErlangTuple(new OtpErlangObject[] {string6, string7});
		
		//timeout
		OtpErlangString string8 = new OtpErlangString("timeout");
		OtpErlangString string9 = new OtpErlangString("30000");
		OtpErlangTuple t = new OtpErlangTuple(new OtpErlangObject[] {string8, string9});
		
		//message
		
		//utfi
		OtpErlangString string10 = new OtpErlangString("utfi");
		OtpErlangString string11 = new OtpErlangString("ECYY4E56F0");
		OtpErlangTuple utfi = new OtpErlangTuple(new OtpErlangObject[] {string10, string11});
		
		//connection_main
		OtpErlangString string12 = new OtpErlangString("connection_name");
		OtpErlangString string13 = new OtpErlangString("digicel.jamaica.air");
		OtpErlangTuple connection_name = new OtpErlangTuple(new OtpErlangObject[] {string12, string13});
		
		//user
		OtpErlangString string14 = new OtpErlangString("user");
		OtpErlangString string15 = new OtpErlangString("epin");
		OtpErlangTuple user = new OtpErlangTuple(new OtpErlangObject[] {string14, string15});
		
		//subscriber
		OtpErlangString string16 = new OtpErlangString("subscriber");
		OtpErlangString string17 = new OtpErlangString("18769999999");
		OtpErlangTuple subscriber = new OtpErlangTuple(new OtpErlangObject[] {string16, string17});
		
		//origin_node_type
		OtpErlangString string18 = new OtpErlangString("origin_node_type");
		OtpErlangString string19 = new OtpErlangString("EXT");
		OtpErlangTuple origin_node_type = new OtpErlangTuple(new OtpErlangObject[] {string18, string19});
		
		//origin_host_name
		OtpErlangString string20 = new OtpErlangString("origin_host_name");
		OtpErlangString string21 = new OtpErlangString("appMT_sop");
		OtpErlangTuple origin_host_name = new OtpErlangTuple(new OtpErlangObject[] {string20, string21});
		
		//origin_transaction_id
		OtpErlangString string22 = new OtpErlangString("origin_transaction_id");
		OtpErlangString string23 = new OtpErlangString("0");
		OtpErlangTuple origin_transaction_id = new OtpErlangTuple(new OtpErlangObject[] {string22, string23});
		//
		OtpErlangTuple msg = new OtpErlangTuple(new OtpErlangObject[] {utfi,connection_name,user,subscriber,origin_node_type,origin_host_name,origin_transaction_id});
		OtpErlangString string24 = new OtpErlangString("message");
		OtpErlangTuple messge = new OtpErlangTuple(new OtpErlangObject[] {string24,msg}); 
		
		//union mensaje
		OtpErlangTuple enviar = new OtpErlangTuple(new OtpErlangObject[] {pi,mn,mt,it,t,messge});
		return enviar;    	
    }
}