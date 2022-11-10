/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package texteditorr;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.filechooser.*;
import javax.swing.text.Document;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;

public class TextEditor extends JFrame implements ActionListener {
 
 UndoManager um =new UndoManager();
 JTextArea textArea;
 JScrollPane scrollPane;
 JLabel fontLabel;
 JSpinner fontSizeSpinner;
 JButton fontColorButton;
 JComboBox fontBox; 
 JMenuBar menuBar;
 JMenu fileMenu;
 JMenu edit;
 JMenuItem openItem;
 JMenuItem saveItem;
 JMenuItem exitItem;
 JMenuItem newItem;
 JMenuItem copyItem;
 JMenuItem cutItem;
 JMenuItem pasteItem;
 JMenuItem ileri;
 JMenuItem geri;

 public TextEditor(){
    
  this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  this.setTitle("TEXT EDİTOR");
  this.setSize(500, 500);
  this.setLayout(new FlowLayout());
  this.setLocationRelativeTo(null);
  
  textArea = new JTextArea();
  textArea.setLineWrap(true);
  textArea.setWrapStyleWord(true);
  textArea.setFont(new Font("Arial",Font.PLAIN,20));
  
  scrollPane = new JScrollPane(textArea);
  scrollPane.setPreferredSize(new Dimension(450,450));
  scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
  
  fontLabel = new JLabel("Font: ");
  
  fontSizeSpinner = new JSpinner();
  fontSizeSpinner.setPreferredSize(new Dimension(50,25));
  fontSizeSpinner.setValue(20);
  fontSizeSpinner.addChangeListener(new ChangeListener() {

   @Override
   public void stateChanged(ChangeEvent e) {
    
    textArea.setFont(new Font(textArea.getFont().getFamily(),Font.PLAIN,(int) fontSizeSpinner.getValue())); 
   }
   
  });
  
  fontColorButton = new JButton("Color");
  fontColorButton.addActionListener(this);
  
  String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
  
  fontBox = new JComboBox(fonts);
  fontBox.addActionListener(this);
  fontBox.setSelectedItem("Arial");
  
  // ------ menubar ------
  
   menuBar = new JMenuBar();
   fileMenu = new JMenu("Dosya İşlemleri");
   openItem = new JMenuItem("Dosya Aç");
   saveItem = new JMenuItem("Dosya Kaydet");    
   newItem = new JMenuItem("Yeni Dosya");
   exitItem = new JMenuItem("Çıkış");
   
   edit = new JMenu("Edit");
   cutItem = new JMenuItem("Kes");
   copyItem = new JMenuItem("Kopyala");
   pasteItem = new JMenuItem("Yapıştır");
   ileri = new JMenuItem("ileri");
   geri = new JMenuItem("geri");
   openItem.addActionListener(this);
   saveItem.addActionListener(this);
   newItem.addActionListener(this);
   exitItem.addActionListener(this);
   cutItem.addActionListener(this);
   copyItem.addActionListener(this);
   pasteItem.addActionListener(this);
   ileri.addActionListener(this);
   geri.addActionListener(this);
   fileMenu.add(openItem);
   fileMenu.add(saveItem);
   fileMenu.add(newItem);
   fileMenu.add(exitItem);
   edit.add(cutItem);
   edit.add(copyItem);
   edit.add(pasteItem);
   edit.add(ileri);
   edit.add(geri);

   menuBar.add(fileMenu);
   menuBar.add(edit);
  // ------ /menubar ------
   
  this.setJMenuBar(menuBar);
  this.add(fontLabel);
  this.add(fontSizeSpinner);
  this.add(fontColorButton);
  this.add(fontBox);
  this.add(scrollPane);
  this.setVisible(true);
 }
 
 @Override
 public void actionPerformed(ActionEvent e) {
  
  if(e.getSource()==fontColorButton) {
   JColorChooser colorChooser = new JColorChooser();
   
   Color color = colorChooser.showDialog(null, "Choose a color", Color.black);
   
   textArea.setForeground(color);
  }
  
  if(e.getSource()==fontBox) {
   textArea.setFont(new Font((String)fontBox.getSelectedItem(),Font.PLAIN,textArea.getFont().getSize()));
  }
  
  if(e.getSource()==openItem) {
   JFileChooser fileChooser = new JFileChooser();
   fileChooser.setCurrentDirectory(new File("."));
   FileNameExtensionFilter filter = new FileNameExtensionFilter("Text files", "txt");
   fileChooser.setFileFilter(filter);
   
   int response = fileChooser.showOpenDialog(null);
   
   if(response == JFileChooser.APPROVE_OPTION) {
    File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
    Scanner fileIn = null;
    
    try {
     fileIn = new Scanner(file);
     if(file.isFile()) {
      while(fileIn.hasNextLine()) {
       String line = fileIn.nextLine()+"\n";
       textArea.append(line);
      }
     }
    } catch (FileNotFoundException e1) {
     // TODO Auto-generated catch block
     e1.getStackTrace();
    }
    finally {
     fileIn.close();
    }
   }
  }
  if(e.getSource()==saveItem) {
   JFileChooser fileChooser = new JFileChooser();
   fileChooser.setCurrentDirectory(new File("."));
   
   int response = fileChooser.showSaveDialog(null);
   
   if(response == JFileChooser.APPROVE_OPTION) {
    File file;
    PrintWriter fileOut = null;
    
    file = new File(fileChooser.getSelectedFile().getAbsolutePath());
    try {
     fileOut = new PrintWriter(file);
     fileOut.println(textArea.getText());
    } 
    catch (FileNotFoundException e1) {
     // TODO Auto-generated catch block
     e1.getStackTrace();
    }
    finally {
     fileOut.close();
    }   
   }
  }

    if (e.getSource()==geri){
   // um.undo();
    }
    if (e.getSource()==ileri){
    //   um.redo();
    }
       
    if (e.getSource()==cutItem)
            textArea.cut();
    if (e.getSource()==copyItem)
            textArea.copy();
    if (e.getSource()==pasteItem)
            textArea.paste();
    if (e.getSource()==newItem) textArea.setText("");
    if(e.getSource()==exitItem) {
            System.exit(0);
  }  
 }
}

