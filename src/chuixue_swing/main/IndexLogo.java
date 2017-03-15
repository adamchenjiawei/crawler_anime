package chuixue_swing.main;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.FileDialog;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import org.apache.commons.lang3.StringUtils;

import chuixue_swing.model.Anime;
import chuixue_swing.util.Search_Anime;

public class IndexLogo extends JFrame{
	private static int Width = 800;
	private static int Height = 600;
	private JPanel upPanel, centerPanel, downPanel, ldPanel, rdPanel;
	private JTable jTable;
	private DefaultTableModel defaultModel = null;
	Anime anime = null;
	Container c = getContentPane();
	
	public IndexLogo(){
		init();
		c.setLayout(new BoxLayout(c, BoxLayout.Y_AXIS));
		c.add(upPanel);
		c.add(centerPanel);
		c.add(downPanel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, Width, Height);
		setVisible(true);
	}

	void init() {
		upPanel = new JPanel();
		
		upPanel.setLayout(new GridLayout(1, 3));
		JLabel label = new JLabel("搜索内容:");
		upPanel.add(label);
		
		final TextField textField = new TextField();
		upPanel.add(textField);
		
		JButton jButton = new JButton("搜索");
		upPanel.add(jButton);
		
		
		
		centerPanel = new JPanel();
		String[] columnNames = {"标题", "漫画路径"};
		
		Object[][] cellData = {{
				null!=anime?anime.getAnime_name():"",
						null!=anime?anime.getAddress():""
		}};
		defaultModel = new DefaultTableModel(cellData, columnNames);
		jTable = new JTable(defaultModel);
		
		centerPanel.setLayout(new BorderLayout());
		centerPanel.add(jTable.getTableHeader(), BorderLayout.PAGE_START);
		centerPanel.add(jTable, BorderLayout.CENTER);//在使用时要注意！
		
		jButton.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				String anime_name = textField.getText();
				List<Anime> animes = Search_Anime.getInstanse().getAnimeInfo(anime_name);
				defaultModel.setRowCount(0);
				Object[] data = null;
				for(Anime anime : animes){
					data = new Object[]{anime.getAnime_name(),anime.getAddress()};
					defaultModel.addRow(data);
				}
				jTable.revalidate();
			}
		});
		
		jTable.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				FileDialog fileDialog = new FileDialog(new JFrame(),"保存文件",FileDialog.SAVE);
				fileDialog.setVisible(true);
				String s = fileDialog.getDirectory();
				//TODO
				
				String anime_url = (String) defaultModel.getValueAt(jTable.getSelectedRow(), 1);
				
			}
		});
		
		downPanel = new JPanel();
		ldPanel = new JPanel();
		ldPanel.setBorder(new TitledBorder("左下"));
		rdPanel = new JPanel();
		rdPanel.setBorder(new TitledBorder("右下"));
		downPanel.setLayout(new GridLayout(1, 2));
		downPanel.add(ldPanel);
		downPanel.add(rdPanel);
	}

	public static void main(String[] args) {
		new IndexLogo();
	}
}
