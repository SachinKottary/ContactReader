/*
 *
 *  *
 *  *  *  * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *  *  *  * @Project:
 *  *  *  *		 VOOT
 *  *  *  * @Copyright:
 *  *  *  *     		Copyright © 2017, Viacom18 Media Private Limited. All Rights Reserved *
 *  *  *  * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *  *
 *  *
 *
 */

package com.techtreeit.sample.techtree.rx;

import io.reactivex.subjects.PublishSubject;

public class RxBus {
    private final PublishSubject<Object> bus = PublishSubject.create();

    public void send(final Object event) {
        bus.onNext(event);
    }

    public PublishSubject<Object> getBus() {
        return bus;
    }

    public boolean hasObservers() {
        return bus.hasObservers();
    }
} 