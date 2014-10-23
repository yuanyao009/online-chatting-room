package server;

//package com.chinasofti.chat.server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * add the new user into list
 *
 */
public class Remind {
	private UserVo user;
	private DataOutputStream dout;

	
		public void remindOne(Socket soc) {
			for(String s:LoginUser.map.keySet()) {
				user = LoginUser.map.get(s);
				try {
					dout=new DataOutputStream(soc.getOutputStream());
					dout.writeUTF("remind,"+user.getUsername()+","+user.getSex());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		
		public void remindAll(UserVo user1) {
			for(String s:LoginUser.map.keySet()) {
				user = LoginUser.map.get(s);
				Socket soc = user.getSoc();
				try {
					dout=new DataOutputStream(soc.getOutputStream());
					dout.writeUTF("new,"+user1.getUsername()+","+user1.getSex());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		public void remindQuit(UserVo user1) {
			for(String s:LoginUser.map.keySet()) {
				user = LoginUser.map.get(s);
				Socket soc = user.getSoc();
				try {
					dout=new DataOutputStream(soc.getOutputStream());
					dout.writeUTF("quit,"+user1.getUsername()+","+user1.getSex());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
}
