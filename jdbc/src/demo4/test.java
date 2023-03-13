package demo4;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.table.JTableHeader;

import java.sql.*;



/*
����Աʵ��
*/

class Admin {
	private String account;                 //�˺�
	private String identity;           //���
	private String password;      //����
	private String confirmPassword; //�ڶ������������
	
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getIdentity() {
		return identity;
	}
	public void setIdentity(String identity) {
		this.identity = identity;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}





/*
�����û���¼
*/


class Login {

	Admin admin;
	
	void setAdmin(Admin admin) {
		this.admin=admin;
		
	}
	/*
	 * JudgeAdmin()����
	 * �ж�Admin��ID����������ѡ���Ƿ���ȷ�������ȷ����ʾ��¼�ɹ�
	 * ������󣬵���һ�����ڣ���ʾ�˺Ż��������ݴ���
	 */
	private String driver = "com.mysql.jdbc.Driver";
    private String url = "jdbc:mysql://localhost:3306/studentdb?characterEncoding=utf-8";
    private String user = "root";
    private String password = "root";
    
    
	
	 public boolean login(Admin admin) throws SQLException, ClassNotFoundException {
	    	String sql="select * from LOG where account=? and password=? and identity = ?";
	        
	    	Class.forName(driver);
	    	Connection conn = DriverManager.getConnection(url, user, password);
	    	PreparedStatement ps = conn.prepareStatement(sql);
	    	
	        ps.setString(1, admin.getAccount());                                                                                                                                                                                                                                                                                                                                      
	        ps.setString(2, admin.getPassword());
	        ps.setString(3, admin.getIdentity());
	        ResultSet rs = ps.executeQuery();
	        int ans = 0;
	        if(rs.next()) {
	        	ans = 1;
	        }  
	        rs.close();
	        ps.close();
	        conn.close();
	        if(ans == 1) {
	        	return true;
	        }
	        else return false;
	    }
	int JudgeAdmin() {
		
		    try {
		        if(login(this.admin)) {
		        	System.out.println("��¼�ɹ�");
		        	return 1;
		        }else {
		            return 0;
		        }
		    }catch(Exception e) {
		        //e.printStackTrace();
		    	
		    }
		return 0;
		
	}	
}




/*
 * ����Աע�����
 * 
 */
class Register extends JFrame{
	Register () {
		init();
	}
	
	void init() {
		 final JFrame frame = new JFrame("ע���˺�");
            frame.setLayout(null);
            // ��½�������
            JLabel identity = new JLabel("���:");
            identity.setBounds(250, 150, 100, 25);
            frame.add(identity);
        
            JLabel account = new JLabel("�˺�:");
            account.setBounds(250, 200, 100, 25);
            frame.add(account);

            JLabel password = new JLabel("����:");
            password.setBounds(250, 250, 100, 25);
            frame.add(password);  
               
            JLabel confrimP = new JLabel("ȷ������:");
            confrimP.setBounds(250, 300, 100, 30);
            frame.add(confrimP);
            
            //�ı���ΰ�ť
            
            String[] listData = new String[] { "ѧ��", "��ʦ", "����Ա" };
    		final JComboBox identity2 = new JComboBox(listData);
    		identity2.setBounds(320, 150, 150, 25);
    		frame.add(identity2);
            
            final JTextField account2 = new JTextField();
            account2.setBounds(320, 200, 150, 25);
            frame.add(account2);

            final JPasswordField password2 = new JPasswordField();
            password2.setBounds(320, 250, 150, 25);
            frame.add(password2);

            final JPasswordField confrimP2 = new JPasswordField();
            confrimP2.setBounds(320, 300, 150, 25);
            frame.add(confrimP2);
            
            JButton logIn = new JButton("ע��");
            logIn.setBounds(350, 350, 70, 25);
            frame.add(logIn);
            


            frame.setBounds(400, 100, 800, 640);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
            
          //Ϊע�ᰴť���Ӽ�����
            logIn.addActionListener(new ActionListener() {
               
                public void actionPerformed(ActionEvent e) {
                    String identity = (String)identity2.getSelectedItem();
                    String account = account2.getText();
                    String password = new String (password2.getPassword());
                    String confirmPassword = new String (confrimP2.getPassword());
                    
                    //����Register��
                    Registerr register = new Registerr();
                    register.setAccount(account);
                    register.setIdentity(identity);
                    register.setPassword(password);
                    register.setConfirmPassword(confirmPassword);
                    
                    //���ע��ɹ������ص�¼����
                    try {
						if(register.JudgeRegister()) {

						    frame.setVisible(false);
						   
						    test t = new test();
						    
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						//e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

                }
                
            });
	}
}









class Registerr {
	
		private String account;        //�˺�
		private String identity;      //���
		private String password;      //����
		private String confirmPassword; //�ڶ������������
		
		public String getConfirmPassword() {
			return confirmPassword;
		}
		public void setConfirmPassword(String confirmPassword) {
			this.confirmPassword = confirmPassword;
		}
		public String getAccount() {
			return account;
		}
		public void setAccount(String account) {
			this.account = account;
		}
		public String getIdentity() {
			return identity;
		}
		public void setIdentity(String identity) {
			this.identity = identity;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}


    
    private String driver = "com.mysql.jdbc.Driver";
    private String url = "jdbc:mysql://localhost:3306/studentdb?characterEncoding=utf-8";
    private String user = "root";
    private String sqlpassword = "root";
    
    
    //�ж�ע����˺��Ƿ���Ϲ���
    boolean JudgeRegister() throws SQLException, ClassNotFoundException {
        
        if(this.getIdentity().equals("")) {
            JOptionPane.showMessageDialog(null, "��ݲ���Ϊ�գ�", "���", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if(this.getAccount().equals("")) {
            JOptionPane.showMessageDialog(null, "�˺Ų���Ϊ�գ�", "�˺�Ϊ��", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if(this.getPassword().equals("")) {
            JOptionPane.showMessageDialog(null, "���벻��Ϊ�գ�", "����Ϊ��", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if(!this.getPassword().equals(this.getConfirmPassword())) {
            JOptionPane.showMessageDialog(null, "������������벻һ��!", "���벻һ��", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        //���Ϲ��򣬵���ע��ɹ��Ĵ��ڣ������˺�������ݿ�
        
        addAdmin();
        JOptionPane.showMessageDialog(null, "ע��ɹ�");
        return true;
    }
    
    //�����ݿ����Admin�˻�
    void addAdmin() throws ClassNotFoundException, SQLException {
    	String sql="insert into LOG (account, identity, password) values (?,?,?)";
    	Class.forName(driver);
    	try {
	    	Connection conn = DriverManager.getConnection(url, user, sqlpassword);
	    	PreparedStatement ps = conn.prepareStatement(sql);
	    	ps.setString(1, this.getAccount());
	        ps.setString(2, this.getIdentity());
	        ps.setString(3, this.getPassword());
	        ps.executeUpdate();
	        
	        ps.close();	
	        conn.close();
	        
    	}catch(SQLException ex) {
    		System.out.println("ע��ʧ�ܣ�");
    	}
    	
    }
}

class masterSystem{
	masterSystem(){
		init();
	}
	public static void init(){
		final JFrame frame = new JFrame("����Ա����");
        frame.setLayout(null);
        //�������
        JLabel txt = new JLabel("����Ҫ����Ĳ���ϵͳ:");
        txt.setBounds(300, 200, 200, 25);
        frame.add(txt);
        
      
        JButton studentS = new JButton("ѧ����Ϣ����ϵͳ");
        studentS.setBounds(300, 250, 200, 25);
        frame.add(studentS);
        
        JButton courseS = new JButton("�γ���Ϣ����ϵͳ");
        courseS.setBounds(300, 300, 200, 25);
        frame.add(courseS);


        frame.setBounds(400, 100, 800, 640);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
        //��Ӽ���
        studentS.addActionListener(new ActionListener() {
       	 public void actionPerformed(ActionEvent e) {
       		 //ѧ����Ϣ����ϵͳ
                frame.setVisible(false);
       		 chuangkouS cs = new chuangkouS(); 
       	 }
        });
        courseS.addActionListener(new ActionListener() {
          	 public void actionPerformed(ActionEvent e) {
          		 //ѧ����Ϣ����ϵͳ
                   frame.setVisible(false);
          		 chuangkouC cc = new chuangkouC(); 
          	 }
           });
	}
}

class teacherSystem{
	teacherSystem(){
		init();
	}
	public static void init(){
		final JFrame frame = new JFrame("��ʦ����");
        frame.setLayout(null);
        //�������
        JLabel txt = new JLabel("����Ҫִ�еĲ���:");
        txt.setBounds(300, 200, 200, 25);
        frame.add(txt);
        
      
        JButton sorceA = new JButton("ѧ���ɼ�¼��");
        sorceA.setBounds(300, 250, 200, 25);
        frame.add(sorceA);
        
        JButton sorceSOD = new JButton("ѧ���ɼ���ѯ����");	//����
        sorceSOD.setBounds(300, 300, 200, 25);
        frame.add(sorceSOD);


        frame.setBounds(400, 100, 800, 640);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
        //��Ӽ���
        sorceA.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				final JFrame frame = new JFrame("¼��ѧ���ɼ�");
	            frame.setLayout(null);
	            //�������
	          
	            JLabel txt1 = new JLabel("������Ҫ¼��γ̵���Ϣ:");
	            txt1.setBounds(250, 100, 200, 25);
	            frame.add(txt1);
	            
	            JLabel sno = new JLabel("ѧ��ѧ��:");
	            sno.setBounds(250, 150, 100, 25);
	            frame.add(sno);
	            
	            JLabel cno = new JLabel("�γ̺�  :");
	            cno.setBounds(250, 200, 100, 25);
	            frame.add(cno);

	            JLabel txt2 = new JLabel("������Ҫ¼��ѧ���Ŀγ̳ɼ�:");
	            txt2.setBounds(250, 250, 200, 25);
	            frame.add(txt2);
	               
	            JLabel sorce = new JLabel("�γ̳ɼ�:");
	            sorce.setBounds(250, 300, 100, 30);
	            frame.add(sorce);
	            
	            //�ı���ΰ�ť
	           

	            final JTextField sno2 = new JTextField();
	            sno2.setBounds(320, 150, 150, 25);
	            frame.add(sno2);
	            
	            final JTextField cno2 = new JTextField();
	            cno2.setBounds(320, 200, 150, 25);
	            frame.add(cno2);
	            
	            
	            final JTextField sorce2 = new JTextField();
	            sorce2.setBounds(320, 300, 150, 25);
	            frame.add(sorce2);
	            
	            
	            JButton button = new JButton("ȷ��¼��");
	            button.setBounds(350, 350, 100, 25);
	            frame.add(button);
	            


	            frame.setBounds(400, 100, 800, 640);
	            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	            frame.setVisible(true);
	            
	            //��Ӽ���
	            button.addActionListener(new ActionListener() {
	                
	                public void actionPerformed(ActionEvent e) {
	                    
	                    String sno = sno2.getText();
	                    String cno = cno2.getText();
	                    
	                    String sorce = sorce2.getText();
	                    
	                    
	                    //������
	                    mysqlAS mysqlAS = new mysqlAS();
	                    mysqlAS.setSno(sno);
	                    mysqlAS.setCno(cno);
	                  
	                    mysqlAS.setSorce(sorce);
	                    
	                    //���¼��ɹ������ؽ���
	                    try {
							if(mysqlAS.JudgeRegister()) {

							    frame.setVisible(false);
							   
							    teacherSystem ts = new teacherSystem();
							    
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							//e1.printStackTrace();
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

	                }
	                
	            });

			}
		});
		
        sorceSOD.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				final JFrame frame = new JFrame("���ҿγ���Ϣ");
	            frame.setLayout(null);
	            // �������
	            JLabel txt1 = new JLabel("������ҳɼ��Ŀγ���Ϣ��");
	            txt1.setBounds(250, 100, 200, 25);
	            frame.add(txt1);
	            
	            
	            
	            JLabel cno = new JLabel("�γ̺�  :");
	            cno.setBounds(250, 200, 100, 25);
	            frame.add(cno);

	           
	            
	            //�ı���ΰ�ť
	           
	          
	            final JTextField cno2 = new JTextField();
	            cno2.setBounds(320, 200, 150, 25);
	            frame.add(cno2);
	         
	            
	            JButton button = new JButton("ȷ�ϲ���");
	            button.setBounds(350, 350, 100, 25);
	            frame.add(button);
	            


	            frame.setBounds(400, 100, 800, 640);
	            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	            frame.setVisible(true);
	            
	            //��Ӽ���
	            button.addActionListener(new ActionListener() {
	                
	                public void actionPerformed(ActionEvent e) {
	                   
	                   
	                    String cno = cno2.getText();
	                   
	                    
	                    
	                    //������
	                    mysqlSSOD mysqlSSOD = new mysqlSSOD();
	                    
	                   
	                    mysqlSSOD.setCno(cno);
	                  
	                    
	                    //����ɹ������ؽ���
	                    try {
							if(mysqlSSOD.JudgeRegister()) {

							    frame.setVisible(false);
							    

							    
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							//e1.printStackTrace();
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

	                }
	                
	            });

			}
		});
	}
}

class studentSystem{
	studentSystem(){
		init();
	}
	public static void init(){
		final JFrame frame = new JFrame("ѧ������");
        frame.setLayout(null);
        //�������
        JLabel txt = new JLabel("����Ҫִ�еĲ���:");
        txt.setBounds(300, 200, 200, 25);
        frame.add(txt);
        
      
        JButton passwordU = new JButton("�޸ĵ�¼����");
        passwordU.setBounds(300, 250, 200, 25);
        frame.add(passwordU);
        
        JButton snoSOD = new JButton("�鿴�Լ��γ̳ɼ�");	//����
        snoSOD.setBounds(300, 300, 200, 25);
        frame.add(snoSOD);


        frame.setBounds(400, 100, 800, 640);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
        //��Ӽ���
        passwordU.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				final JFrame frame = new JFrame("���˵�¼�����޸�");
	            frame.setLayout(null);
	            //�������
	          
	            JLabel txt1 = new JLabel("������Ҫ�޸ĵ��˺�:");
	            txt1.setBounds(250, 100, 200, 25);
	            frame.add(txt1);
	            
	          
	            
	            JLabel account = new JLabel("�˺�  :");
	            account.setBounds(250, 150, 100, 25);
	            frame.add(account);

	            JLabel txt2 = new JLabel("������Ҫ¼��ѧ���Ŀγ̳ɼ�:");
	            txt2.setBounds(250, 200, 200, 25);
	            frame.add(txt2);
	               
	            JLabel password = new JLabel("����:");
	            password.setBounds(250, 250, 100, 25);
	            frame.add(password);  
	               
	            JLabel confrimP = new JLabel("ȷ������:");
	            confrimP.setBounds(250, 300, 100, 30);
	            frame.add(confrimP);
	            
	            //�ı���ΰ�ť
	           

	            final JTextField account2 = new JTextField();
	            account2.setBounds(320, 150, 150, 25);
	            frame.add(account2);

	            final JPasswordField password2 = new JPasswordField();
	            password2.setBounds(320, 250, 150, 25);
	            frame.add(password2);

	            final JPasswordField confrimP2 = new JPasswordField();
	            confrimP2.setBounds(320, 300, 150, 25);
	            frame.add(confrimP2);
	            
	            
	            JButton button = new JButton("ȷ���޸�");
	            button.setBounds(350, 350, 100, 25);
	            frame.add(button);
	            


	            frame.setBounds(400, 100, 800, 640);
	            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	            frame.setVisible(true);
	            
	            //��Ӽ���
	            button.addActionListener(new ActionListener() {
	                
	                public void actionPerformed(ActionEvent e) {
	                    
	                	String account = account2.getText();
	                    String password = new String (password2.getPassword());
	                    String confirmPassword = new String (confrimP2.getPassword());
	                    
	                    
	                    //������
	                    mysqlPU mysqlPU = new mysqlPU();
	                    mysqlPU.setAccount(account);
	                   
	                    mysqlPU.setPassword(password);
	                    mysqlPU.setConfirmPassword(confirmPassword);
	                    
	                    //���¼��ɹ����˻ص���¼����
	                    try {
							if(mysqlPU.JudgeRegister()) {

							    frame.setVisible(false);
							   
							    test t = new test();
							    
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							//e1.printStackTrace();
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

	                }
	                
	            });

			}
		});
		//����2
        snoSOD.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				final JFrame frame = new JFrame("���Ҹ��˳ɼ�");
	            frame.setLayout(null);
	            // �������
	            JLabel txt1 = new JLabel("������ҳɼ��ĸ���ѧ�ţ�");
	            txt1.setBounds(250, 100, 200, 25);
	            frame.add(txt1);
	            
	            
	            
	            JLabel sno = new JLabel("����ѧ��  :");
	            sno.setBounds(250, 200, 100, 25);
	            frame.add(sno);

	           
	            
	            //�ı���ΰ�ť
	           
	          
	            final JTextField sno2 = new JTextField();
	            sno2.setBounds(320, 200, 150, 25);
	            frame.add(sno2);
	         
	            
	            JButton button = new JButton("ȷ�ϲ���");
	            button.setBounds(350, 350, 100, 25);
	            frame.add(button);
	            


	            frame.setBounds(400, 100, 800, 640);
	            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	            frame.setVisible(true);
	            
	            //��Ӽ���
	            button.addActionListener(new ActionListener() {
	                
	                public void actionPerformed(ActionEvent e) {
	                   
	                   
	                    String sno = sno2.getText();
	                   
	                    
	                    
	                    //������
	                    mysqlSSOD2 mysqlSSOD2 = new mysqlSSOD2();
	                    
	                   
	                    mysqlSSOD2.setSno(sno);
	                  
	                    
	                    //����ɹ������ؽ���
	                    try {
							if(mysqlSSOD2.JudgeRegister()) {

							    frame.setVisible(false);
							    

							    
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							//e1.printStackTrace();
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

	                }
	                
	            });

			}
		});
	}
}




class mysqlSA {
	
	private String sno;        //ѧ��
	private String sname;      //����
	private String sex;      //�Ա�
	private String sage; //����
	
	



public String getSno() {
		return sno;
	}

	public void setSno(String sno) {
		this.sno = sno;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getSage() {
		return sage;
	}

	public void setSage(String sage) {
		this.sage = sage;
	}

private String driver = "com.mysql.jdbc.Driver";
private String url = "jdbc:mysql://localhost:3306/studentdb?characterEncoding=utf-8";
private String user = "root";
private String sqlpassword = "root";


//�ж��Ƿ���Ϲ���
boolean JudgeRegister() throws SQLException, ClassNotFoundException {
    
    if(this.getSex().equals("")) {
        JOptionPane.showMessageDialog(null, "�Ա���Ϊ�գ�", "�Ա�Ϊ��", JOptionPane.ERROR_MESSAGE);
        return false;
    }
    
    if(this.getSno().equals("")) {
        JOptionPane.showMessageDialog(null, "ѧ�Ų���Ϊ�գ�", "ѧ��Ϊ��", JOptionPane.ERROR_MESSAGE);
        return false;
    }
    
    if(this.getSname().equals("")) {
        JOptionPane.showMessageDialog(null, "��������Ϊ�գ�", "����Ϊ��", JOptionPane.ERROR_MESSAGE);
        return false;
    }
    
    if(this.getSage().equals("")) {
        JOptionPane.showMessageDialog(null, "���䲻��Ϊ�գ�", "����Ϊ��", JOptionPane.ERROR_MESSAGE);
        return false;
    }
    
    //���Ϲ��򣬵�����ӳɹ��Ĵ��ڣ�����������������ݿ�
    
    addAdmin();
    JOptionPane.showMessageDialog(null, "��ӳɹ�");
    return true;
}

//�����ݿ��������
void addAdmin() throws ClassNotFoundException, SQLException {
	String sql="insert into StudentInfor (sno, sname, sex , sage) values (?,?,?,?)";
	Class.forName(driver);
	try {
    	Connection conn = DriverManager.getConnection(url, user, sqlpassword);
    	PreparedStatement ps = conn.prepareStatement(sql);
    	ps.setString(1, this.getSno());
        ps.setString(2, this.getSname());
        ps.setString(3, this.getSex());
        ps.setString(4, this.getSage());
        ps.executeUpdate();
        
        ps.close();	
        conn.close();
        
	}catch(SQLException ex) {
		System.out.println("���ʧ�ܣ�");
	}
	
}
}

class mysqlSS extends JFrame{
	
	private String sno;        //ѧ��
	
	public String getSno() {
		return sno;
	}

	public void setSno(String sno) {
		this.sno = sno;
	}


	{ 
		try 
		{
	        for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) 
	        {
	            if ("Nimbus".equals(info.getName())) 
	            {
	                javax.swing.UIManager.setLookAndFeel(info.getClassName());
	                break;
	            }
	        }
	    }
		catch(Exception e) 
		{
    	System.out.println(e);
		}
	}
	
	private JScrollPane scpDemo;
	private JTableHeader jth;
	private JTable tabDemo;
	private JButton btnShow;
	private JButton bt1;


	
private String driver = "com.mysql.jdbc.Driver";
private String url = "jdbc:mysql://localhost:3306/studentdb?characterEncoding=utf-8";
private String user = "root";
private String sqlpassword = "root";

public mysqlSS(){
	super("�γ���Ϣ����ϵͳ");		//JFrame�ı�������
	this.setSize(660,600);		//���ƴ����С
	this.setLayout(null);		//�Զ��岼��
	this.setLocation(400,100);	//��������Ժ󣬴�������Ļ��λ��
	this.scpDemo = new JScrollPane();
	this.bt1=new JButton("�˳�");
	
	this.btnShow = new JButton("��ʾ����");
	
	this.bt1.setBounds(100, 480, 100, 30);
	
	this.scpDemo.setBounds(10,50,580,400);	//���ù������С
	this.btnShow.setBounds(10,10,120,30);	//���ð�ť
	this.btnShow.addActionListener(new ActionListener()	//������ʾ���ݡ���ť����¼���Ӧ��
	{
		public void actionPerformed(ActionEvent ae)
		{

			addAdmin(ae);
		}
	});
	
	
	this.bt1.addActionListener(new ActionListener()	
	{
		public void actionPerformed(ActionEvent ae)
		{
			chuangkouS cs=  new chuangkouS();
		}
	});
	add(this.scpDemo);
	add(this.btnShow);
	add(this.bt1);
	
	this.setVisible(true);
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	
	
	}
	


//�ж��Ƿ���Ϲ���
boolean JudgeRegister() throws SQLException, ClassNotFoundException {
    
    
    
    if(this.getSno().equals("")) {
        JOptionPane.showMessageDialog(null, "ѧ�Ų���Ϊ�գ�", "ѧ��Ϊ��", JOptionPane.ERROR_MESSAGE);
        return false;
    }
    
    
    
    
    
    //���Ϲ��򣬵�����ӳɹ��Ĵ��ڣ�����������������ݿ�
    
 
    //JOptionPane.showMessageDialog(null, "���ҳɹ�");
    
    return true;
}

//������
public void addAdmin(ActionEvent ae)
{
	try
	{
		Class.forName("com.mysql.jdbc.Driver"); 
		String url = "jdbc:mysql://localhost:3306/studentdb?characterEncoding=utf-8";
		String username = "root";
		String passwords = "root";
		Connection conn=DriverManager.getConnection(url, username, passwords);
		String sql = "select * from StudentInfor where sno = ? "; 
		
		PreparedStatement pstm = conn.prepareStatement(sql);
		
		//System.out.println(this.getSno());
		
		pstm.setString(1, this.getSno());
		
		ResultSet rs = pstm.executeQuery();
		int count = 0;
		while(rs.next())
		{
			count++;
		}
		
		rs = pstm.executeQuery();
	// ����ѯ��õļ�¼���ݣ�ת�����ʺ�����JTable��������ʽ
	Object[][] info = new Object[count][4];
	String []title= {"sno","sname","sex","sage"};
	count = 0;
	//System.out.println(rs.next());
	while(rs.next())
	{	
		
		String sno = rs.getString("sno");
		String sname = rs.getString("sname");
		String sex = rs.getString("sex");
		String sage = rs.getString("sage");
		info[count][0] = sno;
		info[count][1] = sage;
		info[count][2] = sex;
		info[count][3] = sage;
		count++;
	}
	
	
	
	
	
	
	// ����JTable
	this.tabDemo = new JTable(info,title);
	// ��ʾ��ͷ
	this.jth = this.tabDemo.getTableHeader();
	// ��JTable���뵽���������������
	this.scpDemo.getViewport().add(tabDemo);
	
	
	
	

	
	
	}
	catch(ClassNotFoundException cnfe)
	{
		cnfe.printStackTrace();
		JOptionPane.showMessageDialog(null,"����Դ����","����",JOptionPane.ERROR_MESSAGE);
	}
	catch(SQLException sqle)
	{
		sqle.printStackTrace();
		JOptionPane.showMessageDialog(null,"���ݲ�������","����",JOptionPane.ERROR_MESSAGE);
	} 
	}

}


class mysqlCS extends JFrame{
	
	private String sno;        //ѧ��
	private String cno;        //ѧ��
	
	
	public String getCno() {
		return cno;
	}

	public void setCno(String cno) {
		this.cno = cno;
	}

	public String getSno() {
		return sno;
	}

	public void setSno(String sno) {
		this.sno = sno;
	}


	{ 
		try 
		{
	        for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) 
	        {
	            if ("Nimbus".equals(info.getName())) 
	            {
	                javax.swing.UIManager.setLookAndFeel(info.getClassName());
	                break;
	            }
	        }
	    }
		catch(Exception e) 
		{
    	System.out.println(e);
		}
	}
	
	private JScrollPane scpDemo;
	private JTableHeader jth;
	private JTable tabDemo;
	private JButton btnShow;
	private JButton bt1;


	
private String driver = "com.mysql.jdbc.Driver";
private String url = "jdbc:mysql://localhost:3306/studentdb?characterEncoding=utf-8";
private String user = "root";
private String sqlpassword = "root";

public mysqlCS(){
	super("�γ���Ϣ��ѯ����ϵͳ");		//JFrame�ı�������
	this.setSize(660,600);		//���ƴ����С
	this.setLayout(null);		//�Զ��岼��
	this.setLocation(400,100);	//��������Ժ󣬴�������Ļ��λ��
	this.scpDemo = new JScrollPane();
	this.bt1=new JButton("�˳�");
	
	this.btnShow = new JButton("��ʾ����");
	
	this.bt1.setBounds(100, 480, 100, 30);
	
	this.scpDemo.setBounds(10,50,580,400);	//���ù������С
	this.btnShow.setBounds(10,10,120,30);	//���ð�ť
	this.btnShow.addActionListener(new ActionListener()	//������ʾ���ݡ���ť����¼���Ӧ��
	{
		public void actionPerformed(ActionEvent ae)
		{

			addAdmin(ae);
		}
	});
	
	
	this.bt1.addActionListener(new ActionListener()	
	{
		public void actionPerformed(ActionEvent ae)
		{
			chuangkouC cs=  new chuangkouC();
		}
	});
	add(this.scpDemo);
	add(this.btnShow);
	add(this.bt1);
	
	this.setVisible(true);
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	
	
	}
	


//�ж��Ƿ���Ϲ���
boolean JudgeRegister() throws SQLException, ClassNotFoundException {
    
    
    
    if(this.getSno().equals("")) {
        JOptionPane.showMessageDialog(null, "ѧ�Ų���Ϊ�գ�", "ѧ��Ϊ��", JOptionPane.ERROR_MESSAGE);
        return false;
    }
    
    if(this.getCno().equals("")) {
        JOptionPane.showMessageDialog(null, "�γ̺Ų���Ϊ�գ�", "�γ̺�Ϊ��", JOptionPane.ERROR_MESSAGE);
        return false;
    }
    
    
    
    //���Ϲ��򣬵�����ӳɹ��Ĵ��ڣ�����������������ݿ�
    
 
    //JOptionPane.showMessageDialog(null, "���ҳɹ�");
    
    return true;
}

//������
public void addAdmin(ActionEvent ae)
{
	try
	{
		Class.forName("com.mysql.jdbc.Driver"); 
		String url = "jdbc:mysql://localhost:3306/studentdb?characterEncoding=utf-8";
		String username = "root";
		String passwords = "root";
		Connection conn=DriverManager.getConnection(url, username, passwords);
		String sql = "select * from Course where sno = ? and cno = ?"; 
		
		PreparedStatement pstm = conn.prepareStatement(sql);
		
		//System.out.println(this.getSno());
		
		pstm.setString(1, this.getSno());
		pstm.setString(2, this.getCno());
		
		ResultSet rs = pstm.executeQuery();
		int count = 0;
		while(rs.next())
		{
			count++;
		}
		
		rs = pstm.executeQuery();
	// ����ѯ��õļ�¼���ݣ�ת�����ʺ�����JTable��������ʽ
	Object[][] info = new Object[count][4];
	String []title= {"sno","cno","cname","sorce"};
	count = 0;
	//System.out.println(rs.next());
	while(rs.next())
	{	
		
		String sno = rs.getString("sno");
		String cno = rs.getString("cno");
		String cname = rs.getString("cname");
		String sorce = rs.getString("sorce");
		info[count][0] = sno;
		info[count][1] = cno;
		info[count][2] = cname;
		info[count][3] = sorce;
		count++;
	}
	
	
	
	
	
	
	// ����JTable
	this.tabDemo = new JTable(info,title);
	// ��ʾ��ͷ
	this.jth = this.tabDemo.getTableHeader();
	// ��JTable���뵽���������������
	this.scpDemo.getViewport().add(tabDemo);
	
	
	
	

	
	
	}
	catch(ClassNotFoundException cnfe)
	{
		cnfe.printStackTrace();
		JOptionPane.showMessageDialog(null,"����Դ����","����",JOptionPane.ERROR_MESSAGE);
	}
	catch(SQLException sqle)
	{
		sqle.printStackTrace();
		JOptionPane.showMessageDialog(null,"���ݲ�������","����",JOptionPane.ERROR_MESSAGE);
	} 
	}


}


class mysqlSSOD extends JFrame{
	
	
	private String cno;        //�γ̺�
	
	
	public String getCno() {
		return cno;
	}

	public void setCno(String cno) {
		this.cno = cno;
	}



	{ 
		try 
		{
	        for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) 
	        {
	            if ("Nimbus".equals(info.getName())) 
	            {
	                javax.swing.UIManager.setLookAndFeel(info.getClassName());
	                break;
	            }
	        }
	    }
		catch(Exception e) 
		{
    	System.out.println(e);
		}
	}
	
	private JScrollPane scpDemo;
	private JTableHeader jth;
	private JTable tabDemo;
	private JButton btnShow;
	private JButton bt1;


	
private String driver = "com.mysql.jdbc.Driver";
private String url = "jdbc:mysql://localhost:3306/studentdb?characterEncoding=utf-8";
private String user = "root";
private String sqlpassword = "root";

public mysqlSSOD(){
	super("�γ̳ɼ���ѯ����ϵͳ");		//JFrame�ı�������
	this.setSize(660,600);		//���ƴ����С
	this.setLayout(null);		//�Զ��岼��
	this.setLocation(400,100);	//��������Ժ󣬴�������Ļ��λ��
	this.scpDemo = new JScrollPane();
	this.bt1=new JButton("�˳�");
	
	this.btnShow = new JButton("��ʾ����");
	
	this.bt1.setBounds(100, 480, 100, 30);
	
	this.scpDemo.setBounds(10,50,580,400);	//���ù������С
	this.btnShow.setBounds(10,10,120,30);	//���ð�ť
	this.btnShow.addActionListener(new ActionListener()	//������ʾ���ݡ���ť����¼���Ӧ��
	{
		public void actionPerformed(ActionEvent ae)
		{

			addAdmin(ae);
		}
	});
	
	
	this.bt1.addActionListener(new ActionListener()	
	{
		public void actionPerformed(ActionEvent ae)
		{
			teacherSystem ts = new teacherSystem();
		}
	});
	add(this.scpDemo);
	add(this.btnShow);
	add(this.bt1);
	
	this.setVisible(true);
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	
	
	}
	


//�ж��Ƿ���Ϲ���
boolean JudgeRegister() throws SQLException, ClassNotFoundException {
    
    
    
    
    
    if(this.getCno().equals("")) {
        JOptionPane.showMessageDialog(null, "�γ̺Ų���Ϊ�գ�", "�γ̺�Ϊ��", JOptionPane.ERROR_MESSAGE);
        return false;
    }
    
    
    
    //���Ϲ��򣬵�����ӳɹ��Ĵ��ڣ�����������������ݿ�
    
 
    //JOptionPane.showMessageDialog(null, "���ҳɹ�");
    
    return true;
}

//������
public void addAdmin(ActionEvent ae)
{
	try
	{
		Class.forName("com.mysql.jdbc.Driver"); 
		String url = "jdbc:mysql://localhost:3306/studentdb?characterEncoding=utf-8";
		String username = "root";
		String passwords = "root";
		Connection conn=DriverManager.getConnection(url, username, passwords);
		String sql = "select * from Course where cno = ? order by sorce desc;"; 
		
		PreparedStatement pstm = conn.prepareStatement(sql);
		
		
		pstm.setString(1, this.getCno());
		
		ResultSet rs = pstm.executeQuery();
		int count = 0;
		while(rs.next())
		{
			count++;
		}
		
		rs = pstm.executeQuery();
	// ����ѯ��õļ�¼���ݣ�ת�����ʺ�����JTable��������ʽ
	Object[][] info = new Object[count][4];
	String []title= {"sno","cno","cname","sorce"};
	count = 0;
	//System.out.println(rs.next());
	while(rs.next())
	{	
		
		String sno = rs.getString("sno");
		String cno = rs.getString("cno");
		String cname = rs.getString("cname");
		String sorce = rs.getString("sorce");
		info[count][0] = sno;
		info[count][1] = cno;
		info[count][2] = cname;
		info[count][3] = sorce;
		count++;
	}
	
	
	
	
	
	
	// ����JTable
	this.tabDemo = new JTable(info,title);
	// ��ʾ��ͷ
	this.jth = this.tabDemo.getTableHeader();
	// ��JTable���뵽���������������
	this.scpDemo.getViewport().add(tabDemo);
	
	
	
	

	
	
	}
	catch(ClassNotFoundException cnfe)
	{
		cnfe.printStackTrace();
		JOptionPane.showMessageDialog(null,"����Դ����","����",JOptionPane.ERROR_MESSAGE);
	}
	catch(SQLException sqle)
	{
		sqle.printStackTrace();
		JOptionPane.showMessageDialog(null,"���ݲ�������","����",JOptionPane.ERROR_MESSAGE);
	} 
	}


}

class mysqlSSOD2 extends JFrame{
	
	
	private String sno;        //�γ̺�
	
	
	public String getSno() {
		return sno;
	}

	public void setSno(String sno) {
		this.sno = sno;
	}

	



	{ 
		try 
		{
	        for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) 
	        {
	            if ("Nimbus".equals(info.getName())) 
	            {
	                javax.swing.UIManager.setLookAndFeel(info.getClassName());
	                break;
	            }
	        }
	    }
		catch(Exception e) 
		{
    	System.out.println(e);
		}
	}
	
	private JScrollPane scpDemo;
	private JTableHeader jth;
	private JTable tabDemo;
	private JButton btnShow;
	private JButton bt1;


	
private String driver = "com.mysql.jdbc.Driver";
private String url = "jdbc:mysql://localhost:3306/studentdb?characterEncoding=utf-8";
private String user = "root";
private String sqlpassword = "root";

public mysqlSSOD2(){
	super("�γ̳ɼ���ѯ����ϵͳ");		//JFrame�ı�������
	this.setSize(660,600);		//���ƴ����С
	this.setLayout(null);		//�Զ��岼��
	this.setLocation(400,100);	//��������Ժ󣬴�������Ļ��λ��
	this.scpDemo = new JScrollPane();
	this.bt1=new JButton("�˳�");
	
	this.btnShow = new JButton("��ʾ����");
	
	this.bt1.setBounds(100, 480, 100, 30);
	
	this.scpDemo.setBounds(10,50,580,400);	//���ù������С
	this.btnShow.setBounds(10,10,120,30);	//���ð�ť
	this.btnShow.addActionListener(new ActionListener()	//������ʾ���ݡ���ť����¼���Ӧ��
	{
		public void actionPerformed(ActionEvent ae)
		{

			addAdmin(ae);
		}
	});
	
	
	this.bt1.addActionListener(new ActionListener()	
	{
		public void actionPerformed(ActionEvent ae)
		{
			teacherSystem ts = new teacherSystem();
		}
	});
	add(this.scpDemo);
	add(this.btnShow);
	add(this.bt1);
	
	this.setVisible(true);
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	
	
	}
	


//�ж��Ƿ���Ϲ���
boolean JudgeRegister() throws SQLException, ClassNotFoundException {
    
    
    
    
    
    if(this.getSno().equals("")) {
        JOptionPane.showMessageDialog(null, "����ѧ�Ų���Ϊ�գ�", "ѧ��Ϊ��", JOptionPane.ERROR_MESSAGE);
        return false;
    }
    
    
    
    //���Ϲ��򣬵�����ӳɹ��Ĵ��ڣ�����������������ݿ�
    
 
    //JOptionPane.showMessageDialog(null, "���ҳɹ�");
    
    return true;
}

//������
public void addAdmin(ActionEvent ae)
{
	try
	{
		Class.forName("com.mysql.jdbc.Driver"); 
		String url = "jdbc:mysql://localhost:3306/studentdb?characterEncoding=utf-8";
		String username = "root";
		String passwords = "root";
		Connection conn=DriverManager.getConnection(url, username, passwords);
		String sql = "select * from Course where sno = ? order by sorce desc;"; 
		
		PreparedStatement pstm = conn.prepareStatement(sql);
		
		
		pstm.setString(1, this.getSno());
		
		ResultSet rs = pstm.executeQuery();
		int count = 0;
		while(rs.next())
		{
			count++;
		}
		
		rs = pstm.executeQuery();
	// ����ѯ��õļ�¼���ݣ�ת�����ʺ�����JTable��������ʽ
	Object[][] info = new Object[count][4];
	String []title= {"sno","cno","cname","sorce"};
	count = 0;
	//System.out.println(rs.next());
	while(rs.next())
	{	
		
		String sno = rs.getString("sno");
		String cno = rs.getString("cno");
		String cname = rs.getString("cname");
		String sorce = rs.getString("sorce");
		info[count][0] = sno;
		info[count][1] = cno;
		info[count][2] = cname;
		info[count][3] = sorce;
		count++;
	}
	
	
	
	
	
	
	// ����JTable
	this.tabDemo = new JTable(info,title);
	// ��ʾ��ͷ
	this.jth = this.tabDemo.getTableHeader();
	// ��JTable���뵽���������������
	this.scpDemo.getViewport().add(tabDemo);
	
	
	
	

	
	
	}
	catch(ClassNotFoundException cnfe)
	{
		cnfe.printStackTrace();
		JOptionPane.showMessageDialog(null,"����Դ����","����",JOptionPane.ERROR_MESSAGE);
	}
	catch(SQLException sqle)
	{
		sqle.printStackTrace();
		JOptionPane.showMessageDialog(null,"���ݲ�������","����",JOptionPane.ERROR_MESSAGE);
	} 
	}


}


class mysqlSU {
	
	private String sno;   //ѧ��
	
	private String sage; //����
	
	



public String getSno() {
		return sno;
	}

	public void setSno(String sno) {
		this.sno = sno;
	}

	

	public String getSage() {
		return sage;
	}

	public void setSage(String sage) {
		this.sage = sage;
	}

private String driver = "com.mysql.jdbc.Driver";
private String url = "jdbc:mysql://localhost:3306/studentdb?characterEncoding=utf-8";
private String user = "root";
private String sqlpassword = "root";


//�ж��Ƿ���Ϲ���
boolean JudgeRegister() throws SQLException, ClassNotFoundException {
    
    
    
    if(this.getSno().equals("")) {
        JOptionPane.showMessageDialog(null, "ѧ�Ų���Ϊ�գ�", "ѧ��Ϊ��", JOptionPane.ERROR_MESSAGE);
        return false;
    }
    
    
    
    if(this.getSage().equals("")) {
        JOptionPane.showMessageDialog(null, "���䲻��Ϊ�գ�", "����Ϊ��", JOptionPane.ERROR_MESSAGE);
        return false;
    }
    
    //���Ϲ��򣬵�����ӳɹ��Ĵ��ڣ�����������������ݿ�
    
    addAdmin();
    JOptionPane.showMessageDialog(null, "���³ɹ�");
    return true;
}

//�����ݿ��������
void addAdmin() throws ClassNotFoundException, SQLException {
	String sql="update StudentInfor set sage = ?  where sno = ?";
	Class.forName(driver);
	try {
    	Connection conn = DriverManager.getConnection(url, user, sqlpassword);
    	PreparedStatement ps = conn.prepareStatement(sql);
    	ps.setString(2, this.getSno());
       
        ps.setString(1, this.getSage());
        ps.executeUpdate();
        
        ps.close();	
        conn.close();
        
	}catch(SQLException ex) {
		System.out.println("����ʧ�ܣ�");
	}
	
}

}


class mysqlCA{
	private String sno;        //ѧ��
	private String cno;      //�γ̺�
	private String cname;      //�γ���
	private String sorce; //�ɼ�
	
	



public String getSno() {
		return sno;
	}

	public void setSno(String sno) {
		this.sno = sno;
	}

	public String getCno() {
		return cno;
	}

	public void setCno(String cno) {
		this.cno = cno;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getSorce() {
		return sorce;
	}

	public void setSorce(String sorce) {
		this.sorce = sorce;
	}

	
	
private String driver = "com.mysql.jdbc.Driver";
private String url = "jdbc:mysql://localhost:3306/studentdb?characterEncoding=utf-8";
private String user = "root";
private String sqlpassword = "root";


//�ж��Ƿ���Ϲ���
boolean JudgeRegister() throws SQLException, ClassNotFoundException {
    
    if(this.getSno().equals("")) {
        JOptionPane.showMessageDialog(null, "ѧ�Ų���Ϊ�գ�", "ѧ��Ϊ��", JOptionPane.ERROR_MESSAGE);
        return false;
    }
    
    if(this.getCno().equals("")) {
        JOptionPane.showMessageDialog(null, "�γ̺Ų���Ϊ�գ�", "�γ̺�Ϊ��", JOptionPane.ERROR_MESSAGE);
        return false;
    }
    
    if(this.getCname().equals("")) {
        JOptionPane.showMessageDialog(null, "�γ�������Ϊ�գ�", "�γ���Ϊ��", JOptionPane.ERROR_MESSAGE);
        return false;
    }
    
    if(this.getSorce().equals("")) {
        JOptionPane.showMessageDialog(null, "�ɼ�����Ϊ�գ�", "�ɼ�Ϊ��", JOptionPane.ERROR_MESSAGE);
        return false;
    }
    
    //���Ϲ��򣬵�����ӳɹ��Ĵ��ڣ�����������������ݿ�
    
    addAdmin();
    JOptionPane.showMessageDialog(null, "��ӳɹ�");
    return true;
}

//�����ݿ��������
void addAdmin() throws ClassNotFoundException, SQLException {
	String sql="insert into Course (sno, cno, cname , sorce) values (?,?,?,?)";
	Class.forName(driver);
	try {
    	Connection conn = DriverManager.getConnection(url, user, sqlpassword);
    	PreparedStatement ps = conn.prepareStatement(sql);
    	ps.setString(1, this.getSno());
        ps.setString(2, this.getCno());
        ps.setString(3, this.getCname());
        ps.setString(4, this.getSorce());
        ps.executeUpdate();
        
        ps.close();	
        conn.close();
        
	}catch(SQLException ex) {
		System.out.println("���ʧ�ܣ�");
	}
	
}
}



class mysqlCU{
	private String sno;        //ѧ��
	private String cno;      //�γ̺�
	     
	private String sorce; //�ɼ�
	
	



public String getSno() {
		return sno;
	}

	public void setSno(String sno) {
		this.sno = sno;
	}

	public String getCno() {
		return cno;
	}

	public void setCno(String cno) {
		this.cno = cno;
	}

	

	public String getSorce() {
		return sorce;
	}

	public void setSorce(String sorce) {
		this.sorce = sorce;
	}

	
	
private String driver = "com.mysql.jdbc.Driver";
private String url = "jdbc:mysql://localhost:3306/studentdb?characterEncoding=utf-8";
private String user = "root";
private String sqlpassword = "root";


//�ж��Ƿ���Ϲ���
boolean JudgeRegister() throws SQLException, ClassNotFoundException {
    
    if(this.getSno().equals("")) {
        JOptionPane.showMessageDialog(null, "ѧ�Ų���Ϊ�գ�", "ѧ��Ϊ��", JOptionPane.ERROR_MESSAGE);
        return false;
    }
    
    if(this.getCno().equals("")) {
        JOptionPane.showMessageDialog(null, "�γ̺Ų���Ϊ�գ�", "�γ̺�Ϊ��", JOptionPane.ERROR_MESSAGE);
        return false;
    }
    
    
    
    if(this.getSorce().equals("")) {
        JOptionPane.showMessageDialog(null, "�ɼ�����Ϊ�գ�", "�ɼ�Ϊ��", JOptionPane.ERROR_MESSAGE);
        return false;
    }
    
    //���Ϲ��򣬵�����ӳɹ��Ĵ��ڣ�����������������ݿ�
    
    addAdmin();
    JOptionPane.showMessageDialog(null, "���³ɹ�");
    return true;
}

//�����ݿ��������
void addAdmin() throws ClassNotFoundException, SQLException {
	String sql="update Course set sorce = ?  where sno = ? and cno = ? ";
	Class.forName(driver);
	try {
    	Connection conn = DriverManager.getConnection(url, user, sqlpassword);
    	PreparedStatement ps = conn.prepareStatement(sql);
    	ps.setString(2, this.getSno());
        ps.setString(3, this.getCno());
       
        ps.setString(1, this.getSorce());
        ps.executeUpdate();
        
        ps.close();	
        conn.close();
        
	}catch(SQLException ex) {
		System.out.println("����ʧ�ܣ�");
	}
	
}
}



class mysqlAS{
	private String sno;        //ѧ��
	private String cno;      //�γ̺�
	     
	private String sorce; //�ɼ�
	
	



	public String getSno() {
		return sno;
	}

	public void setSno(String sno) {
		this.sno = sno;
	}

	public String getCno() {
		return cno;
	}

	public void setCno(String cno) {
		this.cno = cno;
	}

	

	public String getSorce() {
		return sorce;
	}

	public void setSorce(String sorce) {
		this.sorce = sorce;
	}

	
	
private String driver = "com.mysql.jdbc.Driver";
private String url = "jdbc:mysql://localhost:3306/studentdb?characterEncoding=utf-8";
private String user = "root";
private String sqlpassword = "root";


//�ж��Ƿ���Ϲ���
boolean JudgeRegister() throws SQLException, ClassNotFoundException {
    
    if(this.getSno().equals("")) {
        JOptionPane.showMessageDialog(null, "ѧ�Ų���Ϊ�գ�", "ѧ��Ϊ��", JOptionPane.ERROR_MESSAGE);
        return false;
    }
    
    if(this.getCno().equals("")) {
        JOptionPane.showMessageDialog(null, "�γ̺Ų���Ϊ�գ�", "�γ̺�Ϊ��", JOptionPane.ERROR_MESSAGE);
        return false;
    }
    
    
    
    if(this.getSorce().equals("")) {
        JOptionPane.showMessageDialog(null, "�ɼ�����Ϊ�գ�", "�ɼ�Ϊ��", JOptionPane.ERROR_MESSAGE);
        return false;
    }
    
    //���Ϲ��򣬵�����ӳɹ��Ĵ��ڣ�����������������ݿ�
    
    addAdmin();
    JOptionPane.showMessageDialog(null, "¼��ɹ�");
    return true;
}

//�����ݿ��������
void addAdmin() throws ClassNotFoundException, SQLException {
	String sql="update Course set sorce = ?  where sno = ? and cno = ? ";
	Class.forName(driver);
	try {
    	Connection conn = DriverManager.getConnection(url, user, sqlpassword);
    	PreparedStatement ps = conn.prepareStatement(sql);
    	ps.setString(2, this.getSno());
        ps.setString(3, this.getCno());
       
        ps.setString(1, this.getSorce());
        ps.executeUpdate();
        
        ps.close();	
        conn.close();
        
	}catch(SQLException ex) {
		System.out.println("¼��ʧ�ܣ�");
	}
	
}
}

class mysqlPU{
	private String account;        //�˺�
	
	private String password;      //����
	private String confirmPassword; //�ڶ������������
	
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	
	
private String driver = "com.mysql.jdbc.Driver";
private String url = "jdbc:mysql://localhost:3306/studentdb?characterEncoding=utf-8";
private String user = "root";
private String sqlpassword = "root";


//�ж��Ƿ���Ϲ���
boolean JudgeRegister() throws SQLException, ClassNotFoundException {

   
        
       
        if(this.getAccount().equals("")) {
            JOptionPane.showMessageDialog(null, "�˺Ų���Ϊ�գ�", "�˺�Ϊ��", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if(this.getPassword().equals("")) {
            JOptionPane.showMessageDialog(null, "���벻��Ϊ�գ�", "����Ϊ��", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if(!this.getPassword().equals(this.getConfirmPassword())) {
            JOptionPane.showMessageDialog(null, "������������벻һ��!", "���벻һ��", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    //���Ϲ��򣬵�����ӳɹ��Ĵ��ڣ�����������������ݿ�
    
    addAdmin();
    JOptionPane.showMessageDialog(null, "�޸ĳɹ�");
    return true;
}

//�����ݿ��������
void addAdmin() throws ClassNotFoundException, SQLException {
	String sql="update LOG set password = ?  where account = ?  ";
	Class.forName(driver);
	try {
    	Connection conn = DriverManager.getConnection(url, user, sqlpassword);
    	PreparedStatement ps = conn.prepareStatement(sql);
    	ps.setString(1, this.getPassword());
        ps.setString(2, this.getAccount());
       
        ps.executeUpdate();
        
        ps.close();	
        conn.close();
        
	}catch(SQLException ex) {
		System.out.println("�޸�ʧ�ܣ�");
	}
	
}
}

class mysqlCD{
	private String sno;        //ѧ��
	private String cno;      //�γ̺�
	
	
	



	public String getSno() {
		return sno;
	}

	public void setSno(String sno) {
		this.sno = sno;
	}

	public String getCno() {
		return cno;
	}

	public void setCno(String cno) {
		this.cno = cno;
	}

	
	
private String driver = "com.mysql.jdbc.Driver";
private String url = "jdbc:mysql://localhost:3306/studentdb?characterEncoding=utf-8";
private String user = "root";
private String sqlpassword = "root";


//�ж��Ƿ���Ϲ���
boolean JudgeRegister() throws SQLException, ClassNotFoundException {
    
    if(this.getSno().equals("")) {
        JOptionPane.showMessageDialog(null, "ѧ�Ų���Ϊ�գ�", "ѧ��Ϊ��", JOptionPane.ERROR_MESSAGE);
        return false;
    }
    
    if(this.getCno().equals("")) {
        JOptionPane.showMessageDialog(null, "�γ̺Ų���Ϊ�գ�", "�γ̺�Ϊ��", JOptionPane.ERROR_MESSAGE);
        return false;
    }
    
   
    //���Ϲ��򣬵�����ӳɹ��Ĵ��ڣ�����������������ݿ�
    
    addAdmin();
    JOptionPane.showMessageDialog(null, "ɾ���ɹ�");
    return true;
}

//�����ݿ��������
void addAdmin() throws ClassNotFoundException, SQLException {
	String sql="delete  from course where sno = ? and cno = ? ";
	Class.forName(driver);
	try {
    	Connection conn = DriverManager.getConnection(url, user, sqlpassword);
    	PreparedStatement ps = conn.prepareStatement(sql);
    	ps.setString(1, this.getSno());
        ps.setString(2, this.getCno());
       
        ps.executeUpdate();
        
        ps.close();	
        conn.close();
        
	}catch(SQLException ex) {
		System.out.println("ɾ��ʧ�ܣ�");
	}
	
}
}


class mysqlSD{
	private String sno;        //ѧ��
	
	
	



public String getSno() {
		return sno;
	}

	public void setSno(String sno) {
		this.sno = sno;
	}

	
private String driver = "com.mysql.jdbc.Driver";
private String url = "jdbc:mysql://localhost:3306/studentdb?characterEncoding=utf-8";
private String user = "root";
private String sqlpassword = "root";


//�ж��Ƿ���Ϲ���
boolean JudgeRegister() throws SQLException, ClassNotFoundException {
    
   
    
    if(this.getSno().equals("")) {
        JOptionPane.showMessageDialog(null, "ѧ�Ų���Ϊ�գ�", "ѧ��Ϊ��", JOptionPane.ERROR_MESSAGE);
        return false;
    }
    
    
    //���Ϲ��򣬵�����ӳɹ��Ĵ��ڣ�����������������ݿ�
    
    addAdmin();
    JOptionPane.showMessageDialog(null, "ɾ���ɹ�");
    return true;
}

//�����ݿ��������
void addAdmin() throws ClassNotFoundException, SQLException {
	String sql="delete  from StudentInfor where sno = ? ";
	Class.forName(driver);
	try {
    	Connection conn = DriverManager.getConnection(url, user, sqlpassword);
    	PreparedStatement ps = conn.prepareStatement(sql);
    	ps.setString(1, this.getSno());
        
        ps.executeUpdate();
        
        ps.close();	
        conn.close();
        
	}catch(SQLException ex) {
		System.out.println("ɾ��ʧ�ܣ�");
	}
	
}
}


class chuangkouC extends JFrame
{
	{ 
		try 
		{
	        for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) 
	        {
	            if ("Nimbus".equals(info.getName())) 
	            {
	                javax.swing.UIManager.setLookAndFeel(info.getClassName());
	                break;
	            }
	        }
	    }
		catch(Exception e) 
		{
    	System.out.println(e);
		}
	}
	private JScrollPane scpDemo;
	private JTableHeader jth;
	private JTable tabDemo;
	private JButton btnShow;
	
	private JButton bt1;
	private JButton bt2;
	private JButton bt3;
	private JButton bt4;
	
	
	
	public chuangkouC()
	{
		super("�γ���Ϣ����ϵͳ");		//JFrame�ı�������
		this.setSize(660,600);		//���ƴ����С
		this.setLayout(null);		//�Զ��岼��
		this.setLocation(400,100);	//��������Ժ󣬴�������Ļ��λ��
		this.scpDemo = new JScrollPane();
		this.bt1=new JButton("����");
		this.bt2=new JButton("ɾ��");
		this.bt3=new JButton("����");
		this.bt4=new JButton("����");
		this.btnShow = new JButton("��ʾ����");
		
		this.bt1.setBounds(100, 480, 100, 30);
		this.bt2.setBounds(200, 480, 100, 30);
		this.bt3.setBounds(300, 480, 100, 30);
		this.bt4.setBounds(400, 480, 100, 30);
		this.scpDemo.setBounds(10,50,580,400);	//���ù������С
		this.btnShow.setBounds(10,10,120,30);	//���ð�ť
		this.btnShow.addActionListener(new ActionListener()	//������ʾ���ݡ���ť����¼���Ӧ��
		{
			public void actionPerformed(ActionEvent ae)
			{
				btnShow_ActionPerformed(ae);
			}
		});
		
		/********��ť�����ӡ�����Ӧ*******/
		
		this.bt1.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{	
				final JFrame frame = new JFrame("��ӿγ���Ϣ");
	            frame.setLayout(null);
	            //�������
	            JLabel txt = new JLabel("������Ҫ��ӿγ̵Ŀγ���Ϣ:");
	            txt.setBounds(250, 100, 200, 25);
	            frame.add(txt);
	            
	            JLabel sno = new JLabel("ѧ��ѧ��:");
	            sno.setBounds(250, 150, 100, 25);
	            frame.add(sno);
	        
	            JLabel cno = new JLabel("�γ̺�  :");
	            cno.setBounds(250, 200, 100, 25);
	            frame.add(cno);

	            JLabel cname = new JLabel("�γ�����:");
	            cname.setBounds(250, 250, 100, 25);
	            frame.add(cname);  
	               
	            JLabel sorce = new JLabel("�γ̳ɼ�:");
	            sorce.setBounds(250, 300, 100, 30);
	            frame.add(sorce);
	            
	            //�ı���ΰ�ť
	            final JTextField sno2 = new JTextField();
	            sno2.setBounds(320, 150, 150, 25);
	            frame.add(sno2);

	            final JTextField cno2 = new JTextField();
	            cno2.setBounds(320, 200, 150, 25);
	            frame.add(cno2);
	            
	            final JTextField cname2 = new JTextField();
	            cname2.setBounds(320, 250, 150, 25);
	            frame.add(cname2);
	            
	            final JTextField sorce2 = new JTextField();
	            sorce2.setBounds(320, 300, 150, 25);
	            frame.add(sorce2);
	            
	            
	            JButton button = new JButton("ȷ�����");
	            button.setBounds(350, 350, 100, 25);
	            frame.add(button);
	            


	            frame.setBounds(400, 100, 800, 640);
	            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	            frame.setVisible(true);
	            
	            //��Ӽ���
	            button.addActionListener(new ActionListener() {
	                
	                public void actionPerformed(ActionEvent e) {
	                    
	                    String sno = sno2.getText();
	                    String cno = cno2.getText();
	                    String cname = cname2.getText();
	                    String sorce = sorce2.getText();
	                    
	                    
	                    //������
	                    mysqlCA mysqlCA = new mysqlCA();
	                    mysqlCA.setSno(sno);
	                    mysqlCA.setCno(cno);
	                    mysqlCA.setCname(cname);
	                    mysqlCA.setSorce(sorce);
	                    
	                    //����ɹ������ص�¼����
	                    try {
							if(mysqlCA.JudgeRegister()) {

							    frame.setVisible(false);
							   
							    chuangkouC cc = new chuangkouC();
							    
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							//e1.printStackTrace();
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

	                }
	                
	            });

			}
		});
		
		/******��ť ��ɾ��������Ӧ*****/
		this.bt2.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				final JFrame frame = new JFrame("ɾ���γ���Ϣ");
	            frame.setLayout(null);
	            // �������
	    
	            JLabel txt1 = new JLabel("������Ҫɾ���Ŀγ���Ϣ:");
	            txt1.setBounds(250, 150, 200, 25);
	            frame.add(txt1);
	            
	            JLabel sno = new JLabel("ѧ��ѧ��:");
	            sno.setBounds(250, 200, 100, 25);
	            frame.add(sno);
	            
	            JLabel cno = new JLabel("�γ̺�  :");
	            cno.setBounds(250, 250, 100, 25);
	            frame.add(cno);
	            

	           
	            
	            //�ı���ΰ�ť
	           
	            final JTextField sno2 = new JTextField();
	            sno2.setBounds(320, 200, 150, 25);
	            frame.add(sno2);
	            
	            final JTextField cno2 = new JTextField();
	            cno2.setBounds(320, 250, 150, 25);
	            frame.add(cno2);
	            
	            JButton button = new JButton("ȷ��ɾ��");
	            button.setBounds(350, 350, 100, 25);
	            frame.add(button);
	            


	            frame.setBounds(400, 100, 800, 640);
	            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	            frame.setVisible(true);
	            
	            //��Ӽ���
	            
	            button.addActionListener(new ActionListener() {
	                
	                public void actionPerformed(ActionEvent e) {
	                    
	                    String sno = sno2.getText();
	                    String cno = cno2.getText();
	                    
	                    
	                    
	                    //������
	                    mysqlCD mysqlCD = new mysqlCD();
	                    mysqlCD.setSno(sno);
	                    mysqlCD.setCno(cno);
	                    
	                    
	                    //����ɹ������ؽ���
	                    try {
							if(mysqlCD.JudgeRegister()) {

							    frame.setVisible(false);
							   
							    chuangkouC cc = new chuangkouC();
							    
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							//e1.printStackTrace();
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

	                }
	                
	            });

			}
		});
		
		
		/******��ť �����ġ�����Ӧ*****/
		this.bt3.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				final JFrame frame = new JFrame("���¿γ���Ϣ");
	            frame.setLayout(null);
	            //�������
	          
	            JLabel txt1 = new JLabel("������Ҫ���¿γ̵���Ϣ:");
	            txt1.setBounds(250, 100, 200, 25);
	            frame.add(txt1);
	            
	            JLabel sno = new JLabel("ѧ��ѧ��:");
	            sno.setBounds(250, 150, 100, 25);
	            frame.add(sno);
	            
	            JLabel cno = new JLabel("�γ̺�  :");
	            cno.setBounds(250, 200, 100, 25);
	            frame.add(cno);

	            JLabel txt2 = new JLabel("������Ҫ����ѧ���Ŀγ̳ɼ�:");
	            txt2.setBounds(250, 250, 200, 25);
	            frame.add(txt2);
	               
	            JLabel sorce = new JLabel("�γ̳ɼ�:");
	            sorce.setBounds(250, 300, 100, 30);
	            frame.add(sorce);
	            
	            //�ı���ΰ�ť
	           

	            final JTextField sno2 = new JTextField();
	            sno2.setBounds(320, 150, 150, 25);
	            frame.add(sno2);
	            
	            final JTextField cno2 = new JTextField();
	            cno2.setBounds(320, 200, 150, 25);
	            frame.add(cno2);
	            
	            
	            final JTextField sorce2 = new JTextField();
	            sorce2.setBounds(320, 300, 150, 25);
	            frame.add(sorce2);
	            
	            
	            JButton button = new JButton("ȷ�ϸ���");
	            button.setBounds(350, 350, 100, 25);
	            frame.add(button);
	            


	            frame.setBounds(400, 100, 800, 640);
	            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	            frame.setVisible(true);
	            
	            //��Ӽ���
	            button.addActionListener(new ActionListener() {
	                
	                public void actionPerformed(ActionEvent e) {
	                    
	                    String sno = sno2.getText();
	                    String cno = cno2.getText();
	                    
	                    String sorce = sorce2.getText();
	                    
	                    
	                    //������
	                    mysqlCU mysqlCU = new mysqlCU();
	                    mysqlCU.setSno(sno);
	                    mysqlCU.setCno(cno);
	                  
	                    mysqlCU.setSorce(sorce);
	                    
	                    //����ɹ������ؽ���
	                    try {
							if(mysqlCU.JudgeRegister()) {

							    frame.setVisible(false);
							   
							    chuangkouC cc = new chuangkouC();
							    
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							//e1.printStackTrace();
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

	                }
	                
	            });

			}
		});
		
		
		/******��ť �����ҡ�����Ӧ*****/
		this.bt4.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				final JFrame frame = new JFrame("���ҿγ���Ϣ");
	            frame.setLayout(null);
	            // �������
	            JLabel txt1 = new JLabel("������ҳɼ��Ŀγ���Ϣ��");
	            txt1.setBounds(250, 100, 200, 25);
	            frame.add(txt1);
	            
	            JLabel sno = new JLabel("ѧ��ѧ��:");
	            sno.setBounds(250, 150, 100, 25);
	            frame.add(sno);
	            
	            JLabel cno = new JLabel("�γ̺�  :");
	            cno.setBounds(250, 200, 100, 25);
	            frame.add(cno);

	           
	            
	            //�ı���ΰ�ť
	           
	            final JTextField sno2 = new JTextField();
	            sno2.setBounds(320, 150, 150, 25);
	            frame.add(sno2);
	            
	            final JTextField cno2 = new JTextField();
	            cno2.setBounds(320, 200, 150, 25);
	            frame.add(cno2);
	         
	            
	            JButton button = new JButton("ȷ�ϲ���");
	            button.setBounds(350, 350, 100, 25);
	            frame.add(button);
	            


	            frame.setBounds(400, 100, 800, 640);
	            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	            frame.setVisible(true);
	            
	            //��Ӽ���
	            button.addActionListener(new ActionListener() {
	                
	                public void actionPerformed(ActionEvent e) {
	                   
	                    String sno = sno2.getText();
	                    String cno = cno2.getText();
	                   
	                    
	                    
	                    //������
	                    mysqlCS mysqlCS = new mysqlCS();
	                    
	                    mysqlCS.setSno(sno);
	                    mysqlCS.setCno(cno);
	                  
	                    
	                    //����ɹ������ؽ���
	                    try {
							if(mysqlCS.JudgeRegister()) {

							    frame.setVisible(false);
							   

							    
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							//e1.printStackTrace();
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

	                }
	                
	            });

			}
		});
		
		/******* ��������뵽������******/
		add(this.scpDemo);
		add(this.btnShow);
		add(this.bt1);
		add(this.bt2);
		add(this.bt3);
		add(this.bt4);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		}
	/***�������ݿⲢ��ʾ�������***/
	
	
	public void btnShow_ActionPerformed(ActionEvent ae)
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver"); 
			String url = "jdbc:mysql://localhost:3306/studentdb?characterEncoding=utf-8";
			String username = "root";
			String passwords = "root";
			Connection conn=DriverManager.getConnection(url, username, passwords);
			String sql = "select * from Course"; 
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			int count = 0;
			while(rs.next())
			{
				count++;
			}
				rs = pstm.executeQuery();
	// ����ѯ��õļ�¼���ݣ�ת�����ʺ�����JTable��������ʽ
	Object[][] info = new Object[count][4];
	String []title= {"sno","cno","cname","sorce"};
	count = 0;
	while(rs.next())
	{
		String sno = rs.getString("sno");
		String cno = rs.getString("cno");
		String cname = rs.getString("cname");
		String sorce = rs.getString("sorce");
		info[count][0] = sno;
		info[count][1] = cno;
		info[count][2] = cname;
		info[count][3] = sorce;
		count++;
	}
	// ����JTable
	this.tabDemo = new JTable(info,title);
	// ��ʾ��ͷ
	this.jth = this.tabDemo.getTableHeader();
	// ��JTable���뵽���������������
	this.scpDemo.getViewport().add(tabDemo); 
	}
	catch(ClassNotFoundException cnfe)
	{
		cnfe.printStackTrace();
		JOptionPane.showMessageDialog(null,"����Դ����","����",JOptionPane.ERROR_MESSAGE);
	}
	catch(SQLException sqle)
	{
		sqle.printStackTrace();
		JOptionPane.showMessageDialog(null,"���ݲ�������","����",JOptionPane.ERROR_MESSAGE);
	} 
	}
	
}
 


 class chuangkouS extends JFrame
{
	{ 
		try 
		{
	        for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) 
	        {
	            if ("Nimbus".equals(info.getName())) 
	            {
	                javax.swing.UIManager.setLookAndFeel(info.getClassName());
	                break;
	            }
	        }
	    }
		catch(Exception e) 
		{
    	System.out.println(e);
		}
	}
	private JScrollPane scpDemo;
	private JTableHeader jth;
	private JTable tabDemo;
	private JButton btnShow;
	
	private JButton bt1;
	private JButton bt2;
	private JButton bt3;
	private JButton bt4;
	
	
	
	public chuangkouS()
	{
		super("ѧ����Ϣ����ϵͳ");		//JFrame�ı�������
		this.setSize(660,600);		//���ƴ����С
		this.setLayout(null);		//�Զ��岼��
		this.setLocation(400,100);	//��������Ժ󣬴�������Ļ��λ��
		this.scpDemo = new JScrollPane();
		this.bt1=new JButton("����");
		this.bt2=new JButton("ɾ��");
		this.bt3=new JButton("����");
		this.bt4=new JButton("����");
		this.btnShow = new JButton("��ʾ����");
		
		this.bt1.setBounds(100, 480, 100, 30);
		this.bt2.setBounds(200, 480, 100, 30);
		this.bt3.setBounds(300, 480, 100, 30);
		this.bt4.setBounds(400, 480, 100, 30);
		this.scpDemo.setBounds(10,50,580,400);	//���ù������С
		this.btnShow.setBounds(10,10,120,30);	//���ð�ť
		
		this.btnShow.addActionListener(new ActionListener()	//������ʾ���ݡ���ť����¼���Ӧ��
		{
			public void actionPerformed(ActionEvent ae)
			{
				btnShow_ActionPerformed(ae);
			}
		});
		
		/********��ť�����ӡ�����Ӧ*******/
		
		this.bt1.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{	
				final JFrame frame = new JFrame("���ѧ����Ϣ");
	            frame.setLayout(null);
	            //�������
	            JLabel txt = new JLabel("������Ҫ���ѧ����ѧ����Ϣ:");
	            txt.setBounds(250, 100, 200, 25);
	            frame.add(txt);
	            
	            JLabel sname = new JLabel("ѧ������:");
	            sname.setBounds(250, 150, 100, 25);
	            frame.add(sname);
	        
	            JLabel sno = new JLabel("ѧ��ѧ��:");
	            sno.setBounds(250, 200, 100, 25);
	            frame.add(sno);

	            JLabel sex = new JLabel("ѧ���Ա�:");
	            sex.setBounds(250, 250, 100, 25);
	            frame.add(sex);  
	               
	            JLabel sage = new JLabel("ѧ������:");
	            sage.setBounds(250, 300, 100, 30);
	            frame.add(sage);
	            
	            //�ı���ΰ�ť
	            final JTextField sname2 = new JTextField();
	            sname2.setBounds(320, 150, 150, 25);
	            frame.add(sname2);

	            final JTextField sno2 = new JTextField();
	            sno2.setBounds(320, 200, 150, 25);
	            frame.add(sno2);
	            
	            String[] listData = new String[] { "Ů", "��" };
	    		final JComboBox sex2 = new JComboBox(listData);
	    		sex2.setBounds(320, 250, 150, 25);
	    		frame.add(sex2);
	            
	            
	           /* final JTextField sex2 = new JTextField();
	            sex2.setBounds(320, 250, 150, 25);
	            frame.add(sex2);*/
	            
	            final JTextField sage2 = new JTextField();
	            sage2.setBounds(320, 300, 150, 25);
	            frame.add(sage2);
	            
	            
	            JButton button = new JButton("ȷ�����");
	            button.setBounds(350, 350, 100, 25);
	            frame.add(button);
	            


	            frame.setBounds(400, 100, 800, 640);
	            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	            frame.setVisible(true);
	            
	            //��Ӽ���
	            button.addActionListener(new ActionListener() {
	                
	                public void actionPerformed(ActionEvent e) {
	                    String sex = (String)sex2.getSelectedItem();
	                    String sname = sname2.getText();
	                    String sno = sno2.getText();
	                    String sage = sage2.getText();
	                    
	                    
	                    //������
	                    mysqlSA mysqlSA = new mysqlSA();
	                    mysqlSA.setSex(sex);
	                    mysqlSA.setSname(sname);
	                    mysqlSA.setSno(sno);
	                    mysqlSA.setSage(sage);
	                    
	                    //����ɹ������ؽ���
	                    try {
							if(mysqlSA.JudgeRegister()) {

							    frame.setVisible(false);
							   
							    chuangkouS cs = new chuangkouS();
							    
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							//e1.printStackTrace();
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

	                }
	                
	            });

			}
		});
		/******��ť ��ɾ��������Ӧ*****/
		this.bt2.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				final JFrame frame = new JFrame("ɾ��ѧ����Ϣ");
	            frame.setLayout(null);
	            // �������
	    
	            JLabel txt1 = new JLabel("������Ҫɾ��ѧ����ѧ��ѧ��:");
	            txt1.setBounds(250, 150, 200, 25);
	            frame.add(txt1);
	            
	            JLabel sno = new JLabel("ѧ��ѧ��:");
	            sno.setBounds(250, 200, 100, 25);
	            frame.add(sno);

	           
	            
	            //�ı���ΰ�ť
	           
	            final JTextField sno2 = new JTextField();
	            sno2.setBounds(320, 200, 150, 25);
	            frame.add(sno2);
	         
	            
	            JButton button = new JButton("ȷ��ɾ��");
	            button.setBounds(350, 350, 100, 25);
	            frame.add(button);
	            


	            frame.setBounds(400, 100, 800, 640);
	            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	            frame.setVisible(true);
	            
	            //��Ӽ���
	            button.addActionListener(new ActionListener() {
	                
	                public void actionPerformed(ActionEvent e) {
	                   
	                    String sno = sno2.getText();
	                   
	                    
	                    
	                    //������
	                    mysqlSD mysqlSD = new mysqlSD();
	                  
	                    mysqlSD.setSno(sno);
	                  
	                    
	                    //����ɹ������ؽ���
	                    try {
							if(mysqlSD.JudgeRegister()) {

							    frame.setVisible(false);
							   
							    chuangkouS cs = new chuangkouS();
							    
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							//e1.printStackTrace();
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

	                }
	                
	            });

			}
		});
		
		/******��ť �����ġ�����Ӧ*****/
		this.bt3.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				final JFrame frame = new JFrame("����ѧ����Ϣ");
	            frame.setLayout(null);
	            //�������
	          
	            JLabel txt1 = new JLabel("������Ҫ����ѧ����ѧ��ѧ��:");
	            txt1.setBounds(250, 150, 200, 25);
	            frame.add(txt1);
	            
	            JLabel sno = new JLabel("ѧ��ѧ��:");
	            sno.setBounds(250, 200, 100, 25);
	            frame.add(sno);

	            JLabel txt2 = new JLabel("������Ҫ����ѧ����ѧ������:");
	            txt2.setBounds(250, 250, 200, 25);
	            frame.add(txt2);
	               
	            JLabel sage = new JLabel("ѧ������:");
	            sage.setBounds(250, 300, 100, 30);
	            frame.add(sage);
	            
	            //�ı���ΰ�ť
	           

	            final JTextField sno2 = new JTextField();
	            sno2.setBounds(320, 200, 150, 25);
	            frame.add(sno2);
	            
	            
	            
	            final JTextField sage2 = new JTextField();
	            sage2.setBounds(320, 300, 150, 25);
	            frame.add(sage2);
	            
	            
	            JButton button = new JButton("ȷ�ϸ���");
	            button.setBounds(350, 350, 100, 25);
	            frame.add(button);
	            


	            frame.setBounds(400, 100, 800, 640);
	            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	            frame.setVisible(true);
	            
	            //��Ӽ���
	            button.addActionListener(new ActionListener() {
	                
	                public void actionPerformed(ActionEvent e) {
	                  
	                    String sno = sno2.getText();
	                    String sage = sage2.getText();
	                    
	                    
	                    //������
	                    mysqlSU mysqlSU = new mysqlSU();
	                  
	                    mysqlSU.setSno(sno);
	                    mysqlSU.setSage(sage);
	                    
	                    //����ɹ������ؽ���
	                    try {
							if(mysqlSU.JudgeRegister()) {

							    frame.setVisible(false);
							   
							    chuangkouS cs = new chuangkouS();
							    
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							//e1.printStackTrace();
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

	                }
	                
	            });

			}
		});
		/******��ť �����ҡ�����Ӧ*****/
		this.bt4.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				final JFrame frame = new JFrame("�������ѧ����ѧ��");
	            frame.setLayout(null);
	            // �������
	    
	            JLabel sno = new JLabel("ѧ��ѧ��:");
	            sno.setBounds(250, 200, 100, 25);
	            frame.add(sno);

	           
	            
	            //�ı���ΰ�ť
	           
	            final JTextField sno2 = new JTextField();
	            sno2.setBounds(320, 200, 150, 25);
	            frame.add(sno2);
	         
	            
	            JButton button = new JButton("ȷ�ϲ���");
	            button.setBounds(350, 350, 100, 25);
	            frame.add(button);
	            


	            frame.setBounds(400, 100, 800, 640);
	            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	            frame.setVisible(true);
	            
	            //��Ӽ���
	            button.addActionListener(new ActionListener() {
	                
	                public void actionPerformed(ActionEvent e) {
	                   
	                    String sno = sno2.getText();
	                   
	                    
	                    
	                    //������
	                    mysqlSS mysqlSS = new mysqlSS();
	                    
	                    mysqlSS.setSno(sno);
	                  
	                    
	                    //����ɹ������ؽ���
	                    try {
							if(mysqlSS.JudgeRegister()) {

							    frame.setVisible(false);
							   

							    
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							//e1.printStackTrace();
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

	                }
	                
	            });

			}
		});
		
		/******* ��������뵽������******/
		add(this.scpDemo);
		add(this.btnShow);
		add(this.bt1);
		add(this.bt2);
		add(this.bt3);
		add(this.bt4);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		}
	/***�������ݿⲢ��ʾ�������***/
	
	
	
	
	public void btnShow_ActionPerformed(ActionEvent ae)
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver"); 
			String url = "jdbc:mysql://localhost:3306/studentdb?characterEncoding=utf-8";
			String username = "root";
			String passwords = "root";
			Connection conn=DriverManager.getConnection(url, username, passwords);
			String sql = "select * from StudentInfor"; 
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			int count = 0;
			while(rs.next())
			{
				count++;
			}
				rs = pstm.executeQuery();
	// ����ѯ��õļ�¼���ݣ�ת�����ʺ�����JTable��������ʽ
	Object[][] info = new Object[count][4];
	String []title= {"sname","sno"," sex","sage"};
	count = 0;
	while(rs.next())
	{
		String sname = rs.getString("sname");
		String sno = rs.getString("sno");
		String sex = rs.getString("sex");
		String sage = rs.getString("sage");
		info[count][0] = sname;
		info[count][1] = sno;
		info[count][2] = sex;
		info[count][3] = sage;
		count++;
	}
	// ����JTable
	this.tabDemo = new JTable(info,title);
	// ��ʾ��ͷ
	this.jth = this.tabDemo.getTableHeader();
	// ��JTable���뵽���������������
	this.scpDemo.getViewport().add(tabDemo); 
	}
	catch(ClassNotFoundException cnfe)
	{
		cnfe.printStackTrace();
		JOptionPane.showMessageDialog(null,"����Դ����","����",JOptionPane.ERROR_MESSAGE);
	}
	catch(SQLException sqle)
	{
		sqle.printStackTrace();
		JOptionPane.showMessageDialog(null,"���ݲ�������","����",JOptionPane.ERROR_MESSAGE);
	} 
	}
	
}
 


public class test extends JFrame{    
	
	
	test() {
	init();
	}
	//��¼�����ʼ��
	public void init() {
	final JFrame frame = new JFrame("ѧ����Ϣ����ϵͳ");
        frame.setLayout(null);
        
        JLabel label = new JLabel("�������˺������¼ѧ����Ϣ����ϵͳ��");
		label.setBounds(100,40,300,50);
		frame.add(label);
        
		JLabel account = new JLabel("�˺�");
		account.setBounds(100,100,100,50);
		frame.add(account);
        
        
        JLabel password = new JLabel("����");
		password.setBounds(100,200,100,50);
		frame.add(password);
		
		JLabel identity = new JLabel("���");
		identity.setBounds(100,300,100,50);
		frame.add(identity);
		
		//�ı���Ͱ�ť
		
		
		final JTextField account2 = new JTextField();
		account2.setBounds(150, 100, 300, 50);
		frame.add(account2);
		
		final JPasswordField password2 = new JPasswordField();
		password2.setBounds(150, 200, 300, 50);
		frame.add(password2);
		
		String[] listData = new String[] { "ѧ��", "��ʦ", "����Ա" };
		final JComboBox identity2 = new JComboBox(listData);
		identity2.setBounds(150, 300, 300, 50);
		frame.add(identity2);
		
		
		JButton Blogin = new JButton("��½");
		Blogin.setBounds(100, 450, 100, 50);
		frame.add(Blogin);
        
		JButton Blogon = new JButton("ע��");
		Blogon.setBounds(350, 450, 100, 50);
		frame.add(Blogon);

        frame.setBounds(400, 100, 800, 640);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
        //Ϊ��¼��ť��Ӽ�����
        Blogin.addActionListener(
        	new ActionListener() {  
            public void actionPerformed(ActionEvent e) {
                String Account = account2.getText();
                String Password = new String (password2.getPassword());
                String Identity = (String)identity2.getSelectedItem();
                  
                //����һ��Admin�û�����������е��û�������������
                Admin admin = new Admin();
                admin.setAccount(Account);
                admin.setPassword(Password);
                admin.setIdentity(Identity);
                
                //��¼
                Login login = new Login();
                login.setAdmin(admin);
          
                if(login.JudgeAdmin()==0) {
                	//�����˺Ż��������Ĵ���
                	JOptionPane.showMessageDialog(null, "�˺Ż��������ݴ���", "�˺Ż��������ݴ���", JOptionPane.WARNING_MESSAGE);
                	//���������е���Ϣ
                	password2.setText("");
                	//����˺ſ��е���Ϣ
                	account2.setText("");
                	
                	//System.out.println("��½ʧ��");
                } else {
                	//������¼�ɹ��Ĵ���
                	JOptionPane.showMessageDialog(null, "��½�ɹ�", "��½�ɹ�", JOptionPane.NO_OPTION);
                	//���ȷ�������ת��������
                	frame.setVisible(false);
                	
                	//System.out.println(identity2.getSelectedItem());
               	
                	
                	
	               	if ((String)identity2.getSelectedItem() == "����Ա"){
	               		masterSystem ms = new masterSystem();
	               	}else if((String)identity2.getSelectedItem() == "ѧ��"){
	               		studentSystem ss = new studentSystem();
	               	}else if((String)identity2.getSelectedItem() == "��ʦ"){
	               		teacherSystem ts = new teacherSystem();
	               	}
                	//AdminRegister ar = new AdminRegister(); 
                        
                }
               
            }
        });
         
         //Ϊע�ᰴť��Ӽ����� 
        Blogon.addActionListener(new ActionListener() {
        	 public void actionPerformed(ActionEvent e) {
        		 //ע��ҳ��
                 frame.setVisible(false);
        		 Register ar = new Register(); 
        	 }
         });
	}
	
    public static void main(String []args) { 
       //������
       //��¼����
    	test t = new test();
    }
}


