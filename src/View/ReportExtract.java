package View;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import com.toedter.calendar.JDateChooser;

import Controller.ExpenseBusiness;
import Controller.TransactionBusiness;

public class ReportExtract extends JFrame {
	
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReportExtract frame = new ReportExtract();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ReportExtract() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 531, 441);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblExtractReport = new JLabel("Extract report:");
		lblExtractReport.setFont(new Font("Consolas", Font.PLAIN, 12));
		lblExtractReport.setBounds(31, 30, 200, 20);
		contentPane.add(lblExtractReport);
		
		JLabel lblNewLabel = new JLabel("From:");
		lblNewLabel.setFont(new Font("Consolas", Font.PLAIN, 12));
		lblNewLabel.setBounds(31, 75, 46, 20);
		contentPane.add(lblNewLabel);
		
		JDateChooser begin = new JDateChooser();
		begin.getSpinner().setFont(new Font("Consolas", Font.PLAIN, 13));
		begin.setBounds(120, 65, 270, 30);
		contentPane.add(begin);
		
		JLabel lblNewLabel_1 = new JLabel("To:");
		lblNewLabel_1.setFont(new Font("Consolas", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(31, 120, 46, 20);
		contentPane.add(lblNewLabel_1);
		
		JDateChooser finish = new JDateChooser();
		finish.getSpinner().setFont(new Font("Consolas", Font.PLAIN, 13));
		finish.setBounds(120, 110, 270, 30);
		contentPane.add(finish);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Report", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(31, 159, 458, 229);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblIncome = new JLabel("Income:");
		lblIncome.setFont(new Font("Consolas", Font.PLAIN, 12));
		lblIncome.setBounds(25, 30, 69, 20);
		panel.add(lblIncome);
		
		JLabel lblOutcome = new JLabel("Outcome:");
		lblOutcome.setFont(new Font("Consolas", Font.PLAIN, 12));
		lblOutcome.setBounds(25, 70, 69, 20);
		panel.add(lblOutcome);
		
		JLabel lblNewLabel_2 = new JLabel("Salary:");
		lblNewLabel_2.setFont(new Font("Consolas", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(66, 110, 87, 20);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Other expenses:");
		lblNewLabel_3.setFont(new Font("Consolas", Font.PLAIN, 12));
		lblNewLabel_3.setBounds(66, 150, 124, 20);
		panel.add(lblNewLabel_3);
		
		JLabel txtIncome = new JLabel("0");
		txtIncome.setFont(new Font("Consolas", Font.PLAIN, 12));
		txtIncome.setBounds(220, 30, 70, 20);
		panel.add(txtIncome);
		
		JLabel txtOutcome = new JLabel("0");
		txtOutcome.setFont(new Font("Consolas", Font.PLAIN, 12));
		txtOutcome.setBounds(220, 70, 70, 20);
		panel.add(txtOutcome);
		
		JLabel txtSalary = new JLabel("0");
		txtSalary.setFont(new Font("Consolas", Font.PLAIN, 12));
		txtSalary.setBounds(220, 110, 70, 20);
		panel.add(txtSalary);
		
		JLabel txtOther = new JLabel("0");
		txtOther.setFont(new Font("Consolas", Font.PLAIN, 12));
		txtOther.setBounds(220, 150, 70, 20);
		panel.add(txtOther);
		
		JLabel lblSum = new JLabel("Sum:");
		lblSum.setFont(new Font("Consolas", Font.BOLD, 12));
		lblSum.setBounds(25, 190, 100, 20);
		panel.add(lblSum);
		
		JLabel txtSum = new JLabel("0");
		txtSum.setFont(new Font("Consolas", Font.BOLD, 12));
		txtSum.setBounds(220, 190, 70, 20);
		panel.add(txtSum);
		
		JButton btnExtract = new JButton("Extract");
		btnExtract.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Date beginDate = begin.getDate();
				Date finishDate = finish.getDate();
				int income = TransactionBusiness.calculateIncome(beginDate, finishDate);
				int salary = ExpenseBusiness.calculateSalary(beginDate, finishDate);
				int other = ExpenseBusiness.calculateOtherExpenses(beginDate, finishDate);
				txtIncome.setText(Integer.toString(income));
				txtOutcome.setText(Integer.toString(salary + other));
				txtSalary.setText(Integer.toString(salary));
				txtOther.setText(Integer.toString(other));
				txtSum.setText(Integer.toString(income - salary - other));
			}
		});
		btnExtract.setFont(new Font("Consolas", Font.PLAIN, 12));
		btnExtract.setBounds(400, 110, 89, 30);
		contentPane.add(btnExtract);
		
		
	}
}
