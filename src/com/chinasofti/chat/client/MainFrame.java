package client;


import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Scrollbar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.Scrollable;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
/**
 *
 * charting interface, every user has a process for reciving the message from server
 *
 */
public class MainFrame extends JFrame implements ListSelectionListener,ItemListener,Runnable{
		Border titleBorder1=BorderFactory.createTitledBorder("main ");
		Border titleBorder2=BorderFactory.createTitledBorder("user channel");
		Border titleBorder3=BorderFactory.createTitledBorder("user list");
		Box b3= Box.createHorizontalBox();  //private chart
		Box b5= Box.createHorizontalBox();
		Box b2= Box.createVerticalBox();
		Box b= Box.createVerticalBox();
		
		ImageIcon  ico1= new ImageIcon("image/a3.jpg");
		JScrollPane commonArea;
		JScrollPane selfArea;
		JScrollPane userlist;
		JPanel j4 = new JPanel(new FlowLayout(0));
		JPanel j6 = new JPanel(new FlowLayout(0));
		JPanel j5 = new JPanel(new BorderLayout());
		JTextArea jt1;				//public chart
		JTextArea jt2;
		JList jl1;
		JLabel jla;
		JLabel jima  = new JLabel();
		JLabel juser = new JLabel("the current user         ");
		JLabel jla1;
		JComboBox jc1;
		JCheckBox jch1;
		JButton jsend;		
		JTextField send1= new JTextField(36) ;
		JButton send2 =new JButton("send message");
		JButton send3=new JButton("refresh");
		private DataOutputStream dout;		//send the message to server
		private DataInputStream din;		//recieve the message from server
		private DefaultListModel lis1;
		private DefaultComboBoxModel comb;
		private String sendname;
		private boolean isscret;
		
		public MainFrame(String user,DataOutputStream dout,DataInputStream din) {
			isscret = false;
			sendname=null;
			lis1 = new DefaultListModel();
			comb = new DefaultComboBoxModel();
			comb.addElement("everyon");
			this.dout = dout;
			this.din = din;
			jla1= new JLabel(user);
			jt1 = new JTextArea(10,30);
			jt2 = new JTextArea(10,30);
			jl1 = new JList(lis1);
			jl1.addListSelectionListener(this);
			jc1 = new JComboBox(comb);
			jch1 = new JCheckBox("private chat");
			jch1.addItemListener(this);
			jla = new JLabel("yes");
			jsend = new JButton("shack");
			commonArea= new JScrollPane(jt1);
			commonArea.setSize(200, 200);
			selfArea= new JScrollPane(jt2);
			commonArea.setBorder(titleBorder1);
			selfArea.setBorder(titleBorder2);
			selfArea.setSize(200, 200);
			userlist = new JScrollPane(jl1);
			b2.add(commonArea);
			b2.add(selfArea);
			b3.add(jla);
			b3.add(jc1);
			b3.add(jch1);
			b3.add(jsend); 
			b2.add(b3);
			b5.add(b2);
			b5.add(userlist);
			j4.add(send1);
			j4.add(send2);
			send2.addActionListener(sendmsg);
			j4.add(send3);
			jima.setIcon(ico1);
			j6.add(jima);
			j6.add(juser);
			j6.add(jla1);
			b.add(j6);
			b.add(b5);
			b.add(j4);
			this.add(b);
			this.setSize(650, 500);
			this.setLocationRelativeTo(null);
			this.setResizable(false);
			this.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent event) {
					try {
						MainFrame.this.dout.writeUTF("quit,"+jla1.getText());
						System.exit(0);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			setTitle(user+"chating windows");
			this.setVisible(true);
			Thread t=new Thread(this);
			t.start();
			
		}
		
		//发送信息
		ActionListener sendmsg = new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if(isscret == false) {
					sendname = (String) jc1.getSelectedItem();
					if(!(sendname.equals("everyone"))){
						JOptionPane.showMessageDialog(null, "private chatting ?");
					} else{
						try {
							dout.writeUTF("info,"+sendname+","+jla1.getText()+","+send1.getText());
							send1.setText("");
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				} else{
					sendname = (String) jc1.getSelectedItem();
					if(sendname.equals("everyone")) {
						JOptionPane.showMessageDialog(null, "please choose the user");
					} else{
						if(sendname.equals(jla1.getText())) {
							JOptionPane.showMessageDialog(null, "cannot send information to yourself");
						}else {
							try {
								dout.writeUTF("info,"+sendname+","+jla1.getText()+","+send1.getText());
								send1.setText("");
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					}
				}

			}
		};

		
		//选择私聊对象
		public void valueChanged(ListSelectionEvent e) {
			if(e.getSource()==jl1) {
				String s = (String) jl1.getSelectedValue();
				if(s!=null) {
					String s1 = s.substring(0, s.indexOf("【"));
					jc1.setSelectedItem(s1);
				}
			}
			
		}

		//是否私聊
		public void itemStateChanged(ItemEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource()==jch1) {
				if(jch1.isSelected()) {
					isscret = true;
				} else{
					isscret  = false;
				}
			}
		}

		//单独线程，不断接收服务器发送的信息
		public void run() {
			// TODO Auto-generated method stub
			while(true) {
				String info = null;
				try {
					if((info = din.readUTF())!=null) {
						String[] s = info.split(",");
						if(s[0].equals("remind")) {
							lis1.addElement(s[1]+"【"+s[2]+"】");
							comb.addElement(s[1]);
						} else if(s[0].equals("new")) {
							lis1.addElement(s[1]+"【"+s[2]+"】");
							comb.addElement(s[1]);
							jt1.append("【【公告信息】】："+s[1]+"加入了聊天频道\n");
						} else if(s[0].equals("all")) {
							jt1.append("【"+s[1]+"说:】  "+s[2]+"\n");
						} else if(s[0].equals(jla1.getText())) {
							jt2.append("【"+s[1]+"悄悄的说:】"+s[2]+"\n");
						} else if(s[0].equals("quit")) {
							jt1.append("【【公告信息】】："+s[1]+"退出了频道\n");
							lis1.removeElement(s[1]+"【"+s[2]+"】");
							comb.removeElement(s[1]);
						} else{
							
						}
					} else {
					}
				} catch (IOException e) {
					System.exit(0);
				}
			}
		}
}
