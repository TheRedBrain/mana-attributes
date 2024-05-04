# Mana Attributes
This API adds a mana system. 

## Default implementation
LivingEntities can have up to **_generic.max_mana_** amounts of mana. When mana is reduced, a cooldown of **_generic.mana_regeneration_delay_threshold_** ticks starts. After the cooldown ends **_generic.mana_regeneration_** is regenerated every **_generic.mana_tick_threshold_** ticks.

## Customization
When the gamerule "naturalManaRegeneration" is true, players have a mana regeneration of at least 1.

The client config allows customizing the HUD element.

## API
Casting a "LivingEntity" to the "ManaUsingEntity" interface gives access to all relevant methods.