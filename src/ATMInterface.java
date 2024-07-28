import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ATMInterface extends JFrame
{
    private static double balance = 3000.00;
    private JLabel balanceLabel;
    private JTextField amountField;

    public ATMInterface() 
    {
        setTitle("ATM Interface");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 1));

        // View Balance button
        JButton viewBalanceButton = new JButton("View Balance");
        viewBalanceButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                viewBalance();
            }
        });
        add(viewBalanceButton);

        // Withdraw Money button
        JButton withdrawButton = new JButton("Withdraw Money");
        withdrawButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                withdrawMoney();
            }
        });
        add(withdrawButton);

        // Deposit Money button
        JButton depositButton = new JButton("Deposit Money");
        depositButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                depositMoney();
            }
        });
        add(depositButton);

        // Exit button
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                System.out.println("Thank you for using the ATM. Goodbye!");
                System.exit(0);
            }
        });
        add(exitButton);

        // Label for displaying balance and text field for entering amount
        balanceLabel = new JLabel("Your current balance is: $" + String.format("%.2f", balance));
        add(balanceLabel);

        amountField = new JTextField(10);
        add(amountField);
    }

    private void viewBalance()
    {
        balanceLabel.setText("Your current balance is: $" + String.format("%.2f", balance));
    }

    private void withdrawMoney() 
    {
        try {
            double amount = Double.parseDouble(amountField.getText());
            if (amount > balance)
            {
                JOptionPane.showMessageDialog(this, "Insufficient balance.", "Error", JOptionPane.ERROR_MESSAGE);
            } else 
            {
                balance -= amount;
                JOptionPane.showMessageDialog(this, "You have withdrawn $" + String.format("%.2f", amount) + ". Your new balance is: $" + String.format("%.2f", balance), "Success", JOptionPane.INFORMATION_MESSAGE);
                viewBalance();
            }
        }
        catch (NumberFormatException e) 
        {
            JOptionPane.showMessageDialog(this, "Please enter a valid amount.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void depositMoney()
    {
        try
        {
            double amount = Double.parseDouble(amountField.getText());
            balance += amount;
            JOptionPane.showMessageDialog(this, "You have deposited $" + String.format("%.2f", amount) + ". Your new balance is: $" + String.format("%.2f", balance), "Success", JOptionPane.INFORMATION_MESSAGE);
            viewBalance();
        }
        catch (NumberFormatException e)
        {
            JOptionPane.showMessageDialog(this, "Please enter a valid amount.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(() -> {
            ATMInterface frame = new ATMInterface();
            frame.setVisible(true);
        });
    }
}
