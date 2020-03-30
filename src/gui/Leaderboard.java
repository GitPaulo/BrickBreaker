package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTable;
import java.awt.Insets;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.table.DefaultTableModel;

import core.StorageHandler;
import game.Player;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Leaderboard extends JFrame {
	// Vars
    private StorageHandler sh = new StorageHandler("scoredata.txt");
    private Object[][] leaderboard;
    private Object[] leaderboard_columns = { "ID", "Gamertag", "Score", "Difficulty" };
    private Player player;
    private JPanel contentPane;
    private JTable table;
    private JLabel lblTitle;
    private JButton btnFindMe;
    private JScrollPane scrollPane;

    public Leaderboard(Player player) {
        this.player = player;
        this.leaderboard = sh.getLeaderboardData();
        
        if (leaderboard == null) {
            throw new Error("Problem initialising the leaderboard");
        }
    
        initComponents();
        saveLeaderboard();
        appear();
    }

    private void appear() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(100, 100, 645, 400);
    }
    
    private void saveLeaderboard() {
        if (!player.isSaveStats())
            return;

        Object[][] data = new Object[table.getModel().getRowCount()][table.getModel().getColumnCount()];
        
        for (int i = 0; i < table.getModel().getRowCount(); i++) {
            for (int j = 0; j < table.getModel().getColumnCount(); j++) {
                data[i][j] = table.getModel().getValueAt(i, j);
            }
        }

        sh.saveLeaderboardData(data);
    }

    private void initComponents() {
        // Layout: Panel
        GridBagLayout gbl_contentPane = new GridBagLayout();
        gbl_contentPane.columnWidths = new int[] { 619, 0 };
        gbl_contentPane.rowHeights = new int[] { 27, 144, 142, 23, 0 };
        gbl_contentPane.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
        gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
        
        // Panel
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(gbl_contentPane);
        
        // Layout: Leaderboard Title
        GridBagConstraints gbc_lblTitle = new GridBagConstraints();
        gbc_lblTitle.insets = new Insets(0, 0, 5, 0);
        gbc_lblTitle.gridx = 0;
        gbc_lblTitle.gridy = 0;

        // Leaderboard Title
        lblTitle = new JLabel("Leaderboard");
        lblTitle.setFont(new Font("Microsoft JhengHei UI Light", Font.PLAIN, 20));
        contentPane.add(lblTitle, gbc_lblTitle);
        
        // Layout: Scroll Panel
        GridBagConstraints gbc_scrollPane = new GridBagConstraints();
        gbc_scrollPane.gridheight = 2;
        gbc_scrollPane.fill = GridBagConstraints.BOTH;
        gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
        gbc_scrollPane.gridx = 0;
        gbc_scrollPane.gridy = 1;
        
        // Scroll panel
        scrollPane = new JScrollPane();
        contentPane.add(scrollPane, gbc_scrollPane);
        
        // Leaderboard Table
        table = new JTable();
        table.setAutoCreateRowSorter(true);
        scrollPane.setViewportView(table);
        DefaultTableModel model = new DefaultTableModel(leaderboard, leaderboard_columns);
        model.addRow(new Object[] { leaderboard.length, player.getGamertag(), player.getScore(),
                player.getDifficulty().toString() });
        table.setModel(model);
        
        // Layout: Button
        GridBagConstraints gbc_btnFindMe = new GridBagConstraints();
        gbc_btnFindMe.fill = GridBagConstraints.HORIZONTAL;
        gbc_btnFindMe.gridx = 0;
        gbc_btnFindMe.gridy = 3;
        
        // Button 'Find Me'
        btnFindMe = new JButton("Find Me");
        btnFindMe.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                int needed_row = 0;
                for (int i = 0; i < table.getModel().getRowCount(); i++) {
                    if ((int) model.getValueAt(i, 0) == model.getRowCount() - 1) {
                        needed_row = i;
                    }
                }
                model.moveRow(needed_row, needed_row, 0);
                table.setRowSelectionInterval(0, 0);
            }
        });
        contentPane.add(btnFindMe, gbc_btnFindMe);
    }
}
