package io.plaidapp.ui;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.plaidapp.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class DesignCheckTest {

    @Rule
    public ActivityTestRule<HomeActivity> mActivityTestRule = new ActivityTestRule<>(HomeActivity.class);

    @Test
    public void designCheckTest() {
        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.grid), isDisplayed()));
        recyclerView.perform(actionOnItemAtPosition(2, click()));

        ViewInteraction view = onView(
                allOf(withId(R.id.shot_title),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.dribbble_comments),
                                        0),
                                1),
                        isDisplayed()));
        view.check(matches(isDisplayed()));

        ViewInteraction view2 = onView(
                allOf(withId(R.id.shot_description),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.dribbble_comments),
                                        0),
                                2),
                        isDisplayed()));
        view2.check(matches(isDisplayed()));

        ViewInteraction imageButton = onView(
                allOf(withId(R.id.fab_heart),
                        childAtPosition(
                                allOf(withId(R.id.draggable_frame),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                4),
                        isDisplayed()));
        imageButton.check(matches(isDisplayed()));

        ViewInteraction button2 = onView(
                allOf(withId(R.id.shot_like_count),
                        childAtPosition(
                                allOf(withId(R.id.shot_actions),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.widget.GridLayout.class),
                                                3)),
                                0),
                        isDisplayed()));
        button2.check(matches(isDisplayed()));

        pressBack();

        ViewInteraction actionMenuItemView = onView(
                allOf(withId(R.id.menu_filter), withContentDescription("Filter"), isDisplayed()));
        actionMenuItemView.perform(click());

        pressBack();

    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
