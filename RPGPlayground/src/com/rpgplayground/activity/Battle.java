package com.rpgplayground.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.rpgplayground.R;
import com.rpgplayground.character.Character;
import com.rpgplayground.character.EnemyCharacter;
import com.rpgplayground.character.ability.Ability;
import com.rpgplayground.character.ability.AbilitySelector;
import com.rpgplayground.character.chclass.CharacterClassChoice;
import com.rpgplayground.character.equipment.ArmorSlot;
import com.rpgplayground.character.item.Armor;
import com.rpgplayground.character.item.Item;
import com.rpgplayground.character.item.tools.ItemChooser;
import com.rpgplayground.character.item.tools.ItemChooser.ChooserOption;
import com.rpgplayground.character.tools.BattleHelper;
import com.rpgplayground.character.tools.EnemySelector;

public class Battle extends Activity implements View.OnClickListener {

	// region Fields
	String ver = "0.1.3";
	String verInfo;
	ViewFlipper vf;
	TabHost th;

	// needed for Dialog
	final Context context = this;

	Button start, reset, setName, heal, run, rest, meItem, e1Item,
			selectName, selectHead, selectChest, selectArms, selectLegs;
	ImageButton attack;
	TextView e1Name, e1Class, e1Health, e1Energy, e1tv, meName, meClass,
			meHealth, meEnergy, metv, meXPtext, combatLog, nName, headName,
			headArmor, headBoost, headGValue, chestName, chestArmor,
			chestBoost, chestGValue, worldNewsText;
	EditText editName;
	ProgressBar e1HPb, e1EPb, meHPb, meEPb;

	BattleHelper bh = new BattleHelper();
	EnemyCharacter e1 = new EnemyCharacter();
	Character me = new Character();
	int meXP = 0;
	String e1s = "------";
	String combatLogString = "";

	Boolean firstRun = true;
	Boolean worldNewsIsVisible = false;

	HorizontalScrollView worldNews;

	// endregion Fields

	/**
	 * Initialization
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.phone);
		initialize();
		welcomeDialog();
	}

	private void welcomeDialog() {
		final Dialog whatsNewDialog = new Dialog(context);
		whatsNewDialog.setContentView(R.layout.whatsnew);
		whatsNewDialog.setTitle("New since last version...");

		TextView versionNumber = (TextView) whatsNewDialog
				.findViewById(R.id.tvVersionNumber);
		TextView versionInfo = (TextView) whatsNewDialog
				.findViewById(R.id.tvVersionInfo);
		Button closeDialog = (Button) whatsNewDialog
				.findViewById(R.id.bWhatsNewClose);
		closeDialog.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// custom dialog
				whatsNewDialog.dismiss();
			}
		});
		versionNumber.setText(ver);
		versionInfo.setText(buildVersionInfo());
		whatsNewDialog.show();
	}

	// region Initialize
	private void initialize() {
		setName = (Button) findViewById(R.id.bSetName);
		start = (Button) findViewById(R.id.bStart);
		reset = (Button) findViewById(R.id.bReset);
		attack = (ImageButton) findViewById(R.id.bAttack);
		// attack Dialog initialization
		attack.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				// custom dialog
				final Dialog dialog = new Dialog(context);
				dialog.setContentView(R.layout.attack_dialog);
				dialog.setTitle("Choose Your Action...");

				OnClickListener ocl = new OnClickListener() {
					public void onClick(View v) {
						switch (v.getId()) {
						case R.id.ad1button:
							turns("attack");
							break;
						case R.id.ad2button:
							turns("whirlwind");
							break;
						}

						dialog.dismiss();
					}
				};

				// set the custom dialog components - text, image and button
				addAPlayerAbility(dialog, ocl, R.id.ad1image,
						R.drawable.ic_launcher, R.id.ad1text,
						"Main and OffHand or TwoHand Attack", R.id.ad1button,
						"Primary Attack");
				addAPlayerAbility(dialog, ocl, R.id.ad2image,
						R.drawable.ic_launcher, R.id.ad2text,
						"Attacks all targets for 75% damage", R.id.ad2button,
						"Whirlwind");
				// ImageView dImage1 = (ImageView) dialog
				// .findViewById(R.id.ad1image);
				// dImage1.setImageResource(R.drawable.ic_launcher);
				// TextView dText1 = (TextView)
				// dialog.findViewById(R.id.ad1text);
				// dText1.setText("Main and OffHand or TwoHand Attack");
				//
				// Button dButton1 = (Button)
				// dialog.findViewById(R.id.ad1button);
				// dButton1.setText("Primary Attack");
				// // if button is clicked, close the custom dialog
				// dButton1.setOnClickListener(ocl);

				// ImageView dImage2 = (ImageView) dialog
				// .findViewById(R.id.ad2image);
				// dImage2.setImageResource(R.drawable.ic_launcher);
				// TextView dText2 = (TextView)
				// dialog.findViewById(R.id.ad2text);
				// dText2.setText("Attacks all targets for 75% damage");
				//
				// Button dButton2 = (Button)
				// dialog.findViewById(R.id.ad2button);
				// dButton2.setText("Whirlwind");
				// // if button is clicked, close the custom dialog
				// dButton2.setOnClickListener(ocl);

				dialog.show();
			}
		});
		heal = (Button) findViewById(R.id.bHeal);
		run = (Button) findViewById(R.id.bRun);
		rest = (Button) findViewById(R.id.bRest);
		e1Item = (Button) findViewById(R.id.bE1Item);
		meItem = (Button) findViewById(R.id.bMeItem);

		selectName = (Button) findViewById(R.id.bSelectName);
		selectHead = (Button) findViewById(R.id.bSelectHead);
		selectChest = (Button) findViewById(R.id.bSelectChest);
		selectArms = (Button) findViewById(R.id.bSelectArms);
		selectLegs = (Button) findViewById(R.id.bSelectLegs);

		e1Name = (TextView) findViewById(R.id.tvE1Name);
		e1Class = (TextView) findViewById(R.id.tvE1Class);
		e1Health = (TextView) findViewById(R.id.tvE1Health);
		e1Energy = (TextView) findViewById(R.id.tvE1Energy);
		meName = (TextView) findViewById(R.id.tvMeName);
		meClass = (TextView) findViewById(R.id.tvMeClass);
		meHealth = (TextView) findViewById(R.id.tvMeHealth);
		meEnergy = (TextView) findViewById(R.id.tvMeEnergy);
		metv = (TextView) findViewById(R.id.tvMeString);
		e1tv = (TextView) findViewById(R.id.tvE1String);
		meXPtext = (TextView) findViewById(R.id.tvMeXP);
		combatLog = (TextView) findViewById(R.id.tvCombatLog);
		nName = (TextView) findViewById(R.id.tvName);
		headName = (TextView) findViewById(R.id.tvHeadName);
		headArmor = (TextView) findViewById(R.id.tvHeadArmorV);
		headBoost = (TextView) findViewById(R.id.tvHeadAllBoosts);
		headGValue = (TextView) findViewById(R.id.tvHeadGValue);
		chestName = (TextView) findViewById(R.id.tvChestName);
		chestArmor = (TextView) findViewById(R.id.tvChestArmorV);
		chestBoost = (TextView) findViewById(R.id.tvChestAllBoosts);
		chestGValue = (TextView) findViewById(R.id.tvChestGValue);

		editName = (EditText) findViewById(R.id.etName);

		e1HPb = (ProgressBar) findViewById(R.id.pbE1Health);
		e1EPb = (ProgressBar) findViewById(R.id.pbE1Energy);
		meHPb = (ProgressBar) findViewById(R.id.pbMeHealth);
		meEPb = (ProgressBar) findViewById(R.id.pbMeEnergy);

		worldNews = (HorizontalScrollView) findViewById(R.id.lWorldNews);
		worldNewsText = (TextView) findViewById(R.id.tvWorldNews);
		worldNewsText.setOnClickListener(this);

		setName.setOnClickListener(this);
		start.setOnClickListener(this);
		reset.setOnClickListener(this);
		// attack.setOnClickListener(this);
		heal.setOnClickListener(this);
		run.setOnClickListener(this);
		rest.setOnClickListener(this);
		e1Item.setOnClickListener(this);
		meItem.setOnClickListener(this);

		selectName.setOnClickListener(this);
		selectHead.setOnClickListener(this);
		selectChest.setOnClickListener(this);
		selectArms.setOnClickListener(this);
		selectLegs.setOnClickListener(this);

		initTabHost();
		initViewFlipper();

		AbilitySelector as = new AbilitySelector();
		List<Ability> characterAbilities = new ArrayList<Ability>();
		characterAbilities.add(as.getAbility(10101));
		characterAbilities.add(as.getAbility(10201));
		initializeCharacter(me, CharacterClassChoice.Fighter, 20, 12, 2, 10,
				characterAbilities);

		startWorldNewsTimer();

	}

	private void startWorldNewsTimer() {
		Thread timerStart = new Thread() {
			public void run() {
				try {
					sleep(10000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					worldNewsIsVisible = true;
				}
			}
		};
		timerStart.start();
	}

	private void initTabHost() {

		th = (TabHost) findViewById(R.id.tabhost);
		th.setup();

		TabSpec specs = th.newTabSpec("tag1");
		specs.setContent(R.id.tab1);
		specs.setIndicator("Character");
		th.addTab(specs);

		specs = th.newTabSpec("tag2");
		specs.setContent(R.id.tab2);
		specs.setIndicator("Battle");
		th.addTab(specs);

		specs = th.newTabSpec("tag3");
		specs.setContent(R.id.tab3);
		specs.setIndicator("Map");
		th.addTab(specs);

		specs = th.newTabSpec("tag4");
		specs.setContent(R.id.tab4);
		specs.setIndicator("World");
		th.addTab(specs);

	}

	private void initViewFlipper() {
		vf = (ViewFlipper) findViewById(R.id.viewFlipper1);
		vf.setOnClickListener(this);
	}

	// endregion Initialize
	/**
	 * onPause() / onClick()
	 */
	@Override
	protected void onPause() {
		super.onPause();
		finish();
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bStart:
			startround();
			break;

		// R.id.bAttack does not use the same onClickListener
		case R.id.bHeal:
			if (me.getCurrentEnergy() > 0) {
				turns("heal");
			} else {
				Toast.makeText(getApplicationContext(), "Out of Energy",
						Toast.LENGTH_SHORT).show();
			}
			break;

		case R.id.bRun:
			turns("run");
			break;

		case R.id.bRest:
			turns("rest");
			break;

		case R.id.bSetName:
			String name = editName.getText().toString();
			meName.setText(name);
			nName.setText(name);
			me.setName(name);
			selectName.setText(name);

			editName.setVisibility(View.GONE);
			setName.setVisibility(View.GONE);

			// hide the keyboard when done
			InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
			mgr.hideSoftInputFromWindow(meName.getWindowToken(), 0);

			break;

		case R.id.bReset:
			firstRun = true;
			Toast.makeText(getApplicationContext(), "Currently Disabled",
					Toast.LENGTH_SHORT).show();
			break;

		case R.id.bMeItem:
			setEquippedItems(me);
			updateDisplay();
			break;

		case R.id.bE1Item:
			setEquippedItems(e1);
			updateDisplay();
			break;

		case R.id.bSelectName:
			vf.setDisplayedChild(0);
			break;

		case R.id.bSelectHead:
			vf.setDisplayedChild(1);
			break;

		case R.id.bSelectChest:
			vf.setDisplayedChild(2);
			break;

		case R.id.bSelectArms:
			vf.setDisplayedChild(3);
			break;

		case R.id.bSelectLegs:
			vf.setDisplayedChild(4);
			break;

		case R.id.tvWorldNews:
			worldNewsIsVisible = false;
			break;

		}
		if (worldNewsIsVisible) {
			worldNews.setVisibility(View.VISIBLE);
			worldNewsText.setFocusable(true);
			worldNewsText.setFocusableInTouchMode(true);
			worldNewsText.setSelected(true);
		} else {
			worldNews.setVisibility(View.INVISIBLE);
		}
	}

	// region Game Methods
	/**
	 * Game Private Methods
	 */
	private void startround() {

		EnemySelector es = new EnemySelector();
		e1 = es.getNewEnemy();

		if (firstRun) {
			// set player stats and xp to default
		} else {
			// Sets item to enemy
			// enemy1Item = setItem(true);
			// e1Item.setText(enemy1Item.getName());
		}
		firstRun = false;

		updateDisplay();
	}

	private void updateDisplay() {
		// Display enemy stats on-screen
		e1Health.setText(e1.getCurrentHealth() + " / " + e1.getMaxHealth());
		e1Energy.setText(e1.getCurrentEnergy() + " / " + e1.getMaxEnergy());
		e1Name.setText(e1.getName());
		e1Class.setText(e1.getCharacterClassChoice().toString());
		if (e1.getMaxHealth() <= 0) {
			e1HPb.setMax(1);
			e1HPb.setProgress(0);
		} else {
			e1HPb.setMax(e1.getMaxHealth());
			e1HPb.setProgress(e1.getCurrentHealth());
		}
		if (e1.getMaxEnergy() <= 0) {
			e1EPb.setMax(1);
			e1EPb.setProgress(0);
		} else {
			e1EPb.setMax(e1.getMaxEnergy());
			e1EPb.setProgress(e1.getCurrentEnergy());
		}
		e1tv.setText(e1.toString());

		// Display my stats on-screen
		meHealth.setText(me.getCurrentHealth() + " / " + me.getMaxHealth());
		meEnergy.setText(me.getCurrentEnergy() + " / " + me.getMaxEnergy());
		meName.setText(me.getName());
		meClass.setText(me.getCharacterClassChoice().toString());
		if (me.getMaxHealth() <= 0) {
			meHPb.setMax(1);
			meHPb.setProgress(0);
		} else {
			meHPb.setMax(me.getMaxHealth());
			meHPb.setProgress(me.getCurrentHealth());
		}
		if (me.getMaxEnergy() <= 0) {
			meEPb.setMax(1);
			meEPb.setProgress(0);
		} else {
			meEPb.setMax(me.getMaxEnergy());
			meEPb.setProgress(me.getCurrentEnergy());
		}
		metv.setText(me.toString());
		meXPtext.setText("XP: " + Integer.toString(meXP));

	}

	private CharSequence buildCombatLog(Character c, int abilityId, Boolean last) {

		// TODO now only functions correctly when successful

		StringBuilder sb = new StringBuilder();
		if (c == me) {

			sb.append(bh.getAbilityTotalDamage(abilityId) + " - "
					+ bh.getDefenderTotalResistance() + " = "
					+ bh.useAbilityAttack(e1, me, abilityId) + " damage2enemy");

			// old process
			// sb.append(meTAttack + " - " + e1TDefend + " = " + meSuccessD
			// + " damage2enemy");
			sb.append("\n");
		} else if (c == e1) {

			sb.append(bh.getAbilityTotalDamage(abilityId) + " - "
					+ bh.getDefenderTotalResistance() + " = "
					+ bh.useAbilityAttack(e1, me, abilityId) + " damage2you");

			// old process
			// sb.append(e1TAttack + " - " + meTDefend + " = " + e1SuccessD
			// + " damage2you");
			sb.append("\n");
		}

		if (last) {
			sb.append("\n");
		}

		combatLogString += "" + sb;
		return combatLogString;
	}

	/**
	 * @param Character
	 *            c, int health, int energy, int damage, int healing
	 */
	private void initializeCharacter(Character c, CharacterClassChoice myClass,
			int health, int energy, int damage, int healing,
			List<Ability> characterAbilities) {

		// Set the stats
		c.setStartingHealth(health);
		c.setStartingEnergy(energy);
		c.setBaseDamage(damage);
		c.setBaseHealing(healing);
		c.setId(0);
		c.setCharacterClassChoice(myClass);

		setEquippedItems(c);
		c.overwriteAllCurrentAbilities(characterAbilities);
		if (c == me) {
			meXP = 0;
		}
		// TODO migrate initializeCharacter() to CreateCharacter()
	}

	private void setEquippedItems(Character c) {
		List<Item> bundleOfItems = createBundleOfItems();
		// TODO need a more automated loop for setting these setText()s
		for (Item item : bundleOfItems) {
			if (item.getType() == "armor") {
				Armor armor = (Armor) item;
				if (armor.getArmorSlot() == ArmorSlot.Head) {
					selectHead.setText(armor.getName());
					headName.setText(armor.getName());
					headArmor.setText("Armor: "
							+ (armor.getArmorDefenseValue()));
					headBoost.setText("Inc. Resistance: "
							+ armor.getResistance() + "\n" + "Inc. Damage: "
							+ armor.getDamage());
					headGValue.setText("GoldValue: " + (armor.getGoldValue()));
				} else if (armor.getArmorSlot() == ArmorSlot.Chest) {
					selectChest.setText(armor.getName());
					chestName.setText(armor.getName());
					chestArmor.setText("Armor: "
							+ (armor.getArmorDefenseValue()));
					chestBoost.setText("Inc. Resistance: "
							+ armor.getResistance() + "\n" + "Inc. Damage: "
							+ armor.getDamage());
					chestGValue.setText("GoldValue: " + (armor.getGoldValue()));
				}
			}
		}
		c.overwriteAllCurrentEquippedItems(bundleOfItems);
	}

	private List<Item> createBundleOfItems() {
		ItemChooser ic = new ItemChooser(ChooserOption.random);
		List<Item> bundleOfItems = new ArrayList<Item>();
		// Create a bundle of temporary items
		for (Item item : ic.getNewRandomWeapons()) {
			bundleOfItems.add(item);
		}
		bundleOfItems.add(ic.getNewRandomItem(ArmorSlot.Chest));
		bundleOfItems.add(ic.getNewRandomItem(ArmorSlot.Head));
		bundleOfItems.add(ic.getNewRandomItem(ArmorSlot.Arms));
		bundleOfItems.add(ic.getNewRandomItem(ArmorSlot.Legs));
		bundleOfItems.add(ic.getNewItem(1001));
		return bundleOfItems;
	}

	private void turns(String playerCommand) {

		/**
		 * // "heal" chosen if (playerCommand == "heal") {
		 * me.setCurrentHealth(me.getCurrentHealth() + me.getBaseHealing());
		 * me.setCurrentEnergy(me.getCurrentEnergy() - 1); }
		 */

		// "rest" chosen
		if (playerCommand == "rest") {
			me.setCurrentEnergy(me.getCurrentEnergy() + 5);
		}

		if (e1.getCurrentHealth() > 0 && me.getCurrentHealth() > 0) {

			// Player turn
			// "run" chosen
			if (playerCommand == "run") {
				enemyTurn();
				resetEnemy();
				Toast.makeText(getApplicationContext(),
						"You are hit in the back as you flee.",
						Toast.LENGTH_SHORT).show();
			}

			// "attack" chosen
			if (playerCommand == "attack") {
				e1.takeDamage(bh.useAbilityAttack(me, e1, 10101));
				combatLog.setText(buildCombatLog(me, 10101, false));
			}
			// "whirlwind" chosen (remove later)
			if (playerCommand == "whirlwind") {
				e1.takeDamage(bh.useAbilityAttack(me, e1, 10201));
				me.spendEnergy(3);
				combatLog.setText(buildCombatLog(me, 10201, false));
			}

			enemyTurn();
		}

		updateDisplay();
	}

	private void enemyTurn() {
		// Enemy1 turn

		if (e1.getCurrentHealth() > 0) {
			me.takeDamage(bh.useAbilityAttack(e1, me, 10101));
			combatLog.setText(buildCombatLog(e1, 10101, true));
		} else {
			Toast.makeText(getApplicationContext(), "You win",
					Toast.LENGTH_SHORT).show();
			meXP += e1.getXPGain();
			resetEnemy();
		}
		if (me.getCurrentHealth() <= 0) {
			Toast.makeText(getApplicationContext(), "You lose",
					Toast.LENGTH_SHORT).show();
			youLose();
			meXP = 0;
		}
	}

	private void resetEnemy() {
		e1 = new EnemyCharacter("-", 0, CharacterClassChoice.noClass, 0, 0, 0,
				0, 0);
		updateDisplay();
	}

	private void youLose() {
		// Thread timer = new Thread() {
		// public void run() {
		// try {
		// sleep(5000);
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// } finally {
		// Intent openStartingPoint = new Intent(
		// "com.rpgplayground.activities.MENU");
		// startActivity(openStartingPoint);
		// }
		// }
		// };
		// timer.start();
	}

	// endregion Game Methods

	private void addAPlayerAbility(Dialog dialog, OnClickListener ocl,
			int imageId, int image, int textId, String description,
			int buttonId, String abilityTitle) {
		ImageView newImageView = (ImageView) dialog.findViewById(imageId);
		newImageView.setImageResource(image);
		TextView newTextView = (TextView) dialog.findViewById(textId);
		newTextView.setText(description);

		Button newButtonView = (Button) dialog.findViewById(buttonId);
		newButtonView.setText(abilityTitle);
		// if button is clicked, close the custom dialog
		newButtonView.setOnClickListener(ocl);
	}

	private CharSequence buildVersionInfo() {
		StringBuilder infoBuilder = new StringBuilder();
		List<String> allStrings = new ArrayList<String>();
		allStrings.add(new String("--on Ver. 0.1.3--"));
		allStrings.add(new String(
				"[What's New] has been created to explain what is new."));
		allStrings.add(new String("The Battle Tab has a new look! "
				+ "Everything isn't perfect, "
				+ "but it is closer to the final design."));
		allStrings.add(new String(
				"Look for Trouble is now inside the Log drawer, "
						+ "and it must be clicked before attack works."));
		allStrings
				.add(new String(
						"Heal, Rest, and Run are currently missing. That isn't accidental."));
		allStrings.add(new String(
				"Reset no longer works, as the functionality is in transition. "
						+ "Restart the app to play again."));
		allStrings
				.add(new String(
						"Gargoyle and Stone Golem now hit much harder, "
								+ "making the player take some damage in almost all circumstances."));
		allStrings
				.add(new String(
						"Whirlwind now performs correctly and costs energy, as intended."));
		allStrings
				.add(new String(
						"The World News ticker is not yet working correctly. "
								+ "However, it does show up. To clear it, click on it."));

		for (String s : allStrings) {
			infoBuilder.append("    " + s);
			infoBuilder.append("\n");
		}

		return infoBuilder.toString();
	}
}