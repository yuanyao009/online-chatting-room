package server;

//package com.chinasofti.chat.server;
import java.awt.FlowLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
/*¿*
 * start the server
 *
 */
public class ServerStart extends JFrame
{
    private JButton btnStart=new JButton("启动");
    private JButton btnResume= new JButton("Resume");
	private JButton btnStop=new JButton("停止");
	ChatServer s;
	private ActionListener arg0=new ActionListener() 
	{		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			if(arg0.getSource()==btnStart)
			{
			  s=new ChatServer();
			  s.start();
			  btnStart.setEnabled(false);
			
			}
//			if(arg0.getSource()==btnStop)
//			{
////				  s.stop();
//				  s.stop();

//				 btnStart.setEnabled(true);
//			}
//			
		}
	};
	private ActionListener arg1=new ActionListener() 
	{		
		@Override
		public void actionPerformed(ActionEvent arg1) {
			// TODO Auto-generated method stub
//			if(arg0.getSource()==btnStart)
//			{
//			  s=new ChatServer();
//			  s.start();
//			  btnStart.setEnabled(false);
//			
//			}
			if(arg1.getSource()==btnStop)
			{
//				  s.stop();

				  s.suspend();
				  System.out.println("server colsed");
//				 btnStart.setEnabled(true);
			}
			
		}
	};	
	private ActionListener arg2=new ActionListener() 
	{		
		@Override
		public void actionPerformed(ActionEvent arg2) {
			// TODO Auto-generated method stub
//			if(arg0.getSource()==btnStart)
//			{
//			  s=new ChatServer();
//			  s.start();
//			  btnStart.setEnabled(false);
//			
//			}
			if(arg2.getSource()==btnResume)
			{
//				  s.stop();
				  s.resume();
				  System.out.println("restart the server");				 
//				 btnStart.setEnabled(true);
			}
			
		}
	};	
	public ServerStart()
	{
		JLabel mainFrame=new JLabel("Chating Room Server");
		setLayout(new FlowLayout());
		this.add(mainFrame);
		this.add(btnStart);
		this.add(btnStop);
		this.add(btnResume);
		btnStart.addActionListener(arg0);
		btnStop.addActionListener(arg1);
		btnResume.addActionListener(arg2);
		setSize(400,400);
		setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
	}
	public static void main(String[] args) 
	{
	  new ServerStart();

	}

}