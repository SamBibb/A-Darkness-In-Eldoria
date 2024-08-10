import java.util.HashMap;
import java.util.Map;

public class StoryNode
{
    private String description;
    private Map<String, StoryNode> transitions;
    private Enemy enemy;

    public StoryNode(String description)
    {
        this.description = description;
        this.transitions = new HashMap<>();
    }

    public void setCombatScenario(Enemy enemy)
    {
        this.enemy = enemy;
    }

    public boolean hasCombat()
    {
        return enemy != null;
    }

    public Enemy getEnemy()
    {
        return enemy;
    }



    public String getDescription()
    {
        return description;
    }

    public void addTransition(String command, StoryNode node)
    {
        transitions.put(command, node);
    }

    public StoryNode getNextNode(String command)
    {
        return transitions.get(command);
    }

    public Map<String, StoryNode> getTransitions()
    {
        return transitions;
    }
}