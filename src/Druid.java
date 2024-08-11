import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Druid extends Character
{
    public Druid() {
        super("Druid", 100, 10, 10, 10, 10, 0, 0, 0, 5);
        this.actionResource = 5;
        getInventory().addItem(new Item("Hunting Knife", 0, 5, 0, 0, 0));
        getInventory().addItem(new Item("Magic Branch", 5, 0, 0, 5, 5));
        initialiseCombatActions();
    }

    @Override
    public void initialiseCombatActions()
    {
        addCombatAction("Quick strike", new QuickStrikeAction());
        addCombatAction("Sword block", new SwordBlockAction());
    }

    @Override
    public String getActionResourceName()
    {
        return "Essence";
    }

    @Override
    public int getActionResourceAmount()
    {
        return actionResource;
    }

    @Override
    public void reduceActionResource(int cost)
    {
        setActionResource(getActionResource() - cost);
    }

}
