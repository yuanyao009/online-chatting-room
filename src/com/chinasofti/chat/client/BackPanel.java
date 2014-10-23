package client;

//package com.chinasofti.chat.client;

import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * background
 *
 */
public class BackPanel extends JPanel{
	 private ImageIcon image = null; 
	 public BackPanel( ) {
	  image = new ImageIcon("image/a3.png");
	 } 
	 protected void paintComponent( Graphics g ) {
	  setOpaque(true);
	  super.paintComponent(g);
	    g.drawImage(image.getImage(), -50, 10, null, null);
	 }
}
