import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class GameGUI implements CombatUpdateListener
{
    private JFrame frame;
    private JTextArea storyTextArea;
    private JTextArea inventoryTextArea;
    private JButton startCombatButton;
    private JPanel nodeOptionsPanel;
    private JPanel combatButtonPanel;
    private JPanel cardPanel;
    private JPanel inputPanel;
    private Game game;
    private Character playerCharacter;
    private Combat currentCombat;

    public GameGUI()
    {

        frame = new JFrame("The Darkness in Eldoria...");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        Container container = frame.getContentPane();
        container.setLayout(new BorderLayout());

        storyTextArea = new JTextArea();
        storyTextArea.setEditable(false);
        storyTextArea.setLineWrap(true);
        storyTextArea.setWrapStyleWord(true);
        storyTextArea.setPreferredSize(new Dimension(400, 200));
        storyTextArea.setFont(new Font("Serif", Font.PLAIN, 14));
        storyTextArea.setMargin(new Insets(10, 10, 10, 10));

        JScrollPane storyScrollPane = new JScrollPane(storyTextArea);
        container.add(storyScrollPane, BorderLayout.CENTER);

        inventoryTextArea = new JTextArea();
        inventoryTextArea.setEditable(false);
        inventoryTextArea.setLineWrap(true);
        inventoryTextArea.setWrapStyleWord(true);
        inventoryTextArea.setFont(new Font("Serif", Font.PLAIN, 14));
        inventoryTextArea.setMargin(new Insets(10, 10, 10, 10));
        JScrollPane inventoryScrollPane = new JScrollPane(inventoryTextArea);
        container.add(inventoryScrollPane, BorderLayout.EAST);

        cardPanel = new JPanel();
        cardPanel.setLayout(new CardLayout());

        nodeOptionsPanel = new JPanel();
        nodeOptionsPanel.setLayout(new BoxLayout(nodeOptionsPanel, BoxLayout.Y_AXIS));
        cardPanel.add(nodeOptionsPanel, "nodeOptions");

        inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());

        startCombatButton = new JButton("Begin battle!");
        startCombatButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
             if (game.getCurrentNode().hasCombat())
                {
                initiateCombat(game.getCurrentNode().getEnemy());
                }
            }
        });
        inputPanel.add(startCombatButton, BorderLayout.NORTH);

        cardPanel.add(inputPanel, "inputPanel");

        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.add(nodeOptionsPanel, BorderLayout.CENTER);
        southPanel.add(inputPanel, BorderLayout.SOUTH);

        container.add(cardPanel, BorderLayout.SOUTH);
        container.add(southPanel, BorderLayout.SOUTH);


        combatButtonPanel = new JPanel();
        combatButtonPanel.setLayout(new FlowLayout());
        container.add(combatButtonPanel, BorderLayout.NORTH);


        nodeOptionsPanel.setVisible(true);
        inputPanel.setVisible(true);

        frame.setVisible(true);

        chooseClass();

    }

    private void chooseClass()
    {
        String[] options = {"Mage", "Warrior", "Druid"};
        String selectedClass = (String) JOptionPane.showInputDialog(
                frame,
                "Choose your class:",
                "Class Selection",
                JOptionPane.PLAIN_MESSAGE,
                null,
                options,
                options[0]
        );

        if (selectedClass != null)
        {
            switch (selectedClass)
            {
                case "Mage":
                    playerCharacter = new Mage();
                    break;
                case "Warrior":
                    playerCharacter = new Warrior();
                    break;
                case "Druid":
                    playerCharacter = new Druid();
                    break;
                default:
                    playerCharacter = new Mage();
            }
            game = new Game(playerCharacter);
            updateStory();
        } else
        {
            System.exit(0);
        }
    }

    private void handleCommand(String command)
    {
        if (currentCombat != null)
        {
            currentCombat.performAction(command);
            updateStory();
            updateCombatButtons();
            updateInventory();
        } else if (command.equalsIgnoreCase("inventory"))
        {
            updateInventory();
        } else if (game.getCurrentNode().hasCombat())
        {
            initiateCombat(game.getCurrentNode().getEnemy());
        } else {
            game.processCommand(command);
            updateStory();
        }
    }

    private void initiateCombat(Enemy enemy)
    {
        currentCombat = new Combat(playerCharacter, enemy, this);
        currentCombat.start();
        startCombatButton.setVisible(false);
        updateStory();
        updateInventory();
        updateCombatButtons();
    }


    private void updateStory()
    {
        storyTextArea.setText(game.getCurrentNodeDescription());

        nodeOptionsPanel.removeAll();
        List<String> options = game.getCurrentNodeOptions();
        for (String option : options)
        {
            JButton button = new JButton(option);
            button.addActionListener(e -> handleCommand(option));
            nodeOptionsPanel.add(button);
        }
        nodeOptionsPanel.revalidate();
        nodeOptionsPanel.repaint();

        if (game.getCurrentNode().hasCombat())
        {
            startCombatButton.setVisible(true); // Show button if there is combat
        } else
        {
            startCombatButton.setVisible(false); // Hide button if no combat
        }

        // Switch panels based on game state
        CardLayout cl = (CardLayout) (cardPanel.getLayout());
        if (currentCombat != null)
        {
            cl.show(cardPanel, "inputPanel");
            updateCombatButtons();
        } else
        {
            cl.show(cardPanel, "nodeOptions");
        }

    }

    public void updateInventory()
    {
        inventoryTextArea.setText("Inventory:\n");
        for (String item : game.getPlayerInventory())
        {
            inventoryTextArea.append("- " + item + "\n");
        }
        inventoryTextArea.append("\n" + playerCharacter.getActionResourceName() + ": " + playerCharacter.getActionResourceAmount());
    }

    private void updateCombatButtons()
    {
        combatButtonPanel.removeAll();
        List<String> combatOptions = playerCharacter.getCombatOptions();
        for (String option : combatOptions)
        {
            JButton button = new JButton(option);
            button.addActionListener(e ->
            {
                if (currentCombat != null)
                {
                    currentCombat.performAction(option);
                    updateStory();
                    updateInventory();
                    updateCombatButtons();
                }
            });
            combatButtonPanel.add(button);
        }
        combatButtonPanel.revalidate();
        combatButtonPanel.repaint();
    }

    @Override
    public void updateCombatMessage(String message)
    {
        SwingUtilities.invokeLater(() ->
        {
            storyTextArea.append(message + "\n ");
            storyTextArea.setCaretPosition(storyTextArea.getDocument().getLength()); // Scroll to the bottom
        });
    }

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(GameGUI::new);
    }
}
