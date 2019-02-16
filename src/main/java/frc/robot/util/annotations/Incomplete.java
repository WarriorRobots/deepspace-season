package frc.robot.util.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.SOURCE) // SOURCE means the compiler will ignore this line, preventing crashes
@Target(ElementType.METHOD) // this annotation goes on methods/functions

/**
 * Specifies that a method isn't written yet.
 */
public @interface Incomplete {
}