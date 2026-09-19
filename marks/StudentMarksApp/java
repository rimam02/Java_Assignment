package marks;

import javax.swing.SwingUtilities;

public class StudentMarksApp {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            StudentMarksModel model =
                    new StudentMarksModel();

            StudentMarksView view =
                    new StudentMarksView();

            new StudentMarksController(
                    model,
                    view
            );
        });
    }
}
