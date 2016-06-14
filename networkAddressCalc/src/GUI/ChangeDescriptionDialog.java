package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ChangeDescriptionDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JLabel lbl_DescriptionName;
	private JTextField txt_DescriptionName;
	
	private ChangeDescriptionPresenter changedescriptionpresenter;
	
	public ChangeDescriptionDialog( ChangeDescriptionPresenter changedescriptionpresenter )
	{
		this.changedescriptionpresenter = changedescriptionpresenter;
		init();
	}

	public void init() {
		setBounds(100, 100, 430, 210);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			lbl_DescriptionName = new JLabel("New Description");
		}
		
		txt_DescriptionName = new JTextField();
		txt_DescriptionName.setColumns(10);
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lbl_DescriptionName)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(txt_DescriptionName, GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbl_DescriptionName)
						.addComponent(txt_DescriptionName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(104, Short.MAX_VALUE))
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton save = new JButton("Save");
				save.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						changedescriptionpresenter.verifySave();
					}
				});
				save.setActionCommand("OK");
				buttonPane.add(save);
				getRootPane().setDefaultButton(save);
			}
			{
				JButton cancel = new JButton("Cancel");
				cancel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						changedescriptionpresenter.verifyCancel();
					}
				});
				cancel.setActionCommand("Cancel");
				buttonPane.add(cancel);
			}
		}
	}
}
