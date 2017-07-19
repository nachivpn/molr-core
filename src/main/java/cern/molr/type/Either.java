/**
 * Copyright (c) 2017 European Organisation for Nuclear Research (CERN), All Rights Reserved.
 */

package cern.molr.type;

import java.util.function.Function;
/**
 * A simple data type for constructing a disjoint union (or sum type) of two types
 * 
 * @author nachivpn 
 * @param <L>
 * @param <R>
 */
public abstract class Either<L, R>{

    public abstract <T> T match(Function<L, T> a, Function<R, T> b);
    
    public final class Left extends Either<L, R>{

        L l;
        
        public Left(L l) {
            this.l = l;
        }

        @Override
        public <T> T match(Function<L, T> a, Function<R, T> b) {
            return a.apply(l);
        }
        
    }
    
    public final class Right extends Either<L, R>{

        R r;
        
        public Right(R r) {
            this.r = r;
        }

        @Override
        public <T> T match(Function<L, T> a, Function<R, T> b) {
            return b.apply(r);
        }
        
    }
    
}
