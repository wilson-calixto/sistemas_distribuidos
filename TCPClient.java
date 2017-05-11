import java.net.*;
import java.io.*;
import java.util.*;

public class TCPClient 
{
	public static void teste (String args[],Socket s,String data,Scanner ler)
	{
		//	DataOutputStream  out =	new DataOutputStream( s.getOutputStream());

	}

	public static void main (String args[]) 
	{
		// arguments supply message and hostname of destination
		boolean handshake=false;
		Socket s = null;
		Scanner ler = new Scanner(System.in);
		String localhost = "127.0.0.1";
		DataOutputStream out = null;
			int serverPort = 7890;
		try
		{
		
			s = new Socket(localhost, serverPort);
			DataInputStream in = new DataInputStream( s.getInputStream());
			out =	new DataOutputStream( s.getOutputStream());
			args[0]="0 0 000";//handshake
			out.writeUTF(args[0]);
			// UTF is a string encoding see Sn 4.3			
			String data = in.readUTF();
			int op;
			System.out.println("Menu\nRecebi: "+ data) ;


			System.out.println("Menu\n1-Consultar salas\n2-Reservar horario\n3-Cancelar reserva\n4-Sair\n\nOpcao: ");
			op = ler.nextInt();
			Scanner scanner = new Scanner( System.in );
			switch(op) {
					case 1:
						
//					scanner.nextLine(); 
						String nsala;
						String horario;
						System.out.println("\nCarregando...");
						
						s = new Socket(localhost, serverPort);
						in = new DataInputStream( s.getInputStream());
						out =	new DataOutputStream( s.getOutputStream());
						args[0]="1 1 001";
						out.writeUTF(args[0]);
						// UTF is a string encoding see Sn 4.3			
						data = in.readUTF();
						
						System.out.println("\n\n\n\nFormatar a string Recebi: "+ data) ;
			
						
						System.out.println("\nCarregando...");
			
						for (int j=0,numsala=1; j < 16 ; j=j+4,numsala++) {		
									System.out.println("\nSala "+numsala+"  horarios :");
									for (int i = 0; i < 4 ; i++) {		

															switch(i) {
																			case 0:							
																						System.out.println((data.charAt(i+j)=='1') ? "\n13 as 14 | Disponivel": "\n13 as 14 | Indisponivel"); 
																						break;
																			case 1:							
																						System.out.println((data.charAt(i+j)=='1') ? "\n14 as 15 | Disponivel": "\n14 as 15 | Indisponivel"); 
																						break;
																			case 2:							
																						System.out.println((data.charAt(i+j)=='1') ? "\n15 as 16 | Disponivel": "\n15 as 16 | Indisponivel"); 
																						break;
																			case 3:							
																						System.out.println((data.charAt(i+j)=='1') ? "\n16 as 17 | Disponivel": "\n16 as 17 | Indisponivel"); 
																						break;
																			default:
																						System.out.println("Erro fatal"	);	
															}																
											}
											System.out.println("-------------------------------");
								}
								break;
					case 2:
					
						System.out.println("\nCarregando...");
						
						s = new Socket(localhost, serverPort);
						in = new DataInputStream( s.getInputStream());
						out =	new DataOutputStream( s.getOutputStream());
						System.out.println("\nDigite o numero da sala");
						String a = scanner.nextLine(); 
						System.out.println("\nDigite o numero do horario\n") ;
						String b = scanner.nextLine();						
						//pegar o size do b pra converter
						int b1 = Integer.parseInt(b);
						if(b1 < 10)
							args[0]="2"+a+"00"+b;
						else if(b1<100)
							args[0]="2"+a+"0"+b;
						else							
							args[0]="2"+a+""+b;
						out.writeUTF(args[0]);
						// UTF is a string encoding see Sn 4.3			
						data = in.readUTF();
						int i = data.charAt(0)-48;
						if(i==5){
								System.out.println("\n\nReserva efetuada com sucesso ");
						}	
						if(i==6){
								System.out.println("\n\nO horario indisponivel");
						}							
						break;
					case 3:
					/*
						args[0]="3 3 003";
						System.out.println("Cancelar reserva enviada");
						*/
						System.out.println("\nCarregando...");
						
						s = new Socket(localhost, serverPort);
						in = new DataInputStream( s.getInputStream());
						out =	new DataOutputStream( s.getOutputStream());
						System.out.println("\nDigite o numero da sala que vc quer cancelar");
						a = scanner.nextLine(); 
						System.out.println("\nDigite o numero do horario vc quer cancelar\n") ;
						b = scanner.nextLine();						
						//pegar o size do b pra converter
						b1 = Integer.parseInt(b);
						if(b1 < 10)
							args[0]="3"+a+"00"+b;
						else if(b1<100)
							args[0]="3"+a+"0"+b;
						else							
							args[0]="3"+a+""+b;
						out.writeUTF(args[0]);
						// UTF is a string encoding see Sn 4.3			
						data = in.readUTF();
						i = data.charAt(0)-48;
						if(i==5){
								System.out.println("\n\nReserva cancelada com sucesso ");
						}	
						if(i==6){
								System.out.println("\n\nEste horario ja esta livre");
						}							
						break;

					case 4:
						System.exit(0);
					default:
						System.out.println("Digita direito fulero fdp!"	);
	
			}
	
			
			
		}
		catch (UnknownHostException e)
		{
			System.out.println("Sock:"+e.getMessage());
		}
		catch (EOFException e)
		{
			//System.out.println("EOF:"+e.getMessage());
		}
		catch (IOException e)
		{
		// 		System.out.println("IO:"+e.getMessage());
		}

		
			
			}		

		
}

