/*
 * Copyright (C) Matthew Steglinski (SainttX) <matt@ipvp.org>
 * Copyright (C) contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package ru.ienumerable.volleyball.canvas.slot;

import java.util.function.Supplier;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import ru.ienumerable.volleyball.canvas.ClickInformation;
import ru.ienumerable.volleyball.canvas.template.ItemStackTemplate;


import java.util.Optional;

/**
 * A slot is a position held by an Inventory that can be interacted with by users.
 * <p>
 * A Menu slot always has the potential to be clicked by players, controlling
 * what interactions have any effect on the slot are made available through {@link ClickOptions}.
 */
public interface Slot {

    /**
     * Returns the slots index position inside the parent Menu.
     *
     * @return The slots position
     */
    int getIndex();

    /**
     * Returns the click interactions that the Slot permits.
     *
     * @return The slots options
     */
    ClickOptions getClickOptions();

    /**
     * Modifies the expected function of the slot when handling clicks.
     *
     * @param options The new slot options
     */
    void setClickOptions(ClickOptions options);

    /**
     * Returns the item generated by the template for this slot.
     *
     * @return the templated item
     * @deprecated superseded by {@link #getItem(Player)}
     * @throws UnsupportedOperationException if the template is not {@link ru.ienumerable.volleyball.canvas.template.StaticItemTemplate}.
     */
    @Deprecated
    ItemStack getItem();

    /**
     * Returns the item generated by the template for this slot, rendered
     * for a specific player.
     *
     * @param viewer player viewing the menu/slot
     * @return item rendered for the player
     */
    ItemStack getItem(Player viewer);

    /**
     * Sets a static item template for the slot.
     *
     * @param item The new item in the slot
     */
    void setItem(ItemStack item);

    /**
     * Sets a static item template for the slot using the item provided
     * by the supplier.
     *
     * @param item item supplier
     */
    void setItem(Supplier<ItemStack> item);

    /**
     * Sets the item template to render in the slot.
     *
     * @param item New item template
     */
    void setItemTemplate(ItemStackTemplate item);

    /**
     * Gets the raw item currently in the opened menu inventory a {@link Player}
     * has open.
     *
     * <p>This method differs from {@link #getItem()} or {@link #getItem(Player)}
     * because it does not return a templated or generated item. As an example,
     * this method can be used to retrieve items that a player may have inserted
     * into a Menu themselves (which would not be templates).
     *
     * @param viewer the player with open menu containing this slot
     * @return raw item
     * @throws IllegalStateException if player is not viewing the menu
     */
    ItemStack getRawItem(Player viewer);

    /**
     * Sets the raw item in the menu inventory a {@link Player} has open.
     *
     * <p>Note: when the menu is updated via {@link ru.ienumerable.volleyball.canvas.Menu#update(Player)} or
     * is reopened, the raw item set by this method will be overriden to whatever
     * is currently templated in the slot.
     *
     * @param viewer player with open menu containing this slot
     * @param item item to set
     * @throws IllegalStateException if player is not viewing the menu
     */
    void setRawItem(Player viewer, ItemStack item);

    /**
     * Returns a user-defined handler for when a Player clicks the slot.
     *
     * @return The click handler
     */
    Optional<ClickHandler> getClickHandler();

    /**
     * Sets a new handler policy for when a Player clicks the slot.
     *
     * @param handler The new click handler
     */
    void setClickHandler(ClickHandler handler);

    /**
     * Get the settings of the slot.
     *
     * @return slot settings
     */
    SlotSettings getSettings();

    /**
     * Set the settings of the slot.
     *
     * @param settings slot settings
     */
    void setSettings(SlotSettings settings);

    /**
     * A Slots click handler is a user defined function or policy that occurs when a
     * Player clicks on a slot.
     * <p>
     * Passed through are the player that clicked the slot as well as the information
     * regarding their click, including the clicked Menu as well as the slot clicked.
     */
    @FunctionalInterface
    interface ClickHandler {

        /**
         * Called when a Player successfully clicks on a slot with this handler. Users may
         * change the result a click will have in the underlying inventory by setting the
         * information in the passed through ClickInformation object.
         *
         * @param player The player that clicked the slot
         * @param click  Information about the performed click
         */
        void click(Player player, ClickInformation click);
    }
}
