package server;

//package com.chinasofti.chat.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**server
 *
 */
public class ChatServer extends Thread{
		private static boolean bool=true;
		public void run() {
			startServer();
		}
		
		public static void startServer()
		{
			try 
			{
				ServerSocket server=new ServerSocket(9999);		
				System.out.println("server is starting............");
				while(bool)
				{
					Socket s=server.accept();
	                FriendsThread ft=new FriendsThread(s);		
	                ft.start();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		public static void close()
		{
			Thread.currentThread().stop();
		}
		
}
