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
管理员实体
*/

class Admin {
	private String account;                 //账号
	private String identity;           //身份
	private String password;      //密码
	private String confirmPassword; //第二次输入的密码
	
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
处理用户登录
*/


class Login {

	Admin admin;
	
	void setAdmin(Admin admin) {
		this.admin=admin;
		
	}
	/*
	 * JudgeAdmin()方法
	 * 判断Admin的ID和密码和身份选择是否正确，如果正确，显示登录成功
	 * 如果错误，弹出一个窗口，显示账号或密码或身份错误
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
		        	System.out.println("登录成功");
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
 * 管理员注册界面
 * 
 */
class Register extends JFrame{
	Register () {
		init();
	}
	
	void init() {
		 final JFrame frame = new JFrame("注册账号");
            frame.setLayout(null);
            // 登陆板块内容
            JLabel identity = new JLabel("身份:");
            identity.setBounds(250, 150, 100, 25);
            frame.add(identity);
        
            JLabel account = new JLabel("账号:");
            account.setBounds(250, 200, 100, 25);
            frame.add(account);

            JLabel password = new JLabel("密码:");
            password.setBounds(250, 250, 100, 25);
            frame.add(password);  
               
            JLabel confrimP = new JLabel("确认密码:");
            confrimP.setBounds(250, 300, 100, 30);
            frame.add(confrimP);
            
            //文本框何按钮
            
            String[] listData = new String[] { "学生", "老师", "管理员" };
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
            
            JButton logIn = new JButton("注册");
            logIn.setBounds(350, 350, 70, 25);
            frame.add(logIn);
            


            frame.setBounds(400, 100, 800, 640);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
            
          //为注册按钮增加监听器
            logIn.addActionListener(new ActionListener() {
               
                public void actionPerformed(ActionEvent e) {
                    String identity = (String)identity2.getSelectedItem();
                    String account = account2.getText();
                    String password = new String (password2.getPassword());
                    String confirmPassword = new String (confrimP2.getPassword());
                    
                    //创建Register类
                    Registerr register = new Registerr();
                    register.setAccount(account);
                    register.setIdentity(identity);
                    register.setPassword(password);
                    register.setConfirmPassword(confirmPassword);
                    
                    //如果注册成功，返回登录界面
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
	
		private String account;        //账号
		private String identity;      //身份
		private String password;      //密码
		private String confirmPassword; //第二次输入的密码
		
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
    
    
    //判断注册的账号是否符合规则
    boolean JudgeRegister() throws SQLException, ClassNotFoundException {
        
        if(this.getIdentity().equals("")) {
            JOptionPane.showMessageDialog(null, "身份不能为空！", "身份", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if(this.getAccount().equals("")) {
            JOptionPane.showMessageDialog(null, "账号不能为空！", "账号为空", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if(this.getPassword().equals("")) {
            JOptionPane.showMessageDialog(null, "密码不能为空！", "密码为空", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if(!this.getPassword().equals(this.getConfirmPassword())) {
            JOptionPane.showMessageDialog(null, "两次输入的密码不一致!", "密码不一致", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        //符合规则，弹出注册成功的窗口，并将账号添加数据库
        
        addAdmin();
        JOptionPane.showMessageDialog(null, "注册成功");
        return true;
    }
    
    //向数据库添加Admin账户
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
    		System.out.println("注册失败！");
    	}
    	
    }
}

class masterSystem{
	masterSystem(){
		init();
	}
	public static void init(){
		final JFrame frame = new JFrame("管理员界面");
        frame.setLayout(null);
        //板块内容
        JLabel txt = new JLabel("请点击要进入的操作系统:");
        txt.setBounds(300, 200, 200, 25);
        frame.add(txt);
        
      
        JButton studentS = new JButton("学生信息操作系统");
        studentS.setBounds(300, 250, 200, 25);
        frame.add(studentS);
        
        JButton courseS = new JButton("课程信息操作系统");
        courseS.setBounds(300, 300, 200, 25);
        frame.add(courseS);


        frame.setBounds(400, 100, 800, 640);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
        //添加监听
        studentS.addActionListener(new ActionListener() {
       	 public void actionPerformed(ActionEvent e) {
       		 //学生信息操作系统
                frame.setVisible(false);
       		 chuangkouS cs = new chuangkouS(); 
       	 }
        });
        courseS.addActionListener(new ActionListener() {
          	 public void actionPerformed(ActionEvent e) {
          		 //学生信息操作系统
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
		final JFrame frame = new JFrame("老师界面");
        frame.setLayout(null);
        //板块内容
        JLabel txt = new JLabel("请点击要执行的操作:");
        txt.setBounds(300, 200, 200, 25);
        frame.add(txt);
        
      
        JButton sorceA = new JButton("学生成绩录入");
        sorceA.setBounds(300, 250, 200, 25);
        frame.add(sorceA);
        
        JButton sorceSOD = new JButton("学生成绩查询排序");	//降序
        sorceSOD.setBounds(300, 300, 200, 25);
        frame.add(sorceSOD);


        frame.setBounds(400, 100, 800, 640);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
        //添加监听
        sorceA.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				final JFrame frame = new JFrame("录入学生成绩");
	            frame.setLayout(null);
	            //板块内容
	          
	            JLabel txt1 = new JLabel("请输入要录入课程的信息:");
	            txt1.setBounds(250, 100, 200, 25);
	            frame.add(txt1);
	            
	            JLabel sno = new JLabel("学生学号:");
	            sno.setBounds(250, 150, 100, 25);
	            frame.add(sno);
	            
	            JLabel cno = new JLabel("课程号  :");
	            cno.setBounds(250, 200, 100, 25);
	            frame.add(cno);

	            JLabel txt2 = new JLabel("请输入要录入学生的课程成绩:");
	            txt2.setBounds(250, 250, 200, 25);
	            frame.add(txt2);
	               
	            JLabel sorce = new JLabel("课程成绩:");
	            sorce.setBounds(250, 300, 100, 30);
	            frame.add(sorce);
	            
	            //文本框何按钮
	           

	            final JTextField sno2 = new JTextField();
	            sno2.setBounds(320, 150, 150, 25);
	            frame.add(sno2);
	            
	            final JTextField cno2 = new JTextField();
	            cno2.setBounds(320, 200, 150, 25);
	            frame.add(cno2);
	            
	            
	            final JTextField sorce2 = new JTextField();
	            sorce2.setBounds(320, 300, 150, 25);
	            frame.add(sorce2);
	            
	            
	            JButton button = new JButton("确认录入");
	            button.setBounds(350, 350, 100, 25);
	            frame.add(button);
	            


	            frame.setBounds(400, 100, 800, 640);
	            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	            frame.setVisible(true);
	            
	            //添加监听
	            button.addActionListener(new ActionListener() {
	                
	                public void actionPerformed(ActionEvent e) {
	                    
	                    String sno = sno2.getText();
	                    String cno = cno2.getText();
	                    
	                    String sorce = sorce2.getText();
	                    
	                    
	                    //创建类
	                    mysqlAS mysqlAS = new mysqlAS();
	                    mysqlAS.setSno(sno);
	                    mysqlAS.setCno(cno);
	                  
	                    mysqlAS.setSorce(sorce);
	                    
	                    //如果录入成功，返回界面
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
				final JFrame frame = new JFrame("查找课程信息");
	            frame.setLayout(null);
	            // 板块内容
	            JLabel txt1 = new JLabel("输入查找成绩的课程信息：");
	            txt1.setBounds(250, 100, 200, 25);
	            frame.add(txt1);
	            
	            
	            
	            JLabel cno = new JLabel("课程号  :");
	            cno.setBounds(250, 200, 100, 25);
	            frame.add(cno);

	           
	            
	            //文本框何按钮
	           
	          
	            final JTextField cno2 = new JTextField();
	            cno2.setBounds(320, 200, 150, 25);
	            frame.add(cno2);
	         
	            
	            JButton button = new JButton("确认查找");
	            button.setBounds(350, 350, 100, 25);
	            frame.add(button);
	            


	            frame.setBounds(400, 100, 800, 640);
	            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	            frame.setVisible(true);
	            
	            //添加监听
	            button.addActionListener(new ActionListener() {
	                
	                public void actionPerformed(ActionEvent e) {
	                   
	                   
	                    String cno = cno2.getText();
	                   
	                    
	                    
	                    //创建类
	                    mysqlSSOD mysqlSSOD = new mysqlSSOD();
	                    
	                   
	                    mysqlSSOD.setCno(cno);
	                  
	                    
	                    //如果成功，返回界面
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
		final JFrame frame = new JFrame("学生界面");
        frame.setLayout(null);
        //板块内容
        JLabel txt = new JLabel("请点击要执行的操作:");
        txt.setBounds(300, 200, 200, 25);
        frame.add(txt);
        
      
        JButton passwordU = new JButton("修改登录密码");
        passwordU.setBounds(300, 250, 200, 25);
        frame.add(passwordU);
        
        JButton snoSOD = new JButton("查看自己课程成绩");	//降序
        snoSOD.setBounds(300, 300, 200, 25);
        frame.add(snoSOD);


        frame.setBounds(400, 100, 800, 640);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
        //添加监听
        passwordU.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				final JFrame frame = new JFrame("个人登录密码修改");
	            frame.setLayout(null);
	            //板块内容
	          
	            JLabel txt1 = new JLabel("请输入要修改的账号:");
	            txt1.setBounds(250, 100, 200, 25);
	            frame.add(txt1);
	            
	          
	            
	            JLabel account = new JLabel("账号  :");
	            account.setBounds(250, 150, 100, 25);
	            frame.add(account);

	            JLabel txt2 = new JLabel("请输入要录入学生的课程成绩:");
	            txt2.setBounds(250, 200, 200, 25);
	            frame.add(txt2);
	               
	            JLabel password = new JLabel("密码:");
	            password.setBounds(250, 250, 100, 25);
	            frame.add(password);  
	               
	            JLabel confrimP = new JLabel("确认密码:");
	            confrimP.setBounds(250, 300, 100, 30);
	            frame.add(confrimP);
	            
	            //文本框何按钮
	           

	            final JTextField account2 = new JTextField();
	            account2.setBounds(320, 150, 150, 25);
	            frame.add(account2);

	            final JPasswordField password2 = new JPasswordField();
	            password2.setBounds(320, 250, 150, 25);
	            frame.add(password2);

	            final JPasswordField confrimP2 = new JPasswordField();
	            confrimP2.setBounds(320, 300, 150, 25);
	            frame.add(confrimP2);
	            
	            
	            JButton button = new JButton("确认修改");
	            button.setBounds(350, 350, 100, 25);
	            frame.add(button);
	            


	            frame.setBounds(400, 100, 800, 640);
	            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	            frame.setVisible(true);
	            
	            //添加监听
	            button.addActionListener(new ActionListener() {
	                
	                public void actionPerformed(ActionEvent e) {
	                    
	                	String account = account2.getText();
	                    String password = new String (password2.getPassword());
	                    String confirmPassword = new String (confrimP2.getPassword());
	                    
	                    
	                    //创建类
	                    mysqlPU mysqlPU = new mysqlPU();
	                    mysqlPU.setAccount(account);
	                   
	                    mysqlPU.setPassword(password);
	                    mysqlPU.setConfirmPassword(confirmPassword);
	                    
	                    //如果录入成功，退回到登录界面
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
		//监听2
        snoSOD.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				final JFrame frame = new JFrame("查找个人成绩");
	            frame.setLayout(null);
	            // 板块内容
	            JLabel txt1 = new JLabel("输入查找成绩的个人学号：");
	            txt1.setBounds(250, 100, 200, 25);
	            frame.add(txt1);
	            
	            
	            
	            JLabel sno = new JLabel("个人学号  :");
	            sno.setBounds(250, 200, 100, 25);
	            frame.add(sno);

	           
	            
	            //文本框何按钮
	           
	          
	            final JTextField sno2 = new JTextField();
	            sno2.setBounds(320, 200, 150, 25);
	            frame.add(sno2);
	         
	            
	            JButton button = new JButton("确认查找");
	            button.setBounds(350, 350, 100, 25);
	            frame.add(button);
	            


	            frame.setBounds(400, 100, 800, 640);
	            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	            frame.setVisible(true);
	            
	            //添加监听
	            button.addActionListener(new ActionListener() {
	                
	                public void actionPerformed(ActionEvent e) {
	                   
	                   
	                    String sno = sno2.getText();
	                   
	                    
	                    
	                    //创建类
	                    mysqlSSOD2 mysqlSSOD2 = new mysqlSSOD2();
	                    
	                   
	                    mysqlSSOD2.setSno(sno);
	                  
	                    
	                    //如果成功，返回界面
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
	
	private String sno;        //学号
	private String sname;      //姓名
	private String sex;      //性别
	private String sage; //年龄
	
	



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


//判断是否符合规则
boolean JudgeRegister() throws SQLException, ClassNotFoundException {
    
    if(this.getSex().equals("")) {
        JOptionPane.showMessageDialog(null, "性别不能为空！", "性别为空", JOptionPane.ERROR_MESSAGE);
        return false;
    }
    
    if(this.getSno().equals("")) {
        JOptionPane.showMessageDialog(null, "学号不能为空！", "学号为空", JOptionPane.ERROR_MESSAGE);
        return false;
    }
    
    if(this.getSname().equals("")) {
        JOptionPane.showMessageDialog(null, "姓名不能为空！", "姓名为空", JOptionPane.ERROR_MESSAGE);
        return false;
    }
    
    if(this.getSage().equals("")) {
        JOptionPane.showMessageDialog(null, "年龄不能为空！", "年龄为空", JOptionPane.ERROR_MESSAGE);
        return false;
    }
    
    //符合规则，弹出添加成功的窗口，并将数据添加至数据库
    
    addAdmin();
    JOptionPane.showMessageDialog(null, "添加成功");
    return true;
}

//向数据库添加数据
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
		System.out.println("添加失败！");
	}
	
}
}

class mysqlSS extends JFrame{
	
	private String sno;        //学号
	
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
	super("课程信息操作系统");		//JFrame的标题名称
	this.setSize(660,600);		//控制窗体大小
	this.setLayout(null);		//自定义布局
	this.setLocation(400,100);	//点击运行以后，窗体在屏幕的位置
	this.scpDemo = new JScrollPane();
	this.bt1=new JButton("退出");
	
	this.btnShow = new JButton("显示数据");
	
	this.bt1.setBounds(100, 480, 100, 30);
	
	this.scpDemo.setBounds(10,50,580,400);	//设置滚动框大小
	this.btnShow.setBounds(10,10,120,30);	//设置按钮
	this.btnShow.addActionListener(new ActionListener()	//给“显示数据”按钮添加事件响应。
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
	


//判断是否符合规则
boolean JudgeRegister() throws SQLException, ClassNotFoundException {
    
    
    
    if(this.getSno().equals("")) {
        JOptionPane.showMessageDialog(null, "学号不能为空！", "学号为空", JOptionPane.ERROR_MESSAGE);
        return false;
    }
    
    
    
    
    
    //符合规则，弹出添加成功的窗口，并将数据添加至数据库
    
 
    //JOptionPane.showMessageDialog(null, "查找成功");
    
    return true;
}

//列数据
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
	// 将查询获得的记录数据，转换成适合生成JTable的数据形式
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
	
	
	
	
	
	
	// 创建JTable
	this.tabDemo = new JTable(info,title);
	// 显示表头
	this.jth = this.tabDemo.getTableHeader();
	// 将JTable加入到带滚动条的面板中
	this.scpDemo.getViewport().add(tabDemo);
	
	
	
	

	
	
	}
	catch(ClassNotFoundException cnfe)
	{
		cnfe.printStackTrace();
		JOptionPane.showMessageDialog(null,"数据源错误","错误",JOptionPane.ERROR_MESSAGE);
	}
	catch(SQLException sqle)
	{
		sqle.printStackTrace();
		JOptionPane.showMessageDialog(null,"数据操作错误","错误",JOptionPane.ERROR_MESSAGE);
	} 
	}

}


class mysqlCS extends JFrame{
	
	private String sno;        //学号
	private String cno;        //学号
	
	
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
	super("课程信息查询反馈系统");		//JFrame的标题名称
	this.setSize(660,600);		//控制窗体大小
	this.setLayout(null);		//自定义布局
	this.setLocation(400,100);	//点击运行以后，窗体在屏幕的位置
	this.scpDemo = new JScrollPane();
	this.bt1=new JButton("退出");
	
	this.btnShow = new JButton("显示数据");
	
	this.bt1.setBounds(100, 480, 100, 30);
	
	this.scpDemo.setBounds(10,50,580,400);	//设置滚动框大小
	this.btnShow.setBounds(10,10,120,30);	//设置按钮
	this.btnShow.addActionListener(new ActionListener()	//给“显示数据”按钮添加事件响应。
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
	


//判断是否符合规则
boolean JudgeRegister() throws SQLException, ClassNotFoundException {
    
    
    
    if(this.getSno().equals("")) {
        JOptionPane.showMessageDialog(null, "学号不能为空！", "学号为空", JOptionPane.ERROR_MESSAGE);
        return false;
    }
    
    if(this.getCno().equals("")) {
        JOptionPane.showMessageDialog(null, "课程号不能为空！", "课程号为空", JOptionPane.ERROR_MESSAGE);
        return false;
    }
    
    
    
    //符合规则，弹出添加成功的窗口，并将数据添加至数据库
    
 
    //JOptionPane.showMessageDialog(null, "查找成功");
    
    return true;
}

//列数据
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
	// 将查询获得的记录数据，转换成适合生成JTable的数据形式
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
	
	
	
	
	
	
	// 创建JTable
	this.tabDemo = new JTable(info,title);
	// 显示表头
	this.jth = this.tabDemo.getTableHeader();
	// 将JTable加入到带滚动条的面板中
	this.scpDemo.getViewport().add(tabDemo);
	
	
	
	

	
	
	}
	catch(ClassNotFoundException cnfe)
	{
		cnfe.printStackTrace();
		JOptionPane.showMessageDialog(null,"数据源错误","错误",JOptionPane.ERROR_MESSAGE);
	}
	catch(SQLException sqle)
	{
		sqle.printStackTrace();
		JOptionPane.showMessageDialog(null,"数据操作错误","错误",JOptionPane.ERROR_MESSAGE);
	} 
	}


}


class mysqlSSOD extends JFrame{
	
	
	private String cno;        //课程号
	
	
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
	super("课程成绩查询反馈系统");		//JFrame的标题名称
	this.setSize(660,600);		//控制窗体大小
	this.setLayout(null);		//自定义布局
	this.setLocation(400,100);	//点击运行以后，窗体在屏幕的位置
	this.scpDemo = new JScrollPane();
	this.bt1=new JButton("退出");
	
	this.btnShow = new JButton("显示数据");
	
	this.bt1.setBounds(100, 480, 100, 30);
	
	this.scpDemo.setBounds(10,50,580,400);	//设置滚动框大小
	this.btnShow.setBounds(10,10,120,30);	//设置按钮
	this.btnShow.addActionListener(new ActionListener()	//给“显示数据”按钮添加事件响应。
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
	


//判断是否符合规则
boolean JudgeRegister() throws SQLException, ClassNotFoundException {
    
    
    
    
    
    if(this.getCno().equals("")) {
        JOptionPane.showMessageDialog(null, "课程号不能为空！", "课程号为空", JOptionPane.ERROR_MESSAGE);
        return false;
    }
    
    
    
    //符合规则，弹出添加成功的窗口，并将数据添加至数据库
    
 
    //JOptionPane.showMessageDialog(null, "查找成功");
    
    return true;
}

//列数据
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
	// 将查询获得的记录数据，转换成适合生成JTable的数据形式
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
	
	
	
	
	
	
	// 创建JTable
	this.tabDemo = new JTable(info,title);
	// 显示表头
	this.jth = this.tabDemo.getTableHeader();
	// 将JTable加入到带滚动条的面板中
	this.scpDemo.getViewport().add(tabDemo);
	
	
	
	

	
	
	}
	catch(ClassNotFoundException cnfe)
	{
		cnfe.printStackTrace();
		JOptionPane.showMessageDialog(null,"数据源错误","错误",JOptionPane.ERROR_MESSAGE);
	}
	catch(SQLException sqle)
	{
		sqle.printStackTrace();
		JOptionPane.showMessageDialog(null,"数据操作错误","错误",JOptionPane.ERROR_MESSAGE);
	} 
	}


}

class mysqlSSOD2 extends JFrame{
	
	
	private String sno;        //课程号
	
	
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
	super("课程成绩查询反馈系统");		//JFrame的标题名称
	this.setSize(660,600);		//控制窗体大小
	this.setLayout(null);		//自定义布局
	this.setLocation(400,100);	//点击运行以后，窗体在屏幕的位置
	this.scpDemo = new JScrollPane();
	this.bt1=new JButton("退出");
	
	this.btnShow = new JButton("显示数据");
	
	this.bt1.setBounds(100, 480, 100, 30);
	
	this.scpDemo.setBounds(10,50,580,400);	//设置滚动框大小
	this.btnShow.setBounds(10,10,120,30);	//设置按钮
	this.btnShow.addActionListener(new ActionListener()	//给“显示数据”按钮添加事件响应。
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
	


//判断是否符合规则
boolean JudgeRegister() throws SQLException, ClassNotFoundException {
    
    
    
    
    
    if(this.getSno().equals("")) {
        JOptionPane.showMessageDialog(null, "个人学号不能为空！", "学号为空", JOptionPane.ERROR_MESSAGE);
        return false;
    }
    
    
    
    //符合规则，弹出添加成功的窗口，并将数据添加至数据库
    
 
    //JOptionPane.showMessageDialog(null, "查找成功");
    
    return true;
}

//列数据
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
	// 将查询获得的记录数据，转换成适合生成JTable的数据形式
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
	
	
	
	
	
	
	// 创建JTable
	this.tabDemo = new JTable(info,title);
	// 显示表头
	this.jth = this.tabDemo.getTableHeader();
	// 将JTable加入到带滚动条的面板中
	this.scpDemo.getViewport().add(tabDemo);
	
	
	
	

	
	
	}
	catch(ClassNotFoundException cnfe)
	{
		cnfe.printStackTrace();
		JOptionPane.showMessageDialog(null,"数据源错误","错误",JOptionPane.ERROR_MESSAGE);
	}
	catch(SQLException sqle)
	{
		sqle.printStackTrace();
		JOptionPane.showMessageDialog(null,"数据操作错误","错误",JOptionPane.ERROR_MESSAGE);
	} 
	}


}


class mysqlSU {
	
	private String sno;   //学号
	
	private String sage; //年龄
	
	



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


//判断是否符合规则
boolean JudgeRegister() throws SQLException, ClassNotFoundException {
    
    
    
    if(this.getSno().equals("")) {
        JOptionPane.showMessageDialog(null, "学号不能为空！", "学号为空", JOptionPane.ERROR_MESSAGE);
        return false;
    }
    
    
    
    if(this.getSage().equals("")) {
        JOptionPane.showMessageDialog(null, "年龄不能为空！", "年龄为空", JOptionPane.ERROR_MESSAGE);
        return false;
    }
    
    //符合规则，弹出添加成功的窗口，并将数据添加至数据库
    
    addAdmin();
    JOptionPane.showMessageDialog(null, "更新成功");
    return true;
}

//向数据库添加数据
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
		System.out.println("更新失败！");
	}
	
}

}


class mysqlCA{
	private String sno;        //学号
	private String cno;      //课程号
	private String cname;      //课程名
	private String sorce; //成绩
	
	



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


//判断是否符合规则
boolean JudgeRegister() throws SQLException, ClassNotFoundException {
    
    if(this.getSno().equals("")) {
        JOptionPane.showMessageDialog(null, "学号不能为空！", "学号为空", JOptionPane.ERROR_MESSAGE);
        return false;
    }
    
    if(this.getCno().equals("")) {
        JOptionPane.showMessageDialog(null, "课程号不能为空！", "课程号为空", JOptionPane.ERROR_MESSAGE);
        return false;
    }
    
    if(this.getCname().equals("")) {
        JOptionPane.showMessageDialog(null, "课程名不能为空！", "课程名为空", JOptionPane.ERROR_MESSAGE);
        return false;
    }
    
    if(this.getSorce().equals("")) {
        JOptionPane.showMessageDialog(null, "成绩不能为空！", "成绩为空", JOptionPane.ERROR_MESSAGE);
        return false;
    }
    
    //符合规则，弹出添加成功的窗口，并将数据添加至数据库
    
    addAdmin();
    JOptionPane.showMessageDialog(null, "添加成功");
    return true;
}

//向数据库添加数据
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
		System.out.println("添加失败！");
	}
	
}
}



class mysqlCU{
	private String sno;        //学号
	private String cno;      //课程号
	     
	private String sorce; //成绩
	
	



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


//判断是否符合规则
boolean JudgeRegister() throws SQLException, ClassNotFoundException {
    
    if(this.getSno().equals("")) {
        JOptionPane.showMessageDialog(null, "学号不能为空！", "学号为空", JOptionPane.ERROR_MESSAGE);
        return false;
    }
    
    if(this.getCno().equals("")) {
        JOptionPane.showMessageDialog(null, "课程号不能为空！", "课程号为空", JOptionPane.ERROR_MESSAGE);
        return false;
    }
    
    
    
    if(this.getSorce().equals("")) {
        JOptionPane.showMessageDialog(null, "成绩不能为空！", "成绩为空", JOptionPane.ERROR_MESSAGE);
        return false;
    }
    
    //符合规则，弹出添加成功的窗口，并将数据添加至数据库
    
    addAdmin();
    JOptionPane.showMessageDialog(null, "更新成功");
    return true;
}

//向数据库添加数据
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
		System.out.println("更新失败！");
	}
	
}
}



class mysqlAS{
	private String sno;        //学号
	private String cno;      //课程号
	     
	private String sorce; //成绩
	
	



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


//判断是否符合规则
boolean JudgeRegister() throws SQLException, ClassNotFoundException {
    
    if(this.getSno().equals("")) {
        JOptionPane.showMessageDialog(null, "学号不能为空！", "学号为空", JOptionPane.ERROR_MESSAGE);
        return false;
    }
    
    if(this.getCno().equals("")) {
        JOptionPane.showMessageDialog(null, "课程号不能为空！", "课程号为空", JOptionPane.ERROR_MESSAGE);
        return false;
    }
    
    
    
    if(this.getSorce().equals("")) {
        JOptionPane.showMessageDialog(null, "成绩不能为空！", "成绩为空", JOptionPane.ERROR_MESSAGE);
        return false;
    }
    
    //符合规则，弹出添加成功的窗口，并将数据添加至数据库
    
    addAdmin();
    JOptionPane.showMessageDialog(null, "录入成功");
    return true;
}

//向数据库添加数据
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
		System.out.println("录入失败！");
	}
	
}
}

class mysqlPU{
	private String account;        //账号
	
	private String password;      //密码
	private String confirmPassword; //第二次输入的密码
	
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


//判断是否符合规则
boolean JudgeRegister() throws SQLException, ClassNotFoundException {

   
        
       
        if(this.getAccount().equals("")) {
            JOptionPane.showMessageDialog(null, "账号不能为空！", "账号为空", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if(this.getPassword().equals("")) {
            JOptionPane.showMessageDialog(null, "密码不能为空！", "密码为空", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if(!this.getPassword().equals(this.getConfirmPassword())) {
            JOptionPane.showMessageDialog(null, "两次输入的密码不一致!", "密码不一致", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    //符合规则，弹出添加成功的窗口，并将数据添加至数据库
    
    addAdmin();
    JOptionPane.showMessageDialog(null, "修改成功");
    return true;
}

//向数据库添加数据
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
		System.out.println("修改失败！");
	}
	
}
}

class mysqlCD{
	private String sno;        //学号
	private String cno;      //课程号
	
	
	



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


//判断是否符合规则
boolean JudgeRegister() throws SQLException, ClassNotFoundException {
    
    if(this.getSno().equals("")) {
        JOptionPane.showMessageDialog(null, "学号不能为空！", "学号为空", JOptionPane.ERROR_MESSAGE);
        return false;
    }
    
    if(this.getCno().equals("")) {
        JOptionPane.showMessageDialog(null, "课程号不能为空！", "课程号为空", JOptionPane.ERROR_MESSAGE);
        return false;
    }
    
   
    //符合规则，弹出添加成功的窗口，并将数据添加至数据库
    
    addAdmin();
    JOptionPane.showMessageDialog(null, "删除成功");
    return true;
}

//向数据库添加数据
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
		System.out.println("删除失败！");
	}
	
}
}


class mysqlSD{
	private String sno;        //学号
	
	
	



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


//判断是否符合规则
boolean JudgeRegister() throws SQLException, ClassNotFoundException {
    
   
    
    if(this.getSno().equals("")) {
        JOptionPane.showMessageDialog(null, "学号不能为空！", "学号为空", JOptionPane.ERROR_MESSAGE);
        return false;
    }
    
    
    //符合规则，弹出添加成功的窗口，并将数据添加至数据库
    
    addAdmin();
    JOptionPane.showMessageDialog(null, "删除成功");
    return true;
}

//向数据库添加数据
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
		System.out.println("删除失败！");
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
		super("课程信息操作系统");		//JFrame的标题名称
		this.setSize(660,600);		//控制窗体大小
		this.setLayout(null);		//自定义布局
		this.setLocation(400,100);	//点击运行以后，窗体在屏幕的位置
		this.scpDemo = new JScrollPane();
		this.bt1=new JButton("增加");
		this.bt2=new JButton("删除");
		this.bt3=new JButton("更改");
		this.bt4=new JButton("查找");
		this.btnShow = new JButton("显示数据");
		
		this.bt1.setBounds(100, 480, 100, 30);
		this.bt2.setBounds(200, 480, 100, 30);
		this.bt3.setBounds(300, 480, 100, 30);
		this.bt4.setBounds(400, 480, 100, 30);
		this.scpDemo.setBounds(10,50,580,400);	//设置滚动框大小
		this.btnShow.setBounds(10,10,120,30);	//设置按钮
		this.btnShow.addActionListener(new ActionListener()	//给“显示数据”按钮添加事件响应。
		{
			public void actionPerformed(ActionEvent ae)
			{
				btnShow_ActionPerformed(ae);
			}
		});
		
		/********按钮“增加”的响应*******/
		
		this.bt1.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{	
				final JFrame frame = new JFrame("添加课程信息");
	            frame.setLayout(null);
	            //板块内容
	            JLabel txt = new JLabel("请输入要添加课程的课程信息:");
	            txt.setBounds(250, 100, 200, 25);
	            frame.add(txt);
	            
	            JLabel sno = new JLabel("学生学号:");
	            sno.setBounds(250, 150, 100, 25);
	            frame.add(sno);
	        
	            JLabel cno = new JLabel("课程号  :");
	            cno.setBounds(250, 200, 100, 25);
	            frame.add(cno);

	            JLabel cname = new JLabel("课程姓名:");
	            cname.setBounds(250, 250, 100, 25);
	            frame.add(cname);  
	               
	            JLabel sorce = new JLabel("课程成绩:");
	            sorce.setBounds(250, 300, 100, 30);
	            frame.add(sorce);
	            
	            //文本框何按钮
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
	            
	            
	            JButton button = new JButton("确认添加");
	            button.setBounds(350, 350, 100, 25);
	            frame.add(button);
	            


	            frame.setBounds(400, 100, 800, 640);
	            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	            frame.setVisible(true);
	            
	            //添加监听
	            button.addActionListener(new ActionListener() {
	                
	                public void actionPerformed(ActionEvent e) {
	                    
	                    String sno = sno2.getText();
	                    String cno = cno2.getText();
	                    String cname = cname2.getText();
	                    String sorce = sorce2.getText();
	                    
	                    
	                    //创建类
	                    mysqlCA mysqlCA = new mysqlCA();
	                    mysqlCA.setSno(sno);
	                    mysqlCA.setCno(cno);
	                    mysqlCA.setCname(cname);
	                    mysqlCA.setSorce(sorce);
	                    
	                    //如果成功，返回登录界面
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
		
		/******按钮 “删除”的响应*****/
		this.bt2.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				final JFrame frame = new JFrame("删除课程信息");
	            frame.setLayout(null);
	            // 板块内容
	    
	            JLabel txt1 = new JLabel("请输入要删除的课程信息:");
	            txt1.setBounds(250, 150, 200, 25);
	            frame.add(txt1);
	            
	            JLabel sno = new JLabel("学生学号:");
	            sno.setBounds(250, 200, 100, 25);
	            frame.add(sno);
	            
	            JLabel cno = new JLabel("课程号  :");
	            cno.setBounds(250, 250, 100, 25);
	            frame.add(cno);
	            

	           
	            
	            //文本框何按钮
	           
	            final JTextField sno2 = new JTextField();
	            sno2.setBounds(320, 200, 150, 25);
	            frame.add(sno2);
	            
	            final JTextField cno2 = new JTextField();
	            cno2.setBounds(320, 250, 150, 25);
	            frame.add(cno2);
	            
	            JButton button = new JButton("确认删除");
	            button.setBounds(350, 350, 100, 25);
	            frame.add(button);
	            


	            frame.setBounds(400, 100, 800, 640);
	            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	            frame.setVisible(true);
	            
	            //添加监听
	            
	            button.addActionListener(new ActionListener() {
	                
	                public void actionPerformed(ActionEvent e) {
	                    
	                    String sno = sno2.getText();
	                    String cno = cno2.getText();
	                    
	                    
	                    
	                    //创建类
	                    mysqlCD mysqlCD = new mysqlCD();
	                    mysqlCD.setSno(sno);
	                    mysqlCD.setCno(cno);
	                    
	                    
	                    //如果成功，返回界面
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
		
		
		/******按钮 “更改”的响应*****/
		this.bt3.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				final JFrame frame = new JFrame("更新课程信息");
	            frame.setLayout(null);
	            //板块内容
	          
	            JLabel txt1 = new JLabel("请输入要更新课程的信息:");
	            txt1.setBounds(250, 100, 200, 25);
	            frame.add(txt1);
	            
	            JLabel sno = new JLabel("学生学号:");
	            sno.setBounds(250, 150, 100, 25);
	            frame.add(sno);
	            
	            JLabel cno = new JLabel("课程号  :");
	            cno.setBounds(250, 200, 100, 25);
	            frame.add(cno);

	            JLabel txt2 = new JLabel("请输入要更新学生的课程成绩:");
	            txt2.setBounds(250, 250, 200, 25);
	            frame.add(txt2);
	               
	            JLabel sorce = new JLabel("课程成绩:");
	            sorce.setBounds(250, 300, 100, 30);
	            frame.add(sorce);
	            
	            //文本框何按钮
	           

	            final JTextField sno2 = new JTextField();
	            sno2.setBounds(320, 150, 150, 25);
	            frame.add(sno2);
	            
	            final JTextField cno2 = new JTextField();
	            cno2.setBounds(320, 200, 150, 25);
	            frame.add(cno2);
	            
	            
	            final JTextField sorce2 = new JTextField();
	            sorce2.setBounds(320, 300, 150, 25);
	            frame.add(sorce2);
	            
	            
	            JButton button = new JButton("确认更新");
	            button.setBounds(350, 350, 100, 25);
	            frame.add(button);
	            


	            frame.setBounds(400, 100, 800, 640);
	            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	            frame.setVisible(true);
	            
	            //添加监听
	            button.addActionListener(new ActionListener() {
	                
	                public void actionPerformed(ActionEvent e) {
	                    
	                    String sno = sno2.getText();
	                    String cno = cno2.getText();
	                    
	                    String sorce = sorce2.getText();
	                    
	                    
	                    //创建类
	                    mysqlCU mysqlCU = new mysqlCU();
	                    mysqlCU.setSno(sno);
	                    mysqlCU.setCno(cno);
	                  
	                    mysqlCU.setSorce(sorce);
	                    
	                    //如果成功，返回界面
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
		
		
		/******按钮 “查找”的响应*****/
		this.bt4.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				final JFrame frame = new JFrame("查找课程信息");
	            frame.setLayout(null);
	            // 板块内容
	            JLabel txt1 = new JLabel("输入查找成绩的课程信息：");
	            txt1.setBounds(250, 100, 200, 25);
	            frame.add(txt1);
	            
	            JLabel sno = new JLabel("学生学号:");
	            sno.setBounds(250, 150, 100, 25);
	            frame.add(sno);
	            
	            JLabel cno = new JLabel("课程号  :");
	            cno.setBounds(250, 200, 100, 25);
	            frame.add(cno);

	           
	            
	            //文本框何按钮
	           
	            final JTextField sno2 = new JTextField();
	            sno2.setBounds(320, 150, 150, 25);
	            frame.add(sno2);
	            
	            final JTextField cno2 = new JTextField();
	            cno2.setBounds(320, 200, 150, 25);
	            frame.add(cno2);
	         
	            
	            JButton button = new JButton("确认查找");
	            button.setBounds(350, 350, 100, 25);
	            frame.add(button);
	            


	            frame.setBounds(400, 100, 800, 640);
	            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	            frame.setVisible(true);
	            
	            //添加监听
	            button.addActionListener(new ActionListener() {
	                
	                public void actionPerformed(ActionEvent e) {
	                   
	                    String sno = sno2.getText();
	                    String cno = cno2.getText();
	                   
	                    
	                    
	                    //创建类
	                    mysqlCS mysqlCS = new mysqlCS();
	                    
	                    mysqlCS.setSno(sno);
	                    mysqlCS.setCno(cno);
	                  
	                    
	                    //如果成功，返回界面
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
		
		/******* 将组件加入到窗体中******/
		add(this.scpDemo);
		add(this.btnShow);
		add(this.bt1);
		add(this.bt2);
		add(this.bt3);
		add(this.bt4);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		}
	/***连接数据库并显示到表格中***/
	
	
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
	// 将查询获得的记录数据，转换成适合生成JTable的数据形式
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
	// 创建JTable
	this.tabDemo = new JTable(info,title);
	// 显示表头
	this.jth = this.tabDemo.getTableHeader();
	// 将JTable加入到带滚动条的面板中
	this.scpDemo.getViewport().add(tabDemo); 
	}
	catch(ClassNotFoundException cnfe)
	{
		cnfe.printStackTrace();
		JOptionPane.showMessageDialog(null,"数据源错误","错误",JOptionPane.ERROR_MESSAGE);
	}
	catch(SQLException sqle)
	{
		sqle.printStackTrace();
		JOptionPane.showMessageDialog(null,"数据操作错误","错误",JOptionPane.ERROR_MESSAGE);
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
		super("学生信息操作系统");		//JFrame的标题名称
		this.setSize(660,600);		//控制窗体大小
		this.setLayout(null);		//自定义布局
		this.setLocation(400,100);	//点击运行以后，窗体在屏幕的位置
		this.scpDemo = new JScrollPane();
		this.bt1=new JButton("增加");
		this.bt2=new JButton("删除");
		this.bt3=new JButton("更改");
		this.bt4=new JButton("查找");
		this.btnShow = new JButton("显示数据");
		
		this.bt1.setBounds(100, 480, 100, 30);
		this.bt2.setBounds(200, 480, 100, 30);
		this.bt3.setBounds(300, 480, 100, 30);
		this.bt4.setBounds(400, 480, 100, 30);
		this.scpDemo.setBounds(10,50,580,400);	//设置滚动框大小
		this.btnShow.setBounds(10,10,120,30);	//设置按钮
		
		this.btnShow.addActionListener(new ActionListener()	//给“显示数据”按钮添加事件响应。
		{
			public void actionPerformed(ActionEvent ae)
			{
				btnShow_ActionPerformed(ae);
			}
		});
		
		/********按钮“增加”的响应*******/
		
		this.bt1.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{	
				final JFrame frame = new JFrame("添加学生信息");
	            frame.setLayout(null);
	            //板块内容
	            JLabel txt = new JLabel("请输入要添加学生的学生信息:");
	            txt.setBounds(250, 100, 200, 25);
	            frame.add(txt);
	            
	            JLabel sname = new JLabel("学生姓名:");
	            sname.setBounds(250, 150, 100, 25);
	            frame.add(sname);
	        
	            JLabel sno = new JLabel("学生学号:");
	            sno.setBounds(250, 200, 100, 25);
	            frame.add(sno);

	            JLabel sex = new JLabel("学生性别:");
	            sex.setBounds(250, 250, 100, 25);
	            frame.add(sex);  
	               
	            JLabel sage = new JLabel("学生年龄:");
	            sage.setBounds(250, 300, 100, 30);
	            frame.add(sage);
	            
	            //文本框何按钮
	            final JTextField sname2 = new JTextField();
	            sname2.setBounds(320, 150, 150, 25);
	            frame.add(sname2);

	            final JTextField sno2 = new JTextField();
	            sno2.setBounds(320, 200, 150, 25);
	            frame.add(sno2);
	            
	            String[] listData = new String[] { "女", "男" };
	    		final JComboBox sex2 = new JComboBox(listData);
	    		sex2.setBounds(320, 250, 150, 25);
	    		frame.add(sex2);
	            
	            
	           /* final JTextField sex2 = new JTextField();
	            sex2.setBounds(320, 250, 150, 25);
	            frame.add(sex2);*/
	            
	            final JTextField sage2 = new JTextField();
	            sage2.setBounds(320, 300, 150, 25);
	            frame.add(sage2);
	            
	            
	            JButton button = new JButton("确认添加");
	            button.setBounds(350, 350, 100, 25);
	            frame.add(button);
	            


	            frame.setBounds(400, 100, 800, 640);
	            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	            frame.setVisible(true);
	            
	            //添加监听
	            button.addActionListener(new ActionListener() {
	                
	                public void actionPerformed(ActionEvent e) {
	                    String sex = (String)sex2.getSelectedItem();
	                    String sname = sname2.getText();
	                    String sno = sno2.getText();
	                    String sage = sage2.getText();
	                    
	                    
	                    //创建类
	                    mysqlSA mysqlSA = new mysqlSA();
	                    mysqlSA.setSex(sex);
	                    mysqlSA.setSname(sname);
	                    mysqlSA.setSno(sno);
	                    mysqlSA.setSage(sage);
	                    
	                    //如果成功，返回界面
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
		/******按钮 “删除”的响应*****/
		this.bt2.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				final JFrame frame = new JFrame("删除学生信息");
	            frame.setLayout(null);
	            // 板块内容
	    
	            JLabel txt1 = new JLabel("请输入要删除学生的学生学号:");
	            txt1.setBounds(250, 150, 200, 25);
	            frame.add(txt1);
	            
	            JLabel sno = new JLabel("学生学号:");
	            sno.setBounds(250, 200, 100, 25);
	            frame.add(sno);

	           
	            
	            //文本框何按钮
	           
	            final JTextField sno2 = new JTextField();
	            sno2.setBounds(320, 200, 150, 25);
	            frame.add(sno2);
	         
	            
	            JButton button = new JButton("确认删除");
	            button.setBounds(350, 350, 100, 25);
	            frame.add(button);
	            


	            frame.setBounds(400, 100, 800, 640);
	            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	            frame.setVisible(true);
	            
	            //添加监听
	            button.addActionListener(new ActionListener() {
	                
	                public void actionPerformed(ActionEvent e) {
	                   
	                    String sno = sno2.getText();
	                   
	                    
	                    
	                    //创建类
	                    mysqlSD mysqlSD = new mysqlSD();
	                  
	                    mysqlSD.setSno(sno);
	                  
	                    
	                    //如果成功，返回界面
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
		
		/******按钮 “更改”的响应*****/
		this.bt3.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				final JFrame frame = new JFrame("更新学生信息");
	            frame.setLayout(null);
	            //板块内容
	          
	            JLabel txt1 = new JLabel("请输入要更新学生的学生学号:");
	            txt1.setBounds(250, 150, 200, 25);
	            frame.add(txt1);
	            
	            JLabel sno = new JLabel("学生学号:");
	            sno.setBounds(250, 200, 100, 25);
	            frame.add(sno);

	            JLabel txt2 = new JLabel("请输入要更新学生的学生年龄:");
	            txt2.setBounds(250, 250, 200, 25);
	            frame.add(txt2);
	               
	            JLabel sage = new JLabel("学生年龄:");
	            sage.setBounds(250, 300, 100, 30);
	            frame.add(sage);
	            
	            //文本框何按钮
	           

	            final JTextField sno2 = new JTextField();
	            sno2.setBounds(320, 200, 150, 25);
	            frame.add(sno2);
	            
	            
	            
	            final JTextField sage2 = new JTextField();
	            sage2.setBounds(320, 300, 150, 25);
	            frame.add(sage2);
	            
	            
	            JButton button = new JButton("确认更新");
	            button.setBounds(350, 350, 100, 25);
	            frame.add(button);
	            


	            frame.setBounds(400, 100, 800, 640);
	            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	            frame.setVisible(true);
	            
	            //添加监听
	            button.addActionListener(new ActionListener() {
	                
	                public void actionPerformed(ActionEvent e) {
	                  
	                    String sno = sno2.getText();
	                    String sage = sage2.getText();
	                    
	                    
	                    //创建类
	                    mysqlSU mysqlSU = new mysqlSU();
	                  
	                    mysqlSU.setSno(sno);
	                    mysqlSU.setSage(sage);
	                    
	                    //如果成功，返回界面
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
		/******按钮 “查找”的响应*****/
		this.bt4.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				final JFrame frame = new JFrame("输入查找学生的学号");
	            frame.setLayout(null);
	            // 板块内容
	    
	            JLabel sno = new JLabel("学生学号:");
	            sno.setBounds(250, 200, 100, 25);
	            frame.add(sno);

	           
	            
	            //文本框何按钮
	           
	            final JTextField sno2 = new JTextField();
	            sno2.setBounds(320, 200, 150, 25);
	            frame.add(sno2);
	         
	            
	            JButton button = new JButton("确认查找");
	            button.setBounds(350, 350, 100, 25);
	            frame.add(button);
	            


	            frame.setBounds(400, 100, 800, 640);
	            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	            frame.setVisible(true);
	            
	            //添加监听
	            button.addActionListener(new ActionListener() {
	                
	                public void actionPerformed(ActionEvent e) {
	                   
	                    String sno = sno2.getText();
	                   
	                    
	                    
	                    //创建类
	                    mysqlSS mysqlSS = new mysqlSS();
	                    
	                    mysqlSS.setSno(sno);
	                  
	                    
	                    //如果成功，返回界面
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
		
		/******* 将组件加入到窗体中******/
		add(this.scpDemo);
		add(this.btnShow);
		add(this.bt1);
		add(this.bt2);
		add(this.bt3);
		add(this.bt4);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		}
	/***连接数据库并显示到表格中***/
	
	
	
	
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
	// 将查询获得的记录数据，转换成适合生成JTable的数据形式
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
	// 创建JTable
	this.tabDemo = new JTable(info,title);
	// 显示表头
	this.jth = this.tabDemo.getTableHeader();
	// 将JTable加入到带滚动条的面板中
	this.scpDemo.getViewport().add(tabDemo); 
	}
	catch(ClassNotFoundException cnfe)
	{
		cnfe.printStackTrace();
		JOptionPane.showMessageDialog(null,"数据源错误","错误",JOptionPane.ERROR_MESSAGE);
	}
	catch(SQLException sqle)
	{
		sqle.printStackTrace();
		JOptionPane.showMessageDialog(null,"数据操作错误","错误",JOptionPane.ERROR_MESSAGE);
	} 
	}
	
}
 


public class test extends JFrame{    
	
	
	test() {
	init();
	}
	//登录界面初始化
	public void init() {
	final JFrame frame = new JFrame("学生信息管理系统");
        frame.setLayout(null);
        
        JLabel label = new JLabel("请输入账号密码登录学生信息管理系统：");
		label.setBounds(100,40,300,50);
		frame.add(label);
        
		JLabel account = new JLabel("账号");
		account.setBounds(100,100,100,50);
		frame.add(account);
        
        
        JLabel password = new JLabel("密码");
		password.setBounds(100,200,100,50);
		frame.add(password);
		
		JLabel identity = new JLabel("身份");
		identity.setBounds(100,300,100,50);
		frame.add(identity);
		
		//文本框和按钮
		
		
		final JTextField account2 = new JTextField();
		account2.setBounds(150, 100, 300, 50);
		frame.add(account2);
		
		final JPasswordField password2 = new JPasswordField();
		password2.setBounds(150, 200, 300, 50);
		frame.add(password2);
		
		String[] listData = new String[] { "学生", "老师", "管理员" };
		final JComboBox identity2 = new JComboBox(listData);
		identity2.setBounds(150, 300, 300, 50);
		frame.add(identity2);
		
		
		JButton Blogin = new JButton("登陆");
		Blogin.setBounds(100, 450, 100, 50);
		frame.add(Blogin);
        
		JButton Blogon = new JButton("注册");
		Blogon.setBounds(350, 450, 100, 50);
		frame.add(Blogon);

        frame.setBounds(400, 100, 800, 640);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
        //为登录按钮添加监听器
        Blogin.addActionListener(
        	new ActionListener() {  
            public void actionPerformed(ActionEvent e) {
                String Account = account2.getText();
                String Password = new String (password2.getPassword());
                String Identity = (String)identity2.getSelectedItem();
                  
                //创建一个Admin用户，把输入框中的用户名密码和提出来
                Admin admin = new Admin();
                admin.setAccount(Account);
                admin.setPassword(Password);
                admin.setIdentity(Identity);
                
                //登录
                Login login = new Login();
                login.setAdmin(admin);
          
                if(login.JudgeAdmin()==0) {
                	//弹出账号或密码错误的窗口
                	JOptionPane.showMessageDialog(null, "账号或密码或身份错误", "账号或密码或身份错误", JOptionPane.WARNING_MESSAGE);
                	//清除密码框中的信息
                	password2.setText("");
                	//清除账号框中的信息
                	account2.setText("");
                	
                	//System.out.println("登陆失败");
                } else {
                	//弹出登录成功的窗口
                	JOptionPane.showMessageDialog(null, "登陆成功", "登陆成功", JOptionPane.NO_OPTION);
                	//点击确定后会跳转到主窗口
                	frame.setVisible(false);
                	
                	//System.out.println(identity2.getSelectedItem());
               	
                	
                	
	               	if ((String)identity2.getSelectedItem() == "管理员"){
	               		masterSystem ms = new masterSystem();
	               	}else if((String)identity2.getSelectedItem() == "学生"){
	               		studentSystem ss = new studentSystem();
	               	}else if((String)identity2.getSelectedItem() == "老师"){
	               		teacherSystem ts = new teacherSystem();
	               	}
                	//AdminRegister ar = new AdminRegister(); 
                        
                }
               
            }
        });
         
         //为注册按钮添加监听器 
        Blogon.addActionListener(new ActionListener() {
        	 public void actionPerformed(ActionEvent e) {
        		 //注册页面
                 frame.setVisible(false);
        		 Register ar = new Register(); 
        	 }
         });
	}
	
    public static void main(String []args) { 
       //主程序
       //登录窗口
    	test t = new test();
    }
}


