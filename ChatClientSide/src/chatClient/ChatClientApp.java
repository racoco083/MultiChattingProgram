package chatClient;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Inet4Address;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class ChatClientApp {
	private static String SERVER_IP;// = "192.168.0.2";
	private static int SERVER_PORT;// = 5000;

	public static void main(String[] args) {
		SERVER_IP = args[0];
		SERVER_PORT = Integer.parseInt(args[1]);
		String name = null;
		Scanner scanner = new Scanner(System.in);
		
		while (true) {

			System.out.println("��ȭ���� �Է��ϼ���.");
			System.out.print(">>> ");
			name = scanner.nextLine();

			if (name.isEmpty() == false) {
				break;
			}
			System.out.println("��ȭ���� �ѱ��� �̻� �Է��ؾ� �մϴ�.\n");
		}

		scanner.close();

		Socket socket = new Socket();
		try {
			socket.connect(new InetSocketAddress(SERVER_IP, SERVER_PORT));
			consoleLog("ä�ù濡 �����Ͽ����ϴ�.");
			new ChatWindow(name, socket).show();
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8),
					true);
			String request = "join:" + name + "\r\n";
			pw.println(request);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void consoleLog(String log) {
		System.out.println(log);
	}
}