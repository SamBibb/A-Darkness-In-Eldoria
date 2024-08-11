public class BarrierAction implements CombatAction
{

    private static final int COST = 1;

    @Override
    public void execute(Character player, Enemy enemy)
    {
        player.reduceActionResource(COST);
        player.setTempSpellDefenceBuff(5);
    }

    @Override
    public String getMessage(Character player, Enemy enemy)
    {
        return "You cast barrier, increasing your spell defense!";
    }

    @Override
    public int getCost()
    {
        return COST;
    }
}
