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
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class LoginFailTest {

    @Rule
    public ActivityTestRule<HomeActivity> mActivityTestRule = new ActivityTestRule<>(HomeActivity.class);

    @Test
    public void loginTest() {
        ViewInteraction imageButton = onView(
                allOf(withId(R.id.fab), isDisplayed()));
        imageButton.perform(click());

        onView(allOf(withId(R.id.username), isDisplayed()))
                .perform(typeText("wrongEmail@gmail.com"),closeSoftKeyboard());

        onView(allOf(withId(R.id.password), isDisplayed()))
                .perform(typeText("wrongpassword123"),closeSoftKeyboard());

        /*ViewInteraction emailEntry = onView(allOf(withId(R.id.username), isDisplayed()));
        emailEntry.perform(typeText("shivrajp130.cj@gmail.com"), closeSoftKeyboard());

        *//*ViewInteraction baselineGridTextView = onView(
                allOf(withId(android.R.id.text1), withText("shivrajp130.cj@gmail.com"), isDisplayed()));
        baselineGridTextView.perform(click());*//*

        pressBack();



        ViewInteraction passwordEntry = onView(
                allOf(withId(R.id.password), isDisplayed()));
        passwordEntry.perform(typeText("12345678"), closeSoftKeyboard());*/

        ViewInteraction button = onView(
                allOf(withId(R.id.login), withText("Log in"),
                        withParent(allOf(withId(R.id.actions_container),
                                withParent(withId(R.id.container)))),
                        isDisplayed()));
        button.perform(click());

    }

}
