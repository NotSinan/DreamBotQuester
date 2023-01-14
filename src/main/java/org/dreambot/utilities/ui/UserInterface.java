package org.dreambot.utilities.ui;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;
import org.dreambot.utilities.API;
import org.dreambot.utilities.QuestBranches;

public class UserInterface {

  public static boolean startLoop = false;
  public static boolean stopScript = false;
  private static JFrame jFrame;

  public UserInterface() {
    jFrame = new JFrame("DreamBotQuester");
    jFrame.setSize(400, 400);
    jFrame.setLocationRelativeTo(null);
    jFrame.setLayout(null);
    JComboBox comboBox = new JComboBox();
    comboBox.setSize(300, 100);
    comboBox.setBounds(
        (jFrame.getWidth() - comboBox.getWidth()) / 2,
        50,
        comboBox.getWidth(),
        comboBox.getHeight());

    for (QuestBranches questBranch : QuestBranches.values()) {
      comboBox.addItem(questBranch);
    }

    JButton button = new JButton();
    button.setText("Start");
    button.setSize(200, 100);
    button.setBounds(
        (jFrame.getWidth() - button.getWidth()) / 2, 200, button.getWidth(), button.getHeight());

    button.addActionListener(
        e -> {
          API.selectedQuest = (QuestBranches) comboBox.getSelectedItem();
          jFrame.dispose();
          startLoop = true;
        });

    jFrame.add(comboBox);
    jFrame.add(button);
    jFrame.addWindowListener(
        new WindowAdapter() {
          @Override
          public void windowClosing(WindowEvent e) {
            startLoop = true;
            stopScript = true;
            jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
          }
        });
    jFrame.setVisible(true);
  }

  public static void close() {
    jFrame.setVisible(false);
    jFrame.dispose();
  }
}
