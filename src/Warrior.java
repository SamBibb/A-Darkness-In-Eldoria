import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Warrior extends Character
{
    public Warrior()
    {
        super("Warrior", 100, 15,5, 10,5,0,0,0,5);
        this.actionResource = 5;
        getInventory().addItem(new Item("Long Sword", 0,10,0,0, 0));
        initialiseCombatActions();
    }

    @Override
    public void initialiseCombatActions() {
        addCombatAction("Quick strike", new QuickStrikeAction());
        addCombatAction("Sword block", new SwordBlockAction());
    }

    @Override
    public String getActionResourceName()
    {
        return "Stamina";
    }

    @Override
    public int getActionResourceAmount()
    {
        return actionResource;
    }

    @Override
    public void reduceActionResource(int cost) {
        setActionResource(getActionResource() - cost);
    }

}
