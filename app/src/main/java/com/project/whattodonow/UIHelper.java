package com.project.whattodonow;
import android.content.Context;

import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
/**
 * Common class to handle all UI related components
 *
 * @author Yash Tongaonkar
 * @since 28-01-2019 18:06
 */
public class UIHelper {

    /**
     * static instance for UIHelper
     */
    private static @Nullable
    UIHelper helper = null;

    /**
     * @return - returns single instance of UIHelper
     */
    public static UIHelper getInstance() {
        if (helper == null)
            helper = new UIHelper();
        return helper;
    }

    /**
     * This method hides the keyboard
     *
     * @param context Application context
     */
    private void hideKeyboard(@NonNull Context context) {
        // Check if no view has focus:
        AppCompatActivity appCompatActivity = (AppCompatActivity) context;
        if (appCompatActivity != null) {
            View view = appCompatActivity.getCurrentFocus();
            if (view != null) {
                InputMethodManager imm = (InputMethodManager) appCompatActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }


    /**
     * This method replaces the given fragment in the given layout ID
     *
     * @param context     Activity Context
     * @param fragment    Fragment to be replaces
     * @param tag         Tag to identify the fragment by {@link FragmentManager}
     * @param containerID Layout ID in which the fragment will be replaced
     * @param isResumed   Returns true when the fragment is visible to the user and actively running else false
     */
    public void replaceFragment(Context context,
                                Fragment fragment,
                                String tag,
                                int containerID,
                                boolean isResumed, CustomAnimationType customAnimationType) {
        replaceFragment(context, fragment, tag, containerID, true, isResumed, customAnimationType);
    }

    /**
     * This method replaces the given fragment in the given layout ID
     *
     * @param context        Activity Context
     * @param fragment       Fragment to be replaces
     * @param tag            Tag to identify the fragment by {@link FragmentManager}
     * @param containerID    Layout ID in which the fragment will be replaced
     * @param addToBackStack true if needs to add in back stack, else false
     * @param isResumed      Returns true when the fragment is visible to the user and actively running else false
     */
    public void replaceFragment(Context context,
                                Fragment fragment,
                                String tag,
                                int containerID,
                                boolean addToBackStack,
                                boolean isResumed, CustomAnimationType customAnimationType) {
        try {
            UIHelper.getInstance().hideKeyboard(context);
            if (isResumed) {
                AppCompatActivity appCompatActivity = (AppCompatActivity) context;
                FragmentManager fragmentManager = appCompatActivity.getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                if (addToBackStack)
                    fragmentTransaction.addToBackStack(tag);
                else
                    fragmentTransaction.addToBackStack(null);
                //To apply different transaction animations at one place
                switch (customAnimationType) {
                    case CUSTOM_ANIMATION_NORMAL:
                        break;
                    case CUSTOM_ANIMATION_LEFT_AND_RIGHT:
                        fragmentTransaction.setCustomAnimations(R.anim.left_in, R.anim.right_out, android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                        break;
                    case CUSTOM_ANIMATION_BOTTOM_TO_TOP:
                        fragmentTransaction.setCustomAnimations(R.anim.push_up_in, android.R.anim.fade_out, android.R.anim.fade_in, R.anim.push_down_in);
                        break;
                }
                fragmentTransaction.replace(containerID, fragment, tag);
                fragmentTransaction.commit();
            }
        } catch (Exception ex) {
            Log.e("Logger",ex.toString());
        }
    }


    /**
     * clear the back stack up to n number of steps, where n is defined by the count
     *
     * @param context application context
     * @param count   number of steps that needs to be popped
     */
    private void popBackStack(Context context, int count) {
        AppCompatActivity appCompatActivity = (AppCompatActivity) context;
        FragmentManager fragmentManager = appCompatActivity.getSupportFragmentManager();
        for (int index = 1; index < count; index++) {
            fragmentManager.popBackStack();
        }
        fragmentManager.beginTransaction().commit();
    }

    /**
     * Pop fragment upto given tag
     *
     * @param context Current activity context
     * @param tag     Fragment tag upto which we want to pop
     */
    public void popFragmentUpto(Context context, String tag) {
        AppCompatActivity appCompatActivity = (AppCompatActivity) context;
        if (appCompatActivity != null) {
            appCompatActivity.getSupportFragmentManager().popBackStack(tag, 0);
        }
    }

    /**
     * Method to check if fragment is present in backstack or not
     * Navigate user by checking backstack
     *
     * @param context Current activity context
     * @param tag     Fragment tag
     * @return returns boolean true/false
     */
    public boolean isFragmentPresentInBackStack(Context context, String tag) {
        AppCompatActivity appCompatActivity = (AppCompatActivity) context;
        if (appCompatActivity != null) {
            Fragment fragmentA = appCompatActivity.getSupportFragmentManager().findFragmentByTag(tag);
            return fragmentA != null;
        }
        return false;
    }

    /**
     * Clear the back stack completely
     *
     * @param context application context
     */
    public void popBackStack(Context context) {
        AppCompatActivity appCompatActivity = (AppCompatActivity) context;
        popBackStack(context, appCompatActivity.getSupportFragmentManager().getBackStackEntryCount());
    }


    /**
     * Get current fragment from container
     *
     * @param fragmentManager - Fragment manager instance
     * @param containerId     - resource container if of fragment to fetch
     * @return return recent fragment name from container
     */
    public Fragment getCurrentFragment(FragmentManager fragmentManager, int containerId) {
        return fragmentManager.findFragmentById(containerId);
    }

    public boolean checkIfFragmentPresentInBackStack(Context context, String tag) {
        AppCompatActivity appCompatActivity = (AppCompatActivity) context;
        if (appCompatActivity != null) {
            return appCompatActivity.getSupportFragmentManager().findFragmentByTag(tag) != null;
        }
        return false;
    }
    /**
     * This method is used to hide soft input keyboard
     *
     * @param context - context of activity
     */
    public void hideKeyBoard(Context context) {
        AppCompatActivity appCompatActivity = (AppCompatActivity) context;
        if (appCompatActivity != null) {
            // Check if no view has focus:
            View view = appCompatActivity.getCurrentFocus();
            if (view != null) {
                InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }

    /**
     * This method is used to show soft input keyboard
     *
     * @param context - context of activity
     */
    public void showKeyboard(Context context) {
        AppCompatActivity appCompatActivity = (AppCompatActivity) context;
        if (appCompatActivity != null) {
            // Check if no view has focus:
            View view = appCompatActivity.getCurrentFocus();
            if (view != null) {
                InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
            }
        }
    }
    /**
     * Custom enum to represents Transaction Animation type
     */
    public enum CustomAnimationType {
        CUSTOM_ANIMATION_NORMAL,
        CUSTOM_ANIMATION_LEFT_AND_RIGHT,
        CUSTOM_ANIMATION_BOTTOM_TO_TOP
    }

}
