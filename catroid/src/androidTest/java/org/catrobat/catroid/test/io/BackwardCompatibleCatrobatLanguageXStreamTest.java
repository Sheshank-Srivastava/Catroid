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
package org.catrobat.catroid.test.io;

import android.test.InstrumentationTestCase;

import org.catrobat.catroid.common.Constants;
import org.catrobat.catroid.content.Project;
import org.catrobat.catroid.io.StorageHandler;
import org.catrobat.catroid.test.utils.TestUtils;
import org.catrobat.catroid.utils.UtilZip;

import java.util.Locale;

public class BackwardCompatibleCatrobatLanguageXStreamTest extends InstrumentationTestCase {

	private static final String ZIP_FILENAME_FALLING_BALLS = "Falling_balls.catrobat";
	private static final String ZIP_FILENAME_COLOR_LEANER_BALLOONS = "Color_Learner_-_Balloons.catrobat";
	private static final String ZIP_FILENAME_PONG_STARTER = "Pong_Starter.catrobat";
	private static final String ZIP_FILENAME_WHIP = "Whip.catrobat";
	private static final String ZIP_FILENAME_AIR_FIGHT = "Air_fight_0.5.catrobat";
	private static final String ZIP_FILENAME_XRAY_PHONE = "X-Ray_phone.catrobat";
	private static final String ZIP_FILENAME_ALL_BRICKS = "All_Bricks.catrobat";
	private static final String ZIP_FILENAME_NOTE_AND_SPEAK_BRICK = "Note_And_Speak_Brick.catrobat";
	private static final String ZIP_FILENAME_GHOST_EFFECT_BRICKS = "Ghost_Effect_Bricks.catrobat";
	private static final String ZIP_FILENAME_LEGO_NXT = "old-lego-nxt.catrobat";

	private static final String PROJECT_NAME_FALLING_BALLS = "falling balls";
	private static final String PROJECT_NAME_COLOR_LEANER_BALLOONS = "color learner - balloons";
	private static final String PROJECT_NAME_PONG_STARTER = "pong starter";
	private static final String PROJECT_NAME_WHIP = "whip";
	private static final String PROJECT_NAME_AIR_FIGHT = "air fight 0.5";
	private static final String PROJECT_NAME_XRAY_PHONE = "x-ray phone";
	private static final String PROJECT_NAME_ALL_BRICKS = "all bricks";
	private static final String PROJECT_NAME_NOTE_AND_SPEAK_BRICK = "noteandspeakbrick";
	private static final String PROJECT_NAME_GHOST_EFFECT_BRICKS = "ghosteffectbricks";
	private static final String PROJECT_NAME_LEGO_NXT = "oldlegonxt";

	public void testLoadingProjectsOfCatrobatLanguageVersion08() throws Exception {
		TestUtils.copyAssetProjectZipFile(getInstrumentation().getContext(), ZIP_FILENAME_FALLING_BALLS,
				Constants.TMP_PATH);
		TestUtils.copyAssetProjectZipFile(getInstrumentation().getContext(), ZIP_FILENAME_COLOR_LEANER_BALLOONS, Constants.TMP_PATH);

		UtilZip.unZipFile(Constants.TMP_PATH + "/" + ZIP_FILENAME_FALLING_BALLS, Constants.DEFAULT_ROOT + "/"
				+ PROJECT_NAME_FALLING_BALLS);
		UtilZip.unZipFile(Constants.TMP_PATH + "/" + ZIP_FILENAME_COLOR_LEANER_BALLOONS, Constants.DEFAULT_ROOT + "/"
				+ PROJECT_NAME_COLOR_LEANER_BALLOONS);

		Project fallingBallsProject = StorageHandler.getInstance().loadProject(PROJECT_NAME_FALLING_BALLS,
				getInstrumentation().getTargetContext());
		assertTrue("Cannot load falling balls project", fallingBallsProject != null);
		assertEquals("Wrong project loaded", PROJECT_NAME_FALLING_BALLS, fallingBallsProject.getName().toLowerCase(Locale.getDefault()));

		Project colorLeanerBalloonsProject = StorageHandler.getInstance().loadProject(
				PROJECT_NAME_COLOR_LEANER_BALLOONS, getInstrumentation().getTargetContext());
		assertTrue("Cannot load color leaner balloons project", colorLeanerBalloonsProject != null);
		assertEquals("Wrong project loaded", PROJECT_NAME_COLOR_LEANER_BALLOONS, colorLeanerBalloonsProject.getName()
				.toLowerCase(Locale.getDefault()));

		UtilZip.deleteZipFile(ZIP_FILENAME_FALLING_BALLS, Constants.TMP_PATH);
		UtilZip.deleteZipFile(ZIP_FILENAME_COLOR_LEANER_BALLOONS, Constants.TMP_PATH);

		TestUtils.deleteTestProjects(PROJECT_NAME_FALLING_BALLS, PROJECT_NAME_COLOR_LEANER_BALLOONS);
	}

	public void testLoadingProjectsOfCatrobatLanguageVersion09() throws Exception {
		TestUtils.copyAssetProjectZipFile(getInstrumentation().getContext(), ZIP_FILENAME_PONG_STARTER, Constants.TMP_PATH);
		TestUtils.copyAssetProjectZipFile(getInstrumentation().getContext(), ZIP_FILENAME_WHIP, Constants.TMP_PATH);

		UtilZip.unZipFile(Constants.TMP_PATH + "/" + ZIP_FILENAME_PONG_STARTER, Constants.DEFAULT_ROOT + "/"
				+ PROJECT_NAME_PONG_STARTER);
		UtilZip.unZipFile(Constants.TMP_PATH + "/" + ZIP_FILENAME_WHIP, Constants.DEFAULT_ROOT + "/"
				+ PROJECT_NAME_WHIP);

		Project pongStarterProject = StorageHandler.getInstance().loadProject(PROJECT_NAME_PONG_STARTER,
				getInstrumentation().getTargetContext());
		assertTrue("Cannot load pong starter project", pongStarterProject != null);
		assertEquals("Wrong project loaded", PROJECT_NAME_PONG_STARTER,
				pongStarterProject.getName().toLowerCase(Locale.getDefault()));

		Project whipProject = StorageHandler.getInstance().loadProject(PROJECT_NAME_WHIP,
				getInstrumentation().getTargetContext());
		assertTrue("Cannot load whip project", whipProject != null);
		assertEquals("Wrong project loaded", PROJECT_NAME_WHIP, whipProject.getName().toLowerCase(Locale.getDefault()));

		UtilZip.deleteZipFile(ZIP_FILENAME_PONG_STARTER, Constants.TMP_PATH);
		UtilZip.deleteZipFile(ZIP_FILENAME_WHIP, Constants.TMP_PATH);

		TestUtils.deleteTestProjects(PROJECT_NAME_PONG_STARTER, PROJECT_NAME_WHIP);
	}

	public void testLoadingProjectsOfCatrobatLanguageVersion091() throws Exception {
		TestUtils.copyAssetProjectZipFile(getInstrumentation().getContext(), ZIP_FILENAME_AIR_FIGHT, Constants.TMP_PATH);
		TestUtils.copyAssetProjectZipFile(getInstrumentation().getContext(), ZIP_FILENAME_XRAY_PHONE, Constants.TMP_PATH);
		TestUtils.copyAssetProjectZipFile(getInstrumentation().getContext(), ZIP_FILENAME_ALL_BRICKS, Constants.TMP_PATH);

		UtilZip.unZipFile(Constants.TMP_PATH + "/" + ZIP_FILENAME_AIR_FIGHT, Constants.DEFAULT_ROOT + "/"
				+ PROJECT_NAME_AIR_FIGHT);
		UtilZip.unZipFile(Constants.TMP_PATH + "/" + ZIP_FILENAME_XRAY_PHONE, Constants.DEFAULT_ROOT + "/"
				+ PROJECT_NAME_XRAY_PHONE);
		UtilZip.unZipFile(Constants.TMP_PATH + "/" + ZIP_FILENAME_ALL_BRICKS, Constants.DEFAULT_ROOT + "/"
				+ PROJECT_NAME_ALL_BRICKS);

		Project airFightProject = StorageHandler.getInstance().loadProject(PROJECT_NAME_AIR_FIGHT,
				getInstrumentation().getTargetContext());
		assertTrue("Cannot load air fight project", airFightProject != null);
		assertEquals("Wrong project loaded", PROJECT_NAME_AIR_FIGHT,
				airFightProject.getName().toLowerCase(Locale.getDefault()));

		Project xRayPhoneProject = StorageHandler.getInstance().loadProject(PROJECT_NAME_XRAY_PHONE,
				getInstrumentation().getTargetContext());
		assertTrue("Cannot load X-Ray phone project", xRayPhoneProject != null);
		assertEquals("Wrong project loaded", PROJECT_NAME_XRAY_PHONE,
				xRayPhoneProject.getName().toLowerCase(Locale.getDefault()));

		Project allBricksProject = StorageHandler.getInstance().loadProject(PROJECT_NAME_ALL_BRICKS,
				getInstrumentation().getTargetContext());
		assertTrue("Cannot load All Bricks project", allBricksProject != null);
		assertEquals("Wrong project loaded", PROJECT_NAME_ALL_BRICKS,
				allBricksProject.getName().toLowerCase(Locale.getDefault()));

		UtilZip.deleteZipFile(ZIP_FILENAME_AIR_FIGHT, Constants.TMP_PATH);
		UtilZip.deleteZipFile(ZIP_FILENAME_XRAY_PHONE, Constants.TMP_PATH);
		UtilZip.deleteZipFile(ZIP_FILENAME_ALL_BRICKS, Constants.TMP_PATH);

		TestUtils.deleteTestProjects(PROJECT_NAME_AIR_FIGHT, PROJECT_NAME_XRAY_PHONE, PROJECT_NAME_ALL_BRICKS);
	}

	public void testLoadingProjectsOfCatrobatLanguageVersion092() throws Exception {
		TestUtils.copyAssetProjectZipFile(getInstrumentation().getContext(), ZIP_FILENAME_NOTE_AND_SPEAK_BRICK,
				Constants.TMP_PATH);
		UtilZip.unZipFile(Constants.TMP_PATH + "/" + ZIP_FILENAME_NOTE_AND_SPEAK_BRICK, Constants.DEFAULT_ROOT + "/"
				+ PROJECT_NAME_NOTE_AND_SPEAK_BRICK);

		Project noteAndSpeakBrickProject = StorageHandler.getInstance().loadProject(PROJECT_NAME_NOTE_AND_SPEAK_BRICK,
				getInstrumentation().getTargetContext());
		assertTrue("Cannot load " + PROJECT_NAME_NOTE_AND_SPEAK_BRICK + " project", noteAndSpeakBrickProject != null);
		assertEquals("Wrong project loaded", PROJECT_NAME_NOTE_AND_SPEAK_BRICK, noteAndSpeakBrickProject.getName()
				.toLowerCase(Locale.getDefault()));

		UtilZip.deleteZipFile(ZIP_FILENAME_NOTE_AND_SPEAK_BRICK, Constants.TMP_PATH);
		TestUtils.deleteTestProjects(PROJECT_NAME_NOTE_AND_SPEAK_BRICK);
	}

	public void testLoadingProjectsOfCatrobatLanguageVersion095() throws Exception {
		TestUtils.copyAssetProjectZipFile(getInstrumentation().getContext(), ZIP_FILENAME_GHOST_EFFECT_BRICKS,
				Constants.TMP_PATH);
		UtilZip.unZipFile(Constants.TMP_PATH + "/" + ZIP_FILENAME_GHOST_EFFECT_BRICKS, Constants.DEFAULT_ROOT + "/"
				+ PROJECT_NAME_GHOST_EFFECT_BRICKS);

		Project ghostBricksProject = StorageHandler.getInstance().loadProject(PROJECT_NAME_GHOST_EFFECT_BRICKS,
				getInstrumentation().getTargetContext());
		assertTrue("Cannot load " + PROJECT_NAME_GHOST_EFFECT_BRICKS + " project", ghostBricksProject != null);
		assertEquals("Wrong project loaded", PROJECT_NAME_GHOST_EFFECT_BRICKS, ghostBricksProject.getName()
				.toLowerCase(Locale.getDefault()));

		UtilZip.deleteZipFile(ZIP_FILENAME_GHOST_EFFECT_BRICKS, Constants.TMP_PATH);
		TestUtils.deleteTestProjects(PROJECT_NAME_GHOST_EFFECT_BRICKS);
	}

	public void testLoadingLegoNxtProjectsOfCatrobatLanguageVersion092() throws Exception {
		TestUtils.copyAssetProjectZipFile(getInstrumentation().getContext(), ZIP_FILENAME_LEGO_NXT, Constants.TMP_PATH);
		UtilZip.unZipFile(Constants.TMP_PATH + "/" + ZIP_FILENAME_LEGO_NXT, Constants.DEFAULT_ROOT + "/"
				+ PROJECT_NAME_LEGO_NXT);

		Project legoProject = StorageHandler.getInstance().loadProject(PROJECT_NAME_LEGO_NXT,
				getInstrumentation().getTargetContext());
		assertTrue("Cannot load " + PROJECT_NAME_LEGO_NXT + " project", legoProject != null);
		assertEquals("Wrong project loaded", PROJECT_NAME_LEGO_NXT,
				legoProject.getName().toLowerCase(Locale.getDefault()));

		UtilZip.deleteZipFile(ZIP_FILENAME_LEGO_NXT, Constants.TMP_PATH);
		TestUtils.deleteTestProjects(PROJECT_NAME_LEGO_NXT);
	}
}
