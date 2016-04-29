package formatFiddle;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileSystemView;
import javax.swing.plaf.FileChooserUI;

import java.awt.Choice;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.awt.Button;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import java.awt.TextField;

public class MainFormatFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFormatFrame frame = new MainFormatFrame();
					frame.setTitle("空格变:小程序              2016.04.29  by: yupeng");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFormatFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 165);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JFileChooser jfc=new JFileChooser();  
		jfc.setBounds(37, 36, 244, 39);
        jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );
        FileSystemView fsv = FileSystemView.getFileSystemView();
        jfc.setCurrentDirectory(fsv.getHomeDirectory());
		
        TextField textField = new TextField();
		textField.setBounds(32, 36, 259, 23);
		contentPane.add(textField);
		
		Button button = new Button("选择");
		button.setBounds(311, 36, 76, 23);
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				contentPane.add(jfc);
				jfc.showDialog(new JLabel(), "选择");
				File file=jfc.getSelectedFile();
				if(null == file){
					return;
				}
				
				textField.setText(file.getAbsolutePath()); 
				
			}
		});
        
		contentPane.add(button);
		
		Button okBtn = new Button("确定");
		okBtn.setBounds(164, 85, 76, 23);
		contentPane.add(okBtn);
		okBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(textField.getText().equals("") || textField.getText().length() <= 0){
					return;
				}
				String result = readFileByLines(textField.getText());
//				System.out.println(result);
				saveCache(textField.getText(), result);
				System.exit(0);
			}
		});
		
		
		
		
	}
	
	
	public static String readFileByLines(String fileName) {
		String resultStr = "";
        File file = new File(fileName);  
        BufferedReader reader = null;  
        try {  
            reader = new BufferedReader(new FileReader(file));  
            String tempString = null;  
            int line = 1;
            StringBuilder sbBuilder = new StringBuilder();
            sbBuilder.append("\n").append("=====================").append("\n\n");
            // 一次读入一行，直到读入null为文件结束  
            while ((tempString = reader.readLine()) != null) {
            	if("".equals(tempString) || tempString.length() <= 0){
            		continue;
            	}
            	String[] strs = tempString.split("\\s+");
            	if(strs.length == 2){
            		sbBuilder.append(strs[0] + ":" + strs[1] + "\n");
            	}else if(strs.length == 1){
            		sbBuilder.append(strs[0] + ":" + "" + "\n");
            	}
            	
            }  
            resultStr = sbBuilder.toString();
            reader.close();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            if (reader != null) {  
                try {  
                    reader.close();  
                } catch (IOException e1) {  
                }  
            }  
        }
        return resultStr;
    }  
	
	
	
	private void saveCache(String fileName,String obj){
		FileOutputStream fos = null;
		OutputStreamWriter osw = null;
		File file = new File(fileName);
		try {
			fos = new FileOutputStream(file,true);
			osw = new OutputStreamWriter(fos, "UTF-8"); 
			osw.write(obj);
			osw.flush();
			osw.close();
			fos.close();
		} catch (Exception e) {
			
		} finally{
			try {
				if(osw != null) osw.close();
				if(fos != null) fos.close();
			} catch (IOException e) {
				
			}
			
		}
	}
}
