package com.tpsoares.springbootmicroservicesapiboilerplate.core;

import java.util.Objects;
import java.util.function.Function;

public interface Result<T, E> {

    static <T, E> Success<T, E> success(T value) {
        return new Success<>(value);
    }

    static <T, E> Error<T, E> error(E value) {
        return new Error<>(value);
    }

    <U> Result<U, E> map(Function<T, U> mapping);

    <F> Result<T, F> mapError(Function<E, F> mapping);

    <U> Result<U, E> flatMap(Function<T, Result<U, E>> mapping);

    <F> Result<T, F> flatMapError(Function<E, Result<T, F>> mapping);

    T orElse(T ifError);

    T orElse(Function<E, T> ifError);

    class Success<T, E> implements Result<T, E> {
        private final T value;

        private Success(T value) {
            this.value = value;
        }

        @Override
        public <U> Result<U, E> map(Function<T, U> mapping) {
            return success(mapping.apply(value));
        }

        @Override
        public <F> Result<T, F> mapError(Function<E, F> mapping) {
            return success(value);
        }

        @Override
        public <U> Result<U, E> flatMap(Function<T, Result<U, E>> mapping) {
            return mapping.apply(value);
        }

        @Override
        public <F> Result<T, F> flatMapError(Function<E, Result<T, F>> mapping) {
            return success(value);
        }

        @Override
        public T orElse(T ifError) {
            return value;
        }

        @Override
        public T orElse(Function<E, T> ifError) {
            return value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Success<?, ?> success = (Success<?, ?>) o;

            return Objects.equals(value, success.value);
        }

        @Override
        public int hashCode() {
            return value != null ? value.hashCode() : 0;
        }
    }

    class Error<T, E> implements Result<T, E> {
        private final E value;

        public Error(E value) {
            this.value = value;
        }

        @Override
        public <U> Result<U, E> map(Function<T, U> mapping) {
            return error(value);
        }

        @Override
        public <F> Result<T, F> mapError(Function<E, F> mapping) {
            return error(mapping.apply(value));
        }

        @Override
        public <U> Result<U, E> flatMap(Function<T, Result<U, E>> mapping) {
            return error(value);
        }

        @Override
        public <F> Result<T, F> flatMapError(Function<E, Result<T, F>> mapping) {
            return mapping.apply(value);
        }

        @Override
        public T orElse(T ifError) {
            return ifError;
        }

        @Override
        public T orElse(Function<E, T> ifError) {
            return ifError.apply(value);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Error<?, ?> error = (Error<?, ?>) o;

            return Objects.equals(value, error.value);
        }

        @Override
        public int hashCode() {
            return value != null ? value.hashCode() : 0;
        }
    }
}

