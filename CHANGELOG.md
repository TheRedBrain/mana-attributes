# 2.9.1

- fixed mana bar being empty when joining a world for the first time
- bumped various dependency versions

# 2.9.0

- replaced game rules with server config options
- tweaked default client config settings
- fixed mana bar being empty when joining a world / respawning

# 2.8.1

- fixed game rules

# 2.8.0

- added "naturalMaximumMana" game rule
- changed default value of "generic.max_mana" to 0
- reworked the "naturalManaRegeneration" game rule (now is a double value, instead of a boolean)

# 2.7.0

- added alternative mana bar consisting of icons, similar to vanillas resource bars (this first iteration does not yet support multiple icon types per bar, e.g. reserved mana)
- reworked the "naturalManaRegeneration" game rule (now works with mods that display attribute values)
- fixed an issue where the mana bar was empty when joining a world for the first time/respawning
- fixed an issue where the mana bar was visible in creative mode
- fixed an issue where the mana bar was visible even when the HUD was hidden (pressing F1)

# 2.6.0

HUD rendering overhaul
The initial idea of splitting a bar into three textures per "layer" to allow for easy change of the bar length, came with the cost of massive FPS drops.
With this rewrite the bar size is no longer changeable with a simple config option. Each 'layer' consists of only 1 texture (which can have configurable dimensions).
In addition, the texture can be dynamically replaced by another texture (with configurable dimensions) depending on the max value.
- removed 'is_centered' option, as it was redundant and unnecessarily complicated
- 'fill_direction' no longer chooses between a horizontal and a vertical 'texture set', it only determines the direction from which the bar is filled. This means that changing between horizontal and vertical resource bars also requires a texture change.
- added the option to display an icon (with configurable texture id and dimensions). This can be toggled independently of the bar and the number.
- changed default value of "generic.max_mana" to 10

# 2.5.1

- fixed "offset_from_origin" config values not working correctly
- mana number display is now independent of the mana bar

# 2.4.0

- added "generic.reserved_mana" entity attribute
- changed "natural mana regeneration" gamerule to simply add 1 mana regeneration. The previous implementation prevented mana regeneration from ever becoming negative.
- HUD element overhaul, improves mod compatibility, increases customization options
- generally improved config layout
- removed dependency on Cloth Config
- added dependency on Fzzy Config
- added dependency on Resource Bar API

# 2.3.0

- further improvements to mana bar customization
- added a missing translation keys

# 2.2.0

- update to 1.21.1
- improved mana bar customization

# 2.1.0

- added optional smooth mana bar animations
- added a mana regeneration delay after mana is reduced, controlled by an entity attribute
- added a missing translation key

# 2.0.0

Update to 1.21

# 1.2.0

- changed the way the attributes are registered, which increased compatibility with other mods

# 1.1.0

- added more options to configure the HUD element

# 1.0.1

- fixed an issue where some config values where not applied correctly

# 1.0.0

First release!

#