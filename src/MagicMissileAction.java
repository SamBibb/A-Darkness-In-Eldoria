public class MagicMissileAction implements CombatAction
{

    private static final int COST = 1;

    @Override
    public void execute(Character player, Enemy enemy)
    {
        player.reduceActionResource(COST);
        int damage = (player.getEffectiveSpellAttack() - 5) - enemy.getSpellDefence();
        enemy.setHealth(enemy.getHealth() - damage);
    }

    @Override
    public String getMessage(Character player, Enemy enemy)
    {
        int damage = player.getEffectiveSpellAttack() - enemy.getSpellDefence();
        return "Frost bolt dealt " + damage + " Damage!";
    }

    @Override
    public int getCost()
    {
        return COST;
    }
}