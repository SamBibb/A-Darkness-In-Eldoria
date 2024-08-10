public class Mage extends Character {
    public Mage() {
        super("Mage", 100, 5, 15, 5, 10, 0, 0, 0,5);
        getInventory().addItem(new Item("Magic Staff", 0, 0, 0, 10, 10));
        getInventory().addItem(new Item("Spell Book", 10, 0, 0, 10, 10));
        initialiseCombatActions();
    }

    @Override
    public void initialiseCombatActions() {
        addCombatAction("Frost Bolt", new FrostBoltAction());
        addCombatAction("Barrier", new BarrierAction());
    }

    @Override
    public String getActionResourceName()
    {
        return "Mana";
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
