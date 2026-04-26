import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

class Student {
    String name;
    double grade;

    Student(String name, double grade) {
        this.name = name;
        this.grade = grade;
    }
}

public class GradeTrackerGUI extends JFrame {

    private JTextField nameField, gradeField;
    private DefaultTableModel tableModel;
    private ArrayList<Student> students;

    public GradeTrackerGUI() {
        students = new ArrayList<>();

        setTitle("Student Grade Tracker");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Top Panel (Input)
        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Name:"));
        nameField = new JTextField(10);
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("Grade:"));
        gradeField = new JTextField(5);
        inputPanel.add(gradeField);

        JButton addButton = new JButton("Add Student");
        inputPanel.add(addButton);

        add(inputPanel, BorderLayout.NORTH);

        // Table
        tableModel = new DefaultTableModel(new String[]{"Name", "Grade"}, 0);
        JTable table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Bottom Panel (Results)
        JPanel resultPanel = new JPanel();
        JButton calcButton = new JButton("Calculate");
        JLabel resultLabel = new JLabel("Average: 0 | Highest: 0 | Lowest: 0");

        resultPanel.add(calcButton);
        resultPanel.add(resultLabel);

        add(resultPanel, BorderLayout.SOUTH);

        // Add Button Action
        addButton.addActionListener(e -> {
            String name = nameField.getText();
            double grade;

            try {
                grade = Double.parseDouble(gradeField.getText());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Enter valid grade!");
                return;
            }

            students.add(new Student(name, grade));
            tableModel.addRow(new Object[]{name, grade});

            nameField.setText("");
            gradeField.setText("");
        });

        // Calculate Button Action
        calcButton.addActionListener(e -> {
            if (students.isEmpty()) return;

            double total = 0;
            double highest = students.get(0).grade;
            double lowest = students.get(0).grade;

            for (Student s : students) {
                total += s.grade;

                if (s.grade > highest) highest = s.grade;
                if (s.grade < lowest) lowest = s.grade;
            }

            double avg = total / students.size();

            resultLabel.setText("Average: " + avg +
                    " | Highest: " + highest +
                    " | Lowest: " + lowest);
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new GradeTrackerGUI();
    }
}