public interface CombatAction
{
    void execute(Character player, Enemy enemy);
    String getMessage(Character player, Enemy enemy);
    int getCost();
}
