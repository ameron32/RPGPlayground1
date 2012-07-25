package com.rpgplayground.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.rpgplayground.event.ButtonEvent;
import com.rpgplayground.event.ButtonEventListener;
//import com.rpgplayground.layout.EnemyInterface;
import com.rpgplayground.layout.LayoutPackage;

public class Battle extends Activity implements OnClickListener,
		ButtonEventListener {

	// region fields
	String ver = "0.1.3";
	String verInfo = "";
	String e1s = "------";
	String combatLogString = "";
	ViewFlipper vf;
	TabHost th;

	// needed for Dialog
	final Context context = this;
	// LayoutManager lm;

	Button start, reset, setName, heal, run, rest, meItem, e1Item, selectName,
			selectHead, selectChest, selectArms, selectLegs;

	// region TESTa
	// new instance of enemy TEST
	List<EnemyInterface> enemyInterfaces = new ArrayList<EnemyInterface>();
	// List<Button> allEnemyIncludeButtons = new ArrayList<Button>();
	// List<ImageButton> allEnemyIncludeImageButtons = new
	// ArrayList<ImageButton>();
	// List<TextView> allEnemyIncludeTextViews = new ArrayList<TextView>();
	// List<ProgressBar> allEnemyIncludeProgressBars = new
	// ArrayList<ProgressBar>();
	LinearLayout moreEnemies;
	// View genericEnemyView;
	// endregion TESTa

	ImageButton attack, e1Profile, meHeal;
	TextView e1Name, e1Class, e1Health, e1Energy, eTV, meName, meClass,
			meHealth, meEnergy, metv, meXPtext, combatLog, nName, headName,
			headArmor, headBoost, headGValue, chestName, chestArmor,
			chestBoost, chestGValue, worldNewsText;
	EditText editName;
	ProgressBar e1HPb, e1EPb, meHPb, meEPb;

	BattleHelper bh = new BattleHelper();
	// TODO depreciate e1
	// EnemyCharacter enemyCharacter1 = new EnemyCharacter();
	// TODO List<EnemyCharacter> activeAllies = new arrayList<EnemyCharacter>();
	// TODO convert Character me to PlayerCharacter me
	Character me = new Character();
	// TODO need to imbed meXP into PlayerCharacter
	int meXP = 0;

	// needed for WorldNews
	Boolean worldNewsIsVisible = false;
	LinearLayout worldNews;

	// endregion fields

	// region initialization
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.phone);
		initialize();
		welcomeDialog();
	}

	private void initialize() {
		// lm = new LayoutManager(context, R.layout.enemylayout);
		moreEnemies = (LinearLayout) findViewById(R.id.llMoreEnemies);

		setName = (Button) findViewById(R.id.bSetName);
		start = (Button) findViewById(R.id.bStart);
		reset = (Button) findViewById(R.id.bReset);

		// attack = (ImageButton) findViewById(R.id.ibAttack);
		// attack Dialog initialization

		// attack.setOnClickListener(new OnClickListener() {
		// public void onClick(View v) {
		// final Dialog dialog = new Dialog(context);
		// dialog.setContentView(R.layout.attack_dialog);
		// dialog.setTitle("Choose Your Action...");
		//
		// OnClickListener ocl = new OnClickListener() {
		// public void onClick(View v) {
		// switch (v.getId()) {
		// case R.id.ad1button:
		// turns("attack", "");
		// break;
		// case R.id.ad2button:
		// turns("whirlwind", "");
		// break;
		// }
		//
		// dialog.dismiss();
		// }
		// };
		//
		// addAPlayerAbility(dialog, ocl, R.id.ad1image,
		// R.drawable.ic_launcher, R.id.ad1text,
		// "Main and OffHand or TwoHand Attack", R.id.ad1button,
		// "Primary Attack");
		// addAPlayerAbility(dialog, ocl, R.id.ad2image,
		// R.drawable.ic_launcher, R.id.ad2text,
		// "Attacks all targets for 75% damage", R.id.ad2button,
		// "Whirlwind");
		// ImageView dImage1 = (ImageView) dialog
		// .findViewById(R.id.ad1image);
		// dImage1.setImageResource(R.drawable.ic_launcher);
		// TextView dText1 = (TextView) dialog.findViewById(R.id.ad1text);
		// dText1.setText("Main and OffHand or TwoHand Attack");
		// Button dButton1 = (Button) dialog.findViewById(R.id.ad1button);
		// dButton1.setText("Primary Attack");
		// dButton1.setOnClickListener(ocl);
		//
		// ImageView dImage2 = (ImageView) dialog
		// .findViewById(R.id.ad2image);
		// dImage2.setImageResource(R.drawable.ic_launcher);
		// TextView dText2 = (TextView) dialog.findViewById(R.id.ad2text);
		// dText2.setText("Attacks all targets for 75% damage");
		// Button dButton2 = (Button) dialog.findViewById(R.id.ad2button);
		// dButton2.setText("Whirlwind");
		// dButton2.setOnClickListener(ocl);
		//
		// dialog.show();
		// }
		// });

		heal = (Button) findViewById(R.id.bHeal);
		meHeal = (ImageButton) findViewById(R.id.ibMeHeal);
		run = (Button) findViewById(R.id.bRun);
		rest = (Button) findViewById(R.id.bRest);
		e1Item = (Button) findViewById(R.id.bE1Item);
		meItem = (Button) findViewById(R.id.bMeItem);

		// e1Profile = (ImageButton) findViewById(R.id.ibE1Profile);

		selectName = (Button) findViewById(R.id.bSelectName);
		selectHead = (Button) findViewById(R.id.bSelectHead);
		selectChest = (Button) findViewById(R.id.bSelectChest);
		selectArms = (Button) findViewById(R.id.bSelectArms);
		selectLegs = (Button) findViewById(R.id.bSelectLegs);

		// e1Name = (TextView) findViewById(R.id.tvE1Name);
		// e1Class = (TextView) findViewById(R.id.tvE1Class);
		// e1Health = (TextView) findViewById(R.id.tvE1Health);
		// e1Energy = (TextView) findViewById(R.id.tvE1Energy);
		meName = (TextView) findViewById(R.id.tvMeName);
		meClass = (TextView) findViewById(R.id.tvMeClass);
		meHealth = (TextView) findViewById(R.id.tvMeHealth);
		meEnergy = (TextView) findViewById(R.id.tvMeEnergy);
		metv = (TextView) findViewById(R.id.tvMeString);
		eTV = (TextView) findViewById(R.id.tvE1String);
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

		// e1HPb = (ProgressBar) findViewById(R.id.pbE1Health);
		// e1EPb = (ProgressBar) findViewById(R.id.pbE1Energy);
		meHPb = (ProgressBar) findViewById(R.id.pbMeHealth);
		meEPb = (ProgressBar) findViewById(R.id.pbMeEnergy);

		worldNews = (LinearLayout) findViewById(R.id.lWorldNews);
		worldNewsText = (TextView) findViewById(R.id.tvWorldNews);
		worldNewsText.setOnClickListener(this);

		setName.setOnClickListener(this);
		start.setOnClickListener(this);
		reset.setOnClickListener(this);
		heal.setOnClickListener(this);
		meHeal.setOnClickListener(this);
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
		initPlayer();

		startWorldNewsTimer();

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

	private void initPlayer() {
		AbilitySelector as = new AbilitySelector();
		List<Ability> characterAbilities = new ArrayList<Ability>();
		characterAbilities.add(as.getAbility(10101));
		characterAbilities.add(as.getAbility(10201));
		initializeCharacter(me, CharacterClassChoice.Fighter, 20, 12, 2, 10,
				characterAbilities);
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

	// endregion initialization

	// region onPause/onClick
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

		// R.id.ibAttack does not use the same onClickListener
		case R.id.bHeal:
			if (me.getCurrentEnergy() > 0) {
				turns("heal", "me");
			} else {
				Toast.makeText(getApplicationContext(), "Out of Energy",
						Toast.LENGTH_SHORT).show();
			}
			break;

		case R.id.ibMeHeal:
			if (me.getCurrentEnergy() > 0) {
				// healing is broken
				// turns("heal");
				makeToast("Healing is Currently Disabled (Broken).", "short");
			} else {
				Toast.makeText(getApplicationContext(), "Out of Energy",
						Toast.LENGTH_SHORT).show();
			}
			break;

		case R.id.bRun:
			turns("run", "me");
			break;

		case R.id.bRest:
			turns("rest", "me");
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
			Toast.makeText(getApplicationContext(), "Currently Disabled",
					Toast.LENGTH_SHORT).show();
			break;

		case R.id.bMeItem:
			setEquippedItems(me);
			updateDisplay();
			break;

		case R.id.bE1Item:
			for (EnemyInterface ei : enemyInterfaces) {
				setEquippedItems(ei.getEnemyCharacter());
			}
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

	// endregion onPause/onClick

	// region Game Methods
	/**
	 * Game Private Methods
	 */
	private void startround() {
		removeAllEnemyInterfaces();
		EnemySelector es = new EnemySelector();
		// TODO depreciate e1
		Random r = new Random();
		int numberOfEnemies = r.nextInt(5) + 1;
		for (int x = 0; x < numberOfEnemies; x++) {
			EnemyCharacter ec = es.getNewEnemy();
			enemyInterfaces.add(createEnemyInterface(x, ec));
		}

		updateDisplay();
	}

	private void updateDisplay() {
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

		// Display enemy stats on-screen
		// e1Health.setText(enemyCharacter1.getCurrentHealth() + " / " +
		// enemyCharacter1.getMaxHealth());
		// e1Energy.setText(enemyCharacter1.getCurrentEnergy() + " / " +
		// enemyCharacter1.getMaxEnergy());
		// e1Name.setText(enemyCharacter1.getName());
		// e1Class.setText(enemyCharacter1.getCharacterClassChoice().toString());
		// e1Profile.setImageResource(((EnemyCharacter)
		// enemyCharacter1).getImageResource());
		// if (enemyCharacter1.getMaxHealth() <= 0) {
		// e1HPb.setMax(1);
		// e1HPb.setProgress(0);
		// } else {
		// e1HPb.setMax(enemyCharacter1.getMaxHealth());
		// e1HPb.setProgress(enemyCharacter1.getCurrentHealth());
		// }
		// if (enemyCharacter1.getMaxEnergy() <= 0) {
		// e1EPb.setMax(1);
		// e1EPb.setProgress(0);
		// } else {
		// e1EPb.setMax(enemyCharacter1.getMaxEnergy());
		// e1EPb.setProgress(enemyCharacter1.getCurrentEnergy());
		// }
		// lm.loadParent(R.layout.enemylayout);
		// lm.updateDisplay(e1);

		String eTVString = "";
		for (EnemyInterface ei : enemyInterfaces) {
			eTVString += ei.getEnemyCharacter().toString() + "\n";
		}
		eTV.setText(eTVString);

		for (EnemyInterface ei : enemyInterfaces) {
			LayoutPackage lp = ei.getEnemyLayout();
			EnemyCharacter ec = ei.getEnemyCharacter();

			lp.geteHealth().setText(
					ec.getCurrentHealth() + " / " + ec.getMaxHealth());
			lp.geteEnergy().setText(
					ec.getCurrentEnergy() + " / " + ec.getMaxEnergy());
			lp.geteName().setText(ec.getName());
			lp.geteClass().setText(ec.getCharacterClassChoice().toString());
			lp.geteProfile().setImageResource(ec.getImageResource());
			if (ec.getMaxHealth() <= 0) {
				lp.geteHealthBar().setMax(1);
				lp.geteHealthBar().setProgress(0);
			} else {
				lp.geteHealthBar().setMax(ec.getMaxHealth());
				lp.geteHealthBar().setProgress(ec.getCurrentHealth());
			}
			if (ec.getMaxEnergy() <= 0) {
				lp.geteEnergyBar().setMax(1);
				lp.geteEnergyBar().setProgress(0);
			} else {
				lp.geteEnergyBar().setMax(ec.getMaxEnergy());
				lp.geteEnergyBar().setProgress(ec.getCurrentEnergy());
			}

		}

	}

	private CharSequence appendToCombatLog(Character c, int abilityId,
			Boolean last) {

		// TODO now only functions correctly when successful

		StringBuilder sb = new StringBuilder();
		// if (c == me) {
		//
		// sb.append(bh.getAbilityTotalDamage(abilityId) + " - "
		// + bh.getDefenderTotalResistance() + " = "
		// + bh.useAbilityAttack(enemyCharacter1, me, abilityId)
		// + " damage2enemy");
		//
		// // old process
		// // sb.append(meTAttack + " - " + e1TDefend + " = " + meSuccessD
		// // + " damage2enemy");
		// sb.append("\n");
		// }
		//
		// if (c == enemyCharacter1) {
		//
		// sb.append(bh.getAbilityTotalDamage(abilityId) + " - "
		// + bh.getDefenderTotalResistance() + " = "
		// + bh.useAbilityAttack(enemyCharacter1, me, abilityId)
		// + " damage2you");
		//
		// // old process
		// // sb.append(e1TAttack + " - " + meTDefend + " = " + e1SuccessD
		// // + " damage2you");
		// sb.append("\n");
		// }
		//
		// if (last) {
		// sb.append("\n");
		// }

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

	private void turns(String playerCommand, String target) {

		/**
		 * // "heal" chosen if (playerCommand == "heal") {
		 * me.setCurrentHealth(me.getCurrentHealth() + me.getBaseHealing());
		 * me.setCurrentEnergy(me.getCurrentEnergy() - 1); }
		 */

		// "rest" chosen
		if (playerCommand == "rest") {
			me.setCurrentEnergy(me.getCurrentEnergy() + 5);
		}

		if (me.getCurrentHealth() > 0) {

			// Player turn
			// "run" chosen
			if (playerCommand == "run") {
				for (EnemyInterface ei : enemyInterfaces) {
					enemyTurn(ei);
					resetEnemy(ei);
				}
				Toast.makeText(getApplicationContext(),
						"You are hit in the back as you flee.",
						Toast.LENGTH_SHORT).show();
			}

			// "attack" chosen
			if (playerCommand == "attack") {
				EnemyCharacter firstEnemy = enemyInterfaces.get(0)
						.getEnemyCharacter();
				firstEnemy.takeDamage(bh
						.useAbilityAttack(me, firstEnemy, 10101));
				combatLog.setText(appendToCombatLog(me, 10101, false));
			}
			// "whirlwind" chosen (remove later)
			if (playerCommand == "whirlwind") {
				for (EnemyInterface ei : enemyInterfaces) {
					EnemyCharacter ec = ei.getEnemyCharacter();
					ec.takeDamage(bh.useAbilityAttack(me, ec, 10201));
				}
				me.spendEnergy(3);
				combatLog.setText(appendToCombatLog(me, 10201, false));
			}

			for (EnemyInterface ei : enemyInterfaces) {
				if (ei.getEnemyCharacter().getCurrentHealth() <= 0) {
					resetEnemy(ei);
					meXP += ei.getEnemyCharacter().getXPGain();
					Toast.makeText(getApplicationContext(), "Enemy Defeated",
							Toast.LENGTH_SHORT).show();
				} else {
					enemyTurn(ei);
				}
			}
		}

		updateDisplay();
	}

	private void enemyTurn(EnemyInterface ei) {
		// Enemy turn
		EnemyCharacter ec = ei.getEnemyCharacter();
		if (ec.getCurrentHealth() > 0) {
			me.takeDamage(bh.useAbilityAttack(ec, me, 10101));
			combatLog.setText(appendToCombatLog(ec, 10101, true));
		} else {
			Toast.makeText(getApplicationContext(), "You win",
					Toast.LENGTH_SHORT).show();
//			meXP += ec.getXPGain();
//			resetEnemy(ei);
		}
		if (me.getCurrentHealth() <= 0) {
			Toast.makeText(getApplicationContext(), "You lose",
					Toast.LENGTH_SHORT).show();
			youLose();
			meXP = 0;
		}
	}

	private void resetEnemy(EnemyInterface ei) {
		EnemyInterface toRemove = null;
		View viewToRemove = null;
		ViewGroup vgToRemove = null;
		for (EnemyInterface e : enemyInterfaces) {
			if (e.getId() == ei.getId()) {
				toRemove = e;
				viewToRemove = e.getEnemyView();
				vgToRemove = e.getVg();
			}
		}
		if (toRemove != null && viewToRemove != null && vgToRemove != null) {
			enemyInterfaces.remove(toRemove);
			moreEnemies.removeView(viewToRemove);
			moreEnemies.removeView(vgToRemove);
		}

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

	// endregion Game Methods

	// region Shortcuts

	private void makeToast(String s, String duration) {
		int d = Toast.LENGTH_SHORT;
		if (duration == "long") {
			d = Toast.LENGTH_LONG;
		} else if (duration == "short") {
			d = Toast.LENGTH_SHORT;
		}
		Toast.makeText(getApplicationContext(), s, d).show();
	}

	// endregion Shortcuts

	private EnemyInterface createEnemyInterface(int enemyNumber,
			EnemyCharacter enemyCharacter) {
		// region TESTb
		// new instance of enemy TEST

		ViewGroup vg = (ViewGroup) findViewById(R.layout.enemylayout);
		View enemyLayout = LayoutInflater.from(context).inflate(
				R.layout.enemylayout, vg, false);
		TextView eHealth = (TextView) enemyLayout.findViewById(R.id.tvEHealth);
		TextView eEnergy = (TextView) enemyLayout.findViewById(R.id.tvEEnergy);
		TextView eName = (TextView) enemyLayout.findViewById(R.id.tvEName);
		TextView eClass = (TextView) enemyLayout.findViewById(R.id.tvEClass);
		ProgressBar eHealthBar = (ProgressBar) enemyLayout
				.findViewById(R.id.pbEHealth);
		ProgressBar eEnergyBar = (ProgressBar) enemyLayout
				.findViewById(R.id.pbEEnergy);
		ImageButton cAttack = (ImageButton) enemyLayout
				.findViewById(R.id.ibAttack);
		ImageButton eProfile = (ImageButton) enemyLayout
				.findViewById(R.id.ibEProfile);

		LayoutPackage enemyPackage = new LayoutPackage(this, enemyNumber,
				eHealth, eEnergy, eName, eClass, eHealthBar, eEnergyBar,
				cAttack, eProfile);
		EnemyInterface enemyInterface = new EnemyInterface(enemyNumber,
				enemyCharacter, enemyPackage, vg, enemyLayout);

		moreEnemies.addView(enemyLayout);
		return enemyInterface;
		// endregion TESTb
	}

	private void removeAllEnemyInterfaces() {
		// for (EnemyInterface ei : enemyInterfaces) {
		// removeEnemyInterface(ei.getId());
		// }

		moreEnemies.removeAllViews();
		enemyInterfaces.clear();
	}

	// private void removeEnemyInterface(int enemyInterfaceId) {
	// int enemyInterfaceToRemove = 0;
	// EnemyInterface ei = null;
	// for (EnemyInterface eI : enemyInterfaces) {
	// if (eI.getId() == enemyInterfaceId) {
	// enemyInterfaceToRemove = enemyInterfaces.indexOf(eI);
	// moreEnemies.removeViewAt(enemyInterfaceToRemove);
	// }
	// }
	// if (enemyInterfaces.contains(ei)) {
	// enemyInterfaces.remove(ei);
	// }
	// }

	class EnemyInterface {

		EnemyCharacter enemyCharacter;
		LayoutPackage enemyLayout;
		ViewGroup vg;
		View enemyView;
		int id;

		public EnemyInterface(int id, EnemyCharacter ec, LayoutPackage lp,
				ViewGroup vg, View enemyView) {
			this.id = id;
			enemyCharacter = ec;
			enemyLayout = lp;
			vg = (ViewGroup) findViewById(R.layout.enemylayout);
			enemyView = LayoutInflater.from(context).inflate(
					R.layout.enemylayout, vg, false);
		}

		// region eiGettersSetters
		public EnemyCharacter getEnemyCharacter() {
			return enemyCharacter;
		}

		public void setEnemyCharacter(EnemyCharacter enemyCharacter) {
			this.enemyCharacter = enemyCharacter;
		}

		public LayoutPackage getEnemyLayout() {
			return enemyLayout;
		}

		public void setEnemyLayout(LayoutPackage enemyLayout) {
			this.enemyLayout = enemyLayout;
		}

		public ViewGroup getVg() {
			return vg;
		}

		public void setVg(ViewGroup vg) {
			this.vg = vg;
		}

		public View getEnemyView() {
			return enemyView;
		}

		public void setEnemyView(View enemyView) {
			this.enemyView = enemyView;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		// endregion eiGettersSetters

	}

	// public class LayoutPackage extends ButtonEventCreator implements
	// OnClickListener {
	//
	// int id;
	// TextView eHealth;
	// TextView eEnergy;
	// TextView eName;
	// TextView eClass;
	// ProgressBar eHealthBar;
	// ProgressBar eEnergyBar;
	// ImageButton cAttack;
	// ImageButton eProfile;
	//
	// public LayoutPackage(Battle mainActivity, int packageId, TextView
	// eHealth, TextView eEnergy,
	// TextView eName, TextView eClass, ProgressBar eHealthBar,
	// ProgressBar eEnergyBar, ImageButton cAttack,
	// ImageButton eProfile) {
	// super();
	// this.eHealth = eHealth;
	// this.eEnergy = eEnergy;
	// this.eName = eName;
	// this.eClass = eClass;
	// this.eHealthBar = eHealthBar;
	// this.eEnergyBar = eEnergyBar;
	// this.cAttack = cAttack;
	// this.eProfile = eProfile;
	// this.cAttack.setOnClickListener(this);
	// this.eProfile.setOnClickListener(this);
	// this.addListener(mainActivity);
	//
	// }
	//
	// public void onClick(View v) {
	// switch (v.getId()) {
	// case R.id.ibAttack:
	// Toast.makeText(getApplicationContext(),
	// "click attack: " + eName.getText(), Toast.LENGTH_SHORT)
	// .show();
	// this.fireButtonEvent(this, new ButtonEvent(this,
	// ButtonEventType.Attack));
	// break;
	// case R.id.ibEProfile:
	// Toast.makeText(getApplicationContext(),
	// "click profile: " + eName.getText(), Toast.LENGTH_SHORT)
	// .show();
	// this.fireButtonEvent(this, new ButtonEvent(this,
	// ButtonEventType.Profile));
	// break;
	// }
	// }
	//
	// // region getterssetters
	// public int getId() {
	// return id;
	// }
	//
	// public void setId(int id) {
	// this.id = id;
	// }
	//
	// public TextView geteHealth() {
	// return eHealth;
	// }
	//
	// public void seteHealth(TextView eHealth) {
	// this.eHealth = eHealth;
	// }
	//
	// public TextView geteEnergy() {
	// return eEnergy;
	// }
	//
	// public void seteEnergy(TextView eEnergy) {
	// this.eEnergy = eEnergy;
	// }
	//
	// public TextView geteName() {
	// return eName;
	// }
	//
	// public void seteName(TextView eName) {
	// this.eName = eName;
	// }
	//
	// public TextView geteClass() {
	// return eClass;
	// }
	//
	// public void seteClass(TextView eClass) {
	// this.eClass = eClass;
	// }
	//
	// public ProgressBar geteHealthBar() {
	// return eHealthBar;
	// }
	//
	// public void seteHealthBar(ProgressBar eHealthBar) {
	// this.eHealthBar = eHealthBar;
	// }
	//
	// public ProgressBar geteEnergyBar() {
	// return eEnergyBar;
	// }
	//
	// public void seteEnergyBar(ProgressBar eEnergyBar) {
	// this.eEnergyBar = eEnergyBar;
	// }
	//
	// public ImageButton getcAttack() {
	// return cAttack;
	// }
	//
	// public void setcAttack(ImageButton cAttack) {
	// this.cAttack = cAttack;
	// }
	//
	// public ImageButton geteProfile() {
	// return eProfile;
	// }
	//
	// public void seteProfile(ImageButton eProfile) {
	// this.eProfile = eProfile;
	// }
	// // endregion getterssetters
	//
	// }

	public void onButtonEvent(LayoutPackage lp, ButtonEvent event) {
		switch (event.getType()) {
		case Attack:
			abilityDialog(lp.getId());
			break;
		case Profile:
			// TODO Open profile.
			break;
		}
	}

	private void abilityDialog(int enemyId) {
		final Dialog dialog = new Dialog(context);
		dialog.setContentView(R.layout.attack_dialog);
		dialog.setTitle("Choose Your Action...");

		OnClickListener ocl = new OnClickListener() {
			public void onClick(View v) {
				switch (v.getId()) {
				case R.id.ad1button:
					turns("attack", "");
					break;
				case R.id.ad2button:
					turns("whirlwind", "");
					break;
				}

				dialog.dismiss();
			}
		};

		addAPlayerAbility(dialog, ocl, R.id.ad1image, R.drawable.ic_launcher,
				R.id.ad1text, "Main and OffHand or TwoHand Attack",
				R.id.ad1button, "Primary Attack");
		addAPlayerAbility(dialog, ocl, R.id.ad2image, R.drawable.ic_launcher,
				R.id.ad2text, "Attacks all targets for 75% damage",
				R.id.ad2button, "Whirlwind");
		ImageView dImage1 = (ImageView) dialog.findViewById(R.id.ad1image);
		dImage1.setImageResource(R.drawable.ic_launcher);
		TextView dText1 = (TextView) dialog.findViewById(R.id.ad1text);
		dText1.setText("Main and OffHand or TwoHand Attack");
		Button dButton1 = (Button) dialog.findViewById(R.id.ad1button);
		dButton1.setText("Primary Attack");
		dButton1.setOnClickListener(ocl);

		ImageView dImage2 = (ImageView) dialog.findViewById(R.id.ad2image);
		dImage2.setImageResource(R.drawable.ic_launcher);
		TextView dText2 = (TextView) dialog.findViewById(R.id.ad2text);
		dText2.setText("Attacks all targets for 75% damage");
		Button dButton2 = (Button) dialog.findViewById(R.id.ad2button);
		dButton2.setText("Whirlwind");
		dButton2.setOnClickListener(ocl);

		dialog.show();
	}

}