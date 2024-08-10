public class QuickStrikeAction implements CombatAction
{

    private static final int COST = 1;

    @Override
    public void execute(Character player, Enemy enemy)
    {
        player.reduceActionResource(COST);
        int damage = player.getEffectiveMeleeAttack() - enemy.getMeleeDefence();
        enemy.setHealth(enemy.getHealth() - damage);
    }

    @Override
    public String getMessage(Character player, Enemy enemy)
    {
        int damage = player.getEffectiveMeleeAttack() - enemy.getMeleeDefence();
        return "Quick strike dealt " + damage + " Damage!";
    }

    @Override
    public int getCost()
    {
        return COST;
    }
}
