package marks;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class StudentMarksView extends JFrame {

    JTextField rollNoField;
    JTextField nameField;
    JTextField marksField;

    JButton addButton;
    JButton updateButton;
    JButton deleteButton;
    JButton averageButton;

    JTable table;
    DefaultTableModel tableModel;

    JLabel averageLabel;

    public StudentMarksView() {

        setTitle("Student Marks Viewer");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout(10, 10));

        JPanel inputPanel = new JPanel(
                new GridLayout(2, 6, 5, 5)
        );

        inputPanel.add(new JLabel("Roll No"));
        inputPanel.add(new JLabel("Name"));
        inputPanel.add(new JLabel("Marks"));
        inputPanel.add(new JLabel(""));
        inputPanel.add(new JLabel(""));
        inputPanel.add(new JLabel(""));

        rollNoField = new JTextField();
        nameField = new JTextField();
        marksField = new JTextField();

        inputPanel.add(rollNoField);
        inputPanel.add(nameField);
        inputPanel.add(marksField);

        addButton = new JButton("Add");
        updateButton = new JButton("Update");
        deleteButton = new JButton("Delete");

        inputPanel.add(addButton);
        inputPanel.add(updateButton);
        inputPanel.add(deleteButton);

        add(inputPanel, BorderLayout.NORTH);

        String[] columns = {
                "Roll No",
                "Name",
                "Marks"
        };

        tableModel = new DefaultTableModel(columns, 0);

        table = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(table);

        add(scrollPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(
                new FlowLayout()
        );

        averageButton = new JButton("Show Average");

        averageLabel = new JLabel(
                "Class Average: 0.0"
        );

        bottomPanel.add(averageButton);
        bottomPanel.add(averageLabel);

        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }
}
