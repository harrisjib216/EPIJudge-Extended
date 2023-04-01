package epi.test_framework

import java.lang.annotation.ElementType

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
annotation class EpiUserType(val ctorParams: Array<Class<*>>)