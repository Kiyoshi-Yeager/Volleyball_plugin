# VolleyBall
This simple plugin is designed specifically for private servers. It allows you to create a ball and interact with it in the open world of minecraft.

![VolleyBall gameplay](https://github.com/IEnumerablee/Volleyball/blob/master/volleyball.gif?raw=true)
# Usage
- To create a ball, right-click on a live pufferfish while holding 8 pieces of leather in your hand.
- Throw the ball: Left-click (LMB).
- Throw the ball harder: Left-click (LMB) while running or jumping.
- Throw power slider: Hold SHIFT.
- Pick up the ball: Press SHIFT near the ball.
commands:
- /ballskin: Change the ball's skin.
- /ballsreload: Reload the plugin. Requires the volleyball.reload permission.
# Dependencies
The plugin requires **Canvas** to function. Since Canvas is not included in a pre-compiled form, I have bundled it with this version of the plugin.
# Adding a New Ball Skin
The plugin uses the official Minecraft API, so you need to use official Minecraft heads. For example, you can use a site like Minecraft-Heads. In the "url" field, you need to insert the set of characters at the end of the link. For example, for the link:
http://textures.minecraft.net/texture/93c2f1c5d2c8f0e33730c14dca1c1d1e1abd8596b0839d6738d18f46432b6fa6,
in the "url" field, insert:
93c2f1c5d2c8f0e33730c14dca1c1d1e1abd8596b0839d6738d18f46432b6fa6.

You can also set a permission requirement for using a specific ball skin. To do this, use the optional parameters perm and lock_msg. In perm, specify any permission, and in lock_msg, write the message that will be displayed if a player does not have access to this skin.
