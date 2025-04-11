import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

/*
 <applet code="VotingApplet.class" width="600" height="400">
    </applet>
 */

public class VotingApplet extends Applet implements ActionListener {
   private java.util.Map<string, Integer>Votes;
   private CheckBoxGroup group;
   private Button submitButton;
   private ChartCanvas chartCanvas;

   class chartCanvas extends Canvas {
      private Color[] color = {Color.YELLOW, Color.BLUE, Color.RED, Color.GREEN};
      
      public void paint(Graphics g) {
         super.paint(g);
         int startY = 50;
         int barHeight = 20;
         int MaxBarWidth = 300;
         int totalVotes = votes.values().stream().mapToInt(Integer::intValue).sum();

         int colorIndex = 0;
         for (java.util.Map.Entry<String, Integer> entry : votes.entrySet()) {
            String platform = entry.getKey();
            int count = entry.getValue();
            int percentage = totalVotes == 0 ? 0: (count * 100) / totalVotes;
            int barWidth = (percentage * maxBarWidth) / 100;

            g.setColor(Color.BLUE);
            g.drawString(platform, 20, startY);

            g.setColor(colors[colorIndex % color.length]);
            g.fillRect(150, startY - barHeight, barWidth, barHeight);

            g.setColor(Color.BLACK);
            g.drawString(percentage + "% ("+ count + votes")", 160 + maxBarWidth, startY);

            startY += 30;
            colorIndex++;

         }
      }
   }

   public void init() {
      setLayout(new BorderLayout());
      setBackground(Color.lightGray);

      String[] platforms = {"TikTok", "Instagram", "Snapchat", "Twitter(X)", "Youtube"};
      votes = new java.util.LinkedHashMap<>();

      for (String platform:platforms) {
         votes.put(platform, 0);
      }

      add(newLabel("What is your favourite social media platform?"), BorderLayout.NORTH);

      Panel OptionsPanel = new Panel(new GridLayout(5,1));
      group = new CheckboxGroup();
      for (String platform : platforms) {
         OptionsPanel.add(new Checkbox(platform, group, false));
      }
      add(optionsPanel , BorderLayout.WEST);

      chartCanvas = new ChartCanvas();
      chartCanvas.setBackground(Color.WHITE);
      add(chartCanvas, BorderLayout.CENTER);

      submitButton = new Button("Submit Vote");
      submitButton.addActionListerner(this);
      add(submitButton, BorderLayout.SOUTH);

      setSize(600, 400);
   }

   public void actionPerformed(ActionEvent e) {
      Checkbox selected = group.getSelectedCheckbox();
      if (selected != null) {
         votes.put(selected.getLabel(), votes.get(selected.getLabel()) + 1);
         chartCanvas.repaint();
         group.getSelectedCheckbox(null);
      }
   }
 }