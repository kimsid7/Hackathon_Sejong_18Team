package blog.naver.com.since201109.pdf;
//package hack;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;


public class Lecture extends JFrame implements ListSelectionListener {
		
	JFrame frame;
	ImageIcon icon;
	JList listSemester;
	JList listSubject;
	String[] strSemester;
	int semsize=0;
	int clasize=0;
	String[] strSubject1;

	JButton add1 = new JButton("학기 추가");
	JButton add2 = new JButton("과목 추가");

	JButton del1 = new JButton("학기 삭제");
	JButton del2 = new JButton("과목 삭제");
	Lecture() {

		add1.addActionListener(new action2());
		add2.addActionListener(new action1());
		add1.setFont(new Font("맑은고딕",Font.BOLD,11));
		add2.setFont(new Font("맑은고딕",Font.BOLD,11));
		del1.setFont(new Font("맑은고딕",Font.BOLD,11));
		del2.setFont(new Font("맑은고딕",Font.BOLD,11));
					frame=this;
					setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					setTitle("강의선택");
					this.setLayout(null);
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
				                setOpaque(false); //그림을 표시하게 설정,투명하게 조절
				                super.paintComponent(g);
				            }
				        };
				        background.setLayout(null);
				        background.setSize(400,400);
				        background.setVisible(true);
				     
					JLabel lblSemester=new JLabel("학기");
					lblSemester.setBounds(20,10,100,50);
					background.add(lblSemester);
					
					String str;

					File fi = new File("semester.txt");
					try{
					BufferedReader reader = new BufferedReader(new FileReader(fi));//파일을 읽어오고
					while((str = reader.readLine())!=null){//파일을 한줄 읽어오고 null값이 아니면 실행
						semsize++;
					}
					reader.close();
					}
					catch(Exception b){
						b.printStackTrace();
					
					}
					/* c값만큼 길이 */
					listSemester=new JList(strSemester);
					listSemester.setBounds(20,55,100,100);
					listSemester.setBorder(new LineBorder(Color.black));
					listSemester.addListSelectionListener(this);
					background.add(listSemester);
					call_semester();
				
					JLabel lblSubject=new JLabel("과목");
					lblSubject.setBounds(140,10,100,50);
					this.add(lblSubject);
				
					/* 과목 리시트 호출 */
					listSubject=new JList();
					listSubject.setBounds(140,55,100,120);
					listSubject.setBorder(new LineBorder(Color.black));
					listSubject.addListSelectionListener(this);
					background.add(listSubject);
			
					JButton btnAccept=new JButton("확인");
					btnAccept.setBounds(200,300,80,30);
					btnAccept.setFont(new Font("맑은고딕",Font.BOLD,11));
					btnAccept.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							if((String)listSemester.getSelectedValue()==null){
								JOptionPane.showMessageDialog(null, "학과를 선택해 주세요.", "ERROR", JOptionPane.WARNING_MESSAGE);
								return;}
							if((String)listSubject.getSelectedValue()==null){
								JOptionPane.showMessageDialog(null, "과목을 선택해 주세요.", "ERROR", JOptionPane.WARNING_MESSAGE);
								return;}
							String selectedSemester=(String)listSemester.getSelectedValue();
							String selectedSubject=(String)listSubject.getSelectedValue();
							new pLectlist((String)listSemester.getSelectedValue()+"_"+(String)listSubject.getSelectedValue()+".txt");
						}
					});
					this.add(btnAccept);
					
					JButton btnExit=new JButton("나가기");
					btnExit.setBounds(290,300,80,30);
					btnExit.setFont(new Font("맑은고딕",Font.BOLD,11));
					btnExit.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							frame.dispose();
						}
					});
					this.add(btnExit);
					add1.setVisible(true);
					add1.setBounds(20, 200, 100, 30);
					background.add(add1);
					del1.setVisible(true);
					del1.setBounds(20, 230, 100, 30);
					background.add(del1);
					del1.addActionListener(new action3());
					del2.setVisible(true);
					del2.setBounds(140, 230, 100, 30);
					del2.addActionListener(new action4());
					background.add(del2);
					add2.setVisible(true);
					add2.setBounds(140, 200, 100, 30);
					background.add(add2);
			        add(background);
					setBounds(100,100,400,400);
					setVisible(true);
					
	}
	class action2 implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			String value=null;
			value = JOptionPane.showInputDialog(null, "학기를 입력해 주세요.");
			if(value!=null){
				try{
					BufferedWriter writer = new BufferedWriter(new FileWriter(new File("semester.txt"),true));
					writer.write(value);//버퍼에 텍스트 저장
					writer.write("\r\n");
					writer.close();
					System.out.println("저장 성공");
					semsize++;
					call_semester();
					}
					catch(Exception b){
						b.printStackTrace();
					}
				
			}
		}
	
	}
	class action1 implements ActionListener{
		public void actionPerformed(ActionEvent e){
				if((String)listSemester.getSelectedValue()==null){
					JOptionPane.showMessageDialog(null, "학과를 선택해 주세요.", "ERROR", JOptionPane.WARNING_MESSAGE);
					return;}
				String value=null;
				value = JOptionPane.showInputDialog(null, "과목을 입력해 주세요.");
				if(value!=null){
					try{
						BufferedWriter writer = new BufferedWriter(new FileWriter(new File((String)listSemester.getSelectedValue()+"class.txt"),true));
						writer.write(value);//버퍼에 텍스트 저장
						writer.write("\r\n");
						writer.close();
						System.out.println("저장 성공");
						clasize++;
						call_class(((String)listSemester.getSelectedValue()));
						}
						catch(Exception b){
							b.printStackTrace();
						}
					
				}
		}
	}
	class action3 implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if((String)listSemester.getSelectedValue()==null){
				JOptionPane.showMessageDialog(null, "학과를 선택해 주세요.", "ERROR", JOptionPane.WARNING_MESSAGE);
				return;}
			String str;
			String temp="";
			File fi = new File("semester.txt");
			File f = new File((String)listSemester.getSelectedValue()+"class.txt");
					 if (f.delete()) {
					      System.out.println("파일 또는 디렉토리를 성공적으로 지웠습니다");
					    } else {
					      System.err.println("파일 또는 디렉토리 지우기 실패");
					    }
			try{
			BufferedReader reader = new BufferedReader(new FileReader(fi));//파일을 읽어오고
			while((str = reader.readLine())!=null){//파일을 한줄 읽어오고 null값이 아니면 실행

				if(str.compareTo((String)listSemester.getSelectedValue())!=0){
					temp+=str+"\r\n";
				}
			}
			reader.close();
			BufferedWriter writer = new BufferedWriter(new FileWriter(new File("semester.txt")));
			writer.write(temp);//버퍼에 텍스트 저장
			writer.close();
			}
			catch(Exception b){
				b.printStackTrace();
			
			}
			call_semester();
		}
	}
	class action4 implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if((String)listSemester.getSelectedValue()==null){
				JOptionPane.showMessageDialog(null, "학과를 선택해 주세요.", "ERROR", JOptionPane.WARNING_MESSAGE);
				return;}
			if((String)listSubject.getSelectedValue()==null){
				JOptionPane.showMessageDialog(null, "과목을 선택해 주세요.", "ERROR", JOptionPane.WARNING_MESSAGE);
				return;}
			String str;
			String temp="";
			String sav =(String)listSubject.getSelectedValue();
			String sav2 =(String)listSemester.getSelectedValue()+"class.txt";
			File fi = new File((String)listSemester.getSelectedValue()+"class.txt");
			File f = new File((String)listSemester.getSelectedValue()+"_"+(String)listSubject.getSelectedValue()+".txt");
					 if (f.delete()) {
					      System.out.println("파일 또는 디렉토리를 성공적으로 지웠습니다");
					    } else {
					      System.err.println("파일 또는 디렉토리 지우기 실패");
					    }
			try{
			BufferedReader reader = new BufferedReader(new FileReader(fi));//파일을 읽어오고
			while((str = reader.readLine())!=null){//파일을 한줄 읽어오고 null값이 아니면 실행

				if(str.compareTo((String)listSubject.getSelectedValue())!=0){
					temp+=str+"\r\n";
				}
			}
			reader.close();
			BufferedWriter writer = new BufferedWriter(new FileWriter(new File(sav2)));
			writer.write(temp);//버퍼에 텍스트 저장
			writer.close();
			}
			catch(Exception b){
				b.printStackTrace();
			
			}
			call_class(sav);
		}
	}
	public void call_semester(){
		File fi = new File("semester.txt");
		strSemester = new String[semsize];	
		String str;
		int i=0;
		try{
			BufferedReader reader = new BufferedReader(new FileReader(fi));//파일을 읽어오고
			while((str = reader.readLine())!=null){//파일을 한줄 읽어오고 null값이 아니면 실행
				strSemester[i]=str;
				i++;
			}
			reader.close();
			}
			catch(Exception b){
				b.printStackTrace();
			}
		listSemester.setListData(strSemester);
	}
	public void call_class(String str){
		String sss;

		File fi = new File(str+"class.txt");
		try{
		BufferedReader reader = new BufferedReader(new FileReader(fi));//파일을 읽어오고
		while((sss = reader.readLine())!=null){//파일을 한줄 읽어오고 null값이 아니면 실행
			clasize++;
		}
		reader.close();
		}
		catch(Exception b){
			b.printStackTrace();
		
		}
		strSubject1= new String[clasize];
		call_class2(fi);
		//크기를 찾아옴
	}
	public void call_class2(File fi){
		String str;
		int i=0;
		try{
			BufferedReader reader = new BufferedReader(new FileReader(fi));//파일을 읽어오고
			while((str = reader.readLine())!=null){//파일을 한줄 읽어오고 null값이 아니면 실행
				strSubject1[i]=str;
				i++;
			}
			reader.close();
			}
			catch(Exception b){
				b.printStackTrace();
			}
		listSubject.setListData(strSubject1);
	}
	public void valueChanged(ListSelectionEvent event) {
		
		// semester
		if(event.getSource()==listSemester) {
			String stringValue=(String)listSemester.getSelectedValue();
			call_class(stringValue);
		}
		
		// professor
	} // valueChanged


}
