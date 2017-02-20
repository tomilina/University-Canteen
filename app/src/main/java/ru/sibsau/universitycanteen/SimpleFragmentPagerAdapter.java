/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ru.sibsau.universitycanteen;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Provides the appropriate {@link Fragment} for a view pager.
 */
public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {

    final int PAGE_COUNT = 5;
    private String tabTitles[] = new String[] { "Столовая 1","Столовая 2","Столовая 3","Буфет П","Буфет Л"};
    private Context context;

    public SimpleFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

//    @Override
//    public Fragment getItem(int position) {
//        return PageFragment.newInstance(position + 1);
//    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }


    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: return new Canteen_1();
            case 1: return new Canteen_2();
            case 2: return new Canteen_3();
            case 3: return new Bufet_P();
            case 4: return new Bufet_L();
            default: return PageFragment.newInstance(position + 1);
        }
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }
}
