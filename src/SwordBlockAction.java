public class SwordBlockAction implements CombatAction
{

    private static final int COST = 1;

    @Override
    public void execute(Character player, Enemy enemy)
    {
        player.reduceActionResource(COST);
        player.setTempMeleeDefenceBuff(5);
    }

    @Override
    public String getMessage(Character player, Enemy enemy)
    {
        return "You performed a Sword Block, increasing your defense!";

    }

    @Override
    public int getCost()
    {
        return COST;
    }
}
