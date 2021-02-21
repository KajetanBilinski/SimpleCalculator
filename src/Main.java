import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args)
    {
        new Calculator();
    }
    public static class Calculator extends JFrame
    {
        JPanel panel = new JPanel();
        JTextField textField = new JTextField();
        JPanel OperationsPanel = new JPanel();
        JButton Plus = new JButton("+");
        JButton Minus  = new JButton("-");
        JButton Dziel = new JButton("/");
        JButton Mnoz = new JButton("*");
        JButton Pierwiastek = new JButton("√");
        JButton Potega = new JButton("²");
        JButton MinusPlus = new JButton("+/-");
        JButton Rowna = new JButton("=");
        JButton Dot = new JButton(".");
        JButton Remove = new JButton("Back");
        ArrayList<JButton> buts = new ArrayList<>();
        ArrayList<JButton> ops = new ArrayList<>();
        boolean isNull=true;
        boolean remembering=false;
        boolean operationClicked=false;
        double remembered = 0;
        ArrayList<Double> vals = new ArrayList<>();
        String operation;

        Calculator()
        {
            ops.add(Plus);
            ops.add(Minus);
            ops.add(Dziel);
            ops.add(Pierwiastek);
            ops.add(Potega);
            ops.add(MinusPlus);
            ops.add(Rowna);
            ops.add(Remove);
            ops.add(Dot);
            ops.add(Mnoz);
            setup();
            setupListeners();
            setVisible(true);
            setPreferredSize(new Dimension(350,280));
            setResizable(false);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            pack();
        }

        private void setupListeners()
        {
            Remove.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    textField.setText("");
                    vals.clear();
                }
            });
            MinusPlus.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(!textField.getText().isEmpty())
                        textField.setText(Double.parseDouble(textField.getText())*-1+"");
                }
            });
            Pierwiastek.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(!textField.getText().isEmpty())
                        textField.setText(Math.sqrt(Double.parseDouble(textField.getText()))+"");
                    operationClicked=true;
                }
            });
            Potega.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(!textField.getText().isEmpty())
                        textField.setText(Math.pow(Double.parseDouble(textField.getText()),2)+"");
                    operationClicked=true;
                }
            });
            Dot.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(textField.getText().isEmpty())
                    {
                        textField.setText("0.");
                    }
                    else
                    {
                        if(!textField.getText().contains("."))
                            textField.setText(textField.getText()+".");
                    }
                }
            });
            Rowna.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    if(!isNull&&!textField.getText().isEmpty()&&!vals.isEmpty())
                    {
                        vals.add(Double.parseDouble(textField.getText()));
                        if(operation.equals("+")||operation.equals("-")||operation.equals("*")||operation.equals("/"))
                        {
                            if(operation.equals("+"))
                                textField.setText(String.valueOf((new Calcing(vals.get(0), vals.get(1)).dod())));
                            if(operation.equals("-"))
                                textField.setText(String.valueOf((new Calcing(vals.get(0), vals.get(1)).odej())));
                            if(operation.equals("*"))
                                textField.setText(String.valueOf((new Calcing(vals.get(0), vals.get(1)).mno())));
                            if(operation.equals("/"))
                                textField.setText(String.valueOf((new Calcing(vals.get(0), vals.get(1)).dziel())));
                            vals.clear();
                            isNull=true;
                            operationClicked=true;
                        }
                    }
                }
            });
        }

        private void setup()
        {
            GridBagLayout gbl = new GridBagLayout();
            GridBagConstraints gbc = new GridBagConstraints();
            OperationsPanel.setLayout(gbl);
            gbc.gridx=0;
            gbc.gridy=0;
            for (int i = 0; i <=9 ; i++)
            {
                JButton but = new JButton(i+"");
                but.setPreferredSize(new Dimension(70,35));
                but.setForeground(new Color(0xFFFFFFFF, true));
                but.setBackground(new Color(0xFF000000, true));
                but.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(!operationClicked)
                        textField.setText(textField.getText()+but.getText());
                        else
                        {
                            textField.setText(but.getText());
                            operationClicked=false;
                        }
                    }
                });
                buts.add(but);
            }
            for(JButton a : ops)
            {
                a.setPreferredSize(new Dimension(70,35));
                a.setForeground(new Color(0xFF000000, true));
                a.setBackground(new Color(0xFFFFFFFF, true));
                if(a.getText().equals("+")||a.getText().equals("-")||a.getText().equals("/")||a.getText().equals("*"))
                    a.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if(isNull&&!textField.getText().isEmpty())
                            {
                                vals.add(Double.parseDouble(textField.getText()));
                                isNull=false;
                                textField.setText("");
                                operationClicked=true;
                                operation=a.getText();
                            }
                        }
                    });
            }
            for (int i = 0; i <4 ; i++)
            {
                if(i==0)
                    OperationsPanel.add(Plus,gbc);
                if(i==1)
                    OperationsPanel.add(Minus,gbc);
                if(i==2)
                    OperationsPanel.add(Dziel,gbc);
                if(i==3)
                    OperationsPanel.add(Mnoz,gbc);
                gbc.gridx++;
            }
            gbc.gridx=0;
            gbc.gridy=1;
            for (int i = 0; i <4 ; i++)
            {
                if(i==0)
                    OperationsPanel.add(buts.get(7),gbc);
                if(i==1)
                    OperationsPanel.add(buts.get(8),gbc);
                if(i==2)
                    OperationsPanel.add(buts.get(9),gbc);
                if(i==3)
                    OperationsPanel.add(Pierwiastek,gbc);
                gbc.gridx++;
            }
            gbc.gridx=0;
            gbc.gridy=2;
            for (int i = 0; i <4 ; i++)
            {
                if(i==0)
                    OperationsPanel.add(buts.get(4),gbc);
                if(i==1)
                    OperationsPanel.add(buts.get(5),gbc);
                if(i==2)
                    OperationsPanel.add(buts.get(6),gbc);
                if(i==3)
                    OperationsPanel.add(Potega,gbc);
                gbc.gridx++;
            }
            gbc.gridx=0;
            gbc.gridy=3;
            for (int i = 0; i <4 ; i++)
            {
                if(i==0)
                    OperationsPanel.add(buts.get(1),gbc);
                if(i==1)
                    OperationsPanel.add(buts.get(2),gbc);
                if(i==2)
                    OperationsPanel.add(buts.get(3),gbc);
                if(i==3)
                    OperationsPanel.add(MinusPlus,gbc);
                gbc.gridx++;
            }
            gbc.gridx=0;
            gbc.gridy=4;
            for (int i = 0; i <4 ; i++)
            {
                if(i==0)
                    OperationsPanel.add(Remove,gbc);
                if(i==1)
                    OperationsPanel.add(buts.get(0),gbc);
                if(i==2)
                    OperationsPanel.add(Dot,gbc);
                if(i==3)
                    OperationsPanel.add(Rowna,gbc);
                gbc.gridx++;
            }

            textField.setPreferredSize(new Dimension(280,30));
            textField.setFont(new Font("Arial",Font.BOLD,20));
            textField.setBackground(new Color(0x000000));
            textField.setVisible(true);
            textField.setEnabled(false);

            gbc.gridx=0;
            panel.add(textField);
            setLayout(new GridBagLayout());
            gbc.gridy=1;
            add(OperationsPanel,gbc);
            gbc.gridy=0;
            add(panel,gbc);
        }
    }
}
