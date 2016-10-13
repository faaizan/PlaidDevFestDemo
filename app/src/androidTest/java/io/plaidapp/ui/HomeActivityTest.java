package io.plaidapp.ui;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.plaidapp.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.*;
import static android.support.test.espresso.assertion.ViewAssertions.*;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.*;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class HomeActivityTest {

    @Rule
    public ActivityTestRule<HomeActivity> mActivityTestRule = new ActivityTestRule<>(HomeActivity.class);

    @Test
    public void homeActivityTest() {
        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.grid), isDisplayed()));
        recyclerView.perform(actionOnItemAtPosition(2, click()));

        ViewInteraction fABToggle = onView(
                allOf(withId(R.id.fab_heart),
                        withParent(allOf(withId(R.id.draggable_frame),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed()));
        fABToggle.perform(click());

        pressBack();

        ViewInteraction imageButton = onView(
                allOf(withId(R.id.back),
                        withParent(allOf(withId(R.id.back_wrapper),
                                withParent(withId(R.id.draggable_frame)))),
                        isDisplayed()));
        imageButton.perform(click());

        ViewInteraction imageButton2 = onView(
                allOf(withId(R.id.fab), isDisplayed()));
        imageButton2.perform(click());

        ViewInteraction baselineGridTextView = onView(
                allOf(withId(android.R.id.text1), withText("shivrajp130@gmail.com"), isDisplayed()));
        baselineGridTextView.perform(click());

        ViewInteraction passwordEntry = onView(
                allOf(withId(R.id.password), isDisplayed()));
        passwordEntry.perform(replaceText("2345678"), closeSoftKeyboard());

        ViewInteraction button = onView(
                allOf(withId(R.id.login), withText("Log in"),
                        withParent(allOf(withId(R.id.actions_container),
                                withParent(withId(R.id.container)))),
                        isDisplayed()));
        button.perform(click());

    }

}
