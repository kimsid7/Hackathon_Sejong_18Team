package blog.naver.com.since201109.pdf;

import org.icepdf.ri.common.SwingController; 

import org.icepdf.ri.common.SwingViewBuilder;
import org.icepdf.ri.common.MyAnnotationCallback;
import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import show_frame.*;
import javax.sound.sampled.*;

public class PDFJframeViewer extends JFrame implements ListSelectionListener{
   JPanel viewerComponentPanel;
   JTextArea text = new JTextArea(10,10); // �ؽ�Ʈ �ڽ�
   Container con = getContentPane();   
   JToolBar tool = new JToolBar();
   ImageIcon icon1 = new ImageIcon("save.png");
   ImageIcon icon2 = new ImageIcon("load.png");
   JButton item1 = new JButton("New");
   JButton item2 = new JButton(icon1);
   JButton item3 = new JButton(icon2);
   JButton item7 = new JButton(new ImageIcon("calender.jpg"));
   JButton item4 = new JButton(new ImageIcon("pen.png"));
   JButton item5 = new JButton("font");
   
   JButton item_rec = new JButton( new ImageIcon("record.png"));
   JButton item_stop = new JButton( new ImageIcon("pause.png"));
   JButton item_play = new JButton( new ImageIcon("play.png"));
   JButton item_save = new JButton( new ImageIcon("savee.png"));
   JButton item_index = new JButton(new ImageIcon("index.png"));
   String[] bookmark;
   String[] bookmark2;
   JList list;
   JList list2;
   String[] s=new String[100];
   File file;
   JLabel label = new JLabel("���� ���");
   JButton btnNextPage;
   JButton btnPreviousPage;
   JTextField currentPage;
   int nowPage=0;
   JFileChooser jfc = new JFileChooser();
   JFrame frm = new JFrame();
   File recoder;
   int audioCount = 0;   
   int bookc = 0;
   private ArrayList<String> index_list;
   
   public PDFJframeViewer(String filePath){
      File f = new File(filePath);
      file = f;
      // Get a file from the command line to open
      // build a component controller
      SwingController controller = new SwingController();
      SwingViewBuilder factory = new SwingViewBuilder(controller);
      viewerComponentPanel = factory.buildViewerPanel();
      btnNextPage = factory.buildNextPageButton();
      btnPreviousPage=factory.buildPreviousPageButton();
      currentPage=factory.buildCurrentPageNumberTextField();
      for(int i=0;i<100;i++){
         s[i]="";
      }
      String fi;
      String fi_Audio;
      String str;
      int c=0;
      while(true){
         fi = "s_"+f.getName()+c+".txt";
      try{
      BufferedReader reader = new BufferedReader(new FileReader(fi));//������ �о����
      text.setText("");//������ �б����� �ʱ�ȭ
      while((str = reader.readLine())!=null){//������ ���� �о���� null���� �ƴϸ� ����
         s[c] = s[c]+str+ "\r\n";//���� �ִ� �ؽ�Ʈ�� ���� �о�� ���ڿ��� ���ؼ� ����� �ٹٲ�
      }
      c++;
      reader.close();
      }
      catch(Exception b){
         break;
      
      }
      }
      
      c=0;
      while(true){
         fi_Audio = "A_" + f.getName() + audioCount + ".wav";
      try{
      BufferedReader reader = new BufferedReader(new FileReader(fi_Audio));//������ �о����
      audioCount++;
      reader.close();
      }
      catch(Exception b){
         break;
      
      }
      }
      bookmark=new String[10];
      ccccc();
      text.setFont(new Font("����",Font.PLAIN,20));
      // add interactive mouse link annotation support via callback
      controller.getDocumentViewController().setAnnotationCallback(new MyAnnotationCallback(controller.getDocumentViewController()));

      JFrame applicationFrame = new JFrame();
      applicationFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      applicationFrame.getContentPane().add(viewerComponentPanel);
      applicationFrame.setLayout(null);
      viewerComponentPanel.setSize(800,950);
      viewerComponentPanel.setVisible(true);
      viewerComponentPanel.setLocation(0,0);
      // Now that the GUI is all in place, we can try openning a PDF
      controller.openDocument(filePath);

      
      applicationFrame.add(label);
      label.setSize(400,50);
      label.setLocation(900,700);
      label.setFont(new Font("����",Font.PLAIN,60));
      applicationFrame.add(tool);//���ʿ� ���ٸ� ����
      tool.setSize(600,35);
      tool.setLocation(800,0);
      tool.add(item2);//���ٿ� �����۵��� ����
      tool.add(item4);
      tool.add(item5);
      tool.add(item7);
      act act1 = new act();
      item2.addActionListener(act1);
      item4.addActionListener(act1);
      item5.addActionListener(act1);
      item7.addActionListener(act1);
      btnNextPage.addActionListener(act1);
      btnPreviousPage.addActionListener(act1);
      item_rec.addActionListener(act1);
       item_stop.addActionListener(act1);
       item_play.addActionListener(act1);
       item_save.addActionListener(act1);
       item_index.addActionListener(act1);
      
       con.add(text);
      con.add(new JScrollPane(text));
      text.setLocation(800,35);
      text.setSize(600,600);
      //�������� �̸�
      //��� ���� ����
      //���� ����
      //���� ����
      
      btnNextPage.setBounds(1090,640,30,30);
      btnPreviousPage.setBounds(1030,640,30,30);
      currentPage.setBounds(1060,640,30,30);
      
      item_rec.setBounds(850 , 700 , 40, 40);
       item_stop.setBounds(950 , 700 , 40, 40);
       item_play.setBounds(1050 , 700 , 40, 40);
       item_save.setBounds(1150 , 700 , 40, 40);
       item_index.setBounds(1250, 700, 40, 40);
         
       label.setBounds(950, 800, 400, 50);
         
       btnNextPage.setVisible(true);
       btnPreviousPage.setVisible(true);
       currentPage.setVisible(true);
         
       item_rec.setVisible(true);
       item_stop.setVisible(true);
       item_play.setVisible(true);
       item_save.setVisible(true);
       item_index.setVisible(true);
         
      nowPage = Integer.parseInt(currentPage.getText());
      
      btnNextPage.setVisible(true);
      btnPreviousPage.setVisible(true);
      currentPage.setVisible(true);
      currentPage.addActionListener(act1);
      currentPage.addKeyListener(new ke());
      
      list=new JList();
      list.setBounds(1400,35,200,500);
      list.setBorder(new LineBorder(Color.black));
      list.addListSelectionListener(this);
      applicationFrame.add(list);
      
      list2=new JList();
      list2.setBounds(1400,535,200,500);
      list2.setBorder(new LineBorder(Color.black));
      list2.addListSelectionListener(this);
      applicationFrame.add(list2);
      list2.setListData(bookmark2);
     
      JLabel jjj = new JLabel("���� ���");
      jjj.setSize(200,35);
      jjj.setVisible(true);
      jjj.setLocation(1430,0);
      applicationFrame.add(jjj);
      
      applicationFrame.add(btnNextPage);
      applicationFrame.add(btnPreviousPage);
      applicationFrame.add(currentPage);
      
      applicationFrame.add(text);
      
      applicationFrame.add(item_rec);
       applicationFrame.add(item_stop);
       applicationFrame.add(item_play);
       applicationFrame.add(item_save);
       applicationFrame.add(item_index);
         
       applicationFrame.add(label);
         
      // show the component
      applicationFrame.pack();
      applicationFrame.setSize(1600,1000);
      applicationFrame.setLocation(200,0);
      applicationFrame.setVisible(true);

      text.setText(s[0]);
      
   }
   public void ccccc(){
	      bookmark2 = new String[audioCount];
	      String fi_Audio;
	      for(int i=0;i<audioCount;i++){
	         fi_Audio = "A_" + file.getName() + i + ".wav";
	         try{
	            bookmark2[i]=fi_Audio;
	         }
	         catch(Exception b){
	            break;
	         }
	      }
	   }
   
   public void valueChanged(ListSelectionEvent event) {
	   int BUFFER_SIZE = 128000;
	   
	   if(event.getSource() == list2)
	   {
		   String filename=(String)list2.getSelectedValue();
		   try{
			   File soundFile = new File(filename);
			   final AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);
			   final AudioFormat audioFormat = audioStream.getFormat();
			   
			   DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
			   
			   final SourceDataLine sourceLine = (SourceDataLine) AudioSystem.getLine(info);
			   sourceLine.open(audioFormat);
			   
			   sourceLine.start();
			   	
			   Runnable runner = new Runnable(){
				   int bufferSize = (int) audioFormat.getSampleRate() * audioFormat.getFrameSize();
				   byte buffer[] = new byte[bufferSize];
				   
				   public void run()
				   {
					   try{
						   int count;
						   while ((count = audioStream.read(
				                   buffer, 0, buffer.length)) != -1) {
				                 if (count > 0) {
				                	 sourceLine.write(buffer, 0, count);
				                 }
				               }
						   sourceLine.drain();
						   sourceLine.close();
					   }catch(IOException e){
						   System.err.println("I/O problems : " + e);
					   }
				   }
			   };
			   
			   Thread playThread = new Thread(runner);
		         playThread.start();
		       } catch (LineUnavailableException e) {
		         System.err.println("Line unavailable: " + e);
		        
		       } catch (UnsupportedAudioFileException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
		        
	   }
      
      
   }
   public void add_viewer(JFrame con){
      con.getContentPane().add(viewerComponentPanel);
   }

   timer_record trmode = new timer_record();
   class act implements ActionListener{
      int count = 0;
      public void actionPerformed(ActionEvent e){
         
         if(e.getSource()==item2){
            int c=0;
            s[Integer.parseInt(currentPage.getText())-1]=text.getText();
               String str = "s_" + file.getName();
               try {
                  BufferedWriter writer = new BufferedWriter(new FileWriter(str+".txt"));
                  writer.write("");
               } catch (IOException e1) {
                  // TODO Auto-generated catch block
                  e1.printStackTrace();
               }
               while(s[c]!=""){
                  
               try{
                  BufferedWriter writer = new BufferedWriter(new FileWriter(str+c+".txt"));
                  writer.write(s[c]);//���ۿ� �ؽ�Ʈ ����
                  writer.close();
                  System.out.println("���� ����");
                  c++;
                  }
                  catch(Exception b){
                     b.printStackTrace();
                  }
               }
            }
         else if(e.getSource()==item7){
            calender c=new calender();
            c.setLocation(600,200);
            c.setVisible(true);
         }
         else if(e.getSource() == item_rec)
            {
                label.setText("������ �����մϴ�.");
                label.setFont(new Font("����" , Font.BOLD , 30));
                  
                trmode.captureAudio(); 
                trmode.stopwatch(1 , 1);
            }
            
            else if(e.getSource() == item_stop)
            {
               
               trmode.running = false;
               trmode.stopwatch(0 , 1);
                   
                   label.setText("��� �ð�: [ " + trmode.timerBuffer + "]");
                   label.setFont(new Font("����" , Font.BOLD , 30));
            }
            
            else if(e.getSource() == item_play)
            {
                label.setFont(new Font("����" , Font.BOLD , 30));
               
               label.setText("������ ����մϴ�.");
               trmode.playAudio();
            }
            
            else if(e.getSource() == item_save)
            {
            	 String str = "A_" + file.getName();
                 
                 recoder = new File(str+audioCount+".wav");
                 try{
                    trmode.save(recoder);
                    audioCount++;
                    ccccc();
                   list2.setListData(bookmark2);
                 }catch(Exception ex){
                    System.err.println(ex);
                 }
               
            }
            
            else if(e.getSource() == item_index)
            {
            	trmode.stopwatch(0, 0);
            	bookmark[bookc] = trmode.timerBuffer;
                bookc++;
                list.setListData(bookmark);
            }
         
   
         else if(e.getSource()==btnNextPage){
            s[Integer.parseInt(currentPage.getText())-1]=text.getText();
            text.setText(s[Integer.parseInt(currentPage.getText())]);
            nowPage = Integer.parseInt(currentPage.getText())+1;
         }
         else if(e.getSource()==btnPreviousPage){
            s[Integer.parseInt(currentPage.getText())-1]=text.getText();
            text.setText(s[Integer.parseInt(currentPage.getText())-2]);
            nowPage = Integer.parseInt(currentPage.getText())-1;
         }
         else if(e.getSource()==item5){
            new font(text);
         }
      }
   }
   class ke extends KeyAdapter{
      public void keyPressed(KeyEvent e){
         if(e.getKeyCode() == KeyEvent.VK_ENTER){
            if(Integer.parseInt(currentPage.getText())<1 ||Integer.parseInt(currentPage.getText())>100){
               System.out.println("ERROR!!");
               currentPage.setText(""+nowPage);
            }
            else{
               s[nowPage-1]=text.getText();
               text.setText(s[Integer.parseInt(currentPage.getText())-1]);
               nowPage = Integer.parseInt(currentPage.getText());
            }
         }
      }
   }

}

class timer_record
{
   ByteArrayOutputStream out;
   protected boolean running = false;
   AudioInputStream aaa;
    static String timerBuffer; // 04:11:15 ���� ��� �ð� ���ڿ��� ����� ���� ����
    static int oldTime; // Ÿ�̸Ӱ� ON �Ǿ��� ���� �ð��� ����ϰ� �ִ� ����
    byte saving[];
    public static void stopwatch(int onOff , int indexOff) {
          if (onOff == 1) // Ÿ�̸� �ѱ�
            oldTime = (int) System.currentTimeMillis() / 1000;

          if (onOff == 0 && indexOff == 1) // Ÿ�̸� ����, �ú��ʸ� timerBuffer �� ����
            secToHHMMSS(  ((int) System.currentTimeMillis() / 1000) - oldTime  );
          
          if(indexOff == 0 && indexOff == 0)
        	secToHHMMSS(  ((int) System.currentTimeMillis() / 1000) - oldTime  );
        }


   // ������ �� �ð��� �ʴ���(sec)�� �Է� �޾�, "04:11:15" ���� ������ ���ڿ��� �ú��ʸ� ����
   public static void secToHHMMSS(int secs) {
       int hour, min, sec;
       sec  = secs % 60;
       min  = secs / 60 % 60;
       hour = secs / 3600;

       timerBuffer = String.format("%02d:%02d:%02d", hour, min, sec);
   }

        
    void captureAudio() {
          try {
            final AudioFormat format = getFormat();
            
            DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
            final TargetDataLine line = (TargetDataLine)AudioSystem.getLine(info);
            line.open(format);
            line.start();
            Runnable runner = new Runnable() {
              int bufferSize = (int)format.getSampleRate() * format.getFrameSize();
              byte buffer[] = new byte[bufferSize];
             
              public void run() {
               saving = buffer;
                out = new ByteArrayOutputStream();
                running = true;
                try {
                  while (running) {
                    int count = 
                      line.read(buffer, 0, buffer.length);
                    if (count > 0) {
                       
                      out.write(buffer, 0, count);
     
                    }
                  }
                  out.close();
                
                } catch (IOException e) {
                  System.err.println("I/O problems: " + e);
                  System.exit(-1);
                }
                line.close();
              }
            };
           
            Thread captureThread = new Thread(runner);
            captureThread.start();
            }catch (LineUnavailableException e) {
            System.err.println("Line unavailable: " + e);
            System.exit(-2);
          }
        }
      
    void playAudio()  {
       try {
         byte audio[] = out.toByteArray();
         InputStream input = new ByteArrayInputStream(audio);
         final AudioFormat format = getFormat();
         final AudioInputStream ais = new AudioInputStream(input, format,audio.length / format.getFrameSize());
       
         DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
         final SourceDataLine line = (SourceDataLine)AudioSystem.getLine(info);
         line.open(format);
         line.start();
         Runnable runner = new Runnable() {
           int bufferSize = (int) format.getSampleRate() * format.getFrameSize();
           byte buffer[] = new byte[bufferSize];

           public void run() {
             try {
               int count;
               while ((count = ais.read(
                   buffer, 0, buffer.length)) != -1) {
                 if (count > 0) {
                   line.write(buffer, 0, count);
                 }
               }
               line.drain();
               line.close();
             } catch (IOException e) {
               System.err.println("I/O problems: " + e);
               System.exit(-3);
             }
           }
         };
         Thread playThread = new Thread(runner);
         playThread.start();
       } catch (LineUnavailableException e) {
         System.err.println("Line unavailable: " + e);
         System.exit(-4);
       } 
     }
    
    void save(File file) throws IOException
    {
       byte audio[] = out.toByteArray();
       InputStream input = new ByteArrayInputStream(audio);
        final AudioFormat format = getFormat();
        final AudioInputStream ais = new AudioInputStream(input, format,audio.length / format.getFrameSize());
        
       AudioSystem.write(ais , AudioFileFormat.Type.WAVE, file);
    }
    
     private AudioFormat getFormat() {
       float sampleRate = 8000;
       int sampleSizeInBits = 8;
       int channels = 1;
       boolean signed = true;
       boolean bigEndian = true;
       return new AudioFormat(sampleRate,sampleSizeInBits, channels, signed, bigEndian);
     }
}

class font extends JFrame{//��Ʈ�� ������ Ŭ����
   String[] name ={ "Serif", "SansSerif", "Dialog", "DialogInput", "Monospaced"};
   String[] style = {"Plain", "Italic", "Bold", "Bold&Italic"};
   String[] size = {"10", "12", "14", "16", "18", "20","28","36","72"};
   JList list1 = new JList(name);
   JList list2 = new JList(style);
   JList list3 = new JList(size);
   font(final JTextArea a){//�����ڴ� �Ķ���ͷ� �ؽ�Ʈ���� ����
      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      setTitle("Font");
      setLayout(null);//����Ʈ�� ���ϴ� ���� ���̱� ���� ���̾ƿ� ����
      add(list1);
      list1.setLocation(30,30);
      list1.setSize(100,200);
      add(list2);
      list2.setLocation(150,30);
      list2.setSize(100,200);
      add(list3);
      list3.setLocation(280,30);
      list3.setSize(100,200);
      JButton b1 = new JButton("Ȯ��");
      JButton b2 = new JButton("���");
      b1.setBounds(300, 400, 70, 30);
      b2.setBounds(400, 400, 70, 30);
      add(b1);
      add(b2);
      /* Ȯ�� ��ư Ŭ�� */
      b1.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e){
            String str1 = (String)list1.getSelectedValue();//����Ʈ�� ������ ������
            String str2 = (String)list2.getSelectedValue();
            String str3 = (String)list3.getSelectedValue();
            if(str1 == null || str2 == null || str3 == null)System.out.println("��Ʈ ���� ����");//���þ��ϰ� Ȯ�δ����� �������
            else if(str2 == "Plain")   a.setFont(new Font(str1,Font.PLAIN,Integer.parseInt(str3)));//str2���� ����
            else if(str2 == "Bold")   a.setFont(new Font(str1,Font.BOLD,Integer.parseInt(str3)));//��Ʈ�̸��� str1
            else if(str2 == "Italic")   a.setFont(new Font(str1,Font.ITALIC,Integer.parseInt(str3)));//��Ʈ ��Ÿ���� str2
            else if(str2 == "Bold&Italic")   a.setFont(new Font(str1,Font.BOLD + Font.ITALIC,Integer.parseInt(str3)));//��Ʈ ������� str3�� �������� ������
            dispose();
            
         }
      });
      /* ��� ��ư Ŭ�� */
      b2.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e){
            dispose();//�ƹ� �۾��� ���� �ʰ� ����
         }
      });
      setSize(500,500);
      setVisible(true);
   }
}