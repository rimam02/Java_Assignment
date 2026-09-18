import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentFeedbackForm extends JFrame implements ActionListener {

    private JTextField nameField;
    private JTextField courseField;
    private JTextField ratingField;
    private JTextField commentsField;

    private JTextArea feedbackArea;

    private JButton submitButton;

    private JMenuItem saveFeedbackItem;
    private JMenuItem clearFormItem;
    private JMenuItem exitItem;
    private JMenuItem aboutItem;

    public StudentFeedbackForm() {

        setTitle("Student Feedback Form");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout(10, 10));

        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        JMenu helpMenu = new JMenu("Help");

        saveFeedbackItem = new JMenuItem("Save Feedback");
        clearFormItem = new JMenuItem("Clear Form");
        exitItem = new JMenuItem("Exit");

        aboutItem = new JMenuItem("About");

        fileMenu.add(saveFeedbackItem);
        fileMenu.add(clearFormItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        helpMenu.add(aboutItem);

        menuBar.add(fileMenu);
        menuBar.add(helpMenu);

        setJMenuBar(menuBar);

        saveFeedbackItem.addActionListener(this);
        clearFormItem.addActionListener(this);
        exitItem.addActionListener(this);
        aboutItem.addActionListener(this);

        JPanel formPanel = new JPanel();

        formPanel.setLayout(new GridLayout(4, 2, 10, 10));

        JLabel nameLabel = new JLabel("Name:");
        JLabel courseLabel = new JLabel("Course:");
        JLabel ratingLabel = new JLabel("Rating:");
        JLabel commentsLabel = new JLabel("Comments:");

        nameField = new JTextField();
        courseField = new JTextField();
        ratingField = new JTextField();
        commentsField = new JTextField();

        formPanel.add(nameLabel);
        formPanel.add(nameField);

        formPanel.add(courseLabel);
        formPanel.add(courseField);

        formPanel.add(ratingLabel);
        formPanel.add(ratingField);

        formPanel.add(commentsLabel);
        formPanel.add(commentsField);

        add(formPanel, BorderLayout.NORTH);

        submitButton = new JButton("Submit Feedback");
        submitButton.addActionListener(this);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(submitButton);

        add(buttonPanel, BorderLayout.CENTER);

        feedbackArea = new JTextArea();
        feedbackArea.setEditable(false);

        feedbackArea.setBorder(
                BorderFactory.createTitledBorder("Submitted Feedback")
        );

        JScrollPane scrollPane = new JScrollPane(feedbackArea);

        add(scrollPane, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void submitFeedback() {

        String name = nameField.getText().trim();
        String course = courseField.getText().trim();
        String ratingText = ratingField.getText().trim();
        String comments = commentsField.getText().trim();

        if (name.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Name cannot be empty.",
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        if (course.isEmpty() || ratingText.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please fill in all required fields.",
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        try {

            int rating = Integer.parseInt(ratingText);

            if (rating < 1 || rating > 5) {

                JOptionPane.showMessageDialog(
                        this,
                        "Rating must be between 1 and 5.",
                        "Validation Error",
                        JOptionPane.ERROR_MESSAGE
                );

                return;
            }

            String feedback =
                    "Name : " + name
                    + " | Course : " + course
                    + " | Rating : " + rating
                    + " | Comments : " + comments
                    + "\n";

            feedbackArea.append(feedback);

            JOptionPane.showMessageDialog(
                    this,
                    "Feedback added to list.",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Rating must be a numeric value.",
                    "Invalid Rating",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void clearForm() {

        nameField.setText("");
        courseField.setText("");
        ratingField.setText("");
        commentsField.setText("");

        JOptionPane.showMessageDialog(
                this,
                "Form fields cleared.",
                "Clear Form",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    private void exitApplication() {

        int result = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to exit?",
                "Confirm Exit",
                JOptionPane.YES_NO_OPTION
        );

        if (result == JOptionPane.YES_OPTION) {
            dispose();
        }
    }

    private void showAbout() {

        JOptionPane.showMessageDialog(
                this,
                "Student Feedback Form v1.0\n"
                        + "Submitted by Rima Maji\n"
                        + "Purpose: Collect student feedback.",
                "About",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Object source = e.getSource();

        if (source == submitButton
                || source == saveFeedbackItem) {

            submitFeedback();

        } else if (source == clearFormItem) {

            clearForm();

        } else if (source == exitItem) {

            exitApplication();

        } else if (source == aboutItem) {

            showAbout();
        }
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new StudentFeedbackForm();
        });
    }
}