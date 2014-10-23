package server;

//package com.chinasofti.chat.server;

import java.io.IOException;
import java.net.Socket;

/**
 * user information
 *
 */
public class UserVo {
	private String username;
	private String sex;
	private Socket soc;
	public UserVo(String username, String sex, Socket soc) {
		super();
		this.username = username;
		this.sex = sex;
		this.soc = soc;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Socket getSoc() {
		return soc;
	}
	public void setSoc(Socket soc) {
		this.soc = soc;
	}
	
	public void SocketClose() {
		try {
			this.soc.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}
