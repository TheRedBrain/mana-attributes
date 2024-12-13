# 1.6.1

- removed debug log spam

# 1.6.0

HUD rendering overhaul
The initial idea of splitting a bar into three textures per "layer" to allow for easy change of the bar length, came with the cost of massive FPS drops.
With this rewrite the bar size is no longer changeable with a simple config option. Each 'layer' consists of only 1 texture (which can have configurable dimensions).
In addition, the texture can be dynamically replaced by another texture (with configurable dimensions) depending on the max value.
- removed 'is_centered' option, as it was redundant and unnecessarily complicated
- 'fill_direction' no longer chooses between a horizontal and a vertical 'texture set', it only determines the direction from which the bar is filled. This means that changing between horizontal and vertical resource bars also requires a texture change.
- added the option to display an icon (with configurable texture id and dimensions). This can be toggled independently of the bar and the number.
- changed default value of "generic.max_mana" to 10

# 1.5.1

- fixed "offset_from_origin" config values not working correctly
- mana number display is now independent of the mana bar

# 1.4.0

- added "generic.reserved_mana" entity attribute
- changed "natural mana regeneration" gamerule to simply add 1 mana regeneration. The previous implementation prevented mana regeneration from ever becoming negative.
- HUD element overhaul, improves mod compatibility, increases customization options
- generally improved config layout
- removed dependency on Cloth Config
- added dependency on Fzzy Config
- added dependency on Resource Bar API

# 1.3.0

- added optional smooth mana bar animations
- added a mana regeneration delay after mana is reduced, controlled by an entity attribute
- added a missing translation key

# 1.2.0

- changed the way the attributes are registered, which increased compatibility with other mods.

# 1.1.0

- added more options to configure the HUD element.

# 1.0.1

- fixed an issue where some config values where not applied correctly.

# 1.0.0

First release!

#