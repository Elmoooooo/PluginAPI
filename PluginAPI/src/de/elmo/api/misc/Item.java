package de.elmo.api.misc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Item {
	private Material material;
	private int amount;
	private int shortID;
	private String displayname;
	private ArrayList<String> lore;
	private HashMap<Enchantment, Integer> enchantments;

	public Item(Material material) {
		this.material = material;
	}

	public ItemStack toItemStack() {
		ItemStack itemStack = new ItemStack(this.material, this.amount, (short) this.shortID);
		ItemMeta itemMeta = itemStack.getItemMeta();
		itemMeta.setDisplayName(this.displayname);
		itemMeta.setLore(this.lore);

		for (Map.Entry<Enchantment, Integer> e : itemMeta.getEnchants().entrySet()) {
			itemMeta.removeEnchant((Enchantment) e.getKey());
		}

		for (Map.Entry<Enchantment, Integer> e : this.enchantments.entrySet()) {
			itemMeta.addEnchant((Enchantment) e.getKey(), ((Integer) e.getValue()).intValue(), true);
		}

		itemStack.setItemMeta(itemMeta);

		return itemStack;
	}

	public void addToLore(String loreName) {
		if (!this.lore.contains(loreName)) {
			this.lore.add(loreName);
		}
	}

	public void removeFromLore(String loreName) {
		if (this.lore.contains(loreName)) {
			this.lore.remove(loreName);
		}
	}

	public void addToEnchantments(Enchantment enchantment, int level) {
		if (!this.enchantments.containsKey(enchantment)) {
			this.enchantments.put(enchantment, Integer.valueOf(level));
		}
	}

	public void removeFromEnchantments(Enchantment enchantment) {
		if (this.enchantments.containsKey(enchantment)) {
			this.enchantments.remove(enchantment);
		}
	}

	public Item setAmount(int amount) {
		this.amount = amount;
		return this;
	}

	public Item setShortID(int shortID) {
		this.shortID = shortID;
		return this;
	}

	public Item setDisplayname(String displayname) {
		this.displayname = displayname;
		return this;
	}

	public Material getMaterial() {
		return this.material;
	}

	public int getAmount() {
		return this.amount;
	}

	public int getShortID() {
		return this.shortID;
	}

	public String getDisplayname() {
		return this.displayname;
	}

	public ArrayList<String> getLore() {
		return this.lore;
	}

	public HashMap<Enchantment, Integer> getEnchantments() {
		return this.enchantments;
	}
}
