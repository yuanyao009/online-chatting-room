package client;

//package com.chinasofti.chat.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * construct the communication
 *
 */
public class OutIn {
	DataOutputStream dout;
	DataInputStream din;
	Socket socket;
	
	public OutIn(String add,int soc) {
		try {
			socket=new Socket(add,soc);		
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public DataOutputStream getOutStream() {
		try {
		   dout=new DataOutputStream(socket.getOutputStream());
		}  catch(Exception e) {
			e.printStackTrace();
		}		
		return dout;
	}
	
	public DataInputStream getInstream() {
		try {
			din=new DataInputStream(socket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return din;	
	}

}