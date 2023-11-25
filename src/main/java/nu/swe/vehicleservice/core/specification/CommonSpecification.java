package nu.swe.vehicleservice.core.specification;

import org.springframework.data.jpa.domain.Specification;

import java.util.Objects;

/**
 * A utility class for building Specifications commonly used in JPA Criteria Queries.
 *
 * @param <T> The type of the entity class to which the Specifications apply.
 */
public class CommonSpecification<T> {

    /**
     * Creates a Specification that always evaluates to true, effectively returning all records.
     *
     * @return A Specification that always evaluates to true.
     */
    public static <T> Specification<T> alwaysTrue() {
        return (root, query, cb) -> cb.and();
    }

    /**
     * Creates a Specification that checks if the specified attribute contains the given value
     * in a case-insensitive manner.
     *
     * @param attribute The name of the attribute to search.
     * @param value     The value to search for.
     * @return A Specification that checks if the attribute contains the value.
     */
    public static <T> Specification<T> contains(String attribute, String value) {
        Objects.requireNonNull(value, "Value should not be empty");
        Objects.requireNonNull(attribute, "Attribute should not be empty");

        var searchVal = "%" + value.trim().toLowerCase() + "%";
        return (root, query, cb) -> cb.like(cb.lower(root.get(attribute)), searchVal);
    }

    /**
     * Creates a Specification that checks if a nested attribute (nested within another attribute)
     * contains the given value in a case-insensitive manner.
     *
     * @param attributeFirst  The name of the outer attribute.
     * @param attributeSecond The name of the nested attribute within the outer attribute.
     * @param value           The value to search for.
     * @return A Specification that checks if the nested attribute contains the value.
     */
    public static <T> Specification<T> contains(String attributeFirst, String attributeSecond, String value) {
        Objects.requireNonNull(value, "Value should not be empty");
        Objects.requireNonNull(attributeFirst, "First attribute should not be empty");
        Objects.requireNonNull(attributeSecond, "Second attribute should not be empty");

        var searchVal = "%" + value.trim().toLowerCase() + "%";
        return (root, query, cb) -> cb.like(cb.lower(root.get(attributeFirst).get(attributeSecond)), searchVal);
    }

    /**
     * Creates a Specification that checks if the specified attribute equals the given value.
     *
     * @param attribute The name of the attribute to compare.
     * @param value     The value to compare with.
     * @return A Specification that checks if the attribute equals the value.
     */
    public static <T> Specification<T> attributeEquals(String attribute, Object value) {
        Objects.requireNonNull(attribute, "Attribute should not be empty");
        Objects.requireNonNull(value, "Value should not be empty");

        return (root, query, cb) -> cb.equal(root.get(attribute), value);
    }

    /**
     * Creates a Specification that checks if two attributes of an entity are equal.
     *
     * @param attribute1 The name of the first attribute to compare.
     * @param attribute2 The name of the second attribute to compare.
     * @return A Specification that checks if the two attributes are equal.
     */
    public static <T> Specification<T> attributeEquals(String attribute1, String attribute2, Object value) {
        Objects.requireNonNull(value, "value should not be empty");
        Objects.requireNonNull(attribute1, "First attribute should not be empty");
        Objects.requireNonNull(attribute2, "Second attribute should not be empty");

        return (root, query, cb) -> cb.equal(root.get(attribute1).get(attribute2), value);
    }

    /**
     * Creates a Specification that checks if the specified attribute equals the given value. Null Safe.
     *
     * @param attribute The name of the attribute to compare.
     * @param value     The value to compare with.
     * @return A Specification that checks if the attribute equals the value.
     */
    public static <T> Specification<T> safeEquals(String attribute, Object value) {
        Objects.requireNonNull(attribute, "Attribute should not be empty");
        if (value == null) {
            return Specification.where(null);
        }

        return (root, query, cb) -> cb.equal(root.get(attribute), value);
    }

    /**
     * Creates a Specification that checks if the specified comparable attribute is greater than/equal to the given value. Null-safe.
     *
     * @param attribute The name of the attribute to compare.
     * @param value     The value to compare with.
     * @return A Specification that checks if the attribute equals the value.
     */
    public static <T> Specification<T> safeGreaterThanOrEqualTo(String attribute, Comparable value) {
        Objects.requireNonNull(attribute, "Attribute should not be empty");
        if (value == null) {
            return Specification.where(null);
        }

        return (root, query, cb) -> cb.greaterThanOrEqualTo(root.get(attribute), value);
    }

    /**
     * Creates a Specification that checks if the specified comparable attribute is less than/equal to the given value. Null-safe.
     *
     * @param attribute The name of the attribute to compare.
     * @param value     The value to compare with.
     * @return A Specification that checks if the attribute equals the value.
     */
    public static <T> Specification<T> safeLessThanOrEqualTo(String attribute, Comparable value) {
        Objects.requireNonNull(attribute, "Attribute should not be empty");
        if (value == null) {
            return Specification.where(null);
        }

        return (root, query, cb) -> cb.lessThanOrEqualTo(root.get(attribute), value);
    }

}
