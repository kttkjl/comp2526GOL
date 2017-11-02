package ca.bcit.comp2526.a2a;

/**
 * The types of Entities allowed in a Cell.
 * A hack to not using instanceOf.
 * @author Jacky
 * @version 1.0a
 */
public enum EntityType {
    /**
     * Default entity.
     */
    EMPTY,
    /**
     * Plant Entity.
     */
    PLANT,
    /**
     * Herbivore Entity.
     */
    HERBIVORE
}
