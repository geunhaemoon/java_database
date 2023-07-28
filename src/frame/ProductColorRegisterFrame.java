package frame;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import entitiy.ProductColor;
import service.ProductColorService;
import utils.CustomSwingTextUtil;

public class ProductColorRegisterFrame extends JFrame {

	private JPanel contentPane;
	private JTextField productColorNameTextField;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProductColorRegisterFrame frame = new ProductColorRegisterFrame();
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
	
	public ProductColorRegisterFrame() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel tittleLabel = new JLabel("상품 색상 등록");
		tittleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		tittleLabel.setBounds(12, 10, 410, 27);
		contentPane.add(tittleLabel);
		
		JLabel productColorNameLabel = new JLabel("색상명");
		productColorNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		productColorNameLabel.setBounds(12, 47, 57, 15);
		contentPane.add(productColorNameLabel);
		
		productColorNameTextField = new JTextField();
		productColorNameTextField.setBounds(65, 41, 357, 21);
		contentPane.add(productColorNameTextField);
		
		
		
		JButton registerSubmitButton = new JButton("등록하기");
		// 객체
		registerSubmitButton.addMouseListener(new MouseAdapter() {
			
			//중복체크 이런 기능 만들기 
			@Override
			public void mouseClicked(MouseEvent e) {
				// textfield에 적힌 값 들고옴 문자열로 저장
				String productColorName = productColorNameTextField.getText();
				
				if(CustomSwingTextUtil.isTextEmpty(contentPane,productColorName)) {return;}
				if(ProductColorService.getInstance().isProductColorNameDuplicated(productColorName)) {
					JOptionPane.showMessageDialog(contentPane, "이미 존재하는 색상명입니다.", "중복오류", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				//등록할 색상 생성 
				ProductColor productColor = ProductColor.builder()
						.productColorName(productColorName)
						.build();
				
				// ! 실패했을때 
				if(!ProductColorService.getInstance().registerProductColor(productColor)) {
					JOptionPane.showMessageDialog(contentPane, "색상등록 중 오류가 발생하였습니다", "등록오류", JOptionPane.ERROR_MESSAGE);
					return;
				//성공했을때	
				}JOptionPane.showMessageDialog(contentPane, "새로운 색상을 등록하였습니다.", "등록성공", JOptionPane.PLAIN_MESSAGE);
				CustomSwingTextUtil.clearTextField(productColorNameTextField);
			}
		});
		
		registerSubmitButton.setBounds(12, 72, 410, 78);
		contentPane.add(registerSubmitButton);
	}

	
}

