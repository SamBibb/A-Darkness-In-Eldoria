import java.util.Random;

public class Combat
{
    private Character player;
    private Enemy enemy;
    private CombatUpdateListener listener;

    public Combat(Character player, Enemy enemy, CombatUpdateListener listener)
    {
        this.player = player;
        this.enemy = enemy;
        this.listener = listener;
    }

    public void start()
    {
        listener.updateCombatMessage("Combat started against " + enemy.getName());
        updateCombatStatus();
    }

    public void performAction(String action)
    {
        System.out.println("Performing action: " + action);
        System.out.println("Action resource amount: " + player.getActionResourceAmount());
        try
        {
                if (player.getActionResourceAmount() > 0)
                {
                    String actionResult = player.performCombatAction(action, enemy);
                    listener.updateCombatMessage(actionResult);

                    if (enemy.getHealth() <= 0)
                    {
                        listener.updateCombatMessage("You defeated " + enemy.getName());
                        return;
                    }

                    System.out.println("Player's defence is: " + player.getEffectiveMeleeDefence());
                    System.out.println("Player's health is: " + player.getEffectiveHealth());
                    System.out.println("Player's action resource is: " + player.getActionResourceAmount());


                    // Check if player has any action resource left
                    if (player.getActionResourceAmount() <= 0)
                    {
                        listener.updateCombatMessage("You have used all your " + player.getActionResourceName());
                        enemyTurn(); // Now the enemy takes its turn after the player exhausts their resources
                        resetPlayerActionResource(); // Reset player action resource for the next turn
                    }
                } else
                {
                    listener.updateCombatMessage("You have exhausted your " + player.getActionResourceName());
                    enemyTurn();
                    resetPlayerActionResource();
                }

                    if (player.getHealth() <= 0)
                    {
                        listener.updateCombatMessage("You were defeated by " + enemy.getName());
                        //            currentCombat = null; // You can uncomment this to end combat
                    }


                updateCombatStatus();
                resetBuffs();
        } catch (IllegalArgumentException e)
        {
            listener.updateCombatMessage(e.getMessage());
        }
    }


    private void resetPlayerActionResource()
    {
        System.out.println("Resetting player action resource.");
        player.resetActionResource(player);
    }

    private void resetBuffs()
    {
        // Reset player buffs
        player.resetTempMeleeAttackBuff();
        player.resetTempMeleeDefenceBuff();
        player.resetTempSpellAttackBuff();
        player.resetTempSpellDefenceBuff();
        // Reset enemy buffs
        enemy.resetTempMeleeAttackBuff();
        enemy.resetTempMeleeDefenceBuff();
        enemy.resetTempSpellDefenceBuff();
        enemy.resetTempSpellAttackBuff();
    }

    private void enemyTurn()
    {
        Random rand = new Random();
        int action = rand.nextInt(3);

        switch (action)
        {
            case 0:
                enemyAttack();
                break;
            case 1:
                enemyDefend();
                break;
            case 2:
                enemySpecial();
                break;
        }
    }

    private void enemyAttack()
    {
        int effectiveAttack = enemy.getMeleeAttack();
        int effectivePlayerDefence = player.getEffectiveMeleeDefence();
        int damage = effectiveAttack - effectivePlayerDefence;
        if (damage > 0)
        {
            player.reduceHealth(damage);
            listener.updateCombatMessage("The " + enemy.getName() + " strikes! You take " + damage + " damage!");
            System.out.println("Damage is: " + damage + " Enemy effective attack is: " + effectiveAttack + " Player defence is: " + effectivePlayerDefence);
        }
    }

    private void enemyDefend()
    {
        listener.updateCombatMessage("The enemy braces for your attack!");
        enemy.setMeleeDefence(enemy.getMeleeDefence() + 5);
        System.out.println("The enemy melee defence is: " + enemy.getMeleeDefence());
    }

    private void enemySpecial()
    {
        listener.updateCombatMessage("The enemy uses a special move!");
        int damage = enemy.getMeleeAttack() + 10;
        player.reduceHealth(damage);
        System.out.println("Enemy damage is: " + damage);
    }

    private void updateCombatStatus()
    {
        listener.updateCombatMessage("Your health: " + player.getEffectiveHealth());
        listener.updateCombatMessage(enemy.getName() + "'s health: " + enemy.getHealth());
    }
}
