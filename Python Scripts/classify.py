# -*- coding: utf-8 -*-
"""
Created on Thu Mar  7 15:00:48 2019

@author: Voctor10
"""
import nltk
import sys
nltk.download('punkt')
from nltk.stem.lancaster import LancasterStemmer
import json
import numpy as np
stemmer = LancasterStemmer()


training_data = []

# TRAINING DATA HERE

#VERY GOOD
training_data.append({"class":"VERY GOOD", "sentence":"When this card is Summoned: Target 1 face-up monster your opponent controls; this card's ATK and DEF become equal to that monster's original ATK and DEF."})
training_data.append({"class":"VERY GOOD", "sentence":"You can Special Summon this card from your hand by removing from play 1 face-up monster you control. If you do, return the removed from play monster to the field during the next Standby Phase."})
training_data.append({"class":"VERY GOOD", "sentence":"Once per turn, you can either: Target 1 monster you control; equip this card to that target, OR: Unequip this card and Special Summon it. A monster equipped with this card is treated as a Tuner, it gains 500 ATK and DEF, also if the equipped monster would be destroyed by battle or card effect, destroy this card instead."})
training_data.append({"class":"VERY GOOD", "sentence":"You can Ritual Summon this card with \"Black Illusion Ritual\". Once per turn: You can target 1 monster your opponent controls; equip that target to this card (max. 1). This card's ATK/DEF become equal to that equipped monster's. If this card would be destroyed by battle, destroy that equipped monster instead. While equipped with that monster, any battle damage you take from battles involving this card inflicts equal effect damage to your opponent."})
training_data.append({"class":"VERY GOOD", "sentence":"Cannot be Normal Summoned/Set. Must be Special Summoned by its own effect, and cannot be Special Summoned by other ways. When a face-up monster you control is destroyed by a card effect and sent to the Graveyard (except during the Damage Step): You can Special Summon this card from your hand. Other monsters you control cannot declare an attack. Once per turn: You can target 1 Synchro Monster your opponent controls; equip that target to this card. This card gains ATK equal to the combined ATK of the monsters equipped to it by this effect. Once per turn, during either player's turn, when your opponent activates a Spell Card: You can negate the activation, and if you do, destroy it."})
training_data.append({"class":"VERY GOOD", "sentence":"Once per turn, when your opponent activates a monster effect (Quick Effect): You can target 1 Effect Monster your opponent controls or in their GY; equip that target to this card you control. This card gains ATK/DEF equal to that equipped monster's. Monsters with that equipped monster's name cannot attack, also their effects on the field and their activated effects are negated."})
training_data.append({"class":"VERY GOOD", "sentence":"During your opponent's Main Phase (Quick Effect): You can send this card from your hand to the GY, then target 1 Effect Monster your opponent controls; negate the effects of that face-up monster your opponent controls, until the end of this turn."})
training_data.append({"class":"VERY GOOD", "sentence":"Draw 2 cards."})
training_data.append({"class":"VERY GOOD", "sentence":"You can Special Summon 1 monster from your hand. If this card is destroyed by battle or card effect and sent to the Graveyard: You can Special Summon 1 monster from your hand."})
training_data.append({"class":"VERY GOOD", "sentence":"Once per turn: You can discard any number of cards, and if you do, this card gains 1 Level and 400 ATK for each discarded card, until the end of this turn."})
training_data.append({"class":"VERY GOOD", "sentence":"When this card is Normal Summoned: You can Special Summon 1 Level 3 or lower Sea Serpent-Type monster from your Deck."})
training_data.append({"class":"VERY GOOD", "sentence":"During either player's turn: You can send this card from your hand to the Graveyard; this turn, each time your opponent Special Summons a monster(s), immediately draw 1 card. You can only use 1 \"Maxx \"C\"\" per turn."})
training_data.append({"class":"VERY GOOD", "sentence":"When this card is Normal Summoned: You can target 1 Level 3 monster in your GY; Special Summon that target, but it has its effects negated. You can only use this effect of \"Crane Crane\" once per turn."})
training_data.append({"class":"VERY GOOD", "sentence":"When this card is Normal Summoned: You can Special Summon 1 Level 3 Fiend monster from your hand or Deck, but it has its effects negated, also it cannot be used as Synchro Material."})
training_data.append({"class":"VERY GOOD", "sentence":"If this card is Normal or Special Summoned: You can draw 1 card, then place 1 card from your hand on either the top or bottom of your Deck. If this card is destroyed by battle or card effect: You can Special Summon any number of \"Baobaboon\" from your Deck."})
training_data.append({"class":"VERY GOOD", "sentence":"When a monster on the field activates its effect, or when a Spell/Trap that is already face-up on the field activates its effect (Quick Effect): You can send this card from your hand or field to the GY; destroy that card on the field. You can only use this effect of \"Ghost Ogre & Snow Rabbit\" once per turn."})
training_data.append({"class":"VERY GOOD", "sentence":"Before damage calculation, if this card is being attacked by an opponent's monster, and was in face-up Attack Position at the start of the Damage Step: Inflict damage to your opponent equal to the attacking monster's ATK. If you do, after damage calculation: Destroy this card."})
training_data.append({"class":"VERY GOOD", "sentence":"When this card is Normal Summoned: You can Special Summon 1 Level 4 or lower monster from your hand, also, after that, change this card to Defense Position."})
training_data.append({"class":"VERY GOOD", "sentence":"If your opponent controls a monster and you control no monsters, you can Special Summon this card (from your hand). If this card attacks a Defense Position monster, inflict piercing battle damage to your opponent. When this card inflicts battle damage to your opponent: You can add 1 \"Heroic\" card from your Deck to your hand."})
training_data.append({"class":"VERY GOOD", "sentence":"This card can attack your opponent directly. If this card is sent from the field to the Graveyard: You can target 1 Dragon, Warrior, or Spellcaster-Type Normal Monster in your Graveyard; add it to your hand."})
training_data.append({"class":"VERY GOOD", "sentence":"Once per turn, when a Normal Monster you control inflicts battle damage to your opponent: You can target 1 card on the field; destroy it."})
training_data.append({"class":"VERY GOOD", "sentence":"During either player's turn, when a Spell Card is activated: You can send the top 2 cards of your Deck to the Graveyard; negate the activation, and if you do, destroy it. This card must be face-up on the field to activate and to resolve this effect."})
training_data.append({"class":"VERY GOOD", "sentence":"If this card is destroyed by battle and sent to the Graveyard, or if this card you control is sent to your Graveyard by an opponent's card effect: You can target 1 monster in either player's Graveyard, except \"Samsara, Dragon of Rebirth\"; Special Summon it. You can only use this effect of \"Samsara, Dragon of Rebirth\" once per turn."})
training_data.append({"class":"VERY GOOD", "sentence":"At the start of the Damage Step, if this card battles a non-DARK monster: Destroy that monster."})
training_data.append({"class":"VERY GOOD", "sentence":"Cannot be Normal Summoned/Set. Must first be Special Summoned (from your hand) while you control no monsters. When this card is Special Summoned from the hand: You can Special Summon 2 \"Vague Shadow Tokens\" (Winged Beast-Type/DARK/Level 1/ATK 0/DEF 0), but they cannot be Tributed or be used as Synchro Material. You can banish this card and 1 or more face-up non-Tuners you control, then target 1 \"Blackwing\" Synchro Monster in your Graveyard whose Level equals the total Levels the banished monsters had on the field; Special Summon it, and if you do, it is treated as a Tuner."})
training_data.append({"class":"VERY GOOD", "sentence":"Cannot be Special Summoned. During the End Phase of the turn this card is Normal Summoned or flipped face-up: Return it to the hand. When this card is Normal Summoned or flipped face-up: Destroy all other face-up monsters on the field."})
training_data.append({"class":"VERY GOOD", "sentence":"Once per turn: You can discard 1 card, then target 1 card your opponent controls; destroy it. If this Synchro Summoned card is sent from the field to the GY: You can draw 1 card. You can only use this effect of \"Coral Dragon\" once per turn."})
training_data.append({"class":"VERY GOOD", "sentence":"Cannot be Normal Summoned/Set. Must first be Special Summoned (from your hand) by banishing 1 LIGHT and 1 DARK monster from your Graveyard. Once per turn: You can target 1 face-up monster on the field; banish that target. This card cannot attack the turn you activate this effect."})
training_data.append({"class":"VERY GOOD", "sentence":"If this card is Normal Summoned: Place 2 Spell Counters on it. If this card is Pendulum Summoned: Place 3 Spell Counters on it. This card gains 400 ATK for each Spell Counter on it. You can remove 1 Spell Counter from this card, then target 1 Spell/Trap Card on the field; destroy it. You can only use this effect of \"Breaker the Dark Magical Warrior\" once per turn."})
training_data.append({"class":"VERY GOOD", "sentence":"You can discard any number of cards to the Graveyard, then target the same number of cards your opponent controls; return those cards to the hand. You can only use this effect of \"Brionac, Dragon of the Ice Barrier\" once per turn."})
training_data.append({"class":"VERY GOOD", "sentence":"You can reduce the battle damage you take from an attack involving a Pendulum Monster you control to 0. During your End Phase: You can destroy this card, and if you do, add 1 Pendulum Monster with 1500 or less ATK from your Deck to your hand. You can only use each Pendulum Effect of \"Odd-Eyes Pendulum Dragon\" once per turn."})
training_data.append({"class":"VERY GOOD", "sentence":"If this card battles an opponent's monster, any battle damage this card inflicts to your opponent is doubled."})
training_data.append({"class":"VERY GOOD", "sentence":"When this card is Synchro Summoned: You can destroy all cards on the field. Once per turn: You can banish 1 Plant monster from your GY, then target 1 Defense Position monster your opponent controls; change that target to face-up Attack Position, and if you do, its ATK becomes 0 until the end of this turn."})
training_data.append({"class":"VERY GOOD", "sentence":"When this card is Normal or Flip Summoned: Destroy all Set cards your opponent controls."})
training_data.append({"class":"VERY GOOD", "sentence":"Cannot be Normal Summoned/Set. Must be Special Summoned (from your hand) by having exactly 3 DARK monsters in your GY. You can banish 1 DARK monster from your GY, then target 1 card on the field; destroy that target."})
training_data.append({"class":"VERY GOOD", "sentence":"If this card is Special Summoned, or a Level 5 or higher monster(s) is Special Summoned to your opponent's side of the field: Target 1 Special Summoned monster your opponent controls; return that target to the hand. You can only use this effect of \"Black Rose Moonlight Dragon\" once per turn."})
training_data.append({"class":"VERY GOOD", "sentence":"Once per turn, when another Level 5 or higher monster activates its effect on the field (Quick Effect): You can negate the activation, and if you do, destroy it. Once per turn, when a monster effect is activated that targets 1 Level 5 or higher monster on the field (and no other cards) (Quick Effect): You can negate the activation, and if you do, destroy it. If this card's effect destroys a monster, this card gains ATK equal to the destroyed monster's original ATK until the end of this turn."})
training_data.append({"class":"VERY GOOD", "sentence":"When a monster(s) you control is targeted for an attack or by an opponent's card effect (except during the Damage Step): You can destroy all cards your opponent controls."})
training_data.append({"class":"VERY GOOD", "sentence":"Cannot be destroyed by battle with a monster with equal or lower Level. When your opponent Normal or Special Summons exactly 1 monster (and no other monsters are Summoned) while this monster is on the field: You can make that monster lose ATK equal to its Level x 200 (until the end of this turn), and if you do, inflict damage to your opponent equal to the ATK lost by this effect."})
training_data.append({"class":"VERY GOOD", "sentence":"At the start of the Damage Step, if your Pendulum Monster battles an opponent's monster: You can Special Summon this card from your hand, and if you do, your monster cannot be destroyed by that battle. You can only use this effect of \"Performapal Odd-Eyes Dissolver\" once per turn. During your Main Phase: You can Fusion Summon 1 Fusion Monster from your Extra Deck, using this card you control as Fusion Material, as well as other monsters you control or cards in your Pendulum Zones."})
training_data.append({"class":"VERY GOOD", "sentence":"Cannot be destroyed by battle or card effects. If you take damage from an attack involving this card, or from an opponent's card effect: This card gains ATK equal to the damage you took."})
training_data.append({"class":"VERY GOOD", "sentence":"Cannot be Normal Summoned/Set. Must first be Special Summoned (from your hand) by banishing 1 LIGHT and 1 DARK monster from your Graveyard. Once per turn, you can activate 1 of these effects: Target 1 monster on the field; banish that target face-up. This card cannot attack the turn this effect is activated or If this attacking card destroys an opponent's monster by battle, after damage calculation: It can make a second attack in a row."})
training_data.append({"class":"VERY GOOD", "sentence":"Once per turn, when another monster's effect is activated (Quick Effect): You can negate the activation, and if you do, destroy that monster, and if you do that, this card gains ATK equal to the destroyed monster's original ATK until the end of this turn. If this card battles an opponent's Level 5 or higher monster, during damage calculation: This card gains ATK equal to the current ATK of the opponent's monster it is battling during that damage calculation only."})
training_data.append({"class":"VERY GOOD", "sentence":"You can activate 1 of these effects; Draw 2 cards or Return all Spell and Trap Cards on the field to the hand or Destroy all monsters your opponent controls or Look at your opponent's hand, also shuffle 1 card from their hand into the Deck. You can only use this effect of \"Pot of The Forbidden\" once per turn."})
training_data.append({"class":"VERY GOOD", "sentence":"You can only control 1 \"First of the Dragons\". This card cannot be destroyed by battle, except by battle with a Normal Monster, and is unaffected by other monsters' effects."})
training_data.append({"class":"VERY GOOD", "sentence":"If this card is Synchro Summoned: You can target monsters your opponent controls and/or in their Graveyard, up to the number of Synchro Materials used for the Synchro Summon of this card; banish them. If this Synchro Summoned card is destroyed by battle or card effect: You can target 1 banished monster, except this card; Special Summon it to your field."})
training_data.append({"class":"VERY GOOD", "sentence":"If this card is Synchro Summoned: You can banish all Spell and Trap Cards your opponent controls and in their Graveyard. If this Synchro Summoned card is destroyed by battle or card effect: You can target 1 other monster in your Graveyard; Special Summon it."})
training_data.append({"class":"VERY GOOD", "sentence":"When this card is Synchro Summoned: You can banish up to 1 card each from your opponent's hand, field, and GY. (The card in the hand is chosen at random.)"})
training_data.append({"class":"VERY GOOD", "sentence":"This monster can only be Special Summoned by Fusion Summon from the Extra Deck. This card cannot be destroyed by card effects. During battle between this attacking card and a Defense Position monster whose DEF is lower than the ATK of this card, inflict the difference as Battle Damage to your opponent. When this card destroys an opponent's monster by battle and sends it to the Graveyard, you gain Life Points equal to the destroyed monster's ATK."})
training_data.append({"class":"VERY GOOD", "sentence":"Cannot be destroyed by battle or card effects. Other monsters you control cannot attack. Once per turn: You can target 1 monster your opponent controls; any battle damage your opponent takes from attacks involving this card this turn is halved, also, change that monster's ATK to 0, and if you do, gain LP equal to its original ATK."})
training_data.append({"class":"VERY GOOD", "sentence":"Requires 3 Tributes to Normal Summon (cannot be Normal Set). This card's Normal Summon cannot be negated. When Normal Summoned, cards and effects cannot be activated. Cannot be targeted by Spells, Traps, or card effects. Once per turn, during the End Phase, if this card was Special Summoned: Send it to the Graveyard. You can Tribute 2 monsters; destroy all monsters your opponent controls. This card cannot declare an attack the turn this effect is activated."})
training_data.append({"class":"VERY GOOD", "sentence":"You can Special Summon this card (from your hand) by banishing 1 face-up Dragon-Type monster you control. Once per turn: You can Special Summon 1 Dragon-Type monster from your hand or Graveyard, except \"Red-Eyes Darkness Metal Dragon\"."})
training_data.append({"class":"VERY GOOD", "sentence":"Must be Synchro Summoned, and cannot be Special Summoned by other ways. You can only use each of these effects of \"Tyrant Red Dragon Archfiend\" once per turn. During your Main Phase 1: You can destroy all other cards on the field, also, for the rest of this turn, other monsters you control cannot attack. During either player's Battle Phase, when a Spell/Trap Card is activated: You can negate the activation, and if you do, destroy that card, and if you do that, this card gains 500 ATK."})
training_data.append({"class":"VERY GOOD", "sentence":"If a Pendulum Monster you control attacks, for that battle, it cannot be destroyed by battle, also you take no battle damage. At the end of the Damage Step, if a Pendulum Monster you control attacked: All monsters your opponent currently controls lose ATK equal to that attacking monster's ATK, until the end of this turn."})
training_data.append({"class":"VERY GOOD", "sentence":"For this card's Synchro Summon, you can treat 1 Pendulum Summoned Pendulum Monster you control as a Tuner. If this card is Synchro Summoned using a Pendulum Summoned Pendulum Monster Tuner: You can target 1 card in your Graveyard; add it to your hand. When this card destroys an opponent's monster by battle: You can halve your opponent's LP. If this card in the Monster Zone is destroyed by battle or card effect: You can place this card in your Pendulum Zone."})
training_data.append({"class":"VERY GOOD", "sentence":"Must be Synchro Summoned, and cannot be Special Summoned by other ways. This card's Synchro Summon cannot be negated. When Synchro Summoned, cards and effects cannot be activated. If this card attacks, it is unaffected by other card effects until the end of the Damage Step."})
training_data.append({"class":"VERY GOOD", "sentence":"You can target 1 monster on the field; change it to face-up Attack Position if it is in Defense Position, also change its ATK to 0. You can only use this effect of \"Subterror Behemoth Speleogeist\" once per turn."})
training_data.append({"class":"VERY GOOD", "sentence":"When a face-up monster you control is flipped face-down, if you control no face-up monsters: You can Special Summon this card from your hand in Defense Position. Once per turn: You can change this card to face-down Defense Position."})
training_data.append({"class":"VERY GOOD", "sentence":"During your Main Phase: You can add 1 \"Zefra\" Pendulum Monster from your Deck to your Extra Deck face-up, and if you do, change this card's Pendulum Scale to be the same as that Pendulum Monster's, until the end of this turn. You can only use this effect of \"Zefraath\" once per turn."})
training_data.append({"class":"VERY GOOD", "sentence":"During either player's Main Phase 1: You can discard this card and 1 \"Nekroz\" Spell Card; your opponent cannot Special Summon monsters from the Extra Deck during this phase. When this card is Ritual Summoned: You can banish all other cards on the field and in the Graveyards. You cannot Normal Summon/Set or Special Summon other monsters the turn you activate this effect."})
training_data.append({"class":"VERY GOOD", "sentence":"Must be Synchro Summoned, and cannot be Special Summoned by other ways. Once per turn, during either player's turn, when a monster(s) would be Summoned: You can negate the Summon, and if you do, destroy that monster. This card must be face-up on the field to activate and to resolve this effect. When this card is sent from the field to the Graveyard: You can target 1 \"T.G.\" monster in your Graveyard; Special Summon that target."})
training_data.append({"class":"VERY GOOD", "sentence":"This card can attack while in face-up Defense Position. If it does, apply its DEF for damage calculation. Once per turn: You can discard up to 2 cards, then target that many cards your opponent controls; destroy them. Once per turn: You can banish all Spells and Traps from the GYs, and if you do, inflict 200 damage to your opponent for each card banished."})
training_data.append({"class":"VERY GOOD", "sentence":"Must be Synchro Summoned, and cannot be Special Summoned by other ways. You can banish this card until the End Phase to activate 1 of these Quick Effects; When your opponent activates a card or effect: Negate the activation, and if you do, destroy that card or When your opponent would Summon a monster(s): Negate the Summon, and if you do, destroy that monster(s) or When an opponent's monster declares an attack: Negate the attack, then end the Battle Phase."})
training_data.append({"class":"VERY GOOD", "sentence":"Must be Synchro Summoned, and cannot be Special Summoned by other ways. This card's maximum number of attacks per turn equals the number of non-Tuner monsters used as its Synchro Material. Once per turn, when a card or effect is activated: You can negate the activation and destroy it. When this card leaves the field: You can Special Summon 1 \"Shooting Star Dragon\" from your Extra Deck."})
training_data.append({"class":"VERY GOOD", "sentence":"When this card is Synchro Summoned: You can activate this effect; for the rest of this turn, your opponent cannot activate cards, also cards your opponent controls cannot activate their effects. Your opponent cannot activate cards or effects in response to this effect's activation. If this card destroys a monster by battle: Inflict damage to your opponent equal to that monster's original ATK. If this card in its owner's possession is destroyed by an opponent's card (by battle or card effect): You can target 1 Level 8 or lower DARK Dragon-Type Synchro Monster in your Graveyard; Special Summon it."})
training_data.append({"class":"VERY GOOD", "sentence":"Fusion, Synchro, and Xyz Monsters your opponent controls cannot activate their effects. Once per turn, when your opponent adds a card(s) from their Deck to their hand (except during the Draw Phase or the Damage Step): You can destroy that card(s)"})
training_data.append({"class":"VERY GOOD", "sentence":"Must be Fusion Summoned and cannot be Special Summoned by other ways. If this card is Special Summoned: Destroy all cards your opponent controls. Cannot be destroyed by your opponent's card effects. Your opponent cannot target this card with card effects. When this card destroys an opponent's monster by battle: You can Special Summon 1 \"Supreme King Dragon\" monster from your Deck or Extra Deck. If this card in the Monster Zone is destroyed by battle or card effect: You can place this card in your Pendulum Zone."})
#GOOD
training_data.append({"class":"GOOD", "sentence":"You can Special Summon any number of \"Black Sheep Tokens\" (Zombie/DARK/Level 1/ATK 0/DEF 0)."})
training_data.append({"class":"GOOD", "sentence":"Draw 3 cards, then discard 2."})
training_data.append({"class":"GOOD", "sentence":"When this card is sent from the field to the Graveyard: All monsters your opponent currently controls lose 500 ATK."})
training_data.append({"class":"GOOD", "sentence":"When this card is destroyed by battle and sent to the Graveyard: Gain 1000 Life Points, then you can Special Summon any number of \"Nimble Momongas\" from your Deck in face-down Defense Position."})
training_data.append({"class":"GOOD", "sentence":"When this card is Synchro Summoned: You can Special Summon up to 4 Level 2 non-Tuner monsters from your hand and/or Graveyard in Defense Position, but they have their effects negated, also you cannot Special Summon monsters from the Extra Deck for the rest of this turn, except Synchro Monsters. You can only use this effect of \"Celestial Double Star Shaman\" once per turn."})
training_data.append({"class":"GOOD", "sentence":"When this card is Synchro Summoned: You can draw 1 card. Once per Chain, during your opponent's Main Phase, you can: Immediately after this effect resolves, Synchro Summon using this card you control (this is a Quick Effect)."})
training_data.append({"class":"GOOD", "sentence":"At the start of the Damage Step, if this card battles an opponent's monster: You can return both the opponent's monster and this card to the hand."})
training_data.append({"class":"GOOD", "sentence":"Once per turn: You can target 1 face-up monster your opponent controls; its ATK becomes 0, and if it does, its effects are negated. These changes last until the end of this turn."})
training_data.append({"class":"GOOD", "sentence":"When this card is Normal Summoned: You can send 1 Level 4 or lower monster from your Deck to the GY. When this card is destroyed by battle and sent to the GY: You can draw 1 card."})
training_data.append({"class":"GOOD", "sentence":"You can return 1 face-up monster you control to the hand; Special Summon this card from your hand, but banish it when it leaves the field, also it gains 500 ATK if the returned monster was WIND on the field."})
training_data.append({"class":"GOOD", "sentence":"If this Synchro Summoned monster would be used as a Synchro Material, 1 monster in your hand can be used as 1 of the other materials. Unaffected by other monsters' effects."})
training_data.append({"class":"GOOD", "sentence":"When a card or effect is activated that includes any of these effects (Quick Effect): You can discard this card; negate that effect. Add a card from the Deck to the hand or Special Summon from the Deck or Send a card from the Deck to the GY. You can only use this effect of \"Ash Blossom & Joyous Spring\" once per turn."})
training_data.append({"class":"GOOD", "sentence":"If this card is Normal Summoned: Place 1 Spell Counter on it (max. 1). Gains 300 ATK for each Spell Counter on it. You can remove 1 Spell Counter from this card, then target 1 Spell/Trap on the field; destroy that target."})
training_data.append({"class":"GOOD", "sentence":"Cannot be destroyed by battle. If this card is targeted for an attack: This card gains 1000 ATK and DEF. If this card attacked, after damage calculation: The ATK and DEF gained from this effect returns to 0."})
training_data.append({"class":"GOOD", "sentence":"At the end of the Damage Step, if this card attacked and is still on the field: Target 1 Spell/Trap your opponent controls; destroy that target."})
training_data.append({"class":"GOOD", "sentence":"Once per turn: You can target 1 monster your opponent controls; it cannot change its battle position or declare an attack until the end of your opponent's next turn."})
training_data.append({"class":"GOOD", "sentence":"If this card attacks a Defense Position monster, before damage calculation: Shuffle that monster into the Deck. Once per turn, during your End Phase: Send the top 3 cards of your Deck to the Graveyard."})
training_data.append({"class":"GOOD", "sentence":"This card cannot be Normal Summoned or Set. This card can only be Special Summoned by sending 1 Beast-Type monster from your hand to the Graveyard. When this card is Special Summoned this way, you can activate 1 of these effects: Increase the Level of this card by the Level of the Beast-Type monster or Decrease the Level of this card by the Level of the Beast-Type monster"})
training_data.append({"class":"GOOD", "sentence":"If your opponent controls 2 or more face-up monsters of the same Attribute, you can Special Summon this card (from your hand). Once per turn: You can target 1 face-up card on the field; that target's effects are negated during this turn. There can only be 1 face-up \"Alector, Sovereign of Birds\" on the field."})
training_data.append({"class":"GOOD", "sentence":"Trap Cards, and their effects on the field, cannot be activated. Negate all Trap effects on the field."})
training_data.append({"class":"GOOD", "sentence":"Cannot be Special Summoned. Neither player can Special Summon monsters."})
training_data.append({"class":"GOOD", "sentence":"When this card destroys an opponent's monster by battle and sends it to the Graveyard: You can Special Summon that monster to your field in Defense Position."})
training_data.append({"class":"GOOD", "sentence":"When you take damage from a card in your opponent's possession: You can Special Summon this card from your hand. You must control no cards to activate and to resolve this effect. If Summoned this way, activate the appropriate effect, based on the type of damage: Battle damage: Special Summon 1 \"Emissary of Darkness Token\" (Fairy-Type/LIGHT/Level 7/ATK ?/DEF ?). Its ATK and DEF are each equal to the amount of battle damage you took or if Effect damage: Inflict damage to your opponent equal to the amount of damage you took."})
training_data.append({"class":"GOOD", "sentence":"You can Tribute 1 monster, or no monsters, to Normal Summon (but not Set) this card. The ATK of this card becomes the combined original ATK of the Tributed monsters. Neither player can Tribute cards."})
training_data.append({"class":"GOOD", "sentence":"While this card is face-up on the field, you can send 1 Spell Card from your hand to the Graveyard to negate the activation of an Effect Monster's effect and destroy it."})
training_data.append({"class":"GOOD", "sentence":"If it is your opponent's Battle Phase, and all monsters you controlled at the start of this Battle Phase (min. 2) have been destroyed by battle and sent to the Graveyard: You can Special Summon this card from your hand. If Summoned this way: Choose an Attribute and destroy all face-up monsters with that Attribute. Your opponent cannot Normal or Special Summon monsters with that Attribute."})
training_data.append({"class":"GOOD", "sentence":"Cannot be Normal Summoned/Set. Must first be Special Summoned (from your hand) to your opponent's side of the field by Tributing 2 monsters they control. You cannot Normal Summon/Set the turn you Special Summon this card. During each of your Standby Phases: Take 1000 damage."})
training_data.append({"class":"GOOD", "sentence":"Once per turn: You can Fusion Summon 1 Dragon Fusion Monster from your Extra Deck, using monsters from your hand or field as Fusion Materials."})
training_data.append({"class":"GOOD", "sentence":"Once per turn: You can banish 1 Tuner from your hand, field, or Graveyard, then target 1 card on the field; destroy it. If this Synchro Summoned card is destroyed by card effect and sent to the Graveyard: You can target 1 of your banished Tuners; add it to your hand."})
training_data.append({"class":"GOOD", "sentence":"This card gains 200 ATK and DEF for each non-Tuner Synchro Material Monster used to Synchro Summon this card."})
training_data.append({"class":"GOOD", "sentence":"Requires 3 Tributes to Normal Summon (cannot be Normal Set). To Tribute Summon this card face-up, you can Tribute Continuous Spell/Trap Card(s) you control, as well as monsters. Unaffected by the effects of cards with the same card type (Monster, Spell, and/or Trap) as the original card type of the cards Tributed for its Tribute Summon. If this Tribute Summoned monster in its owner's control is destroyed by an opponent's card (by battle or card effect): You can Special Summon 1 Fusion, Synchro, or Xyz Monster that is EARTH, WATER, FIRE, or WIND from your Extra Deck."})
training_data.append({"class":"GOOD", "sentence":"When this card is Synchro Summoned: You can target 1 Level 9 monster in your Graveyard; Special Summon that target. Level 8 or lower monsters cannot attack the turn they are Normal or Special Summoned."})
training_data.append({"class":"GOOD", "sentence":"If this card is Synchro Summoned: It gains 800 ATK for each card currently in your hand. When this card you control is destroyed by your opponent's card and sent to your GY, if all of the monsters that were used for the Synchro Summon of this card are in your GY: You can Special Summon all of them, but their effects are negated. You can only use this effect of \"Ascension Sky Dragon\" once per turn."})
training_data.append({"class":"GOOD", "sentence":"During either player's turn, when a card or effect is activated that targets exactly 1 card on the field (and no other cards): You can send 1 card from your hand to the Graveyard; negate the activation, and if you do, destroy that card."})
training_data.append({"class":"GOOD", "sentence":"Your opponent cannot target this card with card effects, except during your Main Phase 2."})
training_data.append({"class":"GOOD", "sentence":"When this card, or a monster you control that is owned by your opponent, destroys an opponent's monster by battle and sends it to the Graveyard: You can Special Summon that monster to your side of the field. When your opponent Special Summons a monster(s), except during the Damage Step: You can Tribute 1 EARTH Warrior-Type Synchro Monster; take control of that monster(s). If this face-up card leaves the field: Return control of all monsters you control to the owner."})
training_data.append({"class":"GOOD", "sentence":"Cannot be Special Summoned. If this card is Tribute Summoned: You can Special Summon \"Darklord\" monsters from your hand and/or Deck, up to the number of Effect Monsters your opponent controls. While you control another \"Darklord\" monster, your opponent cannot target this card with card effects. Once per turn: You can send cards from the top of your Deck to the Graveyard, equal to the number of \"Darklord\" monsters on the field, and if you do, gain 500 LP for each \"Darklord\" card sent to the Graveyard by this effect."})
training_data.append({"class":"GOOD", "sentence":"When this card is Fusion Summoned, you can: Apply these effects, in sequence, depending on the number of Fusion Materials with different names used for this card's Summon; 3 or more: Each player sends 3 cards from their Extra Deck to the Graveyard. 5 or more: Each player sends the top 3 cards from their Deck to the Graveyard. 8 or more: Each player returns up to 3 of their banished cards to the Graveyard. 10 or more: Each player sends their entire hand to the Graveyard."})
training_data.append({"class":"GOOD", "sentence":"Cannot be Normal Summoned/Set. Must be Special Summoned (from your face-up Extra Deck) by Tributing all monsters you control, including at least 3 \"Zefra\" monsters, and cannot be Special Summoned by other ways. After you Special Summon this card, you can conduct 1 Pendulum Summon of a \"Zefra\" monster(s) during your Main Phase this turn, in addition to your Pendulum Summon. (You can only gain this effect once per turn.) Once per turn: You can Tribute 1 monster; Special Summon 1 \"Zefra\" monster from your Deck."})
training_data.append({"class":"GOOD", "sentence":"While you control a Level 11 or lower \"Flower Cardian\" monster: You can Special Summon this card from your hand, also you cannot Normal or Special Summon monsters for the rest of this turn, except \"Flower Cardian\" monsters. When this card is targeted for an attack: You can negate the attack, end the Battle Phase, then draw 1 card."})
training_data.append({"class":"GOOD", "sentence":"Must be Ritual Summoned, and cannot be Special Summoned by other ways. During either player's turn, when your opponent would Special Summon a monster(s), OR activates a Spell Card, Trap Card, or monster effect: You can send 1 Fairy-Type monster from your hand to the Graveyard; negate the Special Summon or activation, and if you do, destroy that card."})
training_data.append({"class":"GOOD", "sentence":"Cannot be Normal Summoned/Set. Must be Special Summoned (from your hand) by sending 3 \"Meklord\" monsters from your hand to the Graveyard, and cannot be Special Summoned by other ways. Once per turn: You can target 1 Synchro Monster your opponent controls; equip that target to this card. This card gains ATK equal to the combined ATK of the monsters equipped to it by this effect. Once per turn, during your Standby Phase: You can send 1 of these equipped monsters you control to the Graveyard; inflict damage to your opponent equal to that monster's original ATK. You cannot conduct your Battle Phase the turn you activate this effect."})
training_data.append({"class":"GOOD", "sentence":"Must be Synchro Summoned and cannot be Special Summoned by other ways. The first time each card you control would be destroyed each turn, by battle or card effect, it is not destroyed. Once per turn, during either player's turn, when your opponent activates a monster effect: You can negate that effect, and if you do, destroy 1 card on the field. You can banish this card from your Graveyard, then target 1 Level 8 or lower \"Stardust\" monster in your Graveyard; Special Summon it."})
#BAD
training_data.append({"class":"BAD", "sentence":"At the start of the Damage Step, if this card battles a Pendulum Monster: This card's ATK and DEF become half its current ATK and DEF until the end of the Damage Step."})
training_data.append({"class":"BAD", "sentence":"Cannot be Normal Summoned/Set. Must be Special Summoned (from your hand) by having 6 or more monsters with different Attributes in the GYs. Gains ATK/DEF equal to the number of different Attributes in the GYs x 500. When your opponent would Special Summon a monster(s) (Quick Effect): You can banish 3 monsters from your GY; negate the Summon, and if you do, destroy that monster(s)."})
training_data.append({"class":"BAD", "sentence":"This card cannot be Normal Summoned or Set. This card cannot be Special Summoned except with \"Assault Mode Activate\". If this card battles a monster, at the end of the Damage Step, inflict damage to your opponent equal to that monster's DEF and gain Life Points equal to its ATK. When this card on the field is destroyed, you can Special Summon 1 \"Hyper Psychic Blaster\" from your Graveyard."})
training_data.append({"class":"BAD", "sentence":"While you control a Level 10 or lower \"Flower Cardian\" monster: You can Special Summon this card from your hand, also you cannot Normal or Special Summon monsters for the rest of this turn, except \"Flower Cardian\" monsters. Once per turn: You can target 1 \"Flower Cardian\" monster in your Graveyard; shuffle it into your Deck, then draw 1 card."})
training_data.append({"class":"BAD", "sentence":"Cannot be Normal Summoned/Set. Must be Special Summoned (from your hand) by shuffling 10 other cards with different names from your hand and/or field into the Deck and/or Extra Deck, and cannot be Special Summoned by other ways. This card's Special Summon cannot be negated. If this card is Special Summoned: Shuffle all cards into the Deck, except this card, from each player's hand, field, Graveyard, and face-up Pendulum Monsters from the Extra Deck. Neither player can activate cards or effects in response to this effect's activation."})
training_data.append({"class":"BAD", "sentence":"Cannot be Normal Summoned/Set. Must be Special Summoned (from your hand) by banishing 1 face-up Ritual, Fusion, Synchro, and Xyz Monster from anywhere on the field, and cannot be Special Summoned by other ways. This card's Special Summon cannot be negated. When this card is Special Summoned: Banish all other cards from both players' hands, fields, and Graveyards. Cards and effects cannot be activated in response to this effect's activation."})
training_data.append({"class":"BAD", "sentence":"Discard 3 cards from your hand."})
training_data.append({"class":"BAD", "sentence":"Discard your entire hand."})
print ("%s sentences in training data" % len(training_data))

# TRAINING DATA HERE

# ORGANIZE DATA STRUCTURES

words = []
classes = []
documents = []
ignore_words = ['?']
# loop through each sentence in our training data
for pattern in training_data:
    # tokenize each word in the sentence
    w = nltk.word_tokenize(pattern['sentence'])
    # add to our words list
    words.extend(w)
    # add to documents in our corpus
    documents.append((w, pattern['class']))
    # add to our classes list
    if pattern['class'] not in classes:
        classes.append(pattern['class'])
# stem and lower each word and remove duplicates
words = [stemmer.stem(w.lower()) for w in words if w not in ignore_words]
words = list(set(words))
# remove duplicates
classes = list(set(classes))
# compute sigmoid nonlinearity
def sigmoid(x):
    output = 1/(1+np.exp(-x))
    return output
# convert output of sigmoid function to its derivative
def sigmoid_output_to_derivative(output):
    return output*(1-output)
def clean_up_sentence(sentence):
    # tokenize the pattern
    sentence_words = nltk.word_tokenize(sentence)
    # stem each word
    sentence_words = [stemmer.stem(word.lower()) for word in sentence_words]
    return sentence_words
# return bag of words array: 0 or 1 for each word in the bag that exists in the sentence
def bow(sentence, words, show_details=False):
    # tokenize the pattern
    sentence_words = clean_up_sentence(sentence)
    # bag of words
    bag = [0]*len(words)  
    for s in sentence_words:
        for i,w in enumerate(words):
            if w == s: 
                bag[i] = 1
                if show_details:
                    print ("found in bag: %s" % w)
    return(np.array(bag))
def think(sentence, show_details=False):
    x = bow(sentence.lower(), words, show_details)
    if show_details:
        print ("sentence:", sentence, "\n bow:", x)
    # input layer is our bag of words
    l0 = x
    # matrix multiplication of input and hidden layer
    l1 = sigmoid(np.dot(l0, synapse_0))
    # output layer
    l2 = sigmoid(np.dot(l1, synapse_1))
    return l2
# probability threshold
ERROR_THRESHOLD = 0.2
# load our calculated synapse values
synapse_file = 'synapses.json' 
with open(synapse_file) as data_file: 
    synapse = json.load(data_file) 
    synapse_0 = np.asarray(synapse['synapse0']) 
    synapse_1 = np.asarray(synapse['synapse1'])

# FUNCTION TO GET VALUE
def classify(sentence, show_details=False):
    results = think(sentence, show_details)

    results = [[i,r] for i,r in enumerate(results) if r>ERROR_THRESHOLD ] 
    results.sort(key=lambda x: x[1], reverse=True) 
    return_results =[[classes[r[0]],r[1]] for r in results]
    print ("%s \n classification: %s" % (sentence, return_results))
    return return_results
def getValue(results):
    if(results[0][0] == 'VERY GOOD'):
        return 3
    if(results[0][0] == 'GOOD'):
        return 2
    if(results[0][0] == 'BAD'):
        return 1
def main():
    teststring = sys.argv[1]
    value = getValue(classify(teststring))
    file = open("tmp.txt", "w")
    file.write(str(value))
    file.close()
    return
        
main()