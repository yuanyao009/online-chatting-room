package client;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 * login window
 *
 */
public class Chat extends JFrame implements ActionListener
{

	BackPanel j3 =  new BackPanel();	
	JLabel username;
	JLabel address;
	JLabel socket;
	JLabel passwd;
	JTextField jusername;
	JPasswordField jpasswd;
	JTextField jaddress;
	JTextField jsocket;
	JButton login;
	JButton quit;
	JButton regedit;
	JRadioButton sex1;
	JRadioButton sex2;
	JRadioButton sex3;
	Box b1= Box.createHorizontalBox();
	Box b2= Box.createHorizontalBox();
	Box b4= Box.createHorizontalBox();
	Box b3= Box.createVerticalBox();
	Box b5= Box.createHorizontalBox();
	ButtonGroup bu1;
	private OutIn in;		
	private DataInputStream  din;
	private DataOutputStream dout;
	private String sex;
	
	public Chat() 	{
		username = new JLabel("name");
		address =new JLabel("address");
		socket = new JLabel("port");
		jusername = new JTextField(12);
		jaddress  = new JTextField("localhost");
		jsocket = new JTextField("9999");
		login = new JButton("connect");
		quit =  new JButton("exit");
		login.addActionListener(this);
		quit.addActionListener(this);
		b5.add(Box.createHorizontalStrut(10));		//windows
		b5.add(Box.createHorizontalStrut(185));
		b1.add(Box.createHorizontalStrut(10));
		b1.add(username);
		b1.add(jusername);
		b2.add(Box.createHorizontalStrut(10));
		b2.add(address);
		b2.add(jaddress);
		b2.add(Box.createHorizontalStrut(40));
		b2.add(socket);
		b2.add(jsocket);
		b3.add(b1);
		b3.add(Box.createVerticalStrut(12));
		b3.add(b5);
		b3.add(Box.createVerticalStrut(12));
		b3.add(b2);
		b4.add(login);
		b4.add(Box.createHorizontalStrut(40));
		b4.add(quit);
		b4.add(Box.createHorizontalStrut(40));
		b3.add(b4);
		j3.add(b3);
		setTitle("Instance Chating Room Client___User End");
		this.add(j3,BorderLayout.SOUTH);
		this.setSize(450, 160);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		sex=null;
		this.setResizable(false);
		this.setVisible(true);					
	}
	
	public static void main(String[] args) {
		new Chat();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==quit) {
			System.exit(-1);
		}
		if(e.getSource()==login) {
			if(jusername.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "please input your name：");
			} 
			 else {
				in = new OutIn(jaddress.getText(),Integer.parseInt(jsocket.getText()));
				dout = in.getOutStream();
				din = in.getInstream();
				try {
					dout.writeUTF("login,"+jusername.getText()+","+sex);
					String s1 = din.readUTF(); 
					if(s1.equals("success login")) {
						new MainFrame(jusername.getText(),dout,din);
						this.dispose();
					}
				} catch (IOException e1) {
//					System.out.println("someone drop out");
				}
			}
		}
		if(e.getSource()==regedit) {
			
		}
	}
}
