package org.dreambot.utilities.ui;

import org.dreambot.utilities.QuestBranch;

import javax.swing.*;

public class UserInterface {

    private static QuestBranch selectedItem;
    private static boolean startLoop = false;

    public UserInterface() {
        JFrame jFrame = new JFrame("DreamBotQuester");
        jFrame.setSize(400, 400);
        jFrame.setLocationRelativeTo(null);
        jFrame.setLayout(null);
        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JComboBox comboBox = new JComboBox();
        comboBox.setSize(300, 100);
        comboBox.setBounds((jFrame.getWidth() - comboBox.getWidth()) / 2,
                50, comboBox.getWidth(), comboBox.getHeight());

        for (QuestBranch questBranch : QuestBranch.values()) {
            comboBox.addItem(questBranch);
        }

        JButton button = new JButton();
        button.setText("Start");
        button.setSize(200, 100);
        button.setBounds((jFrame.getWidth() - button.getWidth()) / 2,
                200, button.getWidth(), button.getHeight());

        button.addActionListener(e -> {
            selectedItem = (QuestBranch) comboBox.getSelectedItem();
            jFrame.dispose();
            startLoop = true;
        });

        jFrame.add(comboBox);
        jFrame.add(button);
        jFrame.setVisible(true);
    }

    public static boolean isStartLoop() {
        return startLoop;
    }

    public static QuestBranch getSelectedItem() {
        return selectedItem;
    }
}