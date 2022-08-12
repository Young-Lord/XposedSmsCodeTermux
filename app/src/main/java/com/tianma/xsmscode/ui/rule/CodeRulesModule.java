package com.tianma.xsmscode.ui.rule;


import com.tianma.xsmscode.ui.app.FragmentScope;
import com.tianma.xsmscode.ui.rule.edit.RuleEditFragment;
import com.tianma.xsmscode.ui.rule.edit.RuleEditModule;
import com.tianma.xsmscode.ui.rule.list.RuleListFragment;
import com.tianma.xsmscode.ui.rule.list.RuleListModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class CodeRulesModule {

    @FragmentScope
    @ContributesAndroidInjector(modules = RuleListModule.class)
    abstract RuleListFragment contributeRuleListFragment();


    @FragmentScope
    @ContributesAndroidInjector(modules = RuleEditModule.class)
    abstract RuleEditFragment contributeRuleEditFragment();

}
