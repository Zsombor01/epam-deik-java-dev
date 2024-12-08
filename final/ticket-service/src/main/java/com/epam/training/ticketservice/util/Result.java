package com.epam.training.ticketservice.util;

import org.jetbrains.annotations.UnknownNullability;

import java.util.function.Function;

public sealed interface Result<T, E extends Exception> permits Result.Ok, Result.Err {
    @UnknownNullability
    T result();

    @UnknownNullability
    E error();

    boolean isOk();

    boolean isErr();

    State state();

    <N> Result<N, E> mapResult(Function<T, N> mapper);

    <N extends Exception> Result<T, N> mapError(Function<E, N> mapper);

    static <T, E extends Exception> Result<T, E> ok(T result){
        return new Ok<>(result);
    }

    static <T, E extends Exception> Result<T, E> err(E exception){
        return new Err<>(exception);
    }

    enum State {
        OK, ERROR
    }

    record Ok<T, E extends Exception>(T result) implements Result<T, E>{

        @Override
        public E error(){
            throw new IllegalStateException("Tried to access error value on OK result");
        }

        @Override
        public boolean isOk(){
            return true;
        }

        @Override
        public boolean isErr(){
            return false;
        }

        public State state(){
            return State.OK;
        }

        @Override
        public <N> Result<N, E> mapResult(Function<T, N> mapper){
            return new Ok<>(mapper.apply(result()));
        }

        @Override
        public <N extends Exception> Result<T, N> mapError(Function<E, N> mapper){
            throw new IllegalStateException("Tried to map error value on OK result");
        }
    }

    record Err<T, E extends Exception>(E error) implements Result<T, E>{

        @Override
        public T result(){
            throw new IllegalStateException("Tried to access error value on Err result");
        }

        @Override
        public boolean isOk(){
            return false;
        }

        @Override
        public boolean isErr(){
            return true;
        }

        @Override
        public State state(){
            return State.ERROR;
        }

        @Override
        public <N> Result<N, E> mapResult(Function<T, N> mapper){
            throw new IllegalStateException("Tried to map result value on Err result");
        }

        @Override
        public <N extends Exception> Result<T, N> mapError(Function<E, N> mapper){
            return new Err<>(mapper.apply(error));
        }
    }
}
