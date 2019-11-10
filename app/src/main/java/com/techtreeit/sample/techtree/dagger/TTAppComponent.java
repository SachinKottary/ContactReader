package com.techtreeit.sample.techtree.dagger;

import com.techtreeit.sample.techtree.ui.TTBaseActivity;
import com.techtreeit.sample.techtree.ui.TTContactListViewHolder;
import com.techtreeit.sample.techtree.ui.fragments.TTBaseFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {TTAppModule.class})
public interface TTAppComponent {

     void inject(TTBaseActivity activity);

     void inject(TTBaseFragment fragment);

     void inject(TTContactListViewHolder holder);
}
