////
//// Decompiled by Procyon v0.5.36
////
//
//package com.xdevs.notification_service.utils;
//
//import javax.persistence.criteria.*;
//import java.util.Arrays;
//import java.util.Collection;
//import java.util.List;
//
//public class QueryUtils
//{
//
//    public static String buildLikeExp(final String query) {
//        if (query == null || !H.isTrue((Object)query.trim())) {
//            return null;
//        }
//        return "%" + query.trim().replaceAll("\\s+", "%") + "%";
//    }
//
//    public static <T> Predicate buildLikeFilter(final Root<T> root, final CriteriaBuilder cb, final String keyword, final String... fieldNames) {
//        final String likeExp = buildLikeExp(keyword);
//        if (!H.isTrue((Object)likeExp) || !H.isTrue((Object)fieldNames)) {
//            return null;
//        }
//        return cb.or((Predicate[])H.collect((List)Arrays.asList(fieldNames), (index, fieldName) -> cb.like(cb.upper((Expression)root.get((String) fieldName)), likeExp.toUpperCase())).toArray(new Predicate[0]));
//    }
//
//    public static <T> Predicate buildSimpleLikeFilter(final Root<T> root, final CriteriaBuilder cb, final String keyword, final String... fieldNames) {
//        if (!H.isTrue((Object)keyword) || !H.isTrue((Object)fieldNames)) {
//            return null;
//        }
//        return cb.or((Predicate[])H.collect((List)Arrays.asList(fieldNames), (index, fieldName) -> cb.like(cb.upper((Expression)root.get((String) fieldName)), ("%" + keyword + "%").toUpperCase())).toArray(new Predicate[0]));
//    }
//
//    public static <T, P> Predicate buildEqFilter(final Root<T> root, final CriteriaBuilder cb, final String fieldName, final P value) {
//        return H.isTrue((Object)value) ? cb.equal((Expression)root.get(fieldName), (Object)value) : null;
//    }
//
//    public static <T> Predicate buildIsDeleteFilter(final Root<T> root, final CriteriaBuilder cb) {
//        return cb.equal((Expression)root.get("isDelete"), (Object)false);
//    }
//
//    public static <T, P> Predicate buildInFilter(final Root<T> root, final String fieldName, final Collection<P> values) {
//        return H.isTrue((Object)values) ? root.get(fieldName).in((Collection)values) : null;
//    }
//
//}
