package cz.zcu.kiv.matyasj.dp.annotations;

/**
 * UIS annotation for labeling methods with error functionality.
 *
 * @author Jiri Matyas
 */
public @interface ErrorMethod {
    /** Short description of error functionality in methods */
    String errorMessage();
}
