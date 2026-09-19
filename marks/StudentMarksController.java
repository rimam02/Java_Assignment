package marks;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentMarksController
        implements ActionListener {

    private StudentMarksModel model;
    private StudentMarksView view;

    public StudentMarksController(
            StudentMarksModel model,
            StudentMarksView view) {

        this.model = model;
        this.view = view;

        view.addButton.addActionListener(this);
        view.updateButton.addActionListener(this);
        view.deleteButton.addActionListener(this);
        view.averageButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == view.addButton) {

            addStudent();

        } else if (e.getSource() == view.updateButton) {

            updateStudent();

        } else if (e.getSource() == view.deleteButton) {

            deleteStudent();

        } else if (e.getSource() == view.averageButton) {

            showAverage();
        }
    }

    private void addStudent() {

        try {

            int rollNo = Integer.parseInt(
                    view.rollNoField.getText()
            );

            String name = view.nameField.getText().trim();

            double marks = Double.parseDouble(
                    view.marksField.getText()
            );

            if (name.isEmpty()) {

                JOptionPane.showMessageDialog(
                        view,
                        "Name cannot be empty.",
                        "Validation Error",
                        JOptionPane.ERROR_MESSAGE
                );

                return;
            }

            if (marks < 0 || marks > 100) {

                JOptionPane.showMessageDialog(
                        view,
                        "Marks must be between 0 and 100.",
                        "Validation Error",
                        JOptionPane.ERROR_MESSAGE
                );

                return;
            }

            StudentMarks student =
                    new StudentMarks(
                            rollNo,
                            name,
                            marks
                    );

            model.addStudent(student);

            view.tableModel.addRow(
                    new Object[]{
                            rollNo,
                            name,
                            marks
                    }
            );

            clearFields();

            JOptionPane.showMessageDialog(
                    view,
                    "Student added successfully.",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    view,
                    "Please enter valid numeric values for Roll No and Marks.",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void updateStudent() {

        try {

            int row = view.table.getSelectedRow();

            if (row < 0) {
                throw new ArrayIndexOutOfBoundsException();
            }

            int rollNo = Integer.parseInt(
                    view.rollNoField.getText()
            );

            String name = view.nameField.getText().trim();

            double marks = Double.parseDouble(
                    view.marksField.getText()
            );

            if (name.isEmpty()) {

                JOptionPane.showMessageDialog(
                        view,
                        "Name cannot be empty.",
                        "Validation Error",
                        JOptionPane.ERROR_MESSAGE
                );

                return;
            }

            if (marks < 0 || marks > 100) {

                JOptionPane.showMessageDialog(
                        view,
                        "Marks must be between 0 and 100.",
                        "Validation Error",
                        JOptionPane.ERROR_MESSAGE
                );

                return;
            }

            model.updateStudent(
                    row,
                    rollNo,
                    name,
                    marks
            );

            view.tableModel.setValueAt(
                    rollNo,
                    row,
                    0
            );

            view.tableModel.setValueAt(
                    name,
                    row,
                    1
            );

            view.tableModel.setValueAt(
                    marks,
                    row,
                    2
            );

            JOptionPane.showMessageDialog(
                    view,
                    "Row updated successfully.",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

        } catch (ArrayIndexOutOfBoundsException ex) {

            JOptionPane.showMessageDialog(
                    view,
                    "Please select a row before updating.",
                    "Update Error",
                    JOptionPane.ERROR_MESSAGE
            );

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    view,
                    "Please enter valid numeric values.",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void deleteStudent() {

        try {

            int row = view.table.getSelectedRow();

            if (row < 0) {
                throw new ArrayIndexOutOfBoundsException();
            }

            model.deleteStudent(row);

            view.tableModel.removeRow(row);

            JOptionPane.showMessageDialog(
                    view,
                    "Student deleted successfully.",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

        } catch (ArrayIndexOutOfBoundsException ex) {

            JOptionPane.showMessageDialog(
                    view,
                    "Please select a row before deleting.",
                    "Delete Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void showAverage() {

        double average = model.calculateAverage();

        view.averageLabel.setText(
                String.format(
                        "Class Average: %.1f",
                        average
                )
        );

        launchJavaFXWindow(average);
    }

    private void clearFields() {

        view.rollNoField.setText("");
        view.nameField.setText("");
        view.marksField.setText("");
    }

    private void launchJavaFXWindow(double average) {

        try {

            javafx.application.Platform.runLater(() -> {

                javafx.stage.Stage stage =
                        new javafx.stage.Stage();

                javafx.scene.control.Label label =
                        new javafx.scene.control.Label(
                                String.format(
                                        "Class Average (JavaFX): %.1f",
                                        average
                                )
                        );

                javafx.scene.control.Button button =
                        new javafx.scene.control.Button(
                                "Close"
                        );

                button.setOnAction(
                        event -> stage.close()
                );

                javafx.scene.layout.VBox layout =
                        new javafx.scene.layout.VBox(
                                15,
                                label,
                                button
                        );

                layout.setAlignment(
                        javafx.geometry.Pos.CENTER
                );

                javafx.scene.Scene scene =
                        new javafx.scene.Scene(
                                layout,
                                300,
                                150
                        );

                stage.setTitle(
                        "JavaFX Average Preview"
                );

                stage.setScene(scene);

                stage.show();
            });

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(
                    view,
                    "Unable to launch JavaFX window: "
                            + ex.getMessage(),
                    "JavaFX Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}
