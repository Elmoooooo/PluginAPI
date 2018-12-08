package de.elmo.api.misc;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class Title {
	public Title(String title, String subTitle, int fadeIn, int fadeOut, int stay, Player p) {
		IChatBaseComponent iChatBaseComponent = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + title + "\"}");
		PacketPlayOutTitle packetPlayOutTitle = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE,
				iChatBaseComponent);
		((CraftPlayer) p.getPlayer()).getHandle().playerConnection.sendPacket(packetPlayOutTitle);

		IChatBaseComponent iChatBaseComponent1 = IChatBaseComponent.ChatSerializer
				.a("{\"text\": \"" + subTitle + "\"}");
		PacketPlayOutTitle packetPlayOutTitle1 = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE,
				iChatBaseComponent1);
		((CraftPlayer) p.getPlayer()).getHandle().playerConnection.sendPacket(packetPlayOutTitle1);

		PacketPlayOutTitle packetPlayOutTitle2 = new PacketPlayOutTitle(20 * fadeIn, 20 * stay, 20 * fadeOut);
		((CraftPlayer) p.getPlayer()).getHandle().playerConnection.sendPacket(packetPlayOutTitle2);
	}

	public Title(String title, String subTitle, int fadeIn, int fadeOut, int stay) {
		for (Player online : org.bukkit.Bukkit.getOnlinePlayers()) {
			IChatBaseComponent iChatBaseComponent = IChatBaseComponent.ChatSerializer
					.a("{\"text\": \"" + title + "\"}");
			PacketPlayOutTitle packetPlayOutTitle = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE,
					iChatBaseComponent);
			((CraftPlayer) online.getPlayer()).getHandle().playerConnection.sendPacket(packetPlayOutTitle);

			IChatBaseComponent iChatBaseComponent1 = IChatBaseComponent.ChatSerializer
					.a("{\"text\": \"" + subTitle + "\"}");
			PacketPlayOutTitle packetPlayOutTitle1 = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE,
					iChatBaseComponent1);
			((CraftPlayer) online.getPlayer()).getHandle().playerConnection.sendPacket(packetPlayOutTitle1);

			PacketPlayOutTitle packetPlayOutTitle2 = new PacketPlayOutTitle(20 * fadeIn, 20 * stay, 20 * fadeOut);
			((CraftPlayer) online.getPlayer()).getHandle().playerConnection.sendPacket(packetPlayOutTitle2);
		}
	}
}
