import java.util.ArrayList;
import java.util.List;

public class Inventory
{
    private List<Item> items;

    public Inventory()
    {
        this.items = new ArrayList<>();
    }

    public void addItem(Item item)
    {
        items.add(item);
    }

    public void removeItem(Item item)
    {
        items.remove(item);
    }

    public List<Item> getItems()
    {
        return items;
    }

    public void displayInventory()
    {
        System.out.println("Inventory: ");
        for (Item item : items)
        {
            System.out.println("- " + item.getName());
        }
    }

    public int getTotalHealthModifier()
    {
        return items.stream().mapToInt(Item::getHealthModifier).sum();
    }

    public int getTotalMeleeAttackModifier()
    {
        return items.stream().mapToInt(Item::getMeleeAttackModifier).sum();
    }

    public int getTotalMeleeDefenceModifier()
    {
        return items.stream().mapToInt(Item::getMeleeDefenceModifier).sum();
    }

    public int getTotalSpellAttackModifier()
    {
        return items.stream().mapToInt(Item::getSpellAttackModifier).sum();
    }

    public int getTotalSpellDefenceModifier()
    {
        return items.stream().mapToInt(Item::getSpellDefenceModifier).sum();
    }
}
