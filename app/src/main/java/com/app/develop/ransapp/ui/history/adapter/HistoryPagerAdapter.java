package com.app.develop.ransapp.ui.history.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.app.develop.ransapp.ui.history.FragmentDefault;
import com.app.develop.ransapp.ui.history.FragmentOne;
import com.app.develop.ransapp.ui.history.FragmentThree;
import com.app.develop.ransapp.ui.history.FragmentTwo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 *
 * Pager adapter that keeps state of the fragments inside the bottom page navigation tabs
 *
 */

public class HistoryPagerAdapter extends FragmentPagerAdapter {

    //region Statics
    private static final List<Fragment> BASE_FRAGMENTS = Arrays.asList(
            new FragmentOne(),
            new FragmentTwo(),
            new FragmentThree(),
            new FragmentDefault());

    private static final int FRAGMENT_ONE_POSITION = 0;
    private static final int FRAGMENT_TWO_POSITION = 1;
    private static final int FRAGMENT_THREE_POSITION = 2;
    private static final int FRAGMENT_DEFAULT_POSITION = 3;


    private List<Fragment> mOneFragments;
    private List<Fragment> mTwoFragments;
    private List<Fragment> mThreeFragments;
    private List<Fragment> mDefaultFragments;


    //region constructor
    public HistoryPagerAdapter(@NonNull FragmentManager fragmentManager) {
        super(fragmentManager);
        mOneFragments = new ArrayList<>();
        mTwoFragments = new ArrayList<>();
        mThreeFragments = new ArrayList<>();
        mDefaultFragments = new ArrayList<>();
    }

    //endregion

    //region FragmentPagerAdapter overridden methods
    @Override
    @NonNull
    public Fragment getItem(int position) {
        if (position == FRAGMENT_ONE_POSITION) {
            if (mOneFragments.isEmpty()) {
                return BASE_FRAGMENTS.get(position);
            }
            return mOneFragments.get(mOneFragments.size() - 1);
        } else if (position == FRAGMENT_TWO_POSITION) {
            if (mTwoFragments.isEmpty()) {
                return BASE_FRAGMENTS.get(position);
            }
            return mTwoFragments.get(mTwoFragments.size() - 1);
        } else if (position == FRAGMENT_THREE_POSITION) {
            if (mThreeFragments.isEmpty()) {
                return BASE_FRAGMENTS.get(position);
            }
            return mThreeFragments.get(mThreeFragments.size() - 1);
        } else {
            if (mDefaultFragments.isEmpty()) {
                return BASE_FRAGMENTS.get(position);
            }
            return mDefaultFragments.get(mDefaultFragments.size() - 1);
        }
    }

    @Override
    public int getCount() {
        return BASE_FRAGMENTS.size();
    }

    @Override
    public long getItemId(int position) {
        if (position == FRAGMENT_ONE_POSITION
                && getItem(position).equals(BASE_FRAGMENTS.get(position))) {
            return FRAGMENT_ONE_POSITION;
        } else if (position == FRAGMENT_TWO_POSITION
                && getItem(position).equals(BASE_FRAGMENTS.get(position))) {
            return FRAGMENT_TWO_POSITION;
        } else if (position == FRAGMENT_THREE_POSITION
                && getItem(position).equals(BASE_FRAGMENTS.get(position))) {
            return FRAGMENT_THREE_POSITION;
        } else if (position == FRAGMENT_DEFAULT_POSITION
                && getItem(position).equals(BASE_FRAGMENTS.get(position))) {
            return FRAGMENT_DEFAULT_POSITION;
        }

        return getItem(position).hashCode();
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }

    public void updateFragment(Fragment fragment, int position) {
        if (!BASE_FRAGMENTS.contains(fragment)) {
            addInnerFragment(fragment, position);
        }
        notifyDataSetChanged();
    }


    private void removeInnerFragment(Fragment fragment, List<Fragment> tabFragments) {
        tabFragments.remove(fragment);
        notifyDataSetChanged();
    }

    private void addInnerFragment(Fragment fragment, int position) {
        if (position == FRAGMENT_ONE_POSITION) {
            mOneFragments.add(fragment);
        } else if (position == FRAGMENT_TWO_POSITION) {
            mTwoFragments.add(fragment);
        } else if (position == FRAGMENT_THREE_POSITION) {
            mThreeFragments.add(fragment);
        } else if (position == FRAGMENT_DEFAULT_POSITION) {
            mDefaultFragments.add(fragment);
        }
    }
}