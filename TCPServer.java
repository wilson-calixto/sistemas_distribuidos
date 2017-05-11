import java.net.*;
import java.io.*;
import java.util.*;
public class TCPServer {
	public static void main (String args[]) {
		try{
			int serverPort = 7890;
			ServerSocket listenSocket = new ServerSocket(serverPort);
				while(true) {
					Socket clientSocket = listenSocket.accept();
					Connection c = new Connection(clientSocket);
				}
		} catch(IOException e) {System.out.println("Listen :"+e.getMessage());}
	}
}

class Connection extends Thread {
	DataInputStream in;
	DataOutputStream out;
	Socket clientSocket;
	public Connection (Socket aClientSocket) {
		try {
			clientSocket = aClientSocket;
			in = new DataInputStream( clientSocket.getInputStream());
			out =new DataOutputStream( clientSocket.getOutputStream());
			this.start();
		} catch(IOException e) {System.out.println("Connection:"+e.getMessage());}
	}
	public void run(){
    char[] horarios ={'0','0','0','0','1','1','1','1','1','1','1','1','1','1','1','1'};
/*	 	char[] horarios = new char[12];
    for (int i = 0; i < 12; i++) {
        horarios[i] = '1';
    }

    //horarios[]=*/
		try {
				String data = in.readUTF();
				System.out.println("data "+data);

				int i = data.charAt(0)-48;
				System.out.println("i== "+i);
						System.out.println("antes: ");
						 for (int ii = 0; ii < 12; ii++) {
			System.out.print(horarios[ii] );
    }
				switch(i) {
								case 1:
											data = String.copyValueOf(horarios);
											break;
								case 2:
											System.out.println("snbjsdfhdg?:  "+data.substring(2,5));

											int j = Integer.parseInt(data.substring(2,5));
											System.out.println("j== "+j);
											if(horarios[j]=='1'){

															horarios[j]='0';
															data = "5 0 000";
															System.out.println("depois: ");

															for (int ii = 0; ii < 12; ii++) {
																			System.out.print(horarios[ii] );
															}
			//											data = "Reserva efetuada com sucesso ";
											}else{
															data = "6 0 000";
			//												data = "Reserva de horario falhou";
											}
											break;
									case 3:
											System.out.println("snbjsdfhdg?:  "+data.substring(2,5));

											j = Integer.parseInt(data.substring(2,5));
											System.out.println("j== "+j);
											if(horarios[j]=='0'){

															horarios[j]='1';
															data = "5 0 000";
															System.out.println("depois: ");

															for (int ii = 0; ii < 12; ii++) {
																			System.out.print(horarios[ii] );
															}
			//											data = "Reserva efetuada com sucesso ";
											}else{
															data = "6 0 000";
			//												data = "Reserva de horario falhou";
											}
											break;
									
		  //}
											default:
														data = "404";
						}
				out.writeUTF(data);
		} catch(EOFException e) {System.out.println("EOF:"+e.getMessage());
		} catch(IOException e) {System.out.println("IO:"+e.getMessage());
	} finally{ try {clientSocket.close();}catch (IOException e){/*close failed*/}}
	}
}
