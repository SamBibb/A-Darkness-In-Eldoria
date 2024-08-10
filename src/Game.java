import java.util.*;

public class Game
{
    private StoryNode currentNode;
    private Scanner input;
    private Character playerCharacter;

    private Map<String, StoryNode> storyNodes;


    public Game(Character playerCharacter)
    {
        this.playerCharacter = playerCharacter;
        storyNodes = new HashMap<>();
        this.input = new Scanner(System.in);


        // add story nodes
        StoryNode startNode = new StoryNode("You are at the beginning of your story. Would you like to start?");

        StoryNode beginNode = new StoryNode("You hear the town alarm bells and you realise the town is under attack. You need to get to the town before the attackers get there. Would you like to run to the barracks or head to the gatehouse? ");

        StoryNode barracksNode = new StoryNode("You make it to the barracks and the captain of the guard addresses you. The enemy have breached the wall! We're headed there now to push them back! Would you like to go with the captain or try to find your way out of the town?");

        StoryNode gatehouseNode = new StoryNode("You make it to the gatehouse and find the enemy have broken down the gate to the town and are attacking the guard. A frenzied cultist notices you and charges, defend yourself!");
        Enemy cultist = new Enemy("Cultist", 100,20,5,5,25, 50);
        gatehouseNode.setCombatScenario(cultist);

        StoryNode breachNode = new StoryNode("");

        StoryNode leaveTownNode = new StoryNode("");

        //add node transitions
        startNode.addTransition("start", beginNode);

        //beginNode transitions
        beginNode.addTransition("barracks", barracksNode);
        beginNode.addTransition("gatehouse", gatehouseNode);

        //barracksNode transitions
        barracksNode.addTransition("breach", breachNode);
        barracksNode.addTransition("leave", leaveTownNode);
        currentNode = startNode;
    }



    public void processCommand(String command)
    {
        switch (command)
        {
            case "inventory" :
                playerCharacter.getInventory().displayInventory();
                break;
            case "quit":
                System.exit(0);
                break;
            default:
                StoryNode nextNode = currentNode.getNextNode(command);
                if (nextNode != null)
                {
                    currentNode = nextNode;
                } else
                {
                    System.out.println("Invalid command");
                }
                break;
        }
    }

    public String getCurrentNodeDescription()
    {
        return currentNode.getDescription();
    }

    public StoryNode getCurrentNode()
    {
        return currentNode;
    }

    public List<String> getCurrentNodeOptions()
    {
        return new ArrayList<>(currentNode.getTransitions().keySet());
    }

    public List<String> getPlayerInventory()
    {
        List<String> inventory = new ArrayList<>();
        for (Item item : playerCharacter.getInventory().getItems())
        {
            inventory.add(item.getName());
        }
        return inventory;
    }

}