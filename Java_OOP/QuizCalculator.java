
import javax.swing.*;
import java.awt.*;

public class QuizCalculator extends JFrame {

    JTextField name, subject, total, correct;
    JLabel result;
    DefaultListModel<String> history;
    JList<String> historyList;

    public QuizCalculator() {

        setTitle("Student Quiz Calculator");
        setSize(550, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Calculator Form
        JPanel form = new JPanel(new GridLayout(5, 2, 5, 5));

        name = new JTextField();
        subject = new JTextField();
        total = new JTextField();
        correct = new JTextField();

        result = new JLabel("Result will appear here");

        form.add(new JLabel("Student Name:"));
        form.add(name);

        form.add(new JLabel("Subject:"));
        form.add(subject);

        form.add(new JLabel("Total Questions:"));
        form.add(total);

        form.add(new JLabel("Correct Answers:"));
        form.add(correct);

        JButton calculate = new JButton("Calculate");
        JButton clear = new JButton("Clear");

        form.add(calculate);
        form.add(clear);

        // Quiz History
        history = new DefaultListModel<>();
        historyList = new JList<>(history);

        historyList.setSelectionMode(
            ListSelectionModel.MULTIPLE_INTERVAL_SELECTION
        );

        JButton delete = new JButton("Delete Selected");
        JButton deleteAll = new JButton("Delete All");

        JPanel deleteButtons = new JPanel();
        deleteButtons.add(delete);
        deleteButtons.add(deleteAll);

        JPanel historyPanel = new JPanel(new BorderLayout(5, 5));

        historyPanel.add(
            new JLabel("Quiz History:"),
            BorderLayout.NORTH
        );

        historyPanel.add(
            new JScrollPane(historyList),
            BorderLayout.CENTER
        );

        historyPanel.add(
            deleteButtons,
            BorderLayout.SOUTH
        );

        // Main Layout
        add(form, BorderLayout.NORTH);
        add(result, BorderLayout.CENTER);
        add(historyPanel, BorderLayout.SOUTH);

        // Calculate Button
        calculate.addActionListener(e -> calculateScore());

        // Clear Button
        clear.addActionListener(e -> {
            name.setText("");
            subject.setText("");
            total.setText("");
            correct.setText("");
            result.setText("Result will appear here");
        });

        // Delete Selected History
        delete.addActionListener(e -> {

            int[] selected = historyList.getSelectedIndices();

            if (selected.length > 0) {

                for (int i = selected.length - 1; i >= 0; i--) {
                    history.remove(selected[i]);
                }

            } else {
                JOptionPane.showMessageDialog(this,
                    "Please select a history record.");
            }
        });

        // Delete All History
        deleteAll.addActionListener(e -> {

            if (history.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                    "No history records to delete.");
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to delete all history?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION
            );

            if (confirm == JOptionPane.YES_OPTION) {
                history.clear();
            }
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Calculate Quiz Score
    public void calculateScore() {

        try {

            int t = Integer.parseInt(total.getText());
            int c = Integer.parseInt(correct.getText());

            if (t <= 0 || c < 0 || c > t ||
                name.getText().trim().isEmpty() ||
                subject.getText().trim().isEmpty()) {

                JOptionPane.showMessageDialog(this,
                    "Please enter valid information.");
                return;
            }

            double percentage = (c * 100.0) / t;

            String status = percentage >= 75
                ? "PASSED" : "FAILED";

            String score = String.format(
                "%.2f%% - %s", percentage, status
            );

            result.setText(score);

            history.addElement(
                name.getText() + " | " +
                subject.getText() + " | " +
                score
            );

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(this,
                "Please enter valid numbers.");
        }
    }

    public static void main(String[] args) {
        new QuizCalculator();
    }
}
