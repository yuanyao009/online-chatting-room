package server;
//package com.chinasofti.chat.server;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
/**

 *give every user a process
 */
public class FriendsThread extends Thread 
{
	private Socket socket;
	private DataOutputStream dout;
	private DataInputStream din;
	private boolean bool=true;
	
	public boolean isBool() {
		return bool;
	}
	public void setBool(boolean bool) {
		this.bool = bool;
	}
	public FriendsThread(Socket socket)
	{
		this.socket=socket;
		try
		{
		  dout=new DataOutputStream(this.socket.getOutputStream());
		  din=new DataInputStream(this.socket.getInputStream());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@Override
	public void run()
	{
		while(bool)
		{
		   try 
		   {
			String info=din.readUTF();			
			String t[]=info.split(",");
			if(t[0].equals("quit")) {
				UserVo use1 = LoginUser.map.get(t[1]);
				System.out.println(use1.getUsername()+"quit the system");
				new Remind().remindQuit(use1);
				LoginUser.map.remove(t[1]);
				use1.SocketClose();
				return;
			}
			if(t[0].equals("login"))
			{	
				dout.writeUTF("successful login in");
				UserVo user = new UserVo(t[1],t[2],socket);
				new Remind().remindOne(socket);
				LoginUser.map.put(t[1], user);
				new Remind().remindAll(user);
			}
			if(t[0].equals("info"))
			{	
				String sendname=t[1];
				String sayname = t[2];
				String inf  = t[3];
				new SendMSG().sendInfo(sendname, sayname,inf);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		
	}

}
