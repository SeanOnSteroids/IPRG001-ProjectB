import java.util.*;

public class Spellbook
{
    private final double SPELL_DAMAGE_MULTIPLIER = 8.30;
    private final double BLINDINGLIGHT_SPELL_BASE_DAMAGE = 2.46;
    private final double BLINDINGLIGHT_SPELL_MANA_DEDUDCTION_RATE = 11.53;
    private final double LIGHTPRISM_SPELL_MANA_DEDUCTION_RATE = 15.38;

    private String spellbookName;
    private double currentMana;
    private boolean isLightPrismSpellPassiveInEffect;
    
    public Spellbook(String spbkName, double mana)
    {
        spellbookName = spbkName;
        currentMana = mana;
        isLightPrismSpellPassiveInEffect = false;
    }

    public double getSpellDamageMultiplier() { return SPELL_DAMAGE_MULTIPLIER; }
    public double getBlindingLightSpellBaseDamage() { return BLINDINGLIGHT_SPELL_BASE_DAMAGE; }
    public double getBlindingLightSpellManaDeductionRate() { return BLINDINGLIGHT_SPELL_MANA_DEDUDCTION_RATE; }
    public double getLightPrismSpellManaDeductionRate() { return LIGHTPRISM_SPELL_MANA_DEDUCTION_RATE; } 

    public String getSpellbookName() { return spellbookName; }
    public void setSpellbookName(String newSpellbookName) { spellbookName = newSpellbookName; } 

    public double getCurrentMana() { return currentMana; }
    public void setCurrentMana(double newMana) { currentMana = newMana; }
    
    public boolean getIsLightPrismSpellPassiveInEffect() { return isLightPrismSpellPassiveInEffect; }
    public void setIsLightPrismSpellPassiveInEffect(boolean lightprismPassiveEffectState) { isLightPrismSpellPassiveInEffect = lightprismPassiveEffectState; }

    public double castBlindingLightSpell()
    {
        currentMana -= BLINDINGLIGHT_SPELL_MANA_DEDUDCTION_RATE;

        double finalSpellDamageApplied = BLINDINGLIGHT_SPELL_BASE_DAMAGE * SPELL_DAMAGE_MULTIPLIER;
        
        return finalSpellDamageApplied;
    }

    public double castLightPrismSpell()
    {
        currentMana -= LIGHTPRISM_SPELL_MANA_DEDUCTION_RATE;
        isLightPrismSpellPassiveInEffect = true;

        int maxRandomDamageApplierLimit = 4;
        int minRandomDamageApplierLimit = 1;

        int randomSpellDamageApplier = new Random().nextInt(maxRandomDamageApplierLimit - minRandomDamageApplierLimit + 1) + minRandomDamageApplierLimit;
        double finalSpellDamageApplied = randomSpellDamageApplier * SPELL_DAMAGE_MULTIPLIER;

        return finalSpellDamageApplied;
    }

    public String toString()
    {
        return "\n - Mana: " + currentMana + 
               "\n - IsLightPrismPassiveInEffect: " + isLightPrismSpellPassiveInEffect + 
               "\n - Spell damage multipler: " + SPELL_DAMAGE_MULTIPLIER +
               "\n - BlindingLight spell base damage: " + BLINDINGLIGHT_SPELL_BASE_DAMAGE + 
               "\n - BlindingLight spell mana deduction: " + BLINDINGLIGHT_SPELL_MANA_DEDUDCTION_RATE + 
               "\n - LightPrism spell mana deduction: " + LIGHTPRISM_SPELL_MANA_DEDUCTION_RATE;
    }
}
