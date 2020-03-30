package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import game.GameDifficulty;
import game.Player;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Start extends JFrame {
	// Vars
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textField;
    private JLabel lblTitle;
    private JLabel lblGamertag;
    private JLabel lblDifficulty;
    private JComboBox<GameDifficulty> comboBox;
    private JCheckBox chckbxSaveGameScore;
    private JButton btnStart;
    private JLabel lblByPaulo;

    public Start() {
        initComponents();
        appear();
    }

    private void appear() {
    	this.setContentPane(contentPane);
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(100, 100, 279, 256);
        this.setVisible(true);
        this.setTitle("Brick Breaker");
        this.setLocationRelativeTo(null);
    }
    
    private void initComponents() {
    	// Layout: Panel
    	GridBagLayout gbl_contentPane = new GridBagLayout();
        gbl_contentPane.columnWidths = new int[] { 0, 0 };
        gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        gbl_contentPane.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
        gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
    	
        // Panel
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(gbl_contentPane);
        
        // Layout: Title
        GridBagConstraints gbc_lblTitle = new GridBagConstraints();
        gbc_lblTitle.insets = new Insets(0, 0, 5, 0);
        gbc_lblTitle.gridx = 0;
        gbc_lblTitle.gridy = 1;
        
        // Title 
        lblTitle = new JLabel("Brick Breaker\r\n");
        lblTitle.setFont(new Font("Microsoft JhengHei UI Light", Font.PLAIN, 20));
        contentPane.add(lblTitle, gbc_lblTitle);
        
        // Layout: Sub Title
        GridBagConstraints gbc_lblByPaulo = new GridBagConstraints();
        gbc_lblByPaulo.insets = new Insets(0, 0, 5, 0);
        gbc_lblByPaulo.gridx = 0;
        gbc_lblByPaulo.gridy = 2;
        
        // Sub Title
        lblByPaulo = new JLabel("by Paulo");
        lblByPaulo.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 11));
        contentPane.add(lblByPaulo, gbc_lblByPaulo);
        
        // Layout: Gamertag
        GridBagConstraints gbc_lblGamertag = new GridBagConstraints();
        gbc_lblGamertag.anchor = GridBagConstraints.WEST;
        gbc_lblGamertag.insets = new Insets(0, 0, 5, 0);
        gbc_lblGamertag.gridx = 0;
        gbc_lblGamertag.gridy = 3;
        
        // Gamertag
        lblGamertag = new JLabel("Gamertag");
        contentPane.add(lblGamertag, gbc_lblGamertag);
        
        // Layout: Input
        GridBagConstraints gbc_textField = new GridBagConstraints();
        gbc_textField.insets = new Insets(0, 0, 5, 0);
        gbc_textField.fill = GridBagConstraints.HORIZONTAL;
        gbc_textField.gridx = 0;
        gbc_textField.gridy = 4;
        
        // Input
        textField = new JTextField();
        contentPane.add(textField, gbc_textField);
        textField.setColumns(10);
        
        // Layout: Difficulty Label
        GridBagConstraints gbc_lblDifficulty = new GridBagConstraints();
        gbc_lblDifficulty.anchor = GridBagConstraints.WEST;
        gbc_lblDifficulty.insets = new Insets(0, 0, 5, 0);
        gbc_lblDifficulty.gridx = 0;
        gbc_lblDifficulty.gridy = 5;
        
        // Difficulty Label
        lblDifficulty = new JLabel("Difficulty");
        contentPane.add(lblDifficulty, gbc_lblDifficulty);
        
        // Layout: Difficulty Combo Box
        GridBagConstraints gbc_comboBox = new GridBagConstraints();
        gbc_comboBox.insets = new Insets(0, 0, 5, 0);
        gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
        gbc_comboBox.gridx = 0;
        gbc_comboBox.gridy = 6;
        
        // Difficulty Combo Box
        comboBox = new JComboBox<GameDifficulty>();
        comboBox.setModel(new DefaultComboBoxModel<GameDifficulty>(GameDifficulty.values()));
        contentPane.add(comboBox, gbc_comboBox);
        
        // Layout: Save Check Box
        GridBagConstraints gbc_chckbxSaveGameScore = new GridBagConstraints();
        gbc_chckbxSaveGameScore.fill = GridBagConstraints.HORIZONTAL;
        gbc_chckbxSaveGameScore.insets = new Insets(0, 0, 5, 0);
        gbc_chckbxSaveGameScore.gridx = 0;
        
        // Save Check Box
        chckbxSaveGameScore = new JCheckBox("Save game score");
        chckbxSaveGameScore.setSelected(true);
        gbc_chckbxSaveGameScore.gridy = 7;
        contentPane.add(chckbxSaveGameScore, gbc_chckbxSaveGameScore);
        
        // Layout: Start Button
        GridBagConstraints gbc_btnStart = new GridBagConstraints();
        gbc_btnStart.fill = GridBagConstraints.BOTH;
        gbc_btnStart.insets = new Insets(0, 0, 5, 0);
        gbc_btnStart.gridx = 0;
        gbc_btnStart.gridy = 8;
        
        // Start Button
        btnStart = new JButton("Start");
        btnStart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (textField.getText().length() < 1) {
                    lblTitle.setText("Invalid Fields. Complete all!");
                    lblTitle.setForeground(Color.red);
                    return;
                }
                
                // Log start
                System.out.println("Game start!");
                System.out.println("Difficulty: " + comboBox.getSelectedItem());
                
                // Create game
                Game game = new Game(new Player(textField.getText(), chckbxSaveGameScore.isSelected(),
                        (GameDifficulty) comboBox.getSelectedItem()));
                game.setVisible(true);
                game.setTitle("Brick Breaker");
                game.setLocationRelativeTo(null);
                dispose();
            }
        });
        btnStart.setBackground(new Color(0, 255, 127));
        contentPane.add(btnStart, gbc_btnStart);
    }
}
