/*
 * Catroid: An on-device visual programming system for Android devices
 * Copyright (C) 2010-2017 The Catrobat Team
 * (<http://developer.catrobat.org/credits>)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * An additional term exception under section 7 of the GNU Affero
 * General Public License, version 3, is available at
 * http://developer.catrobat.org/license_additional_term
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.catrobat.catroid.common;

import android.content.Context;
import android.widget.ArrayAdapter;

import org.catrobat.catroid.R;
import org.catrobat.catroid.content.BroadcastScript;
import org.catrobat.catroid.content.CollisionScript;
import org.catrobat.catroid.physics.PhysicsCollision;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class MessageContainer {

	private static Map<String, List<BroadcastScript>> receiverMap = new HashMap<>();
	private static Map<String, List<BroadcastScript>> backupReceiverMap = null;
	private static ArrayAdapter<String> messageAdapter = null;
	private static Map<String, List<CollisionScript>> collisionReceiverMap = new HashMap<>();
	private static int hiddenEntries = 0;

	// Suppress default constructor for noninstantiability
	private MessageContainer() {
		throw new AssertionError();
	}

	public static void clear() {
		receiverMap.clear();
		hiddenEntries = 0;
		messageAdapter = null;
	}

	public static void createBackup() {
		backupReceiverMap = receiverMap;
		receiverMap = new HashMap<>();
	}

	public static void clearBackup() {
		backupReceiverMap = null;
		messageAdapter = null;
	}

	public static void restoreBackup() {
		receiverMap = backupReceiverMap;
		backupReceiverMap = null;
	}

	public static void addMessage(String message) {
		if (message == null || message.isEmpty()) {
			return;
		}

		if (!PhysicsCollision.isCollisionBroadcastMessage(message)) {
			if (!receiverMap.containsKey(message)) {
				receiverMap.put(message, new ArrayList<BroadcastScript>());
				if (message.startsWith(Constants.RASPI_BROADCAST_PREFIX)) {
					hiddenEntries++;
				} else {
					addMessageToAdapter(message);
				}
			}
		} else {
			if (!collisionReceiverMap.containsKey(message)) {
				collisionReceiverMap.put(message, new ArrayList<CollisionScript>());
			}
		}
	}

	public static void addMessage(String message, BroadcastScript script) {
		if (message == null || message.isEmpty()) {
			return;
		}

		if (!PhysicsCollision.isCollisionBroadcastMessage(message)) {
			addMessage(message);
			receiverMap.get(message).add(script);
		} else if (script instanceof CollisionScript) {
			addMessage(message);
			collisionReceiverMap.get(message).add((CollisionScript) script);
		}
	}

	private static void addMessageToAdapter(String message) {
		if (messageAdapter != null && (messageAdapter.getPosition(message) < 0)) {
			messageAdapter.add(message);
		}
	}

	public static void removeReceiverScript(String message, BroadcastScript script) {
		if (message == null || message.isEmpty()) {
			return;
		}

		if (!PhysicsCollision.isCollisionBroadcastMessage(message)) {
			if (receiverMap.containsKey(message)) {
				receiverMap.get(message).remove(script);
			}
		} else if (collisionReceiverMap.containsKey(message) && script instanceof CollisionScript) {
			CollisionScript collisionScript = (CollisionScript) script;
			collisionReceiverMap.get(message).remove(collisionScript);
		}
	}

	public static ArrayAdapter<String> getMessageAdapter(Context context) {
		if (messageAdapter == null) {
			messageAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item);
			messageAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			messageAdapter.add(context.getString(R.string.new_broadcast_message));
			if (receiverMap.size() == hiddenEntries) {
				addMessage(context.getString(R.string.brick_broadcast_default_value));
			} else {
				for (String message : receiverMap.keySet()) {
					if (message.startsWith(Constants.RASPI_BROADCAST_PREFIX)) {
						continue;
					}
					addMessageToAdapter(message);
				}
			}
		}
		return messageAdapter;
	}

	public static int getPositionOfMessageInAdapter(Context context, String message) {
		if (messageAdapter == null) {
			getMessageAdapter(context);
		}
		return messageAdapter.getPosition(message);
	}

	public static String getFirst(Context context) {
		return getMessageAdapter(context).getItem(1);
	}

	public static void removeUnusedMessages(List<String> usedMessages) {
		messageAdapter = null;
		receiverMap = new HashMap<>();
		collisionReceiverMap = new HashMap<>();
		hiddenEntries = 0;

		for (String message : usedMessages) {
			addMessage(message);
		}

		if (messageAdapter != null) {
			Context context = messageAdapter.getContext();
			for (int index = 0; index < messageAdapter.getCount(); index++) {
				String message = messageAdapter.getItem(index);
				if (!message.equals(context.getString(R.string.new_broadcast_message))
						&& !usedMessages.contains(message)) {
					messageAdapter.remove(message);
				}
			}
		}
	}
}
