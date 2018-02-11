package com.sannacode.test.contacts.di.scopes;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by Andrew on 11.02.2018.
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface FragmentScope {
}
