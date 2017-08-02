/**
 * Copyright (c) 2017 European Organisation for Nuclear Research (CERN), All Rights Reserved.
 */

package cern.molr.type.either;

import java.util.function.Function;

public class Right<RL,RR> implements Either<RL, RR>{

    RR r;
    
    public Right(RR r) {
        this.r = r;
    }

    @Override
    public <T> T match(Function<RL, T> a, Function<RR, T> b) {
        return b.apply(r);
    }

    @Override
    public <S> Either<RL, S> bind(Function<RR, Either<RL, S>> f) {
        return f.apply(r);
    }

    @Override
    public <S> Either<RL, S> fmap(Function<RR, S> f) {
        return new Right<>(f.apply(r));
    }
    
}