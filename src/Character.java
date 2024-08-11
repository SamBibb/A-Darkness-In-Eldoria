import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public abstract class Character
{
    private String name;
    private int health;
    private int meleeAttack;
    private int spellAttack;
    private int meleeDefence;
    private int spellDefence;
    private int experience;
    public int actionResource;
    public int initialActionResource;
    private int level;
    private Inventory inventory;
    private int tempMeleeDefenceBuff = 0;
    private int tempSpellDefenceBuff = 0;
    private int tempMeleeAttackBuff = 0;
    private int tempSpellAttackBuff = 0;




    public Character(String name, int health, int meleeAttack, int spellAttack, int meleeDefence, int spellDefence, int experience, int level, int actionResource, int initialActionResource)
    {
        this.name = name;
        this.health = health;
        this.meleeAttack = meleeAttack;
        this.spellAttack = spellAttack;
        this.meleeDefence = meleeDefence;
        this.spellDefence = spellDefence;
        this.experience = experience;
        this.level = level;
        this.inventory = new Inventory();
        this.actionResource = actionResource;
        this.initialActionResource = initialActionResource;
        initialiseCombatActions();
    }

    public abstract String getActionResourceName();
    public abstract int getActionResourceAmount();
//    public abstract void reduceActionResource(int cost);


    //getters
    public String getName()
    {
        return name;
    }

    public int getActionResource()
    {
        return actionResource;
    }

    public int getInitialActionResource() {
        return initialActionResource;
    }

    public int getHealth()
    {
        return health;
    }

    public int getMeleeAttack()
    {
        return meleeAttack;
    }

    public int getSpellAttack()
    {
        return spellAttack;
    }

    public int getSpellDefence()
    {
        return spellDefence;
    }

    public int getMeleeDefence()
    {
        return meleeDefence;
    }

    public int getExperience()
    {
        return experience;
    }

    public int getLevel()
    {
        return level;
    }


    public Inventory getInventory()
    {
        return inventory;
    }

    public int getEffectiveHealth()
    {
        return health + inventory.getTotalHealthModifier();
    }

    public int getEffectiveMeleeAttack()
    {
        return meleeAttack + inventory.getTotalMeleeAttackModifier() + tempMeleeAttackBuff;
    }

    public int getEffectiveMeleeDefence()
    {
        return meleeDefence + inventory.getTotalMeleeDefenceModifier() + tempMeleeDefenceBuff;
    }

    public int getEffectiveSpellAttack()
    {
        return spellAttack + inventory.getTotalSpellAttackModifier() + tempSpellAttackBuff;
    }

    public int getEffectiveSpellDefence()
    {
        return spellDefence + inventory.getTotalSpellDefenceModifier() + tempSpellDefenceBuff;
    }





    //setters
    public void setName(String name)
    {
        this.name = name;
    }

    public void setHealth(int health)
    {
        this.health = health;
    }

    public void setMeleeAttack(int meleeAttack)
    {
        this.meleeAttack = meleeAttack;
    }

    public void setSpellAttack(int spellAttack)
    {
        this.spellAttack = spellAttack;
    }

    public void setMeleeDefence(int meleeDefence)
    {
        this.meleeDefence = meleeDefence;
    }

    public void setSpellDefence(int spellDefence)
    {
        this.spellDefence = spellDefence;
    }

    public void setExperience(int experience)
    {
        this.experience = experience;
    }

    public void setLevel(int level)
    {
        this.level = level;
    }

    public void setActionResource(int amount)
    {
        System.out.println("Setting action resource to " + amount);
        this.actionResource = amount;
    }


    public void setTempMeleeDefenceBuff(int buff)
    {
        this.tempMeleeDefenceBuff = buff;
    }

    public void resetTempMeleeDefenceBuff()
    {
        this.tempMeleeDefenceBuff = 0;
    }

    public void setTempSpellDefenceBuff(int buff)
    {
        this.tempSpellDefenceBuff = buff;
    }

    public void resetTempSpellDefenceBuff()
    {
        this.tempSpellDefenceBuff = 0;
    }

    public void setTempMeleeAttackBuff(int buff)
    {
        this.tempMeleeAttackBuff = buff;
    }

    public void resetTempMeleeAttackBuff()
    {
        this.tempMeleeAttackBuff = 0;
    }

    public void setTempSpellAttackBuff(int buff)
    {
        this.tempSpellAttackBuff = buff;
    }

    public void resetTempSpellAttackBuff()
    {
        this.tempSpellAttackBuff = 0;
    }

    public void reduceActionResource(int amount)
    {
        System.out.println("Reducing action resource by " + amount);
        actionResource -= amount;
    }



    public void resetActionResource(Character player)
    {
        this.actionResource = initialActionResource;
    }


    private Map<String, CombatAction> combatActions = new HashMap<>();

    public abstract void initialiseCombatActions();




    public String performCombatAction(String action, Enemy enemy)
    {
        CombatAction combatAction = combatActions.get(action);
        if (combatAction != null)
        {
            int resourceCost = combatAction.getCost();
            if (resourceCost > getActionResourceAmount())
            {
                return "Not enough " + getActionResourceName() + " to perform " + action;
            }
            combatAction.execute(this, enemy);
            return combatAction.getMessage(this, enemy);
        } else
        {
            throw new IllegalArgumentException("Action not available");
        }
    }

    protected void addCombatAction(String actionName, CombatAction action)
    {
        combatActions.put(actionName, action);
    }

    public List<String> getCombatOptions()
    {
        return new ArrayList<>(combatActions.keySet());
    }

    public void reduceHealth(int amount)
    {
        this.health -= amount;
        if (this.health < 0)
        {
            this.health = 0;
        }
    }



}