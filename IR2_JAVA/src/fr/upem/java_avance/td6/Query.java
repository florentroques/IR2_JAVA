package fr.upem.java_avance.td6;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import fr.upem.java_avance.td6.Donor;
import fr.upem.java_avance.td6.Query.With;
import fr.upem.java_avance.td6.Query.With.Operator;

/**
 * A query that can be executed on a list of {@link Donor}.
 * By example, the queries:
 * <ul>
 * <li>all limit 10 <br>
 * is a Query object: selector=ALL, withs=[], sortedBy=NO_SORT, limit=10
 * <li>name sorted by amount <br>
 * is a Query object: selector=name, withs=[], sortedBy=amount, limit=NO_LIMIT
 * <li>all with name less than john <br>
 * is a Query object: selector=ALL, withs=[name LESS_THAN john],
 * sortedBy=NO_SORT, limit=NO_LIMIT
 * </ul>
 */
public class Query {
    // name of the field of {@link Donor} among
    // { name, gender, company, amount } or ALL
    // used to return only the value of the selected field for each {@link
    // Donor}.
    private final String selector;

    // a list of {@link With} object used to filter the donor list
    private final ArrayList<With> withs;

    // name of the field of {@link Donor} among
    // { name, gender, company, amount }
    // used to sort by or NO_SORT if there is no sort.
    private final String sortedBy;

    // number of result of a query or NO_LIMIT if there is no limit
    private final int limit;

    static final String ALL = "all";
    static final String NO_SORT = "NO_SORT";
    static final int NO_LIMIT = -1;

    /**
     * Represent a filter of the query.
     */
    static class With {
        enum Operator {
            GREATER_THAN, LESS_THAN, EQUALS_TO
        }

        // name of the field of {@link Donor} among
        // { name, gender, company, amount }
        private final String field;

        // an operator among {GREATER_THAN, LESS_THAN, EQUALS_TO}
        private final Operator operator;

        // a constant value (as a String)
        private final String constant;

        With(String field, Operator operator, String constant) {
            this.field = Objects.requireNonNull(field);
            this.operator = Objects.requireNonNull(operator);
            this.constant = Objects.requireNonNull(constant);
        }
    }

    Query(String selector, ArrayList<With> withs, String sortedBy, int limit) {
        if (limit < -1) {
            throw new IllegalArgumentException("invalid limit");
        }
        this.selector = Objects.requireNonNull(selector);
        this.withs = Objects.requireNonNull(withs);
        this.sortedBy = Objects.requireNonNull(sortedBy);
        this.limit = limit;
    }

    public static Query parse(String query) throws ParseException {
        return QueryParser.parse(query);
    }

    private <T> Stream<T> selectStream(Stream<Donor> stream, Class<T> type) {
        switch (selector) {
        case ALL:
            return stream.<T> map(donor -> type.cast(donor));
        case "name":
            return stream.<T> map(donor -> type.cast(donor.getName()));
        case "gender":
            return stream.<T> map(donor -> type.cast(donor.getGender()));
        case "amount":
            return stream.<T> map(donor -> type.cast(donor.getAmount()));
        default:
            //
            throw new AssertionError();
        }
    }

    public <T> List<T> execute(List<Donor> donors, Class<T> type) {
        return selectStream(donors.stream(), type).collect(Collectors.<T>toList());
        
    }
}