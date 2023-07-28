
package frame;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import entitiy.Product;
import service.ProductService;
import utils.CustomSwingTableUtil;

public class ProductSearchFrame extends JFrame {

	private JPanel contentPane;
	private JTextField searchTextField;
	private JTable productTable;
	private JComboBox comboBox;
	private DefaultTableModel searchProductTableModel;
	private JButton productModifyButton;
	private JButton productRemoveButton;
	
	private static ProductSearchFrame instance;
	
	// instance 만들어서 메인 다 지움 왜 인스턴스...? 왜? ..??  
	
	/**
	 * Create the frame.
	 */
	private ProductSearchFrame() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1000, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel titleLabel = new JLabel("상품 조회");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setBounds(12, 5, 85, 30);
		contentPane.add(titleLabel);

		productModifyButton = new JButton("수정");
		productModifyButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!productModifyButton.isEnabled()) {return;}
				
				int productId = Integer.parseInt((String)searchProductTableModel.getValueAt(productTable.getSelectedRow(), 0));
				System.out.println(productId);
				ProductModifyFrame productModifyFrame =
						new ProductModifyFrame(productId);
				productModifyFrame.setVisible(true);
				
			}
		});
		
		productModifyButton.setBounds(764, 5, 95, 45);
		contentPane.add(productModifyButton);
		
		productRemoveButton = new JButton("삭제");
		productRemoveButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if (!productModifyButton.isEnabled()) {return;}
				int productId = Integer.parseInt((String) searchProductTableModel.getValueAt(productTable.getSelectedRow(), 0));
				if (ProductService.getInstance().removeProduct(productId)) {
					JOptionPane.showMessageDialog(contentPane, "상품 삭제 중 오류가 발생하였습니다", "삭제 오류", JOptionPane.ERROR_MESSAGE);
					return;
				}
				JOptionPane.showMessageDialog(contentPane, "선택한 상품을 삭제하였습니다.", "삭제성공" , JOptionPane.PLAIN_MESSAGE);
				setSearchProductTableModel();
				
 			}
		});
		productRemoveButton.setBounds(873, 5, 95, 45);
		contentPane.add(productRemoveButton);
		
		
		JLabel searchLabel = new JLabel("검색");
		searchLabel.setHorizontalAlignment(SwingConstants.CENTER);
		searchLabel.setBounds(425, 65, 75, 30);
		contentPane.add(searchLabel);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"전체", "상품명", "색상", "카테고리"}));
		comboBox.setBounds(512, 65, 170, 30);
		contentPane.add(comboBox);
		
		searchTextField = new JTextField();
		searchTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					setSearchProductTableModel();
//					String searchOption = (String)comboBox.getSelectedItem();
//					String searchValue = searchTextField.getText();
					
//					List<Product> searchProductList = ProductService.getInstance().searchProduct(searchOption, searchValue);
//					String[][] searchProductModelArray = CustomSwingTableUtil.searchProductListToArray(searchProductList);
//					
//					producttable.setModel(new DefaultTableModel(
//							searchProductModelArray,
//							new String[] {
//								"product_id", "product_name", "product_price", "product_color_id", "product_color_name", "product_category_id", "product_category_name"
//							}
//						));
					

				}
			}
		});
		searchTextField.setBounds(700, 65, 270, 30);
		contentPane.add(searchTextField);
		searchTextField.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 106, 960, 345);
		contentPane.add(scrollPane);
		
		productTable = new JTable();
		setSearchProductTableModel();
		productTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				productRemoveButton.setEnabled(true);
				productModifyButton.setEnabled(true);
				
			}
		});
			
		scrollPane.setViewportView(productTable);
	}
	
	public static ProductSearchFrame getInstance() {
		if (instance == null) {
			instance = new ProductSearchFrame();
		}
		return instance;
	}
	
	public void setSearchProductTableModel() {
		String searchOption = (String)comboBox.getSelectedItem();
		String searchValue = searchTextField.getText();
		
		List<Product> searchProductList = ProductService.getInstance().searchProduct(searchOption, searchValue);
		String[][] searchProductModelArray = CustomSwingTableUtil.searchProductListToArray(searchProductList);
		
		
		searchProductTableModel =new DefaultTableModel(
				searchProductModelArray,
				new String[] {
					"product_id", "product_name", "product_price", "product_color_id", "product_color_name", "product_category_id", "product_category_name"
				}
			);
		productTable.setModel(searchProductTableModel);
		productRemoveButton.setEnabled(false);
		productModifyButton.setEnabled(false);
	}

}
