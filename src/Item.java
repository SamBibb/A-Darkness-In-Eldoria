public class Item
{
    private String name;
    private int healthModifier;
    private int meleeAttackModifier;
    private int meleeDefenceModifier;
    private int spellAttackModifier;
    private int spellDefenceModifier;

    public Item(String name, int healthModifier, int meleeAttackModifier, int meleeDefenceModifier, int spellAttackModifier, int spellDefenceModifier)
    {
        this.name = name;
        this.healthModifier = healthModifier;
        this.meleeAttackModifier = meleeAttackModifier;
        this.meleeDefenceModifier = meleeDefenceModifier;
        this.spellAttackModifier = spellAttackModifier;
        this.spellDefenceModifier = spellDefenceModifier;
    }

    public String getName()
    {
        return name;
    }

    public int getHealthModifier() {
        return healthModifier;
    }

    public int getMeleeAttackModifier() {
        return meleeAttackModifier;
    }

    public int getMeleeDefenceModifier() {
        return meleeDefenceModifier;
    }

    public int getSpellAttackModifier() {
        return spellAttackModifier;
    }

    public int getSpellDefenceModifier() {
        return spellDefenceModifier;
    }
}
