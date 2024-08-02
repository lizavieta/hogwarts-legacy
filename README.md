# Overview

This project is a comprehensive implementation of a magic system in Java. It includes various classes, interfaces, and enums to simulate magical effects, item trading, and spell casting within a fantasy setting. Key concepts include mana (magic energy), health, and various levels of magical proficiency.

# Terminology

- Mana (MP): Magic energy required to cast spells and perform magical effects.
- Health (HP): Represents the health of living objects or the durability of inanimate objects.
- Magic Levels: Ranges from NOOB to MASTER, determining the proficiency and mana capacity of a wizard.

# Components

1. Enums

- MagicLevel: Enum defining constants NOOB, ADEPT, STUDENT, EXPERT, and MASTER, each associated with a basic MP value and a string representation of asterisks.

2. Interfaces

- MagicSource: Defines methods for providing mana required to cast spells.
- MagicEffectRealization: Provides default methods to handle magical effects such as taking damage, healing, and weakening/enforcing magic.
- Trader: Defines methods for trading magic items, managing inventory, and handling transactions.
- Tradeable: Represents items that can be traded, including methods for getting the price and weight of the item.

3.  Classes

- MagicItem: Abstract base class for magic items, implementing Tradeable, MagicEffectRealization, and MagicSource interfaces.
- Potion: Abstract class extending MagicItem, specifically for potions.
- HealthPotion: Increases the HP of the consumer.
- ManaPotion: Increases the MP of the consumer.
- Concoction: Can change both HP and MP and activate multiple spells.
- Spell: Abstract base class for various types of spells.
- AttackingSpell: Reduces HP or MP based on specified conditions.
- HealingSpell: Restores HP or MP based on specified conditions.
- ProtectingSpell: Creates a shield against specific attacks.
- Scroll: Allows casting spells without using mana.
- Wizard: Represents a wizard who can trade items, cast spells, and manage inventory.
