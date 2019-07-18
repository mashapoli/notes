package sql.demo;

import com.sun.javaws.util.JfxHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NotesFrame extends JFrame{

    public NotesFrame(){

        super("Notes");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(5, 5));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JPanel periodPanel = new JPanel(new FlowLayout());
        periodPanel.setBorder(BorderFactory.createTitledBorder("Period"));

        JLabel from = new JLabel("From");
        from.setLayout(new BorderLayout());
        String sFrom = "ex.2019-06-30 20:18:54.7";
        JTextField fieldF = new JTextField();
        fieldF.setText(sFrom);

        periodPanel.add(from);
        periodPanel.add(fieldF);

        JLabel to = new JLabel("To");
        from.setLayout(new BorderLayout());
        String sTo = "ex.2019-07-30 20:18:54.7";
        JTextField fieldTo = new JTextField();
        fieldTo.setText(sTo);

        periodPanel.add(to);
        periodPanel.add(fieldTo);

        JPanel notes = new JPanel(new FlowLayout());
        notes.setBorder(BorderFactory.createTitledBorder("Notes"));

        JButton newNotes = new JButton("New Notes");
        JButton edit = new JButton("Edit");
        JButton delete = new JButton("Delete");

        JPanel button = new JPanel(new FlowLayout());
        button.setBorder(BorderFactory.createTitledBorder(""));

        button.add(newNotes);
        button.add(edit);
        button.add(delete);

        mainPanel.add(periodPanel, BorderLayout.NORTH);
        mainPanel.add(notes, BorderLayout.CENTER);
        mainPanel.add(button, BorderLayout.SOUTH);


        getContentPane().add(mainPanel);

        JButton show = new JButton("Show");
        periodPanel.add(show);


        setPreferredSize(new Dimension(600, 600));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String args[]){
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame.setDefaultLookAndFeelDecorated(true);
                new NotesFrame();
            }
        });
    }

}