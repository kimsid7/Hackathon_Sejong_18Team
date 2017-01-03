package blog.naver.com.since201109.pdf;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.*;

public class pLectlist extends JFrame {
   
   private ArrayList<String> list;   
   private File chosenfile;
   private String filename;
   private JButton addBtn,okBtn;
   private JList Lectlist;
   private ImageIcon im;
   private Data[] lectdata; 
   private Data tplectdata;
   private JButton saveBtn;
   private String sfiledata;
   private String sfilename;
   private String[] header;
   private String[] listHeader;
   private String SnS;
   
   private JButton openBtn;
   
   private int initSize;
   private File initFile;
   
   private ImageIcon icon;
   
   pLectlist(String SemesterNsubject)
   {
      SnS = new String();
      SnS =  SemesterNsubject;
      setTitle("���Ǽ���");
      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      setLayout(null);
      setPreferredSize(new Dimension(200,300));
      this.setLocation(400, 200);
      icon = new ImageIcon("sejong.jpg");
		 JPanel background = new JPanel() {
	            public void paintComponent(Graphics g) {
	                // Approach 1: Dispaly image at at full size
	                g.drawImage(icon.getImage(), 0, 0, null);
	                // Approach 2: Scale image to size of component
	                // Dimension d = getSize();
	                // g.drawImage(icon.getImage(), 0, 0, d.width, d.height, null);
	                // Approach 3: Fix the image position in the scroll pane
	                // Point p = scrollPane.getViewport().getViewPosition();
	                // g.drawImage(icon.getImage(), p.x, p.y, null);
	                setOpaque(false); //�׸��� ǥ���ϰ� ����,�����ϰ� ����
	                super.paintComponent(g);
	            }
	        };
	        background.setLayout(null);
	        background.setSize(400,400);
	        background.setVisible(true);
	        
      lectdata = new Data[100];
      header = new String[100];
      listHeader = new String[100];
      //for(int i=0;i<100;i++)
      //{
      //   header[i]="-1";
      //}
      list = new ArrayList<String>();

      addBtn = new JButton("+");
      okBtn = new JButton("Ȯ��");
      //saveBtn = new JButton("save");
      //openBtn = new JButton("open");
      
      addBtn.setBounds(125, 50, 50, 50);
      addBtn.setIcon(im);
      okBtn.setBounds(50, 220, 100, 30);
      
      // �ٸ��� �����ؼ� �ҷ����� �ϱ����� ����Ʈ ���� �ʱ�ȭ ����� �ȴ�.
      //saveBtn.setBounds(120, 10, 100, 30);
      //openBtn.setBounds(120 , 150 , 100,30);
      //add(saveBtn);
      //add(openBtn);
      Lectlist = new JList(list.toArray());
      Lectlist.setBounds(10,10 , 100, 200);
      
      System.out.println(list.toArray().length);
   
      addBtn.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent arg0)
         {
            
            filechooser();
            if(chosenfile.exists())
            {
               list.add(chosenfile.getName());
               Lectlist.setListData(list.toArray());
               lectdata[list.size()-1] = new Data(chosenfile.getName(), list.size()-1);
               System.out.println(chosenfile.getName());
               System.out.println(list.size());
               filesave();
            }
            else
               {
               
            }
         }
      });
      
      okBtn.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent arg0)
         {
//            System.out.println(lectdata[Lectlist.getSelectedIndex()].getfile().getName());
//            System.out.println(Lectlist.getSelectedIndex());
            
            //chosenfile = new File();
            new PDFJframeViewer((lectdata[Lectlist.getSelectedIndex()].getfile()));
            //dispose();
            
         }
      });
      
      /*saveBtn.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent arg0)
         {
            filesave();
            

            
         }
      });
      
      openBtn.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent arg0)
         {
            fileopen();

         }
      });
      */
      fileopen();
      Lectlist.setSelectedIndex(0);
      add(addBtn);
      add(okBtn);
      add (Lectlist);
      add(background);
      setVisible(true);
      pack();
      
      
      
      //System.out.println(list.get(0));
   }
   
   public void filechooser()
   {
      JFileChooser chooser;
      chooser = new JFileChooser();
      chooser.showOpenDialog(this);
      chosenfile = chooser.getSelectedFile();
      filename = chooser.getSelectedFile().getName();
      
   } // ���� chooser ����
   public File getFile()
   {
      return chosenfile;
   }
   public void filesave()
   {
      try{
         //BufferedReader Reader = new BufferedReader(new InputStreamReader(area.getText()));
         
         
            BufferedWriter writer = new BufferedWriter(new FileWriter(SnS)); // ���� chooser���� ���, �̸��� �����´�
            //1�б�_�˰���//sfilename = lectdata[i].getfile().getPath() + ".txt";
            sfiledata = new String();
            
            for(int i=0;i<list.size();i++)
            {   
            sfiledata += (lectdata[i].getfile() + "\r\n") ;
            System.out.println(lectdata[i].getfile());
         
            }
            writer.write(sfiledata); // ������ ���� �̸� 
            writer.close();
         
      }
      
      catch(Exception e){
         e.printStackTrace();
      }
   } // ���� �����ϴ� �Լ�
   
   public void fileopen()
   {
      try{
         if(list.size()==0)
         {
            String s = new String();
            String s1 = new String();

            BufferedReader reader = new BufferedReader(new FileReader(SnS)); // ���� �� ���
            while((s=reader.readLine())!=null)
               {
                  s1+=s+"\r\n";
               } // ���Ͽ��� ���پ� �޾ƿͼ� ��Ʈ���� �״´�.

            
         
            header = s1.split("(\r\n)");

         
            for(int i=0;i<header.length;i++)
            {
               System.out.println(header.length);
               System.out.println(header[i]);
            }
            
   
            for(int i=0;i<header.length;i++)
            {
               list.add(header[i]);
               Lectlist.setListData(list.toArray());
               lectdata[list.size()-1] = new Data(header[i], list.size()-1);
               System.out.println(list.size());
               
            }
            
            reader.close();
   
         }
         else
         {
            //BufferedReader Reader = new BufferedReader(new InputStreamReader(area.getText()));
            String s = new String();
            String s1 = new String();
            s1="";
            s="";
            BufferedReader reader = new BufferedReader(new FileReader(sfilename)); // ���� �� ���
            while((s=reader.readLine())!=null)
               {
                  s1+=s+"\r\n";
               } // ���Ͽ��� ���پ� �޾ƿͼ� ��Ʈ���� �״´�.
            System.out.println(s1); // area�� ���ؽ�Ʈ
            
            
            reader.close();
         //}
      }
      }
      catch(Exception e){
         e.printStackTrace();
      }
    
   
}
}