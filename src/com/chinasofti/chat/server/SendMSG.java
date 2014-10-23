package server;

//package com.chinasofti.chat.server;
import java.io.DataOutputStream;
import java.net.Socket;

/**
 *send information
 */
public class SendMSG 
{
	private  UserVo user;
	private  Socket soc;
	private  DataOutputStream dout;
	public void sendInfo(String sendname,String sayname,String info)
	{
		try{
			
			//public chatting
			if(sendname.equals("everyone")) {
				for(String s:LoginUser.map.keySet()) {
					user = LoginUser.map.get(s);
					soc = user.getSoc();
					dout=new DataOutputStream(soc.getOutputStream());
					dout.writeUTF("all,"+sayname+","+info);
				}
			}
			
			//private chatting
			else {
				user=LoginUser.map.get(sendname);
				soc = user.getSoc(); 
				dout=new DataOutputStream(soc.getOutputStream());
				dout.writeUTF(sendname+","+sayname+","+info);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	

}

