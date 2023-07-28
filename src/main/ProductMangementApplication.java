package main;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import frame.ProductCategoryRegisterFrame;
import frame.ProductColorRegisterFrame;
import frame.ProductRegisterFrame;
import frame.ProductSearchFrame;

public class ProductMangementApplication extends JFrame {

	private JPanel contentPane;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProductMangementApplication frame = new ProductMangementApplication();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ProductMangementApplication() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 295);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton productRegisterFrameOpenButton = new JButton("상품등록");
		productRegisterFrameOpenButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ProductRegisterFrame productRegisterFrame = new ProductRegisterFrame();
				productRegisterFrame.setVisible(true); // 프레임창 하나 더 띄우는
			}
		});
		productRegisterFrameOpenButton.setBounds(12, 10, 410, 32);
		contentPane.add(productRegisterFrameOpenButton);
		
		JButton productSearchFrameOpenButton = new JButton("상품조회");
		productSearchFrameOpenButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ProductSearchFrame productSearchFrame =
						ProductSearchFrame.getInstance();
				productSearchFrame.setVisible(true);
			}
		});
		productSearchFrameOpenButton.setBounds(12, 50, 410, 32);
		contentPane.add(productSearchFrameOpenButton);
		
		JButton productColorRegisterFrameOpenButton = new JButton("상품색상등록");
		productColorRegisterFrameOpenButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ProductColorRegisterFrame productRegisterFrame =
						new ProductColorRegisterFrame();
				productRegisterFrame.setVisible(true);
			}
		});
		productColorRegisterFrameOpenButton.setBounds(12, 92, 410, 32);
		contentPane.add(productColorRegisterFrameOpenButton);
		
		JButton productCategoryRegisterFrameOpenButton = new JButton("상품카테고리등록");
		productCategoryRegisterFrameOpenButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ProductCategoryRegisterFrame productCategoryRegisterFrame =
						new ProductCategoryRegisterFrame();
				productCategoryRegisterFrame.setVisible(true);
			}
		});
		productCategoryRegisterFrameOpenButton.setBounds(12, 134, 410, 32);
		contentPane.add(productCategoryRegisterFrameOpenButton);
	}
}
