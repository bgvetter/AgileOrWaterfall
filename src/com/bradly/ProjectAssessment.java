package com.bradly;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by sylentbv on 3/25/2017.
 */
public class ProjectAssessment extends  JFrame{


    private JPanel rootPanel;
    private JTextField projectNameText;
    private JTextField numProgrammersText;
    private JButton determineMethodologyButton;
    private JCheckBox firmDeadlinesCheckBox;
    private JCheckBox experienceCheckBox;
    private JCheckBox qualityControlCheckBox;
    private JCheckBox integrationCheckBox;
    private JCheckBox workingModelsCheckBox;
    private JLabel recommendationLabel;
    private JButton quitButton;

    private boolean fdead;
    private boolean exp;
    private boolean qual;
    private boolean integ;
    private boolean model;

    public ProjectAssessment() {
        super("Project Methodology Assessment");
        setContentPane(rootPanel);
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(new Dimension(500, 600));
        setVisible(true);

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int quit = JOptionPane.showConfirmDialog(ProjectAssessment.this,"Are you sure you want to exit?",
                        "Quit",JOptionPane.OK_CANCEL_OPTION);
                if(quit==JOptionPane.OK_OPTION) {
                    System.exit(0);
                }
            }
        });
        determineMethodologyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String projectName = projectNameText.getText();
                String numProgrammers = numProgrammersText.getText();
                try{
                    int numProg = Integer.parseInt(numProgrammers);
                    String result = DetermineMethod(numProg,fdead,exp,qual,integ,model);
                    recommendationLabel.setText("We recommend the " + result + " method for project "+
                            projectName);
                }
                catch (NumberFormatException nfe)
                {
                    JOptionPane.showMessageDialog(ProjectAssessment.this,
                            "Please only enter numeric values!");
                    numProgrammersText.selectAll();
                    numProgrammersText.requestFocus();
                }

            }
        });


        firmDeadlinesCheckBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                fdead=firmDeadlinesCheckBox.isSelected();
            }
        });
        experienceCheckBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                exp=experienceCheckBox.isSelected();
            }
        });
        qualityControlCheckBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                qual=qualityControlCheckBox.isSelected();
            }
        });
        integrationCheckBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                integ=integrationCheckBox.isSelected();
            }
        });
        workingModelsCheckBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                model=workingModelsCheckBox.isSelected();
            }
        });
    }

    private static String DetermineMethod(int iNumProgrammers, boolean bFirmDeadlines, boolean bExperiencedProgs, boolean bStringentQuality, boolean bEarlyIntegration, boolean bEarlyModels) {
        String sResult = "";
        //use passed values to determine a suggestion
        if(iNumProgrammers<10 && !bFirmDeadlines &&
                bExperiencedProgs && !bStringentQuality &&
                bEarlyIntegration && bEarlyModels){
            sResult = "Agile";
        }
        else{
            sResult="Waterfall";
        }


        return sResult;
    }

}
