package com.grahamedgecombe.rs2.packet;

import com.grahamedgecombe.rs2.model.Location;
import com.grahamedgecombe.rs2.model.Player;
import com.grahamedgecombe.rs2.net.Packet;

/**
 * Handles player commands (the ::words).
 * @author Graham
 *
 */
public class CommandPacketHandler implements PacketHandler {

	@Override
	public void handle(Player player, Packet packet) {
		String commandString = packet.getRS2String();
		String[] args = commandString.split(" ");
		String command = args[0].toLowerCase();
		try {
			if(command.equals("tele")) {
				if(args.length == 3 || args.length == 4) {
					int x = Integer.parseInt(args[1]);
					int y = Integer.parseInt(args[2]);
					int z = player.getLocation().getZ();
					if(args.length == 4) {
						z = Integer.parseInt(args[3]);
					}
					player.setTeleportTarget(Location.create(x, y, z));
				} else {
					player.getActionSender().sendMessage("Syntax is ::tele [x] [y] [z].");
				}
			}
		} catch(Exception ex) {
			player.getActionSender().sendMessage("Error while processing command.");
		}
	}

}