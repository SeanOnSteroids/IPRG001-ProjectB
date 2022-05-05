import java.util.*;

public class Inventory
{
    private Spellbook[] spellbookCategories;
    private Scanner scn = new Scanner(System.in);

    public Inventory()
    {
        setupSpellbookCategories();
        menu();
    }

    private void menu()
    {
        char selectedActionKey = writeOptionsAndGetReadCharInput();
        
        while(selectedActionKey != 'Q')
        {
            scn = new Scanner(System.in);

            switch(selectedActionKey)
            {
                case 'A':
                {
                    viewSpellbookCategories();
                    break;
                }
                case 'B':
                {
                    setManaForSpellbookByName();
                    break;
                }
                case 'C':
                {
                    setManaForAllSpellbook();
                    break;
                }
                case 'D':
                {
                    setLightPrismPassiveStateForSpellbookByName();
                    break;
                }
                case 'E':
                {
                    setLightPrismPassiveStateForAllSpellbook();
                    break;
                }
                case 'F':
                {
                    castASpellFromASpellbook();
                    break;
                }
                case 'G':
                {
                    castASpellFromAllSpellbook();
                    break;
                }
                default:
                    break;
            }

            selectedActionKey = writeOptionsAndGetReadCharInput();
        }
    }

    private void setupSpellbookCategories()
    {
        spellbookCategories = new Spellbook[]
        {
            new Spellbook("Ruined King's Scroll", 50),
            new Spellbook("Trinity Pilgrim Caster Book", 100),
            new Spellbook("God's Papyrus", 1000)
        };
    }

    private void viewSpellbookCategories()
    {
        for(int spbkIndex = 0; spbkIndex < spellbookCategories.length; spbkIndex++)
        {
            Spellbook spellBook = spellbookCategories[spbkIndex];
            System.out.println("[" + spbkIndex + "] " + spellBook.getSpellbookName() + "'s propreties: " + spellBook.toString());
        }
    }

    private void setManaForSpellbookByName()
    {
        System.out.println("Please enter the name of spellbook to set mana for: ");
        String spbkNameToSearch = scn.nextLine();
        boolean isSpellBookFound = false;

        for(int spbkIndex = 0; spbkIndex < spellbookCategories.length; spbkIndex++)
        {
            Spellbook spellBook = spellbookCategories[spbkIndex];

            if(spellBook.getSpellbookName().equals(spbkNameToSearch))
            {
                isSpellBookFound = true;

                System.out.println("Please enter the mana amount to set for " + spbkNameToSearch + ": ");
                double newManaValue = scn.nextDouble();

                spellBook.setCurrentMana(newManaValue);
                System.out.println("Sucessfully set mana amount of " + newManaValue + " for " + spellBook.getSpellbookName());
            }
        }

        if(!isSpellBookFound)
            System.out.println("Failed to find the spellbook of name: " + spbkNameToSearch);
    }

    private void setManaForAllSpellbook()
    {
        System.out.println("Please enter the mana amount to set: ");
        double newManaValue = scn.nextDouble();

        for(int spbkIndex = 0; spbkIndex < spellbookCategories.length; spbkIndex++)
        {
            Spellbook spellBook = spellbookCategories[spbkIndex];
            spellBook.setCurrentMana(newManaValue);
            System.out.println("Sucessfully set mana amount of " + newManaValue + " for " + spellBook.getSpellbookName());
        }
    }

    private void setLightPrismPassiveStateForSpellbookByName()
    {
        System.out.println("Please enter the name of spellbook to set light prism effect state for: ");
        String spbkNameToSearch = scn.nextLine();
        boolean isSpellBookFound = false;

        for(int spbkIndex = 0; spbkIndex < spellbookCategories.length; spbkIndex++)
        {
            Spellbook spellBook = spellbookCategories[spbkIndex];

            if(spellBook.getSpellbookName().equals(spbkNameToSearch))
            {
                isSpellBookFound = true;

                System.out.println("Please enter light prism passive state to set for " + spbkNameToSearch + ": (t - True, f - False)");
                char newStateChar = scn.next().charAt(0);

                if(newStateChar == 't' || newStateChar == 'f')
                {
                    boolean newStateBool = false;
                    if(newStateChar == 't')
                        newStateBool = true;

                    spellBook.setIsLightPrismSpellPassiveInEffect(newStateBool);
                    System.out.println("Sucessfully set light prism passive state to " + newStateBool + " for " + spellBook.getSpellbookName());
                }
            }
        }

        if(!isSpellBookFound)
            System.out.println("Failed to find the spellbook of name: " + spbkNameToSearch);
    }

    private void setLightPrismPassiveStateForAllSpellbook()
    {
        System.out.println("Please enter light prism passive state to set: (t - True, f - False)");
        char newStateChar = scn.next().charAt(0);

        if(newStateChar == 't' || newStateChar == 'f')
        {
            for(int spbkIndex = 0; spbkIndex < spellbookCategories.length; spbkIndex++)
            {
                boolean newStateBool = false;
                if(newStateChar == 't')
                    newStateBool = true;
            
                Spellbook spellBook = spellbookCategories[spbkIndex];
                spellBook.setIsLightPrismSpellPassiveInEffect(newStateBool);
                System.out.println("Sucessfully set light prism passive state to " + newStateBool + " for " + spellBook.getSpellbookName());
            }
        }
    }

    private void castASpellFromASpellbook()
    {
        System.out.println("Please enter the name of a spellbook to cast spell from: ");
        String spbkNameToSearch = scn.nextLine();
        boolean isSpellBookFound = false;

        for(int spbkIndex = 0; spbkIndex < spellbookCategories.length; spbkIndex++)
        {
            Spellbook spellBook = spellbookCategories[spbkIndex];

            if(spellBook.getSpellbookName().equals(spbkNameToSearch))
            {
                isSpellBookFound = true;

                System.out.println("Please select the spell to cast from " + spbkNameToSearch + ": (1 - BlindingLight, 2 - LightPrism)");
                int selectedSpell = scn.nextInt();

                if(selectedSpell == 1)
                {
                    double dmgApplied = spellBook.castBlindingLightSpell();
                    System.out.println("Casted BlindingLight from " + spellBook.getSpellbookName() + " with damage applied of " + dmgApplied);
                }
                else if(selectedSpell == 2)
                {
                    double dmgApplied = spellBook.castLightPrismSpell();
                    System.out.println("Casted LightPrism from " + spellBook.getSpellbookName() + " with damage applied of " + dmgApplied);
                }
                else
                    System.out.println("Failed to cast spell from " + spellBook.getSpellbookName() + " as wrong option was selected.");
            }
        }

        if(!isSpellBookFound)
            System.out.println("Failed to find the spellbook of name: " + spbkNameToSearch);
    }

    private void castASpellFromAllSpellbook()
    {
        System.out.println("Please select the spell to cast from all spellbook: (1 - BlindingLight, 2 - LightPrism)");
        int selectedSpell = scn.nextInt();

        if(selectedSpell == 1 || selectedSpell == 2)
        {
            for(int spbkIndex = 0; spbkIndex < spellbookCategories.length; spbkIndex++)
            {
                Spellbook spellBook = spellbookCategories[spbkIndex];
                if(selectedSpell == 1)
                {
                    double dmgApplied = spellBook.castBlindingLightSpell();
                    System.out.println("Casted BlindingLight from " + spellBook.getSpellbookName() + " with damage applied of " + dmgApplied);
                }
                else if(selectedSpell == 2)
                {
                    double dmgApplied = spellBook.castLightPrismSpell();
                    System.out.println("Casted LightPrism from " + spellBook.getSpellbookName() + " with damage applied of " + dmgApplied);
                }
            }
        }
        else
            System.out.println("Failed to cast spell from all spellbook as wrong option was selected.");
    }

    private char writeOptionsAndGetReadCharInput()
    {
        System.out.println(
        "\nSelect options:" + 
        "\nq - Quit" + 
        "\na - View spellbook categories" +
        "\nb - Set mana for a spellbook" + 
        "\nc - Set mana for all spellbook" + 
        "\nd - Set light prism passive for a spellbook" +
        "\ne - Set light prism passive for all spellbook" + 
        "\nf - Cast a spell from a spellbook" + 
        "\ng - Cast a spell from all spellbook");

        return scn.next().toUpperCase().charAt(0);
    }
}
