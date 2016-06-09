package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

public class AddSubnetDialog extends JDialog {
	private JTextField txt_Subnetname;
	private JTextField txt_Hostcount;
	private JPanel panel_1;
	private JButton save;
	private JButton cancel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AddSubnetDialog dialog = new AddSubnetDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AddSubnetDialog() {
		setTitle("Add Subnet");
		setBounds(100, 100, 430, 210);
		getContentPane().setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBounds(0, 0, 414, 138);
			panel.setBorder(new EmptyBorder(5, 5, 5, 5));
			getContentPane().add(panel);
			JLabel lbl_Subnetname = new JLabel("Subnet Name");
			txt_Subnetname = new JTextField();
			txt_Subnetname.setColumns(10);
			JLabel lbl_Hostcount = new JLabel("Host Count");
			txt_Hostcount = new JTextField();
			txt_Hostcount.setColumns(10);
			GroupLayout gl_panel = new GroupLayout(panel);
			gl_panel.setHorizontalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
							.addComponent(lbl_Hostcount)
							.addComponent(lbl_Subnetname))
						.addPreferredGap(ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
							.addComponent(txt_Subnetname)
							.addComponent(txt_Hostcount, GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE))
						.addContainerGap())
			);
			gl_panel.setVerticalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(lbl_Subnetname)
							.addComponent(txt_Subnetname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(lbl_Hostcount)
							.addComponent(txt_Hostcount, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(59, Short.MAX_VALUE))
			);
			panel.setLayout(gl_panel);
		}
		
		panel_1 = new JPanel();
		panel_1.setBounds(0, 138, 414, 33);
		getContentPane().add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		save = new JButton("Save");
		save.setActionCommand("OK");
		panel_1.add(save);
		
		cancel = new JButton("Cancel");
		cancel.setActionCommand("Cancel");
		panel_1.add(cancel);
	}

}
