public class Enemy
{
    private String name;
    private int health;
    private int meleeAttack;
    private int spellAttack;
    private int meleeDefence;
    private int spellDefence;
    private int experienceAdd;
    private int tempMeleeDefenceBuff = 0;
    private int tempSpellDefenceBuff = 0;
    private int tempMeleeAttackBuff = 0;
    private int tempSpellAttackBuff = 0;



    public Enemy(String name, int health, int meleeAttack, int spellAttack, int meleeDefence, int spellDefence, int experienceAdd)
    {
        this.name = name;
        this.health = health;
        this.meleeAttack = meleeAttack;
        this.spellAttack = spellAttack;
        this.meleeDefence = meleeDefence;
        this.spellDefence = spellDefence;
        this.experienceAdd = experienceAdd;

    }

    //getters
    public String getName()
    {
        return name;
    }

    public int getHealth()
    {
        return health;
    }

    public int getMeleeAttack()
    {
        return meleeAttack + tempMeleeAttackBuff;
    }

    public int getSpellAttack()
    {
        return spellAttack + tempSpellAttackBuff;
    }

    public int getSpellDefence()
    {
        return spellDefence + tempSpellDefenceBuff;
    }

    public int getMeleeDefence()
    {
        return meleeDefence + tempMeleeDefenceBuff;
    }

    public int getExperienceAdd()
    {
        return experienceAdd;
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

    public void setSpellAttack(int spellAttack) {
        this.spellAttack = spellAttack;
    }

    public void setMeleeDefence(int meleeDefence)
    {
        this.meleeDefence = meleeDefence;
    }

    public void setSpellDefence(int spellDefence) {
        this.spellDefence = spellDefence;
    }

    public void setExperienceAdd(int experienceAdd) {
        this.experienceAdd = experienceAdd;
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
}