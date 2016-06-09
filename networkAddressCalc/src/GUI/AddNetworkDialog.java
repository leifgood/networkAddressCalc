package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

public class AddNetworkDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txt_IPv4;
	private JTextField txt_IPv4_prefix;
	private JTextField txt_IPv6;
	private JTextField txt_IPv6_prefix;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AddNetworkDialog dialog = new AddNetworkDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AddNetworkDialog() {
		setTitle("Add Network");
		setBounds(100, 100, 430, 208);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 414, 137);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		
		JLabel lbl_IPv4 = new JLabel("IPv4");
		
		JLabel lbl_IPv6 = new JLabel("IPv6");
		
		txt_IPv4 = new JTextField();
		txt_IPv4.setColumns(10);
		
		JLabel lbl_IPv4_Slash = new JLabel("/");
		
		txt_IPv4_prefix = new JTextField();
		txt_IPv4_prefix.setColumns(10);
		
		txt_IPv6 = new JTextField();
		txt_IPv6.setColumns(10);
		
		JLabel lbl_IPv6_Slash = new JLabel("/");
		
		txt_IPv6_prefix = new JTextField();
		txt_IPv6_prefix.setColumns(10);
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lbl_IPv4)
						.addComponent(lbl_IPv6))
					.addGap(10)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(txt_IPv4, GroupLayout.PREFERRED_SIZE, 307, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lbl_IPv4_Slash)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txt_IPv4_prefix, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(txt_IPv6, GroupLayout.PREFERRED_SIZE, 307, GroupLayout.PREFERRED_SIZE)
							.addGap(4)
							.addComponent(lbl_IPv6_Slash, GroupLayout.PREFERRED_SIZE, 4, GroupLayout.PREFERRED_SIZE)
							.addGap(4)
							.addComponent(txt_IPv6_prefix, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbl_IPv4)
						.addComponent(txt_IPv4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txt_IPv4_prefix, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lbl_IPv4_Slash))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(lbl_IPv6)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
							.addComponent(txt_IPv6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGroup(gl_contentPanel.createSequentialGroup()
								.addGap(3)
								.addComponent(lbl_IPv6_Slash))
							.addComponent(txt_IPv6_prefix, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 137, 414, 33);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane);
			{
				JButton save = new JButton("Save");
				save.setActionCommand("OK");
				buttonPane.add(save);
				getRootPane().setDefaultButton(save);
			}
			{
				JButton cancel = new JButton("Cancel");
				cancel.setActionCommand("Cancel");
				buttonPane.add(cancel);
			}
		}
	}
}
