package Management.dept;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.sql.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeptForm extends JFrame {
    UserDAO userDAO = new UserDAO();

    JPanel panel1, panel2, panel3, panel4, panel5;

    JTextField text1, text2, text3;
    static DefaultTableModel tableModel;
    static JTable table;

    public DeptForm() throws BadLocationException, SQLException {
        super("학과관리");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(650, 700);
        setLocationRelativeTo(null);

        this.setLayout(null);

        panel();
        label();
        button();

        setVisible(true);
    }

    void panel() {
        panel1 = new JPanel();
        panel2 = new JPanel();
        panel3 = new JPanel();
        panel4 = new JPanel();
        panel5 = new JPanel();

        panel1.setLayout(null); // 레이아웃 매니저 설정
        panel2.setLayout(null); // 레이아웃 매니저 설정
        panel3.setLayout(null); // 레이아웃 매니저 설정
        panel4.setLayout(null); // 레이아웃 매니저 설정
        panel5.setLayout(null); // 레이아웃 매니저 설정

        panel1.setBounds(0, 0, 650, 80);
        panel1.setBackground(Color.WHITE);
        panel2.setBounds(0, 80, 650, 150);
        panel2.setBackground(new Color(15,15,112));
        panel3.setBounds(0, 230, 650, 70);
        panel3.setBackground(Color.WHITE);
        panel4.setBounds(0, 300, 650, 250);
        panel4.setBackground(new Color(15,15,112));
        panel5.setBounds(0, 550, 650, 150);
        panel5.setBackground(Color.WHITE);

        this.add(panel1);
        this.add(panel2);
        this.add(panel3);
        this.add(panel4);
        this.add(panel5);
    }

    void label() throws BadLocationException, SQLException {

        JLabel label0 = new JLabel("학과관리");
        label0.setForeground(Color.BLACK);
        label0.setFont(new Font("나눔고딕", Font.BOLD, 20));
        label0.setBounds(275, -10, 100, 100);

        JLabel label1 = new JLabel("학과코드");
        label1.setFont(new Font("나눔고딕", Font.BOLD, 15));
        label1.setBounds(40, 65, 100, 20);

        JLabel label2 = new JLabel("학과명");
        label2.setFont(new Font("나눔고딕", Font.BOLD, 15));
        label2.setBounds(230, 65, 100, 20);

        JLabel label3 = new JLabel("전공명");
        label3.setFont(new Font("나눔고딕", Font.BOLD, 15));
        label3.setBounds(400, 65, 100, 20);

        JLabel label4 = new JLabel("입력");
        label4.setFont(new Font("나눔고딕", Font.BOLD, 15));
        label4.setForeground(Color.WHITE);
        label4.setBounds(30, 10, 100, 30);

        text1 = new JTextField();
        text1.setBounds(115, 65, 100, 24);
        text2 = new JTextField();
        text2.setBounds(285, 65, 100, 24);
        text3 = new JTextField();
        text3.setBounds(465, 65, 100, 24);

        JTextPane textPane1 = new JTextPane();
        textPane1.setBackground(Color.GRAY);
        textPane1.setBounds(15, 15, 600, 220);
        textPane1.setEditable(false);
        textPane1.setCursor(null); // 커서 비활성화
        textPane1.setOpaque(false); // 투명 배경 설정
        textPane1.setFocusable(false); // 포커스 비활성화

        JScrollPane scrollPane = new JScrollPane(textPane1);
        scrollPane.setBounds(15, 40, 600, 80);
        Border border = BorderFactory.createLineBorder(Color.BLACK, 2);// 검은색 테두리, 두께 2
        scrollPane.setBorder(border);


        // 테이블 모델을 초기화
        tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                // 모든 셀을 수정 불가능하게 설정
                return false;
            }
        };
        tableModel.addColumn("학과코드");
        tableModel.addColumn("학과명");
        tableModel.addColumn("전공명");

        // JTable 생성 및 모델 설정
        table = new JTable(tableModel);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        JTableHeader header = table.getTableHeader();
        Font headerFont = new Font("나눔고딕", Font.BOLD, 15); // Example: Bold and larger font
        header.setFont(headerFont);


        userDAO.deptInfoAll();

        // JScrollPane에 JTable 추가
        JScrollPane scrollPane1 = new JScrollPane(table);
        scrollPane1.setBounds(15, 50, 600, 150);
        Border border2 = BorderFactory.createLineBorder(Color.GRAY, 2);// 검은색 테두리, 두께 2
        scrollPane1.setBorder(border2);

        String imagePath = "./image/resized.png";
        ImageIcon snuUi = new ImageIcon(imagePath);
        JLabel imageLabel = new JLabel(snuUi);
        imageLabel.setBounds(80,-10, 100,100);
        panel1.add(imageLabel);

        panel1.add(label0);
        panel2.add(label1);
        panel2.add(label2);
        panel2.add(label3);
        panel2.add(label4);
        panel2.add(text1);
        panel2.add(text2);
        panel2.add(text3);
        panel2.add(scrollPane);
        panel4.add(scrollPane1);

    }

    void button() {

        String[] category = {"카테고리를 선택하세요", "학과명"};
        JComboBox comboBox1 = new JComboBox<>(category);
        comboBox1.setBounds(70, 20, 150, 24);
        comboBox1.setFont(new Font("나눔고딕", Font.PLAIN, 12));

        JTextField textField = new JTextField();
        textField.setBounds(260, 20, 150, 24);

        JButton btn1 = new JButton("조회");
        btn1.setBounds(450, 20, 100, 24);
        btn1.setFont(new Font("나눔고딕", Font.BOLD, 12));

        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedcategory = (String) comboBox1.getSelectedItem();
                String text = textField.getText();

                if (selectedcategory.equals("학과명")) {
                    userDAO.categorySearchByDeptName(text);
                }

            }
        });

        JButton btn2 = new JButton("등록");
        btn2.setBounds(40, 30, 100, 50);
        btn2.setFont(new Font("나눔고딕", Font.BOLD, 12));

        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String deptCode = text1.getText();
                String deptName = text2.getText();
                String majorName = text3.getText();

                userDAO.deptDataInsert(deptCode, deptName, majorName);
            }
        });

        JButton btn3 = new JButton("수정");
        btn3.setBounds(200, 30, 100, 50);
        btn3.setFont(new Font("나눔고딕", Font.BOLD, 12));

        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String deptCodeToUpdate = text1.getText();
                String deptNameToUpdate = text2.getText();
                String majorNameToUpdate = text3.getText();

                userDAO.updateDeptInfo(deptCodeToUpdate, deptNameToUpdate, majorNameToUpdate);

            }
        });

        JButton btn4 = new JButton("삭제");
        btn4.setBounds(360, 30, 100, 50);
        btn4.setFont(new Font("나눔고딕", Font.BOLD, 12));

        btn4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String deptCodeToDelete = text1.getText();
                String deptNameToDelete = text2.getText();
                String majorNameToDelete = text3.getText();

                userDAO.deleteDeptInfo(deptCodeToDelete, deptNameToDelete, majorNameToDelete);


            }
        });


        JButton btn5 = new JButton("종료");
        btn5.setBounds(500, 30, 100, 50);
        btn5.setFont(new Font("나눔고딕", Font.BOLD, 12));

        btn5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        panel3.add(comboBox1);
        panel3.add(textField);
        panel3.add(btn1);
        panel5.add(btn2);
        panel5.add(btn3);
        panel5.add(btn4);
        panel5.add(btn5);
    }

    public static void main(String[] args) throws BadLocationException, SQLException {
        new DeptForm();
    }
}
