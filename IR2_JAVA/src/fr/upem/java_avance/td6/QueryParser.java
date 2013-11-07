package fr.upem.java_avance.td6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import fr.upem.java_avance.td6.Query.With;
import fr.upem.java_avance.td6.Query.With.Operator;

class QueryParser {
    private QueryParser() {
        // no instance
    }

    static Query parse(String query) throws ParseException {
        Iterator<String> it = Arrays.asList(query.split(" ")).iterator();
        if (!it.hasNext()) {
            throw new ParseException("empty query ?");
        }
        String token = it.next();
        String selector = "all".equals(token) ? Query.ALL : ensureField(token);
        ArrayList<With> withs = new ArrayList<>();
        if (!it.hasNext()) {
            return new Query(selector, withs, Query.NO_SORT, Query.NO_LIMIT);
        }
        token = it.next();
        while ("with".equals(token)) {
            withs.add(parseWith(it));
            if (!it.hasNext()) {
                return new Query(selector, withs, Query.NO_SORT, Query.NO_LIMIT);
            }
            token = it.next();
        }
        String sortedBy = Query.NO_SORT;
        if ("sorted".equals(token)) {
            ensureToken(it, "by");
            if (!(it.hasNext())) {
                throw new ParseException(
                        "query too short, sorted by must be followed by a field");
            }
            sortedBy = ensureField(it.next());
            if (!it.hasNext()) {
                return new Query(selector, withs, sortedBy, Query.NO_LIMIT);
            }
            token = it.next();
        }
        if ("limit".equals(token)) {
            if (!(it.hasNext())) {
                throw new ParseException(
                        "query too short, limit must be followed by a value");
            }
            token = it.next();
            int limit;
            try {
                limit = Integer.parseInt(token);
            } catch (NumberFormatException e) {
                throw new ParseException(
                        "limit must be followed by an integer value: " + token);
            }
            if (!it.hasNext()) {
                return new Query(selector, withs, sortedBy, limit);
            }
            token = it.next();
        }
        throw new ParseException("invalid token " + token);
    }

    private static With parseWith(Iterator<String> it) throws ParseException {
        if (!it.hasNext()) {
            throw new ParseException(
                    "query too short, with must be followed by a field name");
        }
        String field = ensureField(it.next());
        Operator operator;
        if (!it.hasNext()) {
            throw new ParseException(
                    "query too short, with /field/ must be followed by an operator");
        }
        String token = it.next();
        switch (token) {
        case "equals":
            ensureToken(it, "to");
            operator = Operator.EQUALS_TO;
            break;
        case "less":
            ensureToken(it, "than");
            operator = Operator.LESS_THAN;
            break;
        case "greater":
            ensureToken(it, "than");
            operator = Operator.GREATER_THAN;
            break;
        default:
            throw new ParseException("invalid with operator " + token);
        }
        if (!it.hasNext()) {
            throw new ParseException(
                    "query too short, with /field/ operator must be followed by a constant");
        }
        String constant = it.next();
        return new With(field, operator, constant);
    }

    private static void ensureToken(Iterator<String> it, String expected)
            throws ParseException {
        if (!(it.hasNext())) {
            throw new ParseException("query too short, expect token "
                    + expected);
        }
        String token = it.next();
        if (!expected.equals(token)) {
            throw new ParseException("expect " + expected + " but found "
                    + token);
        }
    }

    private static String ensureField(String token) throws ParseException {
        switch (token) {
        case "name":
        case "gender":
        case "company":
        case "amount":
            return token;
        default:
            throw new ParseException("unknown field " + token);
        }
    }
}