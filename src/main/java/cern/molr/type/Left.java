/**
 * Copyright (c) 2017 European Organisation for Nuclear Research (CERN), All Rights Reserved.
 */

package cern.molr.type;

import java.util.function.Function;

public class Left<LL, LR> implements Either<LL, LR>{

    LL l;
    
    public Left(LL l) {
        this.l = l;
    }

    @Override
    public <T> T match(Function<LL, T> a, Function<LR, T> b) {
        return a.apply(l);
    }

    @Override
    public <S> Either<LL, S> bind(Function<LR, Either<LL, S>> f) {
        return new Left<>(l);
    }

    @Override
    public <S> Either<LL, S> fmap(Function<LR, S> f) {
        return new Left<>(l);
    }

}

