package frame;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import entitiy.Product;
import entitiy.ProductCategory;
import entitiy.ProductColor;
import service.ProductCategoryService;
import service.ProductColorService;
import service.ProductService;
import utils.CustomSwingComboBoxUtil;
import utils.CustomSwingTextUtil;

public class ProductModifyFrame extends JFrame {

	private JPanel contentPane;
	private JTextField productPriceTextField;
	private JTextField productNameTextField;
	private JTextField productIdTextField; 
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProductModifyFrame frame = new ProductModifyFrame(1);
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
	public ProductModifyFrame(int productId) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 410);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel tittleLabel = new JLabel("상품 수정");
		tittleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		tittleLabel.setBounds(12, 10, 410, 27);
		contentPane.add(tittleLabel);
		
		JLabel productIdLabel = new JLabel("상품번호");
		productIdLabel.setHorizontalAlignment(SwingConstants.CENTER);
		productIdLabel.setBounds(12, 121, 57, 15);
		contentPane.add(productIdLabel);
		
		productIdTextField = new JTextField();
		productIdTextField.setColumns(10);
		productIdTextField.setBounds(65, 115, 357, 21);
		productIdTextField.setEnabled(false);
		contentPane.add(productIdTextField);
		
		JLabel productNameLabel = new JLabel("상품명");
		productNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		productNameLabel.setBounds(12, 157, 57, 15);
		contentPane.add(productNameLabel);
		
		productNameTextField = new JTextField();
		productNameTextField.setBounds(65, 151, 357, 21);
		contentPane.add(productNameTextField);
		
		JLabel productPriceLabel = new JLabel("가격");
		productPriceLabel.setHorizontalAlignment(SwingConstants.CENTER);
		productPriceLabel.setBounds(12, 188, 57, 15);
		contentPane.add(productPriceLabel);
		
		productPriceTextField = new JTextField();
		productPriceTextField.setBounds(65, 182, 357, 21);
		contentPane.add(productPriceTextField);
		
		
		JLabel productColorLabel = new JLabel("색상");
		productColorLabel.setHorizontalAlignment(SwingConstants.CENTER);
		productColorLabel.setBounds(12, 219, 57, 15);
		contentPane.add(productColorLabel);
		
		
		JComboBox colorComboBox = new JComboBox();
		CustomSwingComboBoxUtil.setComboBoxModel(colorComboBox, ProductColorService.getInstance().getProductColorNameList());
		colorComboBox.setBounds(65, 213, 357, 23);
		contentPane.add(colorComboBox);
		
		JLabel productCategoryLabel = new JLabel("카테고리");
		productCategoryLabel.setHorizontalAlignment(SwingConstants.CENTER);
		productCategoryLabel.setBounds(12, 250, 57, 15);
		contentPane.add(productCategoryLabel);
		
		JComboBox categoryComboBox = new JComboBox();
		CustomSwingComboBoxUtil.setComboBoxModel(categoryComboBox, ProductCategoryService.getInstance().getProductCategoryNameList());
		categoryComboBox.setBounds(65, 244, 357, 23);
		contentPane.add(categoryComboBox);
		
		JButton modifySubmitButton = new JButton("수정하기");
		modifySubmitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String productName = productNameTextField.getText();
				if(CustomSwingTextUtil.isTextEmpty(contentPane, productName)){return;}
				
				String productPrice = productPriceTextField.getText();
				if(CustomSwingTextUtil.isTextEmpty(contentPane, productPrice)){return;}

				String productColorName = (String) colorComboBox.getSelectedItem();
				if(CustomSwingTextUtil.isTextEmpty(contentPane, productColorName)){return;}

				String productCategoryName = (String) categoryComboBox.getSelectedItem();
				if(CustomSwingTextUtil.isTextEmpty(contentPane, productCategoryName)){return;}
				
				
				//이게 true 면 중복
				if (ProductService.getInstance().isProductNameDuplicated(productName)) {
					JOptionPane.showMessageDialog(contentPane, "이미 존재하는 상품명입니다.", "중복오류", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				
				Product product = Product.builder()
						.productId(productId)
						.productName(productName)
						.productPrice(Integer.parseInt(productPrice))
						.productColor(ProductColor.builder().productColorName(productColorName).build())
						.productCategory(ProductCategory.builder().productCategoryName(productCategoryName).build())
						.build();
			
				if(!ProductService.getInstance().registerProduct(product)) {
					JOptionPane.showMessageDialog(contentPane, "상품 수정 중 오류가 발생하였습니다.", "수정 오류", JOptionPane.ERROR_MESSAGE);
					return;
				}
				JOptionPane.showMessageDialog(contentPane, "상품을 수정하였습니다.", "수정성공", JOptionPane.PLAIN_MESSAGE);
				ProductSearchFrame.getInstance().setSearchProductTableModel();
				dispose();
				
			}
		});
		modifySubmitButton.setBounds(12, 283, 410, 78);
		contentPane.add(modifySubmitButton);
		
				
		Product product = ProductService.getInstance().getProductByProductId(productId);

		if (product != null) {
			productIdTextField.setText(Integer.toString(product.getProductId()));
			productNameTextField.setText(product.getProductName());
			productPriceTextField.setText(Integer.toString(product.getProductPrice()));
			colorComboBox.setSelectedItem(product.getProductColor().getProductColorName());
			categoryComboBox.setSelectedItem(product.getProductCategory().getProductCategoryName());
		}
	}
}
